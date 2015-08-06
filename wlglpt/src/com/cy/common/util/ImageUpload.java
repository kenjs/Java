package com.cy.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageUpload {

	private static final int BUFFER_SIZE = 16 * 1024;
	/**
	 * 
	* @Description: 将文件保存至规定地址并以时间重新命名
	* @Note
	* @author fangweichao
	* @since 2013-9-30
	* @param imge 原文件
	* @param fileName
	* @param filepath
	* @return
	* @throws IOException
	 */
	public static String saveImg(File imge, String fileName, String filepath)
			throws IOException {
		InputStream in = null;
		OutputStream out = null;
		
		

		int indexof = fileName.lastIndexOf(".");
		String fileType = fileName.substring(indexof);//获取文件后缀名
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String time = sdf.format(date);
		sdf=new SimpleDateFormat("yyyyMMdd");
		fileName = time + fileType;//重新定义文件名
		
		File file = new File(filepath +sdf.format(date)+"\\");
		if (!file.exists()) {
			file.mkdirs();
		}//判断路径是否存在
		
		file = new File(filepath +sdf.format(date)+"\\"+ fileName);
		int i=0;//防止高并发时候出现的重复命名
		while (file.exists()) {
			fileName = time + "a" + i + fileType;
			file = new File(filepath +sdf.format(date)+"\\"+ fileName);
			i++;
		}
		
		fileName=filepath +sdf.format(date)+"\\"+ fileName;
         try {
             in = new BufferedInputStream(new FileInputStream(imge), BUFFER_SIZE);
             out = new BufferedOutputStream(new FileOutputStream(file),
                     BUFFER_SIZE);
             byte[] buffer = new byte[BUFFER_SIZE];
             int len = 0;
             while ((len = in.read(buffer)) > 0) {
                 out.write(buffer, 0, len);
             }
         }  finally {
             if (null != in) {
                     in.close();
             }
             if (null != out) {
                     out.close();
             }
         }
		

		return fileName;
	}
	/**
	 * 
	* @Description: 删除图片
	* @Note
	* @author 
	* @since 2013-10-14
	* @param filePath
	 */
	public static void deleteImg(String filePath){
		File file=new File(filePath);
		if(file.exists()){
			file.delete();
		}
		
	}
}
