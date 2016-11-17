package cn.springmvc.service;

import java.util.List;

import cn.springmvc.model.Order;

/**
 * 
 * @author johsnon
 *
 */
public interface GetOrderService {
	public List<Order> getOrders(String ID) throws Exception;
}
