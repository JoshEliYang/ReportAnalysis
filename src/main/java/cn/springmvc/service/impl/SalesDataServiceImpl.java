package cn.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.springmvc.ReportDAO.DailySalesDAO;
import cn.springmvc.model.DailySalesAnalysis;
import cn.springmvc.service.SalesDataService;

@Service
public class SalesDataServiceImpl implements SalesDataService {

	@Autowired
	public DailySalesDAO dao;

	public List<DailySalesAnalysis> selectSalesData() throws Exception {
		return dao.selectAllSalesData();
	}

}
