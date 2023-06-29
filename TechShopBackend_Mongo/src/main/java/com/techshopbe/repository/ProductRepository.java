package com.techshopbe.repository;

import java.util.List;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.techshopbe.dto.DetailedProductDTO;
import com.techshopbe.dto.ProductDTO;
import com.techshopbe.dto.RatingInfoDTO;
import com.techshopbe.entity.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

    @Query(value = "{'categoryID': ?0}")
    List<Product> findByCategoryID(String categoryID);

    @Query(value = "{'brandID': ?0}")
    List<Product> findByBrandID(String brandID);
}
