package com.cy.dctms.service.impl;

import com.cy.dctms.common.util.DateUtils;
import com.cy.dctms.dao.OrderCarogoInfoDao;
import com.cy.dctms.service.OrderCarogoInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
@Service("orderCarogoInfoService")
public class OrderCarogoInfoServiceImpl implements OrderCarogoInfoService {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Resource
	private OrderCarogoInfoDao orderCarogoInfoDao;

	@Override
	public void updateOrderCargo() throws SQLException {

        //保存失效货源的表名
        String tableName = "t_order_cargo_history_" + DateUtils.getYearAndMonth();

        int month = DateUtils.getMonth();

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("startTime", DateUtils.getFirstDayOfMonth() + " 00:00:00");
        map.put("endTime", DateUtils.getCurrentDate() + " 23:59:59");

        if (DateUtils.isFirstDay()) {
            month = DateUtils.getMonth() - 1;
            tableName = "t_order_cargo_history_" + DateUtils.getYear() + (month < 10 ? "0" + month : month);

            map.put("startTime", DateUtils.getFirstDayOfPreMonth() + " 00:00:00");
            map.put("endTime", DateUtils.getBeforeDay() + " 23:59:59");
        }

        map.put("tableName", tableName);

		try {
			//判断保存过期货物的表是否存在
			orderCarogoInfoDao.chkTableIsExists(tableName);
		} catch (Exception e) {

			if(log.isInfoEnabled()) {
				log.info("保存" + month + "月份过期货源的表不存在, 创建...... ");
			}

			StringBuilder sqlStr = new StringBuilder();
			sqlStr.append("CREATE TABLE IF NOT EXISTS ").append(tableName).append("(").
			append("`ID` bigint(20) NOT NULL COMMENT '主键',").
			append("`CARGO_NAME` varchar(50) DEFAULT NULL COMMENT '货物名称',").
			append("`CARGO_TYPE` bigint(2) DEFAULT NULL COMMENT '货物类型',").
			append("`CARGO_WEIGHT` double(20,5) DEFAULT NULL COMMENT '重量（货物）',").
			append("`CARGO_CUBAGE` double(20,5) DEFAULT NULL COMMENT '体积（货物）',").
			append("`REQUEST_CAR_LENGTH` varchar(20) DEFAULT NULL COMMENT '车型要求（车长）',").
			append("`REQUEST_CAR_PLATE_TYPE` varchar(20) DEFAULT NULL COMMENT '板-平板、高低板',").
			append("`REQUEST_CAR_BAR_TYPE` varchar(20) DEFAULT NULL COMMENT '车型要求（车 栏）',").
			append("`FREIGHT` double(20,2) DEFAULT NULL COMMENT '发布运费价格',").
			append("`REQUEST_START_TIME` datetime DEFAULT NULL COMMENT '要求装货时间',").
			append("`REQUEST_END_TIME` datetime DEFAULT NULL COMMENT '要求到货时间',").
			append("`START_PROVINCE` varchar(50) DEFAULT NULL COMMENT '装货地-省',").
			append("`START_CITY` varchar(50) DEFAULT NULL COMMENT '装货地-市',").
			append("`START_COUNTY` varchar(50) DEFAULT NULL COMMENT '装货地-县',").
			append("`START_TOWN` varchar(100) DEFAULT NULL COMMENT '装货地-自定义地址',").
			append("`END_PROVINCE` varchar(50) DEFAULT NULL COMMENT '卸货地-省',").
			append("`END_CITY` varchar(50) DEFAULT NULL COMMENT '卸货地-市',").
			append("`END_COUNTY` varchar(50) DEFAULT NULL COMMENT '卸货地-县',").
			append("`END_TOWN` varchar(100) DEFAULT NULL COMMENT '卸货地-自定义地址',").
			append("`CONTACT_NAME` varchar(20) DEFAULT NULL COMMENT '联系人',").
			append("`CONTACT_MOBILEPHONE` varchar(13) DEFAULT NULL COMMENT '手机号',").
			append("`CONTACT_TELEPHONE` varchar(25) DEFAULT NULL COMMENT '固定电话',").
			append("`REMARK` varchar(200) DEFAULT NULL COMMENT '备注',").
			append("`DELETED_FLAG` bigint(2) DEFAULT '0' COMMENT '删除状态0有效1无效',").
			append("`DEPLOY_USERID` bigint(20) DEFAULT NULL COMMENT '发布用户ID',").
			append("`MODIFY_USERID` bigint(20) DEFAULT NULL COMMENT '修改用户ID',").
			append("`COMPANY_ID` bigint(20) DEFAULT NULL COMMENT '企业ID',").
			append("`CARGO_FLAG` bigint(2) DEFAULT '0' COMMENT '货源状态0待交易',").
			append("`CARGO_FLAG_TIME` datetime DEFAULT NULL COMMENT '状态修改时间',").
			append("`CREATE_TIME` datetime NOT NULL COMMENT '创建时间',").
			append("`MODIFY_TIME` datetime NOT NULL COMMENT '修改时间',").
			append("`CARGO_ORIGIN` bigint(2) DEFAULT '0' COMMENT '插入标识（0发布货源、1导入订单货源）',").
			append("PRIMARY KEY (`ID`)) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='WEB货源月份过期货源存储表'");

			//创建当前月份表
			orderCarogoInfoDao.createTableByMonth(sqlStr.toString());

			if(log.isInfoEnabled()) {
				log.info("保存" + month + "月份过期货源的表创建完成. ");
			}
		}

		//把9月份过期货源插入t_order_cargo_history_201409
		orderCarogoInfoDao.insertExpiredCargoToHistoryTable(map);

		//删除过期货源
		orderCarogoInfoDao.deleteCargoOutOfDate(map);

		if(log.isInfoEnabled()) {
			log.info(DateUtils.getCurrentDate() + "之前的货源已经清理. ");
		}
	}

}
