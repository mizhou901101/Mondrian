package org.demo.web.rest.example;

//import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.demo.common.jodo.UserDO;
import org.demo.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

//@Namespace("/rest")
public class UserController implements ModelDriven<UserDO> {
	
	// response param
	private UserDO model;
	
	// request param
	private Integer id;
	
	@Autowired
	private UserService userService;
	
	// Handles /user/{id} GET requests
	public HttpHeaders show() {
		model = userService.queryUser(id);
        return new DefaultHttpHeaders("show");
    }
	
	public UserDO getModel() {
		return model;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setModel(UserDO model) {
		this.model = model;
	}
	

}
