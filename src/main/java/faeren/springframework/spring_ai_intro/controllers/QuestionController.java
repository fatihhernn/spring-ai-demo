package faeren.springframework.spring_ai_intro.controllers;

import faeren.springframework.spring_ai_intro.model.Answer;
import faeren.springframework.spring_ai_intro.model.GetCapitalRequest;
import faeren.springframework.spring_ai_intro.model.GetCapitalResponse;
import faeren.springframework.spring_ai_intro.model.Question;
import faeren.springframework.spring_ai_intro.services.OpenAIService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {

    private final OpenAIService openAIService;

    public QuestionController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping("/ask")
    public Answer askQuestion(@RequestBody Question question){
        return openAIService.getCapital(question);
    }

    @PostMapping("/capital")
    public GetCapitalResponse getCapital(@RequestBody GetCapitalRequest getCapitalRequest){
        return openAIService.getCapital(getCapitalRequest);
    }

    @PostMapping("/capital-with-info")
    public Answer getCapitalWithInfo(@RequestBody GetCapitalRequest getCapitalRequest){
        return openAIService.getCapitalWithInfo(getCapitalRequest);
    }

}
