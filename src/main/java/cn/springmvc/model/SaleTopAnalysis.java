package cn.springmvc.model;

// resources ： r_base_dict_item、e_order_info、e_order_extinfo、e_order_detail
public class SaleTopAnalysis {

	private String ORDER_CREATE_DATE;

	private String STORE_TYPENAME;

	private String STORE_NAME;

	private String CLIENT_TYPENAME;

	private String goods_class_name;

	private String CARGO_NO;

	private String CARGO_NAME;

	private String purchaseAmount;

	private String PAYAMOUNT;

	private String saleCount;

	private String SALEAMOUNT;

	private String totalRate;

	private String ratePercent;

	private String jinbuCount;

	private String jinbuAmount;

	public String getORDER_CREATE_DATE() {
		return ORDER_CREATE_DATE;
	}

	public void setORDER_CREATE_DATE(String oRDER_CREATE_DATE) {
		ORDER_CREATE_DATE = oRDER_CREATE_DATE;
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

	public String getCLIENT_TYPENAME() {
		return CLIENT_TYPENAME;
	}

	public void setCLIENT_TYPENAME(String cLIENT_TYPENAME) {
		CLIENT_TYPENAME = cLIENT_TYPENAME;
	}

	public String getGoods_class_name() {
		return goods_class_name;
	}

	public void setGoods_class_name(String goods_class_name) {
		this.goods_class_name = goods_class_name;
	}

	public String getCARGO_NO() {
		return CARGO_NO;
	}

	public void setCARGO_NO(String cARGO_NO) {
		CARGO_NO = cARGO_NO;
	}

	public String getCARGO_NAME() {
		return CARGO_NAME;
	}

	public void setCARGO_NAME(String cARGO_NAME) {
		CARGO_NAME = cARGO_NAME;
	}

	public String getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(String purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	public String getPAYAMOUNT() {
		return PAYAMOUNT;
	}

	public void setPAYAMOUNT(String pAYAMOUNT) {
		PAYAMOUNT = pAYAMOUNT;
	}

	public String getSaleCount() {
		return saleCount;
	}

	public void setSaleCount(String saleCount) {
		this.saleCount = saleCount;
	}

	public String getSALEAMOUNT() {
		return SALEAMOUNT;
	}

	public void setSALEAMOUNT(String sALEAMOUNT) {
		SALEAMOUNT = sALEAMOUNT;
	}

	public String getTotalRate() {
		return totalRate;
	}

	public void setTotalRate(String totalRate) {
		this.totalRate = totalRate;
	}

	public String getRatePercent() {
		return ratePercent;
	}

	public void setRatePercent(String ratePercent) {
		this.ratePercent = ratePercent;
	}

	public String getJinbuCount() {
		return jinbuCount;
	}

	public void setJinbuCount(String jinbuCount) {
		this.jinbuCount = jinbuCount;
	}

	public String getJinbuAmount() {
		return jinbuAmount;
	}

	public void setJinbuAmount(String jinbuAmount) {
		this.jinbuAmount = jinbuAmount;
	}

	public SaleTopAnalysis(String oRDER_CREATE_DATE, String sTORE_TYPENAME, String sTORE_NAME, String cLIENT_TYPENAME,
			String goods_class_name, String cARGO_NAME, String purchaseAmount, String pAYAMOUNT, String saleCount,
			String sALEAMOUNT, String totalRate, String ratePercent, String jinbuCount, String jinbuAmount) {
		super();
		ORDER_CREATE_DATE = oRDER_CREATE_DATE;
		STORE_TYPENAME = sTORE_TYPENAME;
		STORE_NAME = sTORE_NAME;
		CLIENT_TYPENAME = cLIENT_TYPENAME;
		this.goods_class_name = goods_class_name;
		CARGO_NAME = cARGO_NAME;
		this.purchaseAmount = purchaseAmount;
		PAYAMOUNT = pAYAMOUNT;
		this.saleCount = saleCount;
		SALEAMOUNT = sALEAMOUNT;
		this.totalRate = totalRate;
		this.ratePercent = ratePercent;
		this.jinbuCount = jinbuCount;
		this.jinbuAmount = jinbuAmount;
	}

	public SaleTopAnalysis() {
		super();
		// TODO Auto-generated constructor stub
	}

}
