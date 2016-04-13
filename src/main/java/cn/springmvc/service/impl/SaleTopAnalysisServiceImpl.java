package cn.springmvc.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.springmvc.utils.MemcacheUtil;

import cn.springmvc.ReportDAO.SaleTopAnalysisDAO;
import cn.springmvc.model.SaleTopAnalysis;
import cn.springmvc.service.SaleTopAnalysisService;

/**
 * 
 * @author johsnon
 *
 */
@Service
public class SaleTopAnalysisServiceImpl implements SaleTopAnalysisService {

	@Autowired
	public SaleTopAnalysisDAO saleTopAnalysisdao;

	Logger logger = Logger.getLogger(SaleTopAnalysisServiceImpl.class);

	public List<SaleTopAnalysis> selectAllSaleTopData() {
		/**
		 * 先从redis中找
		 */
		// RedisUtil redis = RedisUtil.getRedis();
		// String res = redis.getdat("AllSaleTopData");

		MemcacheUtil memcache = null;
		List<SaleTopAnalysis> resList = null;

		try {
			memcache = MemcacheUtil.getInstance();
			String res = memcache.getDat("AllSaleTopData", String.class);

			if (res != null) {
				// 从redis中取数据
				resList = JSON.parseArray(res, SaleTopAnalysis.class);

				// redis.destroy();
				memcache.destory();
				return resList;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("get memcache error! (get AllSaleTopData) >>> " + e.getMessage());
		}

		logger.error("get memcache null! (get AllSaleTopData)");

		/**
		 * redis找不到
		 */
		// redis.setdat("AllSaleTopData", outStr);
		try {
			resList = saleTopAnalysisdao.selectAllSaleTopData();
			String outStr = JSON.toJSONString(resList);

			memcache.setDat("AllSaleTopData", outStr);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("insert memcache error! (set AllSaleTopData) >>> " + e.getMessage());
		} finally {
			try {
				memcache.destory();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("memcache close error! (get AllSaleTopData) >>> " + e.getMessage());
			}
		}

		// redis.destroy();
		return resList;
	}

}
