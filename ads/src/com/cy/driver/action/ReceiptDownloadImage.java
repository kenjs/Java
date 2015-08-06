package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.action.WebBaseAction;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.domain.TransactionReceiptPathDomain;
import com.cy.driver.service.TransactionReceiptPathService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;

@Scope("prototype")
@Controller("receiptDownloadImage")
public class ReceiptDownloadImage extends WebBaseAction{

	private Logger log = LoggerFactory.getLogger(getClass());

	@Resource
	private TransactionReceiptPathService transactionReceiptPathService;

	@RequestMapping(value = "/receiptDownLoadImg")
    @ResponseBody
    @Log(type = 79)
	public JSonResponse execute(String id) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        BufferedInputStream bufferedInputStream;
        BufferedOutputStream bufferedOutputStream;
        try {

			if(StringUtils.isBlank(id)) {
				return JSonResponse.makeHasContentJSonRespone("-8", "未找到该图片的信息");
			}
			TransactionReceiptPathDomain domain = transactionReceiptPathService.selectReceiptById(id);
			if(domain != null) {

                String downloadPath = domain.getReceiptPath();
                String downloadFileName = downloadPath.substring(downloadPath.lastIndexOf("/") + 1);

                long fileLength = new File(downloadPath).length();

                response.setContentType("application/octet-stream");
                response.setHeader("Content-disposition", "attachment;filename=" + new String(downloadFileName.getBytes("UTF-8"),"ISO8859-1"));
                response.setHeader("Content-Length", String.valueOf(fileLength));

				bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(downloadPath)));
                bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());

                byte[] buff = new byte[2048];
                int bytesRead;
                while (-1 != (bytesRead = bufferedInputStream.read(buff, 0, buff.length))) {
                    bufferedOutputStream.write(buff, 0, bytesRead);
                }
                bufferedInputStream.close();
                bufferedOutputStream.close();

                return JSonResponse.makeHasContentJSonRespone("1", "下载图片成功");
			} else {
				return JSonResponse.makeHasContentJSonRespone("0", "未找到该图片的信息");
			}
		} catch (Exception e) {
			log.error("ReceiptDownloadImage.class - " + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "未找到图片");
		}

	}

}
