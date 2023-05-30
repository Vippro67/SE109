package com.techshopbe.dto;

import com.techshopbe.entity.Brand;
import com.techshopbe.entity.Category;
import com.techshopbe.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// chi tiet hoa don
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailedProductDTO {
	private String productID;
	private String categoryID;
	private String brandID;
	private String categoryName;
	private String brandName;
	private float productRate;
	private String productName;
	private int productPrice;
	private String shortDescrip;
	private String longDescrip;
	private int stock;
	private int warranty;
	private int purchased;
	private String specs;
	private String stockStatus;
	private String shortTech;
	private int totalReviews;
	private String images;

	public DetailedProductDTO(Product product, Brand brand, Category category) {
		this.productID = product.getProductID();
		this.categoryID = category.getCategoryID();
		this.brandID = brand.getBrandID();
		this.categoryName = category.getCategoryName();
		this.brandName = brand.getBrandName();
		this.productRate = product.getProductRate();
		this.productName = product.getProductName();
		this.productPrice = product.getProductPrice();
		this.shortDescrip = product.getShortDescrip();
		this.longDescrip = product.getLongDescrip();
		this.stock = product.getStock();
		this.warranty = product.getWarranty();
		this.purchased = product.getPurchased();
		this.specs = product.getSpecs();
		this.shortTech = product.getShortTech();
		this.totalReviews = product.getTotalReviews();
		this.images = product.getImages();
	}

}
