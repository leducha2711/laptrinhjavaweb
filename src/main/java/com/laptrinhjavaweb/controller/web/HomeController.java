package com.laptrinhjavaweb.controller.web;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.utils.FormUtil;
import com.laptrinhjavaweb.utils.SessionUtil;

@WebServlet(urlPatterns = {"/trang-chu","/dang-nhap","/thoat"})
public class HomeController extends HttpServlet{

	@Inject
	private ICategoryService categoryService;
	
	@Inject
	private IUserService userService;
	
	private static final long serialVersionUID = 2686801510274002166L;
	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if(action !=null && action.equals("login")) {
			String message = req.getParameter("message");
			String alert = req.getParameter("alert");
			if(message!=null && alert!= null) {
				req.setAttribute("message", resourceBundle.getString(message));
				req.setAttribute("alert", alert);
			}
			RequestDispatcher dispatcher = req.getRequestDispatcher("/views/login.jsp");
			dispatcher.forward(req, resp);
		}else if(action !=null && action.equals("logout")) {
			SessionUtil.getInstance().removeValue(req, "USERMODEL");
			resp.sendRedirect(req.getContextPath()+"/trang-chu");
		}
		else {
			req.setAttribute("categories", categoryService.findAll());
			RequestDispatcher dispatcher = req.getRequestDispatcher("/views/web/home.jsp");
			dispatcher.forward(req, resp);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String action = req.getParameter("action");
		if(action !=null && action.equals("login")) {
			
			UserModel userModel = FormUtil.toModel(UserModel.class, req);
			userModel = userService.findUserByUsernameAndPasswordAndStatus(userModel.getUsername(), userModel.getPassword(), 1);
			if(userModel!=null) {
				SessionUtil.getInstance().putValue(req, "USERMODEL", userModel);
				if(userModel.getRole().getCode().equals("USER")) {
					resp.sendRedirect(req.getContextPath()+"/trang-chu");
				}else if(userModel.getRole().getCode().equals("ADMIN")) {
					resp.sendRedirect(req.getContextPath()+"/admin-home");
				}
			}else {
				resp.sendRedirect(req.getContextPath()+"/dang-nhap?action=login&message=username_password_invalid&alert=danger");
			}
		}
	}
}
