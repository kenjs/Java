package tf56.exchange.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tf56.exchange.services.ToPartyDao;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/applicationContext-*.xml"})
public class ToPartyDaoImplTest {
	@Autowired
	private ToPartyDao topartyDao;
	

	@Test
	public void testaddParty() {
		Map map = new HashMap();
		map.put("partyname", "SZ000024");
		map.put("mobilenumber", "13004519926");
		map.put("password", "");
		System.out.println(topartyDao.addParty(map));
		//partyname=wlooo6, frompartyid=57528, partytype=个人, random=0.01961104017890547, goodssourceid=92086, carplatenumber=藏BA9112, operator=admin

	}
	
	
}
