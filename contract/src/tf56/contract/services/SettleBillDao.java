package tf56.contract.services;

import java.util.List;
import java.util.Map;

import tf56.contract.domain.SettleBill;

/**
 * @author wei.huang
 * @date 2013-11-19
 */
public interface SettleBillDao {
	/**
	 * @author yao.xia
	 * @date 2013-12-19
	 * @function 结算信息
	 * @map{inorout,[settlebillnumber,status,startdate,enddate,inoutpartyid]}
	 */
	public List selectExportList(Map map);
	/**
	 * @author wei.huang
	 * @date 2013-11-19
	 * @function 结算单列表(仅contract部分的数据)
	 * @map{inorout,[settlebillnumber,status,startdate,enddate,inoutpartyid]}
	 */
	public List selectList(Map map);
	/**
	 * @author wei.huang
	 * @date 2013-11-19
	 * @function 结算单列表记录条数
	 * @map{inorout,[settlebillnumber,status,startdate,enddate,inoutpartyid]}
	 */
	public String selectListCount(Map map);
	/**
	 * @author wei.huang
	 * @date 2013-11-20
	 * @function 获取收/发货方的partyid
	 * @param map(inorout=1表示发货方，inorout=0表示分包商;partyid:总包id)
	 */
	public List selectInOutPartyIdList(Map map);
	/**
	 * @author wei.huang
	 * @date 2013-11-20
	 * @function 审核结算单
	 */
	public String updateStatusBySettleBillId(Map map);
	/**
	 * @author wei.huang
	 * @date 2013-11-21
	 * @function 更新实收未收金额
	 * @param map(settlebillid,billamount,status)
	 */
	public String updateAmountBySettleBillId(Map map);
	/***
	 * 查询当前记录中最后一条的结算单号
	 * @author lianggui.zhou
	 * @date 2013-11-19
	 * @param inOrOut:YS or YF
	 * @return
	 */
	public String selectLastSettleBillNumber(String inOrOut);
	/***
	 * 插入结算单
	 * @author lianggui.zhou
	 * @date 2013-11-19
	 * @param settleBill
	 * @return
	 */
	public String insert(SettleBill settleBill);
	/***
	 * 更新结算单
	 * @author lianggui.zhou
	 * @date 2013-11-19
	 * @param settleBill
	 * @return
	 */
	public String update(Map map);

    /***
     * 删除阶结算单
     * 
     * @author lianggui.zhou
     * @date 2013-11-23
     * @param settleBillId
     * @return
     */
	public String delete(String settleBillId);
    /**
     * 根据结算单号查询结算单信息（导出数据用）
     * @author lianggui.zhou
     * @date 2013-11-30
     * @param settleBillId:结算单号，inOrOut:应收or应付
     * @return Map
     */
    public Map exportData(String settleBillNumber,String inOrOut);
}
