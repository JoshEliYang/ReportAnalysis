package cn.springmvc.ReportDAO;

import java.util.List;

import cn.springmvc.model.ThisYearTrafficAnalysis;

public interface ThisYearTrafficAnalysisDAO {

	// 列出16年的流量数据
	public List<ThisYearTrafficAnalysis> selectAllTrafficAnalysis();
}
