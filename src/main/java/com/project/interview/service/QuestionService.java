package com.project.interview.service;
import com.project.interview.dto.AnswerDTO;
import com.project.interview.dto.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import com.project.interview.entity.Question;
import com.project.interview.repository.QuestionRepository;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository repo;
    private List<Question> lastServedQuestions;

    public List<DTO> getQuestionsByCategory(String category) {

        List<Question> questions = repo.findByCategory(category);

        Collections.shuffle(questions);

        List<Question> randomQuestions = questions.subList(0, Math.min(5, questions.size()));

        lastServedQuestions = randomQuestions;

        return randomQuestions.stream().map(q -> {
            DTO dto = new DTO();
            dto.setId(q.getId());
            dto.setTitle(q.getTitle());
            dto.setCategory(q.getCategory());
            dto.setType(q.getType());
            dto.setOptionA(q.getOptionA());
            dto.setOptionB(q.getOptionB());
            dto.setOptionC(q.getOptionC());
            dto.setOptionD(q.getOptionD());
            dto.setSampleAnswer(q.getSampleAnswer());
            return dto;
        }).toList();
    }

    public int calculateScore(List<String> userAnswers, List<Question> questions) {
        int score = 0;

        for (int i = 0; i < Math.min(userAnswers.size(), questions.size()); i++) {
            Question q = questions.get(i);

            if ("mcq".equalsIgnoreCase(q.getType())) {

                String correct = q.getCorrectAnswer();
                String user = userAnswers.get(i);

                if (correct != null && user != null &&
                    correct.trim().equalsIgnoreCase(user.trim())) {
                    score++;
                }
            }
        }
        return score;
    }
    public List<Question> getAllQuestions() {
        return repo.findAll();
    }

    public Question addQuestion(Question question) {
        return repo.save(question);
    }
  //🔥 ADD THIS METHOD
    public List<Question> saveAllQuestions(List<Question> questions) {
        return repo.saveAll(questions);
    }

    //🔥 OPTIONAL CLEAN METHOD
    public List<DTO> getRandomQuestions(String category) {
        return getQuestionsByCategory(category);
    }
    public int calculateScoreNew(List<AnswerDTO> answers) {
        int score = 0;

        for (AnswerDTO ans : answers) {
            Question q = repo.findById(ans.getId()).orElse(null);

            if (q != null && "mcq".equalsIgnoreCase(q.getType())) {

                String correct = q.getCorrectAnswer();
                String user = ans.getAnswer();

                if (correct != null && user != null &&
                    correct.trim().equalsIgnoreCase(user.trim())) {
                    score++;
                }
            }
        }

        return score;
    }
  
    
}