package com.techshopbe.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.techshopbe.entity.Category;
import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, String> {

    @Query(value = "{'categoryName' : ?0}")
    List<Category> findByCategoryName(String categoryName);

    @Query(value = "{'categorySlug': ?0}")
    Category findByCategorySlug(String categorySlug);

}
