package com.interviewace.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ExperienceResponse {

    private Long id;

    private String companyName;

    private String designation;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private Boolean currentlyWorking;
}