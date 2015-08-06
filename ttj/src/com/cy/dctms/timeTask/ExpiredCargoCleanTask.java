package com.cy.dctms.timeTask;

import com.cy.dctms.service.OrderCarogoInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @description 定期把t_order_cargo_info中的失效货源保存到
 * 				t_order_cargo_history_yearmonth中并删掉
 * 				每天的 0:0:0开始执行
 * @author 		haoy
 *
 */
public class ExpiredCargoCleanTask {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Resource
	private OrderCarogoInfoService orderCarogoInfoService;

	public void execute() throws Exception{
		try {
			//开始任务
			orderCarogoInfoService.updateOrderCargo();
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}

}
