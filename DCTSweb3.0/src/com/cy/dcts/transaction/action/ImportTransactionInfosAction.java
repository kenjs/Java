package com.cy.dcts.transaction.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.action.BasePageAction;
import com.cy.dcts.common.bo.DictInfo;
import com.cy.dcts.common.bo.DriverUserInfo;
import com.cy.dcts.common.bo.OrderCargoInfo;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.common.domain.OrderCargoInfoDomain;
import com.cy.dcts.common.domain.TransactionInfoDomain;
import com.cy.dcts.common.util.ConvertOrderCargoInfoUtil;
import com.cy.dcts.common.util.DataArea;
import com.cy.dcts.common.util.DataDictUtil;
import com.cy.dcts.common.util.ExcelReaderUtil;
import com.cy.dcts.common.util.ValidateUtil;
import com.cy.dcts.driverCar.service.IDriverUserCarInfoService;
import com.cy.dcts.orderCargo.service.ISaveOrderCargoInfoService;
import com.cy.dcts.transaction.service.ITransactionInfoService;
import com.cy.dcts.webUser.service.IQueryWebUserInfoService;


/**
 * 导入订单货源信息
 * @author zdy
 *
 */
public class ImportTransactionInfosAction extends BasePageAction {

	private static final long serialVersionUID = -1632633988764801448L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	private IDriverUserCarInfoService driverUserCarInfoService;
	
	private IQueryWebUserInfoService queryWebUserInfoService;
	
	private TransactionInfoDomain transactionInfoDomain=new TransactionInfoDomain();
	
	private String errorMessage="";
	
	private String successMessage="";
	
	private String menuAId;//个人中心的菜单显示
	
	private String upLoadFilePath;
	
	private File uploadFile;

	
	@Override
	protected String execMethod() throws Exception {
		try {
			if(getSessionUser() == null){
				logger.warn("upload import transaction order cargo file fail. session is empty.");
				return LOGIN;
			}
			this.setErrorMessage("");
			this.setSuccessMessage("");
			if (uploadFile == null) {
				logger.warn("upload import transaction order cargo file fail. parameter is empty.");
				//this.sendResponseToJson("2", "参数不能为空！");
				this.setErrorMessage("参数不能为空！");
				return ERROR; 
			}
			logger.debug("upload import transaction order cargo file begin... file=[{}]",uploadFile.getAbsolutePath());

			String name = this.saveFile(uploadFile, "xls", upLoadFilePath);
			logger.debug( "save import transaction order cargo temp file success, fileName=[{}]", name);

			// parse excel file
			Map<Integer, String> map = new ExcelReaderUtil().readExcelFile(name);
			logger.debug( "reader import transaction order cargo file success, fileName=[{}]", name);
			
			String titleStr = "货物名称"
					+ Constants.CELL_SPLIT_STR + "装货地省" 
					+ Constants.CELL_SPLIT_STR + "装货地市"
					+ Constants.CELL_SPLIT_STR + "装货地区"
					+ Constants.CELL_SPLIT_STR + "卸货地省"
					+ Constants.CELL_SPLIT_STR + "卸货地市"
					+ Constants.CELL_SPLIT_STR + "卸货地区"
					+ Constants.CELL_SPLIT_STR + "发货方编码"
					+ Constants.CELL_SPLIT_STR + "发货单号"
					+ Constants.CELL_SPLIT_STR + "收货方编码"
					+ Constants.CELL_SPLIT_STR + "收货单号"
					+ Constants.CELL_SPLIT_STR + "承运商编码"
					+ Constants.CELL_SPLIT_STR + "承运司机号码"
					+ Constants.CELL_SPLIT_STR;
			if(!map.get(0).equals(titleStr)){
				logger.warn( "parse import transaction order cargo file error, title error. fileName=[{}]", name);
				this.setErrorMessage("标题为空或有误，请重新下载模板!");
				return ERROR; 
			}
			
			String emptyStr = "";
		    for(String str :titleStr.split(Constants.CELL_SPLIT_STR)){
		    	emptyStr += Constants.CELL_SPLIT_STR;
		    }
		    List<TransactionInfoDomain> list = new ArrayList<TransactionInfoDomain>();
		    for (int i = 1; i <map.size(); i++) {
		        if(emptyStr.equals(map.get(i))){
		        	break;
		        }
		        logger.debug("begin parse import transaction order cargo file... row=[{}], content=[{}]", i, map.get(i));
		        String str = map.get(i);
		        if(str.endsWith(Constants.CELL_SPLIT_STR + Constants.CELL_SPLIT_STR)){
		        	str += " ";
		        }
		        list.add(getTransactionOrderCargoByStr(str.split(Constants.CELL_SPLIT_STR)));
		    }
		   transactionInfoDomain.setList(list);
		    logger.debug("reader transaction orderCargo file success ! list.size()=[{}]",list.size());
		    this.setSuccessMessage(String.valueOf(list.size()));
		    return SUCCESS;
		}catch (Exception e) {
			logger.error("upload import transaction order cargo file fail");
			e.printStackTrace();
			this.setErrorMessage("导入失败!");
		    return ERROR; 
		}
	}
	
	private TransactionInfoDomain getTransactionOrderCargoByStr(String[] content)
			throws Exception {
		TransactionInfoDomain trandeDomain = new TransactionInfoDomain();
		List<DictInfo> areaList = DataArea.createAreaData();
		String startProvinceCode = null;
		String startCityCode = null;
		String endProvinceCode = null;
		String endCityCode = null;
		String flags="";
		
		//货源名称存入对象
		trandeDomain.setCargoName(content[0]);
		
		if(StringUtils.isNotEmpty(content[1])){
			trandeDomain.setStartProvince(content[1]);
			boolean result = true;
			for(DictInfo dictInfo : areaList){
				if("0".equals(dictInfo.getParentCode())){
					if(trandeDomain.getStartProvince().equals(dictInfo.getName())){
						startProvinceCode = dictInfo.getCode();
						result = false;
						break;
					}
				}
			}
			if(result){
				flags+="1";
				//throw new Exception("导入失败，发货地-省[" + trandeDomain.getStartProvince() + "]不存在！");
			}
		}
		if(StringUtils.isNotEmpty(content[2])){
			trandeDomain.setStartCity(content[2]);
			boolean result = true;
			for(DictInfo dictInfo : areaList){
				if(dictInfo.getParentCode().equals(startProvinceCode)){
					if(trandeDomain.getStartCity().equals(dictInfo.getName())){
						startCityCode = dictInfo.getCode();
						result = false;
						break;
					}
				}
			}
			if(result){
				flags+="2";
				//throw new Exception("导入失败，发货地-市[" + trandeDomain.getStartCity() + "]不存在！");
			}
		}
		if(StringUtils.isNotEmpty(content[3])){
			trandeDomain.setStartCounty(content[3]);
			boolean result = true;
			for(DictInfo dictInfo : areaList){
				if(dictInfo.getParentCode().equals(startCityCode)){
					if(trandeDomain.getStartCounty().equals(dictInfo.getName())){
						result = false;
						break;
					}
				}
			}
			if(result){
				flags+="3";
				//throw new Exception("导入失败，发货地-区县[" + trandeDomain.getStartCounty() + "]不存在！");
			}
		}
		if(StringUtils.isNotEmpty(content[4])){
			trandeDomain.setEndProvince(content[4]);
			boolean result = true;
			for(DictInfo dictInfo : areaList){
				if("0".equals(dictInfo.getParentCode())){
					if(trandeDomain.getEndProvince().equals(dictInfo.getName())){
						endProvinceCode = dictInfo.getCode();
						result = false;
						break;
					}
				}
			}
			if(result){
				flags+="4";
//				throw new Exception("导入失败，卸货地省[" + trandeDomain.getEndProvince() + "]不存在！");
			}
		}

		if(StringUtils.isNotEmpty(content[5])){
			trandeDomain.setEndCity(content[5]);
			boolean result = true;
			for(DictInfo dictInfo : areaList){
				if(dictInfo.getParentCode().equals(endProvinceCode)){
					if(trandeDomain.getEndCity().equals(dictInfo.getName())){
						endCityCode = dictInfo.getCode();
						result = false;
						break;
					}
				}
			}
			if(result){
				flags+="5";
//				throw new Exception("导入失败，卸货地-市[" + trandeDomain.getEndCity() + "]不存在！");
			}
		}
		if(StringUtils.isNotEmpty(content[6])){
			trandeDomain.setEndCounty(content[6]);
			boolean result = true;
			for(DictInfo dictInfo : areaList){
				if(dictInfo.getParentCode().equals(endCityCode)){
					if(trandeDomain.getEndCounty().equals(dictInfo.getName())){
						result = false;
						break;
					}
				}
			}
			if(result){
				flags+="6";
//				throw new Exception("导入失败，卸货地-区县[" + trandeDomain.getEndCounty() + "]不存在！");
			}
		}
		
		//物流企业登录发货方必填
		if(StringUtils.isEmpty(content[7])&&Constants.USER_TYPE_ENTERPRISE_KEY.equals(getSessionUser().getUserType())){
			flags+="7";
		}
		//不为空是验证有效
		if(StringUtils.isNotEmpty(content[7])) {
			trandeDomain.setShipperCode(content[7]);
			if(Constants.USER_TYPE_SHIPPER_KEY.equals(getSessionUser().getUserType())){//登录者是发货方
				if(!trandeDomain.getShipperCode().equals(getSessionUser().getEncoded())){//登录者输入的发货方编码与自己的实际编码不一致
					flags+="7";
//					throw new Exception("发货方编码输入错误");
				}
			}else{
				//(1)验证修改的子货主（发货方）是否有效，表t_web_user_info根据父级userId，用户类型和发货方编码
				Integer shipperId=queryWebUserInfoService.queryWebUserByParentIdUsertypeEncoded(getSessionUser().getId(),trandeDomain.getShipperCode(),Constants.USER_TYPE_SHIPPER_KEY);
				if(shipperId==null||"".equals(shipperId)){
					logger.warn("import transaction info shipper not exists.");
					flags+="7";
//					throw new Exception("编码为"+trandeDomain.getShipperCode()+"的发货方不存在"); 
				}
			}
			
		}
		
		if(StringUtils.isNotEmpty(content[8])) {
			trandeDomain.setShipperOrderNo(content[8]);
		}else {
			flags+="8";
			//throw new Exception("导入失败，发货单号不能为空！");
		}
		if(StringUtils.isNotEmpty(content[9])) {
			trandeDomain.setReceiverCode(content[9]);
			//（2）判断修改的收货方是否有效（在web_user_info表中存在） 根据收货方编码，用户类型和父级Id（即当请登录者的userId）
			Integer receiverId=queryWebUserInfoService.queryWebUserByParentIdUsertypeEncoded(getSessionUser().getId(),trandeDomain.getReceiverCode(),Constants.USER_TYPE_RECEIVE_KEY);
			if(receiverId==null||"".equals(receiverId)){
				logger.warn("import transaction info receiver not exists.");
//				throw new Exception("编码为"+trandeDomain.getReceiverCode()+"的收货方不存在!");
				flags+="9";
			}
		}else {
			flags+="9";
//			throw new Exception("导入失败，收货方不能为空！");
		}
		if(StringUtils.isNotEmpty(content[10])) {
			trandeDomain.setReceiverOrderNo(content[10]);
		}else {
			flags+="a";
//			throw new Exception("导入失败，订单号不能为空！");
		}
		
				
		//承运商编码不是必填的，不为空是验证有效
		if(StringUtils.isNotEmpty(content[11])) {
			trandeDomain.setShipperCompanyCode(content[11]);
			if(Constants.USER_TYPE_ENTERPRISE_KEY.equals(getSessionUser().getUserType())){//登录者是物流企业
				if(!trandeDomain.getShipperCompanyCode().equals(getSessionUser().getEncoded())){//登录者输入的物流企业编码与自己的实际编码不一致
					flags+="b";
				}
			}else{
				//（3）验证修改的子账号承运商（物流企业）是否有效，表t_web_user_info根据父级userId，用户类型和物流企业用户的编码
				Integer shipperComId=queryWebUserInfoService.queryWebUserByParentIdUsertypeEncoded(getSessionUser().getId(),trandeDomain.getShipperCompanyCode(),Constants.USER_TYPE_ENTERPRISE_KEY);
				if(shipperComId==null||"".equals(shipperComId)){
					logger.warn("import transaction info shipper company not exists.");
					flags+="b";
				}
			}
			
		}
		
		//承运人不为空验证是否存在
		if(StringUtils.isNotEmpty(content[12])) {
			BigDecimal bd = new BigDecimal(content[12]);  
			String codes = bd.toPlainString();
			//发货人导入 时：若承运人不为空则承运商必填
			if(Constants.USER_TYPE_SHIPPER_KEY.equals(getSessionUser().getUserType())&&StringUtils.isEmpty(trandeDomain.getShipperCompanyCode())){
				flags+="b";
			}
			
			if(ValidateUtil.validateTelePhone(content[12])) {
				trandeDomain.setCode(content[12]);
				//（4）.输入的手机号不为空时去判断该司机是否在司机库中存在
				DriverUserInfo driverUserInfo=driverUserCarInfoService.queryDriverInfoByCode(trandeDomain.getCode());
				if(driverUserInfo!=null){
					trandeDomain.setDriverId(driverUserInfo.getId());
				}else{
					logger.warn("import transaction info driver not exists.");
					flags+="c";
//					throw new Exception("承运司机还未安装快到网App");
				}
			}
			
			
		}
		trandeDomain.setFlas(flags);
		return trandeDomain;
	}

	//保存文件
	private static String saveFile(File srcFile, String fileExtension, String filepath)
			throws IOException {

		File file = new File(filepath);
		if (!file.exists()) {// 判断路径是否存在
			file.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = sdf.format(new Date()) + "." + fileExtension;// 定义文件名

		file = new File(filepath + "\\" + fileName);
		int i = 0;// 防止高并发时候出现的重复命名
		while (file.exists()) {
			fileName = sdf.format(new Date()) + "_" + i + "." + fileExtension;// 定义文件名
			file = new File(filepath + "\\" + fileName);
			i++;
		}

		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(srcFile), 16 * 1024);
			out = new BufferedOutputStream(new FileOutputStream(file), 16 * 1024);
			byte[] buffer = new byte[16 * 1024];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} finally {
			if (null != in) {
				in.close();
			}
			if (null != out) {
				out.close();
			}
		}

		return filepath + "\\" + fileName;
	}

	public void setUpLoadFilePath(String upLoadFilePath) {
		this.upLoadFilePath = upLoadFilePath;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getMenuAId() {
		return menuAId;
	}

	public void setMenuAId(String menuAId) {
		this.menuAId = menuAId;
	}
	
	public TransactionInfoDomain getTransactionInfoDomain() {
		return transactionInfoDomain;
	}

	public void setTransactionInfoDomain(TransactionInfoDomain transactionInfoDomain) {
		this.transactionInfoDomain = transactionInfoDomain;
	}

	public IDriverUserCarInfoService getDriverUserCarInfoService() {
		return driverUserCarInfoService;
	}

	public void setDriverUserCarInfoService(
			IDriverUserCarInfoService driverUserCarInfoService) {
		this.driverUserCarInfoService = driverUserCarInfoService;
	}

	public IQueryWebUserInfoService getQueryWebUserInfoService() {
		return queryWebUserInfoService;
	}

	public void setQueryWebUserInfoService(
			IQueryWebUserInfoService queryWebUserInfoService) {
		this.queryWebUserInfoService = queryWebUserInfoService;
	}

	public File getUploadFile() {
		return uploadFile;
	}
	
}

