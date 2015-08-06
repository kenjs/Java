package com.cy.dcts.orderCargo.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BasePageAction;
import com.cy.dcts.common.bo.DictInfo;
import com.cy.dcts.common.bo.OrderCargoInfo;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.common.domain.OrderCargoInfoDomain;
import com.cy.dcts.common.util.ConvertOrderCargoInfoUtil;
import com.cy.dcts.common.util.DataArea;
import com.cy.dcts.common.util.DataDictUtil;
import com.cy.dcts.common.util.ExcelReaderUtil;
import com.cy.dcts.common.util.ValidateUtil;
import com.cy.dcts.orderCargo.service.ISaveOrderCargoInfoService;

public class HomeImportOrderCargoFromFileAction extends BasePageAction {
	private static final long serialVersionUID = -1632633988764801448L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ISaveOrderCargoInfoService saveOrderCargoInfoService;
	
	private String errorMessage="";
	
	private String successMessage="";
	
	private String upLoadFilePath;
	
	private File uploadFile;

	
	@Override
	protected String execMethod() throws Exception {
		try {
			if(getSessionUser() == null){
				logger.warn("upload import order cargo file fail. session is empty.");
				//this.sendResponseToJson("1", "请先登录");
				return LOGIN;
			}
			this.setErrorMessage("");
			this.setSuccessMessage("");
			if (uploadFile == null) {
				logger.warn("upload import order cargo file fail. parameter is empty.");
				//this.sendResponseToJson("2", "参数不能为空！");
				this.setErrorMessage("参数不能为空！");
				return ERROR; 
			}
			logger.debug("upload import order cargo file begin... file=[{}]",uploadFile.getAbsolutePath());

			String name = this.saveFile(uploadFile, "xls", upLoadFilePath);
			logger.debug( "save import order cargo temp file success, fileName=[{}]", name);

			// parse excel file
			Map<Integer, String> map = new ExcelReaderUtil().readExcelFile(name);
			logger.debug( "reader import order cargo file success, fileName=[{}]", name);
			
			String titleStr = "装货地省" 
					+ Constants.CELL_SPLIT_STR + "装货地市"
					+ Constants.CELL_SPLIT_STR + "装货地区"
					+ Constants.CELL_SPLIT_STR + "卸货地省"
					+ Constants.CELL_SPLIT_STR + "卸货地市"
					+ Constants.CELL_SPLIT_STR + "卸货地区"
					+ Constants.CELL_SPLIT_STR + "车长"
					+ Constants.CELL_SPLIT_STR + "车板"
					+ Constants.CELL_SPLIT_STR + "车栏"
					+ Constants.CELL_SPLIT_STR + "货物类型"
					+ Constants.CELL_SPLIT_STR + "货物名称"
					+ Constants.CELL_SPLIT_STR + "货物重量（吨）"
					+ Constants.CELL_SPLIT_STR + "货物体积（方）"
					+ Constants.CELL_SPLIT_STR + "联系人"
					+ Constants.CELL_SPLIT_STR + "手机号码"
					+ Constants.CELL_SPLIT_STR + "联系电话"
					+ Constants.CELL_SPLIT_STR + "装货时间"
					+ Constants.CELL_SPLIT_STR + "卸货时间"
					+ Constants.CELL_SPLIT_STR;
			if(!map.get(0).equals(titleStr)){
				logger.warn( "parse import order cargo file error, title error. fileName=[{}]", name);
				//this.sendResponseToJson("3", "标题为空或有误，请重新下载模板!");
				this.setErrorMessage("标题为空或有误，请重新下载模板!");
				return ERROR; 
			}
			
			String emptyStr = "";
		    for(String str :titleStr.split(Constants.CELL_SPLIT_STR)){
		    	emptyStr += Constants.CELL_SPLIT_STR;
		    }
		    List<OrderCargoInfo> list = new ArrayList<OrderCargoInfo>();
		    for (int i = 1; i <= map.size(); i++) {
		        if(map.get(i).equals(emptyStr)){
		        	break;
		        }
		        logger.debug("begin parse import order cargo file... row=[{}], content=[{}]", i, map.get(i));
		        String str = map.get(i);
		        if(str.endsWith(Constants.CELL_SPLIT_STR + Constants.CELL_SPLIT_STR)){
		        	str += " ";
		        }
		        list.add(ConvertOrderCargoInfoUtil.getOrderCargoInfoFromDomain(getOrderCargoByStr(str.split(Constants.CELL_SPLIT_STR))));
		    }
		   
		    //批量保存入库
		    List <String> listId = saveOrderCargoInfoService.batchAddOrderCargoInfo(list,getSessionUser());
		    for(String addId : listId){
			    logger.info("USERID=[{}], USERCODE=[{}], CARGOID=[{}], REDEPLOYFLAG=[{}], TIME=[{}]",
					new Object[]{getSessionUser().getId(), getSessionUser().getId(), addId, 0,
					new SimpleDateFormat(Constants.DATE_FORMATE_LONG).format(new Date())});//TAG=[{}]=Constants.WEB_USER_ADD_ORDER_CARGO_TAG
		    }
		    logger.debug("batch add orderCargo info success ! list.size()=[{}]",list.size());
		    this.setSuccessMessage(String.valueOf(list.size()));
		    //this.sendResponseToJson("0", "success");
		    return SUCCESS;
		}catch (Exception e) {
			this.setErrorMessage(e.getMessage());
			logger.error("upload import order cargo file fail, message:", e);
			//this.sendResponseToJson("4", "导入失败");
		    return ERROR; 
		}
	}
	
	private OrderCargoInfoDomain getOrderCargoByStr(String[] content)
			throws Exception {
		OrderCargoInfoDomain orderCargoInfoDomain = new OrderCargoInfoDomain();
		List<DictInfo> areaList = DataArea.createAreaData();
		List<DictInfo> carLengthList = DataDictUtil.carLengthData();//车长
		List<DictInfo> carPlateTypeList = DataDictUtil.carPlateTypeData();//板
		List<DictInfo> carBarTypeList = DataDictUtil.carBarTypeData();//栏
		List<DictInfo> cargoTypeList = DataDictUtil.cargoTypeData();//货物类型
		String startProvinceCode = null;
		String startCityCode = null;
		String endProvinceCode = null;
		String endCityCode = null;
		if(StringUtils.isNotEmpty(content[0])){
			orderCargoInfoDomain.setStartProvince(content[0]);
			boolean result = true;
			for(DictInfo dictInfo : areaList){
				if("0".equals(dictInfo.getParentCode())){
					if(orderCargoInfoDomain.getStartProvince().equals(dictInfo.getName())){
						startProvinceCode = dictInfo.getCode();
						result = false;
						break;
					}
				}
			}
			if(result){
				throw new Exception("导入失败，发货地-省[" + orderCargoInfoDomain.getStartProvince() + "]不存在！");
			}
		} else {
			throw new Exception("导入失败，发货地-省不能为空！");
		}
		if(StringUtils.isNotEmpty(content[1])){
			orderCargoInfoDomain.setStartCity(content[1]);
			boolean result = true;
			for(DictInfo dictInfo : areaList){
				if(dictInfo.getParentCode().equals(startProvinceCode)){
					if(orderCargoInfoDomain.getStartCity().equals(dictInfo.getName())){
						startCityCode = dictInfo.getCode();
						result = false;
						break;
					}
				}
			}
			if(result){
				throw new Exception("导入失败，发货地-市[" + orderCargoInfoDomain.getStartCity() + "]不存在！");
			}
		}
		if(StringUtils.isNotEmpty(content[2])){
			orderCargoInfoDomain.setStartCounty(content[2]);
			boolean result = true;
			for(DictInfo dictInfo : areaList){
				if(dictInfo.getParentCode().equals(startCityCode)){
					if(orderCargoInfoDomain.getStartCounty().equals(dictInfo.getName())){
						result = false;
						break;
					}
				}
			}
			if(result){
				throw new Exception("导入失败，发货地-区县[" + orderCargoInfoDomain.getStartCounty() + "]不存在！");
			}
		}
		if(StringUtils.isNotEmpty(content[3])){
			orderCargoInfoDomain.setEndProvince(content[3]);
			boolean result = true;
			for(DictInfo dictInfo : areaList){
				if("0".equals(dictInfo.getParentCode())){
					if(orderCargoInfoDomain.getEndProvince().equals(dictInfo.getName())){
						endProvinceCode = dictInfo.getCode();
						result = false;
						break;
					}
				}
			}
			if(result){
				throw new Exception("导入失败，卸货地省[" + orderCargoInfoDomain.getEndProvince() + "]不存在！");
			}
		} else {
			throw new Exception("导入失败，卸货地省不能为空！");
		}
		if(StringUtils.isNotEmpty(content[4])){
			orderCargoInfoDomain.setEndCity(content[4]);
			boolean result = true;
			for(DictInfo dictInfo : areaList){
				if(dictInfo.getParentCode().equals(endProvinceCode)){
					if(orderCargoInfoDomain.getEndCity().equals(dictInfo.getName())){
						endCityCode = dictInfo.getCode();
						result = false;
						break;
					}
				}
			}
			if(result){
				throw new Exception("导入失败，卸货地-市[" + orderCargoInfoDomain.getEndCity() + "]不存在！");
			}
		}
		if(StringUtils.isNotEmpty(content[5])){
			orderCargoInfoDomain.setEndCounty(content[5]);
			boolean result = true;
			for(DictInfo dictInfo : areaList){
				if(dictInfo.getParentCode().equals(endCityCode)){
					if(orderCargoInfoDomain.getEndCounty().equals(dictInfo.getName())){
						result = false;
						break;
					}
				}
			}
			if(result){
				throw new Exception("导入失败，卸货地-区县[" + orderCargoInfoDomain.getEndCounty() + "]不存在！");
			}
		}
		if(StringUtils.isNotEmpty(content[6])) {
			orderCargoInfoDomain.setRequestCarLength(content[6]);
			boolean result = true;
			for(DictInfo dictInfo : carLengthList) {
				if(dictInfo.getName().replaceAll(" ", "").equals(content[6].replaceAll(" ", ""))) {
					result = false;
					break;
				}
			}
			if(result){
				throw new Exception("导入失败，车辆长度[" + orderCargoInfoDomain.getRequestCarLength() + "]不存在！");
			}
		}
		if(StringUtils.isNotEmpty(content[7])) {
			orderCargoInfoDomain.setRequestCarPlateType(content[7]);
			boolean result = true;
			for(DictInfo dictInfo : carPlateTypeList) {
				if(orderCargoInfoDomain.getRequestCarPlateType().equals(dictInfo.getName())) {
					result = false;
					break;
				}
			}
			if(result){
				throw new Exception("导入失败，车板[" + orderCargoInfoDomain.getRequestCarPlateType() + "]不存在！");
			}
		}
		if(StringUtils.isNotEmpty(content[8])) {
			orderCargoInfoDomain.setRequestCarBarType(content[8]);
			boolean result = true;
			for(DictInfo dictInfo : carBarTypeList) {
				if(orderCargoInfoDomain.getRequestCarBarType().equals(dictInfo.getName())) {
					result = false;
					break;
				}
			}
			if(result){
				throw new Exception("导入失败，车栏[" + orderCargoInfoDomain.getRequestCarBarType() + "]不存在！");
			}
		}
		if(StringUtils.isNotEmpty(content[9])) {
			orderCargoInfoDomain.setCargoType(content[9]);
			boolean result = true;
			for(DictInfo dictInfo : cargoTypeList) {
				if(orderCargoInfoDomain.getCargoType().equals(dictInfo.getName())) {
					orderCargoInfoDomain.setCargoType(dictInfo.getCode());
					result = false;
					break;
				}
			}
			if(result){
				throw new Exception("导入失败，货物类型[" + orderCargoInfoDomain.getCargoType() + "]不存在！");
			}
		}
		orderCargoInfoDomain.setCargoName(content[10]);
		if(StringUtils.isNotEmpty(content[11])) {
			orderCargoInfoDomain.setCargoWeight(Double.parseDouble(content[11]));
			if(StringUtils.isNotEmpty(content[12])) {
				orderCargoInfoDomain.setCargoCubage(Double.parseDouble(content[12]));
			}
		}else {
			if(StringUtils.isNotEmpty(content[12])) {
				orderCargoInfoDomain.setCargoCubage(Double.parseDouble(content[12]));
			}else {
				throw new Exception("导入失败，货物重量或体积必须填写其中一个！");
			}
		}
		if(StringUtils.isNotEmpty(content[13])) {
			orderCargoInfoDomain.setContactName(content[13]);
		}else {
			throw new Exception("导入失败，联系人不能为空！");
		}
		
		if(StringUtils.isEmpty(content[14])&&StringUtils.isEmpty(content[15])){
			throw new Exception("导入失败，手机号或固定电话必填一个！");
		}else{
			if(StringUtils.isNotEmpty(content[14])){
				orderCargoInfoDomain.setContactMobilephone(content[14]);//手机号
				if(!ValidateUtil.validateTelePhone(orderCargoInfoDomain.getContactMobilephone())){
					throw new Exception("导入失败，手机号码格式[" + orderCargoInfoDomain.getContactMobilephone() + "]不正确！");
				}
			}
			if(StringUtils.isNotEmpty(content[15])){
				orderCargoInfoDomain.setContactTelephone(content[15]);//固定电话
				if(ValidateUtil.validatePhone(orderCargoInfoDomain.getContactTelephone())||ValidateUtil.validatePhonees(orderCargoInfoDomain.getContactTelephone())){
					
				}else{
					throw new Exception("导入失败，固定电话格式[" + orderCargoInfoDomain.getContactTelephone() + "]不正确，请填写正确的固定电话！");
				}
			}
		}
		
		if(StringUtils.isNotEmpty(content[16])) {
			orderCargoInfoDomain.setRequestStartTime(content[16]);
		}else {
			throw new Exception("导入失败，发货时间不能为空！");
		}
		orderCargoInfoDomain.setRequestEndTime(content[16]);
		return orderCargoInfoDomain;
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

	public void setSaveOrderCargoInfoService(
			ISaveOrderCargoInfoService saveOrderCargoInfoService) {
		this.saveOrderCargoInfoService = saveOrderCargoInfoService;
	}

}


