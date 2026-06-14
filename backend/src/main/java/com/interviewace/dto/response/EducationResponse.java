package com.interviewace.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EducationResponse {

    private Long id;

    private String collegeName;

    private String degree;

    private String fieldOfStudy;

    private Double cgpa;

    private Integer startYear;

    private Integer endYear;
}