package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserInfo;
import com.example.demo.repository.UserInfoRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserInfoRepository userInfoRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
UserInfo uInfo = userInfoRepository.getByUsername(username);
		
		if(uInfo==null)
			throw new UsernameNotFoundException("Username invalid!!");
		
		List<GrantedAuthority> list = new ArrayList<>();
		SimpleGrantedAuthority sga = new SimpleGrantedAuthority(uInfo.getRole());
		list.add(sga);
		
		User user = new User(uInfo.getUsername(),uInfo.getPassword(),list);
		return user;
	}

}
