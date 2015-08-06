package tf56.contract.controllers;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import net.sf.serfj.RestController;
import net.sf.serfj.annotations.POST;
import net.sf.serfj.annotations.GET;
import tf56.contract.domain.ContractAppendix;
import tf56.contract.services.ContractAppendixDao;
import tf56.sofa.util.ParseFormToBean;
import tf56.sofa.util.SofaSpringContext;
import tf56.sofa.util.SysUtil;

import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.serializer.JsonResponse;

public class Contractappendixc extends RestController {
	// 增 必须把获取的map转为bean对数据库进行操作
	@POST
	public String insert() {
		Map formMap = this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		ContractAppendix contractAppendix = new ContractAppendix(); // 把map转成bean对象
		ParseFormToBean pftb = new ParseFormToBean();
		contractAppendix = (ContractAppendix) pftb.parseToBean(formMap,
				contractAppendix);// 将map转为bean对象
		ContractAppendixDao contractAppendixDao = (ContractAppendixDao) SofaSpringContext
				.getBean("contractAppendixDao"); // 调用接口(实现类)
		String msgJson = contractAppendixDao.insert(contractAppendix); // 调用Dao同名方法
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson); // 返回给第1外包
																	// Json格式:
																	// msg:ok|错误信息,id:int值
	}

	// 删
	@POST
	public String delete() {
		Map formMap = this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		ContractAppendixDao contractAppendixDao = (ContractAppendixDao) SofaSpringContext
				.getBean("contractAppendixDao");
		String msgJson = contractAppendixDao.delete(formMap); // 删除和查询的参数用map
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson); // 返回给第1外包
																	// Json格式:
																	// msg:ok|错误信息,id:int值
	}

	// 改
	@POST
	public String update() {
		Map formMap = this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		ContractAppendix contractAppendix = new ContractAppendix(); // 把map转成bean对象
		ParseFormToBean pftb = new ParseFormToBean();
		contractAppendix = (ContractAppendix) pftb.parseToBean(formMap,
				contractAppendix);// 将map转为bean对象
		ContractAppendixDao contractAppendixDao = (ContractAppendixDao) SofaSpringContext
				.getBean("contractAppendixDao"); // 调用接口(实现类)
		String msgJson = contractAppendixDao.update(contractAppendix);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson); // 返回给第1外包
																	// Json格式:
																	// msg:ok|错误信息,id:int值
	}

	// 查一条
	@POST
	public String selectById() {
		Map formMap = this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		ContractAppendixDao contractAppendixDao = (ContractAppendixDao) SofaSpringContext
				.getBean("contractAppendixDao"); // 调用接口(实现类)
		String msgJson = contractAppendixDao.selectById(formMap); // 删除和查询的参数用map
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson); // 返回给第1外包
																	// Json格式:
																	// 自定义
	}

	// 查多条
	@POST
	public String selectList() {
		Map formMap = this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		ContractAppendixDao contractAppendixDao = (ContractAppendixDao) SofaSpringContext
				.getBean("contractAppendixDao"); // 调用接口(实现类)
		List list = contractAppendixDao.selectList(formMap); // 删除和查询的参数用map
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, JsonGenerateUtil
				.list2json(list)); // 返回给第1外包 Json格式: 自定义
	}

	/**
	 * wei.huang 2013-10-28 检验文件是否已经存在
	 * 
	 * @param map
	 *            ：filename 将被上传的文件名
	 * @return 1表示存在，0表示不存在
	 */
/*	@POST
	public String isExist() { // 控制器 查询:获取客户端提交条件数据到map,
								// 调用实现类同名的方法,返回List类型的json数据
		Map paramsMap = this.getParams();
		String workPath = System.getProperty("myappfuse.root.contract");
		String savePath = workPath + "attachment" + File.separator;
		String msg = "0";
		paramsMap = SysUtil.removeFilter(paramsMap);
		File file = new File(savePath + paramsMap.get("filename"));
		if (file.exists()) {
			msg = "1";
		}
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msg);
	}*/
	@POST
	public String isExist() { // 控制器 查询:获取客户端提交条件数据到map,
								// 调用实现类同名的方法,返回List类型的json数据
		Map paramsMap = this.getParams();
		Calendar calendar=Calendar.getInstance();
		String imagePath="/data/httx/pic/contract/"+String.valueOf(calendar.get(Calendar.YEAR))+File.separator
		+String.valueOf(calendar.get(Calendar.MONTH)+1)+File.separator+String.valueOf(calendar.get(Calendar.DAY_OF_MONTH))+File.separator;
		String msg = "0";
		paramsMap = SysUtil.removeFilter(paramsMap);
		File file = new File(imagePath + paramsMap.get("filename"));
		if (file.exists()) {
			msg = "1";
		}
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msg);
	}
}
