package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;

public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userrepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user =  userrepo.getuserByUserName(username);
		if(user==null) {
			throw new UsernameNotFoundException("No user found for given email");
		}
		else {
			CustomUserDetails userdetail = new CustomUserDetails(user);
			return userdetail;
		}
	}

}
