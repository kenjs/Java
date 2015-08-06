package com.cy.swp.common.util;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Richie.Lee on 2014/9/19.
 */
public class FileOperateUtil {
    /**
     * 将上传的文件进行重命�?
     */
    private static String rename(String name) {
        String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSSS")
                .format(new Date());

        if (name.indexOf(".") != -1) {
            fileName += name.substring(name.lastIndexOf("."));
        }
        return fileName;
    }

    /**
     * 压缩后的文件�?
     */
    private static String zipName(String name) {
        String prefix = "";
        if (name.indexOf(".") != -1) {
            prefix = name.substring(0, name.lastIndexOf("."));
        } else {
            prefix = name;
        }
        return prefix + ".zip";
    }

    /**
     * 重命名方式上传文�?
     */
    public static Map<String, String> uploadByRename(HttpServletRequest request, String savePath) throws Exception {
        Map<String, String> reNameResult = new HashMap<String, String>();

        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = mRequest.getFileMap();

        //判断路径是否存在
        File file = new File(savePath);
        if (!file.exists()) {
            file.mkdirs();
        }

        String fileName = null;
        for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, MultipartFile> entry = it.next();
            MultipartFile mFile = entry.getValue();

            String fieldName = mFile.getName();
            String fileReName = rename(mFile.getOriginalFilename());

            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new StringBuilder(savePath).append(File.separator).append(fileReName).toString()));
            FileCopyUtils.copy(mFile.getInputStream(), outputStream);
            reNameResult.put(fieldName, fileReName);
        }
        return reNameResult;
    }

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

    public static void uploadByZip(HttpServletRequest request, String savePath) throws Exception {
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

            String zipFileName = zipName(mFile.getOriginalFilename());
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new StringBuilder(savePath).append(File.separator).append(zipFileName).toString()));
            FileCopyUtils.copy(mFile.getInputStream(), outputStream);
        }
    }

    public static Map<String, String> uploadByRenameAndZip(HttpServletRequest request, String savePath) throws Exception {
        Map<String, String> reNameResult = new HashMap<String, String>();
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

            String fieldName = mFile.getName();
            String fileReName = rename(mFile.getOriginalFilename());
            String zipFileName = zipName(fileReName);
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new StringBuilder(savePath).append(File.separator).append(zipFileName).toString()));
            FileCopyUtils.copy(mFile.getInputStream(), outputStream);
            reNameResult.put(fieldName, zipFileName);
        }
        return reNameResult;
    }


    /**
     * 下载
     */
    public static void download(HttpServletRequest request, HttpServletResponse response, String storeName, String contentType, String realName) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
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

}

