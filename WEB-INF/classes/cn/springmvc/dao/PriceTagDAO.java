package cn.springmvc.dao;

import java.util.List;

import cn.springmvc.model.PriceTag;

public interface PriceTagDAO {

	// 添加价签
	public int insertPriceTag(PriceTag pt);
	
	// 获得所有价签
	public List<PriceTag> selectAllPriceTags();
	
	// 删除价签
	public int deletePriceTag(String id);
	
	//更新价签
	public int updatePriceTag(PriceTag pt);
	
	// 查询价签
	public List<PriceTag> selectPriceTagsByParams(PriceTag pt);
	
	// 获得单个价签
	public PriceTag selectPriceTagsById(String id);
}
