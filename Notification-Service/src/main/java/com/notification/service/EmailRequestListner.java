package com.notification.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.notification.constants.AppConstants;
import com.notification.payload.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailRequestListner {
    @Autowired
    private JavaMailSender mailSender;

    @KafkaListener(topics = AppConstants.TOPIC, groupId="notification-group")
    public void kafakSubscriberContent(String emailRequest) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            EmailRequest emailContaint = mapper.readValue(emailRequest, EmailRequest.class);
            SimpleMailMessage sm = new SimpleMailMessage();
            sm.setTo(emailContaint.getTo());
            sm.setSubject(emailContaint.getSubject());
            sm.setText(emailContaint.getBody());
            mailSender.send(sm);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


    }
}
