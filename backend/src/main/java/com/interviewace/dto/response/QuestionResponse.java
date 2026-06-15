package com.interviewace.dto.response;

import com.interviewace.enums.DifficultyLevel;
import com.interviewace.enums.QuestionCategory;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionResponse {

    private Long id;

    private String questionText;

    private String optionA;

    private String optionB;

    private String optionC;

    private String optionD;

    private String correctAnswer;

    private QuestionCategory category;

    private DifficultyLevel difficulty;
}