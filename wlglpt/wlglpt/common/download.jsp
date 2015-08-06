<%@page import="com.cy.framework.constants.WebConstants"%>
<%@ page import="java.io.*"%>
<%@ page contentType="text/html;charset=GBK"%>
<%@ page import="java.lang.*" %>
<%@ page import="java.io.OutputStream"%>
<%
response.reset();	//�����weblogic����ͬ��Ҫ���ϴ˾䣬��Ϊweblogic����response��д����

FileInputStream is = null;
OutputStream os = null;

String filePath = null;
String fileName = null;
long fileLength = 0;



filePath = (String)request.getAttribute(WebConstants.DOWNLOAD_SRC_PATH);

if (filePath == null) {
	throw new Exception("��������");
}

File file = new File(filePath);
if (!file.exists() || !file.isFile()) {
	throw new IOException("�ļ������ڣ�");
}
//System.out.println("filePath:"+filePath);
fileLength = file.length();

if (fileLength > WebConstants.DOWNLOAD_MAX_SIZE) {
	throw new IOException("�ļ���С�������ƣ�"+WebConstants.DOWNLOAD_MAX_SIZE/1024/1024+"M����");
}

fileName = (String)request.getAttribute(WebConstants.DOWNLOAD_FILE_NAME);
if (fileName == null) {
	//��û�������ļ�������Դ�ļ����ļ�����
	fileName = file.getName();
}

try {
	
	os = response.getOutputStream();
	
	//�ͻ�ʹ�ñ����ļ��ĶԻ���
	fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
	response.setHeader("Content-disposition", "attachment;filename=" + fileName);
	
	// �����ԭ�������tomcat��jsp�����servlet֮���ں���_jspService(HttpServletRequest request, HttpServletResponse response)�������һ�������Ĵ���
	// finally {
    //    if (_jspxFactory != null) _jspxFactory.releasePageContext(_jspx_page_context);
    //  }
	// ���������ͷ���jsp��ʹ�õĶ��󣬻����response.getWriter(),��Ϊ��������Ǻ�respose.getOutputStream()���ͻ�ģ����Ի������������쳣
	//��ʹ����������Ժ�������¼��д��뼴�ɣ�begin
	if(null != out){
		out.clear();
	}
	if(null != pageContext){
		out = pageContext.pushBody();
	}
	//end
	
	
	String type = "vnd.ms-excel";
	//֪ͨ�ͻ��ļ���MIME����
	response.setContentType("application/" + type);

	//֪ͨ�ͻ��ļ��ĳ���
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


