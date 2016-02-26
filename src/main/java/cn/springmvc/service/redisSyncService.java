package cn.springmvc.service;

/**
 * 
 * @author johsnon
 *
 */
public interface redisSyncService extends Runnable {
	public String getId();

	public void setId(String id);

	public String getParameter();

	public void setParameter(String parameter);

	public int[] getParameterInt();

	public void setParameterInt(int[] parameterInt);
}
