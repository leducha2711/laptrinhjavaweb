package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.RoleModel;
import com.laptrinhjavaweb.model.UserModel;

public class UserMapper implements IRowMapper<UserModel>{

	@Override
	public UserModel mapRow(ResultSet resultSet) {
		UserModel userModel = new UserModel();
		try {
			userModel.setId(resultSet.getLong("id"));
			userModel.setUsername(resultSet.getString("username"));
			userModel.setPassword(resultSet.getString("password"));
			userModel.setFullname(resultSet.getString("fullname"));
			userModel.setStatus(resultSet.getInt("status"));
			try {
				RoleModel roleModel = new RoleModel();
				roleModel.setCode(resultSet.getString("code"));
				roleModel.setName(resultSet.getString("name"));
				userModel.setRole(roleModel);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return userModel;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}

}
