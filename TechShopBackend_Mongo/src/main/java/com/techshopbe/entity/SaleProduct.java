package com.techshopbe.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection =  "saleproduct")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleProduct {
	@Id
	private String productID;
	private String startSale;
	private String endSale;
	private int productPrice;
}
