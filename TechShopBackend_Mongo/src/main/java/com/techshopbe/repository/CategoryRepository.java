package com.techshopbe.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.techshopbe.entity.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {

}
