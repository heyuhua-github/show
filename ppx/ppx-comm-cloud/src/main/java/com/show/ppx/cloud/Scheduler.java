package com.show.ppx.cloud;

/**
 * 云调度服务
 * 
 * @author heyuhua
 * @date 2018年3月22日
 */
public interface Scheduler {

	/**
	 * 是否执行任务
	 * 
	 * @param taskName
	 *            任务名称：类名.方法名 或者 类名.具体业务定义名称<br/>
	 *            示例：this.getClass().getSimpleName() + ".cache"
	 * @return 需要执行任务：true
	 */
	boolean run(String taskName);

}
