package ftp;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by haoy on 2014/12/8.
 */
public class FtpFileUtil {

    //ftp服务器地址
    private final String hostName = "";
    //ftp服务器登录名
    private final String userName = "";
    //ftp服务器密码
    private final String passWord = "";

    public void upload() {
        FTPClient ftpClient = new FTPClient();
        FileInputStream fis = null;
        try {
            //连接FTP服务器
            ftpClient.connect(hostName);
            ftpClient.login(userName, passWord);

            ftpClient.setConnectTimeout(60000);

            File srcFile = new File("source file path");
            fis = new FileInputStream(srcFile);

            //设置上次目录
            ftpClient.changeWorkingDirectory("ftp服务器的文件路径");
            ftpClient.setBufferSize(1024);
            ftpClient.setControlEncoding("UTF-8");
            //设置文件类型(二进制)
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

            //上传到FTP服务器
            ftpClient.storeFile("保存到ftp服务器的文件名称", fis);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fis);
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void downLoad() {
        FTPClient ftpClient = new FTPClient();
        FileOutputStream fos = null;

        try {
            ftpClient.connect(hostName);
            ftpClient.login(userName, passWord);

            ftpClient.setConnectTimeout(60000);

            ftpClient.setBufferSize(1024);
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

            fos = new FileOutputStream("下载的文件路径");

            ftpClient.retrieveFile("ftp服务器的文件路径", fos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fos);
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
