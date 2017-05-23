package com.zml.shiro_test.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.zml.shiro_test.service.IUserService;

public class UserRealm extends AuthorizingRealm {

	@Autowired
	private IUserService userService;

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection paramPrincipalCollection) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken paramAuthenticationToken) throws AuthenticationException {
		String username = (String)paramAuthenticationToken.getPrincipal();
		
		return null;
	}
}
