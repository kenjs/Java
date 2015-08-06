package tf56.exchange.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.serfj.RestController;
import net.sf.serfj.annotations.GET;
import net.sf.serfj.annotations.POST;

import tf56.exchange.services.InvoiceDao;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.exchange.services.InvoiceDao;
import tf56.sofa.serializer.JsonResponse;
import tf56.sofa.util.SofaSpringContext;
import tf56.sofa.util.SysUtil;

public class Invoicec extends RestController{
	/**
	 * @author tlp
	 * @funtion 上传发票--类型为XML
	 * @return String
	 * @date  2013-01-05
	 * @throws IOException
	 */
	@GET
	public String uploadInvoice()throws IOException{
		String json="";
		Map  map=this.getParams();	
		map = SysUtil.removeFilter(map);	    
		map= SysUtil.getIsoDecodeMap(map);
		InvoiceDao invoiceDao = (InvoiceDao) SofaSpringContext.getBean("invoiceDao");//得到Dao bean
		json=invoiceDao.uploadInvoice(map);
		HttpServletResponse response = this.getResponseHelper().getResponse();
		response.setContentType("application/json;charset=UTF-8");
		//response.getWriter().write(json);
		PrintWriter	pw = response.getWriter();
		json="["+json+"]";
		pw.write(json);	
		pw.flush();
		pw.close();
		return null;//new JsonResponse().responseJson(response, json);	
	
	}
	/**
	 * @author tlp
	 * @funtion 发票作废--类型为XML
	 * @return String
	 * @date  2013-01-05
	 * @throws IOException
	 */
	@GET
	public String invalidInvoice()throws IOException{
		String json="";
		Map  map=this.getParams();		    
		map = SysUtil.removeFilter(map);	    
		map= SysUtil.getIsoDecodeMap(map);
		//System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBB:"+map);
		InvoiceDao invoiceDao = (InvoiceDao) SofaSpringContext.getBean("invoiceDao");//得到Dao bean
		json=invoiceDao.invalidInvoice(map);
		//json=JsonGenerateUtil.getMsgJson(json);
		HttpServletResponse response = this.getResponseHelper().getResponse();	
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter	pw = response.getWriter();
		json="["+json+"]";
		pw.write(json);	
		pw.flush();
		pw.close();
		return null;//new JsonResponse().responseJson(response, json);		
	}	
}
