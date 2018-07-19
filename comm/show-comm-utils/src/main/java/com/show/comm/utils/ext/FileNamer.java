package com.show.comm.utils.ext;

/**
 * 文件命名
 * 
 * @author heyuhua
 * @date 2018年3月14日
 */
public abstract class FileNamer {

	/**
	 * 文件命名(除后缀外完整相对路径)
	 * 
	 * <pre>
	 * 示例：
	 * 学生头像照片：00000001/stu/20180301000000000000
	 * 考勤抓拍照片：00000002/kq/20180301/20180301000000000000
	 * </pre>
	 * 
	 * @return 文件名
	 */
	abstract public String nameWithoutSuffix();

}
