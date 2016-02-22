package com.springmvc.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author johsnon
 *
 */
public class ThreadPoolUtil {
	private static ExecutorService redisPool = Executors.newCachedThreadPool();

	public static ExecutorService getPool(String poolId) {
		//获得redis线程池
		if ("redisPool".equals(poolId)) {
			return redisPool;
		}
		return null;
	}

}
