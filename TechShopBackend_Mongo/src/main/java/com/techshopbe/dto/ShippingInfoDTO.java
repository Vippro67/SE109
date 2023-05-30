package com.techshopbe.dto;
import com.techshopbe.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// chi tiet hoa don
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShippingInfoDTO {
	private String fullname;
	private String phone;
	private String address;

	
	public ShippingInfoDTO(User user) {
		this.fullname = user.getFullname();
		this.phone = user.getPhone();
		this.address = user.getAddress();
	}
}
