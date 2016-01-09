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

import com.zxz.controllerserver.utils.ThreadPoolManager;

public class SocketLogic {
	private static Logger log = Logger.getGlobal(); 
	
	public SocketLogic() {
		// TODO Auto-generated constructor stub
	}
	
	public static void procSocket(Socket socket) {
		if(socket == null) {
			return;
		}
		ThreadPoolManager.execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub

				log.info("Current Thread:" + Thread.currentThread());
				log.info("Socket:" + socket.toString());
				try {
					BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
					PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
					int m;
					String msg = "";
					while((m=reader.read()) != -1) {
						char ch = (char)m;
						log.info(msg);
						Robot robot = new Robot();
						robot.keyPress(KeyEvent.VK_W);
					}
					log.info(msg);
					if("bye".equals(msg)) {
						log.info("open baidu");
						java.net.URI uri = new java.net.URI("http://www.baidu.com");
						java.awt.Desktop.getDesktop().browse(uri);
					}
					reader.close();
					writer.close();
				} catch (IOException | URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

}
