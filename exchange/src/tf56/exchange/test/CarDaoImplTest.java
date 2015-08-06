package tf56.exchange.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tf56.exchange.services.CarDao;
import tf56.exchange.services.ToPartyDao;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/applicationContext-*.xml"})
public class CarDaoImplTest {
	@Autowired
	private CarDao carService;
	

	@Test
	public void updateCarLocation() {
		Map map = new HashMap();
		map.put("latitude", "120.191889");
		map.put("longitude", "30.265146");
		map.put("carplatenumber", "赣F88116");
		map.put("lbsdate", "2014-02-29 12:12:12");
		System.out.println(carService.updateCarLocation(map));
		//partyname=wlooo6, frompartyid=57528, partytype=个人, random=0.01961104017890547, goodssourceid=92086, carplatenumber=藏BA9112, operator=admin

	}
	
	
}
