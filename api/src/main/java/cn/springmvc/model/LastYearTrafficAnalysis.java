package cn.springmvc.model;

// resources ：e_order_info、e_store_info、e_order_extinfo、r_base_dict_item
public class LastYearTrafficAnalysis {

	private String ORDER_CREATE_DATE;

	private String dayWeek;

	private String CLIENT_TYPENAME;

	private String STORE_TYPENAME;

	private String STORE_NAME;

	private String ALLORDER;

	private String PAYORDER;

	private String ORDER_PAYMENT_RATE;

	public String getORDER_CREATE_DATE() {
		return ORDER_CREATE_DATE;
	}

	public void setORDER_CREATE_DATE(String oRDER_CREATE_DATE) {
		ORDER_CREATE_DATE = oRDER_CREATE_DATE;
	}

	public String getDayWeek() {
		return dayWeek;
	}

	public void setDayWeek(String dayWeek) {
		this.dayWeek = dayWeek;
	}

	public String getCLIENT_TYPENAME() {
		return CLIENT_TYPENAME;
	}

	public void setCLIENT_TYPENAME(String cLIENT_TYPENAME) {
		CLIENT_TYPENAME = cLIENT_TYPENAME;
	}

	public String getSTORE_TYPENAME() {
		return STORE_TYPENAME;
	}

	public void setSTORE_TYPENAME(String sTORE_TYPENAME) {
		STORE_TYPENAME = sTORE_TYPENAME;
	}

	public String getSTORE_NAME() {
		return STORE_NAME;
	}

	public void setSTORE_NAME(String sTORE_NAME) {
		STORE_NAME = sTORE_NAME;
	}

	public String getALLORDER() {
		return ALLORDER;
	}

	public void setALLORDER(String aLLORDER) {
		ALLORDER = aLLORDER;
	}

	public String getPAYORDER() {
		return PAYORDER;
	}

	public void setPAYORDER(String pAYORDER) {
		PAYORDER = pAYORDER;
	}

	public String getORDER_PAYMENT_RATE() {
		return ORDER_PAYMENT_RATE;
	}

	public void setORDER_PAYMENT_RATE(String oRDER_PAYMENT_RATE) {
		ORDER_PAYMENT_RATE = oRDER_PAYMENT_RATE;
	}

	public LastYearTrafficAnalysis(String oRDER_CREATE_DATE, String dayWeek,
			String cLIENT_TYPENAME, String sTORE_TYPENAME, String sTORE_NAME,
			String aLLORDER, String pAYORDER, String oRDER_PAYMENT_RATE) {
		super();
		ORDER_CREATE_DATE = oRDER_CREATE_DATE;
		this.dayWeek = dayWeek;
		CLIENT_TYPENAME = cLIENT_TYPENAME;
		STORE_TYPENAME = sTORE_TYPENAME;
		STORE_NAME = sTORE_NAME;
		ALLORDER = aLLORDER;
		PAYORDER = pAYORDER;
		ORDER_PAYMENT_RATE = oRDER_PAYMENT_RATE;
	}

	public LastYearTrafficAnalysis() {
		super();
		// TODO Auto-generated constructor stub
	}

}
