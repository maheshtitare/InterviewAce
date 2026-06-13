package com.interviewace.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillResponse {

    private Long id;

    private String skillName;

    private String level;

    private Integer yearsOfExperience;
}