package cn.springmvc.service;

import java.util.List;

import cn.springmvc.model.Goods;

public interface GoodsService {
	
	public List<Goods> selectAllGoods() throws Exception;
	
	public Goods selectGoodsById(int id) throws Exception;
}
