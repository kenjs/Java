package jersey.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import jersey.domain.User;
import jersey.service.LoginDao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/")
public class Login {
	private static Logger logger = Logger.getLogger(Login.class);
	@Autowired
	private LoginDao loginDao;
	
	public LoginDao getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	@GET
	@Path("login")
	public void login(@Context HttpServletResponse response) {
		try {
			logger.debug("打开登陆页面");
			response.sendRedirect("../views/login.html");
		} catch (IOException e) {
			e.printStackTrace();
			logger.debug("没找到登陆页面");
		}
	}
	
	@POST
	@Path("checkLogin")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void loginJson(@FormParam("username") String username,@FormParam("password") String password,
			@Context HttpServletResponse response) {
		logger.debug("登录名："+ username);
		System.out.println(username+","+password);
		Map map = new HashMap();
		map.put("username", username);
		map.put("password", password);
		User user = loginDao.selectUserById(map);
		System.out.println(user);
		try {
			if(user != null){	
				response.sendRedirect("../views/main.html");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
}
