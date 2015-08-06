package com.cy.driver.service.impl;

import com.cy.driver.bo.CaptchaNoteLog;
import com.cy.driver.bo.DriverUserInfoBo;
import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.util.DateUtil;
import com.cy.driver.common.util.HttpUtils;
import com.cy.driver.dao.*;
import com.cy.driver.domain.*;
import com.cy.driver.service.DriverBusinessLineInfoService;
import com.cy.driver.service.DriverLineInfoService;
import com.cy.driver.service.DriverUserCargoInfoService;
import com.cy.driver.service.OrderCargoInfoService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 货源信息service impl
 * @since 2014-5-29
 * @author haoyong
 *
 */
@Service("driverUserCargoInfoService")
public class DriverUserCargoInfoServiceImpl implements DriverUserCargoInfoService {
	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
	private DriverUserCargoInfoDao driverUserCargoInfoDao;
    @Resource
    private DriverBusinessLineInfoService driverBusinessLineInfoService;
    @Resource
    private DriverLineInfoService driverLineInfoService;
    @Resource
    private OrderCargoInfoDao orderCargoInfoDao;
    @Resource
    private CommonDao commonDao;
    @Resource
    private OrderCargoInfoService orderCargoInfoService;
    @Resource
    private TransactionInfoDao transactionInfoDao;
    @Resource
	private AppReleasesDao appReleasesDao;
    @Resource
    private DriverImgDao driverImgDao;

    private String requestUrl;

	public int updateDriverUserInfo(DriverUserInfoBo bo) throws SQLException{
		int res = driverUserCargoInfoDao.updateDriverUserInfo(bo);
        return res;
	}

	public DriverUserInfoDomain selectUserBasicInfo(String driverId) throws Exception{
        DriverUserInfoDomain driverUserInfoDomain = driverUserCargoInfoDao.selectUserBasicInfo(driverId);
        if (driverUserInfoDomain != null) {
            List<DriverImgDomain> driverImgs = driverImgDao.selectDriverImgByDriverId(driverId);
            driverUserInfoDomain.setDriverImgs(driverImgs);
        }
        return driverUserInfoDomain;
	}

	@SuppressWarnings("unchecked")
	public List<DriverNotificationInfoDomain> queryDriverNotificationInfo(
			String driverId,String fromSize,String listSize) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("driverId", driverId);
		map.put("fromSize", fromSize);
		map.put("listSize", listSize);
		return (List<DriverNotificationInfoDomain>) driverUserCargoInfoDao.queryDriverNotificationInfo(map);
	}

    @Override
	public int noteSend(String requestIp,String phoneNumber, String content,String captcha,int purpose) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        params.put("kind" , "1");
        params.put("requestIp" , requestIp);
        params.put("phoneNum" , phoneNumber);
        params.put("content" , content);
        params.put("eventFrom", "3");
        params.put("sendOutType", "1");

		String rst = HttpUtils.doPostRequest(requestUrl, params);

        if (log.isDebugEnabled()) {
            log.debug("请求发送短信返回结果：" + rst);
        }

        JSONObject jsonObject = JSONObject.fromObject(rst);

        int code = jsonObject.getInt("errorCode");
        String msg = jsonObject.getString("errorMsg");
        long logId = jsonObject.getLong("object");

        log.debug("短信发送返回信息------->{}", msg);

        CaptchaNoteLog captchaNoteLog = new CaptchaNoteLog(logId, phoneNumber, captcha, 1, purpose);
        commonDao.insertCaptchNoteLog(captchaNoteLog);


        return code;

	}

	public CompanyInfoDomain selectConpanyInfoById(String id) {
		return driverUserCargoInfoDao.selectConpanyInfoById(id);
	}

	public String cargoInfoRemind(Map<String, String> mapPar) {
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		String driverId = mapPar.get("driverId");
		Map<String,Object> nearByMap;
		Map<String,Object> businessMap;
		Map<String,Object> lineMap;
		
		Map<String,Object> locationMap = driverUserCargoInfoDao.selectDriverLastLocation(driverId);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("nearByModifyTime", mapPar.get("nearByModifyTime"));
		if(locationMap != null) {
			if(locationMap.containsKey("province")) {
				map.put("startProvince",locationMap.get("province"));
			}
			if(locationMap.containsKey("city")) {
				map.put("startCity",locationMap.get("city"));
			}
		}
		nearByMap = orderCargoInfoDao.selectNearByCargoRemid(map);
		String nearByNum = nearByMap.get("nearByNum").toString();
		String nearByTime = nearByMap.get("nearByTime") == null ? "" : nearByMap.get("nearByTime").toString();
		
		json.accumulate("nearByNum", nearByNum);
		json.accumulate("nearByTime", nearByTime);
		
		jsonArray.add(json);
		json.clear();
		
		map.clear();
		map.put("businesslineModifyTime", mapPar.get("businesslineModifyTime"));
		List<DriverBusinessLineInfoDomain> list = driverBusinessLineInfoService.selectDriverBusinessLineInfoList(driverId);
        String currDate = DateUtil.getNowStr();
        if(list != null) {
            for (int i = 0;i<list.size();i ++) {
                DriverBusinessLineInfoDomain e = list.get(i);
                map.put("startProvice" + (i +1), e.getStartProvince());
                map.put("startCity" + (i +1), e.getStartCity());
                map.put("endProvince" + (i +1), e.getEndProvince());
                map.put("endCity" + (i +1), e.getEndCity());
                String st = e.getStartTime();
                if(DateUtil.isEarly(st,currDate)) {
                    st = currDate;
                }
                map.put("startTime" + (i +1), st);
                map.put("endTime" + (i +1), e.getEndTime());
            }
        }

		businessMap = orderCargoInfoDao.selectBusinesslineCargoRemid(map);
		String businesslineNum = businessMap.get("businesslineNum").toString();
		String businesslineTime = businessMap.get("businesslineTime") == null ? "" : businessMap.get("businesslineTime").toString();
		
		json.accumulate("businesslineNum", businesslineNum);
		json.accumulate("businesslineTime", businesslineTime);
		
		jsonArray.add(json);
		json.clear();
		
		map.clear();
		map.put("driverLineModifyTime", mapPar.get("driverLineModifyTime"));
		@SuppressWarnings("unchecked")
		List<DriverLineInfoDomain> listLine = (List<DriverLineInfoDomain>) driverLineInfoService.selectDriverLineInfoList(driverId);
		if(listLine != null) {
			for (int i = 0;i<listLine.size();i ++) {
				DriverLineInfoDomain e = listLine.get(i);
				map.put("startProvice" + (i +1), e.getStartProvince());
				map.put("startCity" + (i +1), e.getStartCity());
				map.put("endProvince" + (i +1), e.getEndProvince());
				map.put("endCity" + (i +1), e.getEndCity());				
			}
		}	
		lineMap = orderCargoInfoDao.selectNeededCargoRemid(map);
		String driverLineNum = lineMap.get("driverLineNum").toString();
		String driverLineTime = lineMap.get("driverLineTime") == null ? "" : lineMap.get("driverLineTime").toString();
		
		json.accumulate("driverLineNum", driverLineNum);
		json.accumulate("driverLineTime", driverLineTime);
		
		jsonArray.add(json);
		json.clear();
		
		return jsonArray.toString();
	}

    @Override
    public int driverCall(String driverId, String type) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("driverId", driverId);

        try {

            DriverCallDomain rst = driverUserCargoInfoDao.selectDriverIsCall(driverId);
            if (rst != null) {//修改
                if ("0".equals(type)) {//订单
                    map.put("callTransactionNum", rst.getCallTransactionNum() + 1);
                } else {
                    map.put("callCargoNum", rst.getCallCargoNum() + 1);
                }
                driverUserCargoInfoDao.updateCountDriverUserBusi(map);
            } else {//新增
                if ("0".equals(type)) {
                    map.put("callTransactionNum", 1);
                    map.put("callCargoNum", 0);
                } else {//货源
                    map.put("callTransactionNum", 0);
                    map.put("callCargoNum", 1);
                }
                driverUserCargoInfoDao.insertCountDriverUserBusi(map);
            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -8;
        }
    }

    public void setOrderCargoInfoDao(OrderCargoInfoDao orderCargoInfoDao) {
		this.orderCargoInfoDao = orderCargoInfoDao;
	}

	public void setDriverBusinessLineInfoService(
			DriverBusinessLineInfoService driverBusinessLineInfoService) {
		this.driverBusinessLineInfoService = driverBusinessLineInfoService;
	}

	public void setDriverLineInfoService(DriverLineInfoService driverLineInfoService) {
		this.driverLineInfoService = driverLineInfoService;
	}

    @Override
    public String initHomePageNum(String driverId) throws SQLException {
        int num1 = orderCargoInfoService.selectCargoNumByDriverLine(driverId);//符合司机线路的货物数量
        int num2 = selectNearByCargoCount(driverId);//附近货源量
        int num3 = selectSuitCargoCount(driverId);//符合预约货源量
        int num4 = driverBusinessLineInfoService.selectDriverBusinessLineInfoCount(driverId);//预约数目
        int num5 = transactionInfoDao.selectDriverOrderNumber(driverId);//待确认订单
        int num6 = transactionInfoDao.queryWaitingConfirmReceiptNum(driverId);//查询待确认的货单数目
        int num7 = driverUserCargoInfoDao.queryPactVipDriverNum(driverId);//
        int num8 = driverUserCargoInfoDao.queryHomeReceiptNum(driverId);//首页回单、发货单数量
        int num9 = driverUserCargoInfoDao.queryHomePageUnconfirmedReceiptNum(driverId);//首页我的货单等待确认数量

        JSONObject json = new JSONObject();

        json.accumulate("driverLineCargoNum", num1).accumulate("nearByCargoNum", num2).accumulate("businessLineCargoNum", num3)
                .accumulate("businessLineNum", num4).accumulate("waitingConfirmOrderNum", num5)
                .accumulate("waitingConfirmReceiptNum", num6)
                .accumulate("vipDriverNum", num7).accumulate("homeReceiptNum",num8)
                .accumulate("homeUnconfirmedReceiptNum", num9);

        return json.toString();
    }

    @Override
    public int selectNearByCargoCount(String driverId) {
        Map<String,Object> locationMap = driverUserCargoInfoDao.selectDriverLastLocation(driverId);
        Map<String,Object> map = new HashMap<String,Object>();
        if(locationMap != null) {
            if(locationMap.containsKey("province")) {
                map.put("startProvince",locationMap.get("province"));
            }
            if(locationMap.containsKey("city")) {
                map.put("startCity",locationMap.get("city"));
            }
        }
        return driverUserCargoInfoDao.selectNearByCargoCount(map);
    }

    @Override
    public int selectSuitCargoCount(String driverId) {
        int count;
        List<DriverBusinessLineInfoDomain> list = driverBusinessLineInfoService.selectDriverBusinessLineInfoList(driverId);

        Map<String,Object> map = new HashMap<String, Object>();
        String currDate = DateUtil.getNowStr();
        if(list != null) {
            for (int i = 0;i<list.size();i ++) {
                DriverBusinessLineInfoDomain e = list.get(i);
                map.put("startProvice" + (i +1), e.getStartProvince());
                map.put("startCity" + (i +1), e.getStartCity());
                map.put("endProvince" + (i +1), e.getEndProvince());
                map.put("endCity" + (i +1), e.getEndCity());
                String st = e.getStartTime();
                if(DateUtil.isEarly(st,currDate)) {
                    st = currDate;
                }
                map.put("startTime" + (i +1), st);
            }
        }

        count = driverUserCargoInfoDao.selectSuitCargoCount(map);

        return count;
    }

    @Override
    public int queryPactVipDriverNum(String driverId) throws SQLException {
        return driverUserCargoInfoDao.queryPactVipDriverNum(driverId);
    }

    @Override
	public Object checkVersion(String driverId, String currentVersion,int type, String innerVersion) throws Exception {
		
    	JSONObject jsonObject = new JSONObject();
    	
    	//版本检查时记录当前版本号
        if (StringUtils.isNotBlank(driverId)) {
            DriverUserInfoBo bo = new DriverUserInfoBo();
            bo.setId(Integer.parseInt(driverId));
            bo.setAppVersion(currentVersion);
            driverUserCargoInfoDao.updateDriverUserInfo(bo);
        }

        AppReleasesDomain appReleasesDomain = appReleasesDao.queryLatestAppVersionInfo(type);
        
        if (appReleasesDomain == null) {//强制更新
        	 jsonObject.accumulate("force", 1);
             jsonObject.accumulate("code", 0);
             jsonObject.accumulate("meta", "");
             jsonObject.accumulate("appDatabaseUpdate", 1);
             
             return jsonObject;
		}

        jsonObject.accumulate("code", appReleasesDomain.getInnerVersion());
        jsonObject.accumulate("meta", appReleasesDomain.getMeta());
        
        if (StringUtils.isBlank(innerVersion)) {//强制更新
            jsonObject.accumulate("force", 1);
            jsonObject.accumulate("appDatabaseUpdate", 1);

            return jsonObject;
		}
                
        List<AppReleasesDomain> list = appReleasesDao.queryForceAppInfo(Integer.parseInt(innerVersion));

        if (list == null || list.size() == 0) {//当前版本已是最新
            jsonObject.accumulate("force", 0);
            jsonObject.accumulate("appDatabaseUpdate", 0);
            return jsonObject;
        }

        boolean isLogin = false;
        boolean isUpdate = false;
        for (AppReleasesDomain domain : list) {
            int db = domain.getDatabaseUpdate();
            int vc = domain.getVersionConstraint();
            if (db == 1) {
                isLogin = true;
            }
            if (vc == 1) {
                isUpdate = true;
            }
        }
        if (isUpdate) {
             jsonObject.accumulate("force", 1);
        } else {
            jsonObject.accumulate("force", 0);
        }
        if (isLogin) {
            jsonObject.accumulate("appDatabaseUpdate", 1);
        } else {
            jsonObject.accumulate("appDatabaseUpdate", 0);
        }

		return jsonObject;
	}

    @Override
    public JSonResponse submitCertification(String driverId, String identityLicenseNum) throws SQLException {
        if (StringUtils.isBlank(identityLicenseNum)) {
            return JSonResponse.makeHasContentJSonRespone("-8", "请输入身份证号码");
        }
        DriverUserInfoBo driverUserInfoBo = new DriverUserInfoBo();
        driverUserInfoBo.setId(Integer.parseInt(driverId));
        driverUserInfoBo.setIdentityLicenseNum(identityLicenseNum);
        driverUserInfoBo.setSubmitType("1");
        driverUserInfoBo.setSubmitTime(DateUtil.getCurrentDateTime());
        int i = driverUserCargoInfoDao.updateDriverUserInfo(driverUserInfoBo);
        if (i != 0) {
            return JSonResponse.makeHasContentJSonRespone("1", "认证提交成功");
        }
        return JSonResponse.makeHasContentJSonRespone("0", "认证提交失败");
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    @Value("#{propertiesReader['driver.service.note.url']}")
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }
}
