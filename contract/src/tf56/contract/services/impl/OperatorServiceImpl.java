package tf56.contract.services.impl;

import java.io.IOException;
import java.util.Map;

import net.sf.serfj.client.WebServiceException;
import tf56.contract.services.OperatorService;
import tf56.sofa.json.Json2ObjectUtil;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.util.ClientUtil;
import tf56.sofa.util.MD5;

public class OperatorServiceImpl implements OperatorService{
   
	/**
	 * @author hkf
	 * 更新密码实现
	 */
	@Override
	public String update(Map map) {
		String msg="";
		String oldpwd="";
		String urlS="party/operatorcs/queryOldPassword";
		ClientUtil cu1=new ClientUtil(urlS);
		String url="party/operatorcs/update";
		ClientUtil cu2=new ClientUtil(url);
		String oldpassword=map.get("oldpassword").toString();
		oldpassword=MD5.md5(oldpassword);
		try {
			oldpwd=cu1.post(urlS, map).toString();
			if(oldpassword.equals(oldpwd)){
				msg=cu2.post(url, map).toString();
			}else{
				String jsonmsg=JsonGenerateUtil.getMsgJson(msg);
				return jsonmsg;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WebServiceException e) {
			e.printStackTrace();
		}
		return msg;
	}
	/**
	 * hkf
	 * 根据条件查询列表
	 * 2013.1.9
	 */
	@Override
	public String selectPageByKey(Map map) {
		String msg="";
		String url="party/operatorcs/selectpagebykey";
		ClientUtil cu=new ClientUtil(url);
		try {
			msg=cu.post(url, map).toString();
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
	 * hkf
	 * 删除子账号
	 * 2013.1.9
	 */
	@Override
	public String delete(Map map) {
		String msg="";
		String pmsg="";
		String url="party/operatorcs/delete";
		ClientUtil cu1=new ClientUtil(url);
		String urlp="party/securitypermissioncs/delete";
		ClientUtil cu2=new ClientUtil(urlp);
		try {
			msg=cu1.post(url, map).toString();
			pmsg=cu2.post(urlp, map).toString();
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
	 * hkf
	 * 验证子操作员名
	 * 2013.1.10
	 */
	@Override
	public String checkoperator(Map map) {
		String msg="";
		String url="party/operatorcs/checkoperator";	
		ClientUtil cu=new ClientUtil(url);
		try {
			msg=cu.post(url, map).toString();
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
	 * hkf
	 * 保存操作员
	 * 2013.1.10
	 */
	@Override
	public String save(Map map) {
		String msg="";
		String opmsg="";
		String backmsg="";
		String url="party/operatorcs/save";
		String urlo="party/securitypermissioncs/save";
		String urlsp="party/operatorparametercs/insertOperatorParameter";
		ClientUtil cu1=new ClientUtil(url);
		ClientUtil cu2=new ClientUtil(urlo);
		ClientUtil cu3=new ClientUtil(urlsp);
		
		try {
			msg=cu1.post(url, map).toString();
			opmsg=cu2.post(urlo, map).toString();//落地配操作员权限
			backmsg=cu3.post(urlsp, map).toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WebServiceException e) {
			e.printStackTrace();
		}
		return msg;
	}
	/**
	 * hkf
	 * 根据iperatorid查询操作员
	 * 2013.1.10
	 */
	@Override
	public String selectbyid(Map map) {
		String msg="";
		String securitypermission="";
		String back="";
		String url="party/operatorcs/selectbyoperatorid";
		ClientUtil cu1=new ClientUtil(url);
		String urlo="party/securitypermissioncs/selectPermissionById";
		ClientUtil cu2=new ClientUtil(urlo);
		
		try {
			msg=cu1.post(url, map).toString();
			Map mymap=Json2ObjectUtil.parseJSON2Map(msg);
		    String operator=mymap.get("operator").toString().split("/")[1];
			map.put("operator", operator);
			securitypermission=cu2.post(urlo, map).toString();
		    mymap.put("securitypermission", securitypermission);
		    back=JsonGenerateUtil.map2json(mymap);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WebServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return back;
	}
	/**
	 * hkf
	 * 更新操作员信息
	 * 2013.1.11
	 */
	@Override
	public String updateoperatormsg(Map map) {
		String msg="";
		String pmsg="";
		String url="party/operatorcs/updaterealmessage";
		ClientUtil cu1=new ClientUtil(url);
		String urlo="party/securitypermissioncs/updatePermission";
		ClientUtil cu2=new ClientUtil(urlo);
		try {
			msg=cu1.post(url, map).toString();
			pmsg=cu2.post(urlo, map).toString();
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
	 * hkf
	 * 重置密码
	 * 2013.1.15
	 */
	@Override
	public String updateoldpassword(Map map) {
		String msg="";
		String url="party/operatorcs/updateoldpassword";
		ClientUtil cu=new ClientUtil(url);
		try {
			msg=cu.post(url, map).toString();
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
	 * hkf
	 * 根据partyid查询admin信息
	 * 2013.7.11
	 */
	@Override
	public String selectAdminMsgByPartyid(Map map) {
		String msg="";
		String urlop="party/operatorcs/selectMsgByPartyid";
		ClientUtil cu=new ClientUtil(urlop);
		try {
			msg=cu.post(urlop, map).toString();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WebServiceException e) {
			e.printStackTrace();
		}
		return msg;

	}
	/**
	 * hkf
	 * 验证电话号码的唯一性
	 * 2013.7.11
	 */
	@Override
	public String checkMobileNumber(Map map) {
		String msg="";
		String url="party/operatorcs/checkMobile";
		ClientUtil cu=new ClientUtil(url);
		try {
			msg=cu.post(url, map).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	/**
	 * hkf
	 * 更新admin电话号码
	 * 2013.7.11
	 */
	@Override
	public String updateMobileNumber(Map map) {
		String msg="";
		String url="party/operatorcs/updateMobile";
		ClientUtil cu=new ClientUtil(url);
		try {
			msg=cu.post(url, map).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

}
