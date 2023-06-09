package com.techshopbe.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.techshopbe.entity.Category;
import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, String> {
    List<Category> findByCategoryName(String categoryName);
}
