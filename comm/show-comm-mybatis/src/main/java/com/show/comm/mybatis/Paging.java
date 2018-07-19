package com.show.comm.mybatis;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 分页数据对象
 * 
 * <pre>
 * 使用Paging执行分页查询(PagingInterceptor)务必保证：
 * 1、SQL中字段、表名不能包含“form”、“group by”、“order by”等关键字(拦截器会根据这些关键字进行解析)
 * 2、复杂SQL语句，请尽量使用#c#统计标签，详细信息{@link SQLInterceptor}
 * 3、使用order(column,asc)执行排序时，请保证SQL中已有的order by条件在SQL句末(如有)
 * 4、请在service impl实现中，自行调用setData方法填充查询数据
 * </pre>
 * 
 * @author heyuhua
 * @date 2017年6月6日
 *
 */
public class Paging implements Serializable {

	private static final long serialVersionUID = -8687427837954125779L;

	/** 当前页(默认1) */
	private int currentPage = 1;
	/** 每页数据量(1~Integer.MAX_VALUE-1,默认10) */
	private int pageSize = 10;
	/** 总数据量 */
	private int amount = 0;
	/** 总页数 */
	private int pages = 0;
	/** 查询到的数据集合 */
	private List<?> data = null;

	/** 添加参数map */
	@JsonIgnore
	transient private Map<String, Object> params = new HashMap<String, Object>();
	/** 排序<字段,顺序> */
	@JsonIgnore
	transient private Map<String, String> orders = null;
	/** 排序SQL */
	@JsonIgnore
	transient private String order = "";

	/**
	 * 添加排序
	 * 
	 * @param column
	 *            排序字段(查询SQL中完整的字段名称[0-9A-Za-z_\\-\\.]{1,32})
	 * @param asc
	 *            是否升序
	 */
	public void order(String column, boolean asc) {
		if (null != column && column.matches("[0-9A-Za-z_\\-\\.]{1,32}")) {
			if (null == orders) {
				orders = new LinkedHashMap<String, String>();
			}
			orders.put(column, asc ? "asc" : "desc");
		}
	}

	/**
	 * 添加条件（params.put(key,value)）
	 * 
	 * @param key
	 *            条件KEY
	 * @param value
	 *            条件value(为null时不做任何操作)
	 */
	public void add(String key, Object value) {
		if (null != key && null != value) {
			params.put(key, value);
		}
	}

	/**
	 * 添加like条件（params.put(key,value)）
	 * 
	 * @param key
	 *            条件KEY
	 * @param value
	 *            条件value(自动添加%value%，为null时不做任何操作)
	 */
	public void like(String key, String value) {
		if (null != key && null != value) {
			params.put(key, "%" + value + "%");
		}
	}

	/**
	 * 添加like条件（params.put(key,value)）
	 * 
	 * @param key
	 *            条件KEY
	 * @param value
	 *            条件value(自动添加%value，为null时不做任何操作)
	 */
	public void llike(String key, String value) {
		if (null != key && null != value) {
			params.put(key, "%" + value);
		}
	}

	/**
	 * 添加like条件（params.put(key,value)）
	 * 
	 * @param key
	 *            条件KEY
	 * @param value
	 *            条件value(自动添加value%，为null时不做任何操作)
	 */
	public void rlike(String key, String value) {
		if (null != key && null != value) {
			params.put(key, value + "%");
		}
	}

	/**
	 * 添加手机号码匹配添加（params.put(key,value)）
	 * 
	 * @param key
	 *            条件KEY
	 * @param phone
	 *            手机号码(自动添加%phone%，要求[0-9]{1,11}，长度为11时不添加通配符)
	 */
	public boolean phone(String key, String phone) {
		if (null != key && null != phone && phone.matches("[0-9]{1,11}")) {
			if (phone.length() == 11) {
				params.put(key, phone);
				return true;
			} else {
				params.put(key, "%" + phone + "%");
				return true;
			}
		}
		return false;
	}

	/**
	 * 添加卡号匹配添加（params.put(key,value)）
	 * 
	 * @param key
	 *            条件KEY
	 * @param card
	 *            卡号(自动添加%card%，要求[0-9]{4,10}，长度为10时不添加通配符)
	 */
	public boolean card(String key, String card) {
		if (null != key && null != card && card.matches("[0-9]{4,10}")) {
			if (card.length() == 10) {
				params.put(key, card);
				return true;
			} else {
				params.put(key, "%" + card + "%");
				return true;
			}
		}
		return false;
	}

	/**
	 * 移除条件
	 * 
	 * @param key
	 *            条件KEY
	 */
	public void rmv(String key) {
		if (null != key) {
			params.remove(key);
		}
	}

	/**
	 * 获取条件值
	 * 
	 * @param key
	 *            条件KEY
	 * @return 值
	 */
	public Object get(String key) {
		return null != key ? params.get(key) : null;
	}

	/**
	 * 分页数据对象 - 缺省构造
	 */
	public Paging() {
	}

	/**
	 * 分页数据对象 - 构造
	 * 
	 * @param currentPage
	 *            当前页
	 */
	public Paging(Integer currentPage) {
		if (null != currentPage) {
			int p = currentPage.intValue();
			if (p > 0) {
				this.currentPage = p;
			}
		}
	}

	/**
	 * 分页数据对象 - 构造
	 * 
	 * @param currentPage
	 *            当前页
	 * @param pageSize
	 *            每页数据量
	 */
	public Paging(Integer currentPage, Integer pageSize) {
		if (null != currentPage) {
			int p = currentPage.intValue();
			if (p > 0) {
				this.currentPage = p;
			}
		}
		if (null != pageSize) {
			setPageSize(pageSize.intValue());
		}
	}

	/**
	 * @return 当前页，缺省1
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage
	 *            当前页，缺省1
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return 每页数据量，缺省10
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            每页数据量，缺省10
	 */
	public void setPageSize(int pageSize) {
		if (pageSize > 0) {
			if (pageSize == Integer.MAX_VALUE) {
				pageSize--;
			}
			this.pageSize = pageSize;
		}
	}

	/**
	 * @return 总数据量
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            总数据量
	 */
	public void setAmount(int amount) {
		this.amount = amount;
		if (this.amount > 0) {
			this.pages = amount % pageSize == 0 ? amount / pageSize : amount / pageSize + 1;
		} else {
			this.pages = 0;
		}
	}

	/**
	 * @return 总页数
	 */
	public int getPages() {
		return pages;
	}

	/**
	 * @param pages
	 *            总页数
	 */
	public void setPages(int pages) {
		this.pages = pages;
	}

	/**
	 * @return 查询到的数据集合
	 */
	public List<?> getData() {
		return data;
	}

	/**
	 * @param data
	 *            查询到的数据集合
	 */
	public void setData(List<?> data) {
		this.data = data;
	}

	/**
	 * 获取条件，请在sql中自行具体实现
	 */
	public Map<String, Object> getParams() {
		return params;
	}

	/**
	 * @param params
	 *            查询条件，特殊情况请调用次方法自行传入完整条件
	 */
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	/**
	 * 获取排序<字段,顺序>
	 */
	public Map<String, String> getOrders() {
		return orders;
	}

	/**
	 * 获取排序条件sql
	 */
	public String getOrder() {
		if (null != orders) {
			StringBuilder sb = new StringBuilder();
			orders.forEach((key, val) -> {
				sb.append(", ").append(key).append(" ").append(val);
			});
			if (sb.length() != 0) {
				order = sb.substring(1);
			}
		}
		return order;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Paging [currentPage=");
		builder.append(currentPage);
		builder.append(", pageSize=");
		builder.append(pageSize);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", pages=");
		builder.append(pages);
		builder.append(", params=");
		builder.append(params);
		builder.append(", orders=");
		builder.append(orders);
		builder.append("]");
		return builder.toString();
	}

}
