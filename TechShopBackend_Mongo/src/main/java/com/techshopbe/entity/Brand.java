package com.techshopbe.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection =  "brand")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand {
	@Id
	private String brandID;
	private String brandName;
	private String brandImg;
}
