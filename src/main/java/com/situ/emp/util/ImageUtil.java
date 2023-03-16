package com.situ.emp.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ImageUtil {

	public static BufferedImage image(String code) {
		int width = 250, height = 100;
		// rgb不透明色
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.getGraphics();

		// 设置背景
		g.setColor(new Color(randomColor(), randomColor(), randomColor()));
		g.fillRect(0, 0, width, height);

		// 画干扰线
		g.setColor(new Color(randomColor(), randomColor(), randomColor()));
		g.drawLine(0, 0, 250, 100);
		g.drawLine(0, 100, 250, 0);
		g.drawLine(0, 90, 240, 0);
		g.drawLine(51, 10, 260, 84);
		g.drawLine(52, 12, 270, 89);
		g.drawLine(54, 14, 250, 82);
		g.drawLine(56, 16, 240, 80);
		g.drawLine(100,50,100,50);
		g.drawLine(70, 30, 150,150);
		g.drawLine(150, 150, 50, 10);
		g.drawLine(100, 150, 50, 10);
		g.drawLine(80, 130, 60, 20);
		g.drawLine(0, 10, 240, 90);
		g.drawLine(100, 100, 250, 0);
		g.drawLine(0, 150, 230, 60);
		g.drawLine(100, 0, 20,150 );
		// 设置颜色，字体，写出内容
		g.setColor(new Color(randomColor(), randomColor(), randomColor()));
		g.setFont(new Font("Times New Roman", Font.PLAIN, 80));

		g.drawString(code, 10, 80);

		// 生成图像
		g.dispose();
		return bi;
	}
	private static int randomColor() {
		return (int) (Math.random() * 255 + 1);
	}

//	public static void main(String[] args) {
//		System.out.println(randomColor());
//		for(int i = 0; i < 100; i ++) {
//			System.out.println(createCode());
//			BufferedImage b = image(createCode());
//			b.createGraphics();
//		}
//	}

	/**
	 * 随机生成4-6位 验证码（数字、大写字母、小写字母）
	 * 
	 * @return
	 */
	public static String createCode() {
		int n = (int) (Math.random() * 2 + 4);
		StringBuilder codes = new StringBuilder();
		Random r = new Random();
		// 2.定义一个for循环，随机n次，随机生成字符
		for (int i = 0; i < n; i++) {
			// 3.生成随机字符，可能是数字，大写字母，小写字母
			int num = r.nextInt(3);
			switch (num) {
			case 0:
				// 数字:0-9
				codes.append(r.nextInt(10));
				break;
			case 1:
				// 大写字母:A(65)-Z(65+25)
				char ch1 = (char) (r.nextInt(26) + 65);
				codes.append(ch1);
				break;
			case 2:
				// 小写字母:a(97)-z(97+25)
				char ch2 = (char) (r.nextInt(26) + 97);
				codes.append(ch2);
				break;
			}
		}
		return codes.toString();
	}
}
