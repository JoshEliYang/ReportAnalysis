package cn.springmvc.model.paymentSerialNo;

import java.io.Serializable;

/**
 * 
 * @author johnson
 *
 */
public class PaymentSerialNumber implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String pay_order_create_time;
	String order_no;
	String out_trade_no;
	float pay_order_fee;
	String STORE_NAME;
	String pay_type;

	@Override
	public String toString() {
		return "PaymentSerialNumber [pay_order_create_time=" + pay_order_create_time + ", order_no=" + order_no
				+ ", out_trade_no=" + out_trade_no + ", pay_order_fee=" + pay_order_fee + ", STORE_NAME=" + STORE_NAME
				+ ", pay_type=" + pay_type + "]";
	}

	public String getPay_order_create_time() {
		return pay_order_create_time;
	}

	public void setPay_order_create_time(String pay_order_create_time) {
		this.pay_order_create_time = pay_order_create_time;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public float getPay_order_fee() {
		return pay_order_fee;
	}

	public void setPay_order_fee(float pay_order_fee) {
		this.pay_order_fee = pay_order_fee;
	}

	public String getSTORE_NAME() {
		return STORE_NAME;
	}

	public void setSTORE_NAME(String sTORE_NAME) {
		STORE_NAME = sTORE_NAME;
	}

	public String getPay_type() {
		return pay_type;
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

}
