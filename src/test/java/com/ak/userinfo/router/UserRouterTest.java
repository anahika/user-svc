package com.ak.userinfo.router;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;


@SpringBootTest
public class UserRouterTest {

    private WebTestClient webTestClient;

    @BeforeEach
//    void setUp(){
//        UserHandler userHandler = new UserHandler(userService);
//        RouterFunction<?> routes = new UserRouter()
//                .getRoute(userHandler);
//        webTestClient = WebTestClient.bindToRouterFunction(routes).build();
//    }

    @Test
    public void getUserById(){
        webTestClient.get()
                     .uri(uriBuilder -> uriBuilder.build("/get-user/{userId}"));
    }

}
