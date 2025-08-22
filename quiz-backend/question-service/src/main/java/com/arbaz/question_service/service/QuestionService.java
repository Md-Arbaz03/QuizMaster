package com.arbaz.question_service.service;

import com.arbaz.question_service.model.Question;
import com.arbaz.question_service.model.QuestionWrapper;
import com.arbaz.question_service.model.Response;
import com.arbaz.question_service.repository.QuestionRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {


    private final QuestionRepo repo;


    public QuestionService(QuestionRepo repo) {
        this.repo = repo;
    }

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(repo.findAll(), HttpStatus.OK) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND) ;

    }

    public ResponseEntity<List<Question>> getByCategory(String category) {
        try{
            return new ResponseEntity<>(repo.findByCategory(category), HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
        }
       return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            repo.save(question);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Question added successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteById(int id) {
        try {
            repo.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("question has been deleted"+ "with id : " + id, HttpStatus.OK);
    }

    public ResponseEntity<String> updateQuestion(Question question) {
        try {
            repo.save(question);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Question updated successfully", HttpStatus.OK);
    }

    public ResponseEntity<List<Integer>> generateQuestionIdsForQuiz(String category, Integer numQ) {
        List<Integer> quesitionIds = repo.findRandomQuestionIdsByCategory(category, numQ);
        return new ResponseEntity<>(quesitionIds, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionId) {
        List<QuestionWrapper> questionWrappers = new ArrayList<>();
        List<Question> questions = repo.findAllById(questionId);

        for(Question q : questions){
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionWrappers.add(qw);
        }

        return new ResponseEntity<>(questionWrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int score = 0;

        for(Response r : responses){
            Question question = repo.findById(r.getId()).get();
            if(r.getResponse().equals(question.getRightAnswer())){
                score++;
            }
        }
        return new ResponseEntity<>(score, HttpStatus.OK);
    }
}
