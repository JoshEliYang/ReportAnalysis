package cn.springmvc.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
import cn.springmvc.model.DailyReportParams;
import cn.springmvc.model.DailySalesAnalysis;
import cn.springmvc.model.LastYearTrafficAnalysis;
import cn.springmvc.model.PaginationParams;
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
	public LastYearTrafficAnalysisDAO lastYearTrafficAnalysisDao;
	@Autowired
	public DailySalesDAO dao;
	@Autowired
	public SaleTopAnalysisDAO saleTopAnalysisdao;
	@Autowired
	public ThisYearTrafficAnalysisDAO thisYearTrafficAnalysisDAO;
	@Autowired
	public UserAnalysisDao userDao;

	public static ExecutorService pool = Executors.newFixedThreadPool(20);

	Logger logger = Logger.getLogger(RefreshServiceImpl.class);

	public void refreshMemcache() {
		logger.error("start to refresh memcache ...");

		pool.execute(new Thread(new queryHandler(method_type.dailySalesRefresh16, 0, 0)));
		pool.execute(new Thread(new queryHandler(method_type.dailySalesRefresh15, 0, 0)));
		pool.execute(new Thread(new queryHandler(method_type.trafficAnalysisRefresh16, 0, 0)));
		pool.execute(new Thread(new queryHandler(method_type.trafficAnalysisRefresh15, 0, 0)));
		pool.execute(new Thread(new queryHandler(method_type.salesTopRefresh, 0, 0)));
		pool.execute(new Thread(new queryHandler(method_type.UserValidRefresh, 0, 0)));
		pool.execute(new Thread(new queryHandler(method_type.UserInvalidRefresh, 0, 0)));

		logger.error("refresh memcache finished ...");
	}

	enum method_type {
		dailySalesRefresh16, dailySalesRefresh15, trafficAnalysisRefresh16, trafficAnalysisRefresh15, salesTopRefresh, UserValidRefresh, UserInvalidRefresh,

		dailySales15, dailySales16, trafficAnalysis15, trafficAnalysis16, salesTop, UserValid, UserInvalid
	}

	class queryHandler implements Runnable {
		private method_type method;
		int offset;
		int count;

		public queryHandler(method_type method, int offset, int count) {
			this.method = method;
			this.offset = offset;
			this.count = count;
		}

		public void run() {
			try {
				if (method == method_type.dailySalesRefresh16) {
					dailySalesRefresh(2016);
				} else if (method == method_type.dailySalesRefresh15) {
					dailySalesRefresh(2015);
				} else if (method == method_type.trafficAnalysisRefresh16) {
					trafficAnalysisRefresh(2016);
				} else if (method == method_type.trafficAnalysisRefresh15) {
					trafficAnalysisRefresh(2015);
				} else if (method == method_type.salesTopRefresh) {
					salesTopRefresh();
				} else if (method == method_type.UserValidRefresh) {
					UserValidRefresh();
				} else if (method == method_type.UserInvalidRefresh) {
					UserInvalidRefresh();
				}

				if (method == method_type.dailySales15) {
					DailySales(new DailyReportParams(count, offset, 2015));
				} else if (method == method_type.dailySales16) {
					DailySales(new DailyReportParams(count, offset, 2016));
				}
				if (method == method_type.trafficAnalysis16) {
					trafficAnalysis(new DailyReportParams(count, offset, 2016));
				} else if (method == method_type.trafficAnalysis15) {
					trafficAnalysis(new DailyReportParams(count, offset, 2015));
				}
				if (method == method_type.salesTop) {
					salesTop(new PaginationParams(count, offset));
				}
				if (method == method_type.UserValid) {
					UserValid(offset, count);
				} else if (method == method_type.UserInvalid) {
					UserInvalid(offset, count);
				}

			} catch (Exception e) {
				e.printStackTrace();
				logger.error("refresh error occurred! >>>> " + e.getMessage());
			}
		}

		/**
		 * 刷新日常销售所有数据
		 * 
		 * @param year
		 * @throws Exception
		 */
		public void dailySalesRefresh(int year) throws Exception {
			int total = DailySales(year);
			int count = 100;
			for (int i = 0; total > i * count; i++) {
				Thread thread = null;
				if (year == 2016)
					thread = new Thread(new queryHandler(method_type.dailySales16, i * count, count));
				else if (year == 2015)
					thread = new Thread(new queryHandler(method_type.dailySales15, i * count, count));
				pool.execute(thread);
			}
		}

		/**
		 * 刷新全部流量分析报表
		 * 
		 * @param year
		 * @throws Exception
		 */
		public void trafficAnalysisRefresh(int year) throws Exception {
			int total = trafficAnalysis(year);
			int count = 100;
			for (int i = 0; total > i * count; i++) {
				Thread thread = null;
				if (year == 2016)
					thread = new Thread(new queryHandler(method_type.trafficAnalysis16, i * count, count));
				else if (year == 2015)
					thread = new Thread(new queryHandler(method_type.trafficAnalysis15, i * count, count));
				pool.execute(thread);
			}
		}

		/**
		 * 刷新全部商品销量TOP
		 * 
		 * @throws Exception
		 */
		public void salesTopRefresh() throws Exception {
			int total = salesTop();
			int count = 100;
			for (int i = 0; total > i * count; i++) {
				Thread thread = new Thread(new queryHandler(method_type.salesTop, i * count, count));
				pool.execute(thread);
			}
		}

		/**
		 * 刷新全部有效用户
		 * 
		 * @throws Exception
		 */
		public void UserValidRefresh() throws Exception {
			int total = UserValid();
			int count = 500;
			for (int i = 0; total > i * count; i++) {
				Thread thread = new Thread(new queryHandler(method_type.UserValid, i * count, count));
				pool.execute(thread);
			}
		}

		/**
		 * 刷新全部无效用户数据
		 * 
		 * @throws Exception
		 */
		public void UserInvalidRefresh() throws Exception {
			int total = UserInvalid();
			int count = 500;
			for (int i = 0; total > i * count; i++) {
				Thread thread = new Thread(new queryHandler(method_type.UserInvalid, i * count, count));
				pool.execute(thread);
			}
		}

		/**
		 * 日常报表全部和数量
		 * 
		 * @throws Exception
		 */
		int DailySales(int year) throws Exception {
			String key = "AllSalesData" + year;
			List<DailySalesAnalysis> resList = null;
			if (year == 2016) {
				resList = dao.selectAllSalesData2016();
			} else if (year == 2015) {
				resList = dao.selectAllSalesData();
			}
			String outStr = JSON.toJSONString(resList);
			putIntoMemcache(key, outStr);

			key = key + "_num";
			int num = resList.size();
			putIntoMemcache(key, JSON.toJSONString(num));
			return num;
		}

		/**
		 * 日常报表分页
		 * 
		 * @param rp
		 * @throws Exception
		 */
		void DailySales(DailyReportParams rp) throws Exception {
			String key = "AllSalesData" + rp.getYear() + "_" + rp.getOffset() + "_" + rp.getLimit();
			List<DailySalesAnalysis> res = null;
			if (2016 == rp.getYear()) {
				res = dao.selectAllSalesDataReport(rp);
			} else if (2015 == rp.getYear()) {
				res = dao.selectAllSalesDataReport2015(rp);
			}
			String outStr = JSON.toJSONString(res);
			putIntoMemcache(key, outStr);
		}

		/**
		 * 流量分析
		 * 
		 * @param year
		 * @throws Exception
		 */
		int trafficAnalysis(int year) throws Exception {
			String key = "TrafficAnalysis" + year;
			List<ThisYearTrafficAnalysis> resList = null;
			if (year == 2016) {
				resList = thisYearTrafficAnalysisDAO.selectAllTrafficAnalysis();
			} else if (year == 2015) {
				resList = lastYearTrafficAnalysisDao.selectAllTrafficAnalysis();
			}
			String outStr = JSON.toJSONString(resList);
			putIntoMemcache(key, outStr);

			int num = resList.size();
			putIntoMemcache(key + "_num", JSON.toJSONString(num));
			return num;
		}

		/**
		 * 流量分析（分页）
		 * 
		 * @param rp
		 * @throws Exception
		 */
		void trafficAnalysis(DailyReportParams rp) throws Exception {
			String key = "TrafficAnalysis" + rp.getYear() + "_" + rp.getOffset() + "_" + rp.getLimit();
			List<DailySalesAnalysis> res = null;
			if (rp.getYear() == 2016) {
				res = thisYearTrafficAnalysisDAO.gettraffic(rp);
			} else if (rp.getYear() == 2015) {
				res = thisYearTrafficAnalysisDAO.gettraffic2(rp);
			}
			String outStr = JSON.toJSONString(res);
			putIntoMemcache(key, outStr);
		}

		/**
		 * 销售TOP
		 * 
		 * @throws Exception
		 */
		int salesTop() throws Exception {
			String key = "SalesTop";
			List<SaleTopAnalysis> resList = saleTopAnalysisdao.selectAllSaleTopData();
			String outStr = JSON.toJSONString(resList);
			putIntoMemcache(key, outStr);

			int num = resList.size();
			putIntoMemcache(key + "_num", JSON.toJSONString(num));
			return num;
		}

		/**
		 * 销售TOP（分页）
		 * 
		 * @param pp
		 * @throws Exception
		 */
		void salesTop(PaginationParams pp) throws Exception {
			String key = "SalesTop_" + pp.getOffset() + "_" + pp.getLimit();
			List<SaleTopAnalysis> res = saleTopAnalysisdao.selectAllSaleTopData2(pp);
			String outStr = JSON.toJSONString(res);
			putIntoMemcache(key, outStr);
		}

		/**
		 * 有效用户数量
		 * 
		 * @return
		 * @throws Exception
		 */
		int UserValid() throws Exception {
			String key = "UserValid_num";
			int resDat = userDao.getNumOfUserAnalysisValid();
			putIntoMemcache(key, String.valueOf(resDat));
			return resDat;
		}

		/**
		 * 有效用户（分页）
		 * 
		 * @param st
		 * @param offset
		 * @throws Exception
		 */
		void UserValid(int offset, int count) throws Exception {
			String key = "UserValid_" + offset + "_" + count;
			List<UserAnalysis> resList = userDao.getUserAnalysisWithExpenseRecordPage(offset, count);
			String outStr = JSON.toJSONString(resList);
			putIntoMemcache(key, outStr);
		}

		/**
		 * 无效用户数量
		 * 
		 * @return
		 * @throws Exception
		 */
		int UserInvalid() throws Exception {
			String key = "UserInvalid_num";
			int resDat = userDao.getNumOfUserAnalysisInvalid();
			putIntoMemcache(key, String.valueOf(resDat));
			return resDat;
		}

		/**
		 * 无效用户（分页）
		 * 
		 * @param offset
		 * @param count
		 * @throws Exception
		 */
		void UserInvalid(int offset, int count) throws Exception {
			String key = "UserInvalid_" + offset + "_" + count;
			List<UserAnalysis> resList = userDao.getUserAnalysisNoExpenseRecordPage(offset, count);
			String outStr = JSON.toJSONString(resList);
			putIntoMemcache(key, outStr);
		}

		/**
		 * 插入Memcache缓存
		 * 
		 * @param key
		 * @param json
		 */
		void putIntoMemcache(String key, String json) {
			MemcacheUtil memcache = null;
			try {
				memcache = MemcacheUtil.getInstance();
				memcache.setDat(key, json);
				logger.error("insert " + key + " >>>> \n");
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("insert " + key + " error! >>>> \n" + e.getMessage());
			} finally {
				if (memcache != null)
					try {
						memcache.destory();
					} catch (IOException e) {
						e.printStackTrace();
						logger.error("insert " + key + " memcache error occurred error! >>>> \n" + e.getMessage());
					}
			}
		}

	}
}
