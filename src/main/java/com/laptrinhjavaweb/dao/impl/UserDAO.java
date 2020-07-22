package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import com.laptrinhjavaweb.dao.IUserDAO;
import com.laptrinhjavaweb.mapper.UserMapper;
import com.laptrinhjavaweb.model.UserModel;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO{

	@Override
	public UserModel findUserByUsernameAndPasswordAndStatus(String username, String password, int status) {
		StringBuilder sql = new StringBuilder("SELECT * FROM user AS u ");
		sql.append("JOIN role AS r ON r.id= u.roleid ");
		sql.append("WHERE username =? AND password =? AND status=? ");
		List<UserModel> userModels = query(sql.toString(), new UserMapper(), username, password,status);
		return userModels.isEmpty()? null:userModels.get(0);
	}

}
