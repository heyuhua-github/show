package com.show.comm.utils.ext;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文件操作工具核心<br/>
 * 不对外暴露
 * 
 * @author HeHongxin
 * @date 2014-2-10
 * 
 */
class FileUtilsCore {

	private FileUtilsCore() {
	}

	private static final Logger LOG = LoggerFactory.getLogger(FileUtilsCore.class);

	/** 读取文件 */
	static File getFile(String r, String path, boolean create) {
		String p = path(path);
		if (null != p) {
			File file = new File(r + "/" + p);
			if (file.exists()) {
				return file;
			} else if (create) {
				try {
					File parent = file.getParentFile();
					if (null != parent && !parent.exists()) {
						parent.mkdirs();
					}
					if (file.createNewFile()) {
						return file;
					}
				} catch (IOException e) {
					LOG.error(e.getMessage(), e);
				}
			}
		}
		return null;
	}

	/** 判断是否文件 */
	static boolean isFile(String r, String path) {
		String p = path(path);
		if (null != p) {
			p = (r + "/" + p);
			File file = new File(p);
			return file.exists() && file.isFile();
		}
		return false;
	}

	/** 根据目录创建规则创建目录 */
	static String createFolder(String r, FileFolderRule... rule) {
		if (null != rule && rule.length > 0) {
			String path = "";
			for (FileFolderRule fr : rule) {
				path += "/" + fr.createFolder();
			}
			path = path(path);
			if (null != path) {
				File dir = new File(r + "/" + path);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				return path;
			}
		}
		return null;
	}

	/** 创建指定文件名的文件 */
	static String createFile(String r, String fileName) {
		String p = path(fileName);
		if (null != p) {
			File file = new File(r + "/" + p);
			if (!file.exists()) {
				try {
					File parent = file.getParentFile();
					if (null != parent && !parent.exists()) {
						parent.mkdirs();
					}
					file.createNewFile();
				} catch (IOException e) {
				}
			}
		}
		return p;
	}

	/** 根据目录创建规则创建目录，并在此目录下创建指文件名的文件 */
	static String createFile(String r, String fileName, FileFolderRule... rule) {
		if (null == rule || rule.length < 1) {
			return createFile(r, fileName);
		}
		String folder = createFolder(r, rule);
		if (null != folder) {
			String path = path(folder + "/" + fileName);
			if (null != path) {
				File file = new File(r + "/" + path);
				if (!file.exists()) {
					try {
						file.createNewFile();
					} catch (IOException e) {
					}
				}
			}
			return path;
		}
		return null;
	}

	/** 复制文件 */
	static boolean copyFile(InputStream is, File file) {
		File parent = file.getParentFile();
		if (null != parent && !parent.exists() && !parent.mkdirs()) {
			return false;
		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			byte[] b = new byte[256];
			int k = 0;
			while ((k = is.read(b)) != -1) {
				fos.write(b, 0, k);
			}
			fos.flush();
			return true;
		} catch (IOException e) {
			throw new FileException(FileException.Ecode.FILE_ERROR, e);
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
				}
			}
			try {
				is.close();
			} catch (IOException e) {
			}
		}
	}

	/** 重命名文件 */
	static boolean rename(String r, String source, String dest) {
		File file = new File(r + "/" + source);
		if (file.exists()) {
			File d = new File(r + "/" + dest);
			if (!d.exists()) {
				File parent = file.getParentFile();
				if (null != parent && !parent.exists() && !parent.mkdirs()) {
					return false;
				}
			} else if (d.isFile()) {
				d.delete();
			}
			return file.renameTo(d);
		}
		return false;
	}

	/** 返回路径 */
	static String path(String path) {
		if (null != path) {
			path = path.replaceAll("//+", "/");
			if (path.length() > 0) {
				if (path.charAt(0) == '/') {
					path = path.substring(1);
				}
				if (path.length() > 0) {
					return path;
				}
			}
		}
		return null;
	}

	/** 删除文件 */
	static void deleteFile(String r, String fileName) {
		File file = new File(r + File.separator + fileName);
		if (file.exists()) {
			delete(file);
		}
	}

	/**
	 * 递归删除文件
	 * 
	 * @param file
	 */
	static void delete(File file) {
		if (file.isFile()) {
			file.delete();
		} else {
			File[] files = file.listFiles();
			for (File f : files) {
				delete(f);
			}
			file.delete();
		}
	}
}
