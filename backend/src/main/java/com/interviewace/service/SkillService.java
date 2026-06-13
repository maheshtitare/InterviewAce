package com.interviewace.service;

import com.interviewace.dto.request.SkillRequest;
import com.interviewace.dto.response.SkillResponse;
import com.interviewace.entity.Skill;
import com.interviewace.entity.User;
import com.interviewace.entity.UserSkill;
import com.interviewace.repository.SkillRepository;
import com.interviewace.repository.UserRepository;
import com.interviewace.repository.UserSkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;
    private final UserSkillRepository userSkillRepository;
    private final UserRepository userRepository;

    public SkillResponse addSkill(String email, SkillRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Skill skill = skillRepository.findByName(request.getSkillName())
                .orElseGet(() -> {
                    Skill newSkill = new Skill();
                    newSkill.setName(request.getSkillName());
                    return skillRepository.save(newSkill);
                });

        UserSkill userSkill = new UserSkill();
        userSkill.setUser(user);
        userSkill.setSkill(skill);
        userSkill.setLevel(request.getLevel());
        userSkill.setYearsOfExperience(request.getYearsOfExperience());

        userSkill = userSkillRepository.save(userSkill);

        return mapToResponse(userSkill);
    }

    public List<SkillResponse> getSkills(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return userSkillRepository.findByUser(user)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public void deleteSkill(Long id) {
        userSkillRepository.deleteById(id);
    }

    private SkillResponse mapToResponse(UserSkill userSkill) {

        SkillResponse response = new SkillResponse();

        response.setId(userSkill.getId());
        response.setSkillName(userSkill.getSkill().getName());
        response.setLevel(userSkill.getLevel());
        response.setYearsOfExperience(
                userSkill.getYearsOfExperience()
        );

        return response;
    }
}