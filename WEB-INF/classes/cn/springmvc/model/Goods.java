package cn.springmvc.model;

/**
 * @author liqiang
 * 表格实体类
 */
public class Goods { 

	private int id;
	
	private String cargo_num;
	
	private String cargo_no;
	
	private String cargo_name;
	
	private double cargo_salesPrice;
	
	private double cargo_markPrice;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCargo_num() {
		return cargo_num;
	}

	public void setCargo_num(String cargo_num) {
		this.cargo_num = cargo_num;
	}

	public String getCargo_no() {
		return cargo_no;
	}

	public void setCargo_no(String cargo_no) {
		this.cargo_no = cargo_no;
	}

	public String getCargo_name() {
		return cargo_name;
	}

	public void setCargo_name(String cargo_name) {
		this.cargo_name = cargo_name;
	}

	public double getCargo_salesPrice() {
		return cargo_salesPrice;
	}

	public void setCargo_salesPrice(double cargo_salesPrice) {
		this.cargo_salesPrice = cargo_salesPrice;
	}

	public double getCargo_markPrice() {
		return cargo_markPrice;
	}

	public void setCargo_markPrice(double cargo_markPrice) {
		this.cargo_markPrice = cargo_markPrice;
	}

	@Override
	public String toString() {
		return "Goods [id=" + id + ", cargo_num=" + cargo_num + ", cargo_no="
				+ cargo_no + ", cargo_name=" + cargo_name
				+ ", cargo_salesPrice=" + cargo_salesPrice
				+ ", cargo_markPrice=" + cargo_markPrice + "]";
	}
	
	public Goods() {
		super();
	}

	public Goods(int id, String cargo_num, String cargo_no, String cargo_name,
			double cargo_salesPrice, double cargo_markPrice) {
		super();
		this.id = id;
		this.cargo_num = cargo_num;
		this.cargo_no = cargo_no;
		this.cargo_name = cargo_name;
		this.cargo_salesPrice = cargo_salesPrice;
		this.cargo_markPrice = cargo_markPrice;
	}
}
