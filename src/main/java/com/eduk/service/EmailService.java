package com.eduk.service;

import java.util.Random;

import java.util.Random;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmailService{

	private final JavaMailSender emailSender;
    public static final String ePw = createKey(); //인증번호 저장

    private MimeMessage createMessage(String email) throws Exception{
        System.out.println("보내는 대상 : "+ email);
        System.out.println("인증 번호 : " + ePw);
        MimeMessage  message = emailSender.createMimeMessage();

        String code = createCode(ePw);
        message.addRecipients(RecipientType.TO, email); //보내는 대상
        message.setSubject("eduK 회원가입 인증번호가 도착했습니다. "); //제목

        String msgg="";
        msgg+= "<div style='margin:100px;'>";
       	msgg+= "<h1> 안녕하세요. eduK입니다. </h1>";
        msgg+= "<br>";
        msgg+= "<p>아래 코드를 회원가입 창으로 돌아가 입력하세요<p>";
        msgg+= "<br>";
        msgg+= "<p>감사합니다!<p>";
        msgg+= "<br>";
		msgg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
		msgg+= "<h3 style='color:blue;'>회원가입 코드입니다.</h3>";
		msgg+= "<div style='font-size:130%'>";
		msgg+= "CODE : <strong>";
		msgg+= ePw+"</strong><div><br/> ";
		msgg+= "</div>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress("100sungha@gmail.com","eduk 직원1"));//보내는 사람
        
        return message;
    }

    // 인증코드 만들기
    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random random = new Random();

        for (int i = 0; i < 8; i++) { // 인증코드 8자리
			int index = random.nextInt(3); // int형 수 0~2 까지 랜덤으로 반환

			switch (index) {
			case 0:
				key.append((char) ((int) (random.nextInt(26)) + 97));
				//  a~z  (ex. 1+97=98 => (char)98 = 'b')
				break;
			case 1:
				key.append((char) ((int) (random.nextInt(26)) + 65));
				//  A~Z 
				break;
			case 2:
				key.append((random.nextInt(10)));
				// 0~9
				break;
			}
		}
        
        return key.toString();
    }

    public void sendSimpleMessage(String email) throws Exception {
        MimeMessage message = createMessage(email);
        try{//예외처리
            emailSender.send(message);
        }catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    public String createCode(String ePw){
        return ePw.substring(0, 3) + "-" + ePw.substring(3, 6);
    }
}
