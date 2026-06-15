package com.interviewace.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TestResultResponse {

    private Integer score;

    private Integer correctAnswers;

    private Integer wrongAnswers;

    private Integer totalQuestions;

    private Double accuracy;

    private String result;
}