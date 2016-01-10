package com.zxz.controllerserver.logic;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.logging.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.zxz.controllerserver.bean.KeyVo;
import com.zxz.controllerserver.bean.ServerRequestVo;
import com.zxz.controllerserver.bean.ServerResultVo;
import com.zxz.controllerserver.utils.Consts;
import com.zxz.controllerserver.utils.ThreadPoolManager;

public class SocketLogic {
	BufferedReader mReader;
	PrintWriter mWriter;
	
	private static Logger log = Logger.getGlobal(); 
	
	public SocketLogic() {
		// TODO Auto-generated constructor stub
	}
	
	public void procSocket(Socket socket) {
		if(socket == null) {
			return;
		}
		ThreadPoolManager.execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					mReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
					mWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
					String msg;
					while((msg = mReader.readLine()) != null) {
						procRequest(msg);
					}
					mReader.close();
					mWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
	private void procParams(KeyVo keyVo) {

		if(keyVo == null) {
			return;
		}
		
		Robot robot;
		ServerResultVo result = new ServerResultVo();
		try {
			robot = new Robot();
			if(keyVo.isPress()) {
				robot.keyPress(keyVo.getKeyCode());
				log.info("Key Pressed: " + keyVo.getKeyCode());
				result.setCode(Consts.CODE_SUCCESS);
				result.setMsg("Key Pressed: " + keyVo.getKeyCode());
			} else {				
				robot.keyRelease(keyVo.getKeyCode());
				log.info("Key Released: " + keyVo.getKeyCode());
				result.setCode(Consts.CODE_SUCCESS);
				result.setMsg("Key Released: " + keyVo.getKeyCode());
			}
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			log.info("按键失败");
			e.printStackTrace();
			result.setCode(Consts.CODE_FAIL);
			result.setMsg("按键失败");
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(Consts.CODE_FAIL);
			result.setMsg("未知错误");
		} finally {
			if(mWriter != null) {
				mWriter.println(JSON.toJSONString(result));
			}
		}
	}
	
	/**
	 * 解析request，例如{"type":"key","params":{}}
	 * @param msg
	 */
	public void procRequest(String msg) {
		log.info("msg:" + msg);
		ServerRequestVo request = null;
		try {
			request = JSON.parseObject(msg, ServerRequestVo.class);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		if(request == null) {
			return;
		}
		log.info("request:" + request);
		if(Consts.TYPE_KEY.equals(request.getType())) {
			try {
				KeyVo keyVo = JSON.parseObject(request.getParams(), KeyVo.class);
				log.info("KeyVo:" + keyVo);
				procParams(keyVo);
			} catch (Exception e) {
				log.info("error");
			}
		} else if(Consts.TYPE_CMD.equals(request.getType())) {
			
		}
		
		/*if("bye".equals(msg)) {
			log.info("open baidu");
			java.net.URI uri = new java.net.URI("http://www.baidu.com");
			java.awt.Desktop.getDesktop().browse(uri);
		}*/
	}

}
