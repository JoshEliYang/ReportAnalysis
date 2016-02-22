package cn.springmvc.service.impl;

import java.util.List;
import java.util.concurrent.ExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.springmvc.utils.RedisUtil;
import com.springmvc.utils.ThreadPoolUtil;

import cn.springmvc.ReportDAO.DailySalesDAO;
import cn.springmvc.model.DailySalesAnalysis;
import cn.springmvc.service.SalesDataService;
import cn.springmvc.service.redisSyncService;

@Service
public class SalesDataServiceImpl implements SalesDataService {

	@Autowired
	public DailySalesDAO dao;
	
	@Autowired
	public redisSyncService redisSync;

	public List<DailySalesAnalysis> selectSalesData() throws Exception {
		/**
		 * 先从redis中找
		 */
		RedisUtil redis = RedisUtil.getRedis();
		String res = redis.getdat("AllSalesData");
		List<DailySalesAnalysis> resList=null;
		if(res!=null){
			//从redis中取数据
			resList = JSON.parseArray(res,DailySalesAnalysis.class);
			
			//redis和DB同步
			redisSync.setId("AllSalesData");
//			new Thread(redisSync).start();
			Thread redisThread=new Thread(redisSync);
			ExecutorService redisPool=ThreadPoolUtil.getPool("redisPool");
			redisPool.execute(redisThread);
//			ExecutorService redisPool = Executors.newCachedThreadPool();
//			redisPool.execute(redisThread);
			
			return resList;
		}
		
		/**
		 * redis找不到
		 */
		resList=dao.selectAllSalesData();
		String outStr=JSON.toJSONString(resList);
		redis.setdat("AllSalesData", outStr);
		return resList;
	}

}
