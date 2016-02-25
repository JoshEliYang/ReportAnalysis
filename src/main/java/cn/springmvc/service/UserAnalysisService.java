package cn.springmvc.service;

import java.util.List;

import cn.springmvc.model.UserAnalysis;

/**
 * 
 * @author johsnon
 *
 */
public interface UserAnalysisService {
	// 获得用户分析报表--有消费记录
	public List<UserAnalysis> getUserAnalysisWithExpenseRecord();

	// 获得用户分析报表--有消费记录_分页
	public List<UserAnalysis> getUserAnalysisWithExpenseRecord(int st, int ed);

	// 获得用户分析报表--无消费记录
	public List<UserAnalysis> getUserAnalysisNoExpenseRecord();

	// 获得用户分析报表--无消费记录_分页
	public List<UserAnalysis> getUserAnalysisNoExpenseRecord(int st, int ed);

	// 获得有效用户数据量
	public int getNumOfUserAnalysisValid();

	// 获得无效用户数量
	public int getNumOfUserAnalysisInvalid();
}
