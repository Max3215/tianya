package com.ynyes.tianya.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * 汽车约租
 * 
 * @author gl
 *
 */

@Entity
public class TdReserveCar {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// 0: 约租, 1: 代驾
	@Column
	private Integer type;

	// 您的称呼
	@Column
	private String name;

	// 代驾师傅
	@Column
	private String driverName;

	// 您的电话
	@Column
	private String phone;

	// 邮箱
	@Column
	private String email;

	// QQ
	@Column
	private String qq;

	// 备注
	@Column
	private String note;

	// 取车时间
	@Column
	private String takeCarTime;

	// 还车时间
	@Column
	private String backCarTime;

	// 取车地点
	@Column
	private String getCarPlace;

	// 还车地点
	@Column
	private String backCarPlace;

	// 是否已查看
	@Column
	private Boolean isCheck;

	// 状态
	@Column
	private Integer status=1;

	// 状态字符串
	@Column
	private String statusStr;

	// 处理人Id
	@Column
	private Long dealManagerId;

	// 处理人
	@Column
	private String dealManager;

	public Long getDealManagerId() {
		return dealManagerId;
	}

	public void setDealManagerId(Long dealManagerId) {
		this.dealManagerId = dealManagerId;
	}

	public String getDealManager() {
		return dealManager;
	}

	public void setDealManager(String dealManager) {
		this.dealManager = dealManager;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusStr() {
		String statusStr = "";
		switch (status) { // 1:未联系2:已联系3:已完成4:已取消
		case 1:
			statusStr = "未联系";
			break;
		case 2:
			statusStr = "已联系";
			break;
		case 3:
			statusStr = "已完成";
			break;
		case 4:
			statusStr = "已取消";
			break;
		}
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTakeCarTime() {
		return takeCarTime;
	}

	public void setTakeCarTime(String takeCarTime) {
		this.takeCarTime = takeCarTime;
	}

	public String getBackCarTime() {
		return backCarTime;
	}

	public void setBackCarTime(String backCarTime) {
		this.backCarTime = backCarTime;
	}

	public String getGetCarPlace() {
		return getCarPlace;
	}

	public void setGetCarPlace(String getCarPlace) {
		this.getCarPlace = getCarPlace;
	}

	public String getBackCarPlace() {
		return backCarPlace;
	}

	public void setBackCarPlace(String backCarPlace) {
		this.backCarPlace = backCarPlace;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Boolean getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(Boolean isCheck) {
		this.isCheck = isCheck;
	}

}
