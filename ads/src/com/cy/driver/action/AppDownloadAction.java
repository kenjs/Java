package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.action.WebBaseAction;
import com.cy.driver.common.constants.Constants;
import com.cy.driver.common.countevent.Count;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.common.util.FileOperateUtil;
import com.cy.driver.common.util.ReadPropertiesFile;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * App下载
 * @date 2014-6-10
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("appDownloadAction")
public class AppDownloadAction extends WebBaseAction{
	private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/downloadNewVersion")
    @ResponseBody
    @Log(type = 4)
    @Count(tableNames = {"t_count_system_busi","t_app_releases"},
            columns = {"total_app_downs","down_count"},operaType= {0,0})
	public JSonResponse execute(String os, String regFrom) throws Exception{
        String androidDownloadFilepath = ReadPropertiesFile.getString("config", "android.download.filepath");
        String androidDownloadFilename;

        int from = 0;
        if (StringUtils.isNotBlank(regFrom)) {
            from = Integer.parseInt(regFrom);
        }

        switch (from) {
            case 0:
                androidDownloadFilename = "driver_0.apk";
                break;
            case 1:
                androidDownloadFilename = "driver_1.apk";
                break;
            case 2:
                androidDownloadFilename = "driver_2.apk";
                break;
            case 3:
                androidDownloadFilename = "driver_3.apk";
                break;
            case 4:
                androidDownloadFilename = "driver_4.apk";
                break;
            case 5:
                androidDownloadFilename = "driver_5.apk";
                break;
            case 6:
                androidDownloadFilename = "driver_6.apk";
                break;
            case 7:
                androidDownloadFilename = "driver_7.apk";
                break;
            default:
                androidDownloadFilename = "driver_0.apk";
                break;
        }

		try {
			String filePath;
			if(Constants.OS_ANDROID.equalsIgnoreCase(os)){
                String contentType = "application/octet-stream;charset=utf-8";
				filePath = androidDownloadFilepath + androidDownloadFilename;
                FileOperateUtil.download(request, response , filePath, contentType, androidDownloadFilename);

				return JSonResponse.makeHasContentJSonRespone("1", "Android版本有更新", filePath);
			}
			return JSonResponse.makeHasContentJSonRespone("0", "没有可下载文件");
		} catch (IOException e) {
			log.error("下载APP出错-" + e.getCause().getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "下载失败，请重试。");
		}
	}
}
