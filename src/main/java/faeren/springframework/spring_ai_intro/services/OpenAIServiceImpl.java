package faeren.springframework.spring_ai_intro.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import faeren.springframework.spring_ai_intro.model.Answer;
import faeren.springframework.spring_ai_intro.model.GetCapitalRequest;
import faeren.springframework.spring_ai_intro.model.GetCapitalResponse;
import faeren.springframework.spring_ai_intro.model.Question;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.parser.BeanOutputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OpenAIServiceImpl implements OpenAIService {

    private final ChatClient chatClient;
    private final ObjectMapper objectMapper;

    public OpenAIServiceImpl(ChatClient chatClient, ObjectMapper objectMapper) {
        this.chatClient = chatClient;
        this.objectMapper = objectMapper;
    }

    @Value("classpath:templates/get-capital-prompt.st")
    private Resource getCapitalPrompt;

    @Value("classpath:templates/get-capital-with-info.st")
    private Resource getCapitalPromptWithInfo;

    @Override
    public Answer getCapital(Question question) {
        PromptTemplate promptTemplate = new PromptTemplate(question.question());
        Prompt prompt = promptTemplate.create();
        ChatResponse response = chatClient.call(prompt);
        return new Answer(response.getResult().getOutput().getContent()) ;
    }

    @Override
    public GetCapitalResponse getCapital(GetCapitalRequest getCapitalRequest) {
        BeanOutputParser<GetCapitalResponse> parser = new BeanOutputParser<>(GetCapitalResponse.class);
        String format = parser.getFormat();;
        System.out.println("Format : \n" + format);

        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);

        Prompt prompt = promptTemplate.create(Map.of(
                "stateOrCountry",getCapitalRequest.stateOrCountry(),
                "format",format));

        ChatResponse response = chatClient.call(prompt);

        System.out.println(response.getResult().getOutput().getContent());

        return parser.parse(response.getResult().getOutput().getContent()) ;
    }

    @Override
    public Answer getCapitalWithInfo(GetCapitalRequest getCapitalRequest) {
        //PromptTemplate promptTemplate = new PromptTemplate("What is the capital of " + getCapitalRequest.stateOrCountry()+" ?");
        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPromptWithInfo);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry",getCapitalRequest.stateOrCountry()));
        ChatResponse response = chatClient.call(prompt);
        return new Answer(response.getResult().getOutput().getContent()) ;
    }

    @Override
    public String getCapital(String question) {
        PromptTemplate promptTemplate = new PromptTemplate(question);
        Prompt prompt = promptTemplate.create();
        ChatResponse response = chatClient.call(prompt);
        return response.getResult().getOutput().getContent();
    }
}
