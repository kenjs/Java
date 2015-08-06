package com.cy.swp.common.util;

import org.dom4j.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class XmlParseUtil {

    /**
     * xml转map
     *
     * @param xml
     * @return
     */
    public static String getXmlStr(String xml, String key) {
        Map<String, String> map = new HashMap<String, String>();
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
            while (iterator.hasNext()) {
                Element taskIdEle = (Element) iterator.next();
                map.put(taskIdEle.getName(), taskIdEle.getText());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map.get(key);
    }

}
