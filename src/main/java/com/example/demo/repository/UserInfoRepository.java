package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

	@Query("select u from UserInfo u where u.username=?1")
	UserInfo getByUsername(String username);

}
