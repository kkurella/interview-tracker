package com.iview.practise.service;

import com.iview.practise.dto.QuestionAndAnswer;
import com.iview.practise.dto.Snippet;
import com.iview.practise.entity.CodeSnippet;
import com.iview.practise.entity.Question;
import com.iview.practise.repository.QuestionRepository;
import com.iview.practise.repository.SnippetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private SnippetRepository snippetRepository;

    public QuestionAndAnswer saveQuestion(QuestionAndAnswer qa){
        Question qaEntity = toQuestionEntity(qa);
        Question question = questionRepository.save(qaEntity);
        List<CodeSnippet> codeEntities = toCodeEntities(qa, question);
        List<CodeSnippet> resCodes = snippetRepository.saveAll(codeEntities);
        question.setCodes(resCodes);
        return toDto(question);
    }

    private QuestionAndAnswer toDto(Question question) {
        List<Snippet> snippetEntities =  new ArrayList<>();
        QuestionAndAnswer qaDto = new QuestionAndAnswer();
        for(CodeSnippet code : question.getCodes()) {
            Snippet snippet = new Snippet();
            snippet.setCode(code.getCode());
            snippet.setCode(code.getCode());
            snippet.setLanguage(question.getLanguage());
            snippetEntities.add(snippet);
        }
        qaDto.setAnswer(question.getAnswer());
        qaDto.setQuestion(question.getQuestion());
        qaDto.setLanguage(question.getLanguage());
        qaDto.setCodeSamples(snippetEntities);
        qaDto.setId(question.getQuestionId());
        return qaDto;
    }

    private List<CodeSnippet> toCodeEntities(QuestionAndAnswer qa, Question question) {
        List<CodeSnippet> snippetEntities =  new ArrayList<>();
        for(Snippet code : qa.getCodeSamples()) {
            CodeSnippet codeEntity = new CodeSnippet();
            codeEntity.setCode(code.getCode());
            codeEntity.setQuestion(question);
            codeEntity.setLanguage(qa.getLanguage());
            snippetEntities.add(codeEntity);
        }
        return snippetEntities;
    }

    private Question toQuestionEntity(QuestionAndAnswer qa) {
        Question qaEntity = new Question();

        qaEntity.setQuestionId(qa.getId());
        qaEntity.setQuestion(qa.getQuestion());
        qaEntity.setAnswer(qa.getAnswer());
        qaEntity.setLanguage(qa.getLanguage() == null ? "Java" : qa.getLanguage());
        return qaEntity;
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    @Transactional
    public List<QuestionAndAnswer> fetchQuestions(String language) {
       return convert(questionRepository.findAll().stream()
               .filter(l->l.getLanguage()!=null && l.getLanguage()
                       .equalsIgnoreCase(language)).collect(toList()));
    }

    private List<QuestionAndAnswer> convert(List<Question> qsns) {
        List<QuestionAndAnswer> questionAndAnswers = new ArrayList<>();

        for(Question q : qsns){
            QuestionAndAnswer qaDto = toDto(q);
            questionAndAnswers.add(qaDto);
        }
        return questionAndAnswers;
    }
}
