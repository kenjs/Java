package com.cy.swp.action;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.swp.common.constants.Constants;
import com.cy.swp.common.util.JSonRespone;
import com.cy.swp.domain.OrderCargoInfoDomain;
import com.cy.swp.service.OrderCargoInfoService;
/**
 * 统计某个营销专员今天导入的货源总条数
 * @author zdy
 *
 */

@Scope("prototype")
@Controller("countTodayImportCargoAction")
public class CountTodayImportCargoAction extends BaseAction{
	 private static final long serialVersionUID = 669803459457232348L;
	 private Logger logger = LoggerFactory.getLogger(getClass());
	 @Resource
	 private OrderCargoInfoService orderCargoInfoService;
	 
	 @RequestMapping("/countTodayImportCargo.jspx")
	 @ResponseBody
	protected JSonRespone execMethod(OrderCargoInfoDomain orderCargoInfoDomain) throws Exception {
	        logger.debug("count today import cargo begin. userId=[{}]",
	                getSessionUser().getId());
	        
	     //查询今天导入的货源总条数
	     Integer cargoCount=orderCargoInfoService.queryTodayImportCargoCount(String.valueOf(getSessionUser().getId()),"");//删除字段赋值为空
	     //查询无效货源
	     Integer invalidCargoCount=orderCargoInfoService.queryTodayImportCargoCount(String.valueOf(getSessionUser().getId()),String.valueOf(Constants.DELETED_FLAG_TRUE));//删除字段赋值为空
	     
	     return JSonRespone.makeHasContentJSonRespone("0", String.valueOf(cargoCount+","+invalidCargoCount));
	 }
}
