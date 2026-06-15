package com.interviewace.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResumeResponse {

    private String fullName;
    private String email;
    private String mobileNumber;

    private ProfileResponse profile;

    private List<SkillResponse> skills;

    private List<EducationResponse> educations;

    private List<ProjectResponse> projects;

    private List<ExperienceResponse> experiences;
}