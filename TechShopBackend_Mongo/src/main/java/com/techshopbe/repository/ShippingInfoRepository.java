package com.techshopbe.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.techshopbe.dto.ShippingInfoDTO;
import com.techshopbe.entity.ShippingInfo;

public interface ShippingInfoRepository extends MongoRepository<ShippingInfo, String> {

	@Query("SELECT new com.techshopbe.dto.ShippingInfoDTO(s.fullname, s.phone, s.address) FROM ShippingInfo s WHERE s.invoiceID = ?1")
	ShippingInfoDTO findByInvoiceID(String invoiceID);
}
