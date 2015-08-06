package tf56.contract.services;

import java.util.Map;

public interface WaybillStowageService {

	/**
	 * @author haoyong
	 * @date 2013-12-17
	 * @function 发车确认
	 */
	public String carbeginconfirm_save(Map map);
	
	/**
	 * @author haoyong
	 * @date 2013-12-17
	 * @function 到车确认
	 */
	public String cararriveconfirm_save(Map formMap);
	
	/**
	 * @author haoyong
	 * @date 2013-12-17
	 * @function 装车确认
	 */
	public String carloadconfirm_save(Map formMap);
	

	/**
	 * @author haoyong
	 * @date 2013-12-17
	 * @function 调度单删除
	 * @return
	 */
	public String delete_waybillstowage(Map map);
}
