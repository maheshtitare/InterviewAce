package com.interviewace.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TestAttemptResponse {

    private Long attemptId;

    private Integer score;

    private Integer correctAnswers;

    private Integer wrongAnswers;
}