package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.model.UserModel;

public interface IUserService {
	UserModel findUserByUsernameAndPasswordAndStatus(String username,String password,int status);

}
