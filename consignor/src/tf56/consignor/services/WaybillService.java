package tf56.consignor.services;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;



public interface WaybillService {

	public void exportExcel(String title,String[] headers,HttpServletResponse response,Map map) throws IOException;
	
}