package tf56.contract.services;
import java.io.IOException;
import java.util.List;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
public interface WaybillService {
	
	/**
	 * 查询所有发货方
	 * @param map
	 * @return
	 */
	public String FrompartyIdList(Map map);
	
	/**
	 * 查询发货方对象
	 * @param map
	 * @return
	 */
	public String FrompartyIdName(Map map);
	
	/**
	 * 运单与发货方名称组合
	 * @param map
	 * @return
	 */
	public String combiWaybillList(Map map);
	/**
	 * 发货方分包商名称组合 为发货方管理
	 * @param map
	 * @return
	 */
	public String combiWaybillListForFhf(Map map);
	
	public String FrompartyName(Map map);
	/**
	 * 查询运单详情
	 * hcm
	 * @param map
	 * @return
	 */
	public String selectWaybillById(Map formMap);
	
	/**
	 * 查询发货方关联的分包商名称
	 * @author haoyong
	 * @date2013-11-25
	 * @param map
	 * @return
	 */
	public String queyConsignorInfo(Map map);

	/**
	 * 查询运单list
	 * hcm
	 * @param map
	 * @return
	 */
	public String selectWaybillList(Map map);
	
	/**
	 * 查询运单list 为发货方管理
	 * hcm
	 * @param map
	 * @return
	 */
	public String selectWaybillListForFhf(Map map);
	
	/**
	 * nixianjing
	 * 
	 * 获取运单号
	 * @param map
	 * @return
	 */
	public String getWaybillNumberes(Map map);

	
	/**
	 * nixianjing
	 * 
	 * 根据发货方用户名补全
	 * @param map
	 * @return
	 */
	public String getCheckFrompartyid(Map map);
	
	
	/**
	 * nixianjing
	 * 
	 * 根据分包商用户名补全
	 * @param map
	 * @return
	 */
	public String getCheckTopartyid(Map map);
	
	
	/**
	 * nixianjing
	 * 
	 * 获取调度单号
	 * @return
	 */
	public String getSystemdispatchnumber();

	
	/**
	 * @author xianjing.ni
	 * @date 13-12-19
	 * @function 根据调度单查寻运单(包括货物信息)
	 * @param map
	 * @return
	 */
	public List selectWaybillGoodsByStowageId(Map map);

	/**
	 * @author yaoyan.lin
	 * @date 2014-02-18
	 * @function 查询 我的运单
	 * @param params 查询参数
	 * @param response
	 */
	public String selectAllForExportExcel(Map params);
}
