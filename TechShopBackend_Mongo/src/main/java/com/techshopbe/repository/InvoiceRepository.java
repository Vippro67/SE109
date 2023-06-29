package com.techshopbe.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.techshopbe.entity.Invoice;

public interface InvoiceRepository extends MongoRepository<Invoice, String>{
	
	@Query(value = "{'userID': ?0}")
	List<Invoice> findByUserID(String userID);

	@Query(value = "{'invoiceID': ?0}")
	Invoice findByInvoiceID(String invoiceID);
}
