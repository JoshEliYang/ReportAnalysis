package cn.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.springmvc.ReportDAO.GetOrderDao;
import cn.springmvc.model.Order;
import cn.springmvc.service.GetOrderService;

/**
 * 
 * @author johsnon
 *
 */
@Service
public class GetOrderServiceImpl implements GetOrderService {
	@Autowired
	public GetOrderDao dao;

	public List<Order> getOrders(String ID) throws Exception {
		return dao.getOrders(ID);
	}

}
