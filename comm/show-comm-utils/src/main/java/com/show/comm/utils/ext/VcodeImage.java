package com.show.comm.utils.ext;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * 图片验证码
 * 
 * @author heyuhua
 * @date 2017年7月24日
 */
public class VcodeImage {

	/** 验证码字符 - 字母+数字 */
	private static final String VSTR_WD = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefhijklmnpqrstuvwxy";
	/** 验证码字符 - 数字 */
	private static final String VSTR_D = "0123456789";
	/** 字体 */
	private static final String FONT_NAME = "Fixedsys";
	/** 字体大小 */
	private static final int FONT_SIZE = 24;

	/** 随机数生成器 */
	private static Random random = new Random();
	/** 图片宽 */
	private static final int width = 80;
	/** 图片高 */
	private static final int height = 30;
	/*** 干扰线数量 */
	private static final int lineNum = 72;

	/** 验证码长度 */
	private int len;
	/** 是否仅数字 */
	private boolean digit;

	/** 验证码 */
	private StringBuffer sb = new StringBuffer();
	/** 图片 */
	private BufferedImage image;

	public VcodeImage() {
		this(4, false);
	}

	public VcodeImage(int len) {
		this(len, false);
	}

	public VcodeImage(int len, boolean digit) {
		this.len = len;
		this.digit = digit;
		if (len > 8) {
			throw new RuntimeException("len to large.");
		}
		create();
	}

	/**
	 * @return 验证码
	 */
	public String getVcode() {
		return sb.toString();
	}

	/**
	 * @return 图片
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * 输出jpeg图片
	 * 
	 * @param os
	 *            OutputStream
	 * @throws IOException
	 */
	public void write(OutputStream os) throws IOException {
		ImageIO.write(image, "JPEG", os);
	}

	/** 生成验证码及图片 */
	private void create() {
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);// BufferedImage类是具有缓冲区的Image类
		Graphics g = image.getGraphics();// 获取Graphics对象,对图像进行绘制操作
		// 设置背景色
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		// 设置干扰线的颜色
		g.setColor(getRandColor(110, 120));
		// 绘制干扰线
		for (int i = 0; i <= lineNum; i++) {
			drowLine(g);
		}
		// 绘制随机字符
		g.setFont(new Font(FONT_NAME, Font.ROMAN_BASELINE, FONT_SIZE));
		if (digit) {
			for (int i = 1; i <= len; i++) {
				sb.append(drowString(g, i, VSTR_D));
			}
		} else {
			for (int i = 1; i <= len; i++) {
				sb.append(drowString(g, i, VSTR_WD));
			}
		}
		g.dispose();
	}

	/** 给定范围获得随机颜色 */
	private Color getRandColor(int fc, int bc) {
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	/** 绘制字符串 */
	private String drowString(Graphics g, int i, String source) {
		g.setColor(new Color(random.nextInt(101), random.nextInt(111), random.nextInt(121)));
		String rand = String.valueOf(source.charAt(random.nextInt(source.length())));
		g.translate(random.nextInt(3), random.nextInt(3));
		g.drawString(rand, 13 * i, 22);
		return rand;
	}

	/** 绘制干扰线 */
	private void drowLine(Graphics g) {
		int x = random.nextInt(width);
		int y = random.nextInt(height);
		int x0 = random.nextInt(16);
		int y0 = random.nextInt(16);
		g.drawLine(x, y, x + x0, y + y0);
	}

}
