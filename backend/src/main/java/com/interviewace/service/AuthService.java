package com.interviewace.service;

import com.interviewace.dto.request.LoginEmailRequest;
import com.interviewace.dto.request.LoginMobileRequest;
import com.interviewace.dto.request.RegisterRequest;
import com.interviewace.dto.response.AuthResponse;
import com.interviewace.entity.User;
import com.interviewace.enums.Role;
import com.interviewace.repository.UserRepository;
import com.interviewace.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email already exists";
        }

        if (userRepository.existsByMobileNumber(request.getMobileNumber())) {
            return "Mobile number already exists";
        }

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            return "Password and Confirm Password do not match";
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .mobileNumber(request.getMobileNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_STUDENT)
                .build();

        userRepository.save(user);

        return "User Registered Successfully";
    }

    public AuthResponse loginByEmail(LoginEmailRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponse(
                token,
                user.getFullName(),
                user.getEmail(),
                user.getRole().name()
        );
    }

    public AuthResponse loginByMobile(LoginMobileRequest request) {

        User user = userRepository.findByMobileNumber(request.getMobileNumber())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponse(
                token,
                user.getFullName(),
                user.getEmail(),
                user.getRole().name()
        );
    }
}