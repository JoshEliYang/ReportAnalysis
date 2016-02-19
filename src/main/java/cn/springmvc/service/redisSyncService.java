package cn.springmvc.service;

/**
 * 
 * @author johsnon
 *
 */
public interface redisSyncService extends Runnable {
	public String getId();
	public void setId(String id);
}
