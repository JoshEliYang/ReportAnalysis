package cn.springmvc.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.springmvc.utils.MemcacheUtil;

import cn.springmvc.ReportDAO.DailySalesDAO;
import cn.springmvc.model.DailySalesAnalysis;
import cn.springmvc.service.SalesDataService;

/**
 * 
 * @author johsnon
 *
 */
@Service
public class SalesDataServiceImpl implements SalesDataService {

	@Autowired
	public DailySalesDAO dao;

	Logger logger = Logger.getLogger(SalesDataServiceImpl.class);

	public List<DailySalesAnalysis> selectSalesData() {
		MemcacheUtil memcache = null;
		List<DailySalesAnalysis> resList = null;

		try {
			memcache = MemcacheUtil.getInstance();
			String res = memcache.getDat("AllSalesData", String.class);

			if (res != null) {
				resList = JSON.parseArray(res, DailySalesAnalysis.class);

				memcache.destory();
				return resList;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("get memcache error! (get AllSalesData) >>> " + e.getMessage());
		}

		logger.error("get memcache null! (get AllSalesData)");

		try {
			resList = dao.selectAllSalesData();
			String outStr = JSON.toJSONString(resList);

			memcache.setDat("AllSalesData", outStr);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("insert memcache error! (set AllSalesData) >>> " + e.getMessage());
		} finally {
			try {
				memcache.destory();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("memcache close error! (set AllSalesData) >>> " + e.getMessage());
			}
		}

		return resList;
	}

	// 所有日常销售数据--16年
	public List<DailySalesAnalysis> selectAllSalesData2016() {
		MemcacheUtil memcache = null;
		List<DailySalesAnalysis> resList = null;
		try {
			memcache = MemcacheUtil.getInstance();
			String res = memcache.getDat("AllSalesData2016", String.class);

			if (res != null) {
				resList = JSON.parseArray(res, DailySalesAnalysis.class);

				memcache.destory();
				return resList;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("get memcache error! (get AllSalesData2016) >>> " + e.getMessage());
		}

		logger.error("get memcache null! (get AllSalesData2016)");

		try {
			resList = dao.selectAllSalesData2016();
			String outStr = JSON.toJSONString(resList);

			memcache.setDat("AllSalesData2016", outStr);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("insert memcache error! (set AllSalesData2016) >>> " + e.getMessage());
		} finally {
			try {
				memcache.destory();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("memcache close error! (set AllSalesData2016) >>> " + e.getMessage());
			}
		}

		return resList;
	}

}
