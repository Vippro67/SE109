package com.techshopbe.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.techshopbe.entity.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
	
}
