package com.eduk.webrtc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eduk.domain.ClassRoom;
import com.eduk.repository.ClassRoomRepository;
import com.eduk.repository.MemberRepository;
import com.eduk.security.jwts.TokenProvider;

import io.openvidu.java.client.ConnectionProperties;
import io.openvidu.java.client.ConnectionType;
import io.openvidu.java.client.OpenVidu;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import io.openvidu.java.client.OpenViduRole;
import io.openvidu.java.client.Session;
import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
public class SessionController {
	private OpenVidu openVidu;

	@Autowired
	private ClassRoomRepository classRoomRepository;

	@Autowired
	private MemberRepository memberRep;

	private Map<Long, Session> roomSession = new ConcurrentHashMap<>();
	private Map<String, Map<Long, String>> roomUserToken = new ConcurrentHashMap<>();

	private String OPENVIDU_URL;
	private String SECRET;

	public SessionController(@Value("${openvidu.secret}") String secret, @Value("${openvidu.url}") String openviduUrl) {
		this.SECRET = secret;
		this.OPENVIDU_URL = openviduUrl;
		this.openVidu = new OpenVidu(OPENVIDU_URL, SECRET);
	}

	@RequestMapping(value = "/create-session", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> createSession(@RequestBody SessionMapperDTO roomId, @RequestHeader HttpHeaders httpHeaders) {
		Long MemberId = TokenProvider.getIdFormHeader(httpHeaders);
		

//		if (!user.hasRoleTeacher()) {
//			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//		}

		long room_Id = roomId.getRoomId();

		ClassRoom room = classRoomRepository.findById(room_Id).get();

//		if (!checkAuthorization(c, c.getTeacher())) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}

		if (this.roomSession.get(room_Id) != null) {
			// If there's already a valid Session object for this lesson,
			// it is not necessary to ask for a new one
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			try {
				Session session = this.openVidu.createSession();

				this.roomSession.put(room_Id, session);
				this.roomUserToken.put(session.getSessionId(), new HashMap<>());

				showMap();

				return new ResponseEntity<>(HttpStatus.OK);
			} catch (Exception e) {
				return getErrorResponse(e);
			}
		}
	}
	
	@Data
	@AllArgsConstructor
	static class SessionMapperDTO{
		long roomId;
		
		SessionMapperDTO(){}
	}

	@RequestMapping(value = "/generate-token", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> generateToken(@RequestBody SessionMapperDTO roomId, @RequestHeader HttpHeaders header) {
		System.out.println(roomId);

		long room_Id = roomId.getRoomId();

		ClassRoom cr = classRoomRepository.findById(room_Id).get();

//		if (!checkAuthorizationUsers(c, c.getAttenders())) {
//			System.out.println("Not authorized");
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}

		if (this.roomSession.get(room_Id) == null) {
			System.out.println("There's no Session fot this lesson");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		Session session = this.roomSession.get(room_Id);
		//OpenViduRole role = user.hasRoleTeacher() ? OpenViduRole.PUBLISHER : OpenViduRole.SUBSCRIBER;
		OpenViduRole role = OpenViduRole.PUBLISHER;
		
//		JSONObject responseJson = new JSONObject();
//		TokenOptions tokenOpts = new TokenOptions.Builder().role(role)
//				.data("SERVER=" + this.user.getLoggedUser().getName()).build();
//		System.out.println(tokenOpts);
		String serverData = "{\"serverData\": \"Hello\"}";
		ConnectionProperties connectionProperties = new ConnectionProperties.Builder().type(ConnectionType.WEBRTC).data(serverData).role(role).build();

		try {
			//String token = this.roomSession.get(roomId).generateToken(connectionProperties);
			String token = this.roomSession.get(room_Id).createConnection(connectionProperties).getToken();
			//this.sessionIdUserIdToken.get(session.getSessionId()).put(this.user.getLoggedUser().getId(), token);
			//responseJson.put(0, token);
			Map<String, String> map = new HashMap<String, String>();
			map.put("0", token);
			showMap();

			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		} catch (OpenViduJavaClientException e1) {
			// If internal error generate an error message and return it to client
			System.err.println(e1);
			return getErrorResponse(e1);
		} catch (OpenViduHttpException e2) {
			System.err.println(e2);
			if (404 == e2.getStatus()) {
				// Invalid sessionId (user left unexpectedly). Session object is not valid
				// anymore. Must clean invalid session and create a new one
				System.out.println("USER LEFT");
					
//					this.sessionIdUserIdToken.remove(session.getSessionId());
//					session = this.openVidu.createSession();
//					this.lessonIdSession.put(id_lesson, session);
//					this.sessionIdUserIdToken.put(session.getSessionId(), new HashMap<>());
//					String token = session.generateToken(tokenOpts);
//					// END IMPORTANT STUFF
//
//					this.sessionIdUserIdToken.get(session.getSessionId()).put(this.user.getLoggedUser().getId(), token);
//					responseJson.put(0, token);
//					showMap();

					return new ResponseEntity<>(null, HttpStatus.OK);

			} else {
				return getErrorResponse(e2);
			}
		}
	}

//	@RequestMapping(value = "/remove-user", method = RequestMethod.POST)
//	public ResponseEntity<JSONObject> removeUser(@RequestBody String lessonId) throws Exception {
//
//		if (!this.userIsLogged()) {
//			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//		}
//
//		long id_lesson = -1;
//		try {
//			id_lesson = Long.parseLong(lessonId);
//		} catch (NumberFormatException e) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//
//		Lesson c = lessonRepository.findById(id_lesson).get();
//
//		if (!checkAuthorizationUsers(c, c.getAttenders())) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//		if (this.lessonIdSession.get(id_lesson) == null) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//		String sessionId = this.lessonIdSession.get(id_lesson).getSessionId();
//		if (this.sessionIdUserIdToken.get(sessionId).remove(this.user.getLoggedUser().getId()) != null) {
//			// This user has left the lesson
//			if (this.sessionIdUserIdToken.get(sessionId).isEmpty()) {
//				// The last user has left the lesson
//				this.lessonIdSession.remove(id_lesson);
//				this.sessionIdUserIdToken.remove(sessionId);
//			}
//
//			showMap();
//
//			return new ResponseEntity<>(HttpStatus.OK);
//		} else {
//			System.out.println("Problems in the app server: the user didn't have a valid token");
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

	private void showMap() {
		System.out.println("------------------------------");
		System.out.println(this.roomSession.toString());
		System.out.println(this.roomUserToken.toString());
		System.out.println(roomUserToken.size());
		System.out.println(roomSession.size());
		System.out.println("------------------------------");
	}

//	private boolean userIsLogged() {
//		if (!user.isLoggedUser()) {
//			System.out.println("Not user logged");
//			return false;
//		}
//		return true;
//	}

	private ResponseEntity<Map<String, String>> getErrorResponse(Exception e) {
		Map<String, String> map = new HashMap<String,String>();
		map.put("cause", e.getCause().toString());
		map.put("error", e.getMessage());
		map.put("exception", e.getClass().toGenericString());
		return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// Authorization checking for creating or joining a certain lesson
//	private boolean checkAuthorization(Object o, User u) {
//		return !(o == null || !this.user.getLoggedUser().equals(u));
//	}

	// Authorization checking for joining a session (the user must be an attender)
//	private boolean checkAuthorizationUsers(Object o, Collection<User> users) {
//		return !(o == null || !users.contains(this.user.getLoggedUser()));
//	}

}
