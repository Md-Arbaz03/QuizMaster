package com.arbaz.quiz_service.feign;

import com.arbaz.quiz_service.model.QuestionWrapper;
import com.arbaz.quiz_service.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    @PostMapping("/question/generate")
    public ResponseEntity<List<Integer>> generateQuestionIdsForQuiz(@RequestParam String category, @RequestParam Integer numQ);

    @PostMapping("/question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionId);

    @PostMapping("/question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);

}
