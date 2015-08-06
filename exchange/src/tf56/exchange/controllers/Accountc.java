package tf56.exchange.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.serfj.RestController;
import net.sf.serfj.annotations.GET;

import org.apache.log4j.Logger;

import tf56.exchange.services.AccountService;
import tf56.exchange.services.MjDriverPreferenService;
import tf56.sofa.serializer.JsonResponse;
import tf56.sofa.util.SofaSpringContext;
import tf56.sofa.util.SysUtil;

/**
 * @author tlp
 *@function 账户操作借口
 * @ 2014-04-01 上午09:30:43
 */
public class Accountc extends RestController{
	
	public static Logger logger = Logger.getLogger(Accountc.class);
	
	/**
	 * @author tlp
	 * @funciton 查询收单状态(业务号)()
	 * @date 2014-04-01
	 */
	@GET
	public String selectIdByBusinessNumber(){
		
		//logger.debug("调用party包中控制器");
		Map map = this.getParams();
		logger.debug("toparty map :"+map);
	    map = SysUtil.removeFilter(map);//用系统工具包内的封装的方法删除过滤器（trade包中也要删除，否则无法实例化）	
	    //解决windows下面的编码问题，而linux不需要
		//map= SysUtil.getIsoDecodeMap(map);
		logger.debug("toparty map 解码:"+map);
		AccountService accountService = (AccountService) SofaSpringContext.getBean("accountService");
        String JSON=accountService.selectIdByBusinessNumber(map);
	    logger.debug("toparty JSON:"+JSON);	    
	    HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, JSON);
	    
	}
	
	
}
