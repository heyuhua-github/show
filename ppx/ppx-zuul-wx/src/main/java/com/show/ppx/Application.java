package com.show.ppx;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.show.comm.utils.ext.FileUtils;

/**
 * 公众号
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
@EnableZuulProxy
@EnableFeignClients
@EnableEurekaClient
@EnableScheduling
@SpringBootApplication(scanBasePackages = {
		// 添加需要扫描的包
		"com.show.comm.conf.redis", // redis
		"com.show.ppx.cloud", // 公共服务支撑(缓存、下载、日志、云调度...)
		"com.show.ppx.restful.zuul", // zuul支持
		"com.show.ppx.zuul.wx.**" // 业务
})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Value("${res.file_p}")
	private String fileP;
	@Value("${res.file_u}")
	private String fileU;
	@Value("${res.file_s}")
	private String fileS;

	@PostConstruct
	public void init() {
		FileUtils.init(fileP, fileU, fileS);// 构造文件处理工具
	}

}
