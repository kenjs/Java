package com.cy.driver.action;

import com.cy.driver.common.action.WebBaseAction;
import com.cy.driver.common.util.ImgCodeUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Scope("prototype")
@Controller("imageCodeAction")
public class ImageCodeAction extends WebBaseAction{

	@RequestMapping(value = "/imageCodeAction")
    @ResponseBody
	public String exec() throws Exception {
		ImgCodeUtil.drawImg(0, 0, response);
		return null;
	}

}
