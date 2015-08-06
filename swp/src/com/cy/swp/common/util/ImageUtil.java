package com.cy.swp.common.util;

import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageUtil {

    /**
     * @param picStr Base64编码的字符窜
     * @return byte[] 类型的照片信�?
     * @throws IOException
     */
    public static byte[] fromBase64(String picStr) throws IOException {
        return new BASE64Decoder().decodeBuffer(picStr);
    }

    /**
     * @param bytes byte[]类型的照片信�?
     * @return Java Image对象。可以直接在java程序中绘制到UI界面
     */
    public static Image getImage(byte[] bytes) {
        Image image = null;
        try {
            InputStream is = new ByteArrayInputStream(bytes);
            image = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
