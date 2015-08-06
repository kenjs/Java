package com.cy.driver.service.impl;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.bo.NoteSendRecord;
import com.cy.driver.bo.QuoteInfoBo;
import com.cy.driver.common.util.HttpUtils;
import com.cy.driver.dao.CommonDao;
import com.cy.driver.dao.DriverUserCargoInfoDao;
import com.cy.driver.dao.OrderCargoInfoDao;
import com.cy.driver.dao.QuoteInfoDao;
import com.cy.driver.domain.DriverUserInfoDomain;
import com.cy.driver.domain.OrderCargoInfoDomain;
import com.cy.driver.domain.QuoteInfoDomain;
import com.cy.driver.domain.TransactionInfoDomain;
import com.cy.driver.service.QuoteInfoService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 所有报过价的订单列表service impl
 * @date 2014-6-6
 * @author haoyong
 *
 */
@Service("quoteInfoService")
public class QuoteInfoServiceImpl implements QuoteInfoService {

    @Resource
	private QuoteInfoDao quoteInfoDao;

	@Resource
	private OrderCargoInfoDao orderCargoInfoDao;

	@Resource
	private DriverUserCargoInfoDao driverUserCargoInfoDao;

	@Resource private CommonDao commonDao;

	private String requestUrl;

	public List<?> selectQuoteTransactionList(String driverId,String fromSize,String listSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("driverId", driverId);
		map.put("fromSize", fromSize);
		map.put("listSize",listSize);
		return quoteInfoDao.selectQuoteTransactionList(map);
	}

	@SuppressWarnings("unchecked")
	public List<TransactionInfoDomain> selectTransactionListByStatus(String driverId,String tradeStart,String fromSize,String listSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("driverId", driverId);
		map.put("tradeStart", tradeStart);
		map.put("fromSize", fromSize);
		map.put("listSize",listSize);
		return (List<TransactionInfoDomain>) quoteInfoDao.selectTransactionListByStatus(map);
	}

	public int addNewQuoteInfo(QuoteInfoBo bo) {
        try {
            return quoteInfoDao.addNewQuoteInfo(bo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

	public int selectAttentionCargoCount(String driverId) {
		return quoteInfoDao.selectAttentionCargoCount(driverId);
	}

	public List<?> selectAttentionCargoList(String driverId,String fromSize,String listSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("driverId", driverId);
		map.put("fromSize", fromSize);
		map.put("listSize",listSize);
		return quoteInfoDao.selectAttentionCargoList(map);
	}

	public List<?> selectWaitEvaluationTransacList(String driverId,String fromSize, String listSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("driverId", driverId);
		map.put("fromSize", fromSize);
		map.put("listSize",listSize);
		return quoteInfoDao.selectWaitEvaluationTransactionList(map);
	}

	public QuoteInfoDomain selectQuoteInfoByDriverAndCargoId(
			Map<String, Object> map) {
		return quoteInfoDao.selectQuoteInfoByDriverAndCargoId(map);
	}

	public int updateQuoteInfo(QuoteInfoBo bo) {
		return quoteInfoDao.updateQuoteInfo(bo);
	}

	public List<?> selectDealedTransactionInMyLog(Map<String, Object> map) {
		return quoteInfoDao.selectDealedTransactionInMyLog(map);
	}

	@Override
	public List<?> selectImportDealedTransactionList(Map<String, Object> map)
			throws Exception {
		return quoteInfoDao.selectImportDealedTransactionList(map);
	}

	@Override
	public List<?> selectImportTransactionListByTradeStart(
			Map<String, Object> map) throws Exception {
		return quoteInfoDao.selectImportTransactionListByTradeStart(map);
	}

    @Override
    public List<?> queryImportSuccessTransactions(Map<String, Object> map) throws SQLException {
        return quoteInfoDao.queryImportSuccessTransactions(map);
    }

	@Override
	public JSonResponse driverQuote(QuoteInfoBo quoteInfoBo) throws SQLException {
		if(StringUtils.isBlank(String.valueOf(quoteInfoBo.getCargoId()))){
			return JSonResponse.makeHasContentJSonRespone("-8", "货源不存在");
		}
		if(StringUtils.isBlank(String.valueOf(quoteInfoBo.getQuoteFair()))){
			return JSonResponse.makeHasContentJSonRespone("-8", "请输入报价价格");
		}
		if(StringUtils.isBlank(String.valueOf(quoteInfoBo.getQuoteType()))){
			return JSonResponse.makeHasContentJSonRespone("-8", "请选择报价类型");
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("cargoId", quoteInfoBo.getCargoId());
		map.put("driverId", quoteInfoBo.getDriverId());
		QuoteInfoDomain quoteInfoDomain = quoteInfoDao.selectQuoteInfoByDriverAndCargoId(map);
		if (quoteInfoDomain == null) {
			quoteInfoDao.addNewQuoteInfo(quoteInfoBo);
		} else {
			quoteInfoBo.setId(quoteInfoDomain.getId());
			quoteInfoDao.updateQuoteInfo(quoteInfoBo);
		}

		sendQuote(quoteInfoBo);
		return JSonResponse.makeHasContentJSonRespone("1", "报价成功");
	}

	/**
	 * 报价成功后给企业发送信息
	 * @param quoteInfoBo
	 */
	private void sendQuote(QuoteInfoBo quoteInfoBo) throws SQLException {
		OrderCargoInfoDomain orderCargoInfoDomain = orderCargoInfoDao.selectCargoDetailById(String.valueOf(quoteInfoBo.getCargoId()));
		DriverUserInfoDomain driverUserInfoDomain = driverUserCargoInfoDao.selectUserBasicInfo(String.valueOf(quoteInfoBo.getDriverId()));

		if (StringUtils.isBlank(driverUserInfoDomain.getCarNumber())) {
			return;
		}

		String cargoName = StringUtils.isBlank(orderCargoInfoDomain.getCargoName()) ? "" : orderCargoInfoDomain.getCargoName() ;

		DecimalFormat decimalFormat = new DecimalFormat(".0");

		StringBuilder stringBuilder = new StringBuilder();//短信内容
		stringBuilder.append("【快到网】").append(driverUserInfoDomain.getCarNumber()).append("给您")
				.append(orderCargoInfoDomain.getStartCity()).append("到").append(orderCargoInfoDomain.getEndCity())
				.append(orderCargoInfoDomain.getCargoWeight()).append("吨，")
				.append(cargoName).append("报价").append(decimalFormat.format(quoteInfoBo.getQuoteFair()))
				.append("元，").append("详情请登录www.56top.cn按车牌号查看联系方式。");

		Map<String, String> params = new HashMap<String, String>();
        params.put("kind" , "2");
		params.put("requestIp" , "");
		params.put("phoneNum" , orderCargoInfoDomain.getContactMobilephone());
		params.put("content" , stringBuilder.toString());
		params.put("eventFrom", "3");
        params.put("sendOutType", "0");

		String rst = HttpUtils.doPostRequest(requestUrl, params);

		JSONObject jsonObject = JSONObject.fromObject(rst);

        int code = jsonObject.getInt("errorCode");
        String msg = jsonObject.getString("errorMsg");
        int logId = jsonObject.getInt("object");


        NoteSendRecord noteSendRecord = new NoteSendRecord();
        noteSendRecord.setEventFrom(3);
        noteSendRecord.setNoteSendedId(logId);
        noteSendRecord.setType(0);
        noteSendRecord.setTelephone(orderCargoInfoDomain.getContactMobilephone());
        noteSendRecord.setContent(stringBuilder.toString());
        noteSendRecord.setReturnStatus(code);
        noteSendRecord.setRemark(orderCargoInfoDomain.getContactMobilephone());
        noteSendRecord.setUseFor(7);

        commonDao.addNoteSendRecord(noteSendRecord);
	}

	@Value("#{propertiesReader['driver.service.note.url']}")
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
}
