package tf56.exchange.services.impl;

import java.util.Map;

import org.apache.log4j.Logger;

import tf56.exchange.controllers.Topartyc;
import tf56.exchange.services.AccountService;
import tf56.exchange.services.MjDriverPreferenService;
import tf56.sofa.http.client.HttpClient;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.util.ClientUtil;
//import tf56.sofa.util.MD5;


public class AccountServiceImpl  implements AccountService{

	
	public static Logger logger = Logger.getLogger(Topartyc.class);
	/**
	 * @author tlp
	 * @fuction 基地优惠信息
	 * @data 2013-11-06
	 */
	public String selectIdByBusinessNumber(Map map) {
		String msg="";
		String url_account="account/accountdetailquerycs/selectIdByBusinessNumber";
		try {
			ClientUtil card = new ClientUtil(url_account);

			msg= card.post(url_account,map).toString();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonGenerateUtil.getMsgJson("调用第二层war包时操作失败！");
		}
		
		return msg;
	}


}
