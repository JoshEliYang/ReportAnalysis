package cn.springmvc.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.springmvc.model.DailySalesAnalysis;
import cn.springmvc.model.LastYearTrafficAnalysis;
import cn.springmvc.model.SaleTopAnalysis;
import cn.springmvc.model.ThisYearTrafficAnalysis;
import cn.springmvc.model.UserAnalysis;
import cn.springmvc.service.LastYearTrafficAnalysisService;
import cn.springmvc.service.SaleTopAnalysisService;
import cn.springmvc.service.SalesDataService;
import cn.springmvc.service.ThisYearTrafficAnalysisService;
import cn.springmvc.service.UserAnalysisService;
import jxl.common.Logger;

import com.alibaba.fastjson.JSON;
import com.springmvc.utils.ExcelUtils;


@Scope("prototype")
@Controller
@RequestMapping("/reportexport")
public class ReportExcelExprotController {

	@Autowired
	private LastYearTrafficAnalysisService lastYearTrafficAnalysisService;
	
	@Autowired
	private ThisYearTrafficAnalysisService thisYearTrafficAnalysisService;

	@Autowired
	public SalesDataService salesDataService;
	
	@Autowired
	public SaleTopAnalysisService saleTopAnalysisService;
	
	@Autowired
	public UserAnalysisService userService;
	
	Logger logger=Logger.getLogger(ReportExcelExprotController.class);
	
	/**
	 * @author Josh Yang
	 * @description 所有去年流量
	 * @date 2016-3-1
	 * @return JSON
	 */
	@ResponseBody
	@RequestMapping(value = "/last_year_traffic", method = RequestMethod.GET)
	public void exportLastYearTrafficReportExcel(HttpServletRequest request, 
			HttpServletResponse response) {

		PrintWriter writer = null;
		List<ThisYearTrafficAnalysis> reports = null;
		JSON json = null;
		Map<String, Object> responseMap = null;
		try {
			writer = response.getWriter();
			responseMap = new HashMap<String, Object>();

			reports = lastYearTrafficAnalysisService.selectAllTrafficAnalysisData();
			if (reports == null || reports.isEmpty()) {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json; charset=utf-8");
				responseMap.put("code", 0);
				responseMap.put("data", null);
				responseMap.put("msg", "无数据");
				json = (JSON) JSON.toJSON(responseMap);
				writer.println(json.toJSONString());
				return;
			}

			ExcelUtils.listToExcel(reports,
					this.getLeadToFiledTraffic(), "15年流量分析", response);
		} catch (Exception e) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			responseMap.put("code", -1);
			responseMap.put("data", null);
			responseMap.put("msg", "服务器内部错误");
			json = (JSON) JSON.toJSON(responseMap);
			writer.println(json.toJSONString());
		}
	}
	
	/**
	 * @author Josh Yang
	 * @description 所有今年流量
	 * @date 2016-3-1
	 * @return JSON
	 */
	@ResponseBody
	@RequestMapping(value = "/this_year_traffic", method = RequestMethod.GET)
	public void exportThisYearTrafficReportExcel(HttpServletRequest request, 
			HttpServletResponse response) {

		PrintWriter writer = null;
		List<ThisYearTrafficAnalysis> reports = null;
		JSON json = null;
		Map<String, Object> responseMap = null;
		try {
			writer = response.getWriter();
			responseMap = new HashMap<String, Object>();

			reports = thisYearTrafficAnalysisService.selectAllTrafficAnalysisData();
			if (reports == null || reports.isEmpty()) {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json; charset=utf-8");
				responseMap.put("code", 0);
				responseMap.put("data", null);
				responseMap.put("msg", "无数据");
				json = (JSON) JSON.toJSON(responseMap);
				writer.println(json.toJSONString());
				return;
			}

			ExcelUtils.listToExcel(reports,
					this.getLeadToFiledTraffic(), "16年流量分析", response);
		} catch (Exception e) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			responseMap.put("code", -1);
			responseMap.put("data", null);
			responseMap.put("msg", "服务器内部错误");
			json = (JSON) JSON.toJSON(responseMap);
			writer.println(json.toJSONString());
		}
	}
	
	/**
	 * @author Josh Yang
	 * @description 15年销售分析
	 * @date 2016-3-1
	 * @return JSON
	 */
	@ResponseBody
	@RequestMapping(value = "/sales", method = RequestMethod.GET)
	public void exportAllSalesDataExcel(HttpServletRequest request, 
			HttpServletResponse response) {

		PrintWriter writer = null;
		List<DailySalesAnalysis> reports = null;
		JSON json = null;
		Map<String, Object> responseMap = null;
		try {
			writer = response.getWriter();
			responseMap = new HashMap<String, Object>();

			reports = salesDataService.selectSalesData();
			if (reports == null || reports.isEmpty()) {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json; charset=utf-8");
				responseMap.put("code", 0);
				responseMap.put("data", null);
				responseMap.put("msg", "无数据");
				json = (JSON) JSON.toJSON(responseMap);
				writer.println(json.toJSONString());
				return;
			}

			ExcelUtils.listToExcel(reports,
					this.getLeadToFiledPDailySales(), "15销售日常分析", response);
		} catch (Exception e) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			responseMap.put("code", -1);
			responseMap.put("data", null);
			responseMap.put("msg", "服务器内部错误");
			json = (JSON) JSON.toJSON(responseMap);
			writer.println(json.toJSONString());
		}
	}
	
	/**
	 * @author Josh Yang
	 * @description 15年销售分析
	 * @date 2016-3-1
	 * @return JSON
	 */
	@ResponseBody
	@RequestMapping(value = "/sales/16", method = RequestMethod.GET)
	public void exportAllSalesData2016Excel(HttpServletRequest request, 
			HttpServletResponse response) {

		PrintWriter writer = null;
		List<DailySalesAnalysis> reports = null;
		JSON json = null;
		Map<String, Object> responseMap = null;
		try {
			writer = response.getWriter();
			responseMap = new HashMap<String, Object>();

			reports = salesDataService.selectAllSalesData2016();
			if (reports == null || reports.isEmpty()) {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json; charset=utf-8");
				responseMap.put("code", 0);
				responseMap.put("data", null);
				responseMap.put("msg", "无数据");
				json = (JSON) JSON.toJSON(responseMap);
				writer.println(json.toJSONString());
				return;
			}

			ExcelUtils.listToExcel(reports,
					this.getLeadToFiledPDailySales(), "16销售日常分析", response);
		} catch (Exception e) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			responseMap.put("code", -1);
			responseMap.put("data", null);
			responseMap.put("msg", "服务器内部错误");
			json = (JSON) JSON.toJSON(responseMap);
			writer.println(json.toJSONString());
		}
	}
	
	/**
	 * @author Josh Yang
	 * @description 15年销售top分析
	 * @date 2016-3-1
	 * @return JSON
	 */
	@ResponseBody
	@RequestMapping(value = "/top", method = RequestMethod.GET)
	public void exportAllSalesTopDataExcel(HttpServletRequest request, 
			HttpServletResponse response) {

		PrintWriter writer = null;
		List<SaleTopAnalysis> reports = null;
		JSON json = null;
		Map<String, Object> responseMap = null;
		try {
			writer = response.getWriter();
			responseMap = new HashMap<String, Object>();

			reports = saleTopAnalysisService.selectAllSaleTopData();
			if (reports == null || reports.isEmpty()) {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json; charset=utf-8");
				responseMap.put("code", 0);
				responseMap.put("data", null);
				responseMap.put("msg", "无数据");
				json = (JSON) JSON.toJSON(responseMap);
				writer.println(json.toJSONString());
				return;
			}

			ExcelUtils.listToExcel(reports,
					this.getHeadToFiledTopSales(), "15销售TOP分析", response);
		} catch (Exception e) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			responseMap.put("code", -1);
			responseMap.put("data", null);
			responseMap.put("msg", "服务器内部错误");
			json = (JSON) JSON.toJSON(responseMap);
			writer.println(json.toJSONString());
		}
	}
	
	/**
	 * 获得用户分析报表--有消费记录
	 * 请求全部数据--比较慢，不使用
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/valid",method=RequestMethod.GET)
	public void exportUserAnalysisDataExcel(HttpServletRequest request, 
			HttpServletResponse response){
		
		PrintWriter writer = null;
		JSON json = null;
		Map<String, Object> responseMap = null;
		List<UserAnalysis> reports = null;
		try {
			writer = response.getWriter();
			responseMap = new HashMap<String, Object>();

			reports = userService.getUserAnalysisWithExpenseRecord();
			if (reports == null || reports.isEmpty()) {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json; charset=utf-8");
				responseMap.put("code", 0);
				responseMap.put("data", null);
				responseMap.put("msg", "无数据");
				json = (JSON) JSON.toJSON(responseMap);
				writer.println(json.toJSONString());
				return;
			}

			ExcelUtils.listToExcel(reports,
					this.getHaadToFiledUser(), "有效用户分析", response);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("get valid user report error >>> \n"+e.getMessage());
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			responseMap.put("code", -1);
			responseMap.put("data", null);
			responseMap.put("msg", "服务器内部错误");
			json = (JSON) JSON.toJSON(responseMap);
			writer.println(json.toJSONString());
		}
	}

	//流量
	private LinkedHashMap<String, String> getLeadToFiledTraffic() {
		LinkedHashMap<String, String> superClassMap = new LinkedHashMap<String, String>();
		superClassMap.put("ORDER_CREATE_DATE", "日期");
		superClassMap.put("dayWeek", "星期");
		superClassMap.put("CLIENT_TYPENAME", "订单");
		superClassMap.put("STORE_TYPENAME", "店铺类型");
		superClassMap.put("STORE_NAME", "店铺名称");
		superClassMap.put("ALLORDER", "订单量");
		superClassMap.put("PAYORDER", "支付订单数");
		superClassMap.put("ORDER_PAYMENT_RATE", "支付订单率");

		return superClassMap;
	}

	//销售日常分析
	private LinkedHashMap<String, String> getLeadToFiledPDailySales() {
		LinkedHashMap<String, String> superClassMap = new LinkedHashMap<String, String>();
		superClassMap.put("order_CREATE_DATE", "日期");
		superClassMap.put("dayWeek", "星期");
		superClassMap.put("client_TYPENAME", "订单");
		superClassMap.put("store_TYPENAME", "店铺类型");
		superClassMap.put("store_NAME", "店铺名称");
		superClassMap.put("order_NUMBER", "订单数");
		superClassMap.put("order_PRICE", "客单价");
		superClassMap.put("orderamount", "订单金额");
		superClassMap.put("allyh", "优惠金额");
		superClassMap.put("payment_AMOUNT", "支付金额");
		
		return superClassMap;
	}
	
	//Top销量
	private LinkedHashMap<String, String> getHeadToFiledTopSales() {
		LinkedHashMap<String, String> superClassMap = new LinkedHashMap<String, String>();
		superClassMap.put("ORDER_CREATE_DATE", "时间");
		superClassMap.put("STORE_TYPENAME", "店铺类型");
		superClassMap.put("STORE_NAME", "店铺名称");
		superClassMap.put("CARGO_NO", "商品编号");
		superClassMap.put("CARGO_NAME", "商品名");
		superClassMap.put("goods_class_name", "商品类型");
		superClassMap.put("saleCount", "销售量");
		superClassMap.put("SALEAMOUNT", "销售金额");
		superClassMap.put("totalRate", "毛利");
		superClassMap.put("purchaseAmount", "正常进价");
		superClassMap.put("ratePercent", "毛利率%");
		superClassMap.put("jinbuCount", "进步率（销售量）");
		superClassMap.put("jinbuAmount", "进步率（销售额）");
		superClassMap.put("PAYAMOUNT", "支付金额");
		
		return superClassMap;
	}
	
	//用户
	private LinkedHashMap<String, String> getHaadToFiledUser() {
		LinkedHashMap<String, String> superClassMap = new LinkedHashMap<String, String>();
		superClassMap.put("regist_time", "注册");
		superClassMap.put("nick_name", "会员类型");
		superClassMap.put("real_name", "姓名");
		superClassMap.put("gender", "性别");
		superClassMap.put("phone", "电话");
		superClassMap.put("idCard", "身份信息");
		superClassMap.put("birthday", "生日");
		superClassMap.put("age", "年龄");
		superClassMap.put("total_amount", "总消费额");
		superClassMap.put("num_of_orders", "订单数");
		superClassMap.put("per_customer_transaction", "客单价");
		superClassMap.put("province", "省");
		superClassMap.put("city", "市");
		superClassMap.put("county", "县/区");
		superClassMap.put("address", "地址");
		
		return superClassMap;
	}
}
