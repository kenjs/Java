package tf56.contract.services;

import java.util.List;
import java.util.Map;

public interface PartyService {

    public String checkPartyName(String partyname);//

    public String checkEmail(String email);

    public String chkLogin(String username, String password);// 验证用户登陆

    public String checkMobilenumber(String mobileInfo);

    public String insert(Map map); // 新插入数据

    public String selectByPartyid(Map map);

    public String selectpersonByPartyid(Map map);

    public String selectorganizationByPartyid(Map map);

    public String updateemail(Map map);

    public String updatemobilenumber(Map map);

    public String updatePassword(Map map);

    public String selectRelationShipByPartyid(Map map);

    public String selectPartyTypeAndPartyNameByIdList(Map map);

    public String selectRealnameByPartyid(Map map);

    public String selectOrganizationNameByPartyid(Map map);

    public String selectFromOrganization(Map map);

    public String selectPermissionByOperatorAndPartyid(Map map);

    public String selectCarDriverMsgByPartyId(Map map);

    public String checkDriverMobileNumber(String mobileInfo);

    public String updateDriverMobileNumber(Map map);

    public String updateDriverTelephoneNumber(Map map);

    /***
     * 分包商列表
     * 
     * @author lianggui.zhou
     * @date 2013-09-10
     * @param map
     * @return
     */
    public String subcontractorList(Map map);

    /***
     * 分包商详情
     * 
     * @author lianggui.zhou
     * @date 2013-09-10
     * @param map
     * @return
     */
    public String querySubcontractorInfo(Map map);

    /**
     * 分包商修改之附件添加
     * 
     * @author lianggui.zhou
     * @return
     */
    public String addSubontractorAppdix(Map map);

    /***
     * 增加分包商时修改会员信息
     * 
     * @author lianggui.zhou
     * @date 2013-10-16
     * @param partyid
     * @return
     */
    public String updatePartyInfo(Map params);

    /**
     * 总包会员修改分包商基础信息或者总包修改发货方基础信息
     * 
     * @author lianggui.zhou
     * @date 2013-10-21
     * @return
     */
    public String conUpdateSubconBasicInfo(Map params);

    /***
     * 查询总包会员下的分包商或发货方
     * 
     * @author lianggui.zhou
     * @date 2013-10-17
     * @param partyid 总包partyid
     * @return
     */
    public String contractAndSubcontractList(String partyid, String partytype, Map params);

    /**
     * wei.huang 2013-11-06
     * @param map获取城区间距离列表
     * @return
     */
    public String cityDistanceList(Map map);
    /**
	 * 查询指定发货方下的城区间距离列表
	 * @author wei.huang
	 **/
	public String cityDistanceListByFromAddressAndToAddress(Map map);
	/**
	 * @author wei.huang
	 * @date 2013-11-19
	 * @function 结算单列表（含party部分的数据）
	 * @map{inorout,[settlebillnumber,status,startdate,enddate,inoutpartyid]}
	 * @return
	 */
	public String settleBillList(Map map);

	/**
	 * @author yaoyan.lin
	 * @date 2013-11-20
	 * @function 查询指定发货方下的结算设置列表
	 * @map{inorout,[settlebillnumber,status,startdate,enddate,inoutpartyid]}
	 * @return
	 */
	public String settleSetList(Map params);
	/**
	 * @author wei.huang
	 * @date 2013-11-27
	 * @function 将发货方和分包商的名称合并到waybillList中
	 * @param map
	 * @return
	 */
	public List addConsignorConsigneeToWaybillListForReport(Map map);
	/**
	 * @author wei.huang
	 * @date 2013-11-27
	 * @function 将组织名称合并到总包层面的统计数据中
	 * @param list 待添加名称的list
	 * @param partyIdList 指定形式的partyid字符串
	 * @return
	 */
	public List addOrganizationForStatistic_Contract(List<Map> list,String partyIdList);
	/**
	 * @author wei.huang
	 * @date 2013-11-27
	 * @function 将组织名称合并到发货方层面的统计数据中
	 * @param list 待添加名称的list
	 * @param partyIdList 指定形式的frompartyid字符串
	 * @return
	 */
	public List addOrganizationForStatistic_Consignor(List<Map> list,String fromPartyIdList);
	 
	/***
     * 分包商列表
     * @author hcm
     * @date 2014-2-27
     * @param map
     * @return
     */
    public String factoringList(Map map);

    /**
	 *  @author yaoyan.lin
	 * @date 2014-04-16
	 * @function 提交配载生成的XML
	 * @param map
	 * @return
	 */
	public String waybillStowageCommit(Map map);
}
