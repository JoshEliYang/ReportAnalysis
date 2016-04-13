package cn.springmvc.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springmvc.utils.HttpUtils;

import cn.springmvc.model.LastYearTrafficAnalysis;
import cn.springmvc.service.impl.LastYearTrafficAnalysisServiceImpl;

/**
 * 
 * @author johsnon
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/last_year_traffic")
public class LastYearTrafficAnalysisController {

	@Autowired
	public LastYearTrafficAnalysisServiceImpl trafficAnalysisService;

	Logger logger = Logger.getLogger(LastYearTrafficAnalysisController.class);

	/**
	 * @author liqiang
	 * @date 2016-02-01
	 * @desc 调出所有15年的流量数据
	 * @return JSON
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public Map<String, Object> selectAllTrafficData() {
		List<LastYearTrafficAnalysis> list = null;
		list = trafficAnalysisService.selectAllTrafficAnalysisData();

		if (list != null) {
			return HttpUtils.generateResponse("0", "请求成功", list);
		}

		logger.error("selectAllTrafficData controller error >>> ");
		return HttpUtils.generateResponse("-1", "查询失败", null);
	}
}
