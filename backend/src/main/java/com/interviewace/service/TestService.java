package com.interviewace.service;

import com.interviewace.dto.request.AnswerRequest;
import com.interviewace.dto.request.TestRequest;
import com.interviewace.dto.request.TestSubmitRequest;
import com.interviewace.dto.response.DashboardResponse;
import com.interviewace.dto.response.TestAttemptResponse;
import com.interviewace.dto.response.TestResponse;
import com.interviewace.dto.response.TestResultResponse;
import com.interviewace.entity.Question;
import com.interviewace.entity.Test;
import com.interviewace.entity.TestAnswer;
import com.interviewace.entity.TestAttempt;
import com.interviewace.repository.QuestionRepository;
import com.interviewace.repository.TestAnswerRepository;
import com.interviewace.repository.TestAttemptRepository;
import com.interviewace.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.interviewace.dto.response.LeaderboardResponse;
import java.util.Comparator;
import com.interviewace.entity.User;
import com.interviewace.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.interviewace.dto.response.AdminDashboardResponse;
import com.interviewace.dto.response.CategoryAnalysisResponse;
import com.interviewace.dto.response.DifficultyAnalysisResponse;
import com.interviewace.dto.response.QuestionResponse;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;
    private final TestAttemptRepository testAttemptRepository;
    private final TestAnswerRepository testAnswerRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    // =========================
    // TEST CRUD
    // =========================

    public TestResponse createTest(TestRequest request) {

        Test test = Test.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .category(request.getCategory())
                .difficulty(request.getDifficulty())
                .totalQuestions(request.getTotalQuestions())
                .timeLimitMinutes(request.getTimeLimitMinutes())
                .build();

        Test savedTest = testRepository.save(test);

        return mapToResponse(savedTest);
    }

    public List<TestResponse> getAllTests() {

        return testRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public TestResponse getTestById(Long id) {

        Test test = testRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Test not found"));

        return mapToResponse(test);
    }

    public void deleteTest(Long id) {

        testRepository.deleteById(id);
    }

    private TestResponse mapToResponse(Test test) {

        return TestResponse.builder()
                .id(test.getId())
                .title(test.getTitle())
                .description(test.getDescription())
                .category(test.getCategory())
                .difficulty(test.getDifficulty())
                .totalQuestions(test.getTotalQuestions())
                .timeLimitMinutes(test.getTimeLimitMinutes())
                .build();
    }

    // =========================
    // ATTEMPT HISTORY
    // =========================

   public List<TestAttemptResponse> getAllAttempts() {

    Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

    String email = authentication.getName();

    User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                    new RuntimeException("User not found"));

    return testAttemptRepository.findByUser(user)
            .stream()
            .map(attempt -> TestAttemptResponse.builder()
                    .attemptId(attempt.getId())
                    .score(attempt.getScore())
                    .correctAnswers(attempt.getCorrectAnswers())
                    .wrongAnswers(attempt.getWrongAnswers())
                    .build())
            .collect(Collectors.toList());
}

    // =========================
    // DASHBOARD
    // =========================

    public DashboardResponse getDashboard() {

    Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

    String email = authentication.getName();

    User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                    new RuntimeException("User not found"));

    List<TestAttempt> attempts =
            testAttemptRepository.findByUser(user);

    long totalAttempts = attempts.size();

    int bestScore = attempts.stream()
            .map(TestAttempt::getScore)
            .filter(score -> score != null)
            .max(Integer::compareTo)
            .orElse(0);

    double averageScore = attempts.stream()
            .map(TestAttempt::getScore)
            .filter(score -> score != null)
            .mapToInt(Integer::intValue)
            .average()
            .orElse(0.0);

    int latestScore = attempts.isEmpty()
            ? 0
            : (attempts.get(attempts.size() - 1).getScore() == null
            ? 0
            : attempts.get(attempts.size() - 1).getScore());

    return DashboardResponse.builder()
            .totalAttempts(totalAttempts)
            .bestScore(bestScore)
            .averageScore(averageScore)
            .latestScore(latestScore)
            .build();
}

    public List<LeaderboardResponse> getLeaderboard() {

    return testAttemptRepository.findAll()
            .stream()
            .sorted(
                    Comparator.comparing(
                            TestAttempt::getScore,
                            Comparator.nullsLast(
                                    Comparator.reverseOrder()
                            )
                    )
            )
            .map(attempt -> LeaderboardResponse.builder()
                    .attemptId(attempt.getId())
                    .score(
                            attempt.getScore() == null
                                    ? 0
                                    : attempt.getScore()
                    )
                    .build())
            .collect(Collectors.toList());
}

public AdminDashboardResponse getAdminDashboard() {

    long totalUsers = userRepository.count();

    long totalTests = testRepository.count();

    long totalAttempts = testAttemptRepository.count();

    double averageScore = testAttemptRepository.findAll()
            .stream()
            .map(TestAttempt::getScore)
            .filter(score -> score != null)
            .mapToInt(Integer::intValue)
            .average()
            .orElse(0.0);

    return AdminDashboardResponse.builder()
            .totalUsers(totalUsers)
            .totalTests(totalTests)
            .totalAttempts(totalAttempts)
            .averageScore(averageScore)
            .build();
}

public List<CategoryAnalysisResponse> getCategoryAnalysis() {

    return questionRepository.getCategoryAnalysis();
}

public List<DifficultyAnalysisResponse> getDifficultyAnalysis() {

    return questionRepository.getDifficultyAnalysis();
}

    // =========================
    // MCQ ENGINE
    // =========================

    public Long startTest(Long testId) {

        

    Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

    String email = authentication.getName();

    User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                    new RuntimeException("User not found"));

    Test test = testRepository.findById(testId)
            .orElseThrow(() ->
                    new RuntimeException("Test not found"));

    TestAttempt attempt = TestAttempt.builder()
            .user(user)
            .test(test)
            .startedAt(LocalDateTime.now())
            .score(0)
            .correctAnswers(0)
            .wrongAnswers(0)
            .build();

    TestAttempt savedAttempt =
            testAttemptRepository.save(attempt);

    return savedAttempt.getId();
}


    public TestResultResponse submitTest(TestSubmitRequest request) {

        TestAttempt attempt = testAttemptRepository.findById(
                        request.getAttemptId())
                .orElseThrow(() ->
                        new RuntimeException("Attempt not found"));

        int correctAnswers = 0;
        int wrongAnswers = 0;

        for (AnswerRequest answerRequest : request.getAnswers()) {

            Question question = questionRepository.findById(
                            answerRequest.getQuestionId())
                    .orElseThrow(() ->
                            new RuntimeException("Question not found"));

            boolean isCorrect = question.getCorrectAnswer()
                    .equalsIgnoreCase(answerRequest.getSelectedAnswer());

            if (isCorrect) {
                correctAnswers++;
            } else {
                wrongAnswers++;
            }

            TestAnswer testAnswer = TestAnswer.builder()
                    .attempt(attempt)
                    .question(question)
                    .selectedAnswer(answerRequest.getSelectedAnswer())
                    .isCorrect(isCorrect)
                    .build();

            testAnswerRepository.save(testAnswer);
        }

        int score = correctAnswers;

        double accuracy = 0.0;

        if ((correctAnswers + wrongAnswers) > 0) {
            accuracy = ((double) correctAnswers
                    / (correctAnswers + wrongAnswers)) * 100;
        }

        int totalQuestions = correctAnswers + wrongAnswers;

        String result = accuracy >= 40
                ? "PASS"
                : "FAIL";

        attempt.setScore(score);
        attempt.setCorrectAnswers(correctAnswers);
        attempt.setWrongAnswers(wrongAnswers);
        attempt.setSubmittedAt(LocalDateTime.now());

        testAttemptRepository.save(attempt);

        return TestResultResponse.builder()
                .score(score)
                .correctAnswers(correctAnswers)
                .wrongAnswers(wrongAnswers)
                .totalQuestions(totalQuestions)
                .accuracy(accuracy)
                .result(result)
                .build();
    }

    public List<QuestionResponse> getQuestionsForAttempt(
Long attemptId) {


TestAttempt attempt =
        testAttemptRepository.findById(attemptId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Attempt not found"));

Test test = attempt.getTest();

List<Question> questions =
        questionRepository.findByCategoryAndDifficulty(
                test.getCategory(),
                test.getDifficulty()
        );

return questions.stream()
        .limit(test.getTotalQuestions())
        .map(question -> QuestionResponse.builder()
                .id(question.getId())
                .questionText(question.getQuestionText())
                .optionA(question.getOptionA())
                .optionB(question.getOptionB())
                .optionC(question.getOptionC())
                .optionD(question.getOptionD())

                // Security: frontend ko answer mat bhejo
                .correctAnswer(null)

                .category(question.getCategory())
                .difficulty(question.getDifficulty())
                .build())
        .collect(Collectors.toList());


}

}