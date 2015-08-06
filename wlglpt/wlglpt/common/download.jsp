<%@page import="com.cy.framework.constants.WebConstants"%>
<%@ page import="java.io.*"%>
<%@ page contentType="text/html;charset=GBK"%>
<%@ page import="java.lang.*" %>
<%@ page import="java.io.OutputStream"%>
<%
response.reset();	//如果在weblogic底下同样要加上此句，因为weblogic会向response中写东西

FileInputStream is = null;
OutputStream os = null;

String filePath = null;
String fileName = null;
long fileLength = 0;



filePath = (String)request.getAttribute(WebConstants.DOWNLOAD_SRC_PATH);

if (filePath == null) {
	throw new Exception("参数错误！");
}

File file = new File(filePath);
if (!file.exists() || !file.isFile()) {
	throw new IOException("文件不存在！");
}
//System.out.println("filePath:"+filePath);
fileLength = file.length();

if (fileLength > WebConstants.DOWNLOAD_MAX_SIZE) {
	throw new IOException("文件大小超出限制（"+WebConstants.DOWNLOAD_MAX_SIZE/1024/1024+"M）！");
}

fileName = (String)request.getAttribute(WebConstants.DOWNLOAD_FILE_NAME);
if (fileName == null) {
	//若没有设置文件名，则按源文件的文件名。
	fileName = file.getName();
}

try {
	
	os = response.getOutputStream();
	
	//客户使用保存文件的对话框
	fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
	response.setHeader("Content-disposition", "attachment;filename=" + fileName);
	
	// 具体的原因就是在tomcat中jsp编译成servlet之后在函数_jspService(HttpServletRequest request, HttpServletResponse response)的最后有一段这样的代码
	// finally {
    //    if (_jspxFactory != null) _jspxFactory.releasePageContext(_jspx_page_context);
    //  }
	// 这里是在释放在jsp中使用的对象，会调用response.getWriter(),因为这个方法是和respose.getOutputStream()相冲突的！所以会出现以上这个异常
	//在使用完输出流以后调用以下几行代码即可：begin
	if(null != out){
		out.clear();
	}
	if(null != pageContext){
		out = pageContext.pushBody();
	}
	//end
	
	
	String type = "vnd.ms-excel";
	//通知客户文件的MIME类型
	response.setContentType("application/" + type);

	//通知客户文件的长度
	response.setContentLength(new Long(fileLength).intValue());
	request.setCharacterEncoding("GBK");

	is = new FileInputStream(file);
	byte b[] = new byte[WebConstants.DOWNLOAD_BUFFER_SIZE];


	//m_response.setLocale(java.util.Locale.CHINA);

	int num = 0;
	while ((num = is.read(b)) > 0) {
		try{
		os.write(b, 0, num);
		}catch(Exception e) {
			e.printStackTrace();
		} 
	}
	b = null;


} catch (Exception e) {
	e.printStackTrace();
	throw e;
} finally {
	if (is != null) {
		try {
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	if (os != null) {
		try {
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

%>


