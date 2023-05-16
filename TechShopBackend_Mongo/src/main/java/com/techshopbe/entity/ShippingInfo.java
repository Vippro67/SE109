package com.techshopbe.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection =  "shippinginfo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShippingInfo {
	@Id
	private String shippingInfoID;
	private String invoiceID;
	private String fullname;
	private String phone;
	private String address;
}
