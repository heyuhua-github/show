package com.show.comm.utils.ext;

import java.lang.reflect.Type;
import java.util.EventObject;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 事件管理器
 * 
 * @author heyuhua
 * @date 2017年9月7日
 */
public class EventManager {

	/** 唯一实例 */
	private static final EventManager INSTANCE = new EventManager();
	/** 日志 */
	private static final Logger LOG = LoggerFactory.getLogger(EventManager.class);

	/** 侦听器 */
	private ConcurrentHashMap<String, ConcurrentLinkedQueue<EventListener<?>>> listeners = new ConcurrentHashMap<>();
	/** 线程池 */
	private ExecutorService es = Executors.newCachedThreadPool();

	private EventManager() {
	}

	/** 获得实例 */
	public static EventManager getInstance() {
		return INSTANCE;
	}

	/**
	 * 添加事件侦听器
	 * 
	 * @param listener
	 *            事件侦听器
	 */
	public void addEventListener(EventListener<?> listener) {
		String type = type(listener);
		ConcurrentLinkedQueue<EventListener<?>> ls = listeners.get(type);
		if (null == ls) {
			ls = new ConcurrentLinkedQueue<EventListener<?>>();
		}
		ls.add(listener);
		listeners.put(type, ls);
	}

	/**
	 * 移除事件侦听器
	 * 
	 * @param listener
	 *            事件侦听器
	 */
	public void removeEventListener(EventListener<?> listener) {
		String type = type(listener);
		ConcurrentLinkedQueue<EventListener<?>> ls = listeners.get(type);
		if (null != ls) {
			ls.remove(listener);
		}
	}

	/**
	 * 移除事件所有侦听器
	 * 
	 * @param eventType
	 *            事件类型
	 */
	public void removeEventListeners(Class<? extends EventObject> eventType) {
		listeners.remove(eventType.getName());
	}

	/** 解析事件类型 */
	private String type(EventListener<?> listener) {
		Type type = listener.getClass().getGenericInterfaces()[0];
		Pattern p = Pattern.compile("\\$EventListener\\<[0-9A-Za-z\\.\\-\\$]*\\>");
		Matcher m = p.matcher(type.toString());
		if (m.find()) {
			String s = m.group();
			return s.substring(15, s.length() - 1);
		}
		throw new RuntimeException("EventObject's name does not compile $EventListener<[0-9A-Za-z.-$]*>");
	}

	/**
	 * 同步事件处理
	 * 
	 * @param event
	 *            事件
	 */
	public void send(EventObject event) {
		ConcurrentLinkedQueue<EventListener<?>> ls = listeners.get(event.getClass().getName());
		if (null != ls && !ls.isEmpty()) {
			for (EventListener<?> l : ls) {
				doEvent(l, event);
			}
		}
	}

	/**
	 * 异步事件处理
	 * 
	 * @param event
	 *            事件
	 */
	public void post(EventObject event) {
		ConcurrentLinkedQueue<EventListener<?>> ls = listeners.get(event.getClass().getName());
		if (null != ls && !ls.isEmpty()) {
			for (EventListener<?> l : ls) {
				es.execute(new Runnable() {
					@Override
					public void run() {
						doEvent(l, event);
					}
				});
			}
		}
	}

	/** 执行事件 */
	@SuppressWarnings("unchecked")
	private void doEvent(EventListener<?> l, EventObject event) {
		try {
			EventListener<EventObject> el = (EventListener<EventObject>) l;
			el.onEvent(event);
		} catch (Exception e) {
			LOG.error("Process event failed .", e);
		}
	}

	/**
	 * 事件侦听器
	 */
	public static interface EventListener<T extends EventObject> {

		/**
		 * 处理事件
		 * 
		 * @param event
		 *            事件
		 */
		public void onEvent(T event);

	}

}
