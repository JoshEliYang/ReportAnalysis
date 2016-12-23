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

import cn.springmvc.model.DailyReportParams;
import cn.springmvc.model.DailySalesAnalysis;
import cn.springmvc.model.ThisYearTrafficAnalysis;
import cn.springmvc.service.ThisYearTrafficAnalysisService;

import com.springmvc.utils.HttpUtils;

@Scope("prototype")
@Controller
@RequestMapping("/this_year_traffic")
public class ThisYearTrafficAnalysisController {
	@Autowired
	public ThisYearTrafficAnalysisService thisYearTrafficAnalysisService;

	Logger logger = Logger.getLogger(ThisYearTrafficAnalysisController.class);

	/**
	 * @author liqiang
	 * @date 2016-02-01
	 * @desc 调出所有16年的流量数据
	 * @return JSON
	 * 
	 * 
	 * 
	 *         Abandoned !! Don't request it again!!
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public Map<String, Object> selectAllTrafficData() {
		List<ThisYearTrafficAnalysis> list = null;
		list = thisYearTrafficAnalysisService.selectAllTrafficAnalysisData();

		if (list != null) {
			return HttpUtils.generateResponse("0", "查询成功", list);
		}

		logger.error("selectAllTrafficData contorller error >>> ");
		return HttpUtils.generateResponse("1", "请求失败", null);
	}

	@ResponseBody
	@RequestMapping(value = "/traffic2016", method = RequestMethod.POST)
	public Map<String, Object> getTrafficReport2016(@RequestBody DailyReportParams rp) {
		List<DailySalesAnalysis> list = null;
		String trafficCount = null;
		list = thisYearTrafficAnalysisService.selecttraffic(rp);
		trafficCount = thisYearTrafficAnalysisService.getCount();

		if (list != null) {
			return HttpUtils.generateResponseFour("0", "请求成功", list, trafficCount);
		}

		logger.error("getAllSalesData2016 contorller error >>> ");
		return HttpUtils.generateResponse("-1", "查询失败", null);
	}

	@ResponseBody
	@RequestMapping(value = "/traffic2015", method = RequestMethod.POST)
	public Map<String, Object> getTrafficReport2015(@RequestBody DailyReportParams rp) {
		List<DailySalesAnalysis> list = null;
		String trafficCount = null;
		list = thisYearTrafficAnalysisService.selecttraffic2(rp);
		trafficCount = thisYearTrafficAnalysisService.getCount2();

		if (list != null) {
			return HttpUtils.generateResponseFour("0", "请求成功", list, trafficCount);
		}

		logger.error("getAllSalesData2016 contorller error >>> ");
		return HttpUtils.generateResponse("-1", "查询失败", null);
	}
}
