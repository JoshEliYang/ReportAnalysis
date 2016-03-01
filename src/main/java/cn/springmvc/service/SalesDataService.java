package cn.springmvc.service;

import java.util.List;

import cn.springmvc.model.DailySalesAnalysis;

public interface SalesDataService {

	// 列出16年值钱的销售数据
	public List<DailySalesAnalysis> selectSalesData() throws Exception;

	// 所有日常销售数据--16年
	public List<DailySalesAnalysis> selectAllSalesData2016() throws Exception;
}
