package com.interviewace.controller;

import com.interviewace.dto.request.ProfileRequest;
import com.interviewace.dto.response.ProfileResponse;
import com.interviewace.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public ProfileResponse getProfile(Authentication authentication) {

        String email = authentication.getName();

        return profileService.getProfile(email);
    }

    @PutMapping
    public ProfileResponse updateProfile(
            Authentication authentication,
            @RequestBody ProfileRequest request) {

        String email = authentication.getName();

        return profileService.updateProfile(email, request);
    }

    @GetMapping("/completion")
    public Map<String, Integer> getCompletion(Authentication authentication) {

        String email = authentication.getName();

        Integer completion =
                profileService.getProfileCompletion(email);

        return Map.of(
                "profileCompletion",
                completion
        );
    }
}