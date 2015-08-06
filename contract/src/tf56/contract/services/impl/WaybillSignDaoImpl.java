package tf56.contract.services.impl;

import java.util.HashMap;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import tf56.contract.domain.Waybill;
import tf56.contract.domain.WaybillSign;
import tf56.contract.services.WaybillSignDao;
import tf56.sofa.serializer.JsonGenerateUtil;

public class WaybillSignDaoImpl extends SqlMapClientDaoSupport implements
		WaybillSignDao {
	public String insert(WaybillSign waybillSign) { // 增 实现类
		String waybillsignid = "", msg = "ok";
		try {
			
			String waybillid = waybillSign.getWaybillid();
			//1.根据id找到运单
			Waybill waybill = new Waybill();
			Map map = new HashMap();
			map.put("waybillid", waybillid);
			waybill = (Waybill) this.getSqlMapClientTemplate().queryForObject("iBatisSelectWaybillById", map);
			//2.设置签收表运单号，添加签收记录
			waybillSign.setWaybillnumber(waybill.getWaybillnumber());
			waybillsignid = (String) getSqlMapClientTemplate().insert("iBatisInsertWaybillSign", waybillSign);
			//3.修改&状态
			waybill.setStatus("已签收");
			int i = getSqlMapClientTemplate().update("iBatisUpdateWayBill",waybill);
			if (i != 1) {
				msg = "签收失败";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		String msgJson = JsonGenerateUtil.getMsgIdJson(msg, waybillsignid);
		return msgJson; // 返回json到控制器, json格式:msg:ok|错误信息,id:int值
	}
	
	public WaybillSign selectWaybillSignById(Map map) { // 查询数据到Bean,然后转成map
		WaybillSign waybillSign = new WaybillSign();
		try {
			waybillSign = (WaybillSign) this.getSqlMapClientTemplate().queryForObject(
							"iBatisSelectWaybillSignByWaybillId", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return waybillSign;
	}
	
}