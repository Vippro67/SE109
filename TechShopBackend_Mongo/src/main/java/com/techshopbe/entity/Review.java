package com.techshopbe.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection =  "review")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
	@Id
	private String reviewID;
	private String productID;
	private String userID;
	private String reviewDate;
	private String reviewContent;
	private float rate;

}
