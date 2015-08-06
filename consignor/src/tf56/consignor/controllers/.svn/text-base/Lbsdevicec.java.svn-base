package tf56.consignor.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.serfj.RestController;
import net.sf.serfj.annotations.GET;
import net.sf.serfj.annotations.POST;
import tf56.sofa.serializer.JsonResponse;
import tf56.sofa.util.SofaSpringContext;
import tf56.sofa.util.SysUtil;
import tf56.consignor.services.LbsDeviceService;


public class Lbsdevicec extends RestController{
	/**
	 * @author ycj
	 * @date 2013-1-15
	 * @function 跳转至跟踪页面
	 */
	@GET
	public void lbsdeviceMap() {

	}
	/**
	 * @author ycj
	 * @date 2013-1-15
	 * @function 跳转至回放页面
	 */
	@GET
	public void carNumberMap(){
		
	}
	/**
	 * @author ycj
	 * @date 2013-1-15
	 * @function 轨迹跟踪最新数据，(deviceid设备号、partyid会员号)参数封装成Map，返回json数据  多条
	 */
	@POST
	public String selectLbsTrackByDeviceIdAndParyId() {
		Map paramsMap = this.getParams();
		paramsMap = SysUtil.removeFilter(paramsMap);
		LbsDeviceService lbsDeviceService = (LbsDeviceService) SofaSpringContext
				.getBean("lbsDeviceService");
		String msgJson = lbsDeviceService
				.selectLbsTrackByDeviceIdAndParyId(paramsMap); // 调用本外包services.impl的实现类
		// 返回json,格式:
		// 自定义
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson);
	}
	
	/**
	 * @author lj_jinrong.zhang
	 * @function 查询轨迹数据 参数为deviceids
	 * @data 2013.11.27
	 * */
	@POST
	public String selectLbsTrackListByIds() {
		String msgJson = "";
		HttpServletResponse response = null;
		try {
			Map paramsMap = this.getParams();
			paramsMap = SysUtil.removeFilter(paramsMap);
			LbsDeviceService lbsDeviceService = (LbsDeviceService) SofaSpringContext
			.getBean("lbsDeviceService");
			msgJson = lbsDeviceService.selectLbsTrackListByIds(paramsMap);
			response = this.getResponseHelper().getResponse();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new JsonResponse().responseJson(response, msgJson);
	}
	/**
	 *@author lj_jinrong.zhang
	 *@function 根据deviceids/partyids查询电子围栏
	 *@date 2014-01-09
	 **/
	@POST
	public String selectLbsFenceListByIds() {
		Map formMap = this.getParams();
		formMap = SysUtil.removeFilter(formMap);
		LbsDeviceService lbsDeviceService = (LbsDeviceService) SofaSpringContext
		.getBean("lbsDeviceService");
		String msgJson = lbsDeviceService.seletLbsFenceListByIds(formMap);
	
		HttpServletResponse response = this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msgJson); // 返回 Json格式:
	}
	
}
