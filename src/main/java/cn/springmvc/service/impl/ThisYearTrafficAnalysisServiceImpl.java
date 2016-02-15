package cn.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.springmvc.ReportDAO.ThisYearTrafficAnalysisDAO;
import cn.springmvc.model.ThisYearTrafficAnalysis;
import cn.springmvc.service.ThisYearTrafficAnalysisService;

@Service
public class ThisYearTrafficAnalysisServiceImpl implements
		ThisYearTrafficAnalysisService {
	@Autowired
	public ThisYearTrafficAnalysisDAO thisYearTrafficAnalysisDAO;

	public List<ThisYearTrafficAnalysis> selectAllTrafficAnalysisData()
			throws Exception {
		return thisYearTrafficAnalysisDAO.selectAllTrafficAnalysis();
	}

}
