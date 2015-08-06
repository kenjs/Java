package tf56.exchange.test;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.HashMap;
import java.util.Map;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;

import tf56.exchange.domain.CusPartyScores;
import tf56.exchange.services.InternalWriteScoreDao;
import tf56.sofa.util.ParseFormToBean;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/applicationContext-*.xml"})
public class InternalWriteScoreDaoImplTest {
	@Autowired
	private InternalWriteScoreDao internalWriteScoreDao;
	@Test
	public void testGetPageList() {
		Map map = new HashMap();
		map.put("cpartyname", "CDWL030193");                        //编码
		//map.put("ddate", "2013-06-06 10:22:01");      //日期
		//map.put("ipartyid", "57528");                   //会员ID
		//map.put("csource", "测试");                     //来源
		//map.put("cid", "测试");                         //流水号
		//map.put("nin", "100");                        //收入
		//map.put("nout", "80");                       //支出
		//map.put("nleft", "100");                      //剩余积分
		//map.put("cmemo", "测试");                       //备注
		//map.put("crole", "admin");                       //操作员
		//map.put("month", -1);
		//CusPartyScores cusPartyScores = new CusPartyScores();		    //把map转成bean对象
		//ParseFormToBean pftb = new ParseFormToBean();
		//internalWriteScore = (InternalWriteScore) pftb.parseToBean(map, internalWriteScore);
//		System.out.println("增  加"+internalWriteScoreDao.insert(map)); //增
//		System.out.println("删  除"+internalWriteScoreDao.delete(map));     //删
//		System.out.println("更  新"+internalWriteScoreDao.update(internalWriteScore)); //改
		System.out.println("查一条"+internalWriteScoreDao.selectById(map)); //查一条
//		System.out.println("查多条"+internalWriteScoreDao.selectListScores(map)); //查多条
	}
}