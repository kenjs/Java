package tf56.contract.services.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.serfj.client.WebServiceException;

import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.util.ClientUtil;
import tf56.contract.services.LbsDeviceService;

public class LbsDeviceServiceImpl implements LbsDeviceService {
	/**
	 * 跟踪
	 */
	@Override
	public String selectLbsTrackByDeviceIdAndParyId(Map map) {
		String msgJson = "";
		String url = "lbsTrack/lbstrackcs/selectLbsTrackByDeviceIdAndParyId";
		ClientUtil cu = new ClientUtil(url);
		try {
			//设备列表，当前位置
			if(map.size() > 1){
				for (int i = 0; i < map.size(); i++) {
					Map tempMap = new HashMap();
					String deviceid = (String) map.get("data[" + i + "][deviceid]");
					String partyid = (String) map.get("data[" + i + "][partyid]");
					if (deviceid != null) {
						tempMap.put("deviceid", deviceid);
						tempMap.put("partyid", partyid);
						Thread.sleep(100L);
						String temp = cu.postRequest(url, tempMap).toString();// 调用第lbsTrack外包
						if (!"".equals(temp))
							msgJson += "\"" + deviceid + "\":" + temp + ",";
						else
							msgJson += "\"" + deviceid + "\":{},";
					}
				}
				if (msgJson.length() > 0) {
					msgJson = "{" + msgJson.substring(0, msgJson.length() - 1) + "}";
				}
			}else{//跟踪
				msgJson = cu.postRequest(url, map).toString(); // 调用第lbsTrack外包
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WebServiceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msgJson; // json格式: 自定义
	}
	/**
	 * 回放
	 */
	@Override
	public String selectLbsTrackListByIds(Map map) {
		String msgJson = "";
		String url = "lbsTrack/lbstrackcs/selectLbsTrackListByDeviceIdsOrPartyIds";
		ClientUtil cu = new ClientUtil(url);
		try {
			msgJson = cu.postRequest(url, map).toString(); // 调用第2外包
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WebServiceException e) {
			e.printStackTrace();
		}
		return msgJson;
	}
	/**
	 * 电子围栏的电子围栏
	 */
	@Override
	public String seletLbsFenceListByIds(Map map) {
		String msgJson = "";
		String url = "lbsSiteView/lbsfencecs/selectLbsFenceListByIds";
		ClientUtil cu = new ClientUtil(url);
		try {
			msgJson = cu.postRequest(url, map).toString(); // 调用第2外包
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WebServiceException e) {
			e.printStackTrace();
		}
		return msgJson;
	}
	/**
     * @author wei.huang
     * @date 2014-1-13
     * @function 调度单跟踪
     */
    public String track(Map map){
    	//业务数据
		Map businessMap = new HashMap();
		businessMap.put("调度单号：",map.get("systemdispatchnumber"));
		businessMap.put("发车时间：",map.get("starttime"));
		businessMap.put("车牌号：",map.get("carplatenumber"));
		businessMap.put("司机姓名：",map.get("drivername"));
		businessMap.put("电话号码：",map.get("drivermobile"));
		List businessList = new ArrayList();
		businessList.add(businessMap);
		String businessJson = JsonGenerateUtil.list2json(businessList);
		//设备数据
		Map deviceMap = new HashMap();
		deviceMap.put("type", "party");
		//通过手机号获取对应的partyid
		try {
			String url = "party/partycs/selectPartyidByMobileNumber";
			ClientUtil cu = new ClientUtil(url);
			Map needMap=new HashMap();
			needMap.put("mobilenumber", map.get("drivermobile"));
			String partyid=cu.post(url,needMap).toString();
			if(partyid.equals("")){
				return "{\"msg\":\"sorry\"}";
			}
			deviceMap.put("partyid", partyid);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WebServiceException e) {
			e.printStackTrace();
		} 
		List deviceList = new ArrayList();
		deviceList.add(deviceMap);
		String deviceJson = JsonGenerateUtil.list2json(deviceList);
		String json = "{\"serviceInfo\":"+businessJson+",\"deviceInfo\":"+deviceJson+"}";
		return json;
    }
    /**
     * @author wei.huang
     * @date 2014-1-15
     * @function 调度单回放
     */
    public String playback(Map map){
    	//业务数据
		Map businessMap = new HashMap();
		businessMap.put("调度单号：",map.get("systemdispatchnumber"));
		businessMap.put("发车时间：",map.get("starttime"));
		businessMap.put("车牌号：",map.get("carplatenumber"));
		businessMap.put("司机姓名：",map.get("drivername"));
		businessMap.put("电话号码：",map.get("drivermobile"));
		businessMap.put("starttime",map.get("starttime"));
		//设备数据
		Map deviceMap = new HashMap();
		deviceMap.put("type", "party");
		deviceMap.put("validate", map.get("starttime"));
		if(map.get("status").toString().equals("已发车")){
			Date dt = new Date();   
		    //HH表示24小时制    如果换成hh表示12小时制   
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
		    businessMap.put("endtime", sdf.format(dt));
		    deviceMap.put("invaliddate", "");
			
		}else if(map.get("status").toString().equals("已到车")){
			businessMap.put("endtime",map.get("endtime"));
			businessMap.put("到达时间：",map.get("endtime"));
			deviceMap.put("invaliddate", map.get("endtime"));
		}
		List businessList = new ArrayList();
		businessList.add(businessMap);
		String businessJson = JsonGenerateUtil.list2json(businessList);
		//通过手机号获取对应的partyid
		try {
			String url = "party/partycs/selectPartyidByMobileNumber";
			ClientUtil cu = new ClientUtil(url);
			Map needMap=new HashMap();
			needMap.put("mobilenumber", map.get("drivermobile"));
			String partyid=cu.post(url,needMap).toString();
			if(partyid.equals("")){
				return "{\"msg\":\"sorry\"}";
			}
			deviceMap.put("partyid", partyid);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WebServiceException e) {
			e.printStackTrace();
		} 
		List deviceList = new ArrayList();
		deviceList.add(deviceMap);
		String deviceJson = JsonGenerateUtil.list2json(deviceList);
		String json = "{\"serviceInfo\":"+businessJson+",\"deviceInfo\":"+deviceJson+"}";
		return json;
    }
}
