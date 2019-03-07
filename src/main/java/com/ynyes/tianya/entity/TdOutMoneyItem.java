package com.ynyes.tianya.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 支出金额条目
 * 
 * @author gl
 * 
 */
@Entity
public class TdOutMoneyItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// 支出项目名称
	@Column
	private String title;

	// 现金支出额
	@Column
	private Double outMoneyFromCash;

	// 银行支出额
	@Column
	private Double outMoneyFromBank;

	// 所属客户Id
	@Column
	private Long clientId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getOutMoneyFromCash() {
		return outMoneyFromCash;
	}

	public void setOutMoneyFromCash(Double outMoneyFromCash) {
		this.outMoneyFromCash = outMoneyFromCash;
	}

	public Double getOutMoneyFromBank() {
		return outMoneyFromBank;
	}

	public void setOutMoneyFromBank(Double outMoneyFromBank) {
		this.outMoneyFromBank = outMoneyFromBank;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

}
