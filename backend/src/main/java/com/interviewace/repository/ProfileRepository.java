package com.interviewace.repository;

import com.interviewace.entity.Profile;
import com.interviewace.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findByUser(User user);

    boolean existsByUser(User user);
}