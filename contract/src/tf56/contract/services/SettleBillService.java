package tf56.contract.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public interface SettleBillService {
	
	/**
	 * @author yao.xia
	 * @date 2013-12-18
	 * @function 获取导出所需的全部信息
	 * @return List<Map>
	 */
	public List<Map> selectAllForExportExcel(Map map);
	/**
	 * @author yao.xia
	 * @date 2013-12-18
	 * @function 导出Excel 应收
	 * @param title 文件标题
	 * @param headers 表头
	 * @param map 查询参数
	 * @param response
	 */
	public void exportInExcel(String title,String[] headers,HttpServletResponse response,Map map) throws IOException;
	/**
	 * @author yao.xia
	 * @date 2013-12-18
	 * @function 导出Excel 应付
	 * @param title 文件标题
	 * @param headers 表头
	 * @param map 查询参数
	 * @param response
	 */
	public void exportOutExcel(String title,String[] headers,HttpServletResponse response,Map map) throws IOException;
}
