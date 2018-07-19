package com.show.comm.utils.ext;

import com.show.comm.utils.StringUtils;

/**
 * 生成随机目录
 * 
 * @author HeHongxin
 * @date 2014-2-10
 * 
 */
public class FileFolderRuleRandom extends FileFolderRule {

	private int length = 0;

	/**
	 * 生成随机字符文件目录
	 * 
	 * @param 当前时间毫秒13位数字
	 */
	public FileFolderRuleRandom() {
	}

	/**
	 * 生成随机字符文件目录
	 * 
	 * @param length
	 *            0：当前时间毫秒13位数字<br/>
	 *            4-15：随机字符串<br/>
	 *            其它：抛出RuntimeException("length>=4 and length<=15")
	 */
	public FileFolderRuleRandom(int length) {
		if (length > 0) {
			if (length < 4 || length > 15) {
				throw new RuntimeException("length>=4 and length<=15");
			}
		}
		this.length = length;
	}

	/**
	 * 创建随机目录
	 */
	@Override
	public String createFolder() {
		if (length > 0) {
			return StringUtils.ranStr(length);
		} else {
			return String.valueOf(System.currentTimeMillis());
		}
	}

}
