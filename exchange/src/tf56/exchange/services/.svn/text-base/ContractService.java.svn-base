package tf56.exchange.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;


public interface ContractService {
	public String sendOderXml(Map map);//发送订单XML
	public String sendWaybillXml(String sendStr);//发送运单XML
	public void outputXml(String xmlStr,HttpServletResponse response);

	public String sendXml2Ryp(Map map) throws Exception;//发送xml到化工接口
}
