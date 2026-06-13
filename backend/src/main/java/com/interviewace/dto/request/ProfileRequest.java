package com.interviewace.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileRequest {

    private String headline;

    private String about;

    private String city;

    private String state;

    private String country;

    private String linkedinUrl;

    private String githubUrl;

    private String portfolioUrl;
}