package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import com.laptrinhjavaweb.dao.ICategoryDAO;
import com.laptrinhjavaweb.mapper.CategoryMapper;
import com.laptrinhjavaweb.model.CategoryModel;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO {

	@Override
	public List<CategoryModel> findAll() {
		String sql = "SELECT * FROM category";
		
		return query(sql, new CategoryMapper());
	}

	@Override
	public CategoryModel findOne(Long id) {
		String sql = "SELECT * FROM category WHERE id = ?";
		List<CategoryModel> models = query(sql, new CategoryMapper(), id);
		return models.isEmpty() ? null : models.get(0);
		
	}

	@Override
	public CategoryModel findOne(String code) {
		String sql = "SELECT * FROM category WHERE code = ?";
		List<CategoryModel> models = query(sql, new CategoryMapper(), code);
		return models.isEmpty() ? null : models.get(0);
	}

}
