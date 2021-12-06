package com.eduk.repository;

import java.util.Map;

public interface EmailUtil {

	Map<String, Object> sendEmail(String toAddress, String subject, String body);
}
