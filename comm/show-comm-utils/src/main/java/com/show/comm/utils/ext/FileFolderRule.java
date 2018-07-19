package com.show.comm.utils.ext;

/**
 * 目录创建规则
 * 
 * @author HeHongxin
 * @date 2014-2-10
 * 
 */
public class FileFolderRule {

	private String path;

	public FileFolderRule() {
	}

	/**
	 * 简单目录创建规则
	 * 
	 * @param path
	 *            相对
	 */
	public FileFolderRule(String path) {
		this.path = path;
	}

	public String createFolder() {
		return path;
	}
}
