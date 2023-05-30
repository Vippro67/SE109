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
public class ProductDTO {

	private String productID;
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
	private String categorySlug;
	private String images;
	

	public ProductDTO(Product product, Brand brand, Category category) {
		super();
		productID = product.getProductID();
		categoryName = category.getCategoryName();
		brandName = brand.getBrandName();
		productRate = product.getProductRate();
		productName = product.getProductName();
		productPrice = product.getProductPrice();
		shortDescrip = product.getShortDescrip();
		longDescrip = product.getLongDescrip();
		stock = product.getStock();
		warranty = product.getWarranty();
		purchased = product.getPurchased();
		specs = product.getSpecs();
		categorySlug = category.getCategorySlug();
		images = product.getImages();
	}

}
