package cn.springmvc.service;

import java.util.List;

import cn.springmvc.model.ThisYearTrafficAnalysis;

public interface ThisYearTrafficAnalysisService {

	// 列出16年的流量数据
	public List<ThisYearTrafficAnalysis> selectAllTrafficAnalysisData()
			throws Exception;
}
