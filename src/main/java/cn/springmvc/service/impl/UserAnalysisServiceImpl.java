package cn.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	// 获得用户分析报表--有消费记录
	public List<UserAnalysis> getUserAnalysisWithExpenseRecord() {
		return userDao.getUerAnalysisWithExpenseRecord();
	}

	// 获得用户分析报表--有消费记录_分页
	public List<UserAnalysis> getUserAnalysisWithExpenseRecord(int st, int ed) {
		return userDao.getUserAnalysisWithExpenseRecordPage(st, ed);
	}

	// 获得用户分析报表--无消费记录
	public List<UserAnalysis> getUserAnalysisNoExpenseRecord() {
		return userDao.getUserAnalysisNoExpenseRecord();
	}

	// 获得用户分析报表--无消费记录_分页
	public List<UserAnalysis> getUserAnalysisNoExpenseRecord(int st, int ed) {
		return userDao.getUserAnalysisNoExpenseRecordPage(st, ed);
	}

	// 获得有效用户数据量
	public int getNumOfUserAnalysisValid() {
		return userDao.getNumOfUserAnalysisValid();
	}

	// 获得无效用户数量
	public int getNumOfUserAnalysisInvalid() {
		return userDao.getNumOfUserAnalysisInvalid();
	}
}
