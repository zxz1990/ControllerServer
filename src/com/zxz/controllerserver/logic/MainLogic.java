package com.zxz.controllerserver.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import com.zxz.controllerserver.ui.interfaces.INotifier;
import com.zxz.controllerserver.utils.ThreadPoolManager;

public class MainLogic {
	
	Logger log = Logger.getGlobal(); 
	
	private ServerSocket mServer;
	private boolean needRunning = false;
	private INotifier mNotifier;
	private int mPort;
	
	
	public MainLogic(int port) throws IOException {
		// TODO Auto-generated constructor stub
		mPort = port;
	}
	
	private void init() {
		
	}
	
	public void startListening() {
		log.info("startListening");
		if(needRunning) {
			return;
		}
		
		if(mServer == null) {
			try {
				mServer = new ServerSocket(mPort);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				notify("打开端口错误");
			}
		}
		
		needRunning = true;
		loop();
	}
	
	public void stopListening() {
		log.info("stopListening");
		needRunning = false;
	}
	
	private void loop() {
		ThreadPoolManager.execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				log.info("start running: at port " + mServer.getLocalPort());
				while(true) {
					if(!needRunning) {
						break;
					}
					log.info("proc running");
					try {
						log.info("MainLogic Thread:" + Thread.currentThread().getId());
						Socket socket = mServer.accept();
						SocketLogic.procSocket(socket);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						log.info("IOException");
						e.printStackTrace();
					}
				}
				log.info("end running");
			}
		});
	}
	
	public void setNotifier(INotifier notifier) {
		this.mNotifier = notifier;
	}
	
	public void notify(String msg) {
		if(mNotifier != null) {
			mNotifier.notifyMessage(msg);
		}
	}

}
