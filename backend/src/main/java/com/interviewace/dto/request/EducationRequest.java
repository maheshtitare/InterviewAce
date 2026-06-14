package com.interviewace.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EducationRequest {

    private String collegeName;

    private String degree;

    private String fieldOfStudy;

    private Double cgpa;

    private Integer startYear;

    private Integer endYear;
}