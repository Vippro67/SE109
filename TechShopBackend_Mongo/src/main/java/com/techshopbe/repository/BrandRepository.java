package com.techshopbe.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.techshopbe.entity.Brand;
import java.util.List;

public interface BrandRepository extends MongoRepository<Brand, String> {

    @Query("{ 'brandName' : ?0 }")
    List<Brand> findByBrandName(String brandName);
}
