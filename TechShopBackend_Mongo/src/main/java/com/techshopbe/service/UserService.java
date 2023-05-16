package com.techshopbe.service;

import java.util.List;

import com.techshopbe.dto.ShippingInfoDTO;
import com.techshopbe.dto.UserDTO;
import com.techshopbe.entity.User;

public interface UserService {
	List<User> getAll();
	UserDTO getById(String id);
	void add(User user) throws Exception;
	void delete(String id);
	ShippingInfoDTO getShippingInfoByEmail(String email);
}
