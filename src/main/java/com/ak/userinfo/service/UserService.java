package com.ak.userinfo.service;

import com.ak.userinfo.domain.User;
import com.ak.userinfo.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<User> addUser(User user) {
        return userRepository.save(user);
    }

    public Mono<User> findByID(Long userId) {
        return userRepository.findById(userId);
    }

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

}
