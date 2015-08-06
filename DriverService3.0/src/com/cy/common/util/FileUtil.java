package com.cy.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.lang.StringUtils;

/**
 * 文件处理类
 * @author haoyong
 *
 */
public class FileUtil {

	/**
	 * 图片删除
	 * @param imgPath
	 * @throws Exception
	 */
	public static void removeImg(String imgPath) throws Exception {
		File imgFile = null;
		try {
			imgFile = new File(imgPath);
			if(imgFile.isFile() && imgFile.exists()) {
				imgFile.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(imgFile != null && imgFile.exists()) {
				throw new RuntimeException("删除文件时发生未知错误！");
			}
		}
	}
	
	/**
	 * 上传文件
	 * @param rootFilePath 原始文件路径
	 * @param targetFilePath 上传文件路径
	 * @param targetName 自定义文件名, 如果为空, 默认当前时分秒
	 * @return 文件上传路径
	 * @throws Exception
	 */
	public static String uploadFile(String rootFilePath,String targetFilePath,String targetName) throws Exception {
		if(StringUtils.isBlank(rootFilePath) || StringUtils.isBlank(targetFilePath)) {
			throw new IllegalArgumentException("rootFilePath or targetFilePath can not be null. ");
		}		
		
		File file =  new File(rootFilePath);;
		return uploadFile(file,targetFilePath,targetName);
	}
	
	/**
	 * 上传文件
	 * @param file
	 * @param targetPath
	 * @param name 完整名称，包括文件类型
	 * @return
	 * @throws Exception
	 */
	public static String uploadFile(File file,String targetPath,String name) throws Exception {
		if(file == null || StringUtils.isBlank(targetPath) || StringUtils.isBlank(name)) {
			throw new IllegalArgumentException("file or targetPath or name can not be null. ");
		}
		File targetFile = new File(targetPath);
		if(! targetFile.exists()) {
			targetFile.mkdirs();
		}
		BufferedInputStream is = null;
		BufferedOutputStream os = null;
		if(file.isFile() && file.exists()) {
			byte[] buffer = new byte[1024];
			int byteLength = 0;
			is = new BufferedInputStream(new FileInputStream(file));
			os = new BufferedOutputStream(new FileOutputStream(targetPath + name));
			while((byteLength = is.read(buffer)) != -1) {
				os.write(buffer, 0, byteLength);
			}
			is.close();
			os.close();
		}
		
		return targetPath;
	}
	
}
