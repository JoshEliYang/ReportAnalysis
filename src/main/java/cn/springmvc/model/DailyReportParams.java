package cn.springmvc.model;

import java.io.Serializable;

public class DailyReportParams implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int limit;

	private int offset;

	private int year;
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

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

	@Override
	public String toString() {
		return "DailyReportParams [limit=" + limit + ", offset=" + offset
				+ ", year=" + year + "]";
	}

	public DailyReportParams(int limit, int offset, int year) {
		super();
		this.limit = limit;
		this.offset = offset;
		this.year = year;
	}

	public DailyReportParams() {
		super();
	}



}
