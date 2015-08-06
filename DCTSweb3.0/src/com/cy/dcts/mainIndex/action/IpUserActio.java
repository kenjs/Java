package com.cy.dcts.mainIndex.action;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.bo.DictInfo;
import com.cy.dcts.ipUrlStr.service.IIpUrlStrService;
/**
 *  获取访问者Ip地址转换地区
 * @author nxj
 *
 */
public class IpUserActio extends BaseJsonAction {
	
	private static final long serialVersionUID = -4065339879650386287L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private IIpUrlStrService ipUrlStrService;
	
	@Override
	protected void execMethod() throws Exception {
		try {
			DictInfo dictInfo = new DictInfo();
			String addresses = ipUrlStrService.getIpProvinceCity(request);
			if(addresses != null) {
				String[] adders = addresses.split("-");
				if(adders.length == 1) {
					dictInfo.setCode(adders[0]);
				}else if(adders.length == 2) {
					dictInfo.setCode(adders[0]);
					dictInfo.setName(adders[1]);
				}
			}
			if(StringUtils.isEmpty(dictInfo.getName())) {
				String result = this.sendResponseToJson("2","获取访问者地区省成功！",dictInfo);
				logger.warn("Get the visitor IP provinces success. json=[{}] , province=[{}]",new Object[] { result, dictInfo.getCode()});
			}else {
				String result = this.sendResponseToJson("1","获取访问者地区省市成功！",dictInfo);
				logger.warn("Get the visitor IP provinces and city success. json=[{}] , province=[{}] , city=[{}]",new Object[] { result , dictInfo.getCode() ,dictInfo.getName()});
			}
		}catch (Exception e) {
			logger.debug("Get the visitor IP provinces and city error! ip=[{}]",new Object[] {ipUrlStrService.getIpAddr(request)});
			throw new RuntimeException();
		}
	}

	public void setIpUrlStrService(IIpUrlStrService ipUrlStrService) {
		this.ipUrlStrService = ipUrlStrService;
	}

	
	
}
