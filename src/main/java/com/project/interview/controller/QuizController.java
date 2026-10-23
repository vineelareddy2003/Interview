package com.project.interview.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.project.interview.dto.AnswerDTO;
import com.project.interview.dto.DTO;
import com.project.interview.entity.Question;
import com.project.interview.service.QuestionService;
@CrossOrigin(origins = "http://localhost:3000") 

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuestionService service;

    // ✅ For frontend (no correctAnswer)
    @GetMapping("/{category}")
    public List<DTO> getQuizQuestions(@PathVariable String category) {
        return service.getQuestionsByCategory(category);
    }

    @PostMapping("/submit")
    public int submitQuiz(@RequestBody List<AnswerDTO> answers) {
        return service.calculateScoreNew(answers);
    }
}