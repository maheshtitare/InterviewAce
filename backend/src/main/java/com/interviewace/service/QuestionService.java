package com.interviewace.service;

import com.interviewace.dto.request.QuestionRequest;
import com.interviewace.dto.response.QuestionResponse;
import com.interviewace.entity.Question;
import com.interviewace.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionResponse addQuestion(
            QuestionRequest request) {

        Question question = Question.builder()
                .questionText(request.getQuestionText())
                .optionA(request.getOptionA())
                .optionB(request.getOptionB())
                .optionC(request.getOptionC())
                .optionD(request.getOptionD())
                .correctAnswer(request.getCorrectAnswer())
                .category(request.getCategory())
                .difficulty(request.getDifficulty())
                .createdAt(LocalDateTime.now())
                .build();

        question = questionRepository.save(question);

        return mapToResponse(question);
    }

    public List<QuestionResponse> getAllQuestions() {

        return questionRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public QuestionResponse getQuestionById(
            Long id) {

        Question question =
                questionRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Question not found"));

        return mapToResponse(question);
    }

    public QuestionResponse updateQuestion(
            Long id,
            QuestionRequest request) {

        Question question =
                questionRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Question not found"));

        question.setQuestionText(request.getQuestionText());
        question.setOptionA(request.getOptionA());
        question.setOptionB(request.getOptionB());
        question.setOptionC(request.getOptionC());
        question.setOptionD(request.getOptionD());
        question.setCorrectAnswer(request.getCorrectAnswer());
        question.setCategory(request.getCategory());
        question.setDifficulty(request.getDifficulty());

        question = questionRepository.save(question);

        return mapToResponse(question);
    }

    public void deleteQuestion(
            Long id) {

        Question question =
                questionRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Question not found"));

        questionRepository.delete(question);
    }

    private QuestionResponse mapToResponse(
            Question question) {

        return QuestionResponse.builder()
                .id(question.getId())
                .questionText(question.getQuestionText())
                .optionA(question.getOptionA())
                .optionB(question.getOptionB())
                .optionC(question.getOptionC())
                .optionD(question.getOptionD())
                .correctAnswer(question.getCorrectAnswer())
                .category(question.getCategory())
                .difficulty(question.getDifficulty())
                .build();
    }
}