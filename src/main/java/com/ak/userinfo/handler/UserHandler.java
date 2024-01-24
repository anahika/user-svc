package com.ak.userinfo.handler;

import com.ak.userinfo.domain.User;
import com.ak.userinfo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@Slf4j
public class UserHandler {

    private final UserService userService;

    public UserHandler(UserService userService) {
        this.userService = userService;
    }

//    TODO for this scenario I am supposed to convert Mono object into the user, how can we do that??
//     .block is not recommended, .subscribe is also not giving me the desired output;
    public Mono<ServerResponse> addUser(ServerRequest request) {
        Mono<User> user = request.bodyToMono(User.class);
        log.info("storing user in DB... ");
        return user.flatMap(user1-> ServerResponse.ok().body(userService.addUser(user1), User.class).log());
    }

    public Mono<ServerResponse> getUserById(ServerRequest request) {
        long userId = Integer.parseInt(request.pathVariable("userId"));
        log.info("get request for given userId: " + userId);
        return userService.findByID(userId)
                .flatMap(user -> ok().contentType(MediaType.APPLICATION_JSON).bodyValue(user))
                .switchIfEmpty(ServerResponse.notFound().build()).log();
    }

    public Mono<ServerResponse> getAllUsers(ServerRequest request) {
        Flux<User> users = userService.findAll();
        return ServerResponse.ok().body(users, User.class).log();
    }

}
