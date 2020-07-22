package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import com.laptrinhjavaweb.dao.INewsDAO;
import com.laptrinhjavaweb.mapper.NewsMapper;
import com.laptrinhjavaweb.model.NewsModel;

public class NewsDAO extends AbstractDAO<NewsModel> implements INewsDAO {

	@Override
	public List<NewsModel> findByCategoryId(Long categoryId) {
		String sql = "SELECT * FROM news WHERE categoryid = ?";
		return query(sql, new NewsMapper(), categoryId);
	}

	@Override
	public Long save(NewsModel newsModel) {
		StringBuilder sql = new StringBuilder("INSERT INTO news (title, thumbnail, shortdescription, content, categoryid, createddate, createdby) ");
		sql.append( "VALUES(?, ?, ?, ?, ?, ?, ?)");
		return insert(sql.toString(), newsModel.getTitle(), newsModel.getThumbnail(), newsModel.getShortDescription(),
				newsModel.getContent(), newsModel.getCategoryId(), newsModel.getCreatedDate(), newsModel.getCreatedBy());
	}
	
	@Override
	public NewsModel findOne(Long id) {
		String sql = "SELECT * FROM news WHERE id = ?";
		List<NewsModel> models = query(sql, new NewsMapper(), id);
		return models.isEmpty() ? null : models.get(0);
	}

	@Override
	public void update(NewsModel updateNews) {
		StringBuilder sql = new StringBuilder("UPDATE news SET title =?, thumbnail =?,");
		sql.append("shortdescription =?, content= ?,categoryid=?, createddate=?, ");
		sql.append("createdby =?, modifieddate =?, modifiedby=? WHERE id =?");
		update(sql.toString(), updateNews.getTitle(), updateNews.getThumbnail(), 
				updateNews.getShortDescription(),
				updateNews.getContent(),updateNews.getCategoryId(),
				updateNews.getCreatedDate(), updateNews.getCreatedBy(),
				updateNews.getModifiedDate(), updateNews.getModifiedBy(),
				updateNews.getId());
	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM news WHERE id = ?";
		update(sql, id);
	}

	@Override
	public List<NewsModel> findAll(Integer offset, Integer limit) {
		String sql = "SELECT * FROM news LIMIT ?, ?";
		return query(sql, new NewsMapper(), offset, limit);
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM news ";
		return count(sql);
	}

	
	
	

}
