package com.show.ppx.restful.sv;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.show.comm.restful.RestfulEx;
import com.show.comm.restful.RestfulRes;
import com.show.comm.restful.RestfulStatus;
import com.show.comm.utils.web.WebUtils;

/**
 * 服务异常集中处理
 * 
 * @author heyuhua
 * @date 2017年12月15日
 */
@Order(-1)
@Component
public class SvExHandler implements HandlerExceptionResolver {

	public SvExHandler() {
	}

	private static final Logger LOG = LoggerFactory.getLogger(SvExHandler.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		String uri = request.getRequestURI();
		StringBuilder sb = new StringBuilder();
		sb.append("MVC exception, uri:").append(uri);
		if (null != handler) {
			if (handler instanceof HandlerMethod) {
				HandlerMethod m = (HandlerMethod) handler;
				sb.append(", handler:").append(m.getShortLogMessage());
			} else {
				sb.append(", handler:").append(handler);
			}
		}
		RestfulStatus s = null;
		String err = null;
		if (null != ex) {
			if (ex instanceof RestfulEx) {
				s = ((RestfulEx) ex).getS();
				err = ex.getMessage();
			} else if (ex instanceof HttpRequestMethodNotSupportedException) {
				HttpRequestMethodNotSupportedException e = (HttpRequestMethodNotSupportedException) ex;
				s = RestfulStatus.H404;
				err = e.getMessage();
			} else if (ex instanceof MissingServletRequestParameterException) {
				MissingServletRequestParameterException e = (MissingServletRequestParameterException) ex;
				s = RestfulStatus.H400;
				err = e.getParameterName() + "#Null";
			} else if (ex instanceof MethodArgumentTypeMismatchException) {
				MethodArgumentTypeMismatchException e = (MethodArgumentTypeMismatchException) ex;
				s = RestfulStatus.H400;
				if (null != e.getCause()) {
					err = e.getName() + "#Ex:" + e.getCause().getClass().getSimpleName();
				} else {
					err = e.getName();
				}
			} else if (ex instanceof BindException) {
				s = RestfulStatus.H400;
				BindException e = (BindException) ex;
				BindingResult br = e.getBindingResult();
				if (null != br) {
					FieldError fe = br.getFieldError();
					if (null != fe) {
						err = fe.getField();
					}
				}
			} else {
				Throwable cause = ex.getCause();
				if (null != cause) {
					err = cause.getMessage();
				} else {
					err = ex.getMessage();
				}
				if (null != err && (ex instanceof IllegalStateException) && err.startsWith("Optional ")) {
					Pattern p = Pattern.compile(" parameter '[a-zA-Z0-9_]{1,32}' ");
					Matcher m = p.matcher(err);
					if (m.find()) {
						String pn = m.group().trim().substring(11);
						pn = pn.substring(0, pn.length() - 1);
						s = RestfulStatus.H400;
						err = pn;
					}
				}
			}
			sb.append(", ").append(err);
		}
		RestfulRes<?> sr = new RestfulRes<>();
		sr.setM(err);
		if (null != s) {
			sr.setS(s.getCode());
			LOG.error(sb.toString());
		} else {
			sr.setS(RestfulStatus.H500.getCode());
			if (ex instanceof HttpRequestMethodNotSupportedException) {
				LOG.error(sb.toString(), ex);
			} else {
				LOG.error(sb.toString());
			}
		}
		WebUtils.writeJson(response, JSON.toJSONString(sr));
		return new ModelAndView();
	}

}
