package com.techshopbe.service.impl;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techshopbe.dto.ShippingInfoDTO;
import com.techshopbe.dto.UserDTO;
import com.techshopbe.entity.User;
import com.techshopbe.repository.UserRepository;
import com.techshopbe.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public UserDTO getById(String id) {
		User user = userRepository.findById(id).get();
		UserDTO userDTO = new UserDTO(user);
		return userDTO;
	}

	@Override
	public void add(User user) throws Exception {

		User entityHasSameEmail = null;
		List<User> listUsers = userRepository.findAll();
		for (User user2 : listUsers) {
			if (user2.getEmail() == user.getEmail()) {
				entityHasSameEmail = user2;
			}
		}
		if (entityHasSameEmail == null) {
			String hashPassword = BCrypt.hashpw(user.getPswd(), BCrypt.gensalt());
			user.setPswd(hashPassword);
			userRepository.save(user);
		} else {
			throw new Exception("Email already existed");
		}
	}

	@Override
	public void delete(String id) {
		userRepository.deleteById(id);
	}

	@Override
	public ShippingInfoDTO getShippingInfoByEmail(String email) {
		User user = null;
		List<User> listUsers = userRepository.findAll();
		for (User user2 : listUsers) {
			if (user2.getEmail() == email) {
				user = user2;
			}
		}

		ShippingInfoDTO shippingInfoDTO = new ShippingInfoDTO(user);
		return shippingInfoDTO;
	}

}
