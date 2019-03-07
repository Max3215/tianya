package com.ynyes.tianya.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class TdGuessingGame {
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private Long id;
	 
	// 用户名
	@Column
	private String username;
	
	// 手机号码
	@Column
	private String mobile;
	
	// 竞猜金额 单位 亿
	@Column
	private String guessingPrice;
	
	// 竞猜时间
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date guessingTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getGuessingPrice() {
		return guessingPrice;
	}

	public void setGuessingPrice(String guessingPrice) {
		this.guessingPrice = guessingPrice;
	}

	public Date getGuessingTime() {
		return guessingTime;
	}

	public void setGuessingTime(Date guessingTime) {
		this.guessingTime = guessingTime;
	}
	
	
}
