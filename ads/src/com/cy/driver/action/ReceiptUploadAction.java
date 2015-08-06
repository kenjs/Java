package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.bo.TransactionReceiptPathBo;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.common.util.FileOperateUtil;
import com.cy.driver.common.util.ReadPropertiesFile;
import com.cy.driver.service.TransactionReceiptPathService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.File;

/**
 * 回单上传
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("receiptUploadAction")
public class ReceiptUploadAction extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
	private TransactionReceiptPathService transactionReceiptPathService;

	@RequestMapping(value = "/receiptUpload")
    @ResponseBody
    @Log(type = 66)
	public JSonResponse execMethod(String driverId, String transactionId, String type) throws Exception {
		try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

            String fileName = FileOperateUtil.getUploadFileName(request);

            if (StringUtils.isBlank(fileName)) {
                return JSonResponse.makeHasContentJSonRespone("-8","图片不存在");
            }

			if(StringUtils.isBlank(transactionId)) {
				return JSonResponse.makeHasContentJSonRespone("-8", "订单ID不能为空.");
			}
			if(StringUtils.isBlank(type)) {
				return JSonResponse.makeHasContentJSonRespone("-8", "类型不能为空. ");
			}

			String filePath = ReadPropertiesFile.getString("config", "receipt.upload.path") + "APP" + File.separator
                    + driverId + File.separator;
			TransactionReceiptPathBo bo = new TransactionReceiptPathBo();
			bo.setTransactionId(transactionId);
			bo.setReceiptPath(filePath + fileName);
			bo.setType(type);
			int resultI = transactionReceiptPathService.insertTransactionReceiptPath(bo);
			if(resultI != 0) {
                FileOperateUtil.upload(request, filePath);
                return JSonResponse.makeHasContentJSonRespone("1", "回单上传成功. ", resultI);
			} else {
				return JSonResponse.makeHasContentJSonRespone("-8", "回单上传失败.");
			}
		} catch (Exception e) {
            log.error("ReceiptUploadAction.class - " + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "回单上传失败。");
		}
	}

}