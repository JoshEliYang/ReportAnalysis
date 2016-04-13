package cn.springmvc.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.springmvc.utils.MemcacheUtil;

import cn.springmvc.ReportDAO.SaleTopAnalysisDAO;
import cn.springmvc.model.SaleTopAnalysis;
import cn.springmvc.service.SaleTopAnalysisService;

@Service
public class SaleTopAnalysisServiceImpl implements SaleTopAnalysisService {

	@Autowired
	public SaleTopAnalysisDAO saleTopAnalysisdao;

	Logger logger = Logger.getLogger(SaleTopAnalysisServiceImpl.class);

	public List<SaleTopAnalysis> selectAllSaleTopData() throws Exception {
		/**
		 * 先从redis中找
		 */
		// RedisUtil redis = RedisUtil.getRedis();
		// String res = redis.getdat("AllSaleTopData");

		MemcacheUtil memcache = MemcacheUtil.getInstance();
		String res = memcache.getDat("AllSaleTopData", String.class);

		List<SaleTopAnalysis> resList = null;
		if (res != null) {
			// 从redis中取数据
			resList = JSON.parseArray(res, SaleTopAnalysis.class);

			// redis.destroy();
			memcache.destory();
			return resList;
		}
		logger.error("get memcache null! (get AllSaleTopData)");

		/**
		 * redis找不到
		 */
		resList = saleTopAnalysisdao.selectAllSaleTopData();
		String outStr = JSON.toJSONString(resList);
		// redis.setdat("AllSaleTopData", outStr);
		memcache.setDat("AllSaleTopData", outStr);

		// redis.destroy();
		memcache.destory();
		return resList;
	}

}
