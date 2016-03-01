package cn.springmvc.model;

/**
 * 
 * @author johsnon
 *
 */
public class UserAnalysis {
	String regist_time;
	String CUSTOMER_TOKEN;
	String nick_name;
	String real_name;
	String gender;
	String phone;
	String idCard;
	String birthday;
	int age;
	double total_amount;
	int num_of_orders;
	double per_customer_transaction;
	String province;
	String city;
	String county;
	String address;

	public String getRegist_time() {
		return regist_time;
	}

	public void setRegist_time(String regist_time) {
		this.regist_time = regist_time;
	}

	public String getCUSTOMER_TOKEN() {
		return CUSTOMER_TOKEN;
	}

	public void setCUSTOMER_TOKEN(String cUSTOMER_TOKEN) {
		CUSTOMER_TOKEN = cUSTOMER_TOKEN;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}

	public int getNum_of_orders() {
		return num_of_orders;
	}

	public void setNum_of_orders(int num_of_orders) {
		this.num_of_orders = num_of_orders;
	}

	public double getPer_customer_transaction() {
		return per_customer_transaction;
	}

	public void setPer_customer_transaction(double per_customer_transaction) {
		this.per_customer_transaction = per_customer_transaction;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
