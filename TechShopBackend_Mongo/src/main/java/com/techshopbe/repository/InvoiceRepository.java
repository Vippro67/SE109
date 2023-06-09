package com.techshopbe.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.techshopbe.entity.Invoice;

public interface InvoiceRepository extends MongoRepository<Invoice, String>{
	
	List<Invoice> findByUserID(String userID);
	Invoice findByInvoiceID(String invoiceID);
}
