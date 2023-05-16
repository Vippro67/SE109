package com.techshopbe.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.techshopbe.dto.ReviewDTO;
import com.techshopbe.entity.Review;

public interface ReviewRepository extends MongoRepository<Review, String>  {
	
	@Query("SELECT new com.techshopbe.dto.ReviewDTO(r.reviewID, r.productID, u.fullname, r.reviewDate, r.reviewContent, r.rate) FROM Review r, Product p, User u WHERE r.productID = p.productID AND r.userID = u.userID AND r.productID = ?1 ORDER BY r.reviewDate DESC")
	public List<ReviewDTO> getAllByProductID(String productID, Pageable page);

}
