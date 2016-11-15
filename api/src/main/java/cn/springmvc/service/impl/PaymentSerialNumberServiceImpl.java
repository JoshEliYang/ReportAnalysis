package cn.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.utils.MemcacheUtil;

import cn.springmvc.ReportDAO.PaymentSerialNumberDao;
import cn.springmvc.model.paymentSerialNo.PaymentSerialNumber;
import cn.springmvc.model.paymentSerialNo.SerialNumberQuery;
import cn.springmvc.service.PaymentSerialNumberService;
import jxl.common.Logger;

/**
 * 
 * @author johnson
 *
 */
@Service
public class PaymentSerialNumberServiceImpl implements PaymentSerialNumberService {

	@Autowired
	private PaymentSerialNumberDao serialNumberDao;

	Logger logger = Logger.getLogger(PaymentSerialNumberServiceImpl.class);

	/**
	 * query payment serial numbers
	 */
	public List<PaymentSerialNumber> getList(SerialNumberQuery query) throws Exception {
		List<PaymentSerialNumber> result = null;
		if (query.getType() != 0) {
			query.setTypeName(SerialNumberQuery.types[query.getType()]);
		} else {
			query.setTypeName(null);
		}

		result = serialNumberDao.getAll(query);
		try {
			MemcacheUtil memcacheUtil = MemcacheUtil.getInstance();
			memcacheUtil.setDat(String.valueOf(query.toString().hashCode()), result);
			logger.error("wirted to cache success >>>> key=" + String.valueOf(query.toString().hashCode()));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					"exception occured when wirting to cache >>> key=" + String.valueOf(query.toString().hashCode()));
		}
		return result;
	}

	/**
	 * get count
	 */
	public int getCount(SerialNumberQuery query) throws Exception {
		return serialNumberDao.getCount(query);
	}

	/**
	 * query payment serial number in cache
	 */
	public List<PaymentSerialNumber> getListInCache(SerialNumberQuery query) throws Exception {
		List<PaymentSerialNumber> result = null;

		try {
			MemcacheUtil memcacheUtil = MemcacheUtil.getInstance();
			result = memcacheUtil.getDat(String.valueOf(query.toString().hashCode()), List.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result != null) {
			logger.error("getted from cache success  >>>> key=" + String.valueOf(query.toString().hashCode()));
			return result;
		} else {
			logger.error("getted from cache failed >>>> key=" + String.valueOf(query.toString().hashCode()));
			return getList(query);
		}
	}

}
