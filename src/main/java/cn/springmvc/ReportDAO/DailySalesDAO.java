package cn.springmvc.ReportDAO;

import java.util.List;

import cn.springmvc.model.DailySalesAnalysis;

public interface DailySalesDAO {

	// 列出所有销售数据（16年之前）
	public List<DailySalesAnalysis> selectAllSalesData();
	
	// 所有日常销售数据--16年
	public List<DailySalesAnalysis> selectAllSalesData2016();
}
