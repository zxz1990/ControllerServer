package com.zxz.controllerserver.bean;

public class KeyVo {
	
	/**
	 * 是否按下
	 */
	private boolean press = true;
	
	/**
	 * 键字符
	 */
	private String keyText;
	
	/**
	 * 键码
	 */
	private int keyCode;

	public boolean isPress() {
		return press;
	}

	public void setPress(boolean Press) {
		this.press = Press;
	}

	public String getKeyText() {
		return keyText;
	}

	public void setKeyText(String keyText) {
		this.keyText = keyText;
	}

	public int getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "isPress:"+isPress()+",keycode:"+getKeyCode()+",keytext:"+getKeyText();
	}

}
