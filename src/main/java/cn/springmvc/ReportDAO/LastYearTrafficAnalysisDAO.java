package cn.springmvc.ReportDAO;

import java.util.List;

import cn.springmvc.model.DailyReportParams;
import cn.springmvc.model.DailySalesAnalysis;
import cn.springmvc.model.LastYearTrafficAnalysis;
import cn.springmvc.model.ThisYearTrafficAnalysis;

public interface LastYearTrafficAnalysisDAO {

	// 列出15年的流量数据
	public List<ThisYearTrafficAnalysis> selectAllTrafficAnalysis() throws Exception;

	public List<DailySalesAnalysis> gettraffic(DailyReportParams rp);

	public String getCount();
}
