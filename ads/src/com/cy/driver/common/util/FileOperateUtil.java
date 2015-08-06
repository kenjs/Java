package com.cy.driver.common.util;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by haoy on 2014/10/14.
 */
public class FileOperateUtil {

    /**
     * 多文件上传
     * @param request
     * @param savePath
     * @throws Exception
     */
    public static void upload(HttpServletRequest request, String savePath) throws Exception {
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = mRequest.getFileMap();

        //判断路径是否存在
        File file = new File(savePath);
        if (!file.exists()) {
            file.mkdirs();
        }

        for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, MultipartFile> entry = it.next();
            MultipartFile mFile = entry.getValue();

            String fileName = mFile.getOriginalFilename();
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new StringBuilder(savePath).append(File.separator).append(fileName).toString()));
            FileCopyUtils.copy(mFile.getInputStream(), outputStream);
        }
    }

    /**
     * 下载
     * @param request
     * @param response
     * @param storeName
     * @param contentType
     * @param realName
     * @throws Exception
     */
    public static void download(HttpServletRequest request, HttpServletResponse response, String storeName, String contentType, String realName) throws Exception {
        //response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

//        String ctxPath = request.getSession().getServletContext().getRealPath("/") + FileOperateUtil.UPLOADDIR;
        String ctxPath = request.getSession().getServletContext().getRealPath("/");
        String downLoadPath = ctxPath + storeName;

        long fileLength = new File(downLoadPath).length();

        response.setContentType(contentType);
        response.setHeader("Content-disposition", "attachment; filename="
                + new String(realName.getBytes("utf-8"), "ISO8859-1"));
        response.setHeader("Content-Length", String.valueOf(fileLength));

        bis = new BufferedInputStream(new FileInputStream(downLoadPath));
        bos = new BufferedOutputStream(response.getOutputStream());
        byte[] buff = new byte[2048];
        int bytesRead;
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
            bos.write(buff, 0, bytesRead);
        }
        bis.close();
        bos.close();
    }

    /**
     *
     * @param request
     * @param response
     * @param realPath
     * @param contentType
     * @param realName
     * @throws Exception
     */
    public static void downloadFromDisk(HttpServletRequest request, HttpServletResponse response, String realPath, String contentType, String realName) throws Exception{
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        long fileLength = new File(realPath).length();

        response.setContentType(contentType);
        response.setHeader("Content-disposition", "attachment; filename="
                + new String(realName.getBytes("utf-8"), "ISO8859-1"));
        response.setHeader("Content-Length", String.valueOf(fileLength));

        bis = new BufferedInputStream(new FileInputStream(realPath));
        bos = new BufferedOutputStream(response.getOutputStream());
        byte[] buff = new byte[2048];
        int bytesRead;
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
            bos.write(buff, 0, bytesRead);
        }
        bis.close();
        bos.close();
    }

    /**
     * 获取上传文件的名称
     * @param request
     * @return
     * @throws Exception
     */
    public static String getUploadFileName(HttpServletRequest request) throws Exception {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartHttpServletRequest.getFile("file");
        boolean isEmpty = multipartFile.isEmpty();
        if (isEmpty) {
            return "";
        }
        return multipartFile.getOriginalFilename();
    }
}
