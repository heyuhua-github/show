package com.show.ppx;

import javax.annotation.PostConstruct;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.show.comm.mybatis.SQLPrinterRegister;

/**
 * 考勤服务
 * 
 * <pre>
 * 本地开发环境：
 * Program arguments: -javaagent:D:/show/tools/springloaded-1.2.5.RELEASE.jar -noverify
 * class path: src/test/resources
 * </pre>
 * 
 * @author heyuhua
 * @date 2017年6月6日
 *
 */
@EnableEurekaClient
@EnableScheduling
@ServletComponentScan(basePackages = {
		// 添加需要扫描的包
})
@SpringBootApplication(scanBasePackages = {
		// 添加需要扫描的包
		"com.show.comm.conf", // 公共配置
		"com.show.ppx.comm.cloud.broadcast", // 广播服务
		"com.show.ppx.comm.cloud.schedule", // 云调度任务
		"com.show.ppx.comm.cloud.log", // 日志服务
		"com.show.ppx.comm.restful.sv", // sv支持
		"com.show.ppx.kq.**" // 业务
})
@MapperScan("com.show.ppx.kq.dao")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Value("${debug}")
	private boolean debug;

	/** 是否开启sql调试输出 */
	@PostConstruct
	public void sqldebug() {
		if (debug) {
			SQLPrinterRegister.register(true);
		}
	}

	@Value("${res.file_p}")
	private String fileP;
	@Value("${res.file_u}")
	private String fileU;
	@Value("${res.file_s}")
	private String fileS;

	@PostConstruct
	public void init() {
	}
}
