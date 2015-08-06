package tf56.contract.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tf56.contract.domain.Waybill;
import tf56.contract.domain.WaybillStowageLog;
import tf56.contract.services.WaybillDao;
import tf56.contract.services.WaybillStowageDao;
import tf56.contract.services.WaybillStowageService;
import tf56.sofa.util.ParseFormToBean;
import tf56.sofa.util.SofaSpringContext;

public class WaybillStowageServiceImpl implements WaybillStowageService{
	
	/**
	 * @author haoyong
	 * @date 2013-12-17
	 * @function 发车确认
	 */
	public String carbeginconfirm_save(Map map){
		String msg = "";
		Map waybillLogMap = new HashMap();
		Map waybillStowageLogMap = new HashMap();
		WaybillStowageDao waybillStowageDao = (WaybillStowageDao) SofaSpringContext.getBean("waybillStowageDao");
		WaybillDao waybillDao = (WaybillDao) SofaSpringContext.getBean("waybillDao");
		//修改调度单信息
		msg = waybillStowageDao.updateWaybillStowage(map);
		//修改运单信息
		map.put("waybillstatus","已发车");
		map.put("action", "update");
		waybillStowageDao.updateWaibill(map);
		//记录运单操作日志
		List list = waybillDao.selectWaybillByStowageId(map);
		Waybill w = null;
		if(list != null&&list.size()>0){
			waybillLogMap.put("status", "已发车");
			waybillLogMap.put("trace", "运单已发车");
			waybillLogMap.put("inputDate", map.get("inputdate").toString());
			waybillLogMap.put("inputMan", map.get("inputman").toString());
			for (Object object : list) {
				w = (Waybill) object;
				waybillLogMap.put("waybillid", w.getWaybillid());
				waybillStowageDao.waybillLog(waybillLogMap);
			}
		}
		//记录调度单操作日志
		waybillStowageLogMap.put("waybillstowageid", map.get("waybillstowageid").toString());
		waybillStowageLogMap.put("systemdispatchnumber", map.get("systemdispatchnumber").toString());
		waybillStowageLogMap.put("status", "已发车");
		waybillStowageLogMap.put("trace", "");
		waybillStowageLogMap.put("inputdate", map.get("inputdate").toString());
		waybillStowageLogMap.put("inputman", map.get("inputman").toString());
		WaybillStowageLog bean = new WaybillStowageLog();
		ParseFormToBean pft = new ParseFormToBean();
		bean = (WaybillStowageLog) pft.parseToBean(waybillStowageLogMap, bean);
		waybillStowageDao.insertWaybillStowageLog(bean);
		
		return msg;
	}
	
	/**
	 * @author haoyong
	 * @date 2013-12-17
	 * @function 到车确认
	 */
	public String cararriveconfirm_save(Map formMap){
		String msg = "";
		Map waybillLogMap = new HashMap();
		Map waybillStowageLogMap = new HashMap();
		WaybillStowageDao waybillStowageDao = (WaybillStowageDao) SofaSpringContext.getBean("waybillStowageDao");
		WaybillDao waybillDao = (WaybillDao) SofaSpringContext.getBean("waybillDao");
		//修改调度单信息
		msg = waybillStowageDao.updateWaybillStowage(formMap);
		//修改运单信息
		formMap.put("waybillstatus","已到车");
		formMap.put("action", "update");
		waybillStowageDao.updateWaibill(formMap);
		//记录运单操作日志
		List list = waybillDao.selectWaybillByStowageId(formMap);
		Waybill w = null;
		if(list != null&&list.size()>0){
			waybillLogMap.put("status", "已到车");
			waybillLogMap.put("trace", "运单已到车");
			waybillLogMap.put("inputDate", formMap.get("inputdate").toString());
			waybillLogMap.put("inputMan", formMap.get("inputman").toString());
			for (Object object : list) {
				w = (Waybill) object;
				waybillLogMap.put("waybillid", w.getWaybillid());
				waybillStowageDao.waybillLog(waybillLogMap);
			}
		}
		//记录调度单操作日志
		waybillStowageLogMap.put("waybillstowageid", formMap.get("waybillstowageid").toString());
		waybillStowageLogMap.put("systemdispatchnumber", formMap.get("systemdispatchnumber").toString());
		waybillStowageLogMap.put("status", "已到车");
		waybillStowageLogMap.put("trace", "");
		waybillStowageLogMap.put("inputdate", formMap.get("inputdate").toString());
		waybillStowageLogMap.put("inputman", formMap.get("inputman").toString());
		WaybillStowageLog bean = new WaybillStowageLog();
		ParseFormToBean pft = new ParseFormToBean();
		bean = (WaybillStowageLog) pft.parseToBean(waybillStowageLogMap, bean);
		waybillStowageDao.insertWaybillStowageLog(bean);
		
		return msg;
	}
	
	/**
	 * @author haoyong
	 * @date 2013-12-17
	 * @function 装车确认
	 */
	public String carloadconfirm_save(Map formMap){
		String msg = "";
		Map waybillLogMap = new HashMap();
		Map waybillStowageLogMap = new HashMap();
		WaybillStowageDao waybillStowageDao = (WaybillStowageDao) SofaSpringContext.getBean("waybillStowageDao");
		WaybillDao waybillDao = (WaybillDao) SofaSpringContext.getBean("waybillDao");
		//修改调度单信息
		msg = waybillStowageDao.updateWaybillStowage(formMap);
		//修改运单信息
		formMap.put("waybillstatus","已装车");
		formMap.put("action", "update");
		waybillStowageDao.updateWaibill(formMap);
		//记录运单操作日志
		List list = waybillDao.selectWaybillByStowageId(formMap);
		Waybill w = null;
		if(list != null&&list.size()>0){
			waybillLogMap.put("status", "已装车");
			waybillLogMap.put("trace", "运单已装车");
			waybillLogMap.put("inputDate", formMap.get("inputdate").toString());
			waybillLogMap.put("inputMan", formMap.get("inputman").toString());
			for (Object object : list) {
				w = (Waybill) object;
				waybillLogMap.put("waybillid", w.getWaybillid());
				waybillStowageDao.waybillLog(waybillLogMap);
			}
		}
		//记录调度单操作日志
		waybillStowageLogMap.put("waybillstowageid", formMap.get("waybillstowageid").toString());
		waybillStowageLogMap.put("systemdispatchnumber", formMap.get("systemdispatchnumber").toString());
		waybillStowageLogMap.put("status", "已装车");
		waybillStowageLogMap.put("trace", "");
		waybillStowageLogMap.put("inputdate", formMap.get("inputdate").toString());
		waybillStowageLogMap.put("inputman", formMap.get("inputman").toString());
		WaybillStowageLog bean = new WaybillStowageLog();
		ParseFormToBean pft = new ParseFormToBean();
		bean = (WaybillStowageLog) pft.parseToBean(waybillStowageLogMap, bean);
		waybillStowageDao.insertWaybillStowageLog(bean);
		return msg;
	}
	
	/**
	 * @author haoyong
	 * @date 2013-12-17
	 * @function 调度单删除
	 * @return
	 */
	public String delete_waybillstowage(Map map){
		String msg = "";
		Map waybillLogMap = new HashMap();
		Map waybillStowageLogMap = new HashMap();
		WaybillStowageDao waybillStowageDao = (WaybillStowageDao) SofaSpringContext.getBean("waybillStowageDao");
		WaybillDao waybillDao = (WaybillDao) SofaSpringContext.getBean("waybillDao");
		//删除调度单
		msg = waybillStowageDao.waybillStowageDelete(map);
		//修改运单信息
		map.put("waybillstatus","待配载");
		map.put("action", "delete");
		waybillStowageDao.updateWaibill(map);
		
		//记录运单操作日志
		List list = waybillDao.selectWaybillByStowageId(map);
		Waybill w = null;
		if(list != null&&list.size()>0){
			waybillLogMap.put("status", "待配载");
			waybillLogMap.put("trace", "运单已受理");
			waybillLogMap.put("inputDate", map.get("inputdate").toString());
			waybillLogMap.put("inputMan", map.get("inputman").toString());
			for (Object object : list) {
				w = (Waybill) object;
				waybillLogMap.put("waybillid", w.getWaybillid());
				waybillStowageDao.waybillLog(waybillLogMap);
			}
		}
		
		//记录调度单操作日志
		waybillStowageLogMap.put("waybillstowageid", map.get("waybillstowageid").toString());
		waybillStowageLogMap.put("systemdispatchnumber", map.get("systemdispatchnumber").toString());
		waybillStowageLogMap.put("status", "已删除");
		waybillStowageLogMap.put("trace", "");
		waybillStowageLogMap.put("inputdate", map.get("inputdate").toString());
		waybillStowageLogMap.put("inputman", map.get("inputman").toString());
		WaybillStowageLog bean = new WaybillStowageLog();
		ParseFormToBean pft = new ParseFormToBean();
		bean = (WaybillStowageLog) pft.parseToBean(waybillStowageLogMap, bean);
		waybillStowageDao.insertWaybillStowageLog(bean);
		
		return msg;
	}
}
