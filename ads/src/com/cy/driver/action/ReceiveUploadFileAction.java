package com.cy.driver.action;

import com.cy.driver.bo.DriverImg;
import com.cy.driver.bo.DriverUserInfoBo;
import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.countevent.Count;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.common.util.DateUtil;
import com.cy.driver.common.util.FileOperateUtil;
import com.cy.driver.common.util.ReadPropertiesFile;
import com.cy.driver.dao.DriverImgDao;
import com.cy.driver.dao.DriverUserCargoInfoDao;
import com.cy.driver.domain.DriverImgDomain;
import com.cy.driver.domain.DriverUserInfoDomain;
import com.cy.driver.service.DriverUserCargoInfoService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.File;
import java.sql.SQLException;

/**
 * 接收上传文件
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("receiveUploadFileAction")
public class ReceiveUploadFileAction extends AuthenticationAction{

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
	private DriverUserCargoInfoService driverUserCargoInfoService;
	@Resource
	private DriverUserCargoInfoDao driverUserCargoInfoDao;

	@Resource
	private DriverImgDao driverImgDao;

	@RequestMapping(value = "/uploadFile")
    @ResponseBody
    @Log(type = 20)
    @Count(tableNames = "t_count_system_busi", columns = "total_auth_drivers")
	public JSonResponse execute(String driverId, String type) {
		try {
			authentication(driverId);
			if (!isOk) {
				return jSonResponse;
			}

			long startTimeMills = System.currentTimeMillis();
			log.debug("开始上传图片..." +  startTimeMills);
			DriverUserInfoBo bo = new DriverUserInfoBo();
			String path;
			String imgPath = ReadPropertiesFile.getString("config", "upload.img.path");
			String fileName = FileOperateUtil.getUploadFileName(request);
			StringBuilder sb = new StringBuilder();
			sb.append(imgPath);

			if(StringUtils.isBlank(type)) {
                if (log.isInfoEnabled()) {
                    log.info("上传图片不知道图片类型");
                }
				return JSonResponse.makeHasContentJSonRespone("-8", "请确认图片类型");
			}
			sb.append("APP").append(File.separator);
			sb.append(driverId);
			path = sb.toString() + File.separator + fileName;
			bo.setId(Integer.parseInt(driverId));
			if("1".equals(type)) {
				bo.setIdentityLicenseNumFront(path);
			} else if("2".equals(type)) {
				bo.setIdentityLicenseNumContrary(path);
			} else if("3".equals(type)) {
				bo.setDriversLicense(path);
			} else if("4".equals(type)) {
				bo.setDrivingLicense(path);
			} else if("5".equals(type)) {
				bo.setOperatingLicense(path);
			} else if("6".equals(type)) {
				bo.setHeadPortrait(path);
			}
			//要把用户修改为未验证
			if (!"6".equals(type)) {
				bo.setAuditFlag("0");
				bo.setSubmitType("0");
				bo.setSubmitTime(DateUtil.getCurrentDateTime());
			}
			//判断用户的app版本
			DriverUserInfoDomain driverUserInfoDomain = driverUserCargoInfoDao.selectUserBasicInfo(String.valueOf(bo.getId()));
			if (driverUserInfoDomain != null) {
				String appVersion = driverUserInfoDomain.getAppVersion();
				if (StringUtils.isNotBlank(appVersion) && appVersion.contains(".")) {
					String[] indexs = appVersion.split("\\.");
					if (indexs.length >= 3) {
                        int fir = Integer.parseInt(indexs[0]);
                        int sec = Integer.parseInt(indexs[1]);
                        int thr = Integer.parseInt(indexs[2]);

                        boolean flag = false;
                        if (fir < 2) {
                            flag = true;
                        } else if (fir == 2) {
                            if (sec < 2) {
                                flag = true;
                            } else if (sec == 2) {
                                if (thr < 7) {
                                    flag = true;
                                }
                            }
                        }

						if (flag) {
                            bo.setSubmitType("1");
                            bo.setSubmitTime(DateUtil.getCurrentDateTime());
                        }
					}
				}
			}
			int i = driverUserCargoInfoService.updateDriverUserInfo(bo);
			if(i == 1) {
                if (log.isInfoEnabled()) {
                    log.info("上传图片成功");
                }
                FileOperateUtil.upload(request, sb.toString());

				submitDriverImg(bo);

				long endTimeMills = System.currentTimeMillis();
				long times = endTimeMills - startTimeMills;
				log.debug("上传图片结束...耗时-" +  (times / 1000));
				return JSonResponse.makeHasContentJSonRespone("1", "上传图片成功");
			} else {
                if (log.isInfoEnabled()) {
                    log.info("上传图片失败");
                }
				return JSonResponse.makeHasContentJSonRespone("0", "上传图片失败");
			}
		} catch (Exception e) {
			log.error("ReceiveUploadFileAction.class - " + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "文件上传失败。");
		}

	}

	private void submitDriverImg(DriverUserInfoBo bo) throws SQLException{
		DriverImg driverImg = new DriverImg();
		driverImg.setDriverId(bo.getId());
		driverImg.setSubmitType(1);
		driverImg.setSubmitTime(DateUtil.getCurrentDateTime());
		if (StringUtils.isNotBlank(bo.getIdentityLicenseNumFront())) {
			driverImg.setImgType(1);
			driverImg.setImgPath(bo.getIdentityLicenseNumFront());
		}
		if (StringUtils.isNotBlank(bo.getIdentityLicenseNumContrary())) {
			driverImg.setImgType(2);
			driverImg.setImgPath(bo.getIdentityLicenseNumContrary());
		}
		if (StringUtils.isNotBlank(bo.getDriversLicense())) {
			driverImg.setImgType(3);
			driverImg.setImgPath(bo.getDriversLicense());
		}
		if (StringUtils.isNotBlank(bo.getDrivingLicense())) {
			driverImg.setImgType(4);
			driverImg.setImgPath(bo.getDrivingLicense());
		}
		if (StringUtils.isNotBlank(bo.getOperatingLicense())) {
			driverImg.setImgType(5);
			driverImg.setImgPath(bo.getOperatingLicense());
		}
		if (StringUtils.isNotBlank(bo.getHeadPortrait())) {
			driverImg.setSubmitType(3);
			driverImg.setImgType(6);
			driverImg.setImgPath(bo.getHeadPortrait());
		}
		DriverImgDomain driverImgDomain = driverImgDao.selectDriverImg(driverImg);
		if (driverImgDomain != null) {
			driverImg.setId(driverImgDomain.getId());
			driverImgDao.updateDriverImg(driverImg);
		} else {
			driverImgDao.insertDriverImg(driverImg);
		}
	}
}
