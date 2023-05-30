package com.techshopbe.dto;
import com.techshopbe.entity.Review;
import com.techshopbe.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// chi tiet hoa don
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
	private String reviewID;
	private String productID;
	private String fullname;
	private String reviewDate;
	private String reviewContent;
	private float rate;

	public ReviewDTO(Review review,User user) {
		super();
		this.reviewID = review.getReviewID();
		this.productID = review.getProductID();
		this.fullname = user.getFullname();
		this.reviewDate = review.getReviewDate();
		this.reviewContent = review.getReviewContent();
		this.rate = review.getRate();
	}

}
