package tf56.exchange.controllers;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import net.sf.serfj.RestController;
import net.sf.serfj.annotations.POST;
import org.apache.log4j.Logger;
import tf56.exchange.services.ContractService;
import tf56.sofa.json.Json2ObjectUtil;
import tf56.sofa.serializer.JsonResponse;
import tf56.sofa.util.SofaSpringContext;
import tf56.sofa.util.SysUtil;

/**
 * 
 * 总包接口类
 * @athor changmeng.liu
 * @date 2014-4-16
 * @version 1.0
 * @update
 */
public class Contractc extends RestController{
	
	public static Logger logger = Logger.getLogger(Contractc.class);
	/**
	 * 
	 * @athor changmeng.liu
	 * @date 2014-4-16
	 * @version 1.0
	 * @function 接收外部订单XML 
	 * @param 
	 * @update
	 */
	 @POST
	 public void receiveXml(){
		   Map map = this.getParams();
		   map = SysUtil.removeFilter(map);//用系统工具包内的封装的方法删除过滤器（trade包中也要删除，否则无法实例化）	
		   ContractService contractService=(ContractService) SofaSpringContext.getBean("contractService");
		   Map inputMap=new HashMap();
		   inputMap.put("xmlDoc", transMapToString(map));
		   String msg=contractService.sendOderXml(inputMap);
		   Map outMap=Json2ObjectUtil.parseJSON2Map(msg);
		   String outXml= outMap.get("msg").toString();
		   HttpServletResponse response=this.getResponseHelper().getResponse();
		   contractService.outputXml(outXml, response);	
	 }
	
	 /** 
	  * 方法名称:transMapToString 
	  * 传入参数:map 
	  * 返回值:String 形如 username'chenziwen^password'1234 
	 */  
	 public static String transMapToString(Map map){  
	   java.util.Map.Entry entry;  
	   StringBuffer sb = new StringBuffer();  
	   for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext();)  
	   {  
	     entry = (java.util.Map.Entry)iterator.next();  
	       sb.append(entry.getKey().toString()).append( "=" ).append(null==entry.getValue()?"":  
	       entry.getValue().toString()).append (iterator.hasNext() ? "^" : "");  
	   }  
	   return sb.toString();  
	 }  

	 /**
	  * @author haoyong
	 * @throws Exception 
	  * @date 2014-4-17
	  * @description 发送配载xml到化工接口
	  */
	 @POST
	 public String exchangeXmlBetweenScmAndRyp() throws Exception {
		 Map map = SysUtil.removeFilter(getParams());
		 HttpServletResponse response = this.getResponseHelper().getResponse();
		 ContractService contractService = (ContractService) SofaSpringContext.getBean("contractService");
		 String msg = contractService.sendXml2Ryp(map);
		 return new JsonResponse().responseJson(response, msg);
	 }
	 
}
