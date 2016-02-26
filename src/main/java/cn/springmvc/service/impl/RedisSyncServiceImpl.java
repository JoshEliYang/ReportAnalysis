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
import cn.springmvc.ReportDAO.UserAnalysisDao;
import cn.springmvc.model.DailySalesAnalysis;
import cn.springmvc.model.LastYearTrafficAnalysis;
import cn.springmvc.model.SaleTopAnalysis;
import cn.springmvc.model.ThisYearTrafficAnalysis;
import cn.springmvc.model.UserAnalysis;
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
	@Autowired
	public UserAnalysisDao userDao;

	// 调用方法ID
	private String id;

	// 方法参数
	private String parameter;
	private int[] parameterInt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public int[] getParameterInt() {
		return parameterInt;
	}

	public void setParameterInt(int[] parameterInt) {
		this.parameterInt = parameterInt;
	}

	public void run() {
		if ("AllTrafficAnalysisData".equals(this.getId())) {
			selectAllTrafficAnalysisData();
		}

		if ("AllSalesData".equals(this.getId())) {
			selectSalesData();
		}

		if ("AllSaleTopData".equals(this.getId())) {
			selectAllSaleTopData();
		}

		if ("ThisYearAllTrafficAnalysis".equals(this.getId())) {
			selectThisYearAllTrafficAnalysisData();
		}

		if ("UserValid".equals(this.getId())) {
			getUserValid();
		}

		if ("UserInvalid".equals(this.getId())) {
			getUserInvalid();
		}

		if ("UserValidNum".equals(this.getId())) {
			getUserValidNum();
		}

		if ("UserInvalidNum".equals(this.getId())) {
			getUserInvalidNum();
		}
	}

	public void selectAllTrafficAnalysisData() {
		List<LastYearTrafficAnalysis> resList = trafficAnalysisDao.selectAllTrafficAnalysis();
		String outStr = JSON.toJSONString(resList);
		RedisUtil redis = RedisUtil.getRedis();
		redis.setdat("AllTrafficAnalysisData", outStr);
		redis.destroy();
	}

	public void selectSalesData() {
		List<DailySalesAnalysis> resList = dao.selectAllSalesData();
		String outStr = JSON.toJSONString(resList);
		RedisUtil redis = RedisUtil.getRedis();
		redis.setdat("AllSalesData", outStr);
		redis.destroy();
	}

	public void selectAllSaleTopData() {
		List<SaleTopAnalysis> resList = saleTopAnalysisdao.selectAllSaleTopData();
		String outStr = JSON.toJSONString(resList);
		RedisUtil redis = RedisUtil.getRedis();
		redis.setdat("AllSaleTopData", outStr);
		redis.destroy();
	}

	public void selectThisYearAllTrafficAnalysisData() {
		List<ThisYearTrafficAnalysis> resList = thisYearTrafficAnalysisDAO.selectAllTrafficAnalysis();
		String outStr = JSON.toJSONString(resList);
		RedisUtil redis = RedisUtil.getRedis();
		redis.setdat("ThisYearAllTrafficAnalysis", outStr);
		redis.destroy();
	}

	public void getUserValid() {
		String Key = this.getParameter();
		int st = this.getParameterInt()[0];
		int ed = this.getParameterInt()[1];

		List<UserAnalysis> resList = userDao.getUserAnalysisWithExpenseRecordPage(st, ed);
		String outStr = JSON.toJSONString(resList);
		RedisUtil redis = RedisUtil.getRedis();
		redis.setdat(Key, outStr);
		redis.destroy();
	}

	public void getUserInvalid() {
		String Key = this.getParameter();
		int st = this.getParameterInt()[0];
		int ed = this.getParameterInt()[1];

		List<UserAnalysis> resList = userDao.getUserAnalysisNoExpenseRecordPage(st, ed);
		String outStr = JSON.toJSONString(resList);
		RedisUtil redis = RedisUtil.getRedis();
		redis.setdat(Key, outStr);
		redis.destroy();
	}

	public void getUserValidNum() {
		String Key = this.getParameter();

		int resDat = userDao.getNumOfUserAnalysisValid();
		RedisUtil redis = RedisUtil.getRedis();
		redis.setdat(Key, String.valueOf(resDat));
		redis.destroy();
	}

	public void getUserInvalidNum() {
		String Key = this.getParameter();

		int resDat = userDao.getNumOfUserAnalysisInvalid();
		RedisUtil redis = RedisUtil.getRedis();
		redis.setdat(Key, String.valueOf(resDat));
		redis.destroy();
	}
}
