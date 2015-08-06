package tf56.contract.test;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tf56.contract.domain.SettleBillVerification;
import tf56.contract.services.PartyService;
import tf56.contract.services.SettleBillDao;
import tf56.contract.services.SettleBillVerificationDao;
import tf56.contract.services.WaybillDao;
import tf56.sofa.util.ParseFormToBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/applicationContext-*.xml" })
public class SettleBillDaoTest {
	@Autowired
	 private SettleBillDao settleBillDao;
	@Autowired
	 private PartyService partyservice;
	@Autowired
	 private SettleBillVerificationDao settleBillVerificationDao;
	@Autowired
	private WaybillDao waybillDao;
	@Test
	public void settleBillListTest(){
		Map map=new HashMap();
		map.put("settlebillid", "100");
		map.put("billnumber", "1001100");
		map.put("billamount", "1000");
		map.put("billman", "黄威");
		map.put("billdate", "2013-11-21");
		map.put("inputman", "黄威ww");
		map.put("inputdate", "2013-11-21");
//		System.err.println("查多条:"+settleBillDao.selectList(map));
//		System.err.println("查多条:"+partyservice.settleBillList(map));
//		ParseFormToBean pftb=new ParseFormToBean();
//		SettleBillVerification settleBillVerification=new SettleBillVerification();
//		settleBillVerification=(SettleBillVerification)pftb.parseToBean(map, settleBillVerification);
//		System.err.println("插一条:"+settleBillVerificationDao.insert(settleBillVerification));
		System.err.println("查多条:"+waybillDao.selectMixedInfBySettleBillId(map));
	}
	@Test
	public void settleUpdate(){
		Map map=new HashMap();
		Date date=new Date();
		DateFormat format= new java.text.SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");  
		map.put("needinoutallamount", "1234567");
		map.put("settlebillid", "9");
		map.put("startdate", format.format(date));
		map.put("enddate", format.format(date));
		System.err.println(settleBillDao.update(map));
	}
}
