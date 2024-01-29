package com.ak.userinfo.processor;

import com.ak.userinfo.domain.User;
import com.ak.userinfo.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
@Slf4j
public class UserMongoProcessor {
    private final UserRepository userRepository;

    public Mono<User> getById(Exchange exchange){
        return userRepository.findById(exchange.getMessage().getBody(Long.class));
    }

    public Flux<User> getAll(Exchange exchange){
        return userRepository.findAll();
    }

    public Mono<User> addUser(Exchange exchange){
        log.info("exchange", exchange);
        User user = exchange.getMessage().getBody(User.class);
        return userRepository.save(user);
    }

}
