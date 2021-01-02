package com.stargate.demo.mail;

public interface EmailService {
    void sendSimpleMessage(String to,
            String subject,
            String text);

}
