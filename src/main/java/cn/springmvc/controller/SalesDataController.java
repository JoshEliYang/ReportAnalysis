package cn.springmvc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.springmvc.model.DailySalesAnalysis;
import cn.springmvc.service.SalesDataService;

import com.springmvc.utils.HttpUtils;

@Scope("prototype")
@Controller
@RequestMapping("/sales")
public class SalesDataController {

	@Autowired
	public SalesDataService salesDataService;

	/**
	 * @author liqiang
	 * @date 2016-01-29
	 * @desc 调出所有16年之前的销售数据
	 * @return JSON
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public Map<String, Object> getAllSalesData() {
		List<DailySalesAnalysis> list = null;
		try {
			list = salesDataService.selectSalesData();
			return HttpUtils.generateResponse("0", "请求成功", list);

		} catch (Exception e) {
			return HttpUtils.generateResponse("-1", "查询失败", null);
		}
	}
}
