package cn.springmvc.ReportDAO;

import java.util.List;

import cn.springmvc.model.Order;

/**
 * 
 * @author johsnon
 *
 */
public interface GetOrderDao {
	public List<Order> getOrders(String ID);
}
