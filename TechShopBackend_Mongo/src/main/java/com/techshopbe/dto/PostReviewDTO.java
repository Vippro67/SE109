package com.techshopbe.dto;
import com.techshopbe.entity.Product;
import com.techshopbe.entity.Review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// chi tiet hoa don
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostReviewDTO {
	private String orderID;
	private String productID;
	private String reviewContent;
	private float rate;

	public PostReviewDTO(Review review, Product product) {
		this.orderID = "0";
		this.productID = product.getProductID();
		this.reviewContent = review.getReviewContent();
		this.rate = review.getRate();
	}
	
}
