package cn.springmvc.service;

import java.util.List;

import cn.springmvc.model.paymentSerialNo.PaymentSerialNumber;
import cn.springmvc.model.paymentSerialNo.SerialNumberQuery;

/**
 * 
 * @author johnson
 *
 */
public interface PaymentSerialNumberService {

	/**
	 * query payment serial numbers
	 * 
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public List<PaymentSerialNumber> getList(SerialNumberQuery query) throws Exception;

	/**
	 * get counts
	 * 
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public int getCount(SerialNumberQuery query) throws Exception;

	/**
	 * query payment serial numbers in cache
	 * 
	 * if can't find in cache, it will change to DB automatically
	 * 
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public List<PaymentSerialNumber> getListInCache(SerialNumberQuery query) throws Exception;
}
