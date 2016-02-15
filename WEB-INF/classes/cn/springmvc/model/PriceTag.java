package cn.springmvc.model;

public class PriceTag {

	private int id;
	
	private String shopId;
	
	private String goodsNo;
	
	private String goodsName;
	
	private String unit;
	
	private String specifications;
	
	private String goodsOrigin;
	
	private double salesPrice;
	
	private double propmPrice;
	
	private double marketPrice;
	
	private String qrCode;
	
	private int type;
	
	private int visible;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public String getGoodsOrigin() {
		return goodsOrigin;
	}

	public void setGoodsOrigin(String goodsOrigin) {
		this.goodsOrigin = goodsOrigin;
	}

	public double getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(double salesPrice) {
		this.salesPrice = salesPrice;
	}

	public double getPropmPrice() {
		return propmPrice;
	}

	public void setPropmPrice(double propmPrice) {
		this.propmPrice = propmPrice;
	}

	public double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public PriceTag() {
		super();
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getVisible() {
		return visible;
	}

	public void setVisible(int visible) {
		this.visible = visible;
	}

	public PriceTag(int id, String shopId, String goodsNo, String goodsName,
			String unit, String specifications, String goodsOrigin,
			double salesPrice, double propmPrice, double marketPrice,
			String qrCode, int type, int visible) {
		super();
		this.id = id;
		this.shopId = shopId;
		this.goodsNo = goodsNo;
		this.goodsName = goodsName;
		this.unit = unit;
		this.specifications = specifications;
		this.goodsOrigin = goodsOrigin;
		this.salesPrice = salesPrice;
		this.propmPrice = propmPrice;
		this.marketPrice = marketPrice;
		this.qrCode = qrCode;
		this.type = type;
		this.visible = visible;
	}

	@Override
	public String toString() {
		return "PriceTag [id=" + id + ", shopId=" + shopId + ", goodsNo="
				+ goodsNo + ", goodsName=" + goodsName + ", unit=" + unit
				+ ", specifications=" + specifications + ", goodsOrigin="
				+ goodsOrigin + ", salesPrice=" + salesPrice + ", propmPrice="
				+ propmPrice + ", marketPrice=" + marketPrice + ", qrCode="
				+ qrCode + ", type=" + type + ", visible=" + visible + "]";
	}
}
