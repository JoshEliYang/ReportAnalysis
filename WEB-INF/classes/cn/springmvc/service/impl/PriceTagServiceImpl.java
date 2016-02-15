package cn.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.springmvc.dao.PriceTagDAO;
import cn.springmvc.model.PriceTag;
import cn.springmvc.service.PriceTagService;

@Service
public class PriceTagServiceImpl implements PriceTagService {
	
	@Autowired
	private PriceTagDAO priceTagDao;

	// 添加价签
	public int insertPriceTag(PriceTag pt) throws Exception{
		try{
			priceTagDao.insertPriceTag(pt);
		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		}
		return 0;
	}

	public List<PriceTag> selectAllPriceTags() throws Exception{
		return priceTagDao.selectAllPriceTags();
	}

	// 删除价签
	public int deletePriceTag(String id) throws Exception{
		try {
			// 判断价签是否存在
			PriceTag pt = priceTagDao.selectPriceTagsById(id);
			if (pt == null) {
				return -2;
			}
			priceTagDao.deletePriceTag(id);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	// 更新价签 
	public int updatePriceTag(PriceTag pt) throws Exception{
		try {
			//  判断价签是否存在
			int id = pt.getId();
			PriceTag priceTag = priceTagDao.selectPriceTagsById(String.valueOf(id));
			if (priceTag == null) {
				return -2;
			}
			
			priceTagDao.updatePriceTag(pt);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		
		return 0;
	}

	public List<PriceTag> selectPriceTagsByParams(PriceTag pt) throws Exception{
		// TODO Auto-generated method stub
		return null;
	}

	// 查询单个价签
	public PriceTag selectPriceTagById(String id) throws Exception {
		return priceTagDao.selectPriceTagsById(id);
	}

}
