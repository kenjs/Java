package tf56.exchange.test;


import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;

import tf56.exchange.services.InvoiceDao;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/applicationContext-*.xml"})
public class InvoiceDaoImplTest {

	@Autowired
	private InvoiceDao invoiceDao;
	
	@Test
	public void testuploadInvoice() {
		/*Map map=new HashMap<String, Object>();
		String s = tradeDao.selectConsignorTradePageCount(map);
		System.out.println(s);*/
		
		Map map=new HashMap();
		map.put("tradeid", "96");		
		System.out.println("aaa-->"+invoiceDao.uploadInvoice(map));
		
	}
	

}
