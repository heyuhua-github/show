package com.show.comm.utils.ext;

/**
 * 文件处理异常
 * 
 * @author heyuhua
 * @date 2018年3月14日
 */
public class FileException extends RuntimeException {

	private static final long serialVersionUID = 5517688011893562764L;

	/** 错误代码 */
	private Ecode ecode;

	public FileException(Ecode ecode) {
		super(ecode.toString());
		this.ecode = ecode;
	}

	public FileException(Ecode ecode, Throwable t) {
		super(ecode.toString(), t);
		this.ecode = ecode;
	}

	/**
	 * @return 错误代码
	 */
	public Ecode getEcode() {
		return ecode;
	}

	/** 错误代码 */
	static enum Ecode {
		/** 文件路径错误 */
		ERR_PATH,
		/** 文件格式错误 */
		ERR_FORMAT,
		/** 文件不存在 */
		FILE_NOT_EXISTS,
		/** 文件操作失败 */
		FILE_ERROR
	}
}
