package com.show.comm.utils;

import java.util.*;

/**
 * 简易Json构造器
 * 
 * <pre>
 * 复杂、对象处理Json请使用 {@link com.alibaba.fastjson.JSON}
 * </pre>
 * 
 * @author heyuhua
 * @date 2017年6月22日
 */
public class JsonMaker {

	private StringBuilder sb = null;

	public JsonMaker() {
		sb = new StringBuilder();
	}

	/**
	 * Object属性
	 * 
	 * @param name
	 *            名称
	 * @param value
	 *            值
	 * @return this
	 */
	public JsonMaker appendObj(String name, Object value) {
		sb.append(",\"").append(name).append("\":");
		if (null == value) {
			sb.append("\"\"");
		} else {
			if (value instanceof CharSequence) {
				sb.append("\"");
				sb.append(value.toString().replaceAll("\"", "\\\\\""));
				sb.append("\"");
			} else if (value instanceof Number) {
				sb.append(value);
			} else if (value instanceof Boolean) {
				sb.append(value);
			} else if (value instanceof Date) {
				sb.append("\"");
				sb.append(DateUtils.DF_TIME.format(value));
				sb.append("\"");
			} else {
				sb.append("\"");
				sb.append(value.toString().replaceAll("\"", "\\\\\""));
				sb.append("\"");
			}
		}
		return this;
	}

	/**
	 * 字符属性
	 * 
	 * @see #appendStr(String, String)
	 * @param name
	 *            名称
	 * @param value
	 *            值
	 * @return this
	 */
	public JsonMaker append(String name, String value) {
		return appendStr(name, value);
	}

	/**
	 * 字符属性
	 * 
	 * @param name
	 *            名称
	 * @param value
	 *            值
	 * @return this
	 */
	public JsonMaker appendStr(String name, String value) {
		sb.append(",\"").append(name).append("\":");
		sb.append("\"");
		if (null != value) {
			sb.append(value.replaceAll("\"", "\\\\\""));
		}
		sb.append("\"");
		return this;
	}

	/**
	 * Boolean属性
	 * 
	 * @param name
	 *            名称
	 * @param value
	 *            值
	 * @return this
	 */
	public JsonMaker appendBoolean(String name, Boolean value) {
		sb.append(",\"").append(name).append("\":");
		if (null != value) {
			sb.append(value);
		} else {
			sb.append("\"\"");
		}
		return this;
	}

	/**
	 * Number属性
	 * 
	 * @param name
	 *            名称
	 * @param value
	 *            值
	 * @return this
	 */
	public JsonMaker appendNum(String name, Number value) {
		sb.append(",\"").append(name).append("\":");
		if (null != value) {
			sb.append(value);
		} else {
			sb.append("\"\"");
		}
		return this;
	}

	/**
	 * int属性
	 * 
	 * @param name
	 *            名称
	 * @param value
	 *            值
	 * @return this
	 */
	public JsonMaker appendInt(String name, int value) {
		sb.append(",\"").append(name).append("\":");
		sb.append(value);
		return this;
	}

	/**
	 * long属性
	 * 
	 * @param name
	 *            名称
	 * @param value
	 *            值
	 * @return this
	 */
	public JsonMaker appendLong(String name, long value) {
		sb.append(",\"").append(name).append("\":");
		sb.append(value);
		return this;
	}

	/**
	 * double属性
	 * 
	 * @param name
	 *            名称
	 * @param value
	 *            值
	 * @return this
	 */
	public JsonMaker appendDouble(String name, double value) {
		sb.append(",\"").append(name).append("\":");
		sb.append(value);
		return this;
	}

	/**
	 * date属性
	 * 
	 * @param name
	 *            名称
	 * @param value
	 *            值
	 * @return this
	 */
	public JsonMaker appendDate(String name, Date value) {
		sb.append(",\"").append(name).append("\":");
		sb.append("\"");
		if (null != value) {
			sb.append(DateUtils.DF_TIME.format(value));
		}
		sb.append("\"");
		return this;
	}

	/**
	 * 填充Json
	 * 
	 * @param name
	 *            名称
	 * @param json
	 *            值
	 * @return this
	 */
	public JsonMaker appendJson(String name, CharSequence json) {
		sb.append(",\"").append(name).append("\":");
		sb.append(json);
		return this;
	}

	/**
	 * 填充Json
	 * 
	 * @param name
	 *            名称
	 * @param jm
	 *            JsonMaker对象
	 * @return this
	 */
	public JsonMaker appendJson(String name, JsonMaker jm) {
		return appendJson(name, jm.json());
	}

	/**
	 * 增加Array
	 * 
	 * @param name
	 *            名称
	 * @param jms
	 *            JsonMaker对象数组
	 * @return this
	 */
	public JsonMaker appendArray(String name, JsonMaker... jms) {
		sb.append(",\"").append(name).append("\":");
		sb.append("[");
		if (null != jms && jms.length > 0) {
			boolean b = false;
			for (JsonMaker jm : jms) {
				if (null == jm) {
					continue;
				}
				String j = jm.json();
				if (j.isEmpty()) {
					continue;
				}
				if (b) {
					sb.append(",");
				} else {
					b = true;
				}
				sb.append(jm.json());
			}
		}
		sb.append("]");
		return this;
	}

	/**
	 * 增加Array
	 * 
	 * @param name
	 *            名称
	 * @param jms
	 *            JsonMaker对象集合
	 * @return this
	 */
	public JsonMaker appendArray(String name, Collection<JsonMaker> jms) {
		sb.append(",\"").append(name).append("\":");
		sb.append("[");
		if (null != jms && !jms.isEmpty()) {
			boolean b = false;
			for (JsonMaker jm : jms) {
				if (null == jm) {
					continue;
				}
				String j = jm.json();
				if (j.isEmpty()) {
					continue;
				}
				if (b) {
					sb.append(",");
				} else {
					b = true;
				}
				sb.append(jm.json());
			}
		}
		sb.append("]");
		return this;
	}

	/**
	 * 获取json明文
	 * 
	 * @return JsonMaker构造的json数据
	 */
	public String json() {
		if (sb.length() > 0) {
			return "{" + sb.substring(1) + "}";
		}
		return "";
	}

	/**
	 * 获取json明文
	 * 
	 * @return JsonMaker构造的json数据
	 */
	public String getJson() {
		return json();
	}

	@Override
	public String toString() {
		return json();
	}

	/**
	 * JsonMaker数组转换为Json Array
	 * 
	 * @param jms
	 *            JsonMaker对象数组
	 * @return json ([{...},...])
	 */
	public static String toJsonArray(JsonMaker... jms) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		if (null != jms && jms.length > 0) {
			boolean b = false;
			for (JsonMaker jm : jms) {
				if (null == jm) {
					continue;
				}
				String j = jm.json();
				if (j.isEmpty()) {
					continue;
				}
				if (b) {
					sb.append(",");
				} else {
					b = true;
				}
				sb.append(jm.json());
			}
		}
		sb.append("]");
		return sb.toString();
	}

	/**
	 * JsonMaker数组转换为Json Array
	 * 
	 * @param jms
	 *            JsonMaker对象集合
	 * @return json ([{...},...])
	 */
	public static String toJsonArray(Collection<JsonMaker> jms) {
		if (null != jms && !jms.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			jms.forEach(jm -> {
				String j = jm.json();
				if (!j.isEmpty()) {
					sb.append(",").append(jm.json());
				}
			});
			if (sb.length() > 0) {
				return "[" + sb.substring(1) + "]";
			}
		}
		return "[]";
	}

	/**
	 * Map集合转换为Json Array
	 * 
	 * @param maps
	 *            Map对象集合
	 * @return json ([{...},...])
	 */
	public static String mapToJsonArray(Collection<Map<?, ?>> maps) {
		if (null != maps && !maps.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for (Map<?, ?> map : maps) {
				String j = mapToJsonParser(map);
				if (null != j) {
					sb.append(",").append(j);
				}
			}
			if (sb.length() > 0) {
				return "[" + sb.substring(1) + "]";
			}
		}
		return "[]";
	}

	/**
	 * Map转换为Json
	 * 
	 * @param map
	 *            Map
	 * @return Json ({..},为null或者empty是返回{})
	 */
	public static String mapToJson(Map<?, ?> map) {
		String json = mapToJsonParser(map);
		return null != json ? json : "{}";
	}

	/** Map转换为Json */
	private static String mapToJsonParser(Map<?, ?> map) {
		if (null != map && !map.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			map.forEach((key, val) -> {
				if (null != key) {
					sb.append(",\"").append(key).append("\":");
					if (null != val) {
						if (val instanceof CharSequence) {
							sb.append("\"");
							sb.append(val.toString().replaceAll("\"", "\\\\\""));
							sb.append("\"");
						} else if (val instanceof Number) {
							sb.append(val);
						} else if (val instanceof Boolean) {
							sb.append(val);
						} else if (val instanceof Date) {
							sb.append("\"");
							sb.append(DateUtils.DF_TIME.format(val));
							sb.append("\"");
						} else {
							sb.append("\"");
							sb.append(val.toString().replaceAll("\"", "\\\\\""));
							sb.append("\"");
						}
					} else {
						sb.append("\"\"");
					}
				}
			});
			if (sb.length() > 0) {
				return "{" + sb.substring(1) + "}";
			}
		}
		return null;
	}

}
