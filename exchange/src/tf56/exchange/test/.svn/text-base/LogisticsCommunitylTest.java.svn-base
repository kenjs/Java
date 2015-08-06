package tf56.exchange.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tf56.exchange.services.LogisticsCommunityDao;





@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/applicationContext-*.xml" })
public class LogisticsCommunitylTest {

	@Autowired
	private LogisticsCommunityDao logisticsCommunityDao;

	/*@Test
	public void testauditUser() {
		Map map = new HashMap();
		map.put("cparty", "18057153229");
		map.put("crole", "admin");
		map.put("cpwd", "9a952cd91000872a8d7d1f5ee0c87317");
		map.put("cmd", "auditUser");
		map.put("check", "1656sadfads");
		System.out.println("aaa-->"
				+ new String(logisticsCommunityDao.auditUser(map).getBytes()));
	}*/

	@Test
	public void testqueryGoods() {		 
		Map map = new HashMap();
		map.put("cmd", "queryGoods");
		map.put("check", "d07e7372b0805d2cfaae136e28d4d868");
		map.put("ipartyid", "58914");
		map.put("cpwd", "e10adc3949ba59abbe56e057f20f883e");
		map.put("page", "1");
		map.put("pagesize", "10");
		map.put("cfromprovince", "浙江");
		map.put("cfromcity", null);
		map.put("cfromregion", null);
		map.put("ctoprovince", null);
		map.put("ctocity", null);
		map.put("ctoregion", null);
		map.put("ncarlengthmin", null);
		map.put("ncarlengthmax", null);
		map.put("ccarstruct", null);
		map.put("ntonmin", null);
		map.put("ntonmax", null);
		map.put("nvolmin", null);
		map.put("nvolmax", null);
		// map.put("ncarlengthmin", "9");
		// map.put("ncarlengthmax", "13");
		// map.put("ccarstruct", "高栏挂车");
		// map.put("ntonmin", "2.5");
		// map.put("ntonmax", "50");
		// map.put("nvolmin", "1");
		// map.put("nvolmax", "20");
		System.out.println("aaa-->" + logisticsCommunityDao.queryGoods(map));
	}
	/*@Test
	public void testpushGoods() {
		Map map=new HashMap();
		map.put("cmd", "auditUser");
		map.put("check", "1656sadfads");
		System.out.println("aaa-->"+logisticsCommunityDao.pushGoods(map));
	}*/
	
	/*@Test
	public void testpublishVehicle() {
		Map map=new HashMap();
		map.put("cmd", "publishVehicle");
		map.put("check", "1656sadfads");
		map.put("ipartyid", "58914");
		map.put("crole", "admin");
		map.put("cpwd", "e10adc3949ba59abbe56e057f20f883e");
		map.put("ccarname", "浙A1101");
		map.put("ctoprovince", "广东");
		map.put("ctocity", "东莞");
		map.put("ctoregion",null);
		map.put("cfromprovince", "浙江");
		map.put("cfromcity", "杭州");
		map.put("cfromregion", "");
		map.put("dvaliddate", "2013-03-20 00:00:00");
		map.put("dinvaliddate", "2013-03-30 23:59:59");
		System.out.println("aaa-->"+logisticsCommunityDao.publishVehicle(map));
	}*/
	
	/*@Test
	public void testqueryVehicle() {
		Map map=new HashMap();
		map.put("ipartyid", "57528");
		map.put("cpwd", "e10adc3949ba59abbe56e057f20f883e");
		map.put("cmd", "queryVehicle");
		map.put("check", "1656sadfads");
		System.out.println("aaa-->"+logisticsCommunityDao.queryVehicle(map));
	}*/
}
