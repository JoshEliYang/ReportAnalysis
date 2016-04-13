package cn.springmvc.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.springmvc.utils.MemcacheUtil;

import cn.springmvc.ReportDAO.ThisYearTrafficAnalysisDAO;
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

	public List<ThisYearTrafficAnalysis> selectAllTrafficAnalysisData() {
		/**
		 * 先从redis中找
		 */
		// RedisUtil redis = RedisUtil.getRedis();
		// String res = redis.getdat("ThisYearAllTrafficAnalysis");

		MemcacheUtil memcache = null;
		List<ThisYearTrafficAnalysis> resList = null;

		try {
			memcache = MemcacheUtil.getInstance();
			String res = memcache.getDat("ThisYearAllTrafficAnalysis", String.class);

			if (res != null) {
				// 从redis中取数据
				resList = JSON.parseArray(res, ThisYearTrafficAnalysis.class);

				// redis.destroy();
				memcache.destory();
				return resList;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("get memcache error! (get ThisYearAllTrafficAnalysis) >>> " + e.getMessage());
		}
		logger.error("get memcache null! (get ThisYearAllTrafficAnalysis)");

		/**
		 * redis找不到
		 */
		// redis.setdat("ThisYearAllTrafficAnalysis", outStr);
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

		// redis.destroy();
		return resList;
	}

}
