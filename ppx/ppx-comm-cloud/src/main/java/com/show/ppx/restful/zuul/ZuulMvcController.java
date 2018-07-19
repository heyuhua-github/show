package com.show.ppx.restful.zuul;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.show.comm.restful.RestfulRes;
import com.show.comm.restful.RestfulStatus;
import com.show.comm.utils.web.WebUtils;

import springfox.documentation.annotations.ApiIgnore;

/**
 * Zuul公共Controller
 * 
 * @author heyuhua
 * @date 2018年1月5日
 */
@ApiIgnore
@RestController
public class ZuulMvcController {

	/** 输出错误信息 */
	private void err(HttpServletRequest request, HttpServletResponse response, RestfulStatus s) {
		Zuul.next(false);
		WebUtils.writeJson(response, JSON.toJSONString(RestfulRes.err(s)));
	}

	/** 400页面未找到 */
	@RequestMapping("error/400")
	public void e400(HttpServletRequest request, HttpServletResponse response) {
		err(request, response, RestfulStatus.H400);
	}

	/** 403无权限 */
	@RequestMapping("error/403")
	public void e403(HttpServletRequest request, HttpServletResponse response) {
		err(request, response, RestfulStatus.H403);
	}

	/** 404页面未找到 */
	@RequestMapping("error/404")
	public void e404(HttpServletRequest request, HttpServletResponse response) {
		err(request, response, RestfulStatus.H404);
	}

	/** 405请求类型错误 */
	@RequestMapping("error/405")
	public void e405(HttpServletRequest request, HttpServletResponse response) {
		err(request, response, RestfulStatus.H405);
	}

	/** 500错误 */
	@RequestMapping("error/500")
	public void e500(HttpServletRequest request, HttpServletResponse response) {
		err(request, response, RestfulStatus.H500);
	}

}
