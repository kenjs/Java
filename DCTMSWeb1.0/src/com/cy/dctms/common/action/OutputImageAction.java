package com.cy.dctms.common.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 从本地文件夹中读取图片显示到jsp页面
 * @author zdy
 *
 */
public class OutputImageAction extends BasePageAction{
	private static final long serialVersionUID = 6601989558296876680L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected String execMethod() throws Exception {
		logger.debug("local image output to jsp begin!");
		//获取jsp页面 url后面挂的参数即图片路径（中文乱码得转码new String()）
		String imgPath = new String(request.getParameter("fileUrl").getBytes("iso8859-1"),"utf-8") ;
		File file = new File(imgPath);//ImgPath是图像文件的路径
		try {
			if (file.exists()) { 
				response.reset();
				response.setContentType("image/jpeg");
				response.setContentLength((int) file.length());
				FileInputStream fIS = new FileInputStream(file);
				ServletOutputStream servletOut = response.getOutputStream();
			 
				byte[] buf = new byte[1024];
				int iRead = 0;
				while (true) { 
					iRead = fIS.read(buf);
					if (iRead > 0) { 
						servletOut.write(buf, 0, iRead);
					} else
						break;
				}
				fIS.close();
				servletOut.flush();
				servletOut.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.debug("local image output to jsp success!");
		return null;
	}

}
