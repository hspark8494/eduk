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
public class EmailService {

	private final JavaMailSender emailSender;
	public static final String ePw = createKey(); // 인증번호 저장

	private MimeMessage createMessage(String email) throws Exception {
		System.out.println("보내는 대상 : " + email);
		System.out.println("인증 번호 : " + ePw);
		MimeMessage message = emailSender.createMimeMessage();

		String code = createCode(ePw);
		message.addRecipients(RecipientType.TO, email); // 보내는 대상
		message.setSubject("eduK 인증번호가 도착했습니다. "); // 제목

		String msgg = "";
		msgg += "<table style=\"width: 100%; font-family: Montserrat, -apple-system, 'Segoe UI', sans-serif;\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\r\n"
				+ "            <tr>\r\n"
				+ "              <td align=\"center\" style=\"mso-line-height-rule: exactly; background-color: #eceff1; font-family: Montserrat, -apple-system, 'Segoe UI', sans-serif;\">\r\n"
				+ "                <table class=\"sm-w-full\" style=\"width: 600px;\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\r\n"
				+ "                  <tr>\r\n"
				+ "        <td class=\"sm-py-32 sm-px-24\" style=\"mso-line-height-rule: exactly; padding: 48px; text-align: center; font-family: Montserrat, -apple-system, 'Segoe UI', sans-serif;\">\r\n"
				+ "          \r\n" + "        </td>\r\n" + "        </tr>\r\n" + "                    <tr>\r\n"
				+ "                      <td align=\"center\" class=\"sm-px-24\" style=\"font-family: 'Montserrat', sans-serif; mso-line-height-rule: exactly;\">\r\n"
				+ "                        <table style=\"width: 100%;\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\r\n"
				+ "                          <tr>\r\n"
				+ "                            <td class=\"sm-px-24\" style=\"mso-line-height-rule: exactly; border-radius: 4px; background-color: #ffffff; padding: 48px; text-align: left; font-family: Montserrat, -apple-system, 'Segoe UI', sans-serif; font-size: 16px; line-height: 24px; color: #626262;\">\r\n"
				+ "                              <p style=\"font-family: 'Montserrat', sans-serif; mso-line-height-rule: exactly; margin-bottom: 0; font-size: 17px; font-weight: 600;\">안녕하세요</p>\r\n"
				+ "                              <p style=\"font-family: 'Montserrat', sans-serif; mso-line-height-rule: exactly; margin-top: 0; margin-bottom: 30px; font-size: 24px; font-weight: 700; color: #ff5850;\">eduK입니다.</p>\r\n"
				+ "                              <p class=\"sm-leading-32\" style=\"font-family: 'Montserrat', sans-serif; mso-line-height-rule: exactly; margin: 0; margin-bottom: 16px; font-size: 22px; font-weight: 600; color: #263238;\">\r\n"
				+ "                                저희 서비스를 이용해주셔서 감사합니다! 👋\r\n"
				+ "                              </p>\r\n"
				+ "                              <p style=\"font-family: 'Montserrat', sans-serif; mso-line-height-rule: exactly; margin: 0; margin-bottom: 24px;\">\r\n"
				+ "                                이메일로 수신한 아래의 인증코드를 eduK 페이지에 입력해주시면 <br>\r\n"
				+ "                                인증이 완료됩니다.\r\n" + "                              </p>\r\n"
				+ "                              <p style=\"font-family: 'Montserrat', sans-serif; mso-line-height-rule: exactly; margin: 0; margin-bottom: 24px;\">\r\n"
				+ "                                만약 이상이 있는 경우, 아래 메일로 문의주세요.\r\n"
				+ "                                <a href=\"mailto:100sungha@gmail.com\" class=\"hover-underline\" style=\"font-family: 'Montserrat', sans-serif; mso-line-height-rule: exactly; color: #7367f0; text-decoration: none;\">100sungha@gmail.com</a>\r\n"
				+ "                              </p>\r\n"
				+ "                              <a href=\"https://pixinvent.com?verification_url\" style=\"font-family: 'Montserrat', sans-serif; mso-line-height-rule: exactly; margin-bottom: 24px; display: block; font-size: 16px; line-height: 100%; color: #7367f0; text-decoration: none;\"></a>\r\n"
				+ "                              <div>\r\n" + "                              <p >인증코드 : <strong>";
		msgg += ePw + "</strong></p>\r\n";
		msgg += "                              </div>\r\n"
				+ "                              <table cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\r\n"
				+ "                                <tr>\r\n" + "                                  <td>\r\n"
				+ "                                  </td>\r\n" + "                                </tr>\r\n"
				+ "                              </table>\r\n"
				+ "                              <table style=\"width: 100%;\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\r\n"
				+ "        <tr>\r\n"
				+ "          <td style=\"font-family: 'Montserrat', sans-serif; mso-line-height-rule: exactly; padding-top: 32px; padding-bottom: 32px;\">\r\n"
				+ "            <div style=\"font-family: 'Montserrat', sans-serif; mso-line-height-rule: exactly; height: 1px; background-color: #eceff1; line-height: 1px;\">&zwnj;</div>\r\n"
				+ "          </td>\r\n" + "        </tr>\r\n" + "        </table>\r\n"
				/*+ 
					 * "        <p style=\"font-family: 'Montserrat', sans-serif; mso-line-height-rule: exactly; margin: 0; margin-bottom: 16px;\">\r\n"
					 * + "        인증을 중단하고 메인페이지로 돌아가시겠습니까?\r\n" +
					 * "        <a href=\"https://localhost:1234/\" class=\"hover-underline\" style=\"font-family: 'Montserrat', sans-serif; mso-line-height-rule: exactly; color: #7367f0; text-decoration: none;\">여기를 클릭하세요</a>.\r\n"
					 * + "        </p>\r\n"
					 */
				+ "        <p style=\"font-family: 'Montserrat', sans-serif; mso-line-height-rule: exactly; margin: 0; margin-bottom: 16px;\">감사합니다, <br>eduK팀으로부터</p>\r\n"
				+ "                            </td>\r\n" + "                          </tr>\r\n"
				+ "                          <tr>\r\n"
				+ "        <td style=\"font-family: 'Montserrat', sans-serif; mso-line-height-rule: exactly; height: 20px;\"></td>\r\n"
				+ "        </tr>\r\n" + "        <tr>\r\n"
				+ "        <td style=\"mso-line-height-rule: exactly; padding-left: 48px; padding-right: 48px; font-family: Montserrat, -apple-system, 'Segoe UI', sans-serif; font-size: 14px; color: #eceff1;\">\r\n"
				+ "          <p align=\"center\" style=\"font-family: 'Montserrat', sans-serif; mso-line-height-rule: exactly; margin-bottom: 16px; cursor: default;\">\r\n"
				+ "          </p>\r\n"
				+ "          <p style=\"font-family: 'Montserrat', sans-serif; mso-line-height-rule: exactly; color: #263238;\">\r\n"
				+ "            서비스 및 웹사이트의 사용은 당사의\r\n"
				+ "            <a href=\"#\" class=\"hover-underline\" style=\"font-family: 'Montserrat', sans-serif; mso-line-height-rule: exactly; color: #7367f0; text-decoration: none;\">이용약관</a> 및\r\n"
				+ "            <a href=\"#\" class=\"hover-underline\" style=\"font-family: 'Montserrat', sans-serif; mso-line-height-rule: exactly; color: #7367f0; text-decoration: none;\">개인정보 보호정책</a>\r\n"
				+ "            의 적용을 받습니다.\r\n" + "          </p>\r\n" + "        </td>\r\n" + "        </tr>\r\n"
				+ "        <tr>\r\n"
				+ "        <td style=\"font-family: 'Montserrat', sans-serif; mso-line-height-rule: exactly; height: 16px;\"></td>\r\n"
				+ "        </tr>\r\n" + "                        </table>\r\n" + "                      </td>\r\n"
				+ "                    </tr>\r\n" + "                </table>\r\n" + "              </td>\r\n"
				+ "            </tr>\r\n" + "          </table>";
		message.setText(msgg, "utf-8", "html");// 내용
		message.setFrom(new InternetAddress("100sungha@gmail.com", "eduk 직원1"));// 보내는 사람

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
				// a~z (ex. 1+97=98 => (char)98 = 'b')
				break;
			case 1:
				key.append((char) ((int) (random.nextInt(26)) + 65));
				// A~Z
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
		try {// 예외처리
			emailSender.send(message);
		} catch (MailException es) {
			es.printStackTrace();
			throw new IllegalArgumentException();
		}
	}

	public String createCode(String ePw) {
		return ePw.substring(0, 3) + "-" + ePw.substring(3, 6);
	}
}
