package com.show.comm.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * 图片工具
 * 
 * @author heyuhua
 * @date 2017年11月27日
 */
public class ImageUtils {

	/** 图片后缀 - .jpg */
	public static final String SUF_JPG = ".jpg";
	/** 图片后缀 - .png */
	public static final String SUF_PNG = ".png";
	/** 图片后缀 - .gif */
	public static final String SUF_GIF = ".gif";

	/**
	 * 压缩图片文件
	 * 
	 * <pre>
	 * 支持jpg、png格式图片文件
	 * </pre>
	 * 
	 * @param file
	 *            源图片
	 * @param target
	 *            目标图片
	 * @return true:成功
	 */
	public static boolean compress(File file, File target) throws IOException {
		return compress(new FileInputStream(file), target, 0, 0, 0);
	}

	/**
	 * 压缩图片文件
	 * 
	 * <pre>
	 * 支持jpg、png格式图片文件
	 * </pre>
	 * 
	 * @param file
	 *            源图片
	 * @param target
	 *            目标图片
	 * @param width
	 *            目标图片宽度
	 * @param height
	 *            目标图片高度
	 * @return true:成功
	 */
	public static boolean compress(File file, File target, int width, int height) throws IOException {
		return compress(new FileInputStream(file), target, width, height, 0);
	}

	/**
	 * 压缩图片文件
	 * 
	 * <pre>
	 * 支持jpg、png格式图片文件
	 * </pre>
	 * 
	 * @param file
	 *            源图片
	 * @param target
	 *            目标图片
	 * @param rate
	 *            压缩比例
	 * @return true:成功
	 */
	public static boolean compress(File file, File target, float rate) throws IOException {
		return compress(new FileInputStream(file), target, 0, 0, rate);
	}

	/**
	 * 压缩图片文件
	 * 
	 * <pre>
	 * 支持jpg、png格式图片文件
	 * </pre>
	 * 
	 * @param is
	 *            输入流
	 * @param target
	 *            目标图片
	 * @return true:成功
	 */
	public static boolean compress(InputStream is, File target) throws IOException {
		return compress(is, target, 0, 0, 0);
	}

	/**
	 * 压缩图片文件
	 * 
	 * <pre>
	 * 支持jpg、png格式图片文件
	 * </pre>
	 * 
	 * @param is
	 *            输入流
	 * @param target
	 *            目标图片
	 * @param width
	 *            目标图片宽度
	 * @param height
	 *            目标图片高度
	 * @return true:成功
	 */
	public static boolean compress(InputStream is, File target, int width, int height) throws IOException {
		return compress(is, target, width, height, 0);
	}

	/**
	 * 压缩图片文件
	 * 
	 * <pre>
	 * 支持jpg、png格式图片文件
	 * </pre>
	 * 
	 * @param is
	 *            输入流
	 * @param target
	 *            目标图片
	 * @param rate
	 *            压缩比例
	 * @return true:成功
	 */
	public static boolean compress(InputStream is, File target, float rate) throws IOException {
		return compress(is, target, 0, 0, rate);
	}

	/** 压缩图片文件 */
	private static boolean compress(InputStream is, File target, int width, int height, float rate) throws IOException {
		String path = target.getAbsoluteFile().toString();// 目标物理路径
		String format = path.substring(path.lastIndexOf(".") + 1).toLowerCase();// 格式
		boolean isJpg = format.equals("jpg") || format.equals("jpeg");
		boolean isPng = !isJpg && format.equals("png");
		boolean isGif = !isPng && format.equals("gif");
		if (!isJpg && !isPng && !isGif) {
			throw new IOException("Target is not jpg || png || gif.");
		}
		Image source = ImageIO.read(is);// 源图
		int w = width == 0 ? source.getWidth(null) : width;// 宽度
		int h = height == 0 ? source.getHeight(null) : height;// 高度
		if (rate > 0) {
			w = (int) (w * rate);
			h = (int) (h * rate);
		}
		BufferedImage buf = new BufferedImage(w, h, isJpg ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics = buf.createGraphics();
		graphics.setBackground(new Color(255, 255, 255));
		graphics.setColor(new Color(255, 255, 255));
		graphics.fillRect(0, 0, w, h);
		graphics.drawImage(source.getScaledInstance(w, h, Image.SCALE_SMOOTH), 0, 0, null);
		return ImageIO.write(buf, format, target);
	}

	/**
	 * 判断是否图片
	 * 
	 * @param name
	 *            文件名称
	 * @return 1：jpg <br/>
	 *         2：png <br/>
	 *         3：gif <br/>
	 *         0：非图片
	 */
	public static int isImg(String name) {
		int i = name.lastIndexOf(".");
		if (i > 0) {
			String s = name.substring(i + 1);
			int l = s.length();
			if (l == 3 || l == 4) {
				s = s.toLowerCase();
				if (s.equals("jpg") || s.equals("jpeg")) {
					return 1;
				} else if (s.equals("png")) {
					return 2;
				} else if (s.equals("gif")) {
					return 3;
				}
			}
		}
		return 0;
	}

	/**
	 * 获取图片后缀
	 * 
	 * @param name
	 *            图片名称
	 * @return 后缀/null
	 */
	public static String suffix(String name) {
		int i = isImg(name);
		if (i == 0) {
			return null;
		}
		if (i == 1) {
			return SUF_JPG;
		} else if (i == 2) {
			return SUF_PNG;
		} else {
			return SUF_GIF;
		}
	}

}
