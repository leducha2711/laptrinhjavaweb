package com.laptrinhjavaweb.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.ICategoryDAO;
import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.service.ICategoryService;

public class CategoryService implements ICategoryService{
	
	@Inject
	private ICategoryDAO  categoryDAO;
	
	@Override
	public List<CategoryModel> findAll() {
		return categoryDAO.findAll();
	}

	@Override
	public CategoryModel findOne(Long id) {
		return categoryDAO.findOne(id);
	}

	@Override
	public CategoryModel findOne(String code) {
		return categoryDAO.findOne(code);
	}

}
