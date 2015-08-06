package tf56.contract.services;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import tf56.contract.domain.OutGoods;
import tf56.contract.domain.OutWaybill;
import tf56.contract.domain.Waybill;
public interface OutWaybillDao {
   /**
    * 保存订单
    * hcm
    * @param map
    * @return
    */
	public String saveOutWaybill(OutWaybill outWaybill) throws SQLException;
	/**
	 * 保存货物
	 * @param outGoods
	 * @return
	 */
	public String saveOutGoods(OutGoods outGoods)throws SQLException;
	/**
	 * 根据waybillid删除货物
	 * @param map
	 * @throws SQLException
	 */
	public void deleteOutGoods(Map map)throws SQLException;
	/****
	 * 根据clientnumber查waybillid
	 * hcm
	 */
	public String selectWaybillIdByClientNumber(Map map);
	/***
     * 修改重复发送客户单号时修改
     * @author hcm
     * @return
     */
	public int updateOutWaybill(OutWaybill outWaybill) throws SQLException;
	/**
	 * 
	 * @athor changmeng.liu
	 * @date 2014-4-18
	 * @version 1.0
	 * @function 根据会员ID查询外部运单
	 * @param 
	 * @update
	 */
	public List selectOutWaybillByPartyId(Map map);
	/**
	 * 
	 * @athor changmeng.liu
	 * @date 2014-4-18
	 * @version 1.0
	 * @function 统计记录数分页
	 * @param 
	 * @update
	 */
	public String selectOutWaybillByPartyIdCount(Map map);
	
	/**
	 * 根据运单号查找运单
	 * @param map
	 * @return
	 */
	public OutWaybill selectOutWaybillByWaybillId(Map map);
	
	/**
	 * @author haoyong
	 * @description 根据运单id查找货物
	 * @date 2014-4-21
	 * @param map
	 * @return
	 */
	public List<OutGoods> selectOutGoodsByWaybillId(Map map);
	/**
	 * 
	 * @athor changmeng.liu
	 * @date 2014-4-21
	 * @version 1.0
	 * @function 确认转单
	 * @param 
	 * @update
	 */
	 public String saveOrderWaybill(OutWaybill waybill);//确认转单
}