package com.techshopbe.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.techshopbe.entity.Brand;

public interface BrandRepository  extends MongoRepository<Brand, String> {

}
