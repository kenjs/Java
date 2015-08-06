package tf56.contract.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.serfj.RestController;
import net.sf.serfj.annotations.POST;
import tf56.contract.services.ShipperRelaSubContractorDao;
import tf56.contract.util.SessionUtil;
import tf56.site.domain.SessionBean;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.serializer.JsonResponse;
import tf56.sofa.util.SofaSpringContext;
import tf56.sofa.util.SysUtil;
/*****类Shipperrelasubontractorc.java 关联分包商 @author lianggui.zhou @date 2013-10-16***********/
public class Shipperrelasubontractorc extends RestController{

	/****
	 * 校验总包与分包是否已经关联，
	 * @param partyid 总包partyid
	 * @param topartyid 分包商partyid
	 * @param frompartyid 发货方partyid
	 * @return ok or sorry
	 */
	@POST
	public String checkRealtionExsit(){
		Map params=SysUtil.removeFilter(this.getParams());
		ShipperRelaSubContractorDao shipperRelaSubContractorDao=(ShipperRelaSubContractorDao)SofaSpringContext
																.getBean("shipperRelaSubContractorDao");
		String msg=shipperRelaSubContractorDao.checkRealtionExsit(params);
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msg);
	}
	/****
	 * 关联总包-->分包或总包-->发货方
	 * @author lianggui.zhou
	 * @date 2013-10-17
	 * 
	 * @return
	 */
	@POST
	public String insertShipperRelaSubContractor(){
		Map params=SysUtil.removeFilter(this.getParams());
		ShipperRelaSubContractorDao shipperRelaSubContractorDao=(ShipperRelaSubContractorDao)SofaSpringContext
																.getBean("shipperRelaSubContractorDao");
		String msg=shipperRelaSubContractorDao.transactionInsertShipperRelaSubContractor(params);
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, msg);
	}
	/***
	 * 查询总包会员下的分包商或发货方
	 * @author lianggui.zhou
	 * @date 2013-10-17
	 * @param partyid 总包partyid
	 * @return 
	 */
	@POST
	public String contractAndSubcontractList(){
		Map params=SysUtil.removeFilter(this.getParams());
		ShipperRelaSubContractorDao shipperRelaSubContractorDao=(ShipperRelaSubContractorDao)SofaSpringContext
																.getBean("shipperRelaSubContractorDao");
		List<Map> list=shipperRelaSubContractorDao.contractAndSubcontractList(params.get("partyid").toString(),params.get("partytype").toString(),params.get("frompartyid")==null?"":params.get("frompartyid").toString(),new HashMap());
		HttpServletResponse response=this.getResponseHelper().getResponse();
		return new JsonResponse().responseJson(response, JsonGenerateUtil.getListJson(list));
	}
	/****
	 * 总包导入发货方时，关联分包商
	 * @author lianggui.zhou
	 * @date 2013-10-23
	 * @return
	 */
	@POST
	public String contractLinkSubContractor(){
		Map params=SysUtil.removeFilter(this.getParams());
		String partyid=params.get("partyid").toString();
		String topartyid=params.get("topartyid").toString();
		String frompartyid=params.get("frompartyid").toString();
		ShipperRelaSubContractorDao shipperRelaSubContractorDao=(ShipperRelaSubContractorDao)SofaSpringContext
																.getBean("shipperRelaSubContractorDao");
		HttpServletResponse response=this.getResponseHelper().getResponse();
		String msg=shipperRelaSubContractorDao.transactionContractLinkSubContractor(partyid, topartyid, frompartyid, params);
		
		return new JsonResponse().responseJson(response, msg);
	}
	/**
     * 删除总包下的发货方
     * @author donghui.wang
     * @date 2013-10-23
     */
	@POST
	public String deleteConsignor(){
	    Map params=SysUtil.removeFilter(this.getParams());
        ShipperRelaSubContractorDao shipperRelaSubContractorDao=(ShipperRelaSubContractorDao)SofaSpringContext
                                                                .getBean("shipperRelaSubContractorDao");
        String result = shipperRelaSubContractorDao.deleteConsigner(params);
        
        HttpServletResponse response=this.getResponseHelper().getResponse();
        return new JsonResponse().responseJson(response, result);
	}

    /**
     * 删除总包下的分包商
     * @author donghui.wang
     * @date 2013-10-23
     */
    @POST
    public String deleteSubContract(){
        HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
        Map params=SysUtil.removeFilter(this.getParams());
        ShipperRelaSubContractorDao shipperRelaSubContractorDao=(ShipperRelaSubContractorDao)SofaSpringContext
                                                                .getBean("shipperRelaSubContractorDao");
        params.put("partyid", sessionBean.getPartyid());
        String result = shipperRelaSubContractorDao.deleteSubContract(params);
        
        HttpServletResponse response=this.getResponseHelper().getResponse();
        return new JsonResponse().responseJson(response, JsonGenerateUtil.getMsgJson(result));
    }
    
    /**
     * 判断总包下的某一个分包商是否存在有发货方关联
     * @return
     * @author donghui.wang
     * @date 2013-10-23
     */
    @POST
    public String isSubContractContainsSender() {
        HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
        Map params = SysUtil.removeFilter(this.getParams());
        ShipperRelaSubContractorDao shipperRelaSubContractorDao = (ShipperRelaSubContractorDao) SofaSpringContext.getBean("shipperRelaSubContractorDao");
        params.put("partyid", sessionBean.getPartyid());
        String result = shipperRelaSubContractorDao.isSubContractContainsSender(params);
        HttpServletResponse response = this.getResponseHelper().getResponse();
        return new JsonResponse().responseJson(response, result);
    }

}
