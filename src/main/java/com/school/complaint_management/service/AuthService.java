package com.school.complaint_management.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MailService mailService;

    // (email -> {code, expiresAt})
    private final Map<String, VerificationData> codeStore = new HashMap<>();

    public void sendCode(String email) {
        String code = String.format("%06d", new Random().nextInt(900000) + 100000);

        // 이메일로 전송
        mailService.sendVerificationCode(email, code);

        // 백엔드에 인증번호 저장 (유효시간 5분)
        codeStore.put(email, new VerificationData(code, LocalDateTime.now().plusMinutes(5)));
    }

    public boolean verifyCode(String email, String code) {
        VerificationData data = codeStore.get(email);

        if (data == null) return false;
        if (LocalDateTime.now().isAfter(data.expiresAt())) return false; // 만료됨
        return data.code().equals(code); // 인증번호 일치?
    }

    // 인증번호 + 만료 시간 저장용 record
    public record VerificationData(String code, LocalDateTime expiresAt) {}
}
