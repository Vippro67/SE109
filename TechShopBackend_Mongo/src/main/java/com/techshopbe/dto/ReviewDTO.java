package com.techshopbe.dto;

public class ReviewDTO {
	private String reviewID;
	private String productID;
	private String fullname;
	private String reviewDate;
	private String reviewContent;
	private float rate;

	public ReviewDTO() {
	}

	public ReviewDTO(String reviewID, String productID, String fullname, String reviewDate, String reviewContent,
			float rate) {
		super();
		this.reviewID = reviewID;
		this.productID = productID;
		this.fullname = fullname;
		this.reviewDate = reviewDate;
		this.reviewContent = reviewContent;
		this.rate = rate;
	}

	public String getReviewID() {
		return reviewID;
	}

	public void setReviewID(String reviewID) {
		this.reviewID = reviewID;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

}
