package com.techshopbe.service.impl;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.techshopbe.dto.PostReviewDTO;
import com.techshopbe.dto.ReviewDTO;
import com.techshopbe.entity.Review;
import com.techshopbe.entity.User;
import com.techshopbe.repository.ReviewRepository;
import com.techshopbe.repository.UserRepository;
import com.techshopbe.security.CustomUserDetails;
import com.techshopbe.service.ReviewService;


@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewRepository reviewRepository;
@Autowired
UserRepository userRepository;
	@Override
	public List<ReviewDTO> getAllReviewsByProductID(String productID, Pageable page) {
		List<Review> listReviews = reviewRepository.findAll();
		List<ReviewDTO> listReviewDTOs = new ArrayList<ReviewDTO>();

		Optional<User> user;
		for (Review review : listReviews) {
			if(review.getProductID()==productID)
				{
					user = userRepository.findById(review.getUserID());
					ReviewDTO reviewDTO= new ReviewDTO(review, user.get());
					listReviewDTOs.add(reviewDTO);
				}
		}

		return listReviewDTOs;
		
	}

	@Override
	public void addReview(PostReviewDTO postReviewDTO) {
		
		Review review = new Review();
		// set general info for review
		review.setProductID(postReviewDTO.getProductID());
		review.setRate(postReviewDTO.getRate());
		review.setReviewContent(postReviewDTO.getReviewContent());
		
		LocalDateTime reviewDate = LocalDateTime.now();
		review.setReviewDate(reviewDate.toString());
		
		// set userID for review
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails)auth.getPrincipal();
		
		String userID = userDetails.getUserID();
		review.setUserID(userID);
		
		reviewRepository.save(review);
		
	}

	

}
