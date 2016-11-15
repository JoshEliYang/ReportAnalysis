package cn.springmvc.ReportDAO;

import java.util.List;

import cn.springmvc.model.paymentSerialNo.PaymentSerialNumber;
import cn.springmvc.model.paymentSerialNo.SerialNumberQuery;

/**
 * 
 * @author johnson
 *
 */
public interface PaymentSerialNumberDao {

	/**
	 * query payment serial numbers with specific type
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<PaymentSerialNumber> getAll(SerialNumberQuery query) throws Exception;

	/**
	 * get count
	 * 
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public int getCount(SerialNumberQuery query) throws Exception;

}
