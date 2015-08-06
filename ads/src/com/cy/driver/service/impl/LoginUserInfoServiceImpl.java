package com.cy.driver.service.impl;

import com.cy.driver.bo.*;
import com.cy.driver.common.util.DateUtil;
import com.cy.driver.common.util.ValidateUtil;
import com.cy.driver.dao.*;
import com.cy.driver.domain.DriverImgDomain;
import com.cy.driver.domain.DriverTelephoneInfoDomain;
import com.cy.driver.domain.DriverUserInfoDomain;
import com.cy.driver.domain.TransactionInfoDomain;
import com.cy.driver.service.DriverTelephoneInfoService;
import com.cy.driver.service.LoginUserInfoService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service("loginUserInfoService")
public class LoginUserInfoServiceImpl implements LoginUserInfoService {

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
	private DriverUserCargoInfoDao driverUserCargoInfoDao;
	@Resource
	private TransactionInfoDao transactionInfoDao;
	@Resource
	private MarketingDriverInfoDao marketingDriverInfoDao;
	@Resource
	private DriverTelephoneInfoService driverTelephoneInfoService;
	@Resource
	private DriverImgDao driverImgDao;
	@Resource
	private DriverTelephoneInfoDao driverTelephoneInfoDao;
    @Resource
    private CommonDao commonDao;

	@Override
	public void updateDriverAppVersion(DriverUserInfoBo bo) throws SQLException {
		driverUserCargoInfoDao.updateDriverUserInfo(bo);
	}

	public boolean checkUserAccountExist(String code) {
		return driverUserCargoInfoDao.checkUserAccountExist(code) != 0 ? true:false;
	}

	public DriverUserInfoDomain checkLogin(String code) throws Exception{
		DriverUserInfoDomain driverUserInfoDomain = driverUserCargoInfoDao.checkLogin(code);
		if (driverUserInfoDomain != null) {
			List<DriverImgDomain> driverImgs = driverImgDao.selectDriverImgByDriverId(String.valueOf(driverUserInfoDomain.getId()));
			driverUserInfoDomain.setDriverImgs(driverImgs);

			DriverTelephoneInfoDomain driverTelephoneInfoDomain = driverTelephoneInfoDao.queryTelephoneInfo(driverUserInfoDomain.getId());
			if (driverTelephoneInfoDomain != null) {
				driverUserInfoDomain.setDriverTelephoneInfoDomain(driverTelephoneInfoDomain);
				driverUserInfoDomain.setNoIimei(driverTelephoneInfoDomain.getNoImei());
			}
		}
		return driverUserInfoDomain;
	}

	public int updateBaiduPushId(Map<String, Object> map) {
		return driverUserCargoInfoDao.updateBaiduPushId(map);
	}

	@Override
	public boolean chkFreezeAccount(String code) throws Exception {
		return driverUserCargoInfoDao.chkFreezeAccount(code) == 1 ? true : false;
	}

    @Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int register(DriverUserInfoBo bo, String checkCode) throws Exception {
        if (StringUtils.isBlank(checkCode)) {
            return -1;  //未正确输入验证码
        }

        Map<String, Object> map = new HashMap<String, Object>(2);
        map.put("code", bo.getCode());
        map.put("purpose", 0);

        String chkCode = commonDao.queryLatestCode(map);

        if (StringUtils.isBlank(chkCode)) {
            return -2;  //验证码过期
        }

        if (! chkCode.equals(checkCode)) {
            return -3;  //验证码校验失败
        }

        String driverCode = bo.getCode();

        if(StringUtils.isBlank(driverCode)){
            return -8;
        }

        if(! ValidateUtil.validateTelePhone(driverCode)) {
            return -8;
        }

        boolean numberExist = checkUserAccountExist(driverCode);
        if(numberExist) {
            return 0;   //已注册的号码
        }

		//该用户是否在营销
		MarketingDriverInfo marketingDriverInfo = marketingDriverInfoDao.selectMarketingDriverInfoByMobile(driverCode);

		String marDriverId = "";
		String assisterId = "";
		DriverUserInfoDomain domain = null;
		if (marketingDriverInfo != null) {
			marDriverId = marketingDriverInfo.getId() + "";
			assisterId = marketingDriverInfo.getAssisterId();

			bo.setName(marketingDriverInfo.getName());
			bo.setCarNumber(marketingDriverInfo.getCarNumber());
			bo.setCarLength(marketingDriverInfo.getCarLength());
			bo.setCarWeight(marketingDriverInfo.getCarWeight());
			bo.setCarCubage(marketingDriverInfo.getCarCubage());
			bo.setCarPlateType(marketingDriverInfo.getCarPlateType());
			bo.setCarBarType(marketingDriverInfo.getCarBarType());
			bo.setCarTypes(marketingDriverInfo.getCarTypes());
			marketingDriverInfoDao.addDriverFromMarketingDriver(bo);

			domain = marketingDriverInfoDao.queryDriverUserInfoByCode(marDriverId);
		} else {
			driverUserCargoInfoDao.addDriverUserInfo(bo);
		}

		int key = bo.getId();

		if (key == 0 && domain != null) {
			key = domain.getId();
		}

		new DriverInfoReplenishThread(key, bo, marDriverId, assisterId).start();

        String regTime = DateUtil.getCurrentDateTime();
        new NoteOperateDriversThread(driverCode, regTime).start();

		return key;
	}

	/**
	 * 司机信息补充
	 */
	class DriverInfoReplenishThread extends Thread{
		final int key;
		final DriverUserInfoBo bo;
		final String flag;
		final String assisterId;

		DriverInfoReplenishThread(final int key, final DriverUserInfoBo bo, final String flag, final String assisterId) {
			this.key = key;
			this.bo = bo;
			this.flag = flag;
			this.assisterId = assisterId;
		}

		@Override
		public void run() {
			try {
				if (StringUtils.isNotBlank(flag)) {

					//司机id回填到营销表中
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id",flag);
					map.put("driverId",key);
					if (StringUtils.isNotBlank(assisterId)) {
						map.put("regThroughAssist", "1");
					}
					marketingDriverInfoDao.updateMarketingDriverInfoById(map);

					//营销表中的司机线路添加到司机线路中
                    List<MarketingDriverLine> list = new ArrayList<MarketingDriverLine>();
                    List<MarketingDriverLine> dataList = marketingDriverInfoDao.queryMarketingDriverLines(flag);
                    if (dataList.size() > 0) {
                        for (MarketingDriverLine e : dataList) {
                            e.setDriverId(key + "");
                            list.add(e);
                        }
                        marketingDriverInfoDao.addDriverLine(list);
                    }

					//营销表中的司机预约线路添加到司机预约线路中
					List<MarketingDriverBusinessLine> marketingDriverBusinessLines = new ArrayList<MarketingDriverBusinessLine>();
					List<MarketingDriverBusinessLine> datas = marketingDriverInfoDao.queryMarketingDriverBusinessLines(flag);
					if (datas.size() > 0) {
						for (MarketingDriverBusinessLine e : datas) {
							e.setCustomerDriverId(key);
							marketingDriverBusinessLines.add(e);
						}
						marketingDriverInfoDao.addDriverBusinessLineFromMarketing(marketingDriverBusinessLines);
					}
                } else {
					//司机信息添加到营销表中
					marketingDriverInfoDao.addMarketingDriverInfo(key);
				}
				if (key != 0) {
                    //保存手机信息
                    String mobileBrand = bo.getMobileBrand(),
                            mobilePhoneModel = bo.getMobilePhoneModel(),
                            operatingSystemVersionNnumber = bo.getOperatingSystemVersionNnumber(),
                            noIimei = bo.getNoIimei(),
                            info = bo.getInfo(),resolution = bo.getResolution();

                    if(StringUtils.isNotBlank(mobileBrand) || StringUtils.isNotBlank(mobilePhoneModel)
                            || StringUtils.isNotBlank(operatingSystemVersionNnumber) || StringUtils.isNotBlank(noIimei)
                            || StringUtils.isNotBlank(info) || StringUtils.isNotBlank(resolution)) {

                        DriverTelephoneInfo driverTele = new DriverTelephoneInfo();
                        driverTele.setDriverId(key);
                        driverTele.setNoImei(noIimei);
                        driverTele.setMobilePhoneModel(mobilePhoneModel);
                        driverTele.setOperatingSystemVersionNumber(operatingSystemVersionNnumber);
                        driverTele.setMobileBrand(mobileBrand);
                        driverTele.setSoftwareList(info);
                        driverTele.setResolution(resolution);

                        driverTelephoneInfoService.saveUserMobilePhoneInfo(driverTele);
                    }

                    //判断此手机号是否有订单信息
                    List<TransactionInfoDomain> list = transactionInfoDao.selectTransactionInfoByDriverMobiphone(bo.getCode());
                    if (list != null && list.size() > 0) {
                        //把司机id回填到订单中,修改订单状态为待确认
                        Map<String, Object> map = null;
                        for (TransactionInfoDomain e : list) {
                            map = new HashMap<String, Object>();
                            map.put("driverId", key);
                            map.put("tradeStart", 1);
                            map.put("driverMobiphone", bo.getCode());
                            map.put("id", e.getId());
                            transactionInfoDao.updateTransactionInfoById(map);
                        }
                    }
                }
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
	}

    /**
     * 短信营销司机
     */
    class NoteOperateDriversThread extends Thread {
        final String phoneNumber;       //手机号码
        final String time;              //注册时间

        public NoteOperateDriversThread(final String phoneNumber, final String time) {
            this.phoneNumber = phoneNumber;
            this.time = time;
        }

        @Override
        public void run() {
            log.debug("短信营销司机库中手机号码为{}的司机已经注册", phoneNumber);
            try {
                int id = marketingDriverInfoDao.queryNoteOperateDriversByPhone(phoneNumber);
                if (id != 0) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("id", id);
                    map.put("regTime", time);
                    marketingDriverInfoDao.updateNoteOperateDriversRegTime(map);
                }
            } catch (Exception e) {
                log.error(e.getCause().getMessage());
            }
        }
    }
}
