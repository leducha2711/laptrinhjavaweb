package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.model.NewsModel;

public interface INewsDAO {
	NewsModel findOne(Long id);
	List<NewsModel> findByCategoryId(Long categoryId);
	Long save(NewsModel newsModel);
	void update(NewsModel updateNews);
	void delete(long id);
	List<NewsModel> findAll(Integer offset, Integer limit);
	int getTotalItem();
	
}
