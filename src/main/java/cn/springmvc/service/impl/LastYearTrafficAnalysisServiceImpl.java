package cn.springmvc.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.springmvc.utils.MemcacheUtil;

import cn.springmvc.ReportDAO.LastYearTrafficAnalysisDAO;
import cn.springmvc.model.LastYearTrafficAnalysis;
import cn.springmvc.service.LastYearTrafficAnalysisService;

@Service
public class LastYearTrafficAnalysisServiceImpl implements LastYearTrafficAnalysisService {
	@Autowired
	public LastYearTrafficAnalysisDAO trafficAnalysisDao;

	Logger logger=Logger.getLogger(LastYearTrafficAnalysisServiceImpl.class);
	
	public List<LastYearTrafficAnalysis> selectAllTrafficAnalysisData() throws Exception {
		/**
		 * 先从redis中找
		 */
		// RedisUtil redis = RedisUtil.getRedis();
		// String res = redis.getdat("AllTrafficAnalysisData");

		MemcacheUtil memcache = MemcacheUtil.getInstance();
		String res = memcache.getDat("AllTrafficAnalysisData", String.class);

		List<LastYearTrafficAnalysis> resList = null;
		if (res != null) {
			// 从redis中取数据
			resList = JSON.parseArray(res, LastYearTrafficAnalysis.class);

			// redis.destroy();
			memcache.destory();
			return resList;
		}
		logger.error("get memcache null! (get AllTrafficAnalysisData)");

		/**
		 * redis找不到
		 */
		resList = trafficAnalysisDao.selectAllTrafficAnalysis();
		String outStr = JSON.toJSONString(resList);
//		redis.setdat("AllTrafficAnalysisData", outStr);
		memcache.setDat("AllTrafficAnalysisData", outStr);

//		redis.destroy();
		memcache.destory();
		return resList;
	}

}
