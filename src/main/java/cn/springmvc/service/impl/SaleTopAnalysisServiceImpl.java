package cn.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.springmvc.ReportDAO.SaleTopAnalysisDAO;
import cn.springmvc.model.SaleTopAnalysis;
import cn.springmvc.service.SaleTopAnalysisService;

@Service
public class SaleTopAnalysisServiceImpl implements SaleTopAnalysisService {

	@Autowired
	public SaleTopAnalysisDAO saleTopAnalysisdao;

	public List<SaleTopAnalysis> selectAllSaleTopData() throws Exception {
		return saleTopAnalysisdao.selectAllSaleTopData();
	}

}
