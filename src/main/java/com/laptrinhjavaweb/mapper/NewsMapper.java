package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.NewsModel;

public class NewsMapper implements IRowMapper<NewsModel>{

	@Override
	public NewsModel mapRow(ResultSet resultSet) {
		
		try {
			NewsModel model = new NewsModel();
			model.setId(resultSet.getLong("id"));
			model.setTitle(resultSet.getString("title"));
			model.setCategoryId(resultSet.getLong("categoryid"));
			model.setContent(resultSet.getString("content"));
			model.setShortDescription(resultSet.getString("shortdescription"));
			model.setThumbnail(resultSet.getString("thumbnail"));
			model.setCreatedBy(resultSet.getString("createdby"));
			model.setCreatedDate(resultSet.getTimestamp("createddate"));
			if( resultSet.getString("modifiedby") != null) {
				model.setModifiedBy(resultSet.getString("modifiedby"));
			}
			if(resultSet.getTimestamp("modifieddate")!=null) {
				model.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			}
			
			return model;
		} catch (SQLException e) {
			return null;
		}
		
	}

}
