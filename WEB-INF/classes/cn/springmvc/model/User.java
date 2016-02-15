package cn.springmvc.model;

import java.sql.Date;

/**
 * 用户表
 * @author JoshYang
 * 2015年11月11日
 */
public class User {

	private int id;
	private int device_type;
	private String device_num;
	private Date create_time;
	private Date last_time;
	private int state;
	private String nickname;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDevice_type() {
		return device_type;
	}
	public void setDevice_type(int device_type) {
		this.device_type = device_type;
	}
	public String getDevice_num() {
		return device_num;
	}
	public void setDevice_num(String device_num) {
		this.device_num = device_num;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getLast_time() {
		return last_time;
	}
	public void setLast_time(Date last_time) {
		this.last_time = last_time;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public User() {
		super();
	}
	public User(int id, int device_type, String device_num, Date create_time,
			Date last_time, int state, String nickname) {
		super();
		this.id = id;
		this.device_type = device_type;
		this.device_num = device_num;
		this.create_time = create_time;
		this.last_time = last_time;
		this.state = state;
		this.nickname = nickname;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", device_type=" + device_type
				+ ", device_num=" + device_num + ", create_time=" + create_time
				+ ", last_time=" + last_time + ", state=" + state
				+ ", nickname=" + nickname + "]";
	}
}
