package cn.springmvc.service.impl;

import java.util.List;
import java.util.concurrent.ExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.springmvc.utils.RedisUtil;
import com.springmvc.utils.ThreadPoolUtil;

import cn.springmvc.ReportDAO.SaleTopAnalysisDAO;
import cn.springmvc.model.SaleTopAnalysis;
import cn.springmvc.service.SaleTopAnalysisService;
import cn.springmvc.service.redisSyncService;

@Service
public class SaleTopAnalysisServiceImpl implements SaleTopAnalysisService {

	@Autowired
	public SaleTopAnalysisDAO saleTopAnalysisdao;
	
	@Autowired
	public redisSyncService redisSync;

	public List<SaleTopAnalysis> selectAllSaleTopData() throws Exception {
		/**
		 * 先从redis中找
		 */
		RedisUtil redis = RedisUtil.getRedis();
		String res = redis.getdat("AllSaleTopData");
		List<SaleTopAnalysis> resList=null;
		if(res!=null){
			//从redis中取数据
			resList = JSON.parseArray(res,SaleTopAnalysis.class);
			
			//redis和DB同步
			redisSync.setId("AllSaleTopData");
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
		resList=saleTopAnalysisdao.selectAllSaleTopData();
		String outStr=JSON.toJSONString(resList);
		redis.setdat("AllSaleTopData", outStr);
		return resList;
	}

}
