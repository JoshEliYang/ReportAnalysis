package cn.springmvc.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.springmvc.utils.MemcacheUtil;

import cn.springmvc.ReportDAO.DailySalesDAO;
import cn.springmvc.model.DailyReportParams;
import cn.springmvc.model.DailySalesAnalysis;
import cn.springmvc.service.SalesDataService;

/**
 * 
 * @author johsnon
 *
 */
@Service
public class SalesDataServiceImpl implements SalesDataService {

	@Autowired
	public DailySalesDAO dao;

	Logger logger = Logger.getLogger(SalesDataServiceImpl.class);

	public List<DailySalesAnalysis> selectSalesData() {
		MemcacheUtil memcache = null;
		List<DailySalesAnalysis> resList = null;

		try {
			memcache = MemcacheUtil.getInstance();
			String res = memcache.getDat("AllSalesData", String.class);

			if (res != null) {
				resList = JSON.parseArray(res, DailySalesAnalysis.class);

				memcache.destory();
				return resList;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("get memcache error! (get AllSalesData) >>> " + e.getMessage());
		}

		logger.error("get memcache null! (get AllSalesData)");

		try {
			resList = dao.selectAllSalesData();
			String outStr = JSON.toJSONString(resList);

			memcache.setDat("AllSalesData", outStr);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("insert memcache error! (set AllSalesData) >>> " + e.getMessage());
		} finally {
			try {
				memcache.destory();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("memcache close error! (set AllSalesData) >>> " + e.getMessage());
			}
		}

		return resList;
	}

	// 所有日常销售数据--16年
	public List<DailySalesAnalysis> selectAllSalesData2016() {
		MemcacheUtil memcache = null;
		List<DailySalesAnalysis> resList = null;
		try {
			memcache = MemcacheUtil.getInstance();
			String res = memcache.getDat("AllSalesData2016", String.class);

			if (res != null) {
				resList = JSON.parseArray(res, DailySalesAnalysis.class);

				memcache.destory();
				return resList;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("get memcache error! (get AllSalesData2016) >>> " + e.getMessage());
		}

		logger.error("get memcache null! (get AllSalesData2016)");

		try {
			resList = dao.selectAllSalesData2016();
			String outStr = JSON.toJSONString(resList);

			memcache.setDat("AllSalesData2016", outStr);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("insert memcache error! (set AllSalesData2016) >>> " + e.getMessage());
		} finally {
			try {
				memcache.destory();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("memcache close error! (set AllSalesData2016) >>> " + e.getMessage());
			}
		}

		return resList;
	}

	public List<DailySalesAnalysis> selectAllSalesDataReport(DailyReportParams rp) {
		String key = "AllSalesData2016_" + rp.getOffset() + "_" + rp.getLimit();
		MemcacheUtil memcache = null;
		List<DailySalesAnalysis> res = null;
		try {
			memcache = MemcacheUtil.getInstance();
			String jsonStr = memcache.getDat(key, String.class);

			if (jsonStr != null) {
				res = JSON.parseArray(jsonStr, DailySalesAnalysis.class);
				logger.error(key + " get from cache success");
			} else {
				logger.error(key + " get from cache failed");
				res = dao.selectAllSalesDataReport(rp);
				memcache.setDat(key, JSON.toJSONString(res));
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("error occurred when get cache key >>>> " + key + " error: >>>>" + e.getMessage());
		}
		return res;
	}

	public String selectAllSalesDataReportCount() {
		String key = "AllSalesData2016_num";
		MemcacheUtil memcache = null;
		int num = 0;
		try {
			memcache = MemcacheUtil.getInstance();
			String jsonStr = memcache.getDat(key, String.class);

			if (jsonStr != null) {
				num = (Integer) JSON.parse(jsonStr);
				logger.error(key + " get from cache success");
			} else {
				logger.error(key + " get from cache failed");
				num = Integer.parseInt(dao.selectAllSalesDataReportCount());
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("error occurred when get cache key >>>> " + key + " error: >>>>" + e.getMessage());
		}
		return String.valueOf(num);
	}

	public List<DailySalesAnalysis> selectAllSalesDataReport2015(DailyReportParams rp) {
		String key = "AllSalesData2015_" + rp.getOffset() + "_" + rp.getLimit();
		MemcacheUtil memcache = null;
		List<DailySalesAnalysis> res = null;
		try {
			memcache = MemcacheUtil.getInstance();
			String jsonStr = memcache.getDat(key, String.class);

			if (jsonStr != null) {
				res = JSON.parseArray(jsonStr, DailySalesAnalysis.class);
				logger.error(key + " get from cache success");
			} else {
				logger.error(key + " get from cache failed");
				res = dao.selectAllSalesDataReport2015(rp);
				memcache.setDat(key, JSON.toJSONString(res));
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("error occurred when get cache key >>>> " + key + " error: >>>>" + e.getMessage());
		}
		return res;
	}

	public String selectAllSalesDataReportCount2015() {
		String key = "AllSalesData2015_num";
		MemcacheUtil memcache = null;
		int num = 0;
		try {
			memcache = MemcacheUtil.getInstance();
			String jsonStr = memcache.getDat(key, String.class);

			if (jsonStr != null) {
				num = (Integer) JSON.parse(jsonStr);
				logger.error(key + " get from cache success");
			} else {
				logger.error(key + " get from cache failed");
				num = Integer.parseInt(dao.selectAllSalesDataReportCount2015());
				memcache.setDat(key, JSON.toJSONString(num));
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("error occurred when get cache key >>>> " + key + " error: >>>>" + e.getMessage());
		}
		return String.valueOf(num);
	}
}
