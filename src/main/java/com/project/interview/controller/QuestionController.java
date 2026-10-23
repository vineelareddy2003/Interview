package com.project.interview.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.project.interview.entity.Question;
import com.project.interview.service.QuestionService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private QuestionService service;
    @GetMapping
    public List<Question> getAllQuestions() {
        return service.getAllQuestions();
    }
    @PostMapping
    public Question addQuestion(@RequestBody Question question) {
        return service.addQuestion(question);
    }
    @PostMapping("/bulk")
    public List<Question> addQuestions(@RequestBody List<Question> questions) {
        return service.saveAllQuestions(questions);
    }
}
