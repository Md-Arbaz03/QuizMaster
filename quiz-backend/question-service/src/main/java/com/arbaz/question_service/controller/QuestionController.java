package com.arbaz.question_service.controller;

import com.arbaz.question_service.model.Question;
import com.arbaz.question_service.model.QuestionWrapper;
import com.arbaz.question_service.model.Response;
import com.arbaz.question_service.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestion(){
        return questionService.getAllQuestions();
    }

    @GetMapping("/category/{cat}")
    public ResponseEntity<List<Question>> getByCategory(@PathVariable("cat") String category){
        return questionService.getByCategory(category);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable int id){
        return questionService.deleteById(id);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateQuestion(@RequestBody Question question){
        return questionService.updateQuestion(question);
    }

    @PostMapping("/generate")
    public ResponseEntity<List<Integer>> generateQuestionIdsForQuiz(@RequestParam String category, @RequestParam Integer numQ) {
        return questionService.generateQuestionIdsForQuiz(category, numQ);
    }

    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionId) {
        return questionService.getQuestionsFromId(questionId);
    }

    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses) {
        return questionService.getScore(responses);
    }

}
