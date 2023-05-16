package com.techshopbe.repository;

import java.util.List;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.techshopbe.dto.DetailedProductDTO;
import com.techshopbe.dto.ProductDTO;
import com.techshopbe.dto.RatingInfoDTO;
import com.techshopbe.entity.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

	@Query("{}")
	List<ProductDTO> getAll();

	@Query("{'categoryID': ?0}")
	List<ProductDTO> findTopPurchasedByCategoryId(String categoryID);

	@Query("{}")
	List<ProductDTO> findTrendingProducts();

	@Query("SELECT new com.techshopbe.dto.ProductDTO(p.productID, c.categoryName, b.brandName, p.productRate, p.productName, p.productPrice, p.shortDescrip, p.longDescrip,p.stock, p.warranty, p.purchased, p.specs, c.categorySlug, p.images) FROM Product p, Category c, Brand b WHERE p.categoryID = c.categoryID AND p.brandID = b.brandID AND c.categorySlug = ?1")
	List<ProductDTO> findByCategorySlug(String categorySlug);

	@Query("SELECT productPrice FROM Product p where p.productID = ?1")
	int findProductPriceByProductID(String productID);

	@Query("SELECT new com.techshopbe.dto.ProductDTO(p.productID, c.categoryName, b.brandName, p.productRate, p.productName, p.productPrice, p.shortDescrip, p.longDescrip,p.stock, p.warranty, p.purchased, p.specs, c.categorySlug, p.images) FROM Product p, Category c, Brand b WHERE p.categoryID = c.categoryID AND p.brandID = b.brandID AND p.productID != ?1 AND c.categoryID = (SELECT categoryID FROM Product p where p.productID = ?1) ")
	List<ProductDTO> findRelatedProductsByCategory(String productID);
	
	@Query("SELECT new com.techshopbe.dto.ProductDTO(p.productID, c.categoryName, b.brandName, p.productRate, p.productName, p.productPrice, p.shortDescrip, p.longDescrip,p.stock, p.warranty, p.purchased, p.specs, c.categorySlug, p.images) FROM Product p, Category c, Brand b WHERE p.categoryID = c.categoryID AND p.brandID = b.brandID AND p.productID != ?1 AND b.brandID = (SELECT brandID FROM Product p where p.productID = ?1) ")
	List<ProductDTO> findRelatedProductsByBrand(String productID);
	
    @Query("UPDATE Product p SET p.productRate = ?1, p.totalReviews = ?2 WHERE p.productID = ?3")
    int updateRatingInfoByProductID(float rate, int totalReviews, String productID);

    DetailedProductDTO findDetailedProductByProductID(String productID);

    RatingInfoDTO findRatingInfoByProductID(String productID);

}
