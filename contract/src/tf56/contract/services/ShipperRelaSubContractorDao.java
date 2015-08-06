package tf56.contract.services;

import java.util.List;
import java.util.Map;

import tf56.contract.domain.ShipperRelaSubContractor;

/****** 类ShipperRelaSubContractor.java 关联分包商 @author lianggui.zhou @date 2013-10-16 *********/
public interface ShipperRelaSubContractorDao {

    /****
     * 总包关联分包商或总包关联发货方
     * 
     * @param partyid
     * @param topartyid
     * @param frompartyid
     * @return
     */
    public String transactionInsertShipperRelaSubContractor(Map map);

    /****
     * 校验总包与分包是否已经关联，
     * 
     * @param partyid 总包partyid
     * @param topartyid 分包商partyid
     * @param frompartyid 发货方partyid
     * @return ok or sorry
     */
    public String checkRealtionExsit(Map map);

    /***
	 * 查询发货方关联的所有分包商id
	 * @author hcm
	 * @date 2014-1-9
	 * @param frompartyid 总包frompartyid
	 * @return 
	 */
    public List<Map> subcontractList(String frompartyId);
    /***
     * 查询总包会员下的分包商或发货方
     * 
     * @author lianggui.zhou
     * @date 2013-10-17
     * @param partyid 总包partyid
     * @return
     */
    public List<Map> contractAndSubcontractList(String partyId, String partytype,String frompartyid,Map map);
    /**
     * 删除总包下的发货方
     * @param map frompartyid(发货方会员id) 和 partyid(总包会员id)
     * @return 是否成功
     * @author donghui.wang
     * @date 2013-10-23
     */
    public String deleteConsigner(Map map);
    
    /**
     * 删除总包下的分包商
     * @param map frompartyid(分包商会员id) 和 partyid(总包会员id)
     * @return 是否成功
     * @author donghui.wang
     * @date 2013-10-23
     */
    public String deleteSubContract(Map map);

    /**
     * 判断总包下的某一个分包商是否存在有发货方关联
     * @param map
     * @return 1-存在 0-不存在
     * @author donghui.wang
     * @date 2013-10-23
     */
    public String isSubContractContainsSender(Map params);
	/****
	 * 总包导入发货方时，关联分包商
	 * @author lianggui.zhou
	 * @date 2013-10-23
	 * @param partyid总包会员partyid,topartyid 分包商partyid,frompartyid发货方partyid,map 附加参数
	 * @return
	 */
	public String transactionContractLinkSubContractor(String partyid,String topartyid,String frompartyid,Map map);
    public List<Map> shipperRelaSubContractorList(String partyid);
    /***
	 * 查询分包商银行保理列表
	 * @author HCM
	 * @date 2014-2-27
	 * @param 
	 * @return 
	 */
	public List<Map> factoringList(Map map);
	/***
	 * 查询分包商银行保理详情
	 * @author HCM
	 * @date 2014-2-28
	 * @param 
	 * @return 
	 */
	public List<Map> factoringDetail(Map map);
	/***
	 * 查询分包商银行保理发货方详情
	 * @author HCM
	 * @date 2014-2-28
	 * @param 
	 * @return 
	 */
	public List<ShipperRelaSubContractor> factoringDetailFhf(Map map);
	/****
	 * 根据frompartyid查partyid
	 * hcm
	 */
	public String selectPartyIdForOutWaybill(Map map);
}
