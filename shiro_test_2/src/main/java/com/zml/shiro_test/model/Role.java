package com.zml.shiro_test.model;

import java.util.List;

/**
 * 角色实体类
 * @Description: 
 * @author: 朱美炉
 * @date: 2017年6月1日
 * @version: V1.0
 * @类全名：com.zml.shiro_test.model.Role
 */
public class Role {
	
	private Long id;
	private String roleName;	//角色名
	private List <Permission> permissions;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getRoleName() {
		return roleName;
	}
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public List<Permission> getPermissions() {
		return permissions;
	}
	
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	
	
}
