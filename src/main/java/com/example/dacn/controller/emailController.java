package com.example.dacn.controller;

import com.example.dacn.service.emailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/email")
public class emailController {
    @Autowired
    private emailService emailService;

    @PostMapping("/send")
    public void sendEmail(@RequestBody Map<String, Object> data) throws MessagingException {
        String getTo = (String) data.get("getTo");
        String getSubject = (String) data.get("getSubject");
        String getBody = (String) data.get("getBody");
        emailService.sendSimpleEmail(getTo, getSubject, getBody);
    }
}
