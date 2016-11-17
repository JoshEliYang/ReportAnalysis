package cn.springmvc.service;

import java.util.List;

import cn.springmvc.model.PaginationParams;
import cn.springmvc.model.SaleTopAnalysis;

public interface SaleTopAnalysisService {

	// 列出15年销售Top数据
	public List<SaleTopAnalysis> selectAllSaleTopData();

	public List<SaleTopAnalysis> selectAllSaleTopData2(PaginationParams rp) throws Exception;
	
	public String selectAllSaleTopCount();
}
