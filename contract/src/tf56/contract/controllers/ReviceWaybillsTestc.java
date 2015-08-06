package tf56.contract.controllers;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import freemarker.log.Logger;

import net.sf.serfj.RestController;
import net.sf.serfj.annotations.POST;
import tf56.sofa.serializer.JsonResponse;

public class ReviceWaybillsTestc extends RestController{
	private final Logger log = Logger.getLogger("ReviceWaybillsTestc.java");
	
	/**
	 * @author yao.xia
	 * @date 204-04-18
	 * @function 模拟化工接收配载运单，返回xml
	 */
	@POST
    public String reviceWaybills()throws IOException{
		 Map map = this.getParams();
        // 获取收到的报文
        System.out.println("获取的报文："+map.toString());
//        
        // 要返回的报文
        StringBuffer resultBuffer = new StringBuffer();
        resultBuffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        resultBuffer.append("<report_data>");
        resultBuffer.append("<respon_req>953947334</respon_req>");
        resultBuffer.append("<respon_time>20120402113943</respon_time>");
        resultBuffer.append("<result>");
        resultBuffer.append("<id>0000</id>");
        resultBuffer.append("<comment>成功</comment>");
        resultBuffer.append("</result>");
        resultBuffer.append("</report_data>");

        // 设置发送报文的格式
        HttpServletResponse response = this.getResponseHelper().getResponse();
        response.setContentType("text/xml");
        response.setCharacterEncoding("UTF-8");
        return new JsonResponse().responseJson(response, resultBuffer.toString()); // 返回
    }
}
