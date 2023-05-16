package com.techshopbe.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection =  "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	private String userID;
	private String email;
	private String fullname;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String pswd;
	private String DOB;
	private String phone;
	private String address;
	private String roleID;
	private String gender;
	private int totalInvoices = 0;
	
}
