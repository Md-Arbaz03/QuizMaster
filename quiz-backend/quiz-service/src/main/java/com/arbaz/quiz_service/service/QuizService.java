package com.arbaz.quiz_service.service;

import com.arbaz.quiz_service.model.QuestionWrapper;
import com.arbaz.quiz_service.model.Quiz;
import com.arbaz.quiz_service.model.QuizDto;
import com.arbaz.quiz_service.model.Response;
import com.arbaz.quiz_service.repository.QuizRepo;
import com.arbaz.quiz_service.feign.QuizInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {


    private final QuizRepo quizRepo;

    private final QuizInterface quizInterface;

    public QuizService(QuizRepo quizRepo, QuizInterface quizInterface) {
        this.quizRepo = quizRepo;
        this.quizInterface = quizInterface;
    }


    public ResponseEntity<Integer> createQuiz(String category, Integer numQ, String title) {

        List<Integer> questionIds = quizInterface.generateQuestionIdsForQuiz(category, numQ).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questionIds);
        Quiz savedQuiz = quizRepo.save(quiz);

        //return new ResponseEntity<>("Quiz created successfully", HttpStatus.CREATED);
        return new ResponseEntity<>(savedQuiz.getId(), HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Quiz quiz = quizRepo.findById(id).get();
        List<Integer> questionIds = quiz.getQuestionIds();
        ResponseEntity<List<QuestionWrapper>> quizQuestions = quizInterface.getQuestionsFromId(questionIds);
        return quizQuestions;
    }

    public ResponseEntity<Integer> calculateResult(List<Response> response) {

        ResponseEntity<Integer> score = quizInterface.getScore(response);

        return score;
    }
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        try {
            return new ResponseEntity<>(quizRepo.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
}
