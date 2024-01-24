package com.ak.userinfo.repository;


import com.ak.userinfo.domain.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepository extends ReactiveMongoRepository<User, Long> {
}
