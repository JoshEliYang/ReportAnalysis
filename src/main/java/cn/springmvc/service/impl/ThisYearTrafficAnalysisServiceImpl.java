package cn.springmvc.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.springmvc.utils.MemcacheUtil;

import cn.springmvc.ReportDAO.ThisYearTrafficAnalysisDAO;
import cn.springmvc.model.DailyReportParams;
import cn.springmvc.model.DailySalesAnalysis;
import cn.springmvc.model.ThisYearTrafficAnalysis;
import cn.springmvc.service.ThisYearTrafficAnalysisService;

/**
 * 
 * @author johsnon
 *
 */
@Service
public class ThisYearTrafficAnalysisServiceImpl implements ThisYearTrafficAnalysisService {
	@Autowired
	public ThisYearTrafficAnalysisDAO thisYearTrafficAnalysisDAO;

	Logger logger = Logger.getLogger(ThisYearTrafficAnalysisServiceImpl.class);

	/**
	 * Abandoned !! Don't request it again!!
	 */
	public List<ThisYearTrafficAnalysis> selectAllTrafficAnalysisData() {
		MemcacheUtil memcache = null;
		List<ThisYearTrafficAnalysis> resList = null;

		try {
			memcache = MemcacheUtil.getInstance();
			String res = memcache.getDat("ThisYearAllTrafficAnalysis", String.class);

			if (res != null) {
				// 从redis中取数据
				resList = JSON.parseArray(res, ThisYearTrafficAnalysis.class);

				memcache.destory();
				return resList;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("get memcache error! (get ThisYearAllTrafficAnalysis) >>> " + e.getMessage());
		}
		logger.error("get memcache null! (get ThisYearAllTrafficAnalysis)");

		try {
			resList = thisYearTrafficAnalysisDAO.selectAllTrafficAnalysis();
			String outStr = JSON.toJSONString(resList);

			memcache.setDat("ThisYearAllTrafficAnalysis", outStr);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("insert memcache error! (set ThisYearAllTrafficAnalysis) >>> " + e.getMessage());
		} finally {
			try {
				memcache.destory();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("memcache close error! (get ThisYearAllTrafficAnalysis) >>> " + e.getMessage());
			}
		}

		return resList;
	}

	public List<DailySalesAnalysis> selecttraffic(DailyReportParams rp) {
		String key = "TrafficAnalysis2016_" + rp.getOffset() + "_" + rp.getLimit();
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
				res = thisYearTrafficAnalysisDAO.gettraffic(rp);
				memcache.setDat(key, JSON.toJSONString(res));
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("error occurred when get cache key >>>> " + key + " error: >>>>" + e.getMessage());
		}
		return res;
	}

	public String getCount() {
		String key = "TrafficAnalysis2016_num";
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
				num = Integer.parseInt(thisYearTrafficAnalysisDAO.getCount());
				memcache.setDat(key, JSON.toJSONString(num));
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("error occurred when get cache key >>>> " + key + " error: >>>>" + e.getMessage());
		}
		return String.valueOf(num);
	}

	public List<DailySalesAnalysis> selecttraffic2(DailyReportParams rp) {
		String key = "TrafficAnalysis2015_" + rp.getOffset() + "_" + rp.getLimit();
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
				res = thisYearTrafficAnalysisDAO.gettraffic2(rp);
				memcache.setDat(key, res);
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("error occurred when get cache key >>>> " + key + " error: >>>>" + e.getMessage());
		}
		return res;
	}

	public String getCount2() {
		String key = "TrafficAnalysis2015_num";
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
				num = Integer.parseInt(thisYearTrafficAnalysisDAO.getCount2());
				memcache.setDat(key, JSON.toJSONString(num));
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("error occurred when get cache key >>>> " + key + " error: >>>>" + e.getMessage());
		}
		return String.valueOf(num);
	}

}
