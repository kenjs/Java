package com.cy.dctms.orderCargo.dao.imp;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JApplet;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.bo.ManagerWorkLogInfo;
import com.cy.dctms.common.bo.OrderCargoInfo;
import com.cy.dctms.common.dao.BaseDao;
import com.cy.dctms.common.domain.OrderCargoInfoDomain;
import com.cy.dctms.common.util.FlagChangeName;
import com.cy.dctms.orderCargo.dao.IOrderCargoInfoDao;


public class OrderCargoInfoDaoImp extends BaseDao implements IOrderCargoInfoDao{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@SuppressWarnings("unchecked")
	@Override
	public void queryOrderCargoInfoList(OrderCargoInfoDomain orderCargoInfoDomain) {
		try {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("pageSize", orderCargoInfoDomain.getPageInfo().getPageSize());//每页记录数
			queryMap.put("start", (orderCargoInfoDomain.getPageInfo().getCurPage()-1)*orderCargoInfoDomain.getPageInfo().getPageSize());
			//检索条件
			queryMap.put("id", orderCargoInfoDomain.getId());
			queryMap.put("cargoName", orderCargoInfoDomain.getCargoName());
			queryMap.put("cargoType", orderCargoInfoDomain.getCargoType());
			queryMap.put("cargoWeight", orderCargoInfoDomain.getCargoWeight());
			queryMap.put("cargoCubage", orderCargoInfoDomain.getCargoCubage());
			queryMap.put("requestCarLength", orderCargoInfoDomain.getRequestCarLength());
			queryMap.put("requestCarPlateType", orderCargoInfoDomain.getRequestCarPlateType());
			queryMap.put("requestCarBarType", orderCargoInfoDomain.getRequestCarBarType());
			queryMap.put("freight", orderCargoInfoDomain.getFreight());
			queryMap.put("requestStartTime", orderCargoInfoDomain.getRequestStartTime());
			queryMap.put("requestEndTime", orderCargoInfoDomain.getRequestEndTime());
			queryMap.put("startProvince", orderCargoInfoDomain.getStartProvince());
			queryMap.put("startCity", orderCargoInfoDomain.getStartCity());
			queryMap.put("startCounty", orderCargoInfoDomain.getStartCounty());
			queryMap.put("startTown", orderCargoInfoDomain.getStartTown());
			queryMap.put("endProvince", orderCargoInfoDomain.getEndProvince());
			queryMap.put("endCity", orderCargoInfoDomain.getEndCity());
			queryMap.put("endCounty", orderCargoInfoDomain.getEndCounty());
			queryMap.put("endTown", orderCargoInfoDomain.getEndTown());
			queryMap.put("contactName", orderCargoInfoDomain.getContactName());
			queryMap.put("contactMobilephone", orderCargoInfoDomain.getContactMobilephone());
			queryMap.put("contactTelephone", orderCargoInfoDomain.getContactTelephone());
			queryMap.put("remark", orderCargoInfoDomain.getRemark());
			queryMap.put("deletedFlag", orderCargoInfoDomain.getDeletedFlag());
			queryMap.put("deployUserid", orderCargoInfoDomain.getDeployUserid());
			queryMap.put("modifyUserid", orderCargoInfoDomain.getModifyUserid());
			queryMap.put("companyId", orderCargoInfoDomain.getCompanyId());
			queryMap.put("cargoFlag", orderCargoInfoDomain.getCargoFlag());
			queryMap.put("cargoFlagTime", orderCargoInfoDomain.getCargoFlagTime());
			orderCargoInfoDomain.getPageInfo().setTotalRecords((Integer) queryForObject("query_orderCargo_info_count",queryMap));// 总页数
			
			List<OrderCargoInfoDomain> dataList = (List<OrderCargoInfoDomain>) queryForList("query_orderCargo_info_by_page",queryMap);
			for (OrderCargoInfoDomain domain : dataList) {
				if (StringUtils.isNotBlank(domain.getCargoFlag())&&StringUtils.isNotBlank(domain.getRequestStartTime())) {
					domain.setCargoFlag(FlagChangeName.cargoFlag(domain.getCargoFlag(), domain.getRequestStartTime()));
				}
				if (StringUtils.isNotBlank(domain.getCargoType())) {
					domain.setCargoType(FlagChangeName.cargoType(domain.getCargoType()));
				}
			}
			orderCargoInfoDomain.setDataList(dataList);
		} catch (Exception e) {
			logger.error("query_orderCargoInfo_list",e);
			throw new RuntimeException();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void exportOrderCargoInfo(OrderCargoInfoDomain orderCargoInfoDomain) {
		try {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			//检索条件
			queryMap.put("cargoName", java.net.URLDecoder.decode(orderCargoInfoDomain.getCargoName(),"utf-8"));
			queryMap.put("cargoType", orderCargoInfoDomain.getCargoType());
			queryMap.put("cargoWeight", orderCargoInfoDomain.getCargoWeight());
			queryMap.put("cargoCubage", orderCargoInfoDomain.getCargoCubage());
			queryMap.put("requestCarLength", orderCargoInfoDomain.getRequestCarLength());
			queryMap.put("requestCarPlateType", orderCargoInfoDomain.getRequestCarPlateType());
			queryMap.put("requestCarBarType", orderCargoInfoDomain.getRequestCarBarType());
			queryMap.put("freight", orderCargoInfoDomain.getFreight());
			queryMap.put("requestStartTime", orderCargoInfoDomain.getRequestStartTime());
			queryMap.put("requestEndTime", orderCargoInfoDomain.getRequestEndTime());
			queryMap.put("startProvince", orderCargoInfoDomain.getStartProvince());
			queryMap.put("startCity", orderCargoInfoDomain.getStartCity());
			queryMap.put("startCounty", orderCargoInfoDomain.getStartCounty());
			queryMap.put("startTown", orderCargoInfoDomain.getStartTown());
			queryMap.put("endProvince", orderCargoInfoDomain.getEndProvince());
			queryMap.put("endCity", orderCargoInfoDomain.getEndCity());
			queryMap.put("endCounty", orderCargoInfoDomain.getEndCounty());
			queryMap.put("endTown", orderCargoInfoDomain.getEndTown());
			queryMap.put("contactName", orderCargoInfoDomain.getContactName());
			queryMap.put("contactMobilephone", orderCargoInfoDomain.getContactMobilephone());
			queryMap.put("contactTelephone", orderCargoInfoDomain.getContactTelephone());
			queryMap.put("remark", orderCargoInfoDomain.getRemark());
			queryMap.put("deletedFlag", orderCargoInfoDomain.getDeletedFlag());
			queryMap.put("deployUserid", orderCargoInfoDomain.getDeployUserid());
			queryMap.put("modifyUserid", orderCargoInfoDomain.getModifyUserid());
			queryMap.put("companyId", orderCargoInfoDomain.getCompanyId());
			queryMap.put("cargoFlag", orderCargoInfoDomain.getCargoFlag());
			queryMap.put("cargoFlagTime", orderCargoInfoDomain.getCargoFlagTime());
			List<OrderCargoInfoDomain> dataList = (List<OrderCargoInfoDomain>) queryForList("export_orderCargo_info",queryMap);
			for (OrderCargoInfoDomain domain : dataList) {
				if (StringUtils.isNotBlank(domain.getCargoFlag())&&StringUtils.isNotBlank(domain.getRequestStartTime())) {
					domain.setCargoFlag(FlagChangeName.cargoFlag(domain.getCargoFlag(), domain.getRequestStartTime()));
				}
				if (StringUtils.isNotBlank(domain.getCargoType())) {
					domain.setCargoType(FlagChangeName.cargoType(domain.getCargoType()));
				}
			}
			orderCargoInfoDomain.setDataList(dataList);
		} catch (Exception e) {
			logger.error("export_orderCargoInfo_list",e);
			throw new RuntimeException();
		}
	}
	
	@Override
	public OrderCargoInfoDomain queryOrderCargoInfoById(String id) {
		try {
			return (OrderCargoInfoDomain) queryForObject("query_orderCargo_info_by_id", id);
		} catch (Exception e) {
			logger.error("query_orderCargoInfo_by_id",e);
			throw new RuntimeException();
		}
	}
	
	@Override
	public void saveOrderCargoInfo(OrderCargoInfoDomain orderCargoInfoDomain,String userId) {
		try {
			OrderCargoInfo bo = new OrderCargoInfo();
			bo.setCargoName(java.net.URLDecoder.decode(orderCargoInfoDomain.getCargoName(),"utf-8"));
			bo.setCargoType(Long.valueOf(orderCargoInfoDomain.getCargoType()));
			bo.setCargoWeight(Double.valueOf(orderCargoInfoDomain.getCargoWeight()));
			bo.setCargoCubage(Double.valueOf(orderCargoInfoDomain.getCargoCubage()));
			bo.setRequestCarLength(java.net.URLDecoder.decode(orderCargoInfoDomain.getRequestCarLength(),"utf-8"));
			bo.setRequestCarPlateType(java.net.URLDecoder.decode(orderCargoInfoDomain.getRequestCarPlateType(),"utf-8"));
			bo.setRequestCarBarType(java.net.URLDecoder.decode(orderCargoInfoDomain.getRequestCarBarType(),"utf-8"));
			bo.setFreight(Double.valueOf(orderCargoInfoDomain.getFreight()));
			bo.setRequestStartTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(java.net.URLDecoder.decode(orderCargoInfoDomain.getRequestStartTime(),"utf-8")));
			bo.setRequestEndTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(java.net.URLDecoder.decode(orderCargoInfoDomain.getRequestEndTime(),"utf-8")));
			bo.setStartProvince(java.net.URLDecoder.decode(orderCargoInfoDomain.getStartProvince(),"utf-8"));
			bo.setStartCity(java.net.URLDecoder.decode(orderCargoInfoDomain.getStartCity(),"utf-8"));
			bo.setStartCounty(java.net.URLDecoder.decode(orderCargoInfoDomain.getStartCounty(),"utf-8"));
			bo.setStartTown(java.net.URLDecoder.decode(orderCargoInfoDomain.getStartTown(),"utf-8"));
			bo.setEndProvince(java.net.URLDecoder.decode(orderCargoInfoDomain.getEndProvince(),"utf-8"));
			bo.setEndCity(java.net.URLDecoder.decode(orderCargoInfoDomain.getEndCity(),"utf-8"));
			bo.setEndCounty(java.net.URLDecoder.decode(orderCargoInfoDomain.getEndCounty(),"utf-8"));
			bo.setEndTown(java.net.URLDecoder.decode(orderCargoInfoDomain.getEndTown(),"utf-8"));
			bo.setContactName(java.net.URLDecoder.decode(orderCargoInfoDomain.getContactName(),"utf-8"));
			bo.setContactMobilephone(java.net.URLDecoder.decode(orderCargoInfoDomain.getContactMobilephone(),"utf-8"));
			bo.setContactTelephone(java.net.URLDecoder.decode(orderCargoInfoDomain.getContactTelephone(),"utf-8"));
			bo.setRemark(java.net.URLDecoder.decode(orderCargoInfoDomain.getRemark(),"utf-8"));
			bo.setDeletedFlag(Long.valueOf(orderCargoInfoDomain.getDeletedFlag()));
			bo.setDeployUserid(Long.valueOf(orderCargoInfoDomain.getDeployUserid()));
			bo.setModifyUserid(Long.valueOf(orderCargoInfoDomain.getModifyUserid()));
			bo.setCompanyId(Long.valueOf(orderCargoInfoDomain.getCompanyId()));
			bo.setCargoFlag(Long.valueOf(orderCargoInfoDomain.getCargoFlag()));
			bo.setCargoFlagTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(java.net.URLDecoder.decode(orderCargoInfoDomain.getCargoFlagTime(),"utf-8")));
			if (!orderCargoInfoDomain.getId().equals("0")) {
				bo.setId(Long.valueOf(orderCargoInfoDomain.getId()));
				saveObject("modify_orderCargo_info", bo);
				 //添加操作日志
			 	ManagerWorkLogInfo MWLIbo = new ManagerWorkLogInfo();
			 	MWLIbo.setName("修改货物信息信息");
			 	MWLIbo.setColumnId(Long.valueOf(orderCargoInfoDomain.getId()));
			 	String content = "";
			 	content = content + "cargoName:"+bo.getCargoName() +";";
			 	content = content + "cargoType:"+bo.getCargoType() +";";
			 	content = content + "cargoWeight:"+bo.getCargoWeight() +";";
			 	content = content + "cargoCubage:"+bo.getCargoCubage() +";";
			 	content = content + "requestCarLength:"+bo.getRequestCarLength() +";";
			 	content = content + "requestCarPlateType:"+bo.getRequestCarPlateType() +";";
			 	content = content + "requestCarBarType:"+bo.getRequestCarBarType() +";";
			 	content = content + "freight:"+bo.getFreight() +";";
			 	content = content + "requestStartTime:"+bo.getRequestStartTime() +";";
			 	content = content + "requestEndTime:"+bo.getRequestEndTime() +";";
			 	content = content + "startProvince:"+bo.getStartProvince() +";";
			 	content = content + "startCity:"+bo.getStartCity() +";";
			 	content = content + "startCounty:"+bo.getStartCounty() +";";
			 	content = content + "startTown:"+bo.getStartTown() +";";
			 	content = content + "endProvince:"+bo.getEndProvince() +";";
			 	content = content + "endCity:"+bo.getEndCity() +";";
			 	content = content + "endCounty:"+bo.getEndCounty() +";";
			 	content = content + "endTown:"+bo.getEndTown() +";";
			 	content = content + "contactName:"+bo.getContactName() +";";
			 	content = content + "contactMobilephone:"+bo.getContactMobilephone() +";";
			 	content = content + "contactTelephone:"+bo.getContactTelephone() +";";
			 	content = content + "remark:"+bo.getRemark() +";";
			 	content = content + "deletedFlag:"+bo.getDeletedFlag() +";";
			 	content = content + "deployUserid:"+bo.getDeployUserid() +";";
			 	content = content + "modifyUserid:"+bo.getModifyUserid() +";";
			 	content = content + "companyId:"+bo.getCompanyId() +";";
			 	content = content + "cargoFlag:"+bo.getCargoFlag() +";";
			 	content = content + "cargoFlagTime:"+bo.getCargoFlagTime() +";";
			 	MWLIbo.setContent(content);
			 	MWLIbo.setManagerId(Long.valueOf(userId));
			 	MWLIbo.setTableName("t_order_cargo_info");
				addObject("add_workLog_info", MWLIbo);
			}else {
				addObject("add_orderCargo_info", bo);
				 //添加操作日志
			 	ManagerWorkLogInfo MWLIbo = new ManagerWorkLogInfo();
			 	MWLIbo.setName("添加货物信息信息");
			 	MWLIbo.setColumnId(Long.valueOf(orderCargoInfoDomain.getId()));
			 	String content = "";
			 	content = content + "cargoName:"+bo.getCargoName() ;
			 	content = content + "cargoType:"+bo.getCargoType() ;
			 	content = content + "cargoWeight:"+bo.getCargoWeight() ;
			 	content = content + "cargoCubage:"+bo.getCargoCubage() ;
			 	content = content + "requestCarLength:"+bo.getRequestCarLength() ;
			 	content = content + "requestCarPlateType:"+bo.getRequestCarPlateType() ;
			 	content = content + "requestCarBarType:"+bo.getRequestCarBarType() ;
			 	content = content + "freight:"+bo.getFreight() ;
			 	content = content + "requestStartTime:"+bo.getRequestStartTime() ;
			 	content = content + "requestEndTime:"+bo.getRequestEndTime() ;
			 	content = content + "startProvince:"+bo.getStartProvince() ;
			 	content = content + "startCity:"+bo.getStartCity() ;
			 	content = content + "startCounty:"+bo.getStartCounty() ;
			 	content = content + "startTown:"+bo.getStartTown() ;
			 	content = content + "endProvince:"+bo.getEndProvince() ;
			 	content = content + "endCity:"+bo.getEndCity() ;
			 	content = content + "endCounty:"+bo.getEndCounty() ;
			 	content = content + "endTown:"+bo.getEndTown() ;
			 	content = content + "contactName:"+bo.getContactName() ;
			 	content = content + "contactMobilephone:"+bo.getContactMobilephone() ;
			 	content = content + "contactTelephone:"+bo.getContactTelephone() ;
			 	content = content + "remark:"+bo.getRemark() ;
			 	content = content + "deletedFlag:"+bo.getDeletedFlag() ;
			 	content = content + "deployUserid:"+bo.getDeployUserid() ;
			 	content = content + "modifyUserid:"+bo.getModifyUserid() ;
			 	content = content + "companyId:"+bo.getCompanyId() ;
			 	content = content + "cargoFlag:"+bo.getCargoFlag() ;
			 	content = content + "cargoFlagTime:"+bo.getCargoFlagTime() ;
			 	MWLIbo.setManagerId(Long.valueOf(userId));
			 	MWLIbo.setTableName("t_order_cargo_info");
				addObject("add_workLog_info", MWLIbo);
			}
		} catch (Exception e) {
			logger.error("save_orderCargoInfo",e);
			throw new RuntimeException();
		}
	}
	
	@Override
	public void deleteOrderCargoInfo(String id ,String userId) {
		try {
			 deleteObject("delete_orderCargo_info_by_id", id);
			 //添加操作日志
		 	ManagerWorkLogInfo MWLIbo = new ManagerWorkLogInfo();
		 	MWLIbo.setName("删除货物信息信息");
		 	MWLIbo.setColumnId(Long.valueOf(id));
		 	MWLIbo.setContent("id:"+id);
		 	MWLIbo.setManagerId(Long.valueOf(userId));
		 	MWLIbo.setTableName("t_order_cargo_info");
			addObject("add_workLog_info", MWLIbo);
		} catch (Exception e) {
			logger.error("delete_orderCargoInfo",e);
			throw new RuntimeException();
		}
		
	}
}
