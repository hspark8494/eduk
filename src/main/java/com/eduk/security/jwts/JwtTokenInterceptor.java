package com.eduk.security.jwts;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT의 유효성을 검사하는 인터셉터 클래스
 * 
 * @author 박효승
 */
@Component
@RequiredArgsConstructor
public class JwtTokenInterceptor implements HandlerInterceptor {

  private final TokenProvider tokenProvider;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws IOException {

    String accessToken = request.getHeader("authorization");
System.out.println("인터셉터 accessToken : " + accessToken);
    if (accessToken != null) {
      if (tokenProvider.isValidToken(accessToken)) {
        return true;
      }
    }
    
    //TODO : Refresh Token 재발행
    
    response.setStatus(401);
    response.setHeader("accessToken", accessToken);
    response.setHeader("msg", "Check the tokens.");
    return false;
  }
}
