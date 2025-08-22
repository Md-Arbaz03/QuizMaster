package com.arbaz.quiz_service.model;

import lombok.Data;

@Data
public class QuizDto {
    private String title;
    private String category;
    private Integer numQ;
}
