package faeren.springframework.spring_ai_intro;

import faeren.springframework.spring_ai_intro.services.OpenAIService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OpenAIServiceImplTest {

    @Autowired
    OpenAIService openAIService;

    @Test
    void getAnswer() {
        String answer = openAIService.getCapital("Tell me a dad joke");
        System.out.println("Got the answer");
        System.out.println(answer);
    }
}