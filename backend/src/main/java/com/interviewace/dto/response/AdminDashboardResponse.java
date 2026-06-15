package com.interviewace.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AdminDashboardResponse {

    private Long totalUsers;

    private Long totalTests;

    private Long totalAttempts;

    private Double averageScore;
}