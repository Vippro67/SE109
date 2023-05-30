package com.techshopbe.repository;

import java.util.List;


import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.techshopbe.dto.DetailedInvoiceDTO;
import com.techshopbe.entity.DetailedInvoice;

public interface DetailedInvoiceRepository extends MongoRepository<DetailedInvoice, String>{


}
