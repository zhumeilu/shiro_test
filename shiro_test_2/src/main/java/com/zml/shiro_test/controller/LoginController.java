package com.zml.shiro_test.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("/login")
	public String login(String username,String password){
		
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username,password);
		try {
			subject.login(token);	//会自动委托给SecurityManager.login方法进行登录
			
		} catch (AuthenticationException  e) {
			//UnknownAccountException	错误的帐号
			//LockedAccountException	锁定的帐号
			return "/login";
		}
		return "/index";
	}
	/**
	 * 登出
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(){
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "/index";
	}
}
