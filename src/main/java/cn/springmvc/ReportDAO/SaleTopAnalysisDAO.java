package cn.springmvc.ReportDAO;

import java.util.List;

import cn.springmvc.model.PaginationParams;
import cn.springmvc.model.SaleTopAnalysis;

public interface SaleTopAnalysisDAO {

	// 列出15年销售Top数据
	public List<SaleTopAnalysis> selectAllSaleTopData() throws Exception;

	public List<SaleTopAnalysis> selectAllSaleTopData2(PaginationParams rp) throws Exception;

	public String selectAllSaleTopCount();
	
	
}
