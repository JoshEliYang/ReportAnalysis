package cn.springmvc.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.springmvc.model.DailySalesAnalysis;
import cn.springmvc.service.SalesDataService;
import cn.springmvc.model.DailyReportParams;

import com.springmvc.utils.HttpUtils;

/**
 * 
 * @author johsnon
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/sales")
public class SalesDataController {

	@Autowired
	public SalesDataService salesDataService;

	Logger logger = Logger.getLogger(SalesDataController.class);

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
		list = salesDataService.selectSalesData();

		if (list != null) {
			return HttpUtils.generateResponse("0", "请求成功", list);
		}

		logger.error("getAllSalesData contorller error >>> ");
		return HttpUtils.generateResponse("-1", "查询失败", null);
	}

	@ResponseBody
	@RequestMapping(value = "/2016", method = RequestMethod.GET)
	public Map<String, Object> getAllSalesData2016() {
		List<DailySalesAnalysis> list = null;
		list = salesDataService.selectAllSalesData2016();

		if (list != null) {
			return HttpUtils.generateResponse("0", "请求成功", list);
		}

		logger.error("getAllSalesData2016 contorller error >>> ");
		return HttpUtils.generateResponse("-1", "查询失败", null);
	}
	
	@ResponseBody
	@RequestMapping(value = "/dailyReport2016", method = RequestMethod.POST)
	public Map<String, Object> getAllSalesDataReport2016(@RequestBody DailyReportParams rp) {
		List<DailySalesAnalysis> list = null;
		String dataReportCount = null;
		list = salesDataService.selectAllSalesDataReport(rp);
		dataReportCount = salesDataService.selectAllSalesDataReportCount();

		if (list != null) {
			return HttpUtils.generateResponseFour("0", "请求成功", list,dataReportCount);
		}	

		logger.error("getAllSalesData2016 contorller error >>> ");
		return HttpUtils.generateResponse("-1", "查询失败", null);
	}
	
	@ResponseBody
	@RequestMapping(value = "/dailyReport2015", method = RequestMethod.POST)
	public Map<String, Object> getAllSalesDataReport2015(@RequestBody DailyReportParams rp) {
		List<DailySalesAnalysis> list = null;
		String dataReportCount = null;
		list = salesDataService.selectAllSalesDataReport2015(rp);
		dataReportCount = salesDataService.selectAllSalesDataReportCount2015();

		if (list != null) {
			return HttpUtils.generateResponseFour("0", "请求成功", list,dataReportCount);
		}

		logger.error("getAllSalesData2016 contorller error >>> ");
		return HttpUtils.generateResponse("-1", "查询失败", null);
	}
	
}
