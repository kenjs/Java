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
import tf56.exchange.services.ToPartyDao;
import tf56.sofa.http.client.HttpClient;
import tf56.sofa.json.Json2ObjectUtil;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.util.ClientUtil;
import tf56.sofa.util.MD5;
//import tf56.sofa.util.MD5;


public class ToPartyDaoImpl  implements ToPartyDao{

	
	public static Logger logger = Logger.getLogger(Topartyc.class);
	/**
	 * @author tlp
	 * @fuction 从老系统导入会员
	 * @data 2013-11-06
	 */
	public String addParty(Map map) {
		String msg="";
		String url_party="party/partycs/addNewParty";
		String url_account="account/accountcs/addNewParty";
		try {
			ClientUtil party = new ClientUtil(url_party);
			if(null==map.get("partyname")||"".equals(map.get("partyname"))){
				return JsonGenerateUtil.getMsgJson("会员名不能为空:partyname="+map.get("partyname"));
			}
			msg= party.post(url_party,map).toString();
			//System.out.println("msg1:"+msg);
			Map resolveMap=Json2ObjectUtil.parseJSON2Map(msg);
			if("ok".equals(resolveMap.get("msg"))){
				ClientUtil account = new ClientUtil(url_account);
				map.put("partyid", resolveMap.get("partyid"));
				map.put("accountnumber", resolveMap.get("accountnumber"));
				msg=account.post(url_account,map).toString();
				//System.out.println("msg2:"+msg+",map:"+map);
			}else {
				return msg;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonGenerateUtil.getMsgJson("调用第二层war包时操作失败！");
		}
		
		return msg;
	}

//	public String addParty(Map map) {
//		String msg="";
//
//		// TODO Auto-generated method stub
//		String urlS="account/scorecs/insert";//往account中的积分余额表中插入数据
//		String urlA="account/accountcs/insert";//account中的帐户余额表中插入数据
//		String url_rent="rent/sitebasecs/insert";//在rent中插入客户账户资料
//		String url_party="party/partycs/transactAddParty";	
//		String url_account="account/accountcs/insertAccountInfo";
//		String URl_party_update="party/partycs/transactUpdateParty";
//		String url_Party_validate="party/partycs/validatePartyName";
//
//		ClientUtil client_rent =new ClientUtil(url_rent);
//		ClientUtil client_party =new ClientUtil(url_party);
//		ClientUtil client_account =new ClientUtil(url_account);
//		try {
//				//判断该会员是否存在
//				Map m=new HashMap();
//				m.put("partyname", map.get("partyname"));
//				msg=(String) client_party.post(url_Party_validate, m);
//				m=Json2ObjectUtil.parseJSON2Map(msg);
//				if("no".equals(m.get("msg"))){
//					map.put("operator", "admin");
//					map.put("tradetype", "3PL");
//					map.put("password", MD5.md5("123456"));
//					map.put("accounttype", "主账户");
//					msg=(String) client_party.post(url_party, map);
//					Map rmap=Json2ObjectUtil.parseJSON2Map(msg);
//					if(null!=rmap.get("partyid")){
//						rmap.put("amount", "0"); 
//						rmap.put("usableamount", "0");
//						rmap.put("freezeamount", "0");
//						msg=client_account.post(urlS, rmap).toString(); //调用account中的积分表
//						msg=client_account.post(urlA, rmap).toString();//account中的帐户余额表
//						map.put("partyid", rmap.get("partyid"));
//						msg= (String)client_rent.post(url_rent,map);
//						Map	amap=Json2ObjectUtil.parseJSON2Map(msg);
//						if("ok".equals(amap.get("msg"))){
//							return JsonGenerateUtil.getMsgJson("ok");
//						}else {
//							return JsonGenerateUtil.getMsgJson("新增基地客户失败!");
//						}
//						}else{
//							return JsonGenerateUtil.getMsgJson("插入会员账户失败!");
//						}
//					}else {
//						return JsonGenerateUtil.getMsgJson("插入会员失败!");
//					}
//				}
//		}catch(Exception e){
//			
//		}
//	}	
	/**
	 * @author tlp
	 * @fuction 从老系统导入会员个人参数
	 * @data 2013-04-26
	 */
	public String addPerson(Map map) {
		String url_party_queryID ="party/partycs/getPartyIdByPartyName";//根据用户名称，查询ID
		String url_person_insert ="party/personcs/insert_all";//插入个人信息
		String url_person_update ="party/personcs/update";//更新个人信息
		String url_person_queryByPartyId="party/personcs/queryByPartyId";//根据会员ID查询个人信息
		String json="ok";  			
		ClientUtil party = new ClientUtil(url_person_insert);
		try {
			// 判断该会员是否存在
			String partyId = party.post(url_party_queryID,map).toString();
			if("".equals(partyId)){
				json=JsonGenerateUtil.getMsgJson("无该会员信息:partyname="+map.get("partyname"));
			}else{
				map.put("partyid", partyId);
				//查询该会员是否有个人信息
				String person=party.post(url_person_queryByPartyId,map).toString();
				//根据查询结果判断是否有该会员的个人信息
				if("".equals(person)){
					//如果没有,插入个人信息 成功返回MsgIdJson("ok", personid) 失败返回""
					String p=party.post(url_person_insert,map).toString();
					if("".equals(p)){
						return JsonGenerateUtil.getMsgJson("增加会员个人信息出错:partyname="+map.get("partyname"));
					}
					return p;
				}else{
					//如果有，则更新	成功返回OK 失败返回""
					String p=party.post(url_person_update,map).toString();
					if("".equals(p)){
						return JsonGenerateUtil.getMsgJson("更新会员个人信息出错:partyname="+map.get("partyname")); 
					}
					return JsonGenerateUtil.getMsgJson(p);
				}
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WebServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  json;
		
	}
	
	/**
	 * @author tlp
	 * @fuction 从老系统导入车辆参数
	 * @data 2013-04-26
	 */
	public String addCar(Map map){
		String url_car="party/carcs/addCar";
		String json="";
		ClientUtil party = new ClientUtil(url_car);
		try {
			 json = party.post(url_car,map).toString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			json=JsonGenerateUtil.getMsgJson("调用party包出错");
		}
		return json;
	}
//	public String addCar(Map map) {
//		String url_insert="party/carcs/insert";//插入车辆信息
//		String url_insertPartyCar="party/partycarcs/insert";//插入会员车辆关联表
//		String url_queryCar="party/carcs/selectCarByCarName";//根据车牌号查询车辆
//		String url_update="party/carcs/update";//根据车牌号更新车辆信息
//		String url_queryTrailer="party/trailercs/queryTrailerByTrailerPlateNumber";//根据挂车号查询挂车
//		String url_party_queryID ="party/partycs/getPartyIdByPartyName";//根据用户名称，查询ID
//		String json="ok";  	//selectCarByCarName
//		//return JsonGenerateUtil.map2json(map);
//		try {
//			//System.out.println("toPartyDaoImpl map:"+map);
//			//根据车牌号查询车辆车辆 错误返回error,有返回car所有信息,无返回no
//			String car = party.post(url_queryCar,map).toString();
//			//System.out.println("toPartyDaoImpl car:"+car);
//			 if("error".equals(car)){
//					return JsonGenerateUtil.getMsgJson("根据车牌号查询车辆出错:carplatenumber="+map.get("carplatenumber"));
//				}
//			
//			//找到会员ID 错误返回error,有返回id,无返回""
//			 String  partyid=party.post(url_party_queryID,map).toString();
//			 if("error".equals(partyid)){
//					return JsonGenerateUtil.getMsgJson("根据会员名查找会员ID出错:partyname="+map.get("partyname"));
//				}
//			 //System.out.println("partyid:"+partyid);
//			 map.put("partyid", partyid);
//			 //查挂车，如果有挂车号，那么将挂车查询出来放入map中   错误返回error,有返回trailer信息,无返回no
//			 //System.out.println("判断传入的参数是否为NULL:"+map.get("trailerplatenumber")==null);
//			 //System.out.println("判断传入的参数是否为空:"+"".equals(map.get("trailerplatenumber")));
//			 if(!"".equals(map.get("trailerplatenumber"))){
//					String trailer=party.post(url_queryTrailer,map).toString();
//					 //System.out.println("toPartyDaoImpl trailer:"+trailer);
//					if("error".equals(trailer)){
//						return JsonGenerateUtil.getMsgJson("根据挂车号查找挂车出错:trailerplatenumber="+map.get("trailerplatenumber"));
//					}
//					//判断该挂车是否存在
//					if("no".equals(trailer)){
//						return JsonGenerateUtil.getMsgJson("该挂车不存在:trailerplatenumber="+map.get("trailerplatenumber"));
//					}
//					//如果有挂车，那么填入挂车ID
//					Map trailerMap=Json2ObjectUtil.parseJSON2Map(trailer);
//					map.put("trailerid", trailerMap.get("trailerid"));
//				}
//			
//			//判断该车辆是否存在
//			if("no".equals(car)){
//					//无该车辆信息
//					//查询是否有挂车
//				
//					//获取车辆增加后的返回值  错误返回error,成功返回MsgIdJson("ok",carId)
//				   String cars=party.post(url_insert,map).toString();
//				   if("error".equals(cars)){
//						return JsonGenerateUtil.getMsgJson("插入车辆信息出错:carplatenumber="+map.get("carplatenumber"));
//					}
//				   //将返回值解析为Map
//				   Map partyCarMap=Json2ObjectUtil.parseJSON2Map(cars);
//				   //如果不为空
//				   if(!"".equals(partyid)){
//					 //插入车辆会员关联表
//					   partyCarMap.put("partyid", partyid);
//					   partyCarMap.put("carid", partyCarMap.get("id"));
//					   partyCarMap.put("relationship", map.get("relationship"));
//					   json=party.post(url_insertPartyCar,partyCarMap).toString();
//						if("error".equals(json)){
//							return JsonGenerateUtil.getMsgJson("插入会员车辆关系表出错:carid:"+partyCarMap.get("carid")+",partyid:"+partyid);
//						}
//				   }
//			}else{
//				 //有该车牌号信息，更新车辆信息
//				 Map carMap=Json2ObjectUtil.parseJSON2Map(car);
//				 //System.out.println("toPartyDaoImpl carMap:"+carMap);
//				 //放入车辆ID
//				 map.put("carid", carMap.get("carid"));
//				 //System.out.println("service map:"+map);
//				 //更新车辆信息    错误返回error,成功返回MsgIdJson("ok",""),失败返回MsgIdJson("更新失败","")
//				 json=party.post(url_update,map).toString();
//				 //System.out.println("AAAAAAAAAAAAAAAAAAAAAA:更新成功！");
//				 if("error".equals(json)){
//					 return JsonGenerateUtil.getMsgJson("更新会员车辆出错:carplatenumber="+map.get("carplatenumber"));
//				 }
//			}
//			//System.out.println("json:"+json);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (WebServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return json;
//	}

	/**
	 * @author tlp
	 * @fuction 从老系统导入挂车
	 * @data 2013-04-26
	 */
	public String addTrailer(Map map) {
		// TODO Auto-generated method stub
		
		String url_insert="party/trailercs/insert";//往挂车表中插入一条挂车记录挂车
		String url_query="party/trailercs/queryTrailerByTrailerPlateNumber";//根据挂车号查询挂车
		String url_update="party/trailercs/update";//根据挂车号更新挂车
		String json="";  	
		ClientUtil party = new ClientUtil(url_insert);
		try {
			//根据挂车号查询挂车 错误返回error,有返回trailer信息,无返回no
			String trailer = party.post(url_query,map).toString();
			if("error".equals(trailer)){
				return JsonGenerateUtil.getMsgJson("查询挂车出错:trailerplatenumber="+map.get("trailerplatenumber"));
			}
			//判断该挂车是否存在
			if("no".equals(trailer)){
				//插入操作  成功返回MsgIdJson("ok"，trailerID),错误返回error
				 json=party.post(url_insert,map).toString();
				 if("error".equals(json)){
					 return JsonGenerateUtil.getMsgJson("插入挂车出错:trailerplatenumber="+map.get("trailerplatenumber"));
				 }
			}else{
				//更新操作 成功返回MsgIdJson("ok"，trailerID),错误返回error，失败返回MsgIdJson("更新失败"，trailerID)
				Map paramap=Json2ObjectUtil.parseJSON2Map(trailer);
				map.put("trailerid", paramap.get("trailerid"));
				 json=party.post(url_update,map).toString();
				 if("error".equals(json)){
					 return JsonGenerateUtil.getMsgJson("更新挂车出错:trailerplatenumber="+map.get("trailerplatenumber"));
				 }
			}
			//System.out.println("json:"+json);
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
	 * @fuction 从老系统导入3PL参数
	 * @data 2013-05-09
	 */
	public String addoperatorparameter(Map map) {
		// TODO Auto-generated method stub
		String json="";
		String url_party_queryID ="party/partycs/getPartyIdByPartyName";//根据用户名称，查询ID
		String url_queryOperatorparameterById ="party/operatorparametercs/queryOperatorparameterByPartyIdAndOperator";//根据会员ID和操作员查询交易参数
		String url_operatorparameter_inset ="party/operatorparametercs/insert";//根据ID查询交易参数
		String url_operatorparameter_update ="party/operatorparametercs/update";//根据ID查询交易参数
		ClientUtil party = new ClientUtil(url_operatorparameter_inset);
		try {
			// 判断该会员是否存在
			String partyId = party.post(url_party_queryID,map).toString();
			if("".equals(partyId)){
				json=JsonGenerateUtil.getMsgJson("无该会员信息:partyname="+map.get("partyname"));
			}else{
				map.put("partyid", partyId);
				String peratorparameter=party.post(url_queryOperatorparameterById,map).toString();//根据会员ID和操作员查询交易参数
				if("error".equals(peratorparameter)){
					return JsonGenerateUtil.getMsgJson("查询操作员参数错误:partyname="+map.get("partyname,"+map.get("operator")));
				}
				if("no".equals(peratorparameter)){
					//插入
					json=party.post(url_operatorparameter_inset,map).toString();
					if("error".equals(json)){
						return JsonGenerateUtil.getMsgJson("插入操作员参数错误:partyname="+map.get("partyname"));
					}
				}else {
					//更新
					json=party.post(url_operatorparameter_update,map).toString();
					if("error".equals(json)){
						return JsonGenerateUtil.getMsgJson("更新操作员参数错误:partyname="+map.get("partyname"));
					}
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return json;
	}
	/**
	 * @author tlp
	 * @fuction 从老系统导入会员图片
	 * @data 2013-05-10
	 */
	public String addimage(Map map) {
		String json="";
		
		String url_queryCar="party/carcs/selectCarByCarName";//根据车牌号查询车辆
		String url_queryTrailer="party/trailercs/queryTrailerByTrailerPlateNumber";//根据挂车号查询挂车
		String url_party_queryID ="party/partycs/getPartyIdByPartyName";//根据用户名称，查询ID
		String url_partyimage_insert="party/partyimagecs/insert";//插入会员图片
		String url_partyimage_validatePartyImage="party/partyimagecs/validatePartyImage";//验证该会员是否有图片
		ClientUtil party = new ClientUtil(url_party_queryID);
		try {
			// 根据会员名查询会员ID
			String partyId = party.post(url_party_queryID,map).toString();
			if("".equals(partyId)){
				json=JsonGenerateUtil.getMsgJson("无该会员信息:partyname="+map.get("partyname"));
			}else{
				map.put("partyid", partyId);
				if("Party".equals(map.get("tablename"))){
					map.put("id", partyId);
				}
				if("Car".equals(map.get("tablename"))){
					//根据车牌号查询车辆ID
					 String car=party.post(url_queryCar,map).toString();
					 if("error".equals(car)){
							return JsonGenerateUtil.getMsgJson("查询车辆ID出错:partyname="+map.get("partyname")+",carplatenumber="+map.get("carplatenumber"));
						}
						if("no".equals(car)){
							return JsonGenerateUtil.getMsgJson("该车辆ID不存在:partyname="+map.get("partyname")+",carplatenumber="+map.get("carplatenumber"));
						}
						Map trailerMap=Json2ObjectUtil.parseJSON2Map(car);
						map.put("id", trailerMap.get("carid"));
				}
				if("Trailer".equals(map.get("tablename"))){
					//根据挂车号查询挂车ID
					String trailer=party.post(url_queryTrailer,map).toString();
					if("error".equals(trailer)){
						return JsonGenerateUtil.getMsgJson("查询挂车出错:partyname="+map.get("partyname")+",trailerplatenumber="+map.get("trailerplatenumber"));
					}
					if("no".equals(trailer)){
						return JsonGenerateUtil.getMsgJson("该挂车不存在:partyname="+map.get("partyname")+",trailerplatenumber="+map.get("trailerplatenumber"));
					}
					Map trailerMap=Json2ObjectUtil.parseJSON2Map(trailer);
					map.put("id", trailerMap.get("trailerid"));
					
				}
				//判断该会员是否已经插入会员图片
				String validate = party.post(url_partyimage_validatePartyImage,map).toString();
				System.out.println("topartydaoimapl validate:"+validate);
				if("error".equals(validate)){
					return JsonGenerateUtil.getMsgJson("验证会员图片出错:partyname="+map.get("partyname"));
				}
				//如果没有则插入会员图片
				if("no".equals(validate)){
					json = party.post(url_partyimage_insert,map).toString();
					if("error".equals(json)){
						return JsonGenerateUtil.getMsgJson("插入会员图片出错:partyname="+map.get("partyname"));
					}
				}
				if("ok".equals(validate)){
					return JsonGenerateUtil.getMsgJson("该图片已经写入");
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return json;
	}
	/*//帮助类   hkf
	private String getAccountNumber(String partyid){
		
		String defaultAaacNum = "800000000000";
		String retstr = "";
		retstr = defaultAaacNum.substring(0, defaultAaacNum.length()-partyid.length());
		retstr = retstr+partyid;
		return retstr;
	}*/
/*
	public static void main(String[] args) throws UnsupportedEncodingException {
		String s="ä¸ªäºº";
		System.out.println(new String(s.getBytes("iso-8859-1"),"UTF-8"));
		System.out.println(MD5.md5("123456"));
	}*/


/**
 * @author tlp
 * @fuction 修改密码
 * @data 2013-07-17
 */
	public String updatepassword(Map map) {
		// TODO Auto-generated method stub
		String json="";
		String url_party_queryID ="party/partycs/getPartyIdByPartyName";//根据用户名称，查询ID
		String url_checkoperator="party/operatorcs/updateoperatorByPartyIdAndOperator" ;
		ClientUtil party = new ClientUtil(url_party_queryID);
		// 根据会员名查询会员ID
		try {
			String partyId = party.post(url_party_queryID,map).toString();
			logger.debug("partyid:"+partyId);
			if("".equals(partyId)){
				json=JsonGenerateUtil.getMsgJson("该会员:"+map.get("partyname")+"不存在！");
			}else{
				map.put("partyid", partyId);
				//修改该角色的密码
				String check=party.post(url_checkoperator,map).toString();
				logger.debug("check:"+check);
				if("ok".equals(check)){
					json=JsonGenerateUtil.getMsgJson("ok");
				}else if("".equals(check)){
					json=JsonGenerateUtil.getMsgJson("该角色"+map.get("operator")+"不存在！");
				} else if("error".equals(check)){
					json=JsonGenerateUtil.getMsgJson("用户:"+map.get("partyname")+"修改密码失败");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			json=JsonGenerateUtil.getMsgJson(e.getMessage());
		}
		return json;
	}

	/**
	 * @author tlp
	 * @funciton 修改realname
	 * @date 2013-08-29
	 */
public String updaterealname(Map map) {
	String json="";
	String url_party_queryID ="party/partycs/getPartyIdByPartyName";//根据用户名称，查询ID
	String url_operator="party/operatorcs/transactionupdateRealname";//跟新operator表中的realname字段
	//String url_person="party/personcs/updateRealNameByPartyid";//更新person表中的realname字段
	//String url_partyaccount="party/partyaccountcs/updatePartyAccountRealNameByPartyId";//更新partyaccount表中的realname字段
	ClientUtil party = new ClientUtil(url_party_queryID);
	try {
		String partyId = party.post(url_party_queryID,map).toString();
		logger.debug("partyid:"+partyId);
		if("".equals(partyId)){
			json=JsonGenerateUtil.getMsgJson("该会员:"+map.get("partyname")+"不存在！");
		}else{
			map.put("partyid", partyId);
			json=party.post(url_operator,map).toString();//
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		json="";
	}
	return JsonGenerateUtil.getMsgJson(json);
}
public static void main(String[] args) {
	//e10adc3949ba59abbe56e057f20f883e
	System.out.println(MD5.md5("123456"));
}
}
