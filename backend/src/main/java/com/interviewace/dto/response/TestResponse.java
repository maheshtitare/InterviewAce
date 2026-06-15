package com.interviewace.dto.response;

import com.interviewace.enums.DifficultyLevel;
import com.interviewace.enums.QuestionCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TestResponse {

    private Long id;

    private String title;

    private String description;

    private QuestionCategory category;

    private DifficultyLevel difficulty;

    private Integer totalQuestions;

    private Integer timeLimitMinutes;
}