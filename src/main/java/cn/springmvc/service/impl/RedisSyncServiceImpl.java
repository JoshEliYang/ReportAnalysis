package cn.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.springmvc.utils.RedisUtil;

import cn.springmvc.ReportDAO.DailySalesDAO;
import cn.springmvc.ReportDAO.LastYearTrafficAnalysisDAO;
import cn.springmvc.ReportDAO.SaleTopAnalysisDAO;
import cn.springmvc.ReportDAO.ThisYearTrafficAnalysisDAO;
import cn.springmvc.model.DailySalesAnalysis;
import cn.springmvc.model.LastYearTrafficAnalysis;
import cn.springmvc.model.SaleTopAnalysis;
import cn.springmvc.model.ThisYearTrafficAnalysis;
import cn.springmvc.service.redisSyncService;

@Service
public class RedisSyncServiceImpl implements redisSyncService {
	@Autowired
	public LastYearTrafficAnalysisDAO trafficAnalysisDao;
	@Autowired
	public DailySalesDAO dao;
	@Autowired
	public SaleTopAnalysisDAO saleTopAnalysisdao;
	@Autowired
	public ThisYearTrafficAnalysisDAO thisYearTrafficAnalysisDAO;

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void run() {
		if ("AllTrafficAnalysisData".equals(this.getId())) {
			selectAllTrafficAnalysisData();
		}
		
		if("AllSalesData".equals(this.getId())){
			selectSalesData();
		}
		
		if("AllSaleTopData".equals(this.getId())){
			selectAllSaleTopData();
		}
		
		if("ThisYearAllTrafficAnalysis".equals(this.getId())){
			
		}
	}

	public void selectAllTrafficAnalysisData() {
		List<LastYearTrafficAnalysis> resList = trafficAnalysisDao.selectAllTrafficAnalysis();
		String outStr = JSON.toJSONString(resList);
		RedisUtil redis = RedisUtil.getRedis();
		redis.setdat("AllTrafficAnalysisData", outStr);
	}
	
	public void selectSalesData(){
		List<DailySalesAnalysis> resList=dao.selectAllSalesData();
		String outStr = JSON.toJSONString(resList);
		RedisUtil redis = RedisUtil.getRedis();
		redis.setdat("AllSalesData", outStr);
	}
	
	public void selectAllSaleTopData(){
		List<SaleTopAnalysis> resList=saleTopAnalysisdao.selectAllSaleTopData();
		String outStr = JSON.toJSONString(resList);
		RedisUtil redis = RedisUtil.getRedis();
		redis.setdat("AllSaleTopData", outStr);
	}
	
	public void selectThisYearAllTrafficAnalysisData(){
		List<ThisYearTrafficAnalysis> resList=thisYearTrafficAnalysisDAO.selectAllTrafficAnalysis();
		String outStr = JSON.toJSONString(resList);
		RedisUtil redis = RedisUtil.getRedis();
		redis.setdat("ThisYearAllTrafficAnalysis", outStr);
	}
}
