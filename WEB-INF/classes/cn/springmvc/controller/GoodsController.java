package cn.springmvc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springmvc.utils.HttpUtils;

import cn.springmvc.model.Goods;
import cn.springmvc.service.GoodsService;

@Scope("prototype")
@Controller
@RequestMapping("/goods")
public class GoodsController {

	@Autowired
	public GoodsService goodsService;

	/**
	 * @author liqiang
	 * @description 获得所有商品信息
	 * @date 2015-1-8
	 * @return JSON
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public Map<String, Object> selectAllGoods() {
		List<Goods> list = null;
		
		try {
			list = goodsService.selectAllGoods();
		} catch (Exception e) {
			e.printStackTrace();
			return HttpUtils.generateResponse("1", "请求失败", null);
		}
		
		return HttpUtils.generateResponse("0", "请求成功", list);
	}
	
	/**
	 * @author liqiang
	 * @description 获得单个商品信息
	 * @date 2015-1-8
	 * @return JSON
	 */
	@ResponseBody
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public Map<String,Object> selectGoodsById(@PathVariable int id){
		Goods good = null;
		
		try {
			good = goodsService.selectGoodsById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return HttpUtils.generateResponse("1", "查询失败", null);
		}
		
		return HttpUtils.generateResponse("0", "查询成功", good);
	}
	 
	/**
	 * @author liqiang
	 * @description 获得单个商品信息
	 * @date 2015-1-8
	 * @return JSON
	 */
	@ResponseBody
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public Map<String, Object> deleteGoodsById(@PathVariable int id) {
		return null;
	}
	
}
