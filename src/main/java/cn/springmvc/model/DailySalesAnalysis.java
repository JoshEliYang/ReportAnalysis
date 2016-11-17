package cn.springmvc.model;

public class DailySalesAnalysis {

	private String ORDER_CREATE_DATE;

	private String dayWeek;

	private String CLIENT_TYPENAME;

	private String STORE_TYPENAME;

	private String STORE_NAME;

	private String ORDER_PRICE;

	private String ORDER_NUMBER;

	private String ORDERAMOUNT;

	private String ALLYH;

	private String PAYMENT_AMOUNT;

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

	public String getORDER_PRICE() {
		return ORDER_PRICE;
	}

	public void setORDER_PRICE(String oRDER_PRICE) {
		ORDER_PRICE = oRDER_PRICE;
	}

	public String getORDER_NUMBER() {
		return ORDER_NUMBER;
	}

	public void setORDER_NUMBER(String oRDER_NUMBER) {
		ORDER_NUMBER = oRDER_NUMBER;
	}

	public String getORDERAMOUNT() {
		return ORDERAMOUNT;
	}

	public void setORDERAMOUNT(String oRDERAMOUNT) {
		ORDERAMOUNT = oRDERAMOUNT;
	}

	public String getALLYH() {
		return ALLYH;
	}

	public void setALLYH(String aLLYH) {
		ALLYH = aLLYH;
	}

	public String getPAYMENT_AMOUNT() {
		return PAYMENT_AMOUNT;
	}

	public void setPAYMENT_AMOUNT(String pAYMENT_AMOUNT) {
		PAYMENT_AMOUNT = pAYMENT_AMOUNT;
	}

	public DailySalesAnalysis(String oRDER_CREATE_DATE, String dayWeek,
			String cLIENT_TYPENAME, String sTORE_TYPENAME, String sTORE_NAME,
			String oRDER_PRICE, String oRDER_NUMBER, String oRDERAMOUNT,
			String aLLYH, String pAYMENT_AMOUNT) {
		super();
		ORDER_CREATE_DATE = oRDER_CREATE_DATE;
		this.dayWeek = dayWeek;
		CLIENT_TYPENAME = cLIENT_TYPENAME;
		STORE_TYPENAME = sTORE_TYPENAME;
		STORE_NAME = sTORE_NAME;
		ORDER_PRICE = oRDER_PRICE;
		ORDER_NUMBER = oRDER_NUMBER;
		ORDERAMOUNT = oRDERAMOUNT;
		ALLYH = aLLYH;
		PAYMENT_AMOUNT = pAYMENT_AMOUNT;
	}

	public DailySalesAnalysis() {
		super();
		// TODO Auto-generated constructor stub
	}

}
