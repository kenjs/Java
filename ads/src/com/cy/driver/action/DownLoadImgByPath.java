package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.util.FileOperateUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;

/**
 * Created by haoy on 2014/12/10.
 */
@Scope("prototype")
@Controller("downLoadImgByPath")
public class DownLoadImgByPath extends AuthenticationAction{
    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/downLoadImgByPath")
    @ResponseBody
    public JSonResponse execute(String driverId, String filePath) throws Exception{
        request.setCharacterEncoding("UTF-8");

        String fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1);
        String contentType = "application/download";
        try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

            if (StringUtils.isNotBlank(filePath) && StringUtils.isNotBlank(fileName)) {
                FileOperateUtil.downloadFromDisk(request, response, filePath, contentType, fileName);

                return JSonResponse.makeHasContentJSonRespone("1", "下载图片成功");
            }
        } catch (Exception e) {
            log.error("下载图片出错-" + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "未找到图片。");
        }
        return JSonResponse.makeHasContentJSonRespone("-8", "");
    }
}
