package tf56.contract.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.serfj.RestController;
import net.sf.serfj.annotations.POST;
import tf56.contract.services.ContractAttributeDao;
import tf56.contract.util.SessionUtil;
import tf56.site.domain.SessionBean;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.serializer.JsonResponse;
import tf56.sofa.util.SofaSpringContext;
import tf56.sofa.util.SysUtil;

/***类Contractattribute.java总包扩展表 @author lianggui.zhou @date 2013-10-21***************/
public class Contractattributec extends RestController{
	/**
	 * 总包会员修改分包商基础信息或者总包修改发货方基础信息
	 * @author lianggui.zhou
	 * @date 2013-10-21
	 * @return
	 */
	@POST
	public String updateContractAttribute(){
		Map params=SysUtil.removeFilter(this.getParams());
		String partyid=params.get("partyid").toString();
		String topartyid=params.get("topartyid")==null?params.get("frompartyid").toString():params.get("topartyid").toString();
		String shipperorsubcontractor=params.get("shipperorsubcontractor").toString();
		ContractAttributeDao contractAttributeDao=(ContractAttributeDao)SofaSpringContext.getBean("contractAttributeDao");
		HttpServletResponse response=this.getResponseHelper().getResponse();
		String msg=contractAttributeDao.updateContractAttribute(partyid, topartyid, shipperorsubcontractor,"业务员",params.get("saler").toString(),params);
		return new JsonResponse().responseJson(response, msg);
	}
	
	/**
	 * 根据frompartyid和topartyid查询总包扩展信息
	 * @return
	 * @author donghui.wang
	 * @date 2013-10-23
	 */
	@POST
    public String selectContractAttributeInfo() {
        Map params = SysUtil.removeFilter(this.getParams());
        ContractAttributeDao contractAttributeDao = (ContractAttributeDao) SofaSpringContext.getBean("contractAttributeDao");
        HttpServletResponse response = this.getResponseHelper().getResponse();
        HttpServletRequest request = this.getResponseHelper().getRequest();
        SessionBean sessionBean = SessionUtil.getSession(request);
        params.put("frompartyid", sessionBean.getPartyid());
        List<Map> result = contractAttributeDao.selectContractAttributeList(params);
        String msg = "";
        if(result.isEmpty() || result == null){
            msg = JsonGenerateUtil.getSelfDefinedJson("[{\"attributeName\":\"业务员\",\"attributeValue\":\"\"},{\"attributeName\":\"客户号\",\"attributeValue\":\"\"}]").replace("\\", "");;
        }else{
            msg = JsonGenerateUtil.list2json(result);
        }
        return new JsonResponse().responseJson(response, msg);
    }
	/**
	 * @author wei.huang
	 * @date 2013-12-25
	 * @function 验证客户号是否存在
	 * @return string
	 */
	@POST
	public String selectClientNumberCount(){
		Map params = SysUtil.removeFilter(this.getParams());
        ContractAttributeDao contractAttributeDao = (ContractAttributeDao) SofaSpringContext.getBean("contractAttributeDao");
        HttpServletResponse response = this.getResponseHelper().getResponse();
        String count=contractAttributeDao.selectClientNumberCount(params);
        return new JsonResponse().responseJson(response, count);
	}
}
