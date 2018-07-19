package com.show.ppx.zuul.adm.service;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.show.ppx.zuul.adm.service.hystric.LoginServiceHystric;

/**
 * 登录服务
 * 
 * @author heyuhua
 * @date 2017年12月28日
 */
@FeignClient(value = "sv-sys", fallback = LoginServiceHystric.class)
public interface LoginService {

}
