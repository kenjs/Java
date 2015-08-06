package tf56.exchange.services.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;
import net.sf.serfj.client.Client;
import net.sf.serfj.client.WebServiceException;
import tf56.exchange.controllers.Topartyc;
import tf56.exchange.services.CarDao;
import tf56.exchange.services.ToPartyDao;
import tf56.sofa.http.client.HttpClient;
import tf56.sofa.json.Json2ObjectUtil;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.util.ClientUtil;
import tf56.sofa.util.MD5;
//import tf56.sofa.util.MD5;


public class CarDaoImpl  implements CarDao{

	
	public static Logger logger = Logger.getLogger(Topartyc.class);
	  /**
	   * @author leipeng.tang
	   * @function 修改在场车经，纬度,目前位置：省，市，区
	   * @date 2014-02-28
	   * @return String
	   * */
	@Override
	public String updateCarLocation(Map map) {
		String msg="";
		String url_lbsTrack="lbsTrack/regeocodecs/transferLngLatToAddress";
		String url_lbsSiteView="lbsSiteView/carlocationcs/updateCarLocation";
		try {
			ClientUtil lbsTrack = new ClientUtil(url_lbsTrack);
			ClientUtil lbsSiteView = new ClientUtil(url_lbsSiteView);
			msg= lbsTrack.post(url_lbsTrack,map).toString();
			//System.out.println("msg:"+msg);
			Map pmap=Json2ObjectUtil.parseJSON2Map(msg);
			map.put("province", pmap.get("province"));
			map.put("city", pmap.get("city"));
			map.put("region", pmap.get("region"));
			map.put("address", pmap.get("address"));
			//System.out.println("map:"+map);
			msg=lbsSiteView.post(url_lbsSiteView,map).toString();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonGenerateUtil.getMsgJson("调用第二层war包时操作失败！");
		}
		return msg;
	}
	  /**
	 * @author leipeng.tang
	 * @function 根据车牌，去向，司机，手机，当前位置，车长，吨位，诚信这些条件查询在场车
	 * @date 2014-02-28
	 * */
	@Override
	public String selectparkcarList(Map map) {
		String msg="";
		String url_lbsSiteView="lbsSiteView/carlocationcs/selectListCarLocation";
		try {
			ClientUtil lbsSiteView = new ClientUtil(url_lbsSiteView);
			msg=lbsSiteView.post(url_lbsSiteView,map).toString();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonGenerateUtil.getMsgJson("调用第二层war包时操作失败！");
		}
		return msg;
	}
	
}
