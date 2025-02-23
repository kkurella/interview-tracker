package com.iview.practise.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionAndAnswer {
    private Long id;
    private String question;
    private String language;
    private String answer;
    private String familiarityLevel;
    private List<Snippet> codeSamples;

}
