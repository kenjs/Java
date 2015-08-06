package tf56.exchange.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import tf56.exchange.controllers.Topartyc;
import tf56.exchange.services.BillService;
import tf56.sofa.http.client.HttpClient;
import tf56.sofa.json.Json2ObjectUtil;
import tf56.sofa.json.Json2ObjectUtilNew;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.util.ClientUtil;
//import tf56.sofa.util.MD5;


public class BillServiceImpl  implements BillService{

	public static Logger logger = Logger.getLogger(Topartyc.class);
	
	/**
	 * @author tlp
	 * @funciton 新增一卡通收单记录
	 * @date 2014-03-27
	 */
	@Override
	public String insertAwaitBill(Map map) {
		String msg="";
		String url_bill="bill/awaitbillcs/insertAwaitBill";
		try {
			ClientUtil bill = new ClientUtil(url_bill);
			//System.out.println("url:"+card.toString());
			msg= bill.post(url_bill,map).toString();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonGenerateUtil.getMsgJson("调用第二层war包时操作失败！");
		}
		
		return msg;
	}

	/**
	 * @author wjj
	 * @funciton 根据身份证新增收单记录
	 * @date 2014-04-02
	 */
	@Override
	public String insertBillByCertificate(Map map) {
		String resultmsg="";
		String url_party="party/partyaccountcs/selectByCertificateNumber";
		String url_bill="bill/billcs/insertBill";
		try {
			ClientUtil party = new ClientUtil(url_party);
			String partymsg = party.post(url_party,map).toString();
			if(partymsg != null){
				Map partymap = Json2ObjectUtil.parseJSON2Map(partymsg);
				if(partymap.get("cardnumber") == null || partymap.get("cardnumber").equals("")){
					return JsonGenerateUtil.getMsgJson("该用户未办理卡！");
				}
				
				map.put("frompartyid", partymap.get("partyid") == null ? "" : partymap.get("partyid"));
				map.put("fromaccountnumber", partymap.get("accountnumber") == null ? "" : partymap.get("accountnumber"));
				map.put("fromrealname", partymap.get("realname") == null ? "" : partymap.get("realname"));
				map.put("fromcardnumber", partymap.get("cardnumber") == null ? "" : partymap.get("cardnumber"));
				//新增一条待收单记录
				ClientUtil bill = new ClientUtil(url_bill);
				resultmsg= bill.post(url_bill,map).toString();
				
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return JsonGenerateUtil.getMsgJson("调用第二层war包时操作失败！");
		}
		
		return resultmsg;
	}
}
