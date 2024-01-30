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
//        todo should i just return it simply or put inside the exchange out event;???
//         it gave an error on setting the exchange only, do we need to convert it somewhere?
        return userRepository.findById(exchange.getMessage().getBody(Long.class));
    }

    public Flux<User> getAll(Exchange exchange){
        return userRepository.findAll();
    }

    public Mono<User> addUser(Exchange exchange){
        User user = exchange.getIn().getBody(User.class);
        return userRepository.save(user);
    }

}
