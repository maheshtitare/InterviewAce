package com.interviewace.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileResponse {

    private String fullName;

    private String email;

    private String headline;

    private String about;

    private String city;

    private String state;

    private String country;

    private String linkedinUrl;

    private String githubUrl;

    private String portfolioUrl;

    private Integer profileCompletion;
}