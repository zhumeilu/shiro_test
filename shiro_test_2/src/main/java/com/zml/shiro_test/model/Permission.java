package com.zml.shiro_test.model;

/**
 * 资源实体类
 * @Description: 
 * @author: 朱美炉
 * @date: 2017年6月1日
 * @version: V1.0
 * @类全名：com.zml.shiro_test.model.Permission
 */
public class Permission {
	
	private Long id;
	private String resourceUrl;
	private String resourceName;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getResourceUrl() {
		return resourceUrl;
	}
	
	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}
	
	public String getResourceName() {
		return resourceName;
	}
	
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	
	
}
