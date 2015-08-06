package tf56.exchange.services.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.PrintWriter;

import java.net.HttpURLConnection;

import java.net.URL;
import java.net.URLConnection;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import tf56.exchange.services.InvoiceDao;
import tf56.sofa.serializer.JsonGenerateUtil;


public class InvoiceDaoImpl extends SqlMapClientDaoSupport implements
		InvoiceDao {

	/**
	 * @author tlp
	 * @funtion 发票作废
	 * @return String
	 * @date 2013-01-05
	 * @throws IOException
	 */
	public String invalidInvoice(Map map) {
		// TODO Auto-generated method stub
		if (null == map.get("fphm")) {
			return "必须传入发票号码";
		}
		if ("".equals(map.get("fphm"))) {
			return "必须传入发票号码";
		}
	
		String wlslh=map.get("zjlsh").toString();
		if (null == wlslh) {
			return "网络受理号不能为空,如果该发票没有上传，不能作废";
		}
		
		try {			
			String document = createInvalidInvoiceXml(map);
			String doc;
			doc = doCCall(document,map.get("url").toString());
			// 作废："100.0.0.4", 8001, "ZyxtfpServer?","SID=zkxx.upload.zyxtfp.zf"
			String result = parserInvalidInvoiceXml(doc, wlslh);
			return result;	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @author tlp
	 * @funtion 发票上传
	 * @return String
	 * @date 2013-01-05
	 * @throws IOException
	 */
	public String uploadInvoice(Map map) {
		// TODO Auto-generated method stub
		try {

				String fphm =map.get("fphm").toString();
				String document = createUploadInvoiceXml(map);

				//return document;
				String doc = doCCall(document, map.get("url").toString());
				//上传："100.0.0.4", 8001,"ZyxtfpServer?", "SID=zkxx.upload.zyxtfp"

				String result = parserUploadInvoiceXml(doc, fphm);
				return result;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	
	}

	/**
	 * @author tlp
	 * @funtion 创建发票上传XML
	 * @return String
	 * @date 2013-01-05
	 * @throws IOException
	 */
	public String createUploadInvoiceXml(Map map) {
		/*
		 * File inputXml = new File("C:\\Users\\tlp\\Desktop\\aa.xml");
		 * SAXReader saxReader = new SAXReader(); Document document; document =
		 * saxReader.read(inputXml);
		 */
		Document document = DocumentHelper.createDocument();
		// 设置文件编码
		// document.setXMLEncoding("gbk");
		// 添加根标签
		Element fp = document.addElement("FP");
		// 添加validate标签
		Element validate = fp.addElement("VALIDATE");
		Element username = validate.addElement("USERNAME");
		username.setText("system");
		Element password = validate.addElement("PASSWORD");
		password.setText("000000");
		// 添加head标签
		Element head = fp.addElement("HEAD");
		Element nsrsbh = head.addElement("NSRSBH");// 纳税人识别号
		nsrsbh.setText(map.get("nsrsbh").toString());
		//Element kjfsbh = head.addElement("KJFSBH");
		Element fpzl = head.addElement("FPZL");
		fpzl.setText("130");
		Element zfs = head.addElement("ZFS");
		zfs.setText("1");
		// 添加fpxx标签
		Element fpxx = fp.addElement("FPXX");
		Element fpxxmx = fpxx.addElement("FPXXMX");
		Element nsrmc = fpxxmx.addElement("NSRMC");
		nsrmc.setText(map.get("nsrmc").toString());//传化公路港物流有限公司浙江分公司
		Element kpfdzjdh = fpxxmx.addElement("KPFDZJDH");// 开票方地址及电话
		kpfdzjdh.setText(map.get("kpfdzjdh").toString());//"萧山区宁围镇二桥村(新北村)82878320"
		Element kpfyhjzh = fpxxmx.addElement("KPFYHJZH");// 开票方银行及账号
		kpfyhjzh.setText(map.get("kpfyhjzh").toString());//"工行杭州二桥支行1202025209900049729"
		Element fpzl_dm = fpxxmx.addElement("FPZL_DM");// 发票种类代码（未填）
		fpzl_dm.setText(map.get("fpzl_dm").toString());//33643
		Element fpdm = fpxxmx.addElement("FPDM");// 发票代码
		fpdm.setText(map.get("fpdm").toString());
		Element fphm = fpxxmx.addElement("FPHM");// 发票号码
		fphm.setText(map.get("fphm").toString());
		Element kprq = fpxxmx.addElement("KPRQ");// 开票日期
		kprq.setText(map.get("kprq").toString());
		Element spfnsrmc = fpxxmx.addElement("SPFNSRMC");
		// spfnsrmc.setText("");
		Element spfnsrsbh = fpxxmx.addElement("SPFNSRSBH");
		// spfnsrsbh.setText("");
		Element spfzdjdh = fpxxmx.addElement("SPFDZJDH");
		// spfzdjdh.setText("");
		Element spfyhjzh = fpxxmx.addElement("SPFYHJZH");
		// spfyhjzh.setText("");
		Element hjje = fpxxmx.addElement("JE");// 合计金额
		hjje.setText(map.get("je").toString());
		Element kpr = fpxxmx.addElement("KPR");// 开票人
		kpr.setText(map.get("kpr").toString());
		Element skr = fpxxmx.addElement("SKR");// 收款人
		skr.setText(map.get("skr").toString());
		Element zfbz = fpxxmx.addElement("ZFBZ");// 作废标志
		zfbz.setText("N");
		Element hyfl = fpxxmx.addElement("HYFL");// 行业分类
		hyfl.setText("71");
		Element ghfqylx = fpxxmx.addElement("GHFQYLX");
		ghfqylx.setText("03");
		Element lzfpdm = fpxxmx.addElement("LZFPDM");
		// lzfpdm.setText("");
		Element lzfphm = fpxxmx.addElement("LZFPHM");
		// lzfphm.setText("");
		Element wspzhm = fpxxmx.addElement("WSPZHM");
		// wspzhm.setText("");
		Element dksqbhm = fpxxmx.addElement("DKSQBHM");
		// dksqbhm.setText("");
		Element se = fpxxmx.addElement("SE");
		// 添加标签MXXX
		Element mxxx = fpxxmx.addElement("MXXX");
		Element hwmc = mxxx.addElement("HWMC");// 货物名称
		hwmc.setText(map.get("hwmc").toString());
		Element sl = mxxx.addElement("SL");// 数量
		sl.setText("1");
		Element dj = mxxx.addElement("DJ");// 单价
		dj.setText(map.get("je").toString());
		Element je = mxxx.addElement("JE");// 金额
		je.setText(map.get("je").toString());

		document.setXMLEncoding("gbk");
		// 将xml转化为字符串
		return document.asXML();

	}

	/**
	 * @author tlp
	 * @funtion 解析上传XML返回结果 (自有营业系统开具网络发票交互接口)
	 * @return void
	 * @date 2013-1-01-04
	 */
	public String parserUploadInvoiceXml(String document, String invoiceCode) {
		try {
			Document doc = DocumentHelper.parseText(document);
			// 获取根标签
			Element fp = doc.getRootElement();
			// 获取RETURNSTATE标签
			Element returnstate = fp.element("RETURNSTATE");
			// 获取RETURNCODE标签
			Element returncode = returnstate.element("RETURNCODE");
			String code = returncode.getText();
			Element ZJLSH = returnstate.element("ZJLSH");
			String zjlsh = ZJLSH.getText();
			Map<String, Object> map = new HashMap<String, Object>();
			// 更新时间
			SimpleDateFormat mat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = mat.format(new Date());
			map.put("duploaddate", time);
			map.put("cuploadstate", code);
			map.put("invoicecode", invoiceCode);

			map.put("cnetworkacceptnumber", zjlsh);
			return JsonGenerateUtil.map2json(map);
		} catch (DocumentException e) {
			// System.out.println(e.getMessage());
			return e.getMessage();
		}
	}

	/**
	 * @author tlp
	 * @funtion 创建作废发票上传XML
	 * @return String
	 * @date 2013-01-05
	 * @throws IOException
	 */
	public String createInvalidInvoiceXml(Map  map) {
		// TODO Auto-generated method stub
		Document document = DocumentHelper.createDocument();
		// 添加根标签
		Element fp = document.addElement("FP");
		// 添加validate标签
		Element validate = fp.addElement("VALIDATE");
		Element username = validate.addElement("USERNAME");
		username.setText("system");
		Element password = validate.addElement("PASSWORD");
		password.setText("000000");
		// 添加head标签
		Element head = fp.addElement("HEAD");
		Element nsrsbh = head.addElement("NSRSBH");
		nsrsbh.setText("330181583218086");
		Element fpzl = head.addElement("FPZL");// 发票种类代码（未填）
		fpzl.setText("33643");
		// 添加fpmx标签
		Element fpmx = head.addElement("FPMX");
		Element fpdm = fpmx.addElement("FPDM");// 发票代码
		fpdm.setText(map.get("fpdm").toString());
		Element fphm = fpmx.addElement("FPHM");// 发票号码
		fphm.setText(map.get("fphm").toString());
		Element zjlsh = fpmx.addElement("ZJLSH");
		zjlsh.setText(map.get("zjlsh").toString());// 网络受理号
		return document.asXML();
	}

	/**
	 * @author tlp
	 * @funtion 解析作废发票返回结果XML
	 * @return String
	 * @date 2013-01-05
	 * @throws IOException
	 */
	public String parserInvalidInvoiceXml(String document, String wlslh) {
		// TODO Auto-generated method stub
		try {
			/* Document document = saxReader.read(inputXml); */
			Document doc = DocumentHelper.parseText(document);
			// 获取根标签
			Element fp = doc.getRootElement();
			// 获取RETURNSTATE标签
			Element returnstate = fp.element("FPMX");
			// 获取RETURNCODE标签
			Element jg = returnstate.element("JG");
			String jieguo=jg.getText();
			Map<String, Object> map =new HashMap<String, Object>();
			map.put("cuploadcancelstate", jieguo);
			map.put("cnetworkacceptnumber", wlslh);
			return JsonGenerateUtil.map2json(map);
		} catch (DocumentException e) {
			// System.out.println(e.getMessage());
			return e.getMessage();
		}

	}

	/**
	 * @author tlp
	 * @funtion 连接服务器，上传参数xml,返回结果XMl
	 * @return String
	 * @date 2013-01-05
	 * @throws IOException
	 */
	public String doCCall(String xmlStr,String aUrl) throws Exception {
		StringBuffer sb = new StringBuffer();
		/*String aUrl = "http://" + host + ":" + port + "/" + servletURL
				+ Parameter;*/
		HttpURLConnection urlConn = (HttpURLConnection) getRemoteAccessConn(aUrl);
		urlConn.connect();// 建立连接
		// 写
		try {
			PrintWriter out = new PrintWriter(urlConn.getOutputStream());
			out.println(xmlStr);
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return e.getMessage();
		}
		
		// 读
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					urlConn.getInputStream()));
			String s = null;
			while ((s = in.readLine()) != null) {
				sb.append(s).append("\n");
			}
			in.close();
			urlConn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return sb.toString();
	}

	/**
	 * @author tlp
	 * @funtion 获取与服务器的连接对象
	 * @return String
	 * @date 2013-01-05
	 * @throws IOException
	 */
	private URLConnection getRemoteAccessConn(String servletURL) {
		URLConnection con = null;
		URL url;
		try {
			url = new URL(servletURL);
			con = url.openConnection();

			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(true);
			con.setConnectTimeout(1000 * 2 * 60);
			con.setRequestProperty("Content-Type", "text/xml; charset=gbk");
			con.setRequestProperty("CONTENT_LENGTH", "" + "20480");
			return con;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @author tlp
	 * @funtion 将String类型的字符串构建成为一个输入流
	 * @return String
	 * @date 2013-01-05
	 * @throws IOException
	 */
	private InputStream getStringStream(String sInputString) {
		if (sInputString != null && !sInputString.trim().equals("")) {
			try {
				ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(
						sInputString.getBytes());
				return tInputStringStream;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();

			}
		}
		return null;
	}

	
}