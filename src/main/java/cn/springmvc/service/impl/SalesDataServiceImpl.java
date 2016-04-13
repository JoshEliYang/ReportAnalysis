package cn.springmvc.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.springmvc.utils.MemcacheUtil;

import cn.springmvc.ReportDAO.DailySalesDAO;
import cn.springmvc.model.DailySalesAnalysis;
import cn.springmvc.service.SalesDataService;

@Service
public class SalesDataServiceImpl implements SalesDataService {

	@Autowired
	public DailySalesDAO dao;
	
	Logger logger=Logger.getLogger(SalesDataServiceImpl.class);

	public List<DailySalesAnalysis> selectSalesData() throws Exception {
		/**
		 * 先从redis中找
		 */
		// RedisUtil redis = RedisUtil.getRedis();
		// String res = redis.getdat("AllSalesData");

		MemcacheUtil memcache = MemcacheUtil.getInstance();
		String res = memcache.getDat("AllSalesData", String.class);

		List<DailySalesAnalysis> resList = null;
		if (res != null) {
			// 从redis中取数据
			resList = JSON.parseArray(res, DailySalesAnalysis.class);

			// redis.destroy();
			memcache.destory();
			return resList;
		}
		logger.error("get memcache null! (get AllSalesData)");

		/**
		 * redis找不到
		 */
		resList = dao.selectAllSalesData();
		String outStr = JSON.toJSONString(resList);
		// redis.setdat("AllSalesData", outStr);
		memcache.setDat("AllSalesData", outStr);

		// redis.destroy();
		memcache.destory();
		return resList;
	}

	// 所有日常销售数据--16年
	public List<DailySalesAnalysis> selectAllSalesData2016() throws Exception {
		/**
		 * 先从redis中找
		 */
		// RedisUtil redis = RedisUtil.getRedis();
		// String res = redis.getdat("AllSalesData2016");

		MemcacheUtil memcache = MemcacheUtil.getInstance();
		String res = memcache.getDat("AllSalesData2016", String.class);

		List<DailySalesAnalysis> resList = null;
		if (res != null) {
			// 从redis中取数据
			resList = JSON.parseArray(res, DailySalesAnalysis.class);

			// redis.destroy();
			memcache.destory();
			return resList;
		}
		logger.error("get memcache null! (get AllSalesData2016)");

		/**
		 * redis找不到
		 */
		resList = dao.selectAllSalesData2016();
		String outStr = JSON.toJSONString(resList);
		// redis.setdat("AllSalesData2016", outStr);
		memcache.setDat("AllSalesData2016", outStr);

		// redis.destroy();
		memcache.destory();
		return resList;
	}

}
