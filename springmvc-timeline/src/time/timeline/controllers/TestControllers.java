package time.timeline.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

@Controller
@RequestMapping("/time")
public class TestControllers extends MultiActionController{
	
	@RequestMapping("/index")
	public String index(HttpServletRequest resquest,HttpServletResponse response) {
		
		return "hello";
	}
}
