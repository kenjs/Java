package tf56.contract.services.impl;
import java.io.IOException;
import java.util.Map;

import net.sf.serfj.client.WebServiceException;
import tf56.contract.services.AreaCityService;
import tf56.sofa.util.ClientUtil;

public class AreaCityServiceImpl implements AreaCityService{
	
	
	
	/**
	 * @author tlp
	 * @funtion 登陆
	 * @return String
	 * @date 2012-12-21
	 * @throws IOException
	 */
	public String selectAreaCityList(Map map) {
		String json="";
		String url = "party/areacitycs/selectAreaCityList";	
		ClientUtil cu=new ClientUtil(url);
		try {
			
			json= cu.post(url, map).toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WebServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
 
}


