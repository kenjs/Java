package tf56.exchange.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.serfj.RestController;
import net.sf.serfj.annotations.GET;

import org.apache.log4j.Logger;

import tf56.exchange.services.ToPartyDao;
import tf56.sofa.serializer.JsonResponse;
import tf56.sofa.util.SofaSpringContext;
import tf56.sofa.util.SysUtil;


public class Topartyc extends RestController{
	
	public static Logger logger = Logger.getLogger(Topartyc.class);
	
	/**
	 * @author tlp
	 * @funciton 导入会员
	 * @date 2013-04-25
	 */
	@GET
	public String addparty(){
		
		//logger.debug("调用party包中控制器");
		Map map = this.getParams();
		logger.debug("toparty map :"+map);
	    map = SysUtil.removeFilter(map);//用系统工具包内的封装的方法删除过滤器（trade包中也要删除，否则无法实例化）	
	    //解决windows下面的编码问题，而linux不需要
		//map= SysUtil.getIsoDecodeMap(map);
		logger.debug("toparty map 解码:"+map);
	    ToPartyDao toPartyDao = (ToPartyDao) SofaSpringContext.getBean("toPartyDao");
        String JSON=toPartyDao.addParty(map);
	    logger.debug("toparty JSON:"+JSON);	    
	    HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, JSON);
	    
	}
	
	/**
	 * @author tlp
	 * @funciton 导入挂车
	 * @date 2013-04-25
	 */
	@GET
	public String addtrailer(){
		
		//logger.debug("调用party包中控制器");
		Map map = this.getParams();
	    map = SysUtil.removeFilter(map);//用系统工具包内的封装的方法删除过滤器（trade包中也要删除，否则无法实例化）	
	    //解决windows下面的编码问题，而linux不需要
		//map= SysUtil.getIsoDecodeMap(map);
	    logger.debug("toparty map:"+map);
	    ToPartyDao partyDao = (ToPartyDao) SofaSpringContext.getBean("toPartyDao");
        String JSON=partyDao.addTrailer(map);
	    logger.debug("toparty JSON:"+JSON);	    
	    HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, JSON);
	    
	}
	/**
	 * @author tlp
	 * @funciton 导入车辆
	 * @date 2013-04-25
	 */
	@GET
	public String addcar(){
		
		//logger.debug("调用party包中控制器");
		Map map = this.getParams();
	    map = SysUtil.removeFilter(map);//用系统工具包内的封装的方法删除过滤器（trade包中也要删除，否则无法实例化）	
	    //解决windows下面的编码问题，而linux不需要
		map= SysUtil.getIsoDecodeMap(map);
	    logger.debug("toparty map:"+map);
	    ToPartyDao partyDao = (ToPartyDao) SofaSpringContext.getBean("toPartyDao");
        String JSON=partyDao.addCar(map);
	    logger.debug("toparty JSON:"+JSON);	    
	    HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, JSON);
	    
	}
	/**
	 * @author tlp
	 * @funciton 导入会员个人信息
	 * @date 2013-04-25
	 */
	@GET
	public String addperson(){
		
		//logger.debug("调用party包中控制器");
		Map map = this.getParams();
	    map = SysUtil.removeFilter(map);//用系统工具包内的封装的方法删除过滤器（trade包中也要删除，否则无法实例化）	
	    //解决windows下面的编码问题，而linux不需要
	    //map= SysUtil.getIsoDecodeMap(map);
	    logger.debug("toparty map:"+map);
	    ToPartyDao partyDao = (ToPartyDao) SofaSpringContext.getBean("toPartyDao");
        String JSON=partyDao.addPerson(map);
	    logger.debug("toparty JSON:"+JSON);	    
	    HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, JSON);
	    
	}
	/**
	 * @author tlp
	 * @funciton 导入会员个人信息
	 * @date 2013-04-25
	 */
	@GET
	public String addoperatorparameter(){
		
		//logger.debug("调用party包中控制器");
		Map map = this.getParams();
	    map = SysUtil.removeFilter(map);//用系统工具包内的封装的方法删除过滤器（trade包中也要删除，否则无法实例化）	
	    //解决windows下面的编码问题，而linux不需要
		//map= SysUtil.getIsoDecodeMap(map);
	    logger.debug("toparty map:"+map);
	    ToPartyDao partyDao = (ToPartyDao) SofaSpringContext.getBean("toPartyDao");
        String JSON=partyDao.addoperatorparameter(map);
	    logger.debug("toparty JSON:"+JSON);	    
	    HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, JSON);
	    
	}
	/**
	 * @author tlp
	 * @funciton 导入会员图片
	 * @date 2013-04-25
	 */
	@GET
	public String addimage(){
		
		//logger.debug("调用party包中控制器");
		Map map = this.getParams();
	    map = SysUtil.removeFilter(map);//用系统工具包内的封装的方法删除过滤器（trade包中也要删除，否则无法实例化）	
	    //解决windows下面的编码问题，而linux不需要
		//map= SysUtil.getIsoDecodeMap(map);
	    logger.debug("toparty map:"+map);
	    ToPartyDao partyDao = (ToPartyDao) SofaSpringContext.getBean("toPartyDao");
        String JSON=partyDao.addimage(map);
	    logger.debug("toparty JSON:"+JSON);	    
	    HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, JSON);
	    
	}
	/**
	 * @author tlp
	 * @funciton 修改口令
	 * @date 2013-07-17
	 */
	@GET
	public String updatepassword(){
		
		//logger.debug("调用party包中控制器");
		Map map = this.getParams();
	    map = SysUtil.removeFilter(map);//用系统工具包内的封装的方法删除过滤器（trade包中也要删除，否则无法实例化）	
	    //解决windows下面的编码问题，而linux不需要
		//map= SysUtil.getIsoDecodeMap(map);
	    logger.debug("toparty map:"+map);
	    ToPartyDao partyDao = (ToPartyDao) SofaSpringContext.getBean("toPartyDao");
        String JSON=partyDao.updatepassword(map);
	    logger.debug("toparty JSON:"+JSON);	    
	    HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, JSON);
	    
	}
	/**
	 * @author tlp
	 * @funciton 修改realname
	 * @date 2013-08-29
	 */
	@GET
	public String updaterealname(){
		
		//logger.debug("调用party包中控制器");
		Map map = this.getParams();
	    map = SysUtil.removeFilter(map);//用系统工具包内的封装的方法删除过滤器（trade包中也要删除，否则无法实例化）	
	    //解决windows下面的编码问题，而linux不需要
		//map= SysUtil.getIsoDecodeMap(map);
	    ToPartyDao partyDao = (ToPartyDao) SofaSpringContext.getBean("toPartyDao");
        String JSON=partyDao.updaterealname(map);
	    HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, JSON);
	    
	}
}
