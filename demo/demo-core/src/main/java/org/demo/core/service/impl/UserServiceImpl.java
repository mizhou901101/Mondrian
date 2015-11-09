package org.demo.core.service.impl;

import org.demo.common.jodo.UserDO;
import org.demo.core.manage.UserManage;
import org.demo.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


public class UserServiceImpl implements UserService {
	@Autowired
	private UserManage userManage;

	@Transactional
	public UserDO queryUser(Integer id) {
		return userManage.queryUser(id);
	}

}
