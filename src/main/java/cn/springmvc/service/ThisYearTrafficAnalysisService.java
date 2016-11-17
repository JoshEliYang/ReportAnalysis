package cn.springmvc.service;

import java.util.List;

import cn.springmvc.model.DailyReportParams;
import cn.springmvc.model.DailySalesAnalysis;
import cn.springmvc.model.ThisYearTrafficAnalysis;

public interface ThisYearTrafficAnalysisService {

	// 列出16年的流量数据
	public List<ThisYearTrafficAnalysis> selectAllTrafficAnalysisData();

	public List<DailySalesAnalysis> selecttraffic(DailyReportParams rp);

	public String getCount();
	
	public List<DailySalesAnalysis> selecttraffic2(DailyReportParams rp);

	public String getCount2();
}
