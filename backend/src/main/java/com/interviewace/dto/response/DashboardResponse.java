package com.interviewace.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DashboardResponse {

    private Long totalAttempts;

    private Integer bestScore;

    private Double averageScore;

    private Integer latestScore;
}