package com.cy.driver.receipt.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.common.util.DateUtil;
import com.cy.driver.cargo.service.OrderCargoInfoService;
import com.cy.driver.operationLog.service.OperationLogService;
import com.cy.driver.transaction.service.TransactionInfoService;
import com.cy.driver.webUser.domain.WebUserInfoDomain;

/**
 * 货单确认
 * @author haoyong
 *
 */
public class ReceiptVerifyAction extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2549552208624052914L;
	private Logger log = LoggerFactory.getLogger(getClass());
	private TransactionInfoService transactionInfoService;
	private OperationLogService operationLogService;
	private OrderCargoInfoService orderCargoInfoService;

	@Override
	protected void execMethod() throws Exception {
		try {
			String driverId = request.getParameter("driverId");
			String deployUserId = request.getParameter("deployUserId");
			String transactionId = request.getParameter("transactionId");
			String shipperCompanyCode = request.getParameter("shipperCompanyCode");
			String shipperCompanyId = request.getParameter("shipperCompanyId");
			
			if(StringUtils.isBlank(driverId)) {
				log.info("司机不存在");
				sendResponseToJson("-9", "司机不存在");
				return;
			}
			int accFlag = operationLogService.checkUser(driverId);
			if(accFlag == 1) {
				log.info("该用户不存在或已被删除");
				sendResponseToJson("-9","该用户不存在或已被删除");
				return;
			} else if(accFlag == 11) {
				log.info("该用户已被冻结");
				sendResponseToJson("-9","该用户已被冻结");
				return;
			}
			log2Db(driverId);
			
			if(StringUtils.isBlank(transactionId)) {
				sendResponseToJson("-8", "交易ID不能为空. ");
				return;
			}

			WebUserInfoDomain domain = null;//公司信息domain
			
			if(StringUtils.isNotBlank(deployUserId) && StringUtils.isNotBlank(shipperCompanyCode)) {
				domain = transactionInfoService.selectShipperCompanyId(deployUserId, shipperCompanyCode);
			}
			
			//如果订单已经完成或取消或已卸货或异常取消或web端取消, 无法继续操作
			Map<String,Long> mapStart = transactionInfoService.selectTransactionStartById(transactionId);
			if(mapStart != null) {
				if(mapStart.containsKey("tradeStart")) {
					long start = mapStart.get("tradeStart");
					if(start == 5) {
						log.warn("id为" + transactionId + "的交易已经完成，无法取消");
						sendResponseToJson("0", "该交易已经完成。");
						return;
					}
					if(start == 6) {
						log.warn("id为" + transactionId + "的交易已经取消");
						sendResponseToJson("0", "该交易已经取消");
						return;
					}
					if(start == 7) {
						log.warn("id为" + transactionId + "的交易司机已卸货，无法取消");
						sendResponseToJson("0", "该交易司机已卸货。");
						return;
					}
				}
				if(mapStart.containsKey("tradeCancelOrigin")) {
					long origin = mapStart.get("tradeCancelOrigin");
					if(origin == 1) {
						log.warn("id为" + transactionId + "的交易已经被司机取消");
						sendResponseToJson("0", "该交易已经被司机取消");
						return;
					}
					if(origin == 2 ) {
						log.warn("id为" + transactionId + "的交易已经被货主取消");
						sendResponseToJson("0", "该交易已经被货主取消");
						return;
					}
				}
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", transactionId);
			map.put("driverId", driverId);
			map.put("tradeStart", "3");
			map.put("shipperCompanyCode", shipperCompanyCode);
			
			if(domain != null && StringUtils.isBlank(shipperCompanyId)) {
				map.put("contactName", domain.getContactName());
				map.put("contactTelephone", domain.getContactTelephone());
				shipperCompanyId = domain.getCompanyId();
			}
			
			map.put("shipperCompanyId", shipperCompanyId);
			
			int result = transactionInfoService.updateTransactionInfoById(map);
			if(result == 0){
				sendResponseToJson("0", "货单确认失败");
				log.info("货单确认失败.");
			}else{
				String requestStartTime = DateUtil.getCurrentDateTime();				
				map.put("cargoFlag", "1");
				map.put("requestStartTime", requestStartTime);
				orderCargoInfoService.updateCargoInfo(map);
				sendResponseToJson("1", "货单确认成功");
				log.info("货单确认成功.");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			sendResponseToJson("-8", e.getMessage());
			e.printStackTrace();
		}
	}

	private void log2Db(String driverId) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("receiptVerify");
		bo.setOperationType(67);
		bo.setRemark("货单确认");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}

	public void setTransactionInfoService(
			TransactionInfoService transactionInfoService) {
		this.transactionInfoService = transactionInfoService;
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}

	public void setOrderCargoInfoService(OrderCargoInfoService orderCargoInfoService) {
		this.orderCargoInfoService = orderCargoInfoService;
	}
}
