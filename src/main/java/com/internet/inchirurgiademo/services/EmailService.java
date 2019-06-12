package com.internet.inchirurgiademo.services;

public interface EmailService {

    void send(String sendTo, String subject, String text);
}
