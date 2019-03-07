package com.ynyes.tianya.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TdBargainParticipant {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	// 姓名
    @Column
    private String trueName;
    
    // 电话
    @Column
    private String mobile;

    // 当前价格
    @Column
    private Double currentPrice;
    
    // 购买价格
    @Column
    private Double getPrice;
    
    // 是否自砍
    @Column
    private Boolean isCutprice;
    
    // 自砍价格
    @Column
    private Double cutPrice;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Double getGetPrice() {
		return getPrice;
	}

	public void setGetPrice(Double getPrice) {
		this.getPrice = getPrice;
	}

	public Double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(Double currentPrice) {
		this.currentPrice = currentPrice;
	}

	public Boolean getIsCutprice() {
		return isCutprice;
	}

	public void setIsCutprice(Boolean isCutprice) {
		this.isCutprice = isCutprice;
	}

	public Double getCutPrice() {
		return cutPrice;
	}

	public void setCutPrice(Double cutPrice) {
		this.cutPrice = cutPrice;
	}

}
