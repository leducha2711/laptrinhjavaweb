package com.laptrinhjavaweb.service.impl;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.IUserDAO;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.IUserService;

public class UserService implements IUserService{

	@Inject
	IUserDAO userDAO;
	
	@Override
	public UserModel findUserByUsernameAndPasswordAndStatus(String username, String password, int status) {
		return userDAO.findUserByUsernameAndPasswordAndStatus(username, password, status);
	}
	
}
