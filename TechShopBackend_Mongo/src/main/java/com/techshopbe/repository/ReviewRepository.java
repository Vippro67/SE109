package com.techshopbe.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.techshopbe.dto.ReviewDTO;
import com.techshopbe.entity.Review;

public interface ReviewRepository extends MongoRepository<Review, String>  {
	

}
