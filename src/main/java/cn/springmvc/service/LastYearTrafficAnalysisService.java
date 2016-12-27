package cn.springmvc.service;

import java.util.List;

import cn.springmvc.model.LastYearTrafficAnalysis;
import cn.springmvc.model.ThisYearTrafficAnalysis;

public interface LastYearTrafficAnalysisService {

	// 列出15年的流量数据
	public List<ThisYearTrafficAnalysis> selectAllTrafficAnalysisData();
}
