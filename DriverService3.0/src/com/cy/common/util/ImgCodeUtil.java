package com.cy.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

public class ImgCodeUtil {

	private final static Font font = new Font("Times New Roman", Font.PLAIN, 17);
	
	
	public static String drawImg(int width,int height,HttpServletResponse response) throws IOException{
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Expires", "0");
		response.setContentType("image/jpeg");
		
		if(width <= 0) width = 80;
		if(height <= 0) height = 25;
		
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		Graphics g = image.getGraphics();
		
		g.setColor(getRandColor(200, 250));
		g.fillRect(1, 1, width - 1, height - 1);
		g.setColor(new Color(102, 102, 102));
		g.drawRect(0, 0, width - 1, height - 1);
		g.setFont(font);
		
		g.setColor(getRandColor(160, 200));
		
		Random random = new Random();
		
		//画随机线
		for(int i = 0;i < 155;i ++) {
			int x = random.nextInt(width - 1);
			int y = random.nextInt(height - 1);
			int x1 = random.nextInt(6) + 1;
			int y1 = random.nextInt(12) + 1;
			
			g.drawLine(x, y, x + x1, y + y1);
		}
		
		//从另一个方向画随机线
		for(int i = 0;i < 70;i ++) {
			int x = random.nextInt(width - 1);   
            int y = random.nextInt(height - 1);   
            int x1 = random.nextInt(12) + 1;   
            int y1 = random.nextInt(6) + 1;  
	        
            g.drawLine(x, y, x - x1, y - y1);
		}
		
		String code = "";
		for(int i = 0;i < 4;i ++) {
			char ch = (char) (random.nextInt(26) + 65);
			code += String.valueOf(ch);
			
			g.setColor(new Color(random.nextInt(110) + 20,random.nextInt(110) + 20,random.nextInt(110) + 20));
			g.drawString(String.valueOf(ch), 15 * i + 10, 16);
		}
		
		g.dispose();
		ImageIO.write(image, "JPEG", response.getOutputStream());
		
		return code;
	}
	
	static Color getRandColor(int fc,int bc) {   
        Random random = new Random();   
        if(fc>255) fc=255;   
        if(bc>255) bc=255;   
        int r=fc+random.nextInt(bc-fc);   
        int g=fc+random.nextInt(bc-fc);   
        int b=fc+random.nextInt(bc-fc);   
        return new Color(r,g,b);   
	}   
}
