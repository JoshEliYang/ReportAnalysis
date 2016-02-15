package cn.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.springmvc.dao.GoodsDAO;
import cn.springmvc.model.Goods;
import cn.springmvc.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	public GoodsDAO dao;
	
	public List<Goods> selectAllGoods() throws Exception {
		
		return dao.selectAllGoods();
		
	}

	public Goods selectGoodsById(int id) throws Exception {
		
		return dao.selectGoodsById(id);
	}

}
