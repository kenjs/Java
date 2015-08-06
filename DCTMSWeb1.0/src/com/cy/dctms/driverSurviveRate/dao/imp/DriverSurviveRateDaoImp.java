package com.cy.dctms.driverSurviveRate.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.dao.BaseDao;
import com.cy.dctms.common.domain.DriverSurviveRateDomain;
import com.cy.dctms.common.util.MathUtil;
import com.cy.dctms.common.util.TimeDealUtil;
import com.cy.dctms.driverSurviveRate.dao.IDriverSurviveRateDao;


public class DriverSurviveRateDaoImp extends BaseDao implements IDriverSurviveRateDao{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@SuppressWarnings("unchecked")
	@Override
	public void queryDriverSurviveRateList(DriverSurviveRateDomain driverSurviveRateDomain) {
		try {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			//��������
			queryMap.put("queryTimeQ", driverSurviveRateDomain.getQueryTimeQ());
			queryMap.put("queryTimeZ", driverSurviveRateDomain.getQueryTimeZ());
			List<DriverSurviveRateDomain> dataList = (List<DriverSurviveRateDomain>) queryForList("query_driverSurviveRate_info",queryMap);
			int registerTotalCount = 0;  // ��ע��������
		    int goodFindTotalNum =0;  // �һ��ܴ���
		    int appointTotalNum =0;  // ԤԼ������
		    int phoneCallTotalNum =0;  // �绰����������
			for (DriverSurviveRateDomain domain : dataList) {
				//��ѯʱ��ת��Ϊ�����ո�ʽ�ַ���
				domain.setQueryTime(TimeDealUtil.TimeToYyyyMMDd(domain.getQueryTime()));
				//��С��ת��Ϊ�ٷ���
				domain.setSurviveRate(MathUtil.decimalChangePercent(domain.getSurviveRate()));
				domain.setActiveRate(MathUtil.decimalChangePercent(domain.getActiveRate()));
				registerTotalCount += Integer.valueOf(domain.getRegisterCount());
				goodFindTotalNum += Integer.valueOf(domain.getGoodFindNum());
				appointTotalNum +=Integer.valueOf(domain.getAppointNum());
				phoneCallTotalNum += Integer.valueOf(domain.getPhoneCallNum());
			}
			driverSurviveRateDomain.setRegisterTotalCount(String.valueOf(registerTotalCount));
			driverSurviveRateDomain.setGoodFindTotalNum(String.valueOf(goodFindTotalNum));
			driverSurviveRateDomain.setAppointTotalNum(String.valueOf(appointTotalNum));
			driverSurviveRateDomain.setPhoneCallTotalNum(String.valueOf(phoneCallTotalNum));
			driverSurviveRateDomain.setDataList(dataList);
		} catch (Exception e) {
			logger.error("query_driverSurviveRate_list",e);
			throw new RuntimeException();
		}
	}
}
