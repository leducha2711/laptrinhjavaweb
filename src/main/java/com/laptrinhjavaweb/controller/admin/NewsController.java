package com.laptrinhjavaweb.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.INewsService;
import com.laptrinhjavaweb.utils.FormUtil;
import com.laptrinhjavaweb.utils.MessageUtil;

@WebServlet(urlPatterns = {"/admin-news"})
public class NewsController extends HttpServlet{

	private static final long serialVersionUID = -8954396074072052061L;
	
	@Inject
	private INewsService newsService;
	
	@Inject
	private ICategoryService categoryService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NewsModel newsModel = FormUtil.toModel(NewsModel.class, req);
		String views ="";
		if(newsModel.getType().equals(SystemConstant.LIST)) {
			Integer offset = (newsModel.getPage()-1)* newsModel.getMaxPageItem();
			newsModel.setListResult(newsService.findAll(offset, newsModel.getMaxPageItem()));
			newsModel.setTotalItem(newsService.getTotalItem());
			newsModel.setTotalPage((int) Math.ceil((double) newsModel.getTotalItem()/ newsModel.getMaxPageItem()));
			views= "/views/admin/news/list.jsp";
		}else if(newsModel.getType().equals(SystemConstant.EDIT)) {
			List<CategoryModel> categoryModels = categoryService.findAll();
			if(newsModel.getId()!=null) {
				newsModel= newsService.findOne(newsModel.getId());
			}else {
				
			}
			req.setAttribute("categories", categoryModels);
			views= "/views/admin/news/edit.jsp";
		}
		MessageUtil.showMessage(req);
		req.setAttribute(SystemConstant.MODEL, newsModel);
		RequestDispatcher dispatcher = req.getRequestDispatcher(views);
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}
