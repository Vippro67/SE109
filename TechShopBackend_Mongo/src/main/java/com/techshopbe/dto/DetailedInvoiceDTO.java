package com.techshopbe.dto;

import com.techshopbe.entity.Category;
import com.techshopbe.entity.DetailedInvoice;
import com.techshopbe.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// chi tiet hoa don
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailedInvoiceDTO {
	private String productID;
	private int productPrice;
	private int quantity;
	private int totalPrice;
	private String productName;
	private boolean isReviewed;
	private String images;
	private String categorySlug;

	public DetailedInvoiceDTO(DetailedInvoice detailedInvoice, Product product, Category category) {
		productID = product.getProductID();
		productPrice = product.getProductPrice();
		quantity = detailedInvoice.getQuantity();
		totalPrice = detailedInvoice.getTotalPrice();
		productName = product.getProductName();
		isReviewed = detailedInvoice.isReviewed();
		images = product.getImages();
		categorySlug = category.getCategorySlug();
	}

}
