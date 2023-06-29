package com.techshopbe.service;

import java.util.List;

import com.techshopbe.dto.DetailedProductDTO;
import com.techshopbe.dto.ProductDTO;

public interface ProductService {
	public List<ProductDTO> getAll();

	public List<ProductDTO> getTrendingProducts();

	public List<ProductDTO> getProductsByCategory(String categorySlug);

	public List<ProductDTO> getTopPurchasedProducts(String categoryID);

	public DetailedProductDTO getDetailedProduct(String productID);

	public List<ProductDTO> getRelatedCategoryProducts(String productID);

	public List<ProductDTO> getRelatedBrandProducts(String productID);

	public void updateRating(String productID, float rate);

	public List<ProductDTO> getProductToAdvise(String categoryName, String brandName, double PriceMax, double PriceMin);
}
