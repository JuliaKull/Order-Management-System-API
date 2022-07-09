package com.bta.email;

public interface EmailService {

    void sendEmail(String to, String text, String subject);
}
