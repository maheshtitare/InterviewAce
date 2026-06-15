package com.interviewace.controller;

import com.interviewace.dto.request.TestRequest;
import com.interviewace.dto.request.TestSubmitRequest;
import com.interviewace.dto.response.AdminDashboardResponse;
import com.interviewace.dto.response.DashboardResponse;
import com.interviewace.dto.response.LeaderboardResponse;
import com.interviewace.dto.response.TestAttemptResponse;
import com.interviewace.dto.response.TestResponse;
import com.interviewace.dto.response.TestResultResponse;
import com.interviewace.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.interviewace.dto.response.CategoryAnalysisResponse;
import com.interviewace.dto.response.DifficultyAnalysisResponse;
import com.interviewace.dto.response.QuestionResponse;


import java.util.List;

@RestController
@RequestMapping("/api/tests")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class TestController {

    private final TestService testService;

    // =========================
    // TEST CRUD
    // =========================

    @PostMapping
    public TestResponse createTest(@RequestBody TestRequest request) {
        return testService.createTest(request);
    }

    @GetMapping
    public List<TestResponse> getAllTests() {
        return testService.getAllTests();
    }

    @GetMapping("/{id}")
    public TestResponse getTestById(@PathVariable Long id) {
        return testService.getTestById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTest(@PathVariable Long id) {
        testService.deleteTest(id);
    }

    // =========================
    // ATTEMPT HISTORY
    // =========================

    @GetMapping("/attempts")
    public List<TestAttemptResponse> getAllAttempts() {
        return testService.getAllAttempts();
    }

    // =========================
    // STUDENT DASHBOARD
    // =========================

    @GetMapping("/dashboard")
    public DashboardResponse getDashboard() {
        return testService.getDashboard();
    }

    // =========================
    // LEADERBOARD
    // =========================

    @GetMapping("/leaderboard")
    public List<LeaderboardResponse> getLeaderboard() {
        return testService.getLeaderboard();
    }

    // =========================
    // ADMIN DASHBOARD
    // =========================

    @GetMapping("/admin/dashboard")
    public AdminDashboardResponse getAdminDashboard() {
        return testService.getAdminDashboard();
    }

    @GetMapping("/admin/category-analysis")
public List<CategoryAnalysisResponse> getCategoryAnalysis() {

    return testService.getCategoryAnalysis();
}

@GetMapping("/admin/difficulty-analysis")
public List<DifficultyAnalysisResponse> getDifficultyAnalysis() {

    return testService.getDifficultyAnalysis();
}

    // =========================
    // MCQ ENGINE
    // =========================

    @PostMapping("/start/{testId}")
    public Long startTest(@PathVariable Long testId) {
        return testService.startTest(testId);
    }
@GetMapping("/{attemptId}/questions")
public List<QuestionResponse> getQuestions(
@PathVariable Long attemptId) {


return testService.getQuestionsForAttempt(
        attemptId
);


}

    @PostMapping("/submit")
    public TestResultResponse submitTest(
            @RequestBody TestSubmitRequest request) {

        return testService.submitTest(request);
    }
}