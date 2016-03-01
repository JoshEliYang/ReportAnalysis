package cn.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.springmvc.utils.RedisUtil;

import cn.springmvc.ReportDAO.ThisYearTrafficAnalysisDAO;
import cn.springmvc.model.ThisYearTrafficAnalysis;
import cn.springmvc.service.ThisYearTrafficAnalysisService;

@Service
public class ThisYearTrafficAnalysisServiceImpl implements ThisYearTrafficAnalysisService {
	@Autowired
	public ThisYearTrafficAnalysisDAO thisYearTrafficAnalysisDAO;

	public List<ThisYearTrafficAnalysis> selectAllTrafficAnalysisData() throws Exception {
		/**
		 * 先从redis中找
		 */
		RedisUtil redis = RedisUtil.getRedis();
		String res = redis.getdat("ThisYearAllTrafficAnalysis");
		List<ThisYearTrafficAnalysis> resList = null;
		if (res != null) {
			// 从redis中取数据
			resList = JSON.parseArray(res, ThisYearTrafficAnalysis.class);

			redis.destroy();
			return resList;
		}

		/**
		 * redis找不到
		 */
		resList = thisYearTrafficAnalysisDAO.selectAllTrafficAnalysis();
		String outStr = JSON.toJSONString(resList);
		redis.setdat("ThisYearAllTrafficAnalysis", outStr);

		redis.destroy();
		return resList;
	}

}
