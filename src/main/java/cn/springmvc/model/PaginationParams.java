package cn.springmvc.model;

public class PaginationParams {

	private int limit;

	private int offset;

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public PaginationParams(int limit, int offset) {
		super();
		this.limit = limit;
		this.offset = offset;
	}

	public PaginationParams() {
		super();
	}

	@Override
	public String toString() {
		return "PaginationParams [limit=" + limit + ", offset=" + offset + "]";
	}

}
