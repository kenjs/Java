package tf56.exchange.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.serfj.RestController;
import net.sf.serfj.annotations.GET;
import net.sf.serfj.annotations.POST;

import org.apache.log4j.Logger;

import tf56.exchange.services.CarDao;
import tf56.sofa.serializer.JsonResponse;
import tf56.sofa.util.SofaSpringContext;
import tf56.sofa.util.SysUtil;


public class Carc extends RestController{
	
	public static Logger logger = Logger.getLogger(Carc.class);
	
	  /**
	   * @author leipeng.tang
	   * @function 修改在场车经，纬度,目前位置：省，市，区
	   * @date 2014-02-28
	   * @return String
	   * */
	@GET
	public String updateCarLocation(){
		//logger.debug("调用party包中控制器");
		Map map = this.getParams();
	    map = SysUtil.removeFilter(map);//用系统工具包内的封装的方法删除过滤器（trade包中也要删除，否则无法实例化）	
	    //解决windows下面的编码问题，而linux不需要
		//map= SysUtil.getIsoDecodeMap(map);
	    CarDao carDao = (CarDao) SofaSpringContext.getBean("carService");
        String JSON=carDao.updateCarLocation(map);
	    HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, JSON);
	    
	}
	  /**
	 * @author leipeng.tang
	 * @function 根据车牌，去向，司机，手机，当前位置，车长，吨位，诚信这些条件查询在场车
	 * @date 2014-02-28
	 * */
	@POST
	public String selectparkcarList(){
		//logger.debug("调用party包中控制器");
		Map map = this.getParams();
	    map = SysUtil.removeFilter(map);//用系统工具包内的封装的方法删除过滤器（trade包中也要删除，否则无法实例化）	
	    //解决windows下面的编码问题，而linux不需要
		//map= SysUtil.getIsoDecodeMap(map);
	    CarDao carDao = (CarDao) SofaSpringContext.getBean("carService");
      String JSON=carDao.selectparkcarList(map);
	    HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, JSON);
	    
	}
	
}
