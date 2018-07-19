package com.show.comm.utils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Josn工具类
 * 
 * <pre>
 * fasterxml.jackson
 * 对象转为json时：
 *  属性忽略：@JsonIgnore
 *  属性命名：@JsonProperty("alias")
 * </pre>
 * 
 * @author heyuhua
 * @date 2018年2月26日
 */
public final class JsonUtils {

	/** Mapper */
	private static final ObjectMapper MAPPER = new ObjectMapper();
	static {
		MAPPER.setDateFormat(DateUtils.DF_TIME);
		MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MAPPER.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
		MAPPER.configure(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS, false);
		MAPPER.configure(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY, false);
		MAPPER.configure(DeserializationFeature.WRAP_EXCEPTIONS, false);
		MAPPER.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
		// MAPPER.setVisibility(MAPPER.getVisibilityChecker().withFieldVisibility(JsonAutoDetect.Visibility.ANY).withGetterVisibility(JsonAutoDetect.Visibility.NONE));
	}

	/**
	 * 对象转换为json String
	 * 
	 * @param obj
	 *            对象
	 * @return json/null
	 */
	public static String to(Object obj) {
		return toJson(obj);
	}

	/**
	 * 对象转换为json String
	 * 
	 * @param obj
	 *            对象
	 * @return json/null
	 */
	public static String toJson(Object obj) {
		try {
			return MAPPER.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
		}
		return null;
	}

	/**
	 * 对象转换为json byte
	 * 
	 * @param obj
	 *            对象
	 * @return json byte[]/null
	 */
	public static byte[] toJsonBytes(Object obj) {
		try {
			return MAPPER.writeValueAsBytes(obj);
		} catch (JsonProcessingException e) {
		}
		return null;
	}

	/**
	 * json解析为Object
	 * 
	 * @param json
	 *            json
	 * @param valueType
	 *            Object
	 * @return Object/null
	 */
	public static <T> T parse(byte[] json, Class<T> valueType) {
		if (null != json) {
			try {
				return MAPPER.readValue(json, valueType);
			} catch (IOException e) {
			}
		}
		return null;
	}

	/**
	 * json解析为Object
	 * 
	 * @param json
	 *            json
	 * @param valueType
	 *            Object
	 * @return Object/null
	 */
	public static <T> T parse(String json, Class<T> valueType) {
		if (null != json) {
			try {
				return MAPPER.readValue(json, valueType);
			} catch (IOException e) {
			}
		}
		return null;
	}

	/**
	 * json解析为Object
	 * 
	 * @param json
	 *            json
	 * @param valueType
	 *            Object
	 * @return Object/null
	 */
	public static <T> List<T> parseArray(byte[] json, Class<T> valueType) {
		if (null != json) {
			try {
				JavaType t = MAPPER.getTypeFactory().constructParametricType(List.class, valueType);
				return MAPPER.readValue(json, t);
			} catch (IOException e) {
			}
		}
		return null;
	}

	/**
	 * json解析为Object
	 * 
	 * @param json
	 *            json
	 * @param valueType
	 *            Object
	 * @return Object/null
	 */
	public static <T> List<T> parseArray(String json, Class<T> valueType) {
		if (null != json) {
			try {
				 JavaType t = MAPPER.getTypeFactory().constructParametricType(List.class, valueType);
				 return MAPPER.readValue(json, t);
			} catch (IOException e) {
			}
		}
		return null;
	}

	/**
	 * json解析为Map
	 * 
	 * @param json
	 *            json
	 * @return Map<String,Object>/null
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> parseMap(byte[] json) {
		if (null != json) {
			try {
				return MAPPER.readValue(json, Map.class);
			} catch (IOException e) {
			}
		}
		return null;
	}

	/**
	 * json解析为Map
	 * 
	 * @param json
	 *            json
	 * @return Map<String,Object>/null
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> parseMap(String json) {
		if (null != json) {
			try {
				return MAPPER.readValue(json, Map.class);
			} catch (IOException e) {
			}
		}
		return null;
	}

}
