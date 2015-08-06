package tf56.exchange.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import net.sf.serfj.RestController;
import net.sf.serfj.annotations.GET;
import net.sf.serfj.annotations.POST;
import tf56.exchange.services.LogisticsCommunityDao;
import tf56.sofa.json.Json2ObjectUtil;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.serializer.JsonResponse;
import tf56.sofa.util.MD5;
import tf56.sofa.util.SofaSpringContext;
import tf56.sofa.util.SysUtil;

/**
 * 
 * @author tlp
 * @funtion 物流社区接口
 * @date 2013-03-013
 */
public class Logisticscommunityc extends RestController {
	/**
	 * 测试页面
	 */
	@GET
	public void test() {

	}

	/**
	 * 
	 * @author tlp
	 * @funtion 物流社区接口
	 * @date 2013-03-013
	 */
	@POST
	public String verification() throws IOException {
		String json = "";
		// 接受请求,获取JSON字符串
		HttpServletRequest request = this.getResponseHelper().getRequest();
		BufferedReader br = new BufferedReader(new InputStreamReader(
				(ServletInputStream) request.getInputStream()));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		// JSONObject jb = JSONObject.fromObject(sb.toString());
		//System.out.println("JSON:" + sb.toString());
		
		/*Map map=this.getParams(); 
		map = SysUtil.removeFilter(map); 
		map= SysUtil.getIsoDecodeMap(map); 
		System.out.println("map:"+map);
		String mapStr=map.toString().substring(1, map.toString().length()-2);
		System.out.println("mapStr:"+mapStr);*/
		HttpServletResponse response = this.getResponseHelper().getResponse();
		//response.setContentType("application/json;charset=UTF-8");
		LogisticsCommunityDao logisticscommunityDao = (LogisticsCommunityDao) SofaSpringContext
				.getBean("logisticsCommunityDao");// 得到Dao bean
		// 构建传入参数
		Map parm = new HashMap();
		// 解析json
		JSONObject jo = null;
		String check = "", cmd = "", timestamp = "";
		try {
			jo = new JSONObject(sb.toString());
			//jo = new JSONObject(mapStr);
			// 获取签名
			check = jo.getString("check");
			// 获取命令类型
			cmd = jo.getString("cmd");
			// 获取时间戳
			timestamp = jo.getString("timestamp");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return new JsonResponse().responseJson(response,
					getDataErrFormat(check, "1", "接收到的包不符合所规定的格式，无法解析"));
		}
		// 判断是否符合所需格式
		if (null == check || null == cmd || null == timestamp)
			return new JsonResponse().responseJson(response,
					getDataErrFormat(check, "1", "接收到的包不符合所规定的格式，无法解析"));
		// 匹配签名是否相同
		if (check.toLowerCase().equals(MD5.md5(cmd + "transfar56" + timestamp))
				|| check.toLowerCase().toUpperCase()
						.equals(MD5.md5(cmd + "transfar56" + timestamp))) {
			// 判断时间戳是否明显不对
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			long num = 0;
			// 查看传过来的时间戳是否与当前时间相差一天以上
			try {
				Date tamp = df.parse(timestamp);
				Date now = new Date();
				String n=df.format(now);
				now=df.parse(n);
				long DAY = 24L * 60L * 60L * 1000L;
				num = (now.getTime() - tamp.getTime()) / DAY;
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new JsonResponse().responseJson(response,
						getDataErrFormat(check, "3", "JSON包中的时间戳明显不对"));
			}
			if (Math.abs(num) > 1) {
				return new JsonResponse().responseJson(response,
						getDataErrFormat(check, "3", "JSON包中的时间戳明显不对"));
			}
			// 根据命令类型匹配操作
			JSONObject j;
			if ("auditUser".equals(cmd)) {
				try {
					j = jo.getJSONObject("para");
					// 传入参数
					parm = parseJsonParameter(j);
					parm.put("check", check);
					parm.put("cmd", cmd);

					json = logisticscommunityDao.auditUser(parm);
					// json=logisticscommunityDao.auditUser(map);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return new JsonResponse()
							.responseJson(
									response,
									getDataErrFormat(check, "1",
											"接收到的包不符合所规定的格式，无法解析"));
				}

			} else if ("resetPassword".equals(cmd)) {
				try {
					j = jo.getJSONObject("para");
					// 传入参数
					parm = parseJsonParameter(j);
					parm.put("check", check);
					parm.put("cmd", cmd);
					json = logisticscommunityDao.resetPassword(parm);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return new JsonResponse()
							.responseJson(
									response,
									getDataErrFormat(check, "1",
											"接收到的包不符合所规定的格式，无法解析"));
				}
			} else if ("queryGoods".equals(cmd)) {
				try {
					j = jo.getJSONObject("para");
					// 传入参数
					parm = parseJsonParameter(j);
					parm.put("check", check);
					parm.put("cmd", cmd);
					json = logisticscommunityDao.queryGoods(parm);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return new JsonResponse()
							.responseJson(
									response,
									getDataErrFormat(check, "1",
											"接收到的包不符合所规定的格式，无法解析"));
				}
			} else if ("queryVehicle".equals(cmd)) {
				try {
					j = jo.getJSONObject("para");
					// 传入参数
					parm = parseJsonParameter(j);
					parm.put("check", check);
					parm.put("cmd", cmd);
					json = logisticscommunityDao.queryVehicle(parm);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return new JsonResponse()
							.responseJson(
									response,
									getDataErrFormat(check, "1",
											"接收到的包不符合所规定的格式，无法解析"));
				}
			} else if ("publishVehicle".equals(cmd)) {
				try {
					j = jo.getJSONObject("para");
					// 传入参数
					parm = parseJsonParameter(j);
					parm.put("check", check);
					parm.put("cmd", cmd);
					json = logisticscommunityDao.publishVehicle(parm);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return new JsonResponse()
							.responseJson(
									response,
									getDataErrFormat(check, "1",
											"接收到的包不符合所规定的格式，无法解析"));
				}
			} else if("updateLocation".equals(cmd)){
				try {
					j = jo.getJSONObject("para");
					// 传入参数
					parm = parseJsonParameter(j);
					parm.put("check", check);
					parm.put("cmd", cmd);
					json = logisticscommunityDao.updateLocation(parm);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return new JsonResponse()
							.responseJson(
									response,
									getDataErrFormat(check, "1",
											"接收到的包不符合所规定的格式，无法解析"));
				}
			} else if ("pushGoods".equals(cmd)) {	
					//当cmd为check时，不在经行解析JSON对象
					//System.out.println("jo_para:"+j);
					parm.put("check", check);
					parm.put("cmd", cmd);
					json = logisticscommunityDao.pushGoods(parm);
			} else {
				// 如果以上都没有匹配，那么返回命令异常 para：出错原因，1
				// JSON包解析出错，2校验码不正确，3无法处理该命令，4命令参数有误。
				json = getDataErrFormat(check, "4", "命令参数有误");
			}
			return new JsonResponse().responseJson(response, json);

		}
		return new JsonResponse().responseJson(response,
				getDataErrFormat(check, "2", "JSON包中的签名不正确"));
	}

	/**
	 * 
	 * @author tlp
	 * @funtion 物流社区接口数据错误格式
	 * @date 2013-03-013
	 */
	private String getDataErrFormat(String check, String code, String result) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timestamp = format.format(new Date());
		Map m = new HashMap<String, Object>();
		m.put("check", check);
		m.put("cmd", "json_error");
		m.put("timestamp", timestamp);
		m.put("code", code);
		m.put("result", result);
		return JsonGenerateUtil.map2json(m);
	}

	/**
	 * 
	 * @author tlp
	 * @throws JSONException
	 * @funtion 解析JSON参数
	 * @date 2013-03-013
	 */
	private Map parseJsonParameter(JSONObject json) throws JSONException {
		Map map = new HashMap<String, Object>();
			Iterator keyIter = json.keys();
			while (keyIter.hasNext()) {
				String key = (String) keyIter.next();
				String value=json.getString(key);
				if(null!=value||"".equals(value)){
					map.put(key, value);
				}
			}
			return map;
		
	}
	/**
	 * 
	 * @author tlp
	 * @throws JSONException
	 * @funtion 解析JSON参数
	 * @date 2013-03-013
	 */
	private Map parseJsonParameter(String json) throws JSONException {
		//import org.codehaus.jettison.json.JSONException;
		//import org.codehaus.jettison.json.JSONObject;
		JSONObject jo = new JSONObject(json);
		Map map = new HashMap<String, Object>();
			Iterator keyIter = jo.keys();
			while (keyIter.hasNext()) {
				String key = (String) keyIter.next();
				String value=jo.getString(key);
				if(null!=value||"".equals(value)){
					map.put(key, value);
				}
			}
			return map;
		
	}
	public static void main(String[] args) {
		String para = "{" + "\"check\"" + ":" + "\"*******\"" + "," + "\"cmd\""
				+ ":" + "\"auditUser\"" + "," + "\"timestamp\"" + ":"
				+ "\"2013-02-12 23:00:00\"" + "," + "\"para\"" + ":{"
				+ "\"cparty\"" + ":\"18057153229\"," + "\"crole\":"
				+ "\"admin\"," + "\"cpwd\":"
				+ "\"55c1b5f4d56498b9913a4a14fdd9313c\" }}";


		try {
			JSONObject jo = new JSONObject(para);
			String check = jo.getString("check");
			String cmd = jo.getString("cmd");
			String timestamp = jo.getString("timestamp");
			Map m = new HashMap<String, Object>();
			JSONObject j = jo.getJSONObject("para");
			Iterator keyIter = j.keys();
			while (keyIter.hasNext()) {
				String key = (String) keyIter.next();
				m.put(key, j.get(key));
			}
			// m.put("cparty", j.get("cparty"));
			// m.put("crole", j.get("crole"));
			// m.put("cpwd", j.get("cpwd"));
			// System.out.println("check:"+check);
			// System.out.println("cmd:"+cmd);
			// System.out.println("timestamp:"+timestamp);
			System.out.println("map:" + m);
			System.out.println(MD5
					.md5("auditUsertransfar562013-12-29 11:54:47"));
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(df.format(new Date()));
			long a=24L*60L*60L*1000L;
			long b=1364799585284l-1356843600022l;
			long c=b/a;
			System.out.println(c);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			System.out.println("JSON 解析错误");
			e.printStackTrace();
		}

	}
}
