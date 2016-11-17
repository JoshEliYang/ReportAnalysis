package cn.springmvc.ReportDAO;

import java.util.List;

import cn.springmvc.model.DailyReportParams;
import cn.springmvc.model.DailySalesAnalysis;

public interface DailySalesDAO {

	// 列出所有销售数据（16年之前）
	public List<DailySalesAnalysis> selectAllSalesData() throws Exception;

	// 所有日常销售数据--16年
	public List<DailySalesAnalysis> selectAllSalesData2016() throws Exception;

	public List<DailySalesAnalysis> selectAllSalesDataReport(DailyReportParams rp) ;
	
	public String selectAllSalesDataReportCount() ;

	public List<DailySalesAnalysis> selectAllSalesDataReport2015(DailyReportParams rp);

	public String selectAllSalesDataReportCount2015();
	
	
}
