package tf56.exchange.test;


import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;

import tf56.exchange.services.InvoiceDao;
import tf56.exchange.services.MesInterfaceDao;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/applicationContext-*.xml"})
public class MesInterfacecDaoImplTest {

	@Autowired
	private MesInterfaceDao mesInterface;
	
	@Test
	public void testuploadInvoice() {
		/*Map map=new HashMap<String, Object>();
		String s = tradeDao.selectConsignorTradePageCount(map);
		System.out.println(s);*/
		
		Map map=new HashMap();
		map.put("cuser", "");
		map.put("ccard", "");	
		map.put("chandset", "18857261860");	
		map.put("cnum", "");	
		map.put("cmsg", "测试数据，大师起床尿尿啦");	
		map.put("csenduser", "");	
		map.put("csendcard", "");	
		map.put("csendflag", "");	
		map.put("csmscorp", "物流管理公司");	
		map.put("ddatesend", "");	
		map.put("cid", "");	
		System.out.println("aaa-->"+mesInterface.writeMessage(map));
	}
	

}
