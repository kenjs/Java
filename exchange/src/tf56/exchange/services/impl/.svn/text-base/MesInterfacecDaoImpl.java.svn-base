package tf56.exchange.services.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sun.org.apache.bcel.internal.generic.SIPUSH;

import tf56.exchange.domain.Invoice;
import tf56.exchange.services.InvoiceDao;
import tf56.exchange.services.MesInterfaceDao;
import tf56.exchange.services.ToPartyDao;
import tf56.sofa.serializer.JsonGenerateUtil;
import tf56.sofa.util.ClientUtil;
import tf56.sofa.util.JsonUtil;
import tf56.sofa.util.ParseFormToBean;

public class MesInterfacecDaoImpl implements MesInterfaceDao{

	/**
	 * @author tlp
	 * @funtion 发送短信---将需要发送的短信写入数据库
	 * @return String
	 * @date 2013-02-01
	 */
	 public String writeMessage(Map map) {
		// TODO Auto-generated method stub
		String json="";
		String url ="club56/messageinterfacecs/writeMessage_doPost";//根据用户名称，查询ID
		ClientUtil club56 = new ClientUtil(url);
		try {
			json = club56.post(url,map).toString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			json="";
		}
		return JsonGenerateUtil.getMsgJson(json);
	
	}

	
}