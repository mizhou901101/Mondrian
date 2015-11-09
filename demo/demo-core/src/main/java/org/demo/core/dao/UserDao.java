package org.demo.core.dao;

import org.demo.common.jodo.UserDO;

public interface UserDao {
	public boolean insertUser(UserDO userDO);
	
	public boolean deleteUser(String id);
	
	public boolean updateUser(UserDO userDO);
	
	public UserDO queryUser(Integer id);
}
