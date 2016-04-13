package cn.springmvc.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.springmvc.utils.MemcacheUtil;

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
import cn.springmvc.service.RefreshService;

/**
 * 定时任务
 * 
 * @author johsnon
 *
 */
// @Service("activityAction")
@Service()
public class RefreshServiceImpl implements RefreshService {
	@Autowired
	public LastYearTrafficAnalysisDAO trafficAnalysisDao;
	@Autowired
	public DailySalesDAO dao;
	@Autowired
	public SaleTopAnalysisDAO saleTopAnalysisdao;
	@Autowired
	public ThisYearTrafficAnalysisDAO thisYearTrafficAnalysisDAO;
	@Autowired
	public UserAnalysisDao userDao;

	Logger logger = Logger.getLogger(RefreshServiceImpl.class);

	public void refreshMemcache() {
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
		AllUserInvalidAnalysis(); // 缓存所有无效用户数据
	}

	/**
	 * 刷新所有流量数据
	 */
	void LastYearTrafficAnalysis() {
		// 获得bean --> 所有流量数据的DAO
		// LastYearTrafficAnalysisDAO trafficAnalysisDao =
		// context.getBean(LastYearTrafficAnalysisDAO.class);
		List<LastYearTrafficAnalysis> resList = null;
		String outStr = null;

		try {
			resList = trafficAnalysisDao.selectAllTrafficAnalysis();
			outStr = JSON.toJSONString(resList);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error("get Dao error >>> " + e1.getMessage());
			return;
		}
		// RedisUtil redis = RedisUtil.getRedis();
		// redis.setdat("AllTrafficAnalysisData", outStr);
		// redis.destroy();

		MemcacheUtil memcache = null;
		try {
			memcache = MemcacheUtil.getInstance();
			memcache.setDat("AllTrafficAnalysisData", outStr);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("memcache insert error >>> " + e.getMessage());
		} finally {
			try {
				memcache.destory();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("memcache close error >>> " + e.getMessage());
			}
		}

	}

	/**
	 * 刷新所有销售数据
	 */
	void DailySales() {
		// 获得bean
		// DailySalesDAO dao = context.getBean(DailySalesDAO.class);
		List<DailySalesAnalysis> resList;
		String outStr = null;
		try {
			resList = dao.selectAllSalesData();
			outStr = JSON.toJSONString(resList);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error("get Dao error >>> " + e1.getMessage());
			return;
		}
		// RedisUtil redis = RedisUtil.getRedis();
		// redis.setdat("AllSalesData", outStr);
		// redis.destroy();

		MemcacheUtil memcache = null;
		try {
			memcache = MemcacheUtil.getInstance();
			memcache.setDat("AllSalesData", outStr);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("memcache insert error >>> " + e.getMessage());
		} finally {
			try {
				memcache.destory();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("memcache close error >>> " + e.getMessage());
			}
		}
	}

	/**
	 * 刷新所有销售数据 2016
	 */
	void DailySales2016() {
		// 获得bean
		// DailySalesDAO dao = context.getBean(DailySalesDAO.class);
		List<DailySalesAnalysis> resList;
		String outStr = null;
		try {
			resList = dao.selectAllSalesData2016();
			outStr = JSON.toJSONString(resList);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error("get Dao error >>> " + e1.getMessage());
			return;
		}
		// RedisUtil redis = RedisUtil.getRedis();
		// redis.setdat("AllSalesData2016", outStr);
		// redis.destroy();

		MemcacheUtil memcache = null;
		try {
			memcache = MemcacheUtil.getInstance();
			memcache.setDat("AllSalesData2016", outStr);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("memcache insert error >>> " + e.getMessage());
		} finally {
			try {
				memcache.destory();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("memcache close error >>> " + e.getMessage());
			}
		}
	}

	/**
	 * 刷新销售top数据
	 */
	void SaleTopAnalysis() {
		// SaleTopAnalysisDAO saleTopAnalysisdao =
		// context.getBean(SaleTopAnalysisDAO.class);
		List<SaleTopAnalysis> resList;
		String outStr = null;

		try {
			resList = saleTopAnalysisdao.selectAllSaleTopData();
			outStr = JSON.toJSONString(resList);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error("get Dao error >>> " + e1.getMessage());
			return;
		}
		// RedisUtil redis = RedisUtil.getRedis();
		// redis.setdat("AllSaleTopData", outStr);
		// redis.destroy();

		MemcacheUtil memcache = null;
		try {
			memcache = MemcacheUtil.getInstance();
			memcache.setDat("AllSaleTopData", outStr);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("memcache insert error >>> " + e.getMessage());
		} finally {
			try {
				memcache.destory();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("memcache close error >>> " + e.getMessage());
			}
		}
	}

	/**
	 * 刷新当年流量数据
	 */
	void ThisYearTrafficAnalysis() {
		// ThisYearTrafficAnalysisDAO thisYearTrafficAnalysisDAO =
		// context.getBean(ThisYearTrafficAnalysisDAO.class);
		List<ThisYearTrafficAnalysis> resList = null;
		String outStr = null;
		try {
			resList = thisYearTrafficAnalysisDAO.selectAllTrafficAnalysis();
			outStr = JSON.toJSONString(resList);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error("get Dao error >>> " + e1.getMessage());
			return;
		}
		// RedisUtil redis = RedisUtil.getRedis();
		// redis.setdat("ThisYearAllTrafficAnalysis", outStr);
		// redis.destroy();

		MemcacheUtil memcache = null;
		try {
			memcache = MemcacheUtil.getInstance();
			memcache.setDat("ThisYearAllTrafficAnalysis", outStr);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("memcache insert error >>> " + e.getMessage());
		} finally {
			try {
				memcache.destory();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("memcache close error >>> " + e.getMessage());
			}
		}
	}

	/**
	 * 有效用户数量
	 * 
	 * @return
	 */
	int UserValidNum() {
		String Key = "UserValidNum";
		// UserAnalysisDao userDao = context.getBean(UserAnalysisDao.class);
		int resDat = 0;
		try {
			resDat = userDao.getNumOfUserAnalysisValid();
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error("get Dao error >>> " + e1.getMessage());
			return 0;
		}
		// RedisUtil redis = RedisUtil.getRedis();
		// redis.setdat(Key, String.valueOf(resDat));
		// redis.destroy();

		MemcacheUtil memcache = null;
		try {
			memcache = MemcacheUtil.getInstance();
			memcache.setDat(Key, String.valueOf(resDat));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("memcache insert error >>> " + e.getMessage());
		} finally {
			try {
				memcache.destory();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("memcache close error >>> " + e.getMessage());
			}
		}

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
		// UserAnalysisDao userDao = context.getBean(UserAnalysisDao.class);
		List<UserAnalysis> resList = null;
		String outStr = null;
		try {
			resList = userDao.getUserAnalysisWithExpenseRecordPage(st, offset);
			outStr = JSON.toJSONString(resList);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error("get Dao error >>> " + e1.getMessage());
			return;
		}
		// RedisUtil redis = RedisUtil.getRedis();
		// redis.setdat(Key, outStr);
		// redis.destroy();

		MemcacheUtil memcache = null;
		try {
			memcache = MemcacheUtil.getInstance();
			memcache.setDat(Key, outStr);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("memcache insert error >>> " + e.getMessage());
		} finally {
			try {
				memcache.destory();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("memcache close error >>> " + e.getMessage());
			}
		}
	}

	/**
	 * 有效用户分析数据--全部
	 */
	void AllUserValidAnalysis() {
		String Key = "UerAnalysisWithExpenseRecord";
		// UserAnalysisDao userDao = context.getBean(UserAnalysisDao.class);
		List<UserAnalysis> resList = null;
		String outStr = null;

		try {
			resList = userDao.getUerAnalysisWithExpenseRecord();
			outStr = JSON.toJSONString(resList);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error("get Dao error >>> " + e1.getMessage());
			return;
		}
		// RedisUtil redis = RedisUtil.getRedis();
		// redis.setdat(Key, outStr);
		// redis.destroy();

		MemcacheUtil memcache = null;
		try {
			memcache = MemcacheUtil.getInstance();
			memcache.setDat(Key, outStr);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("memcache insert error >>> " + e.getMessage());
		} finally {
			try {
				memcache.destory();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("memcache close error >>> " + e.getMessage());
			}
		}
	}

	/**
	 * 无效用户数量
	 * 
	 * @return
	 */
	int UserInvalidNum() {
		String Key = "UserInvalidNum";
		// UserAnalysisDao userDao = context.getBean(UserAnalysisDao.class);
		int resDat = 0;
		try {
			resDat = userDao.getNumOfUserAnalysisInvalid();
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error("get Dao error >>> " + e1.getMessage());
			return 0;
		}
		// RedisUtil redis = RedisUtil.getRedis();
		// redis.setdat(Key, String.valueOf(resDat));
		// redis.destroy();

		MemcacheUtil memcache = null;
		try {
			memcache = MemcacheUtil.getInstance();
			memcache.setDat(Key, String.valueOf(resDat));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("memcache insert error >>> " + e.getMessage());
		} finally {
			try {
				memcache.destory();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("memcache close error >>> " + e.getMessage());
			}
		}

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
		// UserAnalysisDao userDao = context.getBean(UserAnalysisDao.class);
		List<UserAnalysis> resList = null;
		String outStr = null;
		try {
			resList = userDao.getUserAnalysisNoExpenseRecordPage(st, offset);
			outStr = JSON.toJSONString(resList);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error("get Dao error >>> " + e1.getMessage());
			return;
		}
		// RedisUtil redis = RedisUtil.getRedis();
		// redis.setdat(Key, outStr);
		// redis.destroy();

		MemcacheUtil memcache = null;
		try {
			memcache = MemcacheUtil.getInstance();
			memcache.setDat(Key, outStr);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("memcache insert error >>> " + e.getMessage());
		} finally {
			try {
				memcache.destory();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("memcache close error >>> " + e.getMessage());
			}
		}
	}

	/**
	 * 所有无效用户数据
	 */
	void AllUserInvalidAnalysis() {
		String Key = "UserAnalysisNoExpenseRecord";
		// UserAnalysisDao userDao = context.getBean(UserAnalysisDao.class);
		List<UserAnalysis> resList = null;
		String outStr = null;
		try {
			resList = userDao.getUserAnalysisNoExpenseRecord();
			outStr = JSON.toJSONString(resList);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error("get Dao error >>> " + e1.getMessage());
			return;
		}
		// RedisUtil redis = RedisUtil.getRedis();
		// redis.setdat(Key, outStr);
		// redis.destroy();

		MemcacheUtil memcache = null;
		try {
			memcache = MemcacheUtil.getInstance();
			memcache.setDat(Key, outStr);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("memcache insert error >>> " + e.getMessage());
		} finally {
			try {
				memcache.destory();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("memcache close error >>> " + e.getMessage());
			}
		}
	}
}
