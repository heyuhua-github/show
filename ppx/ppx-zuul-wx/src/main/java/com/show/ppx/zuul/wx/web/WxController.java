package com.show.ppx.zuul.wx.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

/**
 * 公众号入口处理
 * 
 * @author heyuhua
 * @date 2018年2月6日
 */
@ApiIgnore
@RestController
public class WxController {

	@RequestMapping("login")
	public String login() {
		return "login.";
	}

	@RequestMapping("help")
	public String help() {
		return "help.";
	}
}
