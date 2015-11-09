package org.demo.core.manage.impl;

import org.demo.common.jodo.UserDO;
import org.demo.core.dao.UserDao;
import org.demo.core.manage.UserManage;
import org.springframework.beans.factory.annotation.Autowired;

public class UserManageImpl implements UserManage {
	@Autowired
	private UserDao userDAO;
	
	public UserDO queryUser(Integer id) {
		return userDAO.queryUser(id);
	}

	
}
