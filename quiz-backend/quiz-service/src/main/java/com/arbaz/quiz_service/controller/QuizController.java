package com.arbaz.quiz_service.controller;

import com.arbaz.quiz_service.model.QuestionWrapper;
import com.arbaz.quiz_service.model.Quiz;
import com.arbaz.quiz_service.model.QuizDto;
import com.arbaz.quiz_service.model.Response;
import com.arbaz.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/create")
    public ResponseEntity<Integer> createQuiz(@RequestBody QuizDto quizDto) {
        return quizService.createQuiz(quizDto.getCategory(), quizDto.getNumQ(), quizDto.getTitle());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id) {
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("/submit")
    public ResponseEntity<Integer> submitQuiz(@RequestBody List<Response> response) {
        return quizService.calculateResult(response);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

}
