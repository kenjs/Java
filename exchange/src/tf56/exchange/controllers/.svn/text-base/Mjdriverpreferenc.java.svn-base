package tf56.exchange.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.serfj.RestController;
import net.sf.serfj.annotations.GET;

import org.apache.log4j.Logger;

import tf56.exchange.services.MjDriverPreferenService;
import tf56.exchange.services.ToPartyDao;
import tf56.sofa.serializer.JsonResponse;
import tf56.sofa.util.SofaSpringContext;
import tf56.sofa.util.SysUtil;

/**
 * @author tlp
 *@function 类Mjdriverpreferenc.java的实现描述：一卡通项目接口 
 * @ 2014-3-27 下午03:57:43
 */
public class Mjdriverpreferenc extends RestController{
	
	public static Logger logger = Logger.getLogger(Mjdriverpreferenc.class);
	
	/**
	 * @author tlp
	 * @funciton 门禁优惠信息
	 * @date 2014-01-03
	 */
	@GET
	public String addMjDriverPreferen(){
		
		//logger.debug("调用party包中控制器");
		Map map = this.getParams();
		logger.debug("toparty map :"+map);
	    map = SysUtil.removeFilter(map);//用系统工具包内的封装的方法删除过滤器（trade包中也要删除，否则无法实例化）	
	    //解决windows下面的编码问题，而linux不需要
		//map= SysUtil.getIsoDecodeMap(map);
		logger.debug("toparty map 解码:"+map);
	    MjDriverPreferenService  driverPreferenService = (MjDriverPreferenService) SofaSpringContext.getBean("mjDriverPreferenService");
        String JSON=driverPreferenService.addMjDriverPreferen(map);
	    logger.debug("toparty JSON:"+JSON);	    
	    HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, JSON);
	    
	}
	
}
