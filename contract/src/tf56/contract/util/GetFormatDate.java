package tf56.contract.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetFormatDate {
	/**
	 * @author wei.huang
	 * @date 2013-11-07
	 * @function 获取当前日期时间
	 * @return
	 */
	public static String getCurrentDate(){
		String temp_str="";   
	    Date dt = new Date();   
	    //HH表示24小时制    如果换成hh表示12小时制   
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
	    temp_str=sdf.format(dt); 
	    return temp_str;
	}
}
