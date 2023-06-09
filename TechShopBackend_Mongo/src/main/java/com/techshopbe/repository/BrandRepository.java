package com.techshopbe.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.techshopbe.entity.Brand;
import java.util.List;

public interface BrandRepository extends MongoRepository<Brand, String> {
    List<Brand> findByBrandName(String brandName);
}
