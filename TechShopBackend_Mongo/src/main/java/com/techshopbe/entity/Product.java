package com.techshopbe.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection =  "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	@Id
	private String productID;
	private String categoryID;
	private String brandID;
	private float productRate;
	private String productName;
	private int productPrice;
	private String shortDescrip;
	private String longDescrip;
	private int stock;
	private int warranty;
	private int purchased;
	private String specs;
	private String shortTech;
	private int totalReviews;
	private String images;

}
