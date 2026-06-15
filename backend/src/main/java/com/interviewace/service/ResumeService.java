package com.interviewace.service;

import com.interviewace.dto.response.*;
import com.interviewace.entity.Resume;
import com.interviewace.entity.User;
import com.interviewace.repository.ResumeRepository;
import com.interviewace.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final UserRepository userRepository;
    private final ResumeRepository resumeRepository;

    private final ProfileService profileService;
    private final SkillService skillService;
    private final EducationService educationService;
    private final ProjectService projectService;
    private final ExperienceService experienceService;

    private static final String UPLOAD_DIR = "uploads/resumes";

    public ResumeResponse getResume(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        ResumeResponse response = new ResumeResponse();

        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setMobileNumber(user.getMobileNumber());

        ProfileResponse profile =
                profileService.getProfile(email);

        List<SkillResponse> skills =
                skillService.getSkills(email);

        List<EducationResponse> educations =
                educationService.getEducations(email);

        List<ProjectResponse> projects =
                projectService.getProjects(email);

        List<ExperienceResponse> experiences =
                experienceService.getExperiences(email);

        response.setProfile(profile);
        response.setSkills(skills);
        response.setEducations(educations);
        response.setProjects(projects);
        response.setExperiences(experiences);

        return response;
    }

    public ResumeUploadResponse uploadResume(
            String email,
            MultipartFile file) {

        try {

            if (file.isEmpty()) {
                throw new RuntimeException("File is empty");
            }

            if (!file.getOriginalFilename()
                    .toLowerCase()
                    .endsWith(".pdf")) {

                throw new RuntimeException(
                        "Only PDF files are allowed");
            }

            User user = userRepository.findByEmail(email)
                    .orElseThrow(() ->
                            new RuntimeException("User not found"));

            Files.createDirectories(
                    Paths.get(UPLOAD_DIR));

            String fileName =
                    System.currentTimeMillis()
                            + "_"
                            + file.getOriginalFilename();

            Path filePath =
                    Paths.get(UPLOAD_DIR, fileName);

            Files.copy(
                    file.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING);

            Resume resume =
                    resumeRepository.findByUser(user)
                            .orElse(new Resume());

            resume.setUser(user);
            resume.setFileName(fileName);
            resume.setFilePath(filePath.toString());
            resume.setUploadedAt(LocalDateTime.now());

            resumeRepository.save(resume);

            return ResumeUploadResponse.builder()
                    .message("Resume uploaded successfully")
                    .fileName(fileName)
                    .build();

        } catch (IOException e) {

            throw new RuntimeException(
                    "Failed to upload resume");
        }
    }
}