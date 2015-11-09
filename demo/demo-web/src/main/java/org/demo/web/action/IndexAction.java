package org.demo.web.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.demo.common.jodo.UserDO;
import org.demo.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("package1")
@Namespace("/demo")
@Results({@Result(name = "success", location = "/demo_vm/index.vm")}) 
@ExceptionMappings( { @ExceptionMapping(exception = "java.lange.RuntimeException", result = "exception") }) 
public class IndexAction {
	private String key;
	
	private Map<String, Object> map;
	
	private UserDO userDO;
	

	@Autowired
	private UserService userService;
	
	@Action("index")
	public String execute() {
		key = "haha";
		map = new HashMap<String, Object>();
		map.put("k1", "v1");
		userDO = userService.queryUser(1);
		return "success";
	}
	
	public Map<String, Object> getMap() {
		return map;
	}


	public void setMap(Map<String, Object> map) {
		this.map = map;
	}


	public String getKey() {
		return key;
	}


	public void setKey(String key) {
		this.key = key;
	}
	
	public UserDO getUserDO() {
		return userDO;
	}


	public void setUserDO(UserDO userDO) {
		this.userDO = userDO;
	}

}
