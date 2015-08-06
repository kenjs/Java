package com.cy.dctms.common.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class XmlParseUtil {

	/**
	 * xmlתmap
	 * @param xml
	 * @return
	 */
	public static String getXmlStr(String xml,String key) {
		Map<String,String> map = new HashMap<String, String>();
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xml);
			Element rootEle = doc.getRootElement();
			List<Attribute> list = rootEle.attributes();
			for (Attribute attribute : list) {
				String name = attribute.getName();
				String value = attribute.getValue();
				map.put(name, value);
			}
			Iterator iterator = rootEle.elementIterator("taskid");
			while(iterator.hasNext()) {
				Element taskIdEle = (Element) iterator.next();
				map.put(taskIdEle.getName(), taskIdEle.getText());
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} 
		return map.get(key);
	}
	
	public static void main(String[] args) {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><result  status=\"2\" statustext=\"参数缺失\"><taskid></taskid></result>";
		System.out.println(getXmlStr(xml,"status"));
	}
}
