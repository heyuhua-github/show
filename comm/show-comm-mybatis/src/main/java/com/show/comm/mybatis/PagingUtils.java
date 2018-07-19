package com.show.comm.mybatis;

import javax.servlet.http.HttpServletRequest;

import com.show.comm.utils.JsonUtils;
import com.show.comm.utils.web.ParamUtils;

/**
 * Paging处理工具类
 * 
 * @author heyuhua
 * @date 2017年7月25日
 */
public class PagingUtils {

	private PagingUtils() {
	}

	/** 默认分页当前页 - 1 */
	private static final int DAU_PAGE = 1;
	/** 默认分页每页数据量 - 10 */
	private static final int DAU_SIZE = 10;

	/**
	 * 构造Paging对象
	 * 
	 * <pre>
	 * 缺省currentPage {@link #DAU_PAGE}
	 * 缺省pageSize {@link #DAU_SIZE}
	 * </pre>
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return Paging
	 */
	public static Paging create(HttpServletRequest request) {
		int currentPage = ParamUtils.getInt(request, "currentPage", DAU_PAGE);
		int pageSize = ParamUtils.getInt(request, "pageSize", DAU_SIZE);
		return new Paging(currentPage, pageSize);
	}

	/**
	 * Paging转换为json
	 * 
	 * @param paging
	 *            Paging
	 * @return json
	 */
	public static String json(Paging paging) {
		return JsonUtils.to(paging);
	}

}
