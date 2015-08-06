package tf56.contract.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.serfj.client.WebServiceException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import tf56.contract.services.CityDistanceDao;
import tf56.contract.services.ContractAppendixDao;
import tf56.contract.services.ContractAttributeDao;
import tf56.contract.services.PartyService;
import tf56.contract.services.SettleBillDao;
import tf56.contract.services.SettleDao;
import tf56.contract.services.ShipperRelaSubContractorDao;
import tf56.contract.services.WaybillDao;
import tf56.contract.services.WaybillService;
import tf56.contract.services.WaybillStowageDao;
import tf56.sofa.json.Json2ObjectUtil;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.serializer.JsonResponse;
import tf56.sofa.util.ClientUtil;
import tf56.sofa.util.MD5;
import tf56.sofa.util.SofaSpringContext;

public class PartyServiceImpl implements PartyService{

	public static Logger logger = Logger.getLogger(PartyServiceImpl.class);
	
   //验证用户名
	/**
	 * hkf
	 */
	@Override
	public String checkPartyName(String partyname) {
		// TODO Auto-generated method stub
		String msg="";
		Map paramsMap = new HashMap();
		paramsMap.put("partyname", partyname);
		String url = "party/partycs/checkPartyName";
		ClientUtil cu=new ClientUtil(url);
		try {
			msg = cu.post(url, paramsMap).toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WebServiceException e) {
			e.printStackTrace();
		}
		return msg;
	}
	
//	@Override
//	public String checkPartyName(String partyname) {
//		// TODO Auto-generated method stub
//		String msg="";
//		Map paramsMap = new HashMap();
//		paramsMap.put("partyname", partyname);
//		String url = "party/partycs/checkPartyName";
//		System.out.println("checkPartyName="+partyname);
//		try {
//			msg = new ClientUtil(url).post(url, paramsMap).toString();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (WebServiceException e) {
//			e.printStackTrace();
//		}
//		return msg;
//	}
  //验证邮箱
	/**
	 * hkf
	 */
	@Override
	public String checkEmail(String email) {
		String msg="";
		Map paramsMap = new HashMap();
		paramsMap.put("email", email);
		String url = "party/partycs/checkEmail";
		ClientUtil cu=new ClientUtil(url);
		try {
			msg = cu.post(url, paramsMap).toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WebServiceException e) {
			e.printStackTrace();
		}
		return msg;
	}
	
//	@Override
//	public String checkEmail(String email) {
//		String msg="";
//		Map paramsMap = new HashMap();
//		paramsMap.put("email", email);
//		String url = "party/partycs/checkEmail";
//		try {
////			msg = super.getRequest(url, paramsMap).toString();
//			msg = new ClientUtil(url).getRequest(url, paramsMap).toString();
//			System.out.println("msg="+msg);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (WebServiceException e) {
//			e.printStackTrace();
//		}
//		return msg;
//	}
	//验证手机
	/**
	 * hkf
	 */
	@Override
	public String checkMobilenumber(String mobilenumber) {
		String msg="";
		Map paramsMap = new HashMap();
		paramsMap.put("mobilenumber", mobilenumber);
		String url = "party/partycs/checkMobilenumber";
		ClientUtil cu=new ClientUtil(url);
		try {
			msg = cu.post(url, paramsMap).toString();//返回一个msg给调用的人

		} catch (IOException e) {
			e.printStackTrace();
		} catch (WebServiceException e) {
			e.printStackTrace();
		}
		return msg;
	
	}
	//验证用户登陆
	/**
	 * hkf
	 */
	@Override
	public String chkLogin(String username, String password) {
		
		String msg="";
		Map paramsMap = new HashMap();
		if(username.contains("/")){
			String name=username.split("/")[0];
			String operator=username.split("/")[1];
			paramsMap.put("partyname", name);
			paramsMap.put("operator", operator);
		}else{
			paramsMap.put("partyname", username);
			paramsMap.put("operator", "admin");//role为空时即为admin
		}
		paramsMap.put("password", MD5.md5(password));//加密放入map中
		String url = "party/partycs/chkLogin";
		ClientUtil cu=new ClientUtil(url);
		try {
			msg = cu.post(url, paramsMap).toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WebServiceException e) {
			e.printStackTrace();
		}
		return msg;
	}
 //新插入数据     hkf
	@Override
	public String insert(Map map) {
		String msg="ok";
		String partyid;
		String urlU = "party/partycs/insert";  //调party的insert服务 插入用户名
		ClientUtil cuu=new ClientUtil(urlU);
		String urlP = "party/operatorcs/insert"; //调operator的insert服务 插入密码
		ClientUtil cup=new ClientUtil(urlP);
		String urlPA="party/partyaccountcs/insert";//会员帐户表插入默认数据
		ClientUtil cupa=new ClientUtil(urlPA);
		String urlS="account/scorecs/insert";//往scorecs中的积分余额表中插入数据
		ClientUtil cus=new ClientUtil(urlS);
		String urlA="account/accountcs/insert";//account中的帐户余额表中插入数据
		ClientUtil cua=new ClientUtil(urlA);
		String urlPS="party/personcs/insert";//往Person表中插入数据
		ClientUtil cups=new ClientUtil(urlPS);
		String urlsp="party/securitypermissioncs/save";//往securityPermission表中初始化数据
		ClientUtil cusp=new ClientUtil(urlsp);
		String urlspp="party/operatorparametercs/insertOperatorParameter";
		ClientUtil cuspp=new ClientUtil(urlspp);
		try {
			partyid = cuu.post(urlU, map).toString();
			if(partyid!=null&&(!"".equals(partyid))){//如何用户名信息保存成功并返回id就允许插入密码
				map.put("partyid", partyid);
				map.put("operator", "admin");
				String ss=cup.post(urlP, map).toString();//插入密码如果失败需要删除用户名信息，暂时未考虑
				map.put("accounttype", "主账户");
				String accountnumber=getAccountNumber(partyid);
				map.put("accountnumber", accountnumber);
				map.put("amount", "0"); 
				String ssa=cupa.post(urlPA, map).toString();//返回accountnumber
				String as=cus.post(urlS, map).toString(); //调用account中的积分表
				String aa=cua.post(urlA, map).toString();//account中的帐户余额表
				String pp=cups.post(urlPS, map).toString();//返回person是否成功
				map.put("securitypermission", "客户端登录权限");
				String spsp=(String) cusp.post(urlsp, map);//   
				String spspp=(String) cuspp.post(urlspp, map);//
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WebServiceException e) {
			e.printStackTrace();
		}
		return msg;
	}
	//帮助类   hkf
	private String getAccountNumber(String partyid){
		
		String defaultAaacNum = "800000000000";
		String retstr = "";
		retstr = defaultAaacNum.substring(0, defaultAaacNum.length()-partyid.length());
		retstr = retstr+partyid;
		return retstr;
	}
	/**
	 * hkf
	 * 根据partyid查询会员基本信息
	 * 2013.1.16
	 */
	@Override
	public String selectByPartyid(Map map) {
		String str="";
		String url="party/partycs/selectByPartyid";
		ClientUtil cu=new ClientUtil(url);
		try {
			str=cu.post(url, map).toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WebServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	/**
	 * hkf
	 * 查询个人信息
	 * 2013.1.16
	 */
	@Override
	public String selectpersonByPartyid(Map map) {
		String str="";
		String url="party/personcs/queryByPartyId";
		ClientUtil cu=new ClientUtil(url);
		try {
			str=cu.post(url, map).toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WebServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	/**
	 * hkf
	 * 查询企业信息
	 * 2013.1.16
	 * 
	 */
	@Override
	public String selectorganizationByPartyid(Map map) {
		String str="";
		String url="party/organizationcs/queryByPartyId";
		ClientUtil cu=new ClientUtil(url);
		try {
			str=cu.post(url, map).toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WebServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	/**
	 * hkf
	 * 更新邮箱
	 * 2013.1.17
	 */
	@Override
	public String updateemail(Map map) {
		String str="";
		String url="party/partycs/updateEmail";
		ClientUtil cu=new ClientUtil(url);
		try {
			str=cu.post(url, map).toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WebServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	/**
	 * hkf
	 * 更新手机号码
	 * 2013.1.17
	 */
	@Override
	public String updatemobilenumber(Map map) {
		String str="";
		String url="party/partycs/updateMobilenumber";
		ClientUtil cu=new ClientUtil(url);
		try {
			str=cu.post(url, map).toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WebServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	/**
	 * hkf
	 * 根据partyid查询父子会员表
	 * 2013.1.23
	 */
	@Override
	public String selectRelationShipByPartyid(Map map) {
		String str="";
		String url="party/partyrelationshipcs/selectRelationShipByPartyid";
		ClientUtil cu=new ClientUtil(url);
		try {
			str=cu.post(url, map).toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WebServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	/**
	 * hkf
	 * 根据partyid的集合查询会员类型和会员名
	 * 2013.1.23
	 */
	@Override
	public String selectPartyTypeAndPartyNameByIdList(Map map) {
		String str="";
		String url="party/partycs/selectPartyTypeAndPartyNameByIdList";
		ClientUtil cu=new ClientUtil(url);
		try {
			str=cu.post(url, map).toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WebServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	/**
	 * hkf
	 * 查询person表中的realname
	 * 2013.1.23
	 */
	@Override
	public String selectRealnameByPartyid(Map map) {
		String str="";
		String url="party/personcs/selectRealnameByPartyid";
		ClientUtil cu=new ClientUtil(url);
		try {
			str=cu.post(url, map).toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WebServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	/**
	 * hkf
	 * 根据partyid查询企业名
	 * 2013.1.24
	 */
	@Override
	public String selectOrganizationNameByPartyid(Map map) {
		String str="";
		String url="party/organizationcs/selectOrganizationNameByPartyid";
		ClientUtil cu=new ClientUtil(url);
		try {
			str=cu.post(url, map).toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WebServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	/**
	 * hkf
	 * 更新密码
	 * 2013.1.29
	 */
	@Override
	public String updatePassword(Map map) {
		String str="";
		String url="party/operatorcs/update";
		ClientUtil cu=new ClientUtil(url);
		try {
			str=cu.post(url, map).toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WebServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	/**
	 * hkf
	 * 查询父会员的公司名
	 * 2013.1.31
	 */
	@Override
	public String selectFromOrganization(Map map) {
		String str="";
		String url="party/partyrelationshipcs/selectFromPartyId";
		String urlp="party/organizationcs/selectOrganization";
		ClientUtil cu=new ClientUtil(url);
		ClientUtil cup=new ClientUtil(url);
		try {
			String frompartyid=(String) cu.post(url, map);
			if(frompartyid==null||frompartyid.equals("")){//如果找不到父partyid
				return str;
			}else{
				Map parmap=new HashMap();
				parmap.put("partyid", frompartyid);
				str=(String) cup.post(urlp, parmap);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WebServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return str;
	}
/**
 * hke
 * 根据partyid和operator查询权限
 * 2013.4.22
 */
	@Override
	public String selectPermissionByOperatorAndPartyid(Map map) {
		String str="";
		String url="party/securitypermissioncs/selectPermissionByOperatorAndPartyid";
		ClientUtil cu=new ClientUtil(url);
		try {
			str=cu.post(url, map).toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WebServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * hkf
	 * 查询司机和车信息（被维护by wcl 2013-09-13）
	 * 2013.7.3
	 */
	@Override
	public String selectCarDriverMsgByPartyId(Map map) {
		String msg="";
		String url="party/vpartycarcs/selectDetailByPartyId";
		ClientUtil cu1=new ClientUtil(url);
		String urla="party/partyaccountcs/selectDetailByPartyId";
		ClientUtil cu2=new ClientUtil(urla);
		try {
			String vparty=cu1.post(url, map).toString();
			String partyacc=cu2.post(urla, map).toString();
			Map vmap=Json2ObjectUtil.parseJSON2Map(vparty);
			if(!vmap.get("carexamineenddate").toString().equals("")){
				vmap.put("carexamineenddate", vmap.get("carexamineenddate").toString().substring(0, 10));
			}
			if(!vmap.get("carinsureenddate").toString().equals("")){
				vmap.put("carinsureenddate", vmap.get("carinsureenddate").toString().substring(0, 10));
			}
			Map pmap=Json2ObjectUtil.parseJSON2Map(partyacc);
			String cardcode=pmap.get("cardcode")+"";
			vmap.put("cardcode", cardcode);
			msg=JsonGenerateUtil.map2json(vmap);		
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return msg;
	}
	
	

	/**
	 * hkf
	 * 验证司机手机号
	 * 2013.7.3
	 */
	@Override
	public String checkDriverMobileNumber(String mobileInfo) {
		String msg="";
		Map paramsMap = new HashMap();
		paramsMap.put("mobilenumber", mobileInfo);
		String url = "party/personcs/checkMobilenumber";
		ClientUtil cu=new ClientUtil(url);
		try {
			msg = cu.post(url, paramsMap).toString();//返回一个msg给调用的人
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WebServiceException e) {
			e.printStackTrace();
		}
		return msg;
	
	}

	/**
	 * hkf
	 * 更新司机手机号码
	 * 2013.7.3
	 */
	@Override
	public String updateDriverMobileNumber(Map map) {
		String msg="";
		String url="party/personcs/updateDriverMobileNumber";
		ClientUtil cu=new ClientUtil(url);
		try {
			msg = cu.post(url, map).toString();//返回一个msg给调用的人

		} catch (IOException e) {
			e.printStackTrace();
		} catch (WebServiceException e) {
			e.printStackTrace();
		}
		return msg;
	}
	/**
	 * hkf
	 * 更新司机电话号码
	 * 2013.7.3
	 */
	@Override
	public String updateDriverTelephoneNumber(Map map) {
		String msg="";
		String url="party/personcs/updateDriverTelephoneNumber";
		ClientUtil cu=new ClientUtil(url);
		try {
			msg = cu.post(url, map).toString();//返回一个msg给调用的人

		} catch (IOException e) {
			e.printStackTrace();
		} catch (WebServiceException e) {
			e.printStackTrace();
		}
		return msg;
	}
	/***
	 * 分包商link总包，总包link发货方or总包link发货方link分包商列表
	 *  @author lianggui.zhou 
	 *  @date 2013-09-10
	 * @param map
	 * @return
	 */
	public String subcontractorList(Map map){
		//String url1="contract/shipperrelasubontractorcs/contractAndSubcontractList";
		ShipperRelaSubContractorDao shipperRelaSubContractorDao=
															(ShipperRelaSubContractorDao)SofaSpringContext.getBean("shipperRelaSubContractorDao");
		String url="party/subcontractorcs/subcontractorList";
		ClientUtil cu=new ClientUtil(url);
		StringBuffer sb=new StringBuffer();
		String msg="";
		String partytype=map.get("partytype").toString();
		try {
			List<Map> list=shipperRelaSubContractorDao.contractAndSubcontractList(map.get("partyid").toString(),map.get("partytype").toString(),map.get("frompartyid")==null?"":map.get("frompartyid").toString(),new HashMap());
			int i=list.size()-1;
			int j=0;
			for(Map map1:list){//组合总包下面的分包商partyid带入Party查询分包商信息
				if("分包".equals(partytype)||"总分发".equals(partytype)||"linked".equals(partytype)){
					String topartyid=map1.get("topartyid")==null?"":map1.get("topartyid").toString();
					j++;
					if(j==list.size()&&!topartyid.equals("")){
						sb.append(map1.get("topartyid").toString());
					}else if(j<list.size()&&!topartyid.equals("")){
						sb.append(map1.get("topartyid").toString()+"-");
					}
				}
				if("发货方".equals(partytype)){
					String frompartyid=map1.get("frompartyid")==null?"":map1.get("frompartyid").toString();
					j++;
					if(j==list.size()&&!frompartyid.equals("")){
						sb.append(map1.get("frompartyid").toString());
					}else if(j<list.size()&&!frompartyid.equals("")){
						sb.append(map1.get("frompartyid").toString()+"-");
					}
				}
			}
			map.put("partystrs", sb.toString());
			msg=cu.post(url, map).toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WebServiceException e) {
			e.printStackTrace();
		}
		return msg;
	}
	/***
	 * 分包商详情
	 *  @author lianggui.zhou 
	 *  @date 2013-09-10
	 * @param map
	 * @return
	 */
	public String querySubcontractorInfo(Map map){
		String url="party/subcontractorcs/querySubcontractorInfo";
		ClientUtil cu=new ClientUtil(url);
		//String url1="contract/contractappendixcs/selectList";
		ContractAppendixDao contractAppendixDao=(ContractAppendixDao)SofaSpringContext.getBean("contractAppendixDao");
		String msg="";
		 Map map1=new HashMap();
		try {
			 msg=cu.post(url, map).toString();
			 map1=Json2ObjectUtil.parseJSON2Map(msg);
			 List list=new ArrayList();
			// msg=super.postRequest(url1, map).toString();
			 list=contractAppendixDao.selectList(map);
			 map1.put("list",list);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WebServiceException e) {
			e.printStackTrace();
		}
		return JsonGenerateUtil.map2json(map1);
	}
	/**
	 * 分包商修改之附件添加
	 * @author lianggui.zhou
	 * @return
	 */
	public String addSubontractorAppdix(Map map){
		String url="contract/subcontractorcs/addSubontractorAppdix";
		ClientUtil cu=new ClientUtil(url);
		String msg="";
		try {
			 msg=cu.post(url, map).toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WebServiceException e) {
			e.printStackTrace();
		}
		return msg;
	}
	/***
	 * 增加分包商时修改会员信息
	 * @author lianggui.zhou
	 * @date 2013-10-16
	 * @param partyid
	 * @return
	 */
	public String updatePartyInfo(Map params){
		String url = "party/partycs/updatePartyInfo";
		ClientUtil cu=new ClientUtil(url);
		//String url1="contract/shipperrelasubontractorcs/insertShipperRelaSubContractor";
		ShipperRelaSubContractorDao shipperRelaSubContractorDao=
			(ShipperRelaSubContractorDao)SofaSpringContext.getBean("shipperRelaSubContractorDao");
		String msg="sorry";
		String officeaddress=params.get("officeaddress").toString();
		String partyid=params.get("partyid").toString();//总包商会员id
		String topartyid=params.get("topartyid")==null?params.get("frompartyid").toString():params.get("topartyid").toString();//分包商会员id
		String saler=params.get("saler").toString();//
		params.put("detailaddress", params.get("detailaddress"));
		//params.remove("detailaddress");
		params.put("partytype", "企业");
		String topartyname=params.get("partyname").toString();
		params.putAll(addKeyValue(officeaddress));
		try {
			params.put("partyid", topartyid);
			String result=cu.post(url, params).toString();
			if(result.equals("ok")){
				params.put("partyid", partyid);
				String re=shipperRelaSubContractorDao.transactionInsertShipperRelaSubContractor(params);
				if(re.equals("ok")){
					msg="ok";
					String shipperorsubcontractor=params.get("shipperorsubcontractor").toString();
					if(shipperorsubcontractor.equals("0")){
						this.conUpdateSubconBasicInfo(params);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WebServiceException e) {
			e.printStackTrace();
		}
		return msg;
	}
	private Map addKeyValue(String str){
		Map map=new HashMap();
		if(!str.equals("")){
			if(str.split("-").length==1){
				map.put("province",str.split("-")[0]);
				map.put("city",null);
				map.put("region",null);
			}
			if(str.split("-").length==2){
				map.put("province",str.split("-")[0]);
				map.put("city",str.split("-")[1]);
				map.put("region",null);
			}
			if(str.split("-").length==3){
				map.put("province",str.split("-")[0]);
				map.put("city",str.split("-")[1]);
				map.put("region",str.split("-")[2]);
			}
			else{
				map.put("province",null);
				map.put("city",null);
				map.put("region",null);
			}
		}else{
			map.put("province",null);
			map.put("city",null);
			map.put("region",null);
		}
		return map;
	}
	/**
	 * 总包会员修改分包商基础信息或者总包修改发货方基础信息
	 * @author lianggui.zhou
	 * @date 2013-10-21
	 * @return
	 */
	public String conUpdateSubconBasicInfo(Map params){
		String url = "party/partycs/updatePartyInfo";
		ClientUtil cu=new ClientUtil(url);
		//String url1="contract/contractattributecs/updateContractAttribute";
		ContractAttributeDao contractAttributeDao=(ContractAttributeDao)SofaSpringContext.getBean("contractAttributeDao");
		String msg="sorry";
		String officeaddress=params.get("officeaddress").toString();
		String partyid=params.get("partyid").toString();//总包商会员id
		String topartyid=params.get("topartyid")==null?params.get("frompartyid").toString():params.get("topartyid").toString();//分包商会员id
		String saler=params.get("saler").toString();//
		params.put("officeaddress", params.get("detailaddress"));
		params.remove("detailaddress");
		params.put("partytype", "企业");
		//String officeaddress=params.get("officeaddress").toString();
		String topartyname=params.get("partyname").toString();
		params.putAll(addKeyValue(officeaddress));
		try {
			params.put("partyid", topartyid);
			String result=cu.post(url, params).toString();
			if(result.equals("ok")){
				params.put("partyid", partyid);
				//String partyid=params.get("partyid").toString();
				//String topartyid=params.get("topartyid")==null?params.get("frompartyid").toString():params.get("topartyid").toString();
				String shipperorsubcontractor=params.get("shipperorsubcontractor").toString();
				String re = "ok";
				int i = 0;
				re=contractAttributeDao.updateContractAttribute(partyid, topartyid, shipperorsubcontractor,"业务员",params.get("saler").toString(), params);
				if(!(re.equals("ok"))){i++;}
				if(shipperorsubcontractor.equals("0")){
					re=contractAttributeDao.updateContractAttribute(partyid, topartyid, shipperorsubcontractor,"车辆数量",params.get("clsl").toString(), params);
					if(!(re.equals("ok"))){	i++;}
					re=contractAttributeDao.updateContractAttribute(partyid, topartyid, shipperorsubcontractor,"投保金额",params.get("tbje").toString(), params);
					if(!(re.equals("ok"))){i++;}
					re=contractAttributeDao.updateContractAttribute(partyid, topartyid, shipperorsubcontractor,"年营业额",params.get("nyye").toString(), params);
					if(!(re.equals("ok"))){	i++;}
					re=contractAttributeDao.updateContractAttribute(partyid, topartyid, shipperorsubcontractor,"网点数量",params.get("wdsl").toString(), params);
					if(!(re.equals("ok"))){	i++;}
					re=contractAttributeDao.updateContractAttribute(partyid, topartyid, shipperorsubcontractor,"年运输量",params.get("nysl").toString(), params);
					if(!(re.equals("ok"))){	i++;}
					re=contractAttributeDao.updateContractAttribute(partyid, topartyid, shipperorsubcontractor,"是否使用系统",params.get("syxt").toString(), params);
					if(!(re.equals("ok"))){	i++;}
					
					re=contractAttributeDao.updateContractAttribute(partyid, topartyid, shipperorsubcontractor,"优势行业",params.get("yshy").toString(), params);
					if(!(re.equals("ok"))){	i++;}
					re=contractAttributeDao.updateContractAttribute(partyid, topartyid, shipperorsubcontractor,"经营范围",params.get("jyfw").toString(), params);
					if(!(re.equals("ok"))){	i++;}
				}
				if(i==0){
					msg="ok";
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WebServiceException e) {
			e.printStackTrace();
		}
		return msg;
	}
	/***
	 * 查询总包会员下的分包商或发货方
	 * @author lianggui.zhou
	 * @date 2013-10-17
	 * @param partyid 总包partyid
	 * @return 
	 */
	public String contractAndSubcontractList(String partyid,String partytype,Map params){
		Map map=new HashMap();
		params.put("partyid", partyid);
		params.put("partytype", partytype);
		params.put("frompartyid", params.get("frompartyid") == null ?"":params.get("frompartyid").toString());
		//String url="contract/shipperrelasubontractorcs/contractAndSubcontractList";
		String urlparty="party/subcontractorcs/contractAndSubcontractorList";
		ClientUtil cu=new ClientUtil(urlparty);
		String msg="";
		StringBuffer sb=new StringBuffer();
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try {
			list=(List)params.get("list");
			int i=list.size()-1;
			int j=0;
			for(Map map1:list){//组合总包下面的分包商partyid带入Party查询分包商信息
				if("分包".equals(partytype)||"总分发".equals(partytype)||"linked".equals(partytype)){
					String topartyid=map1.get("topartyid")==null?"":map1.get("topartyid").toString();
					j++;
					if(j==list.size()&&!topartyid.equals("")){
						sb.append(map1.get("topartyid").toString());
					}else if(j<list.size()&&!topartyid.equals("")){
						sb.append(map1.get("topartyid").toString()+"-");
					}
				}
				if("发货方".equals(partytype)){
					String frompartyid=map1.get("frompartyid")==null?"":map1.get("frompartyid").toString();
					j++;
					if(j==list.size()&&!frompartyid.equals("")){
						sb.append(map1.get("frompartyid").toString());
					}else if(j<list.size()&&!frompartyid.equals("")){
						sb.append(map1.get("frompartyid").toString()+"-");
					}
				}
			}
			params.put("partystrs", sb.toString());
			params.remove("list");
			msg=cu.post(urlparty, params).toString();
			if("分包".equals(partytype)){
				Map newMap = Json2ObjectUtil.parseJSON2Map(msg);
				List newList = (List) newMap.get("Data");
				Map mapTemp;
				for(Object object:newList){
					mapTemp = (Map) object;
					for(Map map2:list){
						String topartyid=map2.get("topartyid")==null?"":map2.get("topartyid").toString();
						String partyId = (String) mapTemp.get("partyid");
						if(topartyid.equals(partyId)){
							mapTemp.put("tbje", map2.get("tbje"));
							mapTemp.put("nyye", map2.get("nyye"));
							mapTemp.put("nysl", map2.get("nysl"));
							mapTemp.put("syxt", map2.get("syxt"));
							mapTemp.put("clsl", map2.get("clsl"));
							mapTemp.put("wdsl", map2.get("wdsl"));
							mapTemp.put("yshy", map2.get("yshy"));
							mapTemp.put("jyfw", map2.get("jyfw"));
						}
					}
					
				}
			msg = JsonGenerateUtil.map2json(newMap);
			}
			if("发货方".equals(partytype)){
				Map newMap = Json2ObjectUtil.parseJSON2Map(msg);
				List newList = (List) newMap.get("Data");
				Map mapTemp;
				for(Object object:newList){
					mapTemp = (Map) object;
					for(Map map2:list){
						String frompartyid=map2.get("frompartyid")==null?"":map2.get("frompartyid").toString();
						String partyId = (String) mapTemp.get("partyid");
						if(frompartyid.equals(partyId)){
							mapTemp.put("khh", map2.get("khh"));
						}
					}
					
				}
			msg = JsonGenerateUtil.map2json(newMap);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WebServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}
	/**
	 * wei.huang
	 * 2013-11-06
	 * @param map获取城区间距离列表
	 * @return
	 */
	public String cityDistanceList(Map map){
		String msgJson = "";
		CityDistanceDao cityDistanceDao = (CityDistanceDao) SofaSpringContext.getBean("cityDistanceDao");
		List<Map> list = cityDistanceDao.selectList(map);
		StringBuffer sb = new StringBuffer();
		try {
			int j = 0;
			for (Map map1 : list) {
				String frompartyid = map1.get("fromPartyId") == null ? "": map1.get("fromPartyId").toString();
				j++;
				if (j == list.size() && !frompartyid.equals("")) {
					sb.append(frompartyid);
				} else if (j < list.size() && !frompartyid.equals("")) {
					sb.append(frompartyid + "-");
				}
			}
			String msg = "";
			String url = "party/subcontractorcs/contractAndSubcontractorList";
			ClientUtil cu=new ClientUtil(url);
			map.put("partystrs", sb.toString());
			msg = cu.post(url, map).toString();
			
			Map maplist = Json2ObjectUtil.parseJSON2Map(msg);
			String count = maplist.get("Count").toString();
			List<Map<String, Object>> datalist =(ArrayList<Map<String, Object>>) maplist.get("Data");
			// 合并数据
			for (int m = 0; m < datalist.size(); m++) {
				for (int n = 0; n < list.size(); n++) {
					if (datalist.get(m).get("partyid").toString().equals(list.get(n).get("fromPartyId").toString())) {
						datalist.get(m).remove("partyid");
						datalist.get(m).putAll(list.get(n));
						break;
					}
				}
			}
			msgJson = JsonGenerateUtil.getPageListJson(datalist, count);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WebServiceException e) {
			e.printStackTrace();
		}
		return msgJson;
	}
	/**
	 * 查询指定发货方下的城区间距离列表
	 * @author wei.huang
	 **/
	public String cityDistanceListByFromAddressAndToAddress(Map map){
		String msgJson = "";
		CityDistanceDao cityDistanceDao = (CityDistanceDao) SofaSpringContext.getBean("cityDistanceDao");
		List<Map> list = cityDistanceDao.selectListByFromAddressAndToAddress(map);
		StringBuffer sb = new StringBuffer();
		try {
			int j = 0;
			for (Map map1 : list) {
				String topartyid = map1.get("topartyid") == null ? "": map1.get("topartyid").toString();
				j++;
				if (j == list.size() && !topartyid.equals("")) {
					sb.append(topartyid);
				} else if (j < list.size() && !topartyid.equals("")) {
					sb.append(topartyid + "-");
				}
			}
			String msg = "";
			String url = "party/subcontractorcs/contractAndSubcontractorList";
			ClientUtil cu=new ClientUtil(url);
			map.put("partystrs", sb.toString());
			msg = cu.post(url, map).toString();
			
			Map maplist = Json2ObjectUtil.parseJSON2Map(msg);
			List<Map<String, Object>> datalist =(ArrayList<Map<String, Object>>) maplist.get("Data");
			// 合并数据
			for (int m = 0; m < datalist.size(); m++) {
				for (int n = 0; n < list.size(); n++) {
					if (datalist.get(m).get("partyid").toString().equals(list.get(n).get("topartyid").toString())) {
						datalist.get(m).remove("partyid");
						datalist.get(m).putAll(list.get(n));
						break;
					}
				}
			}
			msgJson = JsonGenerateUtil.getListJson(datalist);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WebServiceException e) {
			e.printStackTrace();
		}
		return msgJson;
	}
	/**
	 * @author wei.huang
	 * @date 2013-11-19
	 * @function 结算单列表（含party部分的数据）
	 * @param map{inorout,[settlebillnumber,status,startdate,enddate,inoutpartyid]}
	 * @return
	 */
	public String settleBillList(Map map){
		String msgJson="";
		Map temp2=new HashMap();
		temp2=map;
		try{
		Map newMap = new HashMap();
		newMap.put("partyid",map.get("partyid"));
		
		String inoutpartyrealname="";
		String conorgname="";
		if(map.get("inoutpartyrealname") != null && !map.get("inoutpartyrealname").equals("")){
			inoutpartyrealname = map.get("inoutpartyrealname").toString();
		}
		if("1".equals(map.get("inorout"))){
			//取该总包所有发货方
			newMap.put("partytype", "发货方");
		}else {
			//取该总包所有分包商
			newMap.put("partytype", "分包");
		}
		WaybillService waybillService = (WaybillService)SofaSpringContext.getBean("waybillService");
		String ognStr = waybillService.combiWaybillList(newMap);
		Map ognMap = Json2ObjectUtil.parseJSON2Map(ognStr);
		List ognList = (List) ognMap.get("Data");
		//取名字匹配的idList
		Map mapTemp = null;
		String partyId = "";
		List idList = new ArrayList<String>();
		if(StringUtils.isNotBlank(inoutpartyrealname)){
			for (Object object : ognList) {
				mapTemp = (Map) object;
				partyId = (String) mapTemp.get("partyid");
				String organizationName = (String)mapTemp.get("organization");
				if(organizationName.contains(inoutpartyrealname)){
					idList.add(partyId);
				}
			}
			if(idList.size()<1){
				idList.add("999999999");
			}
			map.put("idList", idList.toString().replace("[", "").replace("]", ""));
		}
		Map temp=null;
		if(map.get("conorgname")!=null){
			temp=getConIdForSub(temp2, partyId);
		}if(map.get("conorgname")!=null&&!"".equals((map.get("conorgname").toString()))){
			map.put("conIdList", temp.get("conIdList").toString().replace("[", "").replace("]", ""));
		}
		//取数
		SettleBillDao settleBillDao = (SettleBillDao) SofaSpringContext.getBean("settleBillDao");
		List<Map> list = settleBillDao.selectList(map);
		String count=settleBillDao.selectListCount(map);
		//取名称
		for (Map obj:list) {
			for (Object object : ognList) {
				mapTemp = (Map) object;
				partyId = (String) mapTemp.get("partyid");
				if(partyId.equals(obj.get("inoutpartyid").toString())){
					obj.put("organization",mapTemp.get("organization"));
					break;
				}
			}
			//取发货方名称
		}
		Map mapTemp1=null;
		if(map.get("conorgname")!=null){
			List orgList=(List)temp.get("orgList");
			for (Map obj:list) {
				for (Object object : orgList) {
					mapTemp1 = (Map) object;
					partyId = (String) mapTemp1.get("partyid");
					if(obj.get("inoutpartyidsec")!=null&&partyId.equals(obj.get("inoutpartyidsec").toString())){
						obj.put("conorganization",mapTemp1.get("organization"));
						break;
					}
					if(obj.get("inoutpartyidsec")==null){
						obj.put("conorganization","");
						break;
					}
			}
			}
		}
		msgJson = JsonGenerateUtil.getPageListJson(list, count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msgJson;
	}

	/**
	 * @author yaoyan.lin
	 * @date 2013-11-21
	 * @function 结算设置列表（含party部分的数据）
	 * @return
	 */
	@Override
	public String settleSetList(Map map) {
		String msgJson = "";
		SettleDao settleDao = (SettleDao) SofaSpringContext.getBean("settleDao");
		List<Map> list = settleDao.selectList(map);
		StringBuffer sb = new StringBuffer();
		try {
			int j = 0;
			for (Map map1 : list) {
				String topartyid = map1.get("topartyid") == null ? "": map1.get("topartyid").toString();
				j++;
				if (j == list.size() && !topartyid.equals("")) {
					sb.append(topartyid);
				} else if (j < list.size() && !topartyid.equals("")) {
					sb.append(topartyid + "-");
				}
			}
			String msg = "";
			String url = "party/subcontractorcs/contractAndSubcontractorList";
			ClientUtil cu=new ClientUtil(url);
			map.put("partystrs", sb.toString());
			msg = cu.post(url, map).toString();
			
			Map maplist = Json2ObjectUtil.parseJSON2Map(msg);
			String count = maplist.get("Count").toString();
			List<Map<String, Object>> datalist =(ArrayList<Map<String, Object>>) maplist.get("Data");
			// 合并数据
			for (int m = 0; m < datalist.size(); m++) {
				for (int n = 0; n < list.size(); n++) {
					if (datalist.get(m).get("partyid").toString().equals(list.get(n).get("topartyid").toString())) {
						datalist.get(m).remove("partyid");
						datalist.get(m).putAll(list.get(n));
						break;
					}
				}
			}
			msgJson = JsonGenerateUtil.getPageListJson(datalist, count);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WebServiceException e) {
			e.printStackTrace();
		}
		return msgJson;
	}
	
	/**
	 * @author wei.huang
	 * @date 2013-11-27
	 * @function 将发货方和分包商的名称合并到waybillList中
	 * @param map
	 * @return
	 */
	public List addConsignorConsigneeToWaybillListForReport(Map map){
		WaybillDao waybillDao=(WaybillDao)SofaSpringContext.getBean("waybillDao");
		List<Map> list=waybillDao.selectWaybillListForReport(map);
		String frompartyidlist_temp=map.get("frompartyid_temp").toString();
		String topartyidlist_temp=map.get("topartyid_temp").toString();
		try{
			String url = "party/subcontractorcs/contractAndSubcontractorList";
			ClientUtil cu=new ClientUtil(url);
			//传递给party的参数
			Map fromPartyIdMap=new HashMap();
			fromPartyIdMap.put("partystrs", frompartyidlist_temp);
			fromPartyIdMap.put("skipCount", "0");
			fromPartyIdMap.put("pageSize", "1000000000");
			String msg_FromPartyId = cu.post(url, fromPartyIdMap).toString();
			Map toPartyIdMap=new HashMap();
			toPartyIdMap.put("partystrs", topartyidlist_temp);
			toPartyIdMap.put("skipCount", "0");
			toPartyIdMap.put("pageSize", "1000000000");
			String msg_ToPartyId = cu.post(url, toPartyIdMap).toString();
			//将party部分的数据转换成List
			Map maplist_FromPartyId = Json2ObjectUtil.parseJSON2Map(msg_FromPartyId);
			List<Map<String, Object>> datalist_FromPartyId =(ArrayList<Map<String, Object>>) maplist_FromPartyId.get("Data");
			Map maplist_ToPartyId = Json2ObjectUtil.parseJSON2Map(msg_ToPartyId);
			List<Map<String, Object>> datalist_ToPartyId =(ArrayList<Map<String, Object>>) maplist_ToPartyId.get("Data");
			//合并数据，将发货方、分包商对应的organization添加到list中
			for (int n = 0; n < list.size(); n++) {
				//合并frompartyid对应的organization
				for (int m = 0; m < datalist_FromPartyId.size(); m++) {
					if(list.get(n).get("frompartyid")==null||list.get(n).get("frompartyid").equals("")){
						list.get(n).put("consignor", "");
						break;
					}
					if (list.get(n).get("frompartyid").toString().equals(datalist_FromPartyId.get(m).get("partyid").toString())) {
						list.get(n).put("consignor", datalist_FromPartyId.get(m).get("organization").toString());
						break;
					}
				}
				//合并topartyid对应的organization
				for (int m = 0; m < datalist_ToPartyId.size(); m++) {
					if(list.get(n).get("topartyid")==null||list.get(n).get("topartyid").equals("")){
						list.get(n).put("consignee", "");
						break;
					}
					if (list.get(n).get("topartyid").toString().equals(datalist_ToPartyId.get(m).get("partyid").toString())) {
						list.get(n).put("consignee", datalist_ToPartyId.get(m).get("organization").toString());
						break;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WebServiceException e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * @author wei.huang
	 * @date 2013-11-27
	 * @function 将组织名称合并到总包层面的统计数据中
	 * @param list 待添加名称的list
	 * @param partyIdList 指定形式的partyid字符串
	 * @return
	 */
	public List addOrganizationForStatistic_Contract(List<Map> list,String partyIdList){
		try{
			String url = "party/subcontractorcs/contractAndSubcontractorList";
			ClientUtil cu=new ClientUtil(url);
			//传递给party的参数
			Map contractPartyIdMap=new HashMap();
			contractPartyIdMap.put("partystrs", partyIdList);
			contractPartyIdMap.put("skipCount", "0");
			contractPartyIdMap.put("pageSize", "1000000000");
			String msg_PartyId = cu.post(url, contractPartyIdMap).toString();
			//将party部分的数据转换成List
			Map maplist_PartyId = Json2ObjectUtil.parseJSON2Map(msg_PartyId);
			List<Map<String, Object>> datalist_PartyId =(ArrayList<Map<String, Object>>) maplist_PartyId.get("Data");
			//合并数据，将partyid对应的organization添加到list中
			for (int n = 0; n < list.size(); n++) {
				boolean flag=false;
				//合并partyid对应的organization
				for (int m = 0; m < datalist_PartyId.size(); m++) {
					if(list.get(n).get("partyid")==null||list.get(n).get("partyid").equals("")){
						list.get(n).put("organization", "");
						flag=true;
						break;
					}
					if (list.get(n).get("partyid").toString().equals(datalist_PartyId.get(m).get("partyid").toString())) {
						list.get(n).put("organization", datalist_PartyId.get(m).get("organization").toString());
						flag=true;
						break;
					}
				}
				if(!flag){
					list.remove(n);
					n--;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WebServiceException e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * @author wei.huang
	 * @date 2013-11-27
	 * @function 将组织名称合并到发货方层面的统计数据中
	 * @param list 待添加名称的list
	 * @param partyIdList 指定形式的frompartyid字符串
	 * @return
	 */
	public List addOrganizationForStatistic_Consignor(List<Map> list,String fromPartyIdList){
		try{
			String url = "party/subcontractorcs/contractAndSubcontractorList";
			ClientUtil cu=new ClientUtil(url);
			//传递给party的参数
			Map consignorPartyIdMap=new HashMap();
			consignorPartyIdMap.put("partystrs", fromPartyIdList);
			consignorPartyIdMap.put("skipCount", "0");
			consignorPartyIdMap.put("pageSize", "1000000000");
			String msg_PartyId = cu.post(url, consignorPartyIdMap).toString();
			//将party部分的数据转换成List
			Map maplist_PartyId = Json2ObjectUtil.parseJSON2Map(msg_PartyId);
			List<Map<String, Object>> datalist_PartyId =(ArrayList<Map<String, Object>>) maplist_PartyId.get("Data");
			//合并数据，将frompartyid对应的organization添加到list中
			for (int n = 0; n < list.size(); n++) {
				boolean flag=false;
				//合并partyid对应的organization
				for (int m = 0; m < datalist_PartyId.size(); m++) {
					if(list.get(n).get("frompartyid")==null||list.get(n).get("frompartyid").equals("")){
						list.get(n).put("organization", "");
						flag=true;
						break;
					}else if (list.get(n).get("frompartyid").toString().equals(datalist_PartyId.get(m).get("partyid").toString())) {
						list.get(n).put("organization", datalist_PartyId.get(m).get("organization").toString());
						flag=true;
						break;
					}
				}
				if(!flag){
					list.remove(n);
					n--;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WebServiceException e) {
			e.printStackTrace();
		}
		return list;
	}
	private Map<String,Object> getConIdForSub(Map map1,String partyId){
		Map map=new HashMap();
		map.putAll(map1);
		String conOrgName="";
		if(map.get("conorgname") != null && !map.get("conorgname").equals("")){
			conOrgName = map.get("conorgname").toString();
		}
			//取该总包所有发货方
		map.put("partytype", "发货方");
		WaybillService waybillService = (WaybillService)SofaSpringContext.getBean("waybillService");
		String ognStr = waybillService.combiWaybillList(map);
		Map ognMap = Json2ObjectUtil.parseJSON2Map(ognStr);
		List ognList = (List) ognMap.get("Data");
		//取名字匹配的idList
		Map mapTemp = null;
		String partyId1 = "";
		List idList = new ArrayList<String>();
			for (Object object : ognList) {
				mapTemp = (Map) object;
				partyId1 = (String) mapTemp.get("partyid");
				String organizationName = (String)mapTemp.get("organization");
				if(organizationName.contains(conOrgName)){
					idList.add(partyId1);
				}
			}
			if(idList.size()<1){
				idList.add("9999999999");
			}
		map.clear();
		map.put("conIdList", idList);
		map.put("orgList", ognList);
		return map;
	}
	/***
     * 分包商银行保理列表
     * @author hcm
     * @date 2014-2-27
     * @param map
     * @return
     */
    public String factoringList(Map map){
    	ShipperRelaSubContractorDao shipperRelaSubContractorDao=(ShipperRelaSubContractorDao)SofaSpringContext.getBean("shipperRelaSubContractorDao");
		List<Map> list=shipperRelaSubContractorDao.factoringList(map);
		String partytype = map.get("partytype").toString();
		map.put("frompartyid", map.get("frompartyid") == null ?"":map.get("frompartyid").toString());
		String urlparty="party/subcontractorcs/contractAndSubcontractorList";
		ClientUtil cu=new ClientUtil(urlparty);
		String msg="";
		StringBuffer sb=new StringBuffer();
		try {
			int i=list.size()-1;
			int j=0;
			for(Map map1:list){
				if("分包".equals(partytype)){
					String topartyid=map1.get("topartyid")==null?"":map1.get("topartyid").toString();
					j++;
					if(j==list.size()&&!topartyid.equals("")){
						sb.append(map1.get("topartyid").toString());
					}else if(j<list.size()&&!topartyid.equals("")){
						sb.append(map1.get("topartyid").toString()+"-");
					}
				}
			}
			map.put("partystrs", sb.toString());
			msg=cu.post(urlparty, map).toString();
			if("分包".equals(partytype)){
				Map newMap = Json2ObjectUtil.parseJSON2Map(msg);
				List newList = (List) newMap.get("Data");
				Map mapTemp;
				for(Object object:newList){
					mapTemp = (Map) object;
					for(Map map2:list){
						String topartyid=map2.get("topartyid")==null?"":map2.get("topartyid").toString();
						String partyId = (String) mapTemp.get("partyid");
						if(topartyid.equals(partyId)){
							mapTemp.put("yhjgzh", map2.get("yhjgzh"));
							mapTemp.put("zzjgdmz", map2.get("zzjgdmz"));
							mapTemp.put("status", map2.get("status"));
						}
					}
					
				}
			msg = JsonGenerateUtil.map2json(newMap);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WebServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
		}
    public static void main(String[] args) {
		
	}

	@Override
	public String waybillStowageCommit(Map map) {
		String msg = "";
		WaybillStowageDao waybillStowageDao = (WaybillStowageDao) SofaSpringContext.getBean("waybillStowageDao");
		try {
			String url = "exchange/contractcs/exchangeXmlBetweenScmAndRyp";
			ClientUtil cu=new ClientUtil(url);
			msg = cu.post(url, map).toString();
			msg = JsonGenerateUtil.getMsgJson(getContentOfXml(msg, "/report_data/result/comment"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WebServiceException e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	private Document StrToXMLDocment(String xmlDoc) {
        Document document = null;
        try {
            document = DocumentHelper.parseText(xmlDoc);// DocumentHelper.parseText(str)这个方法将传入的XML字符串转换处理后返回一个Document对象
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }
	
	private String getContentOfXml(String xmlDoc,String nodesName){
		/** 获取不重复的单个节点的内容*/
        Document document =  StrToXMLDocment(xmlDoc);
        List<Element> list0 = document.selectNodes(nodesName);
        String value = "";
        for(Element el : list0){
        	value = el.getText();
        }
        return value;
	}
}	


