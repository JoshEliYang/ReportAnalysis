package cn.springmvc.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springmvc.utils.HttpUtils;

import cn.springmvc.model.UserAnalysis;
import cn.springmvc.service.UserAnalysisService;

/**
 * 
 * @author johsnon
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/user_report")
public class UserAnalysisController {

	@Autowired
	public UserAnalysisService userService;

	Logger logger = Logger.getLogger(UserAnalysisController.class);

	/**
	 * 获得用户分析报表--有消费记录 请求全部数据--比较慢，不使用
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/valid", method = RequestMethod.GET)
	public Map<String, Object> getUserAnalysis() {
		List<UserAnalysis> userReport = null;
		try {
			userReport = userService.getUserAnalysisWithExpenseRecord();
			return HttpUtils.generateResponse("0", "请求成功", userReport);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getUserAnalysis contorller error >>> " + e.getMessage());
			return HttpUtils.generateResponse("1", "请求失败", userReport);
		}
	}

	/**
	 * 获得用户分析报表--有消费记录_分页
	 * 
	 * @param st
	 * @param ed
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/valid/{st}/{ed}", method = RequestMethod.GET)
	public Map<String, Object> getUserAnalysisPage(@PathVariable int st, @PathVariable int ed) {
		List<UserAnalysis> userReport = null;
		try {
			userReport = userService.getUserAnalysisWithExpenseRecord(st, ed);
			return HttpUtils.generateResponse("0", "请求成功", userReport);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getUserAnalysisPage contorller error >>> " + e.getMessage());
			return HttpUtils.generateResponse("1", "请求失败", null);
		}
	}

	/**
	 * 获得用户分析报表--无消费记录 请求全部数据--非常慢，不使用
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/invalid", method = RequestMethod.GET)
	public Map<String, Object> getUserAnalysisNoExpense() {
		List<UserAnalysis> userReport = null;
		try {
			userReport = userService.getUserAnalysisNoExpenseRecord();
			return HttpUtils.generateResponse("0", "请求成功", userReport);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getUserAnalysisNoExpense contorller error >>> " + e.getMessage());
			return HttpUtils.generateResponse("1", "请求失败", null);
		}
	}

	/**
	 * 获得用户分析报表--无消费记录_分页
	 * 
	 * @param st
	 * @param ed
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/invalid/{st}/{ed}", method = RequestMethod.GET)
	public Map<String, Object> getUserAnalysisPageNoExpense(@PathVariable int st, @PathVariable int ed) {
		List<UserAnalysis> userReport = null;
		try {
			userReport = userService.getUserAnalysisNoExpenseRecord(st, ed);
			return HttpUtils.generateResponse("0", "请求成功", userReport);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getUserAnalysisPageNoExpense contorller error >>> " + e.getMessage());
			return HttpUtils.generateResponse("1", "请求失败", null);
		}
	}

	/**
	 * 获得有效用户数据量
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/valid/num", method = RequestMethod.GET)
	public Map<String, Object> getNumOfUserAnalysisValid() {
		int numOfValid = 0;
		try {
			numOfValid = userService.getNumOfUserAnalysisValid();
			return HttpUtils.generateResponse("0", "请求成功", numOfValid);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getNumOfUserAnalysisValid contorller error >>> " + e.getMessage());
			return HttpUtils.generateResponse("1", "请求失败", null);
		}
	}

	/**
	 * 获得无效用户数量
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "invalid/num", method = RequestMethod.GET)
	public Map<String, Object> getNumOfUserAnalysisInvalid() {
		int numOfInvalid = 0;
		try {
			numOfInvalid = userService.getNumOfUserAnalysisInvalid();
			return HttpUtils.generateResponse("0", "请求成功", numOfInvalid);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getNumOfUserAnalysisInvalid contorller error >>> " + e.getMessage());
			return HttpUtils.generateResponse("1", "请求失败", null);
		}
	}
}
