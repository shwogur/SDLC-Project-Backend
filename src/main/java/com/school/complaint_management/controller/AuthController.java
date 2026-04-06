package com.school.complaint_management.controller;

import com.school.complaint_management.service.AuthCodeStore;
import com.school.complaint_management.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MailService mailService;
    private final AuthCodeStore authCodeStore;

    // 인증번호 발송
    @PostMapping("/send-code")
    public String sendCode(@RequestParam String email) {
        String code = String.format("%06d", new Random().nextInt(900000) + 100000);

        mailService.sendVerificationCode(email, code);
        authCodeStore.saveCode(email, code);

        return "OK";  // 더 이상 인증번호를 프론트에 보내지 않음
    }

    // 인증번호 검증
    @PostMapping("/verify")
    public boolean verifyCode(
            @RequestParam String email,
            @RequestParam String code
    ) {
        boolean valid = authCodeStore.verifyCode(email, code);

        if (valid) authCodeStore.removeCode(email);

        return valid;
    }
}
