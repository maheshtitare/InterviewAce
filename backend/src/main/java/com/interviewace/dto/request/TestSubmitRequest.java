package com.interviewace.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestSubmitRequest {

    private Long attemptId;

    private List<AnswerRequest> answers;
}