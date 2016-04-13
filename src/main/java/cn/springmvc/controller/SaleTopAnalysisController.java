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

import cn.springmvc.model.SaleTopAnalysis;
import cn.springmvc.service.SaleTopAnalysisService;

import com.springmvc.utils.HttpUtils;

/**
 * 
 * @author johsnon
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/top")
public class SaleTopAnalysisController {

	@Autowired
	public SaleTopAnalysisService saleTopAnalysisService;

	Logger logger = Logger.getLogger(SaleTopAnalysisController.class);

	/**
	 * @author liqiang
	 * @date 2016-02-01
	 * @desc 调出所有15年的销售Top数据
	 * @return JSON
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public Map<String, Object> selectAllTopData() {
		List<SaleTopAnalysis> list = null;
		list = saleTopAnalysisService.selectAllSaleTopData();

		if (list != null) {
			return HttpUtils.generateResponse("0", "请求成功", list);
		}

		logger.error("selectAllTopData contorller error >>> ");
		return HttpUtils.generateResponse("-1", "查询失败", null);
	}

}
