import com.cy.dctms.service.DayCountSystemBusiService;
import com.cy.dctms.service.DaycountDriverActiveService;
import com.cy.dctms.service.DaycountDriverUserBusiService;
import com.cy.dctms.service.DaycountWebUserBusiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * Created by Richie.Lee on 2014/10/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class DaycountServiceTest extends AbstractJUnit4SpringContextTests {
    @Resource
    private DaycountDriverActiveService daycountDriverActiveService;

    @Resource
    private DaycountWebUserBusiService daycountWebUserBusiService;

    @Resource
    private DaycountDriverUserBusiService daycountDriverUserBusiService;

    @Resource
    private DayCountSystemBusiService dayCountSystemBusiService;

    /**
     * 测试司机活跃数据统计
     * @throws Exception
     */
    @Test
    public void countDriverActiveDataTesting() throws Exception {
        daycountDriverActiveService.countDriverActiveData();
    }

    /**
     * 测试企业业务数据统计
     * @throws Exception
     */
    @Test
    public void countWebUserBusiDataTesting() throws Exception {
        daycountWebUserBusiService.countWebUserBusiData();
    }

    /**
     * 测试司机业务数据统计
     * @throws Exception
     */
    @Test
    public void countDriverUserBusiDataTesting() throws Exception {
        daycountDriverUserBusiService.countDriverUserBusiData();
    }

    /**
     * 测试系统业务数据统计
     * @throws SQLException
     */
    @Test
    public void countSystemBusiDataTesting() throws SQLException {
        dayCountSystemBusiService.countSystemBusiData();
    }


}
