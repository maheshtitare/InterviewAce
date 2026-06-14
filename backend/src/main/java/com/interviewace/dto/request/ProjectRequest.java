package com.interviewace.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProjectRequest {

    private String title;

    private String description;

    private String technologies;

    private String githubUrl;

    private String liveUrl;

    private LocalDate startDate;

    private LocalDate endDate;
}