package com.project.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.project.interview.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByCategory(String category);

}