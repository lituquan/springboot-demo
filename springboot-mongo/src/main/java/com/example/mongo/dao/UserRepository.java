package com.example.mongo.dao;

import com.example.mongo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User,Integer> {
    User findByName(String name);

    User findFirstByUserId(Integer id);
}
