package cn.springmvc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.springmvc.model.ThisYearTrafficAnalysis;
import cn.springmvc.service.ThisYearTrafficAnalysisService;

import com.springmvc.utils.HttpUtils;

@Controller
@RequestMapping("/this_year_traffic")
public class ThisYearTrafficAnalysisController {
	@Autowired
	public ThisYearTrafficAnalysisService thisYearTrafficAnalysisService;

	/**
	 * @author liqiang
	 * @date 2016-02-01
	 * @desc 调出所有16年的流量数据
	 * @return JSON
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public Map<String, Object> selectAllTrafficData() {
		List<ThisYearTrafficAnalysis> list = null;
		try {
			list = thisYearTrafficAnalysisService
					.selectAllTrafficAnalysisData();
			return HttpUtils.generateResponse("0", "查询成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			return HttpUtils.generateResponse("1", "请求失败", null);
		}
	}
}
