package com.ak.userinfo.router;

import com.ak.userinfo.handler.UserHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
@Slf4j
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> getRoute(UserHandler userHandler){
        log.debug("get request for given particular userId");
        return RouterFunctions.route(GET("/get-user/{userId}").and(accept(MediaType.APPLICATION_JSON)), userHandler::getUserById);
    }

    @Bean
    public RouterFunction<ServerResponse> postRoute(UserHandler userHandler){
        return RouterFunctions.route(POST("/add-user").and(accept(MediaType.APPLICATION_JSON)), userHandler::addUser);
    }

    @Bean
    public RouterFunction<ServerResponse> getListRoute(UserHandler userHandler){
        return RouterFunctions.route(GET("/get-users").and(accept(MediaType.APPLICATION_JSON)), userHandler::getAllUsers);
    }


}
