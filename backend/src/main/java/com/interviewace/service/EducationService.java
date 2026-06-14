package com.interviewace.service;

import com.interviewace.dto.request.EducationRequest;
import com.interviewace.dto.response.EducationResponse;
import com.interviewace.entity.Education;
import com.interviewace.entity.User;
import com.interviewace.repository.EducationRepository;
import com.interviewace.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationService {

    private final EducationRepository educationRepository;
    private final UserRepository userRepository;

    public EducationResponse addEducation(
            String email,
            EducationRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Education education = new Education();

        education.setUser(user);
        education.setCollegeName(request.getCollegeName());
        education.setDegree(request.getDegree());
        education.setFieldOfStudy(request.getFieldOfStudy());
        education.setCgpa(request.getCgpa());
        education.setStartYear(request.getStartYear());
        education.setEndYear(request.getEndYear());

        education = educationRepository.save(education);

        return mapToResponse(education);
    }

    public List<EducationResponse> getEducations(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return educationRepository.findByUser(user)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public void deleteEducation(Long id) {
        educationRepository.deleteById(id);
    }

    private EducationResponse mapToResponse(
            Education education) {

        EducationResponse response =
                new EducationResponse();

        response.setId(education.getId());
        response.setCollegeName(
                education.getCollegeName());
        response.setDegree(
                education.getDegree());
        response.setFieldOfStudy(
                education.getFieldOfStudy());
        response.setCgpa(
                education.getCgpa());
        response.setStartYear(
                education.getStartYear());
        response.setEndYear(
                education.getEndYear());

        return response;
    }
}