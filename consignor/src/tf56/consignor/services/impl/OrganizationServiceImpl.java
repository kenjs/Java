package tf56.consignor.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.serfj.client.WebServiceException;
import tf56.consignor.services.OrganizationService;
import tf56.sofa.json.Json2ObjectUtil;
import tf56.sofa.util.ClientUtil;

public class OrganizationServiceImpl implements OrganizationService{
    /**
     * 根据合同签订双方名称查询partyId
     * @author lianggui.zhou
     * @param organizationName
     * @return OrganizationId
     * @since 2013年9月16日
     */
    public String selecteOrganizationIdByName(String organizationName){
    	Map map=new HashMap();
    	map.put("organizationName", organizationName);
    	String url = "party/organizationcs/selecteOrganizationIdByName";
    	ClientUtil cu=new ClientUtil(url);
    	String msg="";
    	try {
			msg=cu.post(url,map).toString();
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
     * 获取发货方信息
     * @author wei.huang
     * @return 发货方信息
     * @since 2013年9月30日
     */
    public String getConsignorInf(Map map){
    	String msg="";
    	String url = "party/organizationcs/getConsignor";
        ClientUtil cu=new ClientUtil(url);
    	try {
			msg=cu.post(url,map).toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WebServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
    }
    public String queryOrgName(Map map){
		String url="party/organizationcs/queryOrgName";
        ClientUtil cu=new ClientUtil(url);
		String msg="ok";
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
    public String selectOrganizationNameByPartyId(Map map){
    	String url="party/organizationcs/selectOrganizationNameByPartyid";
        ClientUtil cu=new ClientUtil(url);
        String msg="";
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
    /***
     * 根据组织名或者备注查询满足条件的组织
     * @author lianggui.zhou
     * @param map(organization,description)
     * @return description,partyId,orgName
     */
    public List queryDecription(Map map){
        String url="party/organizationcs/queryDecription";
        ClientUtil cu=new ClientUtil(url);
        String msg="";
        List list=new ArrayList();
        try {
            msg=cu.post(url, map).toString();
            list=Json2ObjectUtil.parseJSON2List(msg);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (WebServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }
}
