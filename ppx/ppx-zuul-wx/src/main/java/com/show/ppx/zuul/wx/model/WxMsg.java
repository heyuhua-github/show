package com.show.ppx.zuul.wx.model;

/**
 * 公众号api消息
 * 
 * @author heyuhua
 * @date 2018年2月6日
 */
public class WxMsg {

	/** 消息类型 */
	public static enum Mtype {
		text, event, unsupported;

		/**
		 * 解析消息类型
		 * 
		 * @param type
		 *            类型
		 * @return 类型/缺省text
		 */
		public static Mtype parse(String type) {
			try {
				return Mtype.valueOf(type.toLowerCase());
			} catch (IllegalArgumentException e) {
				return unsupported;
			}
		}
	}

	/** 事件 */
	public static enum Mevent {
		view, click, unsubscribe, subscribe, unsupported;
		/**
		 * 解析消息类型
		 * 
		 * @param type
		 *            类型
		 * @return 类型/缺省text
		 */
		public static Mevent parse(String type) {
			try {
				return Mevent.valueOf(type.toLowerCase());
			} catch (IllegalArgumentException e) {
				return null;
			}
		}
	}

	/** 公众号OpenId */
	private String appOpenId;
	/** 用户OpenId */
	private String usrOpenId;

	/** 消息类型 */
	private Mtype type;
	/** 文本消息内容 */
	private String textContent;
	/** 事件 */
	private Mevent event;
	/** 事件Key */
	private String eventKey;

	/** 时间戳 */
	private String timestamp;
	/** 随机数 */
	private String nonce;

	public WxMsg() {
	}

	public WxMsg(String appOpenId, String usrOpenId) {
		this.type = Mtype.unsupported;
		this.appOpenId = appOpenId;
		this.usrOpenId = usrOpenId;
	}

	public WxMsg(String appOpenId, String usrOpenId, String textContent) {
		this.type = Mtype.text;
		this.appOpenId = appOpenId;
		this.usrOpenId = usrOpenId;
		this.textContent = textContent;
	}

	public WxMsg(String appOpenId, String usrOpenId, Mevent event, String eventKey) {
		this.type = Mtype.event;
		this.appOpenId = appOpenId;
		this.usrOpenId = usrOpenId;
		this.event = event;
		this.eventKey = eventKey;
	}

	/**
	 * 填充时间戳和随机数
	 * 
	 * @param timestamp
	 *            时间戳
	 * @param nonce
	 *            随机数
	 */
	public void timestampAndNonce(String timestamp, String nonce) {
		this.timestamp = timestamp;
		this.nonce = nonce;
	}

	/**
	 * @return 公众号OpenId
	 */
	public String getAppOpenId() {
		return appOpenId;
	}

	/**
	 * @param appOpenId
	 *            公众号OpenId
	 */
	public void setAppOpenId(String appOpenId) {
		this.appOpenId = appOpenId;
	}

	/**
	 * @return 用户OpenId
	 */
	public String getUsrOpenId() {
		return usrOpenId;
	}

	/**
	 * @param usrOpenId
	 *            用户OpenId
	 */
	public void setUsrOpenId(String usrOpenId) {
		this.usrOpenId = usrOpenId;
	}

	/**
	 * @return 消息类型
	 */
	public Mtype getType() {
		return type;
	}

	/**
	 * @param type
	 *            消息类型
	 */
	public void setType(Mtype type) {
		this.type = type;
	}

	/**
	 * @return 文本消息内容
	 */
	public String getTextContent() {
		return textContent;
	}

	/**
	 * @param textContent
	 *            文本消息内容
	 */
	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}

	/**
	 * @return 事件
	 */
	public Mevent getEvent() {
		return event;
	}

	/**
	 * @param event
	 *            事件
	 */
	public void setEvent(Mevent event) {
		this.event = event;
	}

	/**
	 * @return 事件Key
	 */
	public String getEventKey() {
		return eventKey;
	}

	/**
	 * @param eventKey
	 *            事件Key
	 */
	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	/**
	 * @return 时间戳
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @return nonce
	 */
	public String getNonce() {
		return nonce;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WxMsg [appOpenId=");
		builder.append(appOpenId);
		builder.append(", usrOpenId=");
		builder.append(usrOpenId);
		builder.append(", type=");
		builder.append(type);
		builder.append(", textContent=");
		builder.append(textContent);
		builder.append(", event=");
		builder.append(event);
		builder.append(", eventKey=");
		builder.append(eventKey);
		builder.append("]");
		return builder.toString();
	}

}
