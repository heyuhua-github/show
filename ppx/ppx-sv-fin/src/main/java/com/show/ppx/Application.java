package com.show.ppx;

import javax.annotation.PostConstruct;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.show.comm.mybatis.SQLPrinterRegister;
import com.show.comm.utils.ext.FileUtils;

/**
 * 校园内容服务
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
@EnableFeignClients
@EnableScheduling
@ServletComponentScan(basePackages = {
		// 添加需要扫描的包
})
@SpringBootApplication(scanBasePackages = {
		// 添加需要扫描的包
		"com.show.comm.conf", // 公共配置
		"com.show.ppx.cloud", // 公共服务支撑(缓存、下载、日志、云调度...)
		"com.show.ppx.broadcast", // 广播
		"com.show.ppx.restful.sv", // sv支持
		"com.show.ppx.impt", // 数据导入处理支持
		"com.show.ppx.xy.**" // 业务
})
@MapperScan({ //
		"com.show.ppx.xy.honor.dao", //
		"com.show.ppx.xy.notice.dao", //
		"com.show.ppx.xy.mien.dao", //
		"com.show.ppx.xy.moral.dao", //
		"com.show.ppx.xy.play.dao", //
		"com.show.ppx.xy.recipes.dao", //
		"com.show.ppx.xy.sys.dao"//
})
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
		FileUtils.init(fileP, fileU, fileS);// 构造文件处理工具
	}
}
