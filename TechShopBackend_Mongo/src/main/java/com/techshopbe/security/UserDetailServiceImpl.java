package com.techshopbe.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.techshopbe.entity.User;
import com.techshopbe.repository.RoleRepository;
import com.techshopbe.repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = null;
		List<User> listUsers = userRepository.findAll();
		for (User user1 : listUsers) {
			if (user1.getEmail() == email)
				user=user1;
		}
		if (user == null)
			throw new UsernameNotFoundException("Khong tim thay");

		String roleName = roleRepository.findById(user.getRoleID()).get().getRoleName();
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		authorities.add(new SimpleGrantedAuthority(roleName));

		CustomUserDetails customUserDetails = new CustomUserDetails(email, user.getPswd(), user.getUserID(),
				authorities);
		return customUserDetails;
	}

}
