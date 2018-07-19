package com.show.ppx.cloud.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.show.comm.utils.IPUtils;
import com.show.comm.utils.StringUtils;
import com.show.ppx.cloud.Scheduler;

/**
 * 云调度处理
 * 
 * @author heyuhua
 * @date 2018年3月22日
 */
@Service
@EnableScheduling
public class SchedulerImpl implements Scheduler {

	/** 日志 */
	private static final Logger LOG = LoggerFactory.getLogger(SchedulerImpl.class);

	/** Redis 调度服务hash key */
	private static final byte[] REDIS_SCHEDULE_SERV = "cloud:schedule:serv".getBytes();
	/** Redis 调度任务hash key */
	private static final byte[] REDIS_SCHEDULE_TASK = "cloud:schedule:task".getBytes();
	/** 服务器失效移除延时(2分钟) */
	private static final long RMV_DELAY = 1000 * 60 * 2;
	/** 服务器时间异常误差(20秒) */
	private static final long RMV_ERROR = -1000 * 20;
	/** 服务器心跳延时 */
	private static final long ACT_DELAY = 1000 * 30;

	/** 本服务器(IP地址+应用) */
	private String server;
	/** 本服务器(IP地址+应用) */
	private byte[] serverBs;

	@Value("${spring.application.name}")
	private String app;
	@Autowired
	private RedisTemplate<byte[], byte[]> redis;
	/** Redis hash 操作 */
	private HashOperations<byte[], byte[], byte[]> hash;

	@PostConstruct
	public void start() {
		server = IPUtils.localIp() + ":" + app;
		serverBs = server.getBytes();
		hash = redis.opsForHash();
		active();
	}

	/** 激活当前服务器 */
	@Scheduled(fixedDelay = ACT_DELAY, initialDelay = ACT_DELAY)
	public void active() {
		hash.put(REDIS_SCHEDULE_SERV, serverBs, String.valueOf(System.currentTimeMillis()).getBytes());
		LOG.info("Cloud schedule active “" + server + "”.");
	}

	@PreDestroy
	public void destroy() {
		hash.delete(REDIS_SCHEDULE_SERV, serverBs);
		LOG.info("Cloud schedule remove “" + server + "”.");
	}

	@Override
	public boolean run(String taskName) {
		return runAtCurrentServer(app + "." + taskName);
	}

	private boolean runAtCurrentServer(String task) {
		byte[] srv = hash.get(REDIS_SCHEDULE_TASK, task.getBytes());
		if (null == srv) {
			hash.put(REDIS_SCHEDULE_TASK, task.getBytes(), serverBs);// 设置为当前服务器调度
			LOG.info("Cloud schedule task “" + task + "”. (put to “" + server + "”)");
			return true;
		}
		String srvs = new String(srv);
		if (server.equals(srvs)) {
			LOG.info("Cloud schedule task “" + task + "”. (current “" + server + "”)");
			return true;// 当前服务器
		}
		long amt = 0L;
		byte[] ams = hash.get(REDIS_SCHEDULE_SERV, srv);// 服务器活动时间
		if (null != ams) {
			amt = StringUtils.toLong(new String(ams), 0L);
		}
		long set = System.currentTimeMillis() - amt;
		if (set > RMV_DELAY || set < RMV_ERROR) {
			hash.delete(REDIS_SCHEDULE_SERV, srv);// 移除失效服务器
			hash.put(REDIS_SCHEDULE_TASK, task.getBytes(), serverBs);// 设置为当前服务器调度
			LOG.info("Cloud schedule task “" + task + "”. (rmv from “" + srvs + "” and put to “" + server + "”)");
			return true;
		}
		return false;
	}

}
