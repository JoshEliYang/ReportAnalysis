package cn.springmvc.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.springmvc.utils.MemcacheUtil;

import cn.springmvc.ReportDAO.LastYearTrafficAnalysisDAO;
import cn.springmvc.model.LastYearTrafficAnalysis;
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

	public List<LastYearTrafficAnalysis> selectAllTrafficAnalysisData() {
		/**
		 * 先从redis中找
		 */
		// RedisUtil redis = RedisUtil.getRedis();
		// String res = redis.getdat("AllTrafficAnalysisData");

		MemcacheUtil memcache = null;
		List<LastYearTrafficAnalysis> resList = null;

		try {
			memcache = MemcacheUtil.getInstance();
			String res = memcache.getDat("AllTrafficAnalysisData", String.class);
			if (res != null) {
				// 从redis中取数据
				resList = JSON.parseArray(res, LastYearTrafficAnalysis.class);

				// redis.destroy();
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

}
