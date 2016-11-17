package cn.springmvc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springmvc.utils.HttpUtils;

import cn.springmvc.model.Order;
import cn.springmvc.service.GetOrderService;

/**
 * 
 * @author johsnon
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/order")
public class GetOrderController {
	@Autowired
	public GetOrderService service;

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public Map<String, Object> getUserOrders(@RequestBody String ID) {
		List<Order> res = null;
		try {
			res = service.getOrders(ID);
		} catch (Exception e) {
			e.printStackTrace();
			return HttpUtils.generateResponse("1", "请求失败", null);
		}
		return HttpUtils.generateResponse("0", "查询成功", res);
	}
}
