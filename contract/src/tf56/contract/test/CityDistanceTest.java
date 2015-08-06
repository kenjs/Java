package tf56.contract.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tf56.contract.domain.CityDistance;
import tf56.contract.services.CityDistanceDao;
import tf56.contract.services.OrganizationService;
import tf56.contract.services.ShipperRelaSubContractorDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/applicationContext-*.xml" })
public class CityDistanceTest {

    @Autowired
    private CityDistanceDao cityDistanceDao;
    @Autowired
    private OrganizationService org;
    @Autowired
    private ShipperRelaSubContractorDao shipperRelaSubContractorDao;
    @Test
    public void test1() {
//        String fromPartyId = "118402";
//        String partyId = "87";
//        List<CityDistance> result = cityDistanceDao.selectList(fromPartyId, partyId);
//        System.out.println(result.size());
//        Assert.assertNotNull(result);
    }
    @Test
    public void selectConsignor(){
    	//天地物流
    	Random random=new Random();
    	String organizationName="天地物流-"+random.nextInt(1000000);
    	System.err.println(org.selecteOrganizationIdByName(organizationName));
    	/*Map map=new HashMap();
    	map.put("partyid", "87");
    	map.put("partytype", "发货方");
    	System.err.println(shipperRelaSubContractorDao.contractAndSubcontractList("87", "发货方", ""));*/
    }
}
