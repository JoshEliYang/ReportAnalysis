package cn.springmvc.dao;

import java.util.List;

import cn.springmvc.model.Goods;

public interface GoodsDAO {
	
	public List<Goods> selectAllGoods();
	
	public Goods selectGoodsById(int id);
	
	public void deleteGoodById(int id);
}
