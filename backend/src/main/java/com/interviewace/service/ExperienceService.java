package com.interviewace.service;

import com.interviewace.dto.request.ExperienceRequest;
import com.interviewace.dto.response.ExperienceResponse;
import com.interviewace.entity.Experience;
import com.interviewace.entity.User;
import com.interviewace.repository.ExperienceRepository;
import com.interviewace.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final UserRepository userRepository;

    public ExperienceResponse addExperience(
            String email,
            ExperienceRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Experience experience = new Experience();

        experience.setUser(user);
        experience.setCompanyName(request.getCompanyName());
        experience.setDesignation(request.getDesignation());
        experience.setDescription(request.getDescription());
        experience.setStartDate(request.getStartDate());
        experience.setEndDate(request.getEndDate());
        experience.setCurrentlyWorking(
                request.getCurrentlyWorking());

        experience = experienceRepository.save(experience);

        return mapToResponse(experience);
    }

    public List<ExperienceResponse> getExperiences(
            String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return experienceRepository.findByUser(user)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public void deleteExperience(Long id) {
        experienceRepository.deleteById(id);
    }

    private ExperienceResponse mapToResponse(
            Experience experience) {

        ExperienceResponse response =
                new ExperienceResponse();

        response.setId(experience.getId());
        response.setCompanyName(
                experience.getCompanyName());
        response.setDesignation(
                experience.getDesignation());
        response.setDescription(
                experience.getDescription());
        response.setStartDate(
                experience.getStartDate());
        response.setEndDate(
                experience.getEndDate());
        response.setCurrentlyWorking(
                experience.getCurrentlyWorking());

        return response;
    }
}