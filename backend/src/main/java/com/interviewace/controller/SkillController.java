package com.interviewace.controller;

import com.interviewace.dto.request.SkillRequest;
import com.interviewace.dto.response.SkillResponse;
import com.interviewace.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
public class SkillController {

    private final SkillService skillService;

    @PostMapping
    public SkillResponse addSkill(
            Authentication authentication,
            @RequestBody SkillRequest request) {

        String email = authentication.getName();

        return skillService.addSkill(email, request);
    }

    @GetMapping
    public List<SkillResponse> getSkills(
            Authentication authentication) {

        String email = authentication.getName();

        return skillService.getSkills(email);
    }

    @DeleteMapping("/{id}")
    public String deleteSkill(@PathVariable Long id) {

        skillService.deleteSkill(id);

        return "Skill deleted successfully";
    }
}