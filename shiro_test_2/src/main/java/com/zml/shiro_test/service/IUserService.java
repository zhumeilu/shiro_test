package com.zml.shiro_test.service;

import com.zml.shiro_test.model.User;

public interface IUserService {
	void save(User user);

	User getUserByUsername(String username);
}
