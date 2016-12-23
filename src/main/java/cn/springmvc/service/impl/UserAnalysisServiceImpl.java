package cn.springmvc.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.springmvc.utils.MemcacheUtil;

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

	Logger logger = Logger.getLogger(UserAnalysisServiceImpl.class);

	/**
	 * 获得用户分析报表--有消费记录 获取全部数据--不使用
	 */
	public List<UserAnalysis> getUserAnalysisWithExpenseRecord() {
		String Key = "UerAnalysisWithExpenseRecord";

		MemcacheUtil memcache = null;
		List<UserAnalysis> resList = null;
		try {
			memcache = MemcacheUtil.getInstance();
			String res = memcache.getDat(Key, String.class);

			if (res != null) {
				resList = JSON.parseArray(res, UserAnalysis.class);

				memcache.destory();
				return resList;
			}
			logger.error("get memcache null! (get " + Key + ")");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("get memcache error >>> " + e.getMessage());
		}

		try {
			resList = userDao.getUerAnalysisWithExpenseRecord();
			String outStr = JSON.toJSONString(resList);

			memcache.setDat(Key, outStr);
			memcache.destory();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("insert memcache error >>> " + e.getMessage());
		}
		return resList;
	}

	/**
	 * 获得用户分析报表--有消费记录_分页 常用
	 */
	public List<UserAnalysis> getUserAnalysisWithExpenseRecord(int st, int ed) {
		// 拼接Redis Key
		String Key = "UserValid_" + st + "_" + ed;

		MemcacheUtil memcache = null;
		List<UserAnalysis> resList = null;
		try {
			memcache = MemcacheUtil.getInstance();
			String res = memcache.getDat(Key, String.class);

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
				// ExecutorService
				// redisPool=ThreadPoolUtil.getPool("redisPool");
				// redisPool.execute(redisThread);

				// redis.destroy();
				memcache.destory();
				return resList;
			}
			logger.error("get memcache null! (get " + Key + ")");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("get memcache error >>> " + e.getMessage());
		}

		try {
			resList = userDao.getUserAnalysisWithExpenseRecordPage(st, ed);
			String outStr = JSON.toJSONString(resList);

			memcache.setDat(Key, outStr);
			// redis.destroy();
			memcache.destory();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("insert memcache error >>> " + e.getMessage());
		}
		return resList;
	}

	/**
	 * 获得用户分析报表--无消费记录 获取全部数据--不使用
	 */
	public List<UserAnalysis> getUserAnalysisNoExpenseRecord() {
		String Key = "UserAnalysisNoExpenseRecord";

		MemcacheUtil memcache = null;
		List<UserAnalysis> resList = null;
		try {
			memcache = MemcacheUtil.getInstance();
			String res = memcache.getDat(Key, String.class);

			if (res != null) {
				resList = JSON.parseArray(res, UserAnalysis.class);

				memcache.destory();
				return resList;
			}
			logger.error("get memcache null! (get " + Key + ")");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("get memcache error >>> " + e.getMessage());
		}

		try {
			resList = userDao.getUserAnalysisNoExpenseRecord();
			String outStr = JSON.toJSONString(resList);

			memcache.setDat(Key, outStr);
			memcache.destory();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("insert memcache error >>> " + e.getMessage());
		}
		return resList;
	}

	/**
	 * 获得用户分析报表--无消费记录_分页 常用
	 */
	public List<UserAnalysis> getUserAnalysisNoExpenseRecord(int st, int ed) {
		// 拼接Redis Key
		String Key = "UserInvalid_" + st + "_" + ed;

		MemcacheUtil memcache = null;
		List<UserAnalysis> resList = null;
		try {
			memcache = MemcacheUtil.getInstance();
			String res = memcache.getDat(Key, String.class);

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
				// ExecutorService
				// redisPool=ThreadPoolUtil.getPool("redisPool");
				// redisPool.execute(redisThread);

				// redis.destroy();
				memcache.destory();
				return resList;
			}
			logger.error("get memcache null! (get " + Key + ")");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("get memcache error >>> " + e.getMessage());
		}

		// redis.setdat(Key, outStr);
		try {
			resList = userDao.getUserAnalysisNoExpenseRecordPage(st, ed);
			String outStr = JSON.toJSONString(resList);

			memcache.setDat(Key, outStr);
			// redis.destroy();
			memcache.destory();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("insert memcache error >>> " + e.getMessage());
		}
		return resList;
	}

	// 获得有效用户数据量
	public int getNumOfUserAnalysisValid() {
		// 拼接Redis Key
		String Key = "UserValid_num";

		MemcacheUtil memcache = null;
		int resDat = 0;
		try {
			memcache = MemcacheUtil.getInstance();

			String res = memcache.getDat(Key, String.class);
			if (res != null) {
				// 从redis中取数据
				resDat = Integer.parseInt(res);

				// //redis和DB同步--新线程
				// redisSync.setId(Key);
				// redisSync.setParameter(Key);
				// Thread redisThread=new Thread(redisSync);
				// ExecutorService
				// redisPool=ThreadPoolUtil.getPool("redisPool");
				// redisPool.execute(redisThread);

				// redis.destroy();
				try {
					memcache.destory();
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("memcache close error >>> " + e.getMessage());
				}
				return resDat;
			}
			logger.error("get memcache null! (get " + Key + ")");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("get memcache error >>> " + e.getMessage());
		}

		// redis.setdat(Key, String.valueOf(resDat));
		try {
			resDat = userDao.getNumOfUserAnalysisValid();

			memcache.setDat(Key, String.valueOf(resDat));
			// redis.destroy();
			memcache.destory();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("insert memcache error >>> " + e.getMessage());
		}
		return resDat;
	}

	// 获得无效用户数量
	public int getNumOfUserAnalysisInvalid() {
		// 拼接Redis Key
		String Key = "UserInvalid_num";

		// 从redis中查找
		// RedisUtil redis = RedisUtil.getRedis();
		// String res = redis.getdat(Key);

		MemcacheUtil memcache = null;
		int resDat = 0;
		try {
			memcache = MemcacheUtil.getInstance();
			String res = memcache.getDat(Key, String.class);

			if (res != null) {
				// 从redis中取数据
				resDat = Integer.parseInt(res);

				// redis和DB同步--新线程
				// redisSync.setId(Key);
				// redisSync.setParameter(Key);
				// Thread redisThread=new Thread(redisSync);
				// ExecutorService
				// redisPool=ThreadPoolUtil.getPool("redisPool");
				// redisPool.execute(redisThread);

				// redis.destroy();
				memcache.destory();
				return resDat;
			}
			logger.error("get memcache null! (get " + Key + ")");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("get memcache error >>> " + e.getMessage());
		}

		// redis.setdat(Key, String.valueOf(resDat));
		try {
			resDat = userDao.getNumOfUserAnalysisInvalid();

			memcache.setDat(Key, String.valueOf(resDat));
			// redis.destroy();
			memcache.destory();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("insert memcache error >>> " + e.getMessage());
		}

		return resDat;
	}

	public String getvalidCount() {
		// TODO Auto-generated method stub
		return userDao.getvalidCount();
	}

	public String getinvalidCount() {
		// TODO Auto-generated method stub
		return userDao.getinvalidCount();
	}
}
