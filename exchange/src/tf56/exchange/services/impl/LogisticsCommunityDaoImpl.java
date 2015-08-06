package tf56.exchange.services.impl;


import java.io.IOException;
import java.util.Map;
import net.sf.serfj.client.Client;
import net.sf.serfj.client.WebServiceException;
import tf56.exchange.services.LogisticsCommunityDao;
import tf56.sofa.http.client.HttpClient;
import tf56.sofa.util.ClientUtil;


public class LogisticsCommunityDaoImpl  implements LogisticsCommunityDao{

	
	/**
	 * @author tlp
	 * @funtion 验证会员接口
	 * @date 2013-03-12
	 */
	public String auditUser(Map map) {
		String json="";
		String url = "club56/logisticscommunitycs/auditUser";
		ClientUtil client = new ClientUtil(url);   			  
		try {
			
			//json= super.postRequest(url, map).toString();
			json = client.post(url, map).toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WebServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * @author tlp
	 * @funtion  密码重置接口
	 * @date 2013-03-12
	 */
	public String resetPassword(Map map) {
		// TODO Auto-generated method stub
		String json="";
		String url = "club56/logisticscommunitycs/resetPassword";	
		ClientUtil client = new ClientUtil(url);   				  
		try {
			
			//json= super.postRequest(url, map).toString();
			json = client.postRequest(url, map).toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WebServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * @author tlp
	 * @funtion  货源查询接口
	 * @date 2013-03-12
	 */
	public String queryGoods(Map map) {
		// TODO Auto-generated method stub
		String json="";
		String url = "club56/logisticscommunitycs/queryGoods";	
		ClientUtil client = new ClientUtil(url);   		

		try {
			
			//json= super.postRequest(url, map).toString();
			json = client.postRequest(url, map).toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WebServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * @author tlp
	 * @funtion  车辆信息查询接口
	 * @date 2013-03-12
	 */
	public String queryVehicle(Map map) {
		// TODO Auto-generated method stub
		String json="";
		String url = "club56/logisticscommunitycs/queryVehicle";	
		ClientUtil client = new ClientUtil(url);   					  
		try {
			
			//json= super.postRequest(url, map).toString();
			json = client.postRequest(url, map).toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WebServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * @author tlp
	 * @funtion  空车发布接口
	 * @date 2013-03-12
	 */
	public String publishVehicle(Map map) {
		// TODO Auto-generated method stub
		String json="";
		String url = "club56/logisticscommunitycs/publishVehicle";	
		ClientUtil client = new ClientUtil(url);   					  
		try {
			
			//json= super.postRequest(url, map).toString();
			json = client.postRequest(url, map).toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WebServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * @author tlp
	 * @funtion  交易配对推送接口
	 * @date 2013-03-12
	 */
	public String pushGoods(Map map) {
		// TODO Auto-generated method stub
		String json="";
		String url = "club56/logisticscommunitycs/pushGoods";	
		ClientUtil client = new ClientUtil(url);   					  
		try {
			
			//json= super.postRequest(url, map).toString();
			json = client.postRequest(url, map).toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WebServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * @author tlp
	 * @funtion  位置信息上传接口
	 * @date 2013-04-01
	 */
	public String updateLocation(Map map) {
		String json="";
		String url = "club56/logisticscommunitycs/updateLocation";	
		ClientUtil client = new ClientUtil(url);   		 			  
		try {
			
			//json= super.postRequest(url, map).toString();
			json = client.postRequest(url, map).toString();
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