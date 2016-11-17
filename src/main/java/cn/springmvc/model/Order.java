package cn.springmvc.model;

/**
 * 
 * @author johsnon
 *
 */
public class Order {
	// 客户ID
	String CUSTOMER_ID;
	// 订单号
	String ORDER_NO;
	// 订单类型
	String ORDER_TYPE;
	// 订单总金额
	String ORDER_AMOUNT;
	// 订单备注
	String ORDER_REMARK;
	// 订单状态
	String ORDER_STATUS;
	// 支付状态
	String PAY_STATUS;
	// 创建日期
	String ORDER_CREATE_TIME;
	//
	String nick_name;
	//
	String real_name;
	// 身份证
	String ID_CARD;
	//
	String phone;
	//
	String email;
	// 系统删除
	String is_delete;
	// 快递费
	String transport_fee;
	// 发货状态
	String SEND_STATUS;
	// 用户删除
	String USER_DELETE;
	// 支付方式
	String PAY_TYPE;
	// 投递方式
	String DELIVERY_TYPE;
	// 收货人
	String RECEIVE_CONSIGNER;
	//
	String province;
	//
	String city;
	//
	String county;
	//
	String RECEIVE_ADDRESS;
	//
	String RECEIVE_MOBILE;
	// 发票类型
	String INVOICE_TYPE;
	// 发票头
	String INVOICE_TITLE;
	// 发票备注
	String INVOICE_REMARK;
	// 客户端
	String CLIENT_TYPE;

	public String getCUSTOMER_ID() {
		return CUSTOMER_ID;
	}

	public void setCUSTOMER_ID(String cUSTOMER_ID) {
		CUSTOMER_ID = cUSTOMER_ID;
	}

	public String getORDER_NO() {
		return ORDER_NO;
	}

	public void setORDER_NO(String oRDER_NO) {
		ORDER_NO = oRDER_NO;
	}

	public String getORDER_TYPE() {
		return ORDER_TYPE;
	}

	public void setORDER_TYPE(String oRDER_TYPE) {
		ORDER_TYPE = oRDER_TYPE;
	}

	public String getORDER_AMOUNT() {
		return ORDER_AMOUNT;
	}

	public void setORDER_AMOUNT(String oRDER_AMOUNT) {
		ORDER_AMOUNT = oRDER_AMOUNT;
	}

	public String getORDER_REMARK() {
		return ORDER_REMARK;
	}

	public void setORDER_REMARK(String oRDER_REMARK) {
		ORDER_REMARK = oRDER_REMARK;
	}

	public String getORDER_STATUS() {
		return ORDER_STATUS;
	}

	public void setORDER_STATUS(String oRDER_STATUS) {
		ORDER_STATUS = oRDER_STATUS;
	}

	public String getPAY_STATUS() {
		return PAY_STATUS;
	}

	public void setPAY_STATUS(String pAY_STATUS) {
		PAY_STATUS = pAY_STATUS;
	}

	public String getORDER_CREATE_TIME() {
		return ORDER_CREATE_TIME;
	}

	public void setORDER_CREATE_TIME(String oRDER_CREATE_TIME) {
		ORDER_CREATE_TIME = oRDER_CREATE_TIME;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getID_CARD() {
		return ID_CARD;
	}

	public void setID_CARD(String iD_CARD) {
		ID_CARD = iD_CARD;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(String is_delete) {
		this.is_delete = is_delete;
	}

	public String getTransport_fee() {
		return transport_fee;
	}

	public void setTransport_fee(String transport_fee) {
		this.transport_fee = transport_fee;
	}

	public String getSEND_STATUS() {
		return SEND_STATUS;
	}

	public void setSEND_STATUS(String sEND_STATUS) {
		SEND_STATUS = sEND_STATUS;
	}

	public String getUSER_DELETE() {
		return USER_DELETE;
	}

	public void setUSER_DELETE(String uSER_DELETE) {
		USER_DELETE = uSER_DELETE;
	}

	public String getPAY_TYPE() {
		return PAY_TYPE;
	}

	public void setPAY_TYPE(String pAY_TYPE) {
		PAY_TYPE = pAY_TYPE;
	}

	public String getDELIVERY_TYPE() {
		return DELIVERY_TYPE;
	}

	public void setDELIVERY_TYPE(String dELIVERY_TYPE) {
		DELIVERY_TYPE = dELIVERY_TYPE;
	}

	public String getRECEIVE_CONSIGNER() {
		return RECEIVE_CONSIGNER;
	}

	public void setRECEIVE_CONSIGNER(String rECEIVE_CONSIGNER) {
		RECEIVE_CONSIGNER = rECEIVE_CONSIGNER;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getRECEIVE_ADDRESS() {
		return RECEIVE_ADDRESS;
	}

	public void setRECEIVE_ADDRESS(String rECEIVE_ADDRESS) {
		RECEIVE_ADDRESS = rECEIVE_ADDRESS;
	}

	public String getRECEIVE_MOBILE() {
		return RECEIVE_MOBILE;
	}

	public void setRECEIVE_MOBILE(String rECEIVE_MOBILE) {
		RECEIVE_MOBILE = rECEIVE_MOBILE;
	}

	public String getINVOICE_TYPE() {
		return INVOICE_TYPE;
	}

	public void setINVOICE_TYPE(String iNVOICE_TYPE) {
		INVOICE_TYPE = iNVOICE_TYPE;
	}

	public String getINVOICE_TITLE() {
		return INVOICE_TITLE;
	}

	public void setINVOICE_TITLE(String iNVOICE_TITLE) {
		INVOICE_TITLE = iNVOICE_TITLE;
	}

	public String getINVOICE_REMARK() {
		return INVOICE_REMARK;
	}

	public void setINVOICE_REMARK(String iNVOICE_REMARK) {
		INVOICE_REMARK = iNVOICE_REMARK;
	}

	public String getCLIENT_TYPE() {
		return CLIENT_TYPE;
	}

	public void setCLIENT_TYPE(String cLIENT_TYPE) {
		CLIENT_TYPE = cLIENT_TYPE;
	}

}
