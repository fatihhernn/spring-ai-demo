package faeren.springframework.spring_ai_intro.services;

import faeren.springframework.spring_ai_intro.model.Answer;
import faeren.springframework.spring_ai_intro.model.GetCapitalRequest;
import faeren.springframework.spring_ai_intro.model.GetCapitalResponse;
import faeren.springframework.spring_ai_intro.model.Question;

public interface OpenAIService {
    String getCapital(String question);

    Answer getCapital(Question question);

    GetCapitalResponse getCapital(GetCapitalRequest getCapitalRequest);

    Answer getCapitalWithInfo(GetCapitalRequest getCapitalRequest);
}
