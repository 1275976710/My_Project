package com.itheima.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itheima.service.UserService;

//校验用户名的功能
public class CheckUsernameServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		//获得用户名
		String username = request.getParameter("username");	
		UserService service = new UserService();
		boolean isExist = service.checkUsername(username);
		
		//向页面写回json字符串
		String json = "{\"isExist\":"+isExist+"}";	
		response.getWriter().write(json);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}