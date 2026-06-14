package com.interviewace.controller;

import com.interviewace.dto.request.ProjectRequest;
import com.interviewace.dto.response.ProjectResponse;
import com.interviewace.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ProjectResponse addProject(
            Authentication authentication,
            @RequestBody ProjectRequest request) {

        String email = authentication.getName();

        return projectService.addProject(email, request);
    }

    @GetMapping
    public List<ProjectResponse> getProjects(
            Authentication authentication) {

        String email = authentication.getName();

        return projectService.getProjects(email);
    }

    @DeleteMapping("/{id}")
    public String deleteProject(
            @PathVariable Long id) {

        projectService.deleteProject(id);

        return "Project deleted successfully";
    }
}