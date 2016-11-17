package cn.springmvc.service;

import java.util.List;

import cn.springmvc.model.DailyReportParams;
import cn.springmvc.model.DailySalesAnalysis;

public interface SalesDataService {

	// 列出16年值钱的销售数据
	public List<DailySalesAnalysis> selectSalesData();

	// 所有日常销售数据--16年
	public List<DailySalesAnalysis> selectAllSalesData2016();

	public List<DailySalesAnalysis> selectAllSalesDataReport(DailyReportParams rp);
	
	public String selectAllSalesDataReportCount();
	
	
	public List<DailySalesAnalysis> selectAllSalesDataReport2015(DailyReportParams rp);
	
	public String selectAllSalesDataReportCount2015();
}
