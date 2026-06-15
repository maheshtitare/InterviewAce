package com.interviewace.repository;

import com.interviewace.entity.TestAnswer;
import com.interviewace.entity.TestAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestAnswerRepository extends JpaRepository<TestAnswer, Long> {

    List<TestAnswer> findByAttempt(TestAttempt attempt);

}