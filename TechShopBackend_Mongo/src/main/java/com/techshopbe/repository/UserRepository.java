package com.techshopbe.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.techshopbe.dto.ShippingInfoDTO;
import com.techshopbe.entity.User;

public interface UserRepository extends MongoRepository<User, String> {

	User findByEmail(String email);
	
	@Query("SELECT new com.techshopbe.dto.ShippingInfoDTO(u.fullname, u.phone, u.address) FROM User u WHERE u.email = ?1")
	ShippingInfoDTO findShippingInfoByEmail(String email);
	
	@Query("SELECT totalInvoices FROM User u WHERE u.email = ?1")
	int findTotalInvoicesByEmail(String email);
	
    @Query("UPDATE User u SET u.totalInvoices = ?1 WHERE u.email = ?2")
    int updateTotalInvoicesByEmail(int totalInvoices, String email);
}
