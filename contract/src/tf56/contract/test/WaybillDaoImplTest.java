package tf56.contract.test;


import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tf56.contract.services.OutWaybillDao;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/applicationContext-*.xml"})
public class WaybillDaoImplTest {
	@Autowired
	private OutWaybillDao outWaybillDao;
	@Test
	public void testAddList(){
		Map map=new HashMap();
		map.put("partyid", "176766");
//		map.put("frompartyid", "176766");
//		map.put("inorout", "1");
		System.err.println(outWaybillDao.selectOutWaybillByPartyIdCount(map));
	}
	
}
