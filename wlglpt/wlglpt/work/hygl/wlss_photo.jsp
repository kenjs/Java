<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ page import="java.io.*"%>
<%@ page import="java.sql.*, javax.sql.*" %>
<%@ page import="java.util.*"%>
<%@ page import="java.math.*"%>
<%
int sizes = 16 * 1024;

String photo_no = request.getParameter("photo_no");
String path=request.getSession().getServletContext().getRealPath("");
//oracle连接 
   path = path+"/WEB-INF/classes/database.properties";
   Properties pro=new Properties();
	FileInputStream str=null;
	str=new FileInputStream(path); 
	pro.load(str);
Class.forName(pro.getProperty("business_driver"));
String URL=pro.getProperty("business_url");
//连接数据库 取配置用户以免改动多处代码 by xiay 2013-09-22
String user=pro.getProperty("business_username");
String password=pro.getProperty("business_password");
Connection con = DriverManager.getConnection(URL,user,password);

//oracle连接
//String URL="jdbc:oracle:thin@localhost:1521:orcl2";
//user="system";
//password="manager";
//Connection con = DriverManager.getConnection(URL,user,password);

try{
	// 准备语句执行对象
	Statement stmt = con.createStatement();
	
	String sql = "select A.ZPDZ from HY_WLSSDJ_ZP A WHERE A.ZPSCXH = '"+photo_no+"'";
	ResultSet rs = stmt.executeQuery(sql);
	if (rs.next()) {
		String zpdz = rs.getString(1);
		//long size = b.length();
		//out.print(size);
		//byte[] bs = b.getBytes(1, (int)size);
		//
		File file=new File(zpdz);
		if(!file.exists()){
			rs.close();
			response.sendRedirect("/wlglpt/resource/wlglpt/images/qq.jpg");
		}else{
			InputStream in = new BufferedInputStream(new FileInputStream(file));
			 
			
			response.setContentType("image/jpeg"); 
			//获取输出流
			OutputStream outs = response.getOutputStream();
			byte[] buffer = new byte[sizes];
            int len = 0;
            while ((len = in.read(buffer)) > 0) {
                outs.write(buffer, 0, len);
            }
            outs.flush();
    		//输出后 控制处理再次输出时出现已调用异常 by xiay 2013-09-22 
    		outs=null;
    		response.flushBuffer();   
    		out.clear();   
    		out = pageContext.pushBody();  
    		//关闭数据源 
    		rs.close();
			
		}
		
	}else {
		rs.close();
		response.sendRedirect("/wlglpt/resource/wlglpt/images/qq.jpg");
	}
}catch(Exception e){
	e.printStackTrace();
}finally{
	//关闭连接
	con.close();
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html>