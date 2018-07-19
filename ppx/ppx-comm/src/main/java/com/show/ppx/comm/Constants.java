package com.show.ppx.comm;

/**
 * 常量
 * 
 * @author heyuhua
 * @date 2017年12月4日
 */
public class Constants {

	/** 平台名称 */
	public static final String NAME = "ppx";
	/** 平台名称 */
	public static final String NAME_UP = NAME.toUpperCase();

	/** 默认代理商 */
	public static final String SA_DAU = "0001";

	/** 图片格式 - jpg */
	public static final String IMG_JPG = ".jpg";
	/** 图片格式 - png */
	public static final String IMG_PNG = ".png";
	/** 图片格式 - gif */
	public static final String IMG_GIF = ".gif";

	/** 匹配表达式 - ID - 4位 */
	public static final String REG_ID4 = "[0-9]{4}";
	/** 匹配表达式 - ID - 8位 */
	public static final String REG_ID8 = "[0-9]{8}";
	/** 匹配表达式 - ID - 32位 */
	public static final String REG_ID32 = "20[0-9]{30}";
	/** 匹配表达式 - ID - 32位 - 4位前缀 */
	public static final String REG_ID32_P4 = "[0-9]{4}20[0-9]{26}";
	/** 匹配表达式 - ID - 32位 - 8位前缀 */
	public static final String REG_ID32_P8 = "[0-9]{8}20[0-9]{22}";
	/** 匹配表达式 - 数字 - 1~32位 */
	public static final String REG_NUM32 = "[0-9]{1,32}";
	/** 匹配表达式 - md5 */
	public static final String REG_MD5 = "[0-9a-z]{32}";
	/** 匹配表达式 - 时间戳 */
	public static final String REG_TS = "[1234][0-9]{12}";
	/** 匹配表达式 - 姓名/名称 - 10长度 */
	public static final String REG_NAME_10 = "[\u2E80-\u9FFFa-zA-Z0-9\\s-_.·\\(\\)（）]{1,10}";
	/** 匹配表达式 - 姓名/名称 - 32长度 */
	public static final String REG_NAME = "[\u2E80-\u9FFFa-zA-Z0-9\\s-_.·\\(\\)（）]{1,32}";
	/** 匹配表达式 - 姓名/名称 - 100长度 */
	public static final String REG_NAME_100 = "[\u2E80-\u9FFFa-zA-Z0-9\\s-_.·\\(\\)（）]{1,100}";
	/** 匹配表达式 - 姓名/名称 - 200长度 */
	public static final String REG_NAME_200 = "[\u2E80-\u9FFFa-zA-Z0-9\\s-_.·\\(\\)（）]{0,200}";
	/** 匹配表达式 - 邮件地址 */
	public static final String REG_EMAIL = "[\u2E80-\u9FFFa-zA-Z0-9\\-\\._]{1,64}@[\u2E80-\u9FFFa-zA-Z0-9\\-\\._]{3,64}";
	/** 匹配表达式 - 手机 */
	public static final String REG_PHONE = "1[0-9]{10}";
	/** 匹配表达式 - 卡号 */
	public static final String REG_CARD = "[0-9]{10}";
	/** 匹配表达式 - 字符串(2-100) */
	public static final String REG_STR_2_100 = ".{2,100}";
	/** 匹配表达式 - 字符串(2-200) */
	public static final String REG_STR_2_200 = ".{2,200}";
	/** 匹配表达式 - 字符串(2-400) */
	public static final String REG_STR_2_400 = ".{2,400}";
	/** 匹配表达式 - 安全字符串(1,64) */
	public static final String REG_STR_SAFE = "[0-9a-zA-Z\\-_]{1,64}";
	/** 匹配表达式 - 设备编号 */
	public static final String REG_DEV_CODE = "[0-9a-zA-Z\\-_]{4,32}";
	/** 匹配表达式 - WS账号 */
	public static final String REG_WS_ACC = "[0-9a-zA-Z\\-_]{4,20}";

	/** 匹配表达式 - 文件相对路径 */
	public static final String REG_FILE_PATH = "[0-9a-zA-Z_]{2,11}/[0-9a-zA-Z\\/\\.\\-_]{10,120}";

	/** 匹配表达式 - 图片名称 - Id20().jpg */
	public static final String REG_IMG_JPG = "[0-9]{20}" + IMG_JPG;
	/** 匹配表达式 - 图片名称 - Id20().png */
	public static final String REG_IMG_PNG = "[0-9]{20}" + IMG_PNG;
	/** 匹配表达式 - 图片名称 - Id20().gif */
	public static final String REG_IMG_GIF = "[0-9]{20}" + IMG_GIF;

}
