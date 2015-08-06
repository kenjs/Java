package tf56.consignor.services.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.serfj.client.WebServiceException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import tf56.consignor.services.WaybillService;
import tf56.sofa.json.Json2ObjectUtil;
import tf56.sofa.util.ClientUtil;

public class WaybillServiceImpl implements WaybillService {

	@Override
	public void exportExcel(String title, String[] headers,
			HttpServletResponse response, Map formMap) throws IOException {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth(15);
		// 生成报表标题样式
		HSSFCellStyle titleStyle = workbook.createCellStyle();
		titleStyle.setFillForegroundColor(HSSFColor.WHITE.index);
		titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		titleStyle.setBorderBottom(HSSFCellStyle.NO_FILL);
		titleStyle.setBorderLeft(HSSFCellStyle.NO_FILL);
		titleStyle.setBorderRight(HSSFCellStyle.NO_FILL);
		titleStyle.setBorderTop(HSSFCellStyle.NO_FILL);
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成标题字体
		HSSFFont titleFont = workbook.createFont();
		titleFont.setColor(HSSFColor.BLUE.index);
		titleFont.setFontHeightInPoints((short) 28);
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		titleStyle.setFont(titleFont);
		// 生成表头样式
		HSSFCellStyle headStyle = workbook.createCellStyle();
		// 设置这些样式
		headStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		headStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		headStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		headStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		headStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成表头字体
		HSSFFont headFont = workbook.createFont();
		headFont.setColor(HSSFColor.VIOLET.index);
		headFont.setFontHeightInPoints((short) 12);
		headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		headStyle.setFont(headFont);
		// 生成主体样式
		HSSFCellStyle commonStyle = workbook.createCellStyle();
		commonStyle.setFillForegroundColor(HSSFColor.WHITE.index);
		commonStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		commonStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		commonStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		commonStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		commonStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		HSSFFont commonFont = workbook.createFont();
		commonFont.setColor(HSSFColor.BLACK.index);
		commonFont.setFontHeightInPoints((short) 12);
		commonFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		commonStyle.setFont(commonFont);
		// 生成标记字体
		HSSFCellStyle markStyle = workbook.createCellStyle();
		markStyle.setFillForegroundColor(HSSFColor.WHITE.index);
		markStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		markStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		markStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		markStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		markStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		HSSFFont markFont = workbook.createFont();
		markFont.setColor(HSSFColor.RED.index);
		markFont.setFontHeightInPoints((short) 12);
		markFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		markStyle.setFont(markFont);
		// 产生表格标题和表头行
		HSSFRow row = sheet.createRow(0);
		for (int y = 0; y < headers.length; y++) {
			// 表头
			HSSFCell headCell = row.createCell(y);
			headCell.setCellStyle(headStyle);
			HSSFRichTextString text = new HSSFRichTextString(headers[y]);
			headCell.setCellValue(text);
		}
		// 获取数据
		List<Map> list = selectAllForExportExcel(formMap);
		int rowIndex = 0;
		if (list.size() > 0) {
			// 冻结第一行和第一列
			sheet.createFreezePane(0, 3);
			// 生成主体部分的数据
			HSSFRow commonRow = null;
			for (int i = list.size() - 1; i >= 0; i--) {
				rowIndex++;
				commonRow = sheet.createRow(rowIndex);
				for (int j = 0; j < headers.length; j++) {
					HSSFCell commonCell = commonRow.createCell(j);
					commonCell.setCellStyle(commonStyle);
					switch (j) {
					case 0:
						commonCell
								.setCellValue(list.get(i).get("clientnumber") == null ? ""
										: list.get(i).get("clientnumber")
												.toString());
						continue;
					case 1:
						commonCell.setCellValue(list.get(i)
								.get("waybillnumber") == null ? "" : list
								.get(i).get("waybillnumber").toString());
						continue;
					case 2:
						commonCell
								.setCellValue(list.get(i).get("status") == null ? ""
										: list.get(i).get("status").toString());
						continue;
					case 3:
						commonCell
								.setCellValue(list.get(i).get("consigndate") == null ? ""
										: list.get(i).get("consigndate")
												.toString());
						continue;
					case 4:
						commonCell
								.setCellValue((list.get(i).get(
										"consignorprovince") == null ? ""
										: list.get(i).get("consignorprovince")
												.toString())
										+ (list.get(i).get("consignorcity") == null ? ""
												: list.get(i).get(
														"consignorcity")
														.toString())
										+ (list.get(i).get("consignorregion") == null ? ""
												: list.get(i).get(
														"consignorregion")
														.toString()));
						continue;
					case 5:
						commonCell
								.setCellValue((list.get(i).get("consignee") == null ? ""
										: list.get(i).get("consignee")
												.toString()));
						continue;
					case 6:
						commonCell
								.setCellValue((list.get(i).get(
										"consigneeprovince") == null ? ""
										: list.get(i).get("consigneeprovince")
												.toString())
										+ (list.get(i).get("consigneecity") == null ? ""
												: list.get(i).get(
														"consigneecity")
														.toString())
										+ (list.get(i).get("consigneeregion") == null ? ""
												: list.get(i).get(
														"consigneeregion")
														.toString()));
						continue;
					case 7:
						commonCell
								.setCellValue(list.get(i).get("goodsname") == null ? "0.0"
										: list.get(i).get("goodsname")
												.toString());
						continue;
					case 8:
						commonCell
								.setCellValue(list.get(i).get("factnum") == null ? "0.0"
										: list.get(i).get("factnum").toString());
						continue;
					case 9:
						commonCell
								.setCellValue(list.get(i).get("factweight") == null ? "0.0"
										: list.get(i).get("factweight")
												.toString());
						continue;
					case 10:
						commonCell
								.setCellValue(list.get(i).get("factvolume") == null ? "0.0"
										: list.get(i).get("factvolume")
												.toString());
						continue;
					}
				}
			}
		}
		rowIndex++;

		ServletOutputStream outStream = null;
		try {
			response.reset();
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ new String(title.getBytes("gb2312"), "ISO-8859-1")
					+ ".xls");
			outStream = response.getOutputStream();
			workbook.write(outStream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			outStream.close();
		}

	}

	private List<Map> selectAllForExportExcel(Map<String, Object> formMap) {
		String url = "contract/waybillcs/exportExcel";
		ClientUtil cu = new ClientUtil(url);
		String msg = "";
		try {
			msg = cu.post(url, formMap).toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WebServiceException e) {
			e.printStackTrace();
		}
		List list = Json2ObjectUtil.parseJSON2List(msg);
		return list;
	}

}
