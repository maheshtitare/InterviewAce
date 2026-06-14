package com.interviewace.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ExperienceRequest {

    private String companyName;

    private String designation;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private Boolean currentlyWorking;
}