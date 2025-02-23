package com.iview.practise.controller;

import com.iview.practise.service.QuestionService;
import com.iview.practise.dto.Snippet;
import com.iview.practise.dto.FamiliarityLevel;
import com.iview.practise.dto.QuestionAndAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/v1/api/questions")
@CrossOrigin(origins = "*")
public class QuestionsController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/{language}")
    public List<QuestionAndAnswer> fetchAllQuestions(@PathVariable String language){
        return questionService.fetchQuestions(language);
    }

    @DeleteMapping("/{id}")
    public String deleteQuestionsById(@PathVariable Long id){
         questionService.deleteQuestion(id);
         return "Deleted";
    }

    @PostMapping("/")
    public QuestionAndAnswer addQuestion(@RequestBody QuestionAndAnswer qAndA){
        return questionService.saveQuestion(qAndA);
    }

    @PutMapping("/")
    public QuestionAndAnswer updateQuestion(@RequestBody QuestionAndAnswer qAndA){
        return questionService.saveQuestion(qAndA);
    }
}
