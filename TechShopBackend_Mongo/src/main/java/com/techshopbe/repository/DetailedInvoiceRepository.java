package com.techshopbe.repository;

import java.util.List;


import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.techshopbe.dto.DetailedInvoiceDTO;
import com.techshopbe.entity.DetailedInvoice;

public interface DetailedInvoiceRepository extends MongoRepository<DetailedInvoice, String>{

	@Query("SELECT new com.techshopbe.dto.DetailedInvoiceDTO(p.productID, d.price, d.quantity, d.totalPrice, p.productName, d.isReviewed, p.images, c.categorySlug) FROM Product p, DetailedInvoice d, Category c WHERE p.productID = d.productID AND d.invoiceID = ?1 AND p.categoryID = c.categoryID")
	List<DetailedInvoiceDTO> findAllByInvoiceID(String invoiceID);
	

    @Query("UPDATE DetailedInvoice d SET d.isReviewed = true WHERE d.invoiceID = ?1 AND d.productID = ?2")
    int updateRatingInfoByProductID(String invoiceID, String productID);

}
