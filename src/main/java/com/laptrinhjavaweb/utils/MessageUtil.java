package com.laptrinhjavaweb.utils;

import javax.servlet.http.HttpServletRequest;

public class MessageUtil {
	public static void showMessage(HttpServletRequest request) {
		if(request.getParameter("message") !=null) {
			String messageRespond ="";
			String alert="";
			String message = request.getParameter("message");
			if(message.equals("insert_success")) {
				messageRespond="Insert success";
				alert ="success";
			}else if(message.equals("update_success")) {
				messageRespond="Update success";
				alert ="success";
			}
			else if(message.equals("delete_success")) {
				messageRespond="Delete success";
				alert ="success";
			}
			else if(message.equals("error_system")) {
				messageRespond="Error system";
				alert ="danger";
			}
			request.setAttribute("messageRespond", messageRespond);
			request.setAttribute("alert", alert);
		}
	}
}
