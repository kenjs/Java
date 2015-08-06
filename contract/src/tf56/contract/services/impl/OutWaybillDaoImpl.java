package tf56.contract.services.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import tf56.contract.domain.OutGoods;
import tf56.contract.domain.OutWaybill;
import tf56.contract.domain.Waybill;
import tf56.contract.services.OutWaybillDao;
import tf56.sofa.serializer.JsonGenerateUtil;

public class OutWaybillDaoImpl extends SqlMapClientDaoSupport implements OutWaybillDao{

	@Override
	public String saveOutWaybill(OutWaybill outWaybill) throws SQLException{
		String waybillid = "", msg = "ok";
		waybillid = (String) getSqlMapClientTemplate().insert("iBatisInsertOutWaybill", outWaybill);
		return waybillid;
	}
	
	@Override
	public String saveOutGoods(OutGoods outGoods)throws SQLException {
		String goodsid = "", msg = "ok";
		goodsid = (String) getSqlMapClientTemplate().insert("iBatisInsertOutGoods", outGoods);
		return goodsid;
	}
	@Override
	public void deleteOutGoods(Map map)throws SQLException {
		 String msg="ok";
		   int i=getSqlMapClientTemplate().delete("iBatisDeeleteOutGoods", map);
		   if(i<1){
			   msg= "删除失败";
		   }
	}
	/****
	 * 根据clientnumber查waybillid
	 * hcm
	 */
	@Override
	public String selectWaybillIdByClientNumber(Map map) {
		Object id=this.getSqlMapClientTemplate().queryForObject("iBatisSelectWaybillIdByClientNumber", map);
		String i = "";
		if(id!=null){
			int j = (Integer) id;
			i = j+"";
		}
		return i+"";
	}
	/***
     * 修改重复发送客户单号时修改
     * @author hcm
     * @return
     */
    public int updateOutWaybill(OutWaybill outWaybill) throws SQLException{
     	int i = getSqlMapClientTemplate().update("iBatisUpdateOutWayBill",outWaybill);
		return i;
    }

	@Override
	public List selectOutWaybillByPartyId(Map map) {
		List list = new ArrayList();
		try{
			list=this.getSqlMapClientTemplate().queryForList("iBatisSelectOutWaybillByPartyId", map);
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		return list;
	}

	@Override
	public String selectOutWaybillByPartyIdCount(Map map) {
		String count="0";
		try{
			count=(String) this.getSqlMapClientTemplate().queryForObject("iBatisSelectOutWaybillByPartyIdCount", map);
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		return count;
	}

	/**
	 * 根据运单号查找运单
	 * @param map
	 * @return
	 */
	@Override
	public OutWaybill selectOutWaybillByWaybillId(Map map) {
		OutWaybill bean = null;
		try {
			bean = (OutWaybill) this.getSqlMapClientTemplate().queryForObject("iBatisSelectOutWaybillById", map);
		} catch (DataAccessException e) {			
			e.printStackTrace();
		}
		return bean;
	}
	
	/**
	 * @author haoyong
	 * @description 根据运单id查找货物
	 * @date 2014-4-21
	 * @param map
	 * @return
	 */
	public List<OutGoods> selectOutGoodsByWaybillId(Map map) {
		List<OutGoods> list = null;
		try {
			list = this.getSqlMapClientTemplate().queryForList("iBatisSelectOutGoodsByWaybillId", map);
		} catch (DataAccessException e) {			
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public String saveOrderWaybill(OutWaybill waybill) {
		
		String  msg = "ok";
		try {
			int i = getSqlMapClientTemplate().update("iBatisSaveOrderWaybill",waybill);
			if (i != 1) {
				msg = "更新失败";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		String msgJson = JsonGenerateUtil.getMsgIdJson(msg, "");
		return msgJson;
	}
	
}