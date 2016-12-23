package cn.springmvc.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.springmvc.utils.MemcacheUtil;

import cn.springmvc.ReportDAO.SaleTopAnalysisDAO;
import cn.springmvc.model.PaginationParams;
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

	/**
	 * Abandoned !! Don't request it again!!
	 */
	public List<SaleTopAnalysis> selectAllSaleTopData() {
		MemcacheUtil memcache = null;
		List<SaleTopAnalysis> resList = null;

		try {
			memcache = MemcacheUtil.getInstance();
			String res = memcache.getDat("AllSaleTopData", String.class);

			if (res != null) {
				// 从redis中取数据
				resList = JSON.parseArray(res, SaleTopAnalysis.class);

				memcache.destory();
				return resList;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("get memcache error! (get AllSaleTopData) >>> " + e.getMessage());
		}

		logger.error("get memcache null! (get AllSaleTopData)");

		try {
			resList = saleTopAnalysisdao.selectAllSaleTopData();
			String outStr = JSON.toJSONString(resList);

			memcache.setDat("AllSaleTopData", outStr);
			logger.error("memcache insert dat >>> " + outStr);
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

		return resList;
	}

	public List<SaleTopAnalysis> selectAllSaleTopData2(PaginationParams rp) throws Exception {
		String key = "SalesTop_" + rp.getOffset() + "_" + rp.getLimit();
		List<SaleTopAnalysis> res = null;
		MemcacheUtil memcache = MemcacheUtil.getInstance();
		String jsonStr = memcache.getDat(key, String.class);

		if (jsonStr != null) {
			res = JSON.parseArray(jsonStr, SaleTopAnalysis.class);
			logger.error(key + " get from cache success");
		} else {
			logger.error(key + " get from cache failed");
			res = saleTopAnalysisdao.selectAllSaleTopData2(rp);
			memcache.setDat(key, JSON.toJSONString(res));
		}
		return res;
	}

	public String selectAllSaleTopCount() {
		String key = "SalesTop";
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
				num = Integer.parseInt(saleTopAnalysisdao.selectAllSaleTopCount());
				memcache.setDat(key, JSON.toJSONString(num));
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("error occurred when get cache key >>>> " + key + " error: >>>>" + e.getMessage());
		}
		return String.valueOf(num);
	}

}
