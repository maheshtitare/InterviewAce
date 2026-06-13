package com.interviewace.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillRequest {

    private String skillName;

    private String level;

    private Integer yearsOfExperience;
}