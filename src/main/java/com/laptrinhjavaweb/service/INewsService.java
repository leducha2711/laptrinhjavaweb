package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.model.NewsModel;

public interface INewsService {
	List<NewsModel> findByCategoryId(Long categoryId);
	NewsModel save(NewsModel newsModel);
	NewsModel update(NewsModel updateNews);
	void delete(long[] ids);
	List<NewsModel> findAll(Integer offset, Integer limit);
	int getTotalItem();
	NewsModel findOne(long id);
}
