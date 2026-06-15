package com.interviewace.repository;

import com.interviewace.entity.Question;
import com.interviewace.enums.DifficultyLevel;
import com.interviewace.enums.QuestionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import com.interviewace.dto.response.CategoryAnalysisResponse;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import com.interviewace.dto.response.DifficultyAnalysisResponse;

public interface QuestionRepository
        extends JpaRepository<Question, Long> {

    List<Question> findByCategory(
            QuestionCategory category);

    List<Question> findByDifficulty(
            DifficultyLevel difficulty);

    List<Question> findByCategoryAndDifficulty(
            QuestionCategory category,
            DifficultyLevel difficulty);

    @Query("""
           SELECT new com.interviewace.dto.response.CategoryAnalysisResponse(
               CAST(q.category AS string),
               COUNT(q)
           )
           FROM Question q
           GROUP BY q.category
           """)
    List<CategoryAnalysisResponse> getCategoryAnalysis();

    @Query("""
       SELECT new com.interviewace.dto.response.DifficultyAnalysisResponse(
           CAST(q.difficulty AS string),
           COUNT(q)
       )
       FROM Question q
       GROUP BY q.difficulty
       """)
List<DifficultyAnalysisResponse> getDifficultyAnalysis();

}