package com.springmvc.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 单例：静态内部类
 * @author johsnon
 *
 */
public class RedisUtil {
	
	private static class RedisUtilHolder{
		private static final RedisUtil redis=new RedisUtil();
	}

	private RedisUtil() {
		super();
		setup();
	}

	public static RedisUtil getRedis() {
		return RedisUtilHolder.redis;
	}
	
	private JedisPool pool;
	private Jedis jedis;
	
	public void setup(){
		JedisPoolConfig config = new JedisPoolConfig();  
        //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；  
        //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
        config.setMaxTotal(600);
        config.setMaxIdle(500);  
        //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。  
        config.setMaxIdle(5);  
        //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；  
        config.setMaxWaitMillis(1000 * 100);  
        //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；  
        config.setTestOnBorrow(true);  
        
		pool = new JedisPool(config,"127.0.0.1",6379);
		jedis = pool.getResource();
	}
	
	
	public void setdat(String key,String value){
		jedis.set(key, value);
		jedis.expire(key, 24*3600);
	}
	
	public String getdat(String key){
		String res=jedis.get(key);
		jedis.expire(key, 24*3600);
		return res;
	}
	
	//释放jedis资源
	public void destroy(){
		if(jedis!=null){
			pool.returnResource(jedis);
		}
	}
	
}
