package com.interviewace.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LeaderboardResponse {

    private Long attemptId;

    private Integer score;
}