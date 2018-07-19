package com.show.comm.utils.ext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.show.comm.utils.DateUtils;
import com.show.comm.utils.IdUtils;
import com.show.comm.utils.ImageUtils;

/**
 * 文件操作工具类
 * 
 * <pre>
 * 1、支持静态资源读取;
 * 2、支持公开文件操作;
 * 3、支持私有文件操作;
 * 4、支持临时文件操作
 * </pre>
 * 
 * <pre>
 * 1、请在启动时，调用 {@link #init(String, String, String)}方法初始化！
 * </pre>
 * 
 * @author heyuhua
 * @date 2017年12月4日
 */
public class FileUtils {

	private FileUtils() {
	}

	/** 日志 */
	private static final Logger LOG = LoggerFactory.getLogger(FileUtils.class);
	/** 临时文件目录前缀 */
	private static final String TEMP_P = "_t_";
	/** 临时文件匹配表达式 */
	private static final String REG_TEMP = "_t_[0-9]{8}/[0-9]{20}.[0-9a-zA-Z]{1,10}";
	/** 临时文件目录匹配表达式 */
	private static final String REG_TEMP_DIR = "_t_[0-9]{8}";
	/** 文件表达式 */
	private static final String REG_FILE = "[0-9a-zA-Z]{2,8}/[0-9a-zA-Z\\/\\.\\-_]{10,120}";

	/** 临时文件目录名称 */
	public static final String TEMP_FOLDER = "/_temp/";

	/** 静态资源目录 */
	private static File pFile = null;
	/** 静态资源目录路径 */
	private static String pDir = null;

	/** 公开文件目录 */
	private static File uFile = null;
	/** 公开文件目录路径 */
	private static String uDir = null;
	/** 公开临时文件目录 */
	private static File uTempFile = null;
	/** 公开临时文件目录路径 */
	private static String uTempDir = null;

	/** 私有文件目录 */
	private static File sFile = null;
	/** 私有文件目录路径 */
	private static String sDir = null;
	/** 私有临时文件目录 */
	private static File sTempFile = null;
	/** 私有临时文件目录路径 */
	private static String sTempDir = null;

	/**
	 * 初始化目录
	 * 
	 * @param dirP
	 *            静态资源目录物理路径
	 * @param dirU
	 *            公开文件目录物理路径
	 * @param dirS
	 *            私有文件目录物理路径
	 */
	public static void init(String dirP, String dirU, String dirS) {
		{
			if (null == dirP) {
				throw new RuntimeException("'静态资源目录物理路径' 未配置.\n\n");
			}
			if (null == dirU) {
				throw new RuntimeException("'公开文件目录物理路径' 未配置.\n\n");
			}
			if (null == dirS) {
				throw new RuntimeException("'私有文件目录物理路径' 未配置.\n\n");
			}
		}
		{
			pDir = dirP;
			pFile = new File(pDir);
			if (!pFile.exists() || !pFile.isDirectory() || !pFile.canRead()) {
				throw new RuntimeException("Could not find folder @'静态资源目录' is " + pDir);
			}
			LOG.info("Load folder for '静态资源目录' is " + pFile);
		}

		{
			uDir = dirU;
			uFile = new File(uDir);
			if (!uFile.exists()) {
				if (!uFile.mkdir()) {
					throw new RuntimeException("Could not create folder @'公开文件目录' is " + uDir);
				}
			} else {
				LOG.info("Load folder for '公开文件目录' is " + uFile);
			}
			uTempDir = uDir + TEMP_FOLDER;
			uTempFile = new File(uTempDir);
			if (!uTempFile.exists()) {
				if (!uTempFile.mkdir()) {
					throw new RuntimeException("Could not create folder @'公开文件临时目录' is " + uTempDir);
				}
			} else {
				LOG.info("Load folder for '公开文件临时目录' is " + uTempFile);
			}
		}

		{
			sDir = dirS;
			sFile = new File(sDir);
			if (!sFile.exists()) {
				if (!sFile.mkdir()) {
					throw new RuntimeException("Could not create folder @'私有文件目录' is " + sDir);
				}
			} else {
				LOG.info("Load folder for '私有文件目录' is " + sFile);
			}
			sTempDir = sDir + TEMP_FOLDER;
			sTempFile = new File(sTempDir);
			if (!sTempFile.exists()) {
				if (!sTempFile.mkdir()) {
					throw new RuntimeException("Could not create folder @'私有文件临时目录' is " + sTempDir);
				}
			} else {
				LOG.info("Load folder for '私有文件临时目录' is " + sTempFile);
			}
		}

	}

	/**
	 * 临时文件相对路径
	 * 
	 * @param sufix
	 *            后缀
	 * @return 相对路径
	 */
	private static String _tempPath(String sufix) {
		String r20 = IdUtils.id20();
		return TEMP_P + r20.substring(0, 8) + "/" + r20 + "." + sufix;
	}

	/** 清理指定日期之前的临时文件 */
	private static void _tempClear(File dir, String date) {
		int d = DateUtils.toDateInt(date);
		if (d <= 0) {
			return;
		}
		File[] fs = dir.listFiles();
		if (null != fs && fs.length > 0) {
			for (File f : fs) {
				if (!f.isDirectory()) {
					f.delete();
					continue;
				}
				String fn = f.getName();
				if (!fn.matches(REG_TEMP_DIR)) {
					FileUtilsCore.delete(f);// 删除非法目录
					continue;
				}
				int fi = Integer.parseInt(fn.substring(3));
				if (fi < d) {
					FileUtilsCore.delete(f);// 删除过期目录
					continue;
				}
			}
		}
	}

	/**
	 * 判断文件相对路径是否合法
	 * 
	 * @param path
	 *            文件相对路径
	 * @return true：合法
	 */
	private static void _verifyPath(String path) {
		if (null != path && !path.matches(REG_FILE)) {
			throw new FileException(FileException.Ecode.ERR_PATH);
		}
	}

	/**
	 * 判断文件是否临时文件
	 * 
	 * @see #isFile(String)
	 * @param path
	 *            文件相对路径
	 * @return true：临时文件 false:非临时文件
	 */
	public static boolean isTempFile(String path) {
		return null != path && path.matches(REG_TEMP);
	}

	/**
	 * 判断文件是否临时文件
	 * 
	 * @see #isTempFile(String)
	 * @see com.show.comm.utils.ImageUtils#suffix(String)
	 * @param path
	 *            文件相对路径
	 * @return 图片后缀/null
	 */
	public static String isTempImage(String path) {
		if (null != path && path.matches(REG_TEMP)) {
			return ImageUtils.suffix(path);
		}
		return null;
	}

	/**
	 * 判断是否文件路径
	 * 
	 * @param path
	 *            文件相对路径
	 * @return 1:正常文件<br/>
	 *         2:临时文件<br/>
	 *         0:非文件路径<br/>
	 *         -1:空
	 */
	public static int isFile(String path) {
		if (null != path && !path.isEmpty()) {
			if (path.matches(REG_TEMP)) {
				return 2;
			} else if (path.matches(REG_FILE)) {
				return 1;
			} else {
				return 0;
			}
		}
		return -1;
	}

	/*
	 * ---------------------------------------- 静态操作 ----------------------------------------
	 */

	/**
	 * （静态资源）获取根目录
	 * 
	 * @return 文件根目录
	 */
	public static File pDir() {
		return pFile;
	}

	/**
	 * （静态资源）获取文件
	 * 
	 * @param path
	 *            文件相对路径
	 * @return 文件/null
	 */
	public static File pGet(String path) {
		return FileUtilsCore.getFile(pDir, path, false);
	}

	/*
	 * ---------------------------------------- 开放文件操作 ----------------------------------------
	 */

	/**
	 * （公开文件）获取根目录
	 * 
	 * @return 文件根目录
	 */
	public static File uDir() {
		return uFile;
	}

	/**
	 * （公开文件）判断文件是否存在
	 * 
	 * @param path
	 *            文件相对路径
	 * @return true：文件存在
	 */
	public static boolean uExists(String path) {
		return null != path && path.matches(REG_FILE) && FileUtilsCore.isFile(uDir, path);
	}

	/**
	 * （公开文件）获取文件
	 * 
	 * @param path
	 *            文件相对路径
	 * @return 文件(不存在则创建)
	 */
	public static File uGet(String path) {
		_verifyPath(path);
		return FileUtilsCore.getFile(uDir, path, true);
	}

	/**
	 * （公开文件）获取文件
	 * 
	 * @param path
	 *            文件相对路径
	 * @param create
	 *            不存在文件时是否创建
	 * @return 文件
	 */
	public static File uGet(String path, boolean create) {
		_verifyPath(path);
		return FileUtilsCore.getFile(uDir, path, create);
	}

	/**
	 * （公开文件）创建文件
	 * 
	 * @param path
	 *            文件相对路径
	 * @return 创建的文件相对路径
	 */
	public static String uCreate(String path) {
		_verifyPath(path);
		return FileUtilsCore.createFile(uDir, path);
	}

	/**
	 * （公开文件）根据目录创建规则创建目录，并在此目录下创建指文件名的文件
	 * 
	 * @param rule
	 *            目录创建规则(多个依次创建)
	 * @param fileName
	 *            文件名
	 * @return 创建的文件相对路径
	 */
	public static String uCreate(String fileName, FileFolderRule... rule) {
		return FileUtilsCore.createFile(uDir, fileName, rule);
	}

	/**
	 * （公开文件）根据目录创建规则创建目录
	 * 
	 * @param rule
	 *            目录创建规则
	 * @return 创建的目录相对路径
	 */
	public static String uCreateFolder(FileFolderRule... rule) {
		return FileUtilsCore.createFolder(uDir, rule);
	}

	/**
	 * （公开文件）删除文件/目录
	 * 
	 * @param path
	 *            文件相对路径
	 */
	public static void uDel(String path) {
		if (null != path && !path.isEmpty()) {
			FileUtilsCore.deleteFile(uDir, path);
		}
	}

	/**
	 * （公开文件）复制文件
	 * 
	 * @param source
	 *            源文件
	 * @param dest
	 *            目标文件相对路径
	 */
	public static void uCopyTo(File source, String dest) {
		try {
			uCopyTo(new FileInputStream(source), dest);
		} catch (FileNotFoundException e) {
			throw new FileException(FileException.Ecode.FILE_NOT_EXISTS);
		}
	}

	/**
	 * （公开文件）复制文件
	 * 
	 * @param InputStream
	 *            输入流
	 * @param dest
	 *            目标文件相对路径
	 */
	public static void uCopyTo(InputStream is, String dest) {
		_verifyPath(dest);
		FileUtilsCore.copyFile(is, uGet(dest));
	}

	/**
	 * 移动文件
	 * 
	 * @param source
	 *            源文件名称
	 * @param dest
	 *            目标文件相对路径
	 * @return 成功true
	 */
	public static boolean uMove(String source, String dest) {
		return uRename(source, dest);
	}

	/**
	 * 重命名文件
	 * 
	 * @param source
	 *            源文件名称
	 * @param dest
	 *            目标文件相对路径
	 * @return 成功true
	 */
	public static boolean uRename(String source, String dest) {
		_verifyPath(dest);
		return FileUtilsCore.rename(uDir, source, dest);
	}

	/**
	 * 新文件替换旧文件
	 * 
	 * @param newPath
	 *            新文件路径
	 * @param oldPath
	 *            旧文件路径
	 * @param notExistsThenNull
	 *            新文件不存在时是否返回null
	 * @return 新文件路径
	 */
	public static String uReplace(String newPath, String oldPath, boolean notExistsThenNull) {
		if (null == newPath || !newPath.matches(REG_FILE)) {
			if (null != oldPath) {
				uDel(oldPath);// 删除旧文件
			}
		} else {
			if (null != oldPath && !oldPath.equals(newPath)) {
				uDel(oldPath);// 删除旧文件
			}
			if (!notExistsThenNull || FileUtilsCore.isFile(uDir, newPath)) {
				return newPath;
			}
		}
		return null;
	}

	/**
	 * （公开文件）获取临时文件根目录
	 * 
	 * @return 临时文件文件根目录
	 */
	public static File uTempDir() {
		return uTempFile;
	}

	/**
	 * （公开文件）获取临时文件
	 * 
	 * @param path
	 *            文件相对路径
	 * @return 文件
	 */
	public static File uTempGet(String path) {
		return FileUtilsCore.getFile(uTempDir, path, false);
	}

	/**
	 * （公开文件）创建临时文件
	 * 
	 * @param sufix
	 *            文件后缀
	 * @return 文件相对路径
	 */
	public static String uTempCreate(String sufix) {
		return FileUtilsCore.createFile(uTempDir, _tempPath(sufix));
	}

	/**
	 * （公开文件）删除临时文件/目录
	 * 
	 * @param path
	 *            文件相对路径
	 */
	public static void uTempDel(String path) {
		if (null != path && !path.isEmpty()) {
			FileUtilsCore.deleteFile(uTempDir, path);
		}
	}

	/**
	 * （公开文件）保存临时文件
	 * 
	 * @param is
	 *            输入流
	 * @param sufix
	 *            文件后缀
	 * @return 文件相对路径
	 */
	public static String uTempSave(InputStream is, String sufix) {
		if (null == sufix) {
			throw new NullPointerException("sufix is null");
		}
		if (null != is) {
			try {
				String path = _tempPath(sufix);
				FileUtilsCore.copyFile(is, FileUtilsCore.getFile(uTempDir, path, true));
				return path;
			} catch (FileException e) {
			}
		}
		return null;
	}

	/**
	 * （公开文件）移动文件至公开文件目录
	 * 
	 * @param tSource
	 *            源临时文件相对路径
	 * @param uDest
	 *            目标公开文件相对路径
	 * @return 成功true
	 */
	public static boolean uTempMove(String tSource, String uDest) {
		_verifyPath(uDest);
		File tf = uTempGet(tSource);
		if (null != tf) {
			File uf = uGet(uDest, true);
			if (uf.exists() && uf.isFile()) {
				uf.delete();
			}
			return tf.renameTo(uf);
		}
		return false;
	}

	/**
	 * （公开文件）移动图片文件至公开文件目录
	 * 
	 * @param photo
	 *            临时图片相对路径
	 * @param namer
	 *            目标文件命名
	 * @return 目标文件相对路径/null
	 * @throws FileException
	 */
	public static String uTempMoveImage(String photo, FileNamer namer) {
		int ftype = isFile(photo);// 文件类型
		if (ftype == 0) {
			throw new FileException(FileException.Ecode.ERR_PATH);// 不匹配文件路径
		} else if (ftype == -1) {
			return null;// 图片允许空
		} else {
			String suffix = ImageUtils.suffix(photo);// 图片后缀
			if (null == suffix) {
				throw new FileException(FileException.Ecode.ERR_FORMAT);
			}
			if (ftype == 1) {
				return photo;// 正常文件路径,此处不做处理
			} else if (ftype == 2) {
				String path = namer.nameWithoutSuffix() + "." + suffix;// 目标图片名称
				if (FileUtils.uTempMove(photo, path)) {
					return path;
				} else {
					throw new FileException(FileException.Ecode.FILE_ERROR);// 临时文件移动失败
				}
			}
		}
		return null;
	}

	/**
	 * （公开文件）移动文件至私有文件目录
	 * 
	 * @param tSource
	 *            源临时文件相对路径
	 * @param sDest
	 *            目标私有文件相对路径
	 * @return 成功true
	 */
	public static boolean uTempMoveToS(String tSource, String sDest) {
		_verifyPath(sDest);
		File tf = uTempGet(tSource);
		if (null != tf) {
			File sf = sGet(sDest, true);
			if (sf.exists() && sf.isFile()) {
				sf.delete();
			}
			return tf.renameTo(sf);
		}
		return false;
	}

	/**
	 * 清理指定日期之前的临时文件
	 * 
	 * @param date
	 *            日期(yyyyMMdd)
	 */
	public static void uTempClear(String date) {
		_tempClear(uTempFile, date);
	}

	/*
	 * ---------------------------------------- 私有文件操作 ----------------------------------------
	 */

	/**
	 * （私有文件）获取文件根目录
	 * 
	 * @return 根目录
	 */
	public static File sDir() {
		return sFile;
	}

	/**
	 * （私有文件）判断文件是否存在
	 * 
	 * @param path
	 *            文件相对路径
	 * @return true：文件存在
	 */
	public static boolean sExists(String path) {
		return null != path && path.matches(REG_FILE) && FileUtilsCore.isFile(uDir, path);
	}

	/**
	 * （私有文件）获取文件
	 * 
	 * @param path
	 *            文件相对路径
	 * @return 文件(不存在则创建)
	 */
	public static File sGet(String path) {
		_verifyPath(path);
		return FileUtilsCore.getFile(sDir, path, true);
	}

	/**
	 * （私有文件）获取文件
	 * 
	 * @param path
	 *            文件相对路径
	 * @param create
	 *            不存在时是否创建
	 * @return 文件
	 */
	public static File sGet(String path, boolean create) {
		_verifyPath(path);
		return FileUtilsCore.getFile(sDir, path, create);
	}

	/**
	 * （私有文件）创建文件
	 * 
	 * @param path
	 *            文件相对路径
	 * @return 创建的文件相对路径
	 */
	public static String sCreate(String path) {
		_verifyPath(path);
		return FileUtilsCore.createFile(sDir, path);
	}

	/**
	 * （私有文件）根据目录创建规则创建目录，并在此目录下创建指文件名的文件
	 * 
	 * @param rule
	 *            目录创建规则(多个依次创建)
	 * @param fileName
	 *            文件名
	 * @return 创建的文件相对路径
	 */
	public static String sCreate(String fileName, FileFolderRule... rule) {
		return FileUtilsCore.createFile(sDir, fileName, rule);
	}

	/**
	 * （私有文件）根据目录创建规则创建目录
	 * 
	 * @param rule
	 *            目录创建规则
	 * @return 创建的目录相对路径
	 */
	public static String sCreateFolder(FileFolderRule... rule) {
		return FileUtilsCore.createFolder(sDir, rule);
	}

	/**
	 * （私有文件）删除文件/目录
	 * 
	 * @param path
	 *            文件相对路径
	 */
	public static void sDel(String path) {
		if (null != path && !path.isEmpty()) {
			FileUtilsCore.deleteFile(sDir, path);
		}
	}

	/**
	 * （私有文件）复制文件
	 * 
	 * @param source
	 *            源文件
	 * @param dest
	 *            目标文件相对路径
	 */
	public static void sCopyTo(File source, String dest) {
		try {
			sCopyTo(new FileInputStream(source), dest);
		} catch (FileNotFoundException e) {
			throw new FileException(FileException.Ecode.FILE_NOT_EXISTS);
		}
	}

	/**
	 * （私有文件）复制私有文件到指定目标
	 * 
	 * @param InputStream
	 *            输入流
	 * @param dest
	 *            目标文件相对路径
	 */
	public static void sCopyTo(InputStream is, String dest) {
		_verifyPath(dest);
		FileUtilsCore.copyFile(is, sGet(dest));
	}

	/**
	 * （私有文件）移动文件
	 * 
	 * @param source
	 *            源文件名称
	 * @param dest
	 *            目标文件相对路径
	 * @return 成功true
	 */
	public static boolean sMove(String source, String dest) {
		return sRename(source, dest);
	}

	/**
	 * （私有文件）重命名文件
	 * 
	 * @param source
	 *            源文件名称
	 * @param dest
	 *            目标文件相对路径
	 * @return 成功true
	 */
	public static boolean sRename(String source, String dest) {
		_verifyPath(dest);
		return FileUtilsCore.rename(sDir, source, dest);
	}

	/**
	 * 新文件替换旧文件
	 * 
	 * @param newPath
	 *            新文件路径
	 * @param oldPath
	 *            旧文件路径
	 * @param notExistsThenNull
	 *            新文件不存在时是否返回null
	 * @return 新文件路径
	 */
	public static String sReplace(String newPath, String oldPath, boolean notExistsThenNull) {
		if (null == newPath || !newPath.matches(REG_FILE)) {
			if (null != oldPath) {
				sDel(oldPath);// 删除旧文件
			}
		} else {
			if (null != oldPath && !oldPath.equals(newPath)) {
				sDel(oldPath);// 删除旧文件
			}
			if (!notExistsThenNull || FileUtilsCore.isFile(sDir, newPath)) {
				return newPath;
			}
		}
		return null;
	}

	/**
	 * （私有文件）获取临时文件根目录
	 * 
	 * @return 临时文件文件根目录
	 */
	public static File sTempDir() {
		return sTempFile;
	}

	/**
	 * （私有文件）获取临时文件
	 * 
	 * @param path
	 *            文件相对路径
	 * @return 文件
	 */
	public static File sTempGet(String path) {
		return FileUtilsCore.getFile(sTempDir, path, false);
	}

	/**
	 * （私有文件）创建临时文件
	 * 
	 * @param sufix
	 *            文件后缀
	 * @return 文件相对路径
	 */
	public static String sTempCreate(String sufix) {
		return FileUtilsCore.createFile(sTempDir, _tempPath(sufix));
	}

	/**
	 * （私有文件）删除临时文件/目录
	 * 
	 * @param path
	 *            文件相对路径
	 */
	public static void sTempDel(String path) {
		if (null != path && !path.isEmpty()) {
			FileUtilsCore.deleteFile(sTempDir, path);
		}
	}

	/**
	 * （私有文件）保存临时文件
	 * 
	 * @param is
	 *            输入流
	 * @param sufix
	 *            文件后缀
	 * @return 文件相对路径
	 */
	public static String sTempSave(InputStream is, String sufix) {
		if (null == sufix) {
			throw new NullPointerException("sufix is null");
		}
		if (null != is) {
			try {
				String path = _tempPath(sufix);
				FileUtilsCore.copyFile(is, FileUtilsCore.getFile(sTempDir, path, true));
				return path;
			} catch (FileException e) {
			}
		}
		return null;
	}

	/**
	 * （私有文件）移动文件至私有文件目录
	 * 
	 * @param tSource
	 *            源临时文件相对路径
	 * @param sDest
	 *            目标私有文件相对路径
	 * @return 成功true
	 */
	public static boolean sTempMove(String tSource, String sDest) {
		_verifyPath(sDest);
		File tf = sTempGet(tSource);
		if (null != tf) {
			File sf = sGet(sDest, true);
			if (sf.exists() && sf.isFile()) {
				sf.delete();
			}
			return tf.renameTo(sf);
		}
		return false;
	}

	/**
	 * （私有文件）移动文件至公开文件目录
	 * 
	 * @param tSource
	 *            源临时文件相对路径
	 * @param uDest
	 *            目标公开文件相对路径
	 * @return 成功true
	 */
	public static boolean sTempMoveToU(String tSource, String uDest) {
		_verifyPath(uDest);
		File tf = sTempGet(tSource);
		if (null != tf) {
			File uf = uGet(uDest, true);
			if (uf.exists() && uf.isFile()) {
				uf.delete();
			}
			return tf.renameTo(uf);
		}
		return false;
	}

	/**
	 * 清理指定日期之前的临时文件
	 * 
	 * @param date
	 *            日期(yyyyMMdd)
	 */
	public static void sTempClear(String date) {
		_tempClear(sTempFile, date);
	}
	/*
	 * ---------------------------------------- 其它操作 ----------------------------------------
	 */

	/**
	 * 计算文件MD5值
	 * 
	 * @param file
	 *            文件对象
	 * @return MD5/null(文件异常)
	 */
	public static String md5(File file) {
		if (null != file) {
			InputStream is = null;
			try {
				is = new FileInputStream(file);
				return DigestUtils.md5Hex(is);
			} catch (FileNotFoundException e) {
			} catch (IOException e) {
			} finally {
				if (null != is) {
					try {
						is.close();
					} catch (IOException e) {
					}
				}
			}

		}
		return null;
	}
}
