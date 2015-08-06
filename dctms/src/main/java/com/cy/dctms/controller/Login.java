package com.cy.dctms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cy.dctms.entity.WebUserInfo;
import com.cy.dctms.interceptor.MerberInterceptor;

@Controller
public class Login {

	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login() {
		
		return "login";
	}
	
	@RequestMapping(value="/dologin",method=RequestMethod.POST)
	public String validateLogin(@RequestParam String code,@RequestParam String password,
			HttpServletRequest request) throws Exception{
		WebUserInfo webUserInfo = new WebUserInfo();
		if("cy".equals(code) && "0".equals(password)) {
			webUserInfo.setCode(code);
			webUserInfo.setPassword(password);
			request.getSession().setAttribute(MerberInterceptor.SESSION_USER, webUserInfo);
			
			return "import";
		}
		return "redirect:/login";
	}
}
