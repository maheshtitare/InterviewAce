package com.interviewace.dto.request;

import com.interviewace.enums.DifficultyLevel;
import com.interviewace.enums.QuestionCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestRequest {

    private String title;

    private String description;

    private QuestionCategory category;

    private DifficultyLevel difficulty;

    private Integer totalQuestions;

    private Integer timeLimitMinutes;
}