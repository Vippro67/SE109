package com.techshopbe.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.techshopbe.dto.ShippingInfoDTO;
import com.techshopbe.entity.User;

public interface UserRepository extends MongoRepository<User, String> {


}
