package tf56.contract.services;

import java.util.List;
import java.util.Map;

import tf56.contract.domain.WaybillStowage;
import tf56.contract.domain.WaybillStowageLog;

public interface WaybillStowageDao {
	public String insert(WaybillStowage waybillStowage); // 增

	/**
	 * nixianjing
	 * 
	 * 模糊查询调度单号
	 * @param map
	 * @return
	 */
	public List selectSystemdispatchnumberList(Map map);

	
	/**
	 * 获取配载单id
	 * @author haoyong
	 * @date 2013-12-4
	 * @param map
	 * @return
	 */
	public List selectwaybillstowageidlist(Map map);
	
	/**
	 * @author haoyong
	 * @date 2013-12-16
	 * @function 查询调度单信息
	 */
	public List selectWaybillstowageList(Map map);
	
	/**
	 * @author haoyong
	 * @date 2013-12-16
	 * @function 查询调度单记录数
	 */
	public String selectCount(Map map);
	
	/**
	 * @author haoyong
	 * @date 2013-12-17
	 * @function （装车、到车、发车）确认
	 * @param map
	 * @return
	 */
	public String updateWaybillStowage(Map map);
	
	/**
	 * @author haoyong
	 * @date 2013-12-17
	 * @function 删除调度单
	 * @param map
	 * @return
	 */
	public String waybillStowageDelete(Map map);
	
	//调度单信息查询
	public List selectStowageList(Map map);

	//根据调度单号，批量装车确认
	public String loadSureByStowageId(Map map);
	//根据调度单号，批量确认发车
	public String startSureByStowageId(Map map);
	//根据调度单号，批量确认到车
	public String arriveSureByStowageId(Map map);
	//根据调度单号，批量取消配载
	public String stowageCancelByStowageId(Map map);
	
	/**
	 * @author haoyong
	 * @date 2013-12-17
	 * @function 调度单详情
	 * @param map
	 */
	public WaybillStowage selectWaybillStowageById(Map map);
	
	/**
	 * @author haoyong
	 * @date 2013-12-17
	 * @function 修改运单信息
	 * @param map
	 */
	public void updateWaibill(Map map);
	
	/**
	 * @author haoyong
	 * @date 2013-12-18
	 * @function 运单操作日志
	 * @param map
	 */
	public void waybillLog(Map map);
	
	/**
	 *  @author haoyong
	 * @date 2013-12-18
	 * @function 插入调度单操作日志
	 * @param bean
	 * @return
	 */
	public String insertWaybillStowageLog(WaybillStowageLog bean);
	
	/**
	 *  @author haoyong
	 * @date 2013-12-18
	 * @function 插入调度单操作日志
	 * @param bean
	 * @return
	 */
	public WaybillStowage selectWaybillStowageBywaybillid(Map map);

}