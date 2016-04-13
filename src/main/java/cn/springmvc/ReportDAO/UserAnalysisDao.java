package cn.springmvc.ReportDAO;

import java.util.List;

import cn.springmvc.model.UserAnalysis;

/**
 * 
 * @author johsnon
 *
 */
public interface UserAnalysisDao {
	// 获得用户分析报表--有消费记录
	public List<UserAnalysis> getUerAnalysisWithExpenseRecord() throws Exception;

	// 获得用户分析报表--有消费记录_分页
	public List<UserAnalysis> getUserAnalysisWithExpenseRecordPage(int st, int ed) throws Exception;

	// 获得用户分析报表--无消费记录
	public List<UserAnalysis> getUserAnalysisNoExpenseRecord() throws Exception;

	// 获得用户分析报表--无消费记录_分页
	public List<UserAnalysis> getUserAnalysisNoExpenseRecordPage(int st, int ed) throws Exception;

	// 获得有效用户数据量
	public int getNumOfUserAnalysisValid() throws Exception;

	// 获得无效用户数量
	public int getNumOfUserAnalysisInvalid() throws Exception;
}
