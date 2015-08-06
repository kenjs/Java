package tf56.exchange.controllers;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.serfj.RestController;
import net.sf.serfj.annotations.GET;
import net.sf.serfj.annotations.POST;

import tf56.exchange.services.ElectronichubDao;
import tf56.exchange.services.InvoiceDao;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.serializer.JsonResponse;
import tf56.sofa.util.SofaSpringContext;
import tf56.sofa.util.SysUtil;

public class Electronichubc extends RestController{
	/**
	 * @author tlp
	 * @funtion 点子枢纽上传专线数据--类型为XML
	 * @return String
	 * @date  2013-01-05
	 * @throws IOException
	 */
	@GET
	public String uploadLineXML()throws IOException{
		String json="ok";
		Map  map=this.getParams();		    
		map = SysUtil.removeFilter(map);	    
		//map= SysUtil.getIsoDecodeMap(map);
		ElectronichubDao electronichubDao = (ElectronichubDao) SofaSpringContext.getBean("electronichubDao");//得到Dao bean
		json=electronichubDao.uploadLineXML(map);
		//json=JsonGenerateUtil.getMsgJson(json);
		HttpServletResponse response = this.getResponseHelper().getResponse();	
		return new JsonResponse().responseJson(response, json);	
	}
	/**
	 * @author tlp
	 * @funtion 点子枢纽上传货主数据--类型为XML
	 * @return String
	 * @date  2013-01-24
	 * @throws IOException
	 */
	/*@GET
	public String uploadCarrierXML()throws IOException{
		String json="ok";
		Map  map=this.getParams();		    
		map = SysUtil.removeFilter(map);	    
		//map= SysUtil.getIsoDecodeMap(map);
		ElectronichubDao electronichubDao = (ElectronichubDao) SofaSpringContext.getBean("electronichubDao");//得到Dao bean
		json=electronichubDao.uploadCarrierXML(map);
		//json=JsonGenerateUtil.getMsgJson(json);
		HttpServletResponse response = this.getResponseHelper().getResponse();	
		return new JsonResponse().responseJson(response, json);	
	}*/
}
