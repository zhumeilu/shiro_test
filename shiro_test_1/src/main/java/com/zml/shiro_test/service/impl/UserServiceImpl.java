package com.zml.shiro_test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zml.shiro_test.mapper.UserMapper;
import com.zml.shiro_test.model.User;
import com.zml.shiro_test.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	UserMapper userMapper;
	@Override
	public void save(User user) {
		userMapper.insert(user);
	}}
