package com.interviewace.controller;

import com.interviewace.dto.request.ExperienceRequest;
import com.interviewace.dto.response.ExperienceResponse;
import com.interviewace.service.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experience")
@RequiredArgsConstructor
public class ExperienceController {

    private final ExperienceService experienceService;

    @PostMapping
    public ExperienceResponse addExperience(
            Authentication authentication,
            @RequestBody ExperienceRequest request) {

        String email = authentication.getName();

        return experienceService.addExperience(
                email,
                request);
    }

    @GetMapping
    public List<ExperienceResponse> getExperiences(
            Authentication authentication) {

        String email = authentication.getName();

        return experienceService.getExperiences(email);
    }

    @DeleteMapping("/{id}")
    public String deleteExperience(
            @PathVariable Long id) {

        experienceService.deleteExperience(id);

        return "Experience deleted successfully";
    }
}