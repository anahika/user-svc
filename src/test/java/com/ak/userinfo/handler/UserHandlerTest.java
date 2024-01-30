package com.ak.userinfo.handler;

import org.apache.camel.ProducerTemplate;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.reactive.function.server.MockServerRequest;
import reactor.core.publisher.Mono;

@SpringBootTest
public class UserHandlerTest {

    @InjectMocks
    private UserHandler userHandler;

    @Mock
    private ProducerTemplate producerTemplate;


    @Test
    public void getUserByIdTest(){
        MockServerRequest serverRequest = MockServerRequest.builder()
                                            .method(HttpMethod.POST)
                                            .body(Mono.just("{\n" +
                                                    "    \"userId\": 4,\n" +
                                                    "    \"name\": \"Anshika\",\n" +
                                                    "    \"status\": \"ACTIVE\"\n" +
                                                    "}"));
        userHandler.addUser(serverRequest);
    }

}
