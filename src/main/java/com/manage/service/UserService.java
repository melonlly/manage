package com.manage.service;

import com.manage.entity.User;
import org.springframework.data.domain.Page;

public interface UserService {

	// 查找用户的信息
	Page<User> findUsers(Integer page, Integer size, User user);

	User Login(String name, String password);

}
