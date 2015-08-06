package tf56.contract.services;
import java.util.Map;

import tf56.contract.domain.OutWaybill;
public interface OutWaybillService {
	
	/**
	 * 保存订单
	 * hcm
	 * @param map
	 * @return
	 */
	public String transactionSaveOutWaybill(Map map);
	
	/**
	 * 根据运单号查找运单
	 * @param map
	 * @return
	 */
	public String selectOutWaybillByWaybillId(Map map);
	
	public String saveOrderWaybill(OutWaybill waybill);
}
