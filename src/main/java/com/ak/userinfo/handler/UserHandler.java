package com.ak.userinfo.handler;

import com.ak.userinfo.domain.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.ak.userinfo.constants.Constants.ADD_USER;
import static com.ak.userinfo.constants.Constants.FIND_ALL;
import static com.ak.userinfo.constants.Constants.FIND_BY_ID;

@Component
@Slf4j
@AllArgsConstructor
public class UserHandler {
    private ProducerTemplate producerTemplate;

    public Mono<ServerResponse> addUser(ServerRequest request) {
        log.info("storing user in DB... ");
        Mono<User> storedUser = producerTemplate.requestBody(ADD_USER, request.bodyToMono(User.class), Mono.class);
        return storedUser
                .flatMap(user1 -> ServerResponse.ok().body(user1, User.class).log());
    }

    public Mono<ServerResponse> getUserById(ServerRequest request) {
        long userId = Integer.parseInt(request.pathVariable("userId"));
        log.info("get request for given userId: " + userId);
        Mono<User> user = producerTemplate.requestBody(FIND_BY_ID, userId, Mono.class);
        return user
                .flatMap(user1 -> ServerResponse.ok().body(user, User.class))
                .switchIfEmpty(ServerResponse.notFound().build()).log();

    }

    public Mono<ServerResponse> getAllUsers(ServerRequest request) {
        Flux<User> users = producerTemplate.requestBody(FIND_ALL, request, Flux.class);
        return ServerResponse.ok()
                .body(users.collectList(), User.class)
                .switchIfEmpty(ServerResponse.noContent().build())
                .log();
    }

}
