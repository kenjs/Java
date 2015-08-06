package tf56.contract.services;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.formula.functions.T;

public interface ReportQueryService {
	/**
	 * @author wei.huang
	 * @date 2013-11-28
	 * @function 获取完整的满足报表信息要求的运单明细表
	 * @return Json
	 */
	public String selectWaybillDetailForReport(Map map);
	/**
	 * @author wei.huang
	 * @date 2013-12-4
	 * @function 获取导出所需的全部信息
	 * @return List<Map>
	 */
	public List<Map> selectAllForExportExcel(Map map);
	/**
	 * @author wei.huang
	 * @date 2013-12-4
	 * @function 导出Excel
	 * @param title 文件标题
	 * @param headers 表头
	 * @param map 查询参数
	 * @param response
	 */
	public void exportExcel(String title,String[] headers,HttpServletResponse response,Map map) throws IOException;
}
