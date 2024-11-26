package com.example.dacn.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class emailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String getTo, String getSubject, String getBody) throws MessagingException {
        // Tạo MimeMessage từ mailSender
        MimeMessage message = mailSender.createMimeMessage();

        // Tạo MimeMessageHelper để hỗ trợ việc gửi email với định dạng HTML
        MimeMessageHelper helper = new MimeMessageHelper(message, true); // true để hỗ trợ đính kèm

        // Thiết lập các thuộc tính cho email
        helper.setTo(getTo);
        helper.setSubject(getSubject);

        // Thiết lập nội dung email dưới dạng HTML
        helper.setText(getBody, true); // true để thiết lập body email dưới dạng HTML

        // Gửi email
        mailSender.send(message);
    }
}
