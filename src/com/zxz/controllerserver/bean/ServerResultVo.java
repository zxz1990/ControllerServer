package com.zxz.controllerserver.bean;

public class ServerResultVo {
	
	/**
	 * 返回值
	 */
	private int code;
	
	/**
	 * 返回消息
	 */
	private String msg;
	
	/**
	 * 返回数据
	 */
	private String data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
