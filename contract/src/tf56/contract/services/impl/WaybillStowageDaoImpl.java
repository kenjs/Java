package tf56.contract.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.serfj.client.WebServiceException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import tf56.contract.domain.Waybill;
import tf56.contract.domain.WaybillStowage;
import tf56.contract.domain.WaybillStowageLog;
import tf56.contract.services.OrganizationService;
import tf56.contract.services.SettleDao;
import tf56.contract.services.WaybillStowageDao;
import tf56.sofa.json.Json2ObjectUtil;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.util.ClientUtil;
import tf56.sofa.util.SofaSpringContext;

public class WaybillStowageDaoImpl extends SqlMapClientDaoSupport implements
		WaybillStowageDao {
	private final Logger log = Logger.getLogger("WaybillStowageDaoImpl.java");
	
	public String insert(WaybillStowage waybillStowage) { // 增 实现类
		String waybillstowageid = "", msg = "ok";
		try {
			waybillstowageid = (String) getSqlMapClientTemplate().insert("iBatisInsertWaybillStowage", waybillStowage);
			String[] waybillids = waybillStowage.getWaybillid().split("#");
			for (String waybillid : waybillids) {
				//1.根据id找到运单
				Waybill waybill = new Waybill();
				Map map = new HashMap();
				map.put("waybillid", waybillid);
				waybill = (Waybill) this.getSqlMapClientTemplate().queryForObject("iBatisSelectWaybillById", map);
				//2.修改运单配载单号&状态
				waybill.setWaybillstowageid(waybillstowageid);
				waybill.setStatus("已配载");
				//
				int i = getSqlMapClientTemplate().update("iBatisUpdateWayBill",waybill);
				if (i != 1) {
					msg = "更新失败";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		String msgJson = JsonGenerateUtil.getMsgIdJson(msg, waybillstowageid);
		return msgJson; // 返回json到控制器, json格式:msg:ok|错误信息,id:int值
	}

	/**
	 * nxiianjing
	 * 模糊查询调度单号
	 */
	public List selectSystemdispatchnumberList(Map map) {
		List list = null;
		try {
			list = this.getSqlMapClientTemplate().queryForList("iBatisSelectSystemdispatchnumberList", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 获取配载单id
	 * @author haoyong
	 * @date 2013-12-4
	 * @param map
	 * @return
	 */
	public List selectwaybillstowageidlist(Map map) {
		List list = null;
		try {
			list = this.getSqlMapClientTemplate().queryForList("iBatisSelectWaybillStowageIdList", map);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * @author haoyong
	 * @date 2013-12-16
	 * @function 查询调度单信息
	 */
	public List selectWaybillstowageList(Map map){
		List list = null;
		try {
			list = this.getSqlMapClientTemplate().queryForList("iBatisSelectWaybillStowageList", map);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * @author haoyong
	 * @date 2013-12-16
	 * @function 查询调度单记录数
	 */
	public String selectCount(Map map){
		String count = "";
		try {
			count = this.getSqlMapClientTemplate().queryForObject("iBatisSelectWaybillStowageListSize", map).toString();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * @author haoyong
	 * @date 2013-12-17
	 * @function （装车、到车、发车）确认
	 * @param map
	 * @return
	 */
	public String updateWaybillStowage(Map map){
		String msg = "ok",waybillstowageid="";
		try {
			int res = this.getSqlMapClientTemplate().update("iBatisCarconfirm", map);
			if(res != 1){
				msg = "更新失败";
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		msg = JsonGenerateUtil.getMsgIdJson(msg, waybillstowageid);
		return msg;
	}
	
	/**
	 * @author haoyong
	 * @date 2013-12-17
	 * @function 删除调度单
	 * @param map
	 * @return
	 */
	public String waybillStowageDelete(Map map){
		String msg = "ok",waybillstowageid="";
		try {
			int res = this.getSqlMapClientTemplate().delete("iBatisDeleteStowageById", map);
			if(res != 1){				
				msg="删除失败";
			}
		} catch (DataAccessException e) {
			msg=e.getMessage();
			e.printStackTrace();
		}
		msg = JsonGenerateUtil.getMsgIdJson(msg, waybillstowageid);
		return msg;
	}
	
	/**
	 * @author haoyong
	 * @date 2013-12-17
	 * @function 修改运单信息
	 * @param map
	 */
	public void updateWaibill(Map map){
		this.getSqlMapClientTemplate().update("iBatisUpdateWaybillInfor", map);
	}
	
	/**
	 * @author haoyong
	 * @date 2013-12-18
	 * @function 运单操作日志
	 * @param map
	 */
	public void waybillLog(Map map){
		this.getSqlMapClientTemplate().insert("iBatisInsertWaybillLog", map);
	}
	
	//调度单信息查询
	public List selectStowageList(Map map){
		List<Map> list = null;
        try {
            list = this.getSqlMapClientTemplate().queryForList("iBatisSelectStowageList", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
	}
	
	//根据调度单号，批量装车确认
	public String loadSureByStowageId(Map map){
		String msg = "ok";
		try {
			int i = getSqlMapClientTemplate().update("iBatisLoadSureByStowageId",map);
		} catch (DataAccessException e) {
			e.printStackTrace();
			msg="装车确认失败！";
		}
		String msgJson = JsonGenerateUtil.getMsgJson(msg);
		return msgJson; // 返回json到控制器, json格式:msg:ok|错误信息,id:int值
	}
	
	//根据调度单号，批量确认发车
	public String startSureByStowageId(Map map){
		String msg = "ok";
		try {
			int i = getSqlMapClientTemplate().update("iBatisStartSureByStowageId",map);
		} catch (DataAccessException e) {
			e.printStackTrace();
			msg="确认发车失败！";
		}
		String msgJson = JsonGenerateUtil.getMsgJson(msg);
		return msgJson; // 返回json到控制器, json格式:msg:ok|错误信息,id:int值
	}
	
	//根据调度单号，批量确认到车
	public String arriveSureByStowageId(Map map){
		String msg = "ok";
		try {
			int i = getSqlMapClientTemplate().update("iBatisArriveSureByStowageId",map);
		} catch (DataAccessException e) {
			e.printStackTrace();
			msg="确认到车失败！";
		}
		String msgJson = JsonGenerateUtil.getMsgJson(msg);
		return msgJson; // 返回json到控制器, json格式:msg:ok|错误信息,id:int值
	}
	
	//根据调度单号，批量取消配载
	public String stowageCancelByStowageId(Map map){
		String msg = "ok";
		try {
			int i = getSqlMapClientTemplate().update("iBatisStowageCancelByStowageId",map);
		} catch (DataAccessException e) {
			e.printStackTrace();
			msg="取消配载失败！";
		}
		String msgJson = JsonGenerateUtil.getMsgJson(msg);
		return msgJson; // 返回json到控制器, json格式:msg:ok|错误信息,id:int值
	}
	
	/**
	 * @author haoyong
	 * @date 2013-12-17
	 * @function 调度单详情
	 * @param map
	 */
	public WaybillStowage selectWaybillStowageById(Map map){
		WaybillStowage ws = null;
		List list = this.getSqlMapClientTemplate().queryForList("iBatisSelectWaybillByWaybillStowageId", map);
		log.debug(list.size());
		List logList = this.getSqlMapClientTemplate().queryForList("iBatisSelectWaybillStowageLogByWaybillStowageId", map);
		ws = (WaybillStowage) this.getSqlMapClientTemplate().queryForObject("iBatisSelectWaybillStowageByKey", map);
		OrganizationService organizationService=(OrganizationService)SofaSpringContext.getBean("organizationService");
		String topartyname="";
		if(StringUtils.isNotBlank(ws.getTopartyid())){
			  map.clear();
			  map.put("partyid",ws.getTopartyid());
			  String result1=organizationService.selectOrganizationNameByPartyId(map);
			  List<Map<String, Object>> listOrg1 = Json2ObjectUtil.parseJSON2List(result1);
			  for(Map org:listOrg1){
				  topartyname=org.get("name").toString();
			  }
		 }
		ws.setTopartyname(topartyname);
		ws.setWaybillList(list);
		ws.setWaybillStowageLogList(logList);
		return ws;
	}
	
	/**
	 *  @author haoyong
	 * @date 2013-12-18
	 * @function 插入调度单操作日志
	 * @param bean
	 * @return
	 */
	public String insertWaybillStowageLog(WaybillStowageLog bean){
		String msgJson="ok",logid="";
		try {
			logid = (String) this.getSqlMapClientTemplate().insert("iBatisInsertWaybillStowageLog", bean);
			if("".equals(logid)){
				msgJson = "插入调度单操作日志失败";
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			msgJson=e.getMessage();
		}
		
		return JsonGenerateUtil.getMsgIdJson(msgJson, logid);
	}
	
	   
    /**
     *  @author haoyong
     * @date 2013-12-18
     * @function 插入调度单操作日志
     * @param bean
     * @return
     */
    public WaybillStowage selectWaybillStowageBywaybillid(Map map){
        WaybillStowage ws=null;
        try {
            ws = (WaybillStowage) this.getSqlMapClientTemplate().queryForObject("iBatisSelectWaybillStowageByWaybillid", map);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        
        return ws;
    }

}