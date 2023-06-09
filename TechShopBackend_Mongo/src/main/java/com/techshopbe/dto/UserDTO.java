package com.techshopbe.dto;
import com.techshopbe.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// chi tiet hoa don
@Data
@NoArgsConstructor
public class UserDTO {
	private String userID;
	private String fullname;
	private String phone;
	private String address;
	private String email;
	private String gender;
	private String dob;

	public UserDTO(User user) {
		this.userID = user.getUserID();
		this.fullname = user.getFullname();
		this.phone = user.getPhone();
		this.address = user.getAddress();
		this.email = user.getEmail();
		this.gender = user.getGender();
		this.dob = user.getDOB();
	}
}
