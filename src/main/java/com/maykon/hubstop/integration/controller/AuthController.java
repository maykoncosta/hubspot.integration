package com.maykon.hubstop.integration.controller;

import com.maykon.hubstop.integration.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/install")
    public ResponseEntity<String> getAuthorizationUrl() {
        String url = authService.getAuthorizationUrl();
        return ResponseEntity.ok(url);
    }

    @GetMapping("/callback")
    public ResponseEntity<String> callback(@RequestParam String code) {
        authService.exchangeCodeForToken(code);
        return ResponseEntity.ok("Token recebido com sucesso!");
    }

    @GetMapping("/token")
    public String getToken() {
        String token = authService.getToken();
        return token != null ? token : "Token não encontrado!";
    }
}
