package com.interviewace.controller;

import com.interviewace.dto.request.LoginEmailRequest;
import com.interviewace.dto.request.LoginMobileRequest;
import com.interviewace.dto.request.RegisterRequest;
import com.interviewace.dto.response.AuthResponse;
import com.interviewace.dto.response.MessageResponse;
import com.interviewace.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public MessageResponse register(
            @Valid @RequestBody RegisterRequest request) {

        String message = authService.register(request);

        return new MessageResponse(message);
    }

    @PostMapping("/login-email")
    public AuthResponse loginByEmail(
            @Valid @RequestBody LoginEmailRequest request) {

        return authService.loginByEmail(request);
    }

    @PostMapping("/login-mobile")
    public AuthResponse loginByMobile(
            @Valid @RequestBody LoginMobileRequest request) {

        return authService.loginByMobile(request);
    }
}