package tf56.consignor.services.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.serfj.client.WebServiceException;

import org.apache.log4j.Logger;

import tf56.consignor.services.PartyService;
import tf56.sofa.json.Json2ObjectUtil;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.util.ClientUtil;
import tf56.sofa.util.MD5;

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

    @Override
    public List addConsignorConsigneeToWaybillListForReport(Map map) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List addOrganizationForStatistic_Consignor(List<Map> list, String fromPartyIdList) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List addOrganizationForStatistic_Contract(List<Map> list, String partyIdList) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String addSubontractorAppdix(Map map) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String cityDistanceList(Map map) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String cityDistanceListByFromAddressAndToAddress(Map map) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String conUpdateSubconBasicInfo(Map params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String contractAndSubcontractList(String partyid, String partytype, Map params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String querySubcontractorInfo(Map map) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String settleBillList(Map map) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String settleSetList(Map params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String subcontractorList(Map map) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String updatePartyInfo(Map params) {
        // TODO Auto-generated method stub
        return null;
    }
}	


