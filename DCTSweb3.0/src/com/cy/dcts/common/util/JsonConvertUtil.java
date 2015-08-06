package com.cy.dcts.common.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * json与object转换
 * @author hayden
 * */
public class JsonConvertUtil {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public List<Object> jsonStringToList(Class<?> clazz, String jsonContent) {
		try {
			JSONArray arry = JSONArray.fromObject(jsonContent);

			List<Object> list = new ArrayList<Object>();
			for (int i = 0; i < arry.size(); i++) {
				Method[] methods = clazz.getDeclaredMethods();

				Object object = clazz.newInstance();
				JSONObject jsonObject = arry.getJSONObject(i);
				for (Iterator<?> iter = jsonObject.keys(); iter.hasNext();) {
					String key = (String) iter.next();
					String value = jsonObject.get(key).toString();

					for (Method getMethod : methods) {
						if (getMethod.getName().equals("get" + key)) {
							Method setMethod = clazz.getDeclaredMethod("set" + key, getMethod.getReturnType());
							if(getMethod.getReturnType().isInstance(new Integer(0))){
								setMethod.invoke(object, Integer.parseInt(value));
							} else if(getMethod.getReturnType().isInstance(new Long(0))){
								setMethod.invoke(object, Long.parseLong(value));
							} else if(getMethod.getReturnType().isInstance(new Float(0))){
								setMethod.invoke(object, Float.parseFloat(value));
							} else if(getMethod.getReturnType().isInstance(new Double(0))){
								setMethod.invoke(object, Double.parseDouble(value));
							} else{
								setMethod.invoke(object, value);
							}
						}
					}
				}

				list.add(object);
			}
			return list;
		} catch (Exception e) {
			logger.warn("json parse to object error.", e);
			return null;
		}
	}
}
