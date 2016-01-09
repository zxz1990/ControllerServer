package com.zxz.controllerserver.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolManager {
	private static ExecutorService mExecutor = Executors.newCachedThreadPool();

	public static void execute(Runnable runnable) {
		mExecutor.execute(runnable);
	}
	
	
}
