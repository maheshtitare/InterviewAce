package com.interviewace.service;

import com.interviewace.dto.request.ProfileRequest;
import com.interviewace.dto.response.ProfileResponse;
import com.interviewace.entity.Profile;
import com.interviewace.entity.User;
import com.interviewace.repository.ProfileRepository;
import com.interviewace.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public ProfileResponse getProfile(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Profile profile = profileRepository.findByUser(user)
                .orElseGet(() -> {
                    Profile newProfile = new Profile();
                    newProfile.setUser(user);
                    return profileRepository.save(newProfile);
                });

        return mapToResponse(user, profile);
    }

    public ProfileResponse updateProfile(String email, ProfileRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Profile profile = profileRepository.findByUser(user)
                .orElseGet(() -> {
                    Profile newProfile = new Profile();
                    newProfile.setUser(user);
                    return newProfile;
                });

        profile.setHeadline(request.getHeadline());
        profile.setAbout(request.getAbout());
        profile.setCity(request.getCity());
        profile.setState(request.getState());
        profile.setCountry(request.getCountry());
        profile.setLinkedinUrl(request.getLinkedinUrl());
        profile.setGithubUrl(request.getGithubUrl());
        profile.setPortfolioUrl(request.getPortfolioUrl());

        profile.setProfileCompletion(calculateCompletion(profile));

        profileRepository.save(profile);

        return mapToResponse(user, profile);
    }

    public Integer getProfileCompletion(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return profileRepository.findByUser(user)
                .map(Profile::getProfileCompletion)
                .orElse(0);
    }

    private Integer calculateCompletion(Profile profile) {

        int completed = 0;
        int total = 8;

        if (isFilled(profile.getHeadline())) completed++;
        if (isFilled(profile.getAbout())) completed++;
        if (isFilled(profile.getCity())) completed++;
        if (isFilled(profile.getState())) completed++;
        if (isFilled(profile.getCountry())) completed++;
        if (isFilled(profile.getLinkedinUrl())) completed++;
        if (isFilled(profile.getGithubUrl())) completed++;
        if (isFilled(profile.getPortfolioUrl())) completed++;

        return (completed * 100) / total;
    }

    private boolean isFilled(String value) {
        return value != null && !value.trim().isEmpty();
    }

    private ProfileResponse mapToResponse(User user, Profile profile) {

        ProfileResponse response = new ProfileResponse();

        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());

        response.setHeadline(profile.getHeadline());
        response.setAbout(profile.getAbout());
        response.setCity(profile.getCity());
        response.setState(profile.getState());
        response.setCountry(profile.getCountry());

        response.setLinkedinUrl(profile.getLinkedinUrl());
        response.setGithubUrl(profile.getGithubUrl());
        response.setPortfolioUrl(profile.getPortfolioUrl());

        response.setProfileCompletion(profile.getProfileCompletion());

        return response;
    }
}