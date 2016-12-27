package cn.springmvc.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.springmvc.utils.MemcacheUtil;

import cn.springmvc.ReportDAO.LastYearTrafficAnalysisDAO;
import cn.springmvc.model.DailyReportParams;
import cn.springmvc.model.DailySalesAnalysis;
import cn.springmvc.model.LastYearTrafficAnalysis;
import cn.springmvc.model.ThisYearTrafficAnalysis;
import cn.springmvc.service.LastYearTrafficAnalysisService;

/**
 * 
 * @author johsnon
 *
 */
@Service
public class LastYearTrafficAnalysisServiceImpl implements LastYearTrafficAnalysisService {
	@Autowired
	public LastYearTrafficAnalysisDAO trafficAnalysisDao;

	Logger logger = Logger.getLogger(LastYearTrafficAnalysisServiceImpl.class);

	/**
	 * Abandoned !! Don't request it again!!
	 */
	public List<ThisYearTrafficAnalysis> selectAllTrafficAnalysisData() {

		MemcacheUtil memcache = null;
		List<ThisYearTrafficAnalysis> resList = null;

		try {
			memcache = MemcacheUtil.getInstance();
			String res = memcache.getDat("AllTrafficAnalysisData", String.class);
			if (res != null) {
				// 从redis中取数据
				resList = JSON.parseArray(res, ThisYearTrafficAnalysis.class);

				memcache.destory();
				return resList;
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("get memcache error! (get AllTrafficAnalysisData) >>> " + e.getMessage());

			try {
				memcache.destory();
			} catch (IOException e1) {
				e1.printStackTrace();
				logger.error("memcache close error! (get AllTrafficAnalysisData) >>> " + e.getMessage());
			}
		}

		logger.error("get memcache null! (get AllTrafficAnalysisData)");

		/**
		 * redis找不到
		 */
		// redis.setdat("AllTrafficAnalysisData", outStr);
		try {
			resList = trafficAnalysisDao.selectAllTrafficAnalysis();
			String outStr = JSON.toJSONString(resList);

			memcache.setDat("AllTrafficAnalysisData", outStr);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("insert memcache error! (set AllTrafficAnalysisData) >>> " + e.getMessage());
		} finally {
			try {
				memcache.destory();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("memcache close error! (set AllTrafficAnalysisData) >>> " + e.getMessage());
			}
		}

		// redis.destroy();
		return resList;
	}

	public List<DailySalesAnalysis> selecttraffic(DailyReportParams rp) {
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
				res = trafficAnalysisDao.gettraffic(rp);
				memcache.setDat(key, JSON.toJSONString(res));
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("error occurred when get cache key >>>> " + key + " error: >>>>" + e.getMessage());
		}
		return res;
	}

	public String getCount() {
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
				num = Integer.parseInt(trafficAnalysisDao.getCount());
				memcache.setDat(key, JSON.toJSONString(num));
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("error occurred when get cache key >>>> " + key + " error: >>>>" + e.getMessage());
		}
		return String.valueOf(num);
	}

}
