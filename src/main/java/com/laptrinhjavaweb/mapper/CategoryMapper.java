package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.CategoryModel;

public class CategoryMapper implements IRowMapper<CategoryModel> {

	@Override
	public CategoryModel mapRow(ResultSet resultSet) {
		
		try {
			CategoryModel categoryModel = new CategoryModel();
			categoryModel.setId(resultSet.getLong("id"));
			categoryModel.setName(resultSet.getString("name"));
			categoryModel.setCode(resultSet.getString("code"));
			return categoryModel;
		} catch (SQLException e) {
			return null;
		}
		
	}

}
