package com.techshopbe.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection =  "category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
	@Id 
	private String categoryID;
	private String categoryName;
	private String categorySlug;
	private boolean categoryExact;
	
}
