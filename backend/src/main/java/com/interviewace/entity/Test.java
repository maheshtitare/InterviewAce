package com.interviewace.entity;

import com.interviewace.enums.DifficultyLevel;
import com.interviewace.enums.QuestionCategory;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    private QuestionCategory category;

    @Enumerated(EnumType.STRING)
    private DifficultyLevel difficulty;

    private Integer totalQuestions;

    private Integer timeLimitMinutes;
}