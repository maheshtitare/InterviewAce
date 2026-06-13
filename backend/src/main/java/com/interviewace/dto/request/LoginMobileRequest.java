package com.interviewace.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginMobileRequest {

    @Pattern(regexp = "^[0-9]{10}$")
    private String mobileNumber;

    @NotBlank
    private String password;
}