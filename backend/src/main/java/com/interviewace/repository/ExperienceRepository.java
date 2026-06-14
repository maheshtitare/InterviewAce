package com.interviewace.repository;

import com.interviewace.entity.Experience;
import com.interviewace.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExperienceRepository
        extends JpaRepository<Experience, Long> {

    List<Experience> findByUser(User user);
}