package cn.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.springmvc.utils.RedisUtil;

import cn.springmvc.ReportDAO.UserAnalysisDao;
import cn.springmvc.model.UserAnalysis;
import cn.springmvc.service.UserAnalysisService;

/**
 * 
 * @author johsnon
 *
 */
@Service
public class UserAnalysisServiceImpl implements UserAnalysisService {
	@Autowired
	public UserAnalysisDao userDao;

	/**
	 * 获得用户分析报表--有消费记录 获取全部数据--不使用
	 */
	public List<UserAnalysis> getUserAnalysisWithExpenseRecord() {
		String Key = "UerAnalysisWithExpenseRecord";
		RedisUtil redis = RedisUtil.getRedis();
		String res = redis.getdat(Key);
		List<UserAnalysis> resList = null;
		if (res != null) {
			resList = JSON.parseArray(res, UserAnalysis.class);

			redis.destroy();
			return resList;
		}
		resList = userDao.getUerAnalysisWithExpenseRecord();
		String outStr = JSON.toJSONString(resList);
		redis.setdat(Key, outStr);

		redis.destroy();
		return resList;
	}

	/**
	 * 获得用户分析报表--有消费记录_分页 常用
	 */
	public List<UserAnalysis> getUserAnalysisWithExpenseRecord(int st, int ed) {
		// 拼接Redis Key
		String Key = "UserValidFrom" + st + "To" + ed;

		// 从redis中查找
		RedisUtil redis = RedisUtil.getRedis();
		String res = redis.getdat(Key);
		List<UserAnalysis> resList = null;
		if (res != null) {
			// 从redis中取数据
			resList = JSON.parseArray(res, UserAnalysis.class);

			// //redis和DB同步--新线程
			// int[] parameterInt=new int[2];
			// parameterInt[0]=st;
			// parameterInt[1]=ed;
			// redisSync.setId("UserValid");
			// redisSync.setParameter(Key);
			// redisSync.setParameterInt(parameterInt);
			// Thread redisThread=new Thread(redisSync);
			// ExecutorService redisPool=ThreadPoolUtil.getPool("redisPool");
			// redisPool.execute(redisThread);

			redis.destroy();
			return resList;
		}

		resList = userDao.getUserAnalysisWithExpenseRecordPage(st, ed);
		String outStr = JSON.toJSONString(resList);
		redis.setdat(Key, outStr);

		redis.destroy();
		return resList;
	}

	/**
	 * 获得用户分析报表--无消费记录 获取全部数据--不使用
	 */
	public List<UserAnalysis> getUserAnalysisNoExpenseRecord() {
		String Key = "UserAnalysisNoExpenseRecord";

		RedisUtil redis = RedisUtil.getRedis();
		String res = redis.getdat(Key);
		List<UserAnalysis> resList = null;
		if (res != null) {
			resList = JSON.parseArray(res, UserAnalysis.class);

			redis.destroy();
			return resList;
		}

		resList = userDao.getUserAnalysisNoExpenseRecord();
		String outStr = JSON.toJSONString(resList);
		redis.setdat(Key, outStr);

		redis.destroy();
		return resList;
	}

	/**
	 * 获得用户分析报表--无消费记录_分页 常用
	 */
	public List<UserAnalysis> getUserAnalysisNoExpenseRecord(int st, int ed) {
		// 拼接Redis Key
		String Key = "UserInvalidFrom" + st + "To" + ed;

		// 从redis中查找
		RedisUtil redis = RedisUtil.getRedis();
		String res = redis.getdat(Key);
		List<UserAnalysis> resList = null;
		if (res != null) {
			// 从redis中取数据
			resList = JSON.parseArray(res, UserAnalysis.class);

			// redis和DB同步--新线程
			// int[] parameterInt=new int[2];
			// parameterInt[0]=st;
			// parameterInt[1]=ed;
			// redisSync.setId("UserInvalid");
			// redisSync.setParameter(Key);
			// redisSync.setParameterInt(parameterInt);
			// Thread redisThread=new Thread(redisSync);
			// ExecutorService redisPool=ThreadPoolUtil.getPool("redisPool");
			// redisPool.execute(redisThread);

			redis.destroy();
			return resList;
		}

		resList = userDao.getUserAnalysisNoExpenseRecordPage(st, ed);
		String outStr = JSON.toJSONString(resList);
		redis.setdat(Key, outStr);

		redis.destroy();
		return resList;
	}

	// 获得有效用户数据量
	public int getNumOfUserAnalysisValid() {
		// 拼接Redis Key
		String Key = "UserValidNum";

		// 从redis中查找
		RedisUtil redis = RedisUtil.getRedis();
		String res = redis.getdat(Key);
		int resDat = 0;
		if (res != null) {
			// 从redis中取数据
			resDat = Integer.parseInt(res);

			// //redis和DB同步--新线程
			// redisSync.setId(Key);
			// redisSync.setParameter(Key);
			// Thread redisThread=new Thread(redisSync);
			// ExecutorService redisPool=ThreadPoolUtil.getPool("redisPool");
			// redisPool.execute(redisThread);

			redis.destroy();
			return resDat;
		}

		resDat = userDao.getNumOfUserAnalysisValid();
		redis.setdat(Key, String.valueOf(resDat));

		redis.destroy();
		return resDat;
	}

	// 获得无效用户数量
	public int getNumOfUserAnalysisInvalid() {
		// 拼接Redis Key
		String Key = "UserInvalidNum";

		// 从redis中查找
		RedisUtil redis = RedisUtil.getRedis();
		String res = redis.getdat(Key);
		int resDat = 0;
		if (res != null) {
			// 从redis中取数据
			resDat = Integer.parseInt(res);

			// redis和DB同步--新线程
			// redisSync.setId(Key);
			// redisSync.setParameter(Key);
			// Thread redisThread=new Thread(redisSync);
			// ExecutorService redisPool=ThreadPoolUtil.getPool("redisPool");
			// redisPool.execute(redisThread);

			redis.destroy();
			return resDat;
		}

		resDat = userDao.getNumOfUserAnalysisInvalid();
		redis.setdat(Key, String.valueOf(resDat));

		redis.destroy();
		return resDat;
	}
}
