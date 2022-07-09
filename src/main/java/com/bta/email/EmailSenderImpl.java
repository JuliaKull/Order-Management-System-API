package com.bta.email;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSenderImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String to, String text, String subject) {
        final var mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("jk7102777@gmail.com");
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        System.out.println("email was sent");
        System.out.println("link" + text);
        javaMailSender.send(mailMessage);

    }
}
