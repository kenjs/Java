package tf56.contract.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tf56.contract.services.OutWaybillDao;
import tf56.contract.services.OutWaybillService;
import tf56.contract.services.WaybillDao;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/applicationContext-*.xml"})
public class OutWaybillDaoImplTest {
	@Autowired
	private OutWaybillDao outWaybillDao;
	@Autowired
	private OutWaybillService outWaybillService;
//	@Test
//	public void testAddList(){
//		Map map=new HashMap();
//		map.put("partyid", "176766");
////		map.put("consignor", "杭州1");
//		map.put("fromdate", "2014-04-08");
//		map.put("todate", "2014-04-08");
//		System.err.println(outWaybillDao.selectOutWaybillByPartyId(map));
//	}
	
	
	@Test
	public void testselectOutWaybillByWaybillId(){
		Map map=new HashMap();
		map.put("waybillid", "35");
//		map.put("consignor", "杭州1");
//		map.put("fromdate", "2014-04-08");
//		map.put("todate", "2014-04-08");
		System.err.println(outWaybillService.selectOutWaybillByWaybillId(map));
	}
}
