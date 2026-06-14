package com.interviewace.repository;

import com.interviewace.entity.Education;
import com.interviewace.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationRepository
        extends JpaRepository<Education, Long> {

    List<Education> findByUser(User user);
}