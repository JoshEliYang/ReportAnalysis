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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.springmvc.utils.ExcelUtils;
import com.springmvc.utils.HttpUtils;

import cn.springmvc.model.paymentSerialNo.PaymentSerialNumber;
import cn.springmvc.model.paymentSerialNo.SerialNumberQuery;
import cn.springmvc.service.PaymentSerialNumberService;

/**
 * 
 * @author johnson
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/serialNumber")
public class PaymentSerialNumberController {

	@Autowired
	private PaymentSerialNumberService serialNumberService;

	/**
	 * query payment serial numbers
	 * 
	 * Notice: date format yyyy-MM-dd type: 0.all 1.pay 2.refund
	 * 
	 * @param query
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public Map<String, Object> query(@RequestBody SerialNumberQuery query) {
		List<PaymentSerialNumber> res = null;
		query.setEndTime(query.getEndTime() + " 24:00:00");
		try {
			res = serialNumberService.getListInCache(query);
			return HttpUtils.generateResponse(String.valueOf(serialNumberService.getCount(query)),
					"query payment serial number success", res);
		} catch (Exception e) {
			e.printStackTrace();
			return HttpUtils.generateResponse("-1", "failed when query payment serial number", null);
		}
	}

	/**
	 * export payment serial numbers
	 * 
	 * @param startTime
	 * @param endTime
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value = "/{startTime}/{endTime}", method = RequestMethod.GET)
	public void export(@PathVariable final String startTime, @PathVariable final String endTime,
			HttpServletRequest request, HttpServletResponse response) {

		List<PaymentSerialNumber> res = null;
		JSON json = null;
		Map<String, Object> responseMap = null;
		PrintWriter writer = null;

		try {
			writer = response.getWriter();
			responseMap = new HashMap<String, Object>();

			res = serialNumberService.getListInCache(new SerialNumberQuery() {
				{
					this.setStartTime(startTime);
					this.setEndTime(endTime + " 24:00:00");
					this.setAll(true);
				}
			});

			if (res == null || res.isEmpty()) {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json; charset=utf-8");
				responseMap.put("code", 0);
				responseMap.put("data", null);
				responseMap.put("msg", "无数据");
				json = (JSON) JSON.toJSON(responseMap);
				writer.println(json.toJSONString());
				return;
			}

			ExcelUtils.listToExcel(res, this.getHeader(), "支付流水号", response);
		} catch (Exception e) {
			e.printStackTrace();

			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			responseMap.put("code", -1);
			responseMap.put("data", null);
			responseMap.put("msg", "服务器内部错误");
			json = (JSON) JSON.toJSON(responseMap);
			writer.println(json.toJSONString());
		}
	}

	private LinkedHashMap<String, String> getHeader() {
		LinkedHashMap<String, String> superClassMap = new LinkedHashMap<String, String>();
		superClassMap.put("pay_order_create_time", "日期");
		superClassMap.put("order_no", "订单号");
		superClassMap.put("out_trade_no", "支付流水号");
		superClassMap.put("pay_order_fee", "金额");
		superClassMap.put("STORE_NAME", "仓库");
		superClassMap.put("pay_type", "付款/退款");

		return superClassMap;
	}

}
