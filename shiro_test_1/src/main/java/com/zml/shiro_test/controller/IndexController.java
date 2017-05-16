package com.zml.shiro_test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zml.shiro_test.model.User;
import com.zml.shiro_test.service.IUserService;

@Controller
public class IndexController {
	
	@Autowired
	IUserService userService;
	
	@RequestMapping("/index")
	public void index(){
		System.out.println("---------index-------------");
		User user = new User();
		user.setUsername("zml");
		user.setPassword("123456");
		userService.save(user);
	}
	
}
