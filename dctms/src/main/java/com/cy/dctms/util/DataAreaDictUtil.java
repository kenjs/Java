package com.cy.dctms.util;

import java.util.List;

import com.cy.dctms.entity.DictInfo;




public class DataAreaDictUtil {

	/**
	 * 转换地区为JSON，手工封装
	 * @author hayden   2013-12-31
	 * */
	public static String converAreaListToJson(List<DictInfo> list) {
		int l = 0;
		int m = 0;
		int n = 0;
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (DictInfo area : list) {
			if (area.getParentCode().length() == 1) {
				if (l == 0) {
					sb.append("{name:'").append(area.getName()).append("',");
					l = 1;
				} else if (l == 1) {
					sb.append("]}]},");
					sb.append("{name:'").append(area.getName()).append("',");
				}
				m = 0;
				n = 0;
			}

			if (area.getParentCode().length() == 3) {
				if (m == 0) {
					sb.append("cityList:[");
					sb.append("{name:'").append(area.getName()).append("',");
					m = 1;
				} else if (m == 1) {
					sb.append("]},{name:'").append(area.getName()).append("',");
				}
				n = 0;
			}

			if (area.getParentCode().length() == 6) {
				if (n == 0) {
					sb.append("areaList:[");
					sb.append("'").append(area.getName()).append("'");
					n = 1;
				} else if (n == 1) {
					sb.append(",'").append(area.getName()).append("'");
				}
			}
		}

		sb.append("]}]}]");
		return sb.toString();
	}
}
