package cn.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.springmvc.utils.RedisUtil;

import cn.springmvc.ReportDAO.DailySalesDAO;
import cn.springmvc.model.DailySalesAnalysis;
import cn.springmvc.service.SalesDataService;

@Service
public class SalesDataServiceImpl implements SalesDataService {

	@Autowired
	public DailySalesDAO dao;

	public List<DailySalesAnalysis> selectSalesData() throws Exception {
		/**
		 * 先从redis中找
		 */
		RedisUtil redis = RedisUtil.getRedis();
		String res = redis.getdat("AllSalesData");
		List<DailySalesAnalysis> resList = null;
		if (res != null) {
			// 从redis中取数据
			resList = JSON.parseArray(res, DailySalesAnalysis.class);

			redis.destroy();
			return resList;
		}

		/**
		 * redis找不到
		 */
		resList = dao.selectAllSalesData();
		String outStr = JSON.toJSONString(resList);
		redis.setdat("AllSalesData", outStr);

		redis.destroy();
		return resList;
	}

}
