package cn.springmvc.listener;

import java.util.List;
import java.util.TimerTask;

import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.springmvc.utils.RedisUtil;

import cn.springmvc.ReportDAO.DailySalesDAO;
import cn.springmvc.ReportDAO.LastYearTrafficAnalysisDAO;
import cn.springmvc.ReportDAO.SaleTopAnalysisDAO;
import cn.springmvc.ReportDAO.ThisYearTrafficAnalysisDAO;
import cn.springmvc.ReportDAO.UserAnalysisDao;
import cn.springmvc.model.DailySalesAnalysis;
import cn.springmvc.model.LastYearTrafficAnalysis;
import cn.springmvc.model.SaleTopAnalysis;
import cn.springmvc.model.ThisYearTrafficAnalysis;
import cn.springmvc.model.UserAnalysis;

/**
 * 每隔 固定时间 刷新缓存内容
 * 
 * @author johsnon
 *
 */
public class ReportRefresh extends TimerTask {
	WebApplicationContext context = null;

	public ReportRefresh(WebApplicationContext context) {
		super();
		this.context = context;
	}

	@Override
	public void run() {
		// 日常经营分析 --2016
		DailySales2016();
		// 日常经营分析--2015
		DailySales();
		// 16年流量分析
		ThisYearTrafficAnalysis();
		// 15年流量分析
		LastYearTrafficAnalysis();
		// 销售TOP分析15年
		SaleTopAnalysis();

		/**
		 * 获得有效用户
		 */
		int validNum = UserValidNum(); // 有效用户数量
		int offset = 500;
		int maxPage = (int) Math.ceil(validNum * 1.0 / offset) - 1;
		for (int i = 0; i <= maxPage; i++) {
			UserValidAnalysis(i * offset, offset); // 缓存每个有效数据分页
		}
		AllUserValidAnalysis(); // 缓存所有所有有效数据

		/**
		 * 获得无效用户
		 */
		int InvalidNum = UserInvalidNum(); // 无效用户数量
		int INoffset = 500;
		int INmaxPage = (int) Math.ceil(InvalidNum * 1.0 / INoffset) - 1;
		for (int i = 0; i < INmaxPage; i++) {
			UserInvalidAnalysis(i * INoffset, INoffset); // 缓存每个无效数据分页
		}
		AllUserInvalidAnalysis();	//缓存所有无效用户数据
	}

	/**
	 * 刷新所有流量数据
	 */
	void LastYearTrafficAnalysis() {
		// 获得bean --> 所有流量数据的DAO
		LastYearTrafficAnalysisDAO trafficAnalysisDao = context.getBean(LastYearTrafficAnalysisDAO.class);
		List<LastYearTrafficAnalysis> resList = trafficAnalysisDao.selectAllTrafficAnalysis();
		String outStr = JSON.toJSONString(resList);
		RedisUtil redis = RedisUtil.getRedis();
		redis.setdat("AllTrafficAnalysisData", outStr);
		redis.destroy();
	}

	/**
	 * 刷新所有销售数据
	 */
	void DailySales() {
		// 获得bean
		DailySalesDAO dao = context.getBean(DailySalesDAO.class);
		List<DailySalesAnalysis> resList = dao.selectAllSalesData();
		String outStr = JSON.toJSONString(resList);
		RedisUtil redis = RedisUtil.getRedis();
		redis.setdat("AllSalesData", outStr);
		redis.destroy();
	}
	
	/**
	 * 刷新所有销售数据 2016
	 */
	void DailySales2016() {
		// 获得bean
		DailySalesDAO dao = context.getBean(DailySalesDAO.class);
		List<DailySalesAnalysis> resList = dao.selectAllSalesData2016();
		String outStr = JSON.toJSONString(resList);
		RedisUtil redis = RedisUtil.getRedis();
		redis.setdat("AllSalesData2016", outStr);
		redis.destroy();
	}

	/**
	 * 刷新销售top数据
	 */
	void SaleTopAnalysis() {
		SaleTopAnalysisDAO saleTopAnalysisdao = context.getBean(SaleTopAnalysisDAO.class);
		List<SaleTopAnalysis> resList = saleTopAnalysisdao.selectAllSaleTopData();
		String outStr = JSON.toJSONString(resList);
		RedisUtil redis = RedisUtil.getRedis();
		redis.setdat("AllSaleTopData", outStr);
		redis.destroy();
	}

	/**
	 * 刷新当年流量数据
	 */
	void ThisYearTrafficAnalysis() {
		ThisYearTrafficAnalysisDAO thisYearTrafficAnalysisDAO = context.getBean(ThisYearTrafficAnalysisDAO.class);
		List<ThisYearTrafficAnalysis> resList = thisYearTrafficAnalysisDAO.selectAllTrafficAnalysis();
		String outStr = JSON.toJSONString(resList);
		RedisUtil redis = RedisUtil.getRedis();
		redis.setdat("ThisYearAllTrafficAnalysis", outStr);
		redis.destroy();
	}

	/**
	 * 有效用户数量
	 * 
	 * @return
	 */
	int UserValidNum() {
		String Key = "UserValidNum";
		UserAnalysisDao userDao = context.getBean(UserAnalysisDao.class);
		int resDat = userDao.getNumOfUserAnalysisValid();
		RedisUtil redis = RedisUtil.getRedis();
		redis.setdat(Key, String.valueOf(resDat));
		redis.destroy();
		return resDat;
	}

	/**
	 * 有效用户分析数据（分页）
	 * 
	 * @param st
	 * @param offset
	 */
	void UserValidAnalysis(int st, int offset) {
		String Key = "UserValidFrom" + st + "To" + offset;
		UserAnalysisDao userDao = context.getBean(UserAnalysisDao.class);
		List<UserAnalysis> resList = userDao.getUserAnalysisWithExpenseRecordPage(st, offset);
		String outStr = JSON.toJSONString(resList);
		RedisUtil redis = RedisUtil.getRedis();
		redis.setdat(Key, outStr);
		redis.destroy();
	}

	/**
	 * 有效用户分析数据--全部
	 */
	void AllUserValidAnalysis() {
		String Key = "UerAnalysisWithExpenseRecord";
		UserAnalysisDao userDao = context.getBean(UserAnalysisDao.class);
		List<UserAnalysis> resList = userDao.getUerAnalysisWithExpenseRecord();
		String outStr = JSON.toJSONString(resList);
		RedisUtil redis = RedisUtil.getRedis();
		redis.setdat(Key, outStr);
		redis.destroy();
	}

	/**
	 * 无效用户数量
	 * 
	 * @return
	 */
	int UserInvalidNum() {
		String Key = "UserInvalidNum";
		UserAnalysisDao userDao = context.getBean(UserAnalysisDao.class);
		int resDat = userDao.getNumOfUserAnalysisInvalid();
		RedisUtil redis = RedisUtil.getRedis();
		redis.setdat(Key, String.valueOf(resDat));
		redis.destroy();
		return resDat;
	}

	/**
	 * 无效用户分析数据（分页）
	 * 
	 * @param st
	 * @param offset
	 */
	void UserInvalidAnalysis(int st, int offset) {
		String Key = "UserInvalidFrom" + st + "To" + offset;
		UserAnalysisDao userDao = context.getBean(UserAnalysisDao.class);
		List<UserAnalysis> resList = userDao.getUserAnalysisNoExpenseRecordPage(st, offset);
		String outStr = JSON.toJSONString(resList);
		RedisUtil redis = RedisUtil.getRedis();
		redis.setdat(Key, outStr);
		redis.destroy();
	}

	/**
	 * 所有无效用户数据
	 */
	void AllUserInvalidAnalysis() {
		String Key = "UserAnalysisNoExpenseRecord";
		UserAnalysisDao userDao = context.getBean(UserAnalysisDao.class);
		List<UserAnalysis> resList = userDao.getUserAnalysisNoExpenseRecord();
		String outStr = JSON.toJSONString(resList);
		RedisUtil redis = RedisUtil.getRedis();
		redis.setdat(Key, outStr);
		redis.destroy();
	}
}
