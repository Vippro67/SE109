package com.techshopbe.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.techshopbe.entity.User;

public interface UserRepository extends MongoRepository<User, String> {

    User findFirstByEmail(String email);

}
