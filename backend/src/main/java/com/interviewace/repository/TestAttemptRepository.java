package com.interviewace.repository;

import com.interviewace.entity.TestAttempt;
import com.interviewace.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestAttemptRepository extends JpaRepository<TestAttempt, Long> {

    List<TestAttempt> findByUser(User user);

}