package com.interviewace.controller;

import com.interviewace.dto.request.EducationRequest;
import com.interviewace.dto.response.EducationResponse;
import com.interviewace.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/education")
@RequiredArgsConstructor
public class EducationController {

    private final EducationService educationService;

    @PostMapping
    public EducationResponse addEducation(
            Authentication authentication,
            @RequestBody EducationRequest request) {

        String email = authentication.getName();

        return educationService.addEducation(email, request);
    }

    @GetMapping
    public List<EducationResponse> getEducations(
            Authentication authentication) {

        String email = authentication.getName();

        return educationService.getEducations(email);
    }

    @DeleteMapping("/{id}")
    public String deleteEducation(
            @PathVariable Long id) {

        educationService.deleteEducation(id);

        return "Education deleted successfully";
    }
}