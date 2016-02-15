package cn.springmvc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springmvc.utils.HttpUtils;

import cn.springmvc.model.PriceTag;
import cn.springmvc.service.PriceTagService;

@Scope("prototype")
@Controller
@RequestMapping("/pricetags")
public class PriceTagController {
	
	@Autowired
	private PriceTagService priceTagService;
	
	/**
	 * @author Josh Yang
	 * @description 获得所有价签
	 * @date 2015-12-28
	 * @return JSON
	 */
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET)
	public Map<String, Object> getAllPriceTags() {
		
		List<PriceTag> tags = null;
		try {
			 tags = priceTagService.selectAllPriceTags();
		} catch (Exception e) {
			e.printStackTrace();
			return HttpUtils.generateResponse("1", "请求失败", null);
		}
		
		return HttpUtils.generateResponse("0", "查询成功", tags);
	}
	
	/**
	 * @author Josh Yang
	 * @description 添加新价签
	 * @date 2015-12-28
	 * @return JSON
	 */
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST)
	public Map<String, Object> addNewPriceTag(@RequestBody PriceTag pt) {
		try {
			int result = priceTagService.insertPriceTag(pt);
			if (result != 0) {
				return HttpUtils.generateResponse("1", "添加失败", null);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return HttpUtils.generateResponse("1", "请求失败", null);
		}
		
		return HttpUtils.generateResponse("0", "添加成功", null);
	}
	
	/**
	 * @author Josh Yang
	 * @description 根据id查询单个价签
	 * @date 2015-12-28
	 * @return JSON
	 */
	@ResponseBody
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public Map<String, Object> getPriceTagInfoById(@PathVariable String id) {
		PriceTag pt = null;
		try {
			pt = priceTagService.selectPriceTagById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return HttpUtils.generateResponse("1", "请求失败", null);
		}
		
		return HttpUtils.generateResponse("0", "查询成功", pt);
	}
	
	/**
	 * @author Josh Yang
	 * @description 根据id删除单个价签
	 * @date 2015-12-28
	 * @return JSON
	 */
	@ResponseBody
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public Map<String, Object> deletePriceTagById(@PathVariable String id) {
		try {
			int result = priceTagService.deletePriceTag(id);
			if (result == -2) {
				return HttpUtils.generateResponse("1", "无法识别此价签", null);
			}
			
			if (result == -1) {
				return HttpUtils.generateResponse("1", "删除失败", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return HttpUtils.generateResponse("1", "请求失败", null);
		}
		
		return HttpUtils.generateResponse("0", "删除成功", null);
	}
	
	/**
	 * @author Josh Yang
	 * @description 根据id更新单个价签
	 * @date 2015-12-28
	 * @return JSON
	 */
	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.PATCH)
	public Map<String, Object> updatePriceTag(@PathVariable String id, @RequestBody PriceTag pt) {
		PriceTag priceTag = null;
		try {
			int result = priceTagService.updatePriceTag(pt);
			if (result == -2) {
				return HttpUtils.generateResponse("1", "无法识别此价签", null);
			}
			
			if (result == -1) {
				return HttpUtils.generateResponse("1", "更新失败", null);
			}
			
			priceTag = priceTagService.selectPriceTagById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return HttpUtils.generateResponse("1", "请求失败", null);
		}
		
		return HttpUtils.generateResponse("0", "更新成功", priceTag);
	}
}
