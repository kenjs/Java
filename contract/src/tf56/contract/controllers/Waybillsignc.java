package tf56.contract.controllers;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.serfj.RestController;
import net.sf.serfj.annotations.POST;

import org.apache.log4j.Logger;

import tf56.contract.domain.WaybillSign;
import tf56.contract.services.WaybillLogDao;
import tf56.contract.services.WaybillSignDao;
import tf56.contract.util.GetFormatDate;
import tf56.contract.util.SessionUtil;
import tf56.site.domain.SessionBean;
import tf56.sofa.serializer.JsonResponse;
import tf56.sofa.util.ParseFormToBean;
import tf56.sofa.util.SofaSpringContext;
import tf56.sofa.util.SysUtil;

public class Waybillsignc extends RestController {
	private final Logger log = Logger.getLogger("Waybillsignc.java");

	// 增 必须把获取的map转为bean对数据库进行操作
	@POST
	public String insert() throws IOException {
		Map formMap = this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		WaybillSign waybillSign = new WaybillSign(); // 把map转成bean对象
		ParseFormToBean pftb = new ParseFormToBean();
		waybillSign = (WaybillSign) pftb.parseToBean(formMap, waybillSign);// 将map转为bean对象
		WaybillSignDao waybillSignDao = (WaybillSignDao) SofaSpringContext.getBean("waybillSignDao"); // 调用接口(实现类)
		//获取登录用户信息
		HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);

		String sureman = sessionBean.getRealname();
		waybillSign.setInputman(sureman);//输入人
		waybillSign.setInputdate(GetFormatDate.getCurrentDate());
		String msgJson = waybillSignDao.insert(waybillSign); // 调用Dao同名方法
		//添加日志操作记录
		String currrentDate = GetFormatDate.getCurrentDate();
		formMap.put("status", "已签收");
		formMap.put("trace", "运单已签收");
		formMap.put("inputDate", currrentDate);
		formMap.put("inputMan", sureman);
		WaybillLogDao waybillLogDao = (WaybillLogDao) SofaSpringContext.getBean("waybillLogDao"); //调用接口(实现类)
		waybillLogDao.insert(formMap);  //保存日志操作记录
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson); //返回给第1外包 Json格式: msg:ok|错误信息,id:int值
	}
	
}