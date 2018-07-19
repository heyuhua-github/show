package com.show.ppx.zuul.wx.service;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.show.ppx.zuul.wx.service.hystric.WxServiceHystric;

/**
 * 公众号基础服务
 * 
 * @author heyuhua
 * @date 2018年2月6日
 */
@FeignClient(value = "sv-sys", fallback = WxServiceHystric.class)
public interface WxService {


}
