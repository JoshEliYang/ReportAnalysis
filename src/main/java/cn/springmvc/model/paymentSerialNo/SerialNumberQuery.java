package cn.springmvc.model.paymentSerialNo;

/**
 * 
 * @author johnson
 *
 */
public class SerialNumberQuery {
	String startTime;
	String endTime;
	/**
	 * 0:all 1:pay 2:refund
	 */
	int type = 0;
	String typeName = types[type];
	int skip = 0;
	int limit = 100;

	boolean isAll = false;

	public static String[] types = new String[] { "all", "付款", "退款" };

	@Override
	public String toString() {
		return "SerialNumberQuery [startTime=" + startTime + ", endTime=" + endTime + ", type=" + type + ", typeName="
				+ typeName + ", skip=" + skip + ", limit=" + limit + ", isAll=" + isAll + "]";
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getSkip() {
		return skip;
	}

	public void setSkip(int skip) {
		this.skip = skip;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public boolean isAll() {
		return isAll;
	}

	public void setAll(boolean isAll) {
		this.isAll = isAll;
	}

}
