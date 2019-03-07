package com.ynyes.tianya.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class TdBargainRecord {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	// 亲友用户名
	@Autowired
	private String friendName;
	
	// 所帮助用户
	@Autowired
	private String trueName;
	
	// 所帮助用户电话
	@Autowired
	private String mobile;
	
	// 头像地址
	@Autowired
	private String headerUrl;
	
	// 砍价金额
	@Autowired
	private Double cutPrice;
	
	// 剩余价格
	@Autowired
	private Double newPrice;
	
	// 砍价时间
    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date cutTime;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFriendName() {
		return friendName;
	}

	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	public String getHeaderUrl() {
		return headerUrl;
	}

	public void setHeaderUrl(String headerUrl) {
		this.headerUrl = headerUrl;
	}

	public Double getCutPrice() {
		return cutPrice;
	}

	public void setCutPrice(Double cutPrice) {
		this.cutPrice = cutPrice;
	}

	public Double getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(Double newPrice) {
		this.newPrice = newPrice;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getCutTime() {
		return cutTime;
	}

	public void setCutTime(Date cutTime) {
		this.cutTime = cutTime;
	}
	
	
}
