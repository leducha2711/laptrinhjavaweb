package com.laptrinhjavaweb.dao;

import com.laptrinhjavaweb.model.UserModel;

public interface IUserDAO {
	UserModel findUserByUsernameAndPasswordAndStatus(String username,String password,int status);
}
