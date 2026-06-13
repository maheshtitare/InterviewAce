package com.interviewace.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginEmailRequest {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}