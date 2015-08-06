package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.common.util.FileUtil;
import com.cy.driver.service.TransactionReceiptPathService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 删除
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("receiptDeleteAction")
public class ReceiptDeleteAction extends AuthenticationAction {

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
	private TransactionReceiptPathService transactionReceiptPathService;

	@RequestMapping(value = "/receiptDelete")
    @ResponseBody
    @Log(type = 68)
	public JSonResponse execMethod(String driverId, String idJson) throws Exception {
		try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

			JSONObject jsonObject = JSONObject.fromObject(idJson);
			Map<String,Object> map = jsonObject.fromObject(jsonObject);
			JSONArray jsonArray = JSONArray.fromObject(map.get("id"));
			List<String> list = (List<String>) jsonArray.toCollection(jsonArray, String.class);
			List<String> path = transactionReceiptPathService.selectReceiptPathById(list);
			int res = transactionReceiptPathService.deleteTransactionReceiptPath(list);
			if(res != 0) {
				for (String imgPath : path) {
					FileUtil.removeImg(imgPath);
				}
                return JSonResponse.makeHasContentJSonRespone("1", "删除成功！");
			} else {
				return JSonResponse.makeHasContentJSonRespone("0", "删除失败！");
			}
		} catch (Exception e) {
			log.error("ReceiptDeleteAction.class - " + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "回单删除失败，请重试。");
		}
	}

}
