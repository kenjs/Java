package tf56.contract.services;

import java.util.Map;
import tf56.contract.domain.WaybillSign;

public interface WaybillSignDao {
	public String insert(WaybillSign waybillSign); // 增

	/**
	 * 根据运单id取bean
	 * hcm
	 * @param map
	 * @return
	 */
	public WaybillSign selectWaybillSignById(Map map);
}