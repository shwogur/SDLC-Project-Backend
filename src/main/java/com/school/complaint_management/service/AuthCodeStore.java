package com.school.complaint_management.service;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AuthCodeStore {

    private final Map<String, String> codeMap = new ConcurrentHashMap<>();

    public void saveCode(String email, String code) {
        codeMap.put(email, code);
    }

    public boolean verifyCode(String email, String inputCode) {
        return codeMap.containsKey(email) && codeMap.get(email).equals(inputCode);
    }

    public void removeCode(String email) {
        codeMap.remove(email);
    }
}
