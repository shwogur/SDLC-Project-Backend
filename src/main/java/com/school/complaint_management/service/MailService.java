package com.school.complaint_management.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    public void sendVerificationCode(String email, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("[학생 민원 시스템] 이메일 인증번호");
        message.setText("인증번호: " + code + "\n6자리 번호를 입력해주세요.");

        mailSender.send(message);
    }
}
