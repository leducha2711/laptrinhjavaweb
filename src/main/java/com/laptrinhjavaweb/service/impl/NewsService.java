package com.laptrinhjavaweb.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.ICategoryDAO;
import com.laptrinhjavaweb.dao.INewsDAO;
import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.service.INewsService;

public class NewsService implements INewsService{

	@Inject
	private INewsDAO newsDAO;
	
	@Inject
	private ICategoryDAO categoryDAO;
	@Override
	public List<NewsModel> findByCategoryId(Long categoryId) {
		
		return newsDAO.findByCategoryId(categoryId);
	}

	@Override
	public NewsModel save(NewsModel newsModel) {
		newsModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		CategoryModel categoryModel = categoryDAO.findOne(newsModel.getCategoryCode());
		newsModel.setCategoryId(categoryModel.getId());
		Long id = newsDAO.save(newsModel);
		return newsDAO.findOne(id);
	}

	@Override
	public NewsModel update(NewsModel updateNews) {
		NewsModel oldNews = newsDAO.findOne(updateNews.getId());
		updateNews.setCreatedDate(oldNews.getCreatedDate());
		updateNews.setCreatedBy(oldNews.getCreatedBy());
		updateNews.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		CategoryModel categoryModel = categoryDAO.findOne(updateNews.getCategoryCode());
		updateNews.setCategoryId(categoryModel.getId());
		newsDAO.update(updateNews);
		return newsDAO.findOne(updateNews.getId());
	}

	@Override
	public void delete(long[] ids) {
		for(long id : ids) {
			newsDAO.delete(id);
		}
	}

	@Override
	public List<NewsModel> findAll(Integer offset, Integer limit) {
		return newsDAO.findAll(offset, limit);
	}

	@Override
	public int getTotalItem() {
		return newsDAO.getTotalItem();
	}

	@Override
	public NewsModel findOne(long id) {
		NewsModel model = newsDAO.findOne(id);
		CategoryModel categoryModel = categoryDAO.findOne(model.getCategoryId());
		model.setCategoryCode(categoryModel.getCode());
		return model;
	}

	

}
