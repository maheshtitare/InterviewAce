package com.interviewace.controller;

import com.interviewace.dto.request.QuestionRequest;
import com.interviewace.dto.response.QuestionResponse;
import com.interviewace.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping
    public QuestionResponse addQuestion(
            @RequestBody QuestionRequest request) {

        return questionService.addQuestion(request);
    }

    @GetMapping
    public List<QuestionResponse> getAllQuestions() {

        return questionService.getAllQuestions();
    }

    @GetMapping("/{id}")
    public QuestionResponse getQuestionById(
            @PathVariable Long id) {

        return questionService.getQuestionById(id);
    }

    @PutMapping("/{id}")
    public QuestionResponse updateQuestion(
            @PathVariable Long id,
            @RequestBody QuestionRequest request) {

        return questionService.updateQuestion(
                id,
                request);
    }

    @DeleteMapping("/{id}")
    public String deleteQuestion(
            @PathVariable Long id) {

        questionService.deleteQuestion(id);

        return "Question deleted successfully";
    }
}