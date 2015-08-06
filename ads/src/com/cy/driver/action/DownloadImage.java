package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.common.util.FileOperateUtil;
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

@Scope("prototype")
@Controller("downloadImage")
public class DownloadImage extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());
    @Resource
	private DriverUserCargoInfoService driverUserCargoInfoService;

	@RequestMapping(value = "/downloadImage")
    @ResponseBody
    @Log(type = 49)
	public JSonResponse execute(String driverId, String fileType) throws Exception{
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

		String filePath = "";
		String fileName = "";
        String contentType = "application/download";
		try {
			authentication(driverId);
			if (!isOk) {
				return jSonResponse;
			}

			if(StringUtils.isBlank(fileType)) {
				return JSonResponse.makeHasContentJSonRespone("-8", "请确认要下载的图片类型");
			}
			DriverUserInfoDomain domain = driverUserCargoInfoService.selectUserBasicInfo(driverId);
			if(domain != null) {
				if("1".equals(fileType)) {
					if(domain.getIdentityLicenseNumFront() != null) {
						filePath = domain.getIdentityLicenseNumFront();
						fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1);
					} else {
						return JSonResponse.makeHasContentJSonRespone("0", "暂无此图片");
					}
				} else if("2".equals(fileType)) {
					if(domain.getIdentityLicenseNumContrary() != null) {
						filePath = domain.getIdentityLicenseNumContrary();
						fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1);
					} else {
                        return JSonResponse.makeHasContentJSonRespone("0", "暂无此图片");
					}
				} else if("3".equals(fileType)) {
					if(domain.getDriversLicense() != null) {
						filePath = domain.getDriversLicense();
						fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1);
					} else {
                        return JSonResponse.makeHasContentJSonRespone("0", "暂无此图片");
					}
				} else if("4".equals(fileType)) {
					if(domain.getDrivingLicense() != null) {
						filePath = domain.getDrivingLicense();
						fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1);
					} else {
                        return JSonResponse.makeHasContentJSonRespone("0", "暂无此图片");
					}
				} else if("5".equals(fileType)) {
					if(domain.getOperatingLicense() != null) {
						filePath = domain.getOperatingLicense();
						fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1);
					} else {
                        return JSonResponse.makeHasContentJSonRespone("0", "暂无此图片");
					}
				} else if("6".equals(fileType)) {
					if(domain.getHeadPortrait() != null) {
						filePath = domain.getHeadPortrait();
						fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1);
					} else {
                        return JSonResponse.makeHasContentJSonRespone("0", "暂无此图片");
					}
				}

                if (StringUtils.isNotBlank(filePath) && StringUtils.isNotBlank(fileName)) {
                    FileOperateUtil.downloadFromDisk(request, response, filePath, contentType, fileName);

                    return JSonResponse.makeHasContentJSonRespone("1", "下载图片成功");
                }
			} else {
				return JSonResponse.makeHasContentJSonRespone("0", "未找到该用户有关的信息");
			}
		} catch (Exception e) {
			log.error("下载图片出错-" + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "未找到图片。");
		}
		return JSonResponse.makeHasContentJSonRespone("-8", "");
	}

}
