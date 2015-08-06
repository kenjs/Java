package tf56.exchange.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.serfj.RestController;
import net.sf.serfj.annotations.GET;
import net.sf.serfj.annotations.POST;

import tf56.exchange.services.ElectronichubDao;
import tf56.exchange.services.MesInterfaceDao;
import tf56.sofa.serializer.JsonResponse;
import tf56.sofa.util.SofaSpringContext;
import tf56.sofa.util.SysUtil;

public class Messageinterfacec extends RestController{
	
	
	/**
	 * @author tlp
	 * @funtion 短信发送---将需要发送的短信写入数据库
	 * @return String
	 * @date  2013-02-01
	 * @throws IOException
	 */
	@GET
	public String writeMessage_doGet()throws IOException{
		String json="ok";
		Map  map=this.getParams();		    
		map = SysUtil.removeFilter(map);	    
		//map= SysUtil.getIsoDecodeMap(map);
		MesInterfaceDao mesInterfaceDao = (MesInterfaceDao) SofaSpringContext.getBean("mesInterfaceDao");//得到Dao bean
		json=mesInterfaceDao.writeMessage(map);
		HttpServletResponse response = this.getResponseHelper().getResponse();	
		response.setContentType("application/json;charset=UTF-8");
		return new JsonResponse().responseJson(response, json);
		//response.getWriter().write(json);
		/*PrintWriter	pw = response.getWriter();
		json="["+json+"]";
		pw.write(json);	
		pw.flush();
		pw.close();
		return null;*/
	}
	/**
	 * @author tlp
	 * @funtion 短信发送---将需要发送的短信写入数据库
	 * @return String
	 * @date  2013-07-09
	 * @throws IOException
	 */
	@POST
	public String writeMessage_doPost()throws IOException{
		String json="ok";
		Map  map=this.getParams();		    
		map = SysUtil.removeFilter(map);	    
		//map= SysUtil.getIsoDecodeMap(map);
		MesInterfaceDao mesInterfaceDao = (MesInterfaceDao) SofaSpringContext.getBean("mesInterfaceDao");//得到Dao bean
		json=mesInterfaceDao.writeMessage(map);
		//json=JsonGenerateUtil.getMsgJson(json);
		HttpServletResponse response = this.getResponseHelper().getResponse();	
		response.setContentType("application/json;charset=UTF-8");
		return new JsonResponse().responseJson(response, json);
		//response.getWriter().write(json);
		/*PrintWriter	pw = response.getWriter();
		json="["+json+"]";
		pw.write(json);	
		pw.flush();
		pw.close();
		return null;*/
	}
	
}
