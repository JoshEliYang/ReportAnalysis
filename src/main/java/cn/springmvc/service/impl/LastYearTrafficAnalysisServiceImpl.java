package cn.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.springmvc.utils.RedisUtil;

import cn.springmvc.ReportDAO.LastYearTrafficAnalysisDAO;
import cn.springmvc.model.LastYearTrafficAnalysis;
import cn.springmvc.service.LastYearTrafficAnalysisService;

@Service
public class LastYearTrafficAnalysisServiceImpl implements LastYearTrafficAnalysisService {
	@Autowired
	public LastYearTrafficAnalysisDAO trafficAnalysisDao;

	public List<LastYearTrafficAnalysis> selectAllTrafficAnalysisData() throws Exception {
		/**
		 * 先从redis中找
		 */
		RedisUtil redis = RedisUtil.getRedis();
		String res = redis.getdat("AllTrafficAnalysisData");
		List<LastYearTrafficAnalysis> resList = null;
		if (res != null) {
			// 从redis中取数据
			resList = JSON.parseArray(res, LastYearTrafficAnalysis.class);

			redis.destroy();
			return resList;
		}

		/**
		 * redis找不到
		 */
		resList = trafficAnalysisDao.selectAllTrafficAnalysis();
		String outStr = JSON.toJSONString(resList);
		redis.setdat("AllTrafficAnalysisData", outStr);

		redis.destroy();
		return resList;
	}

}
