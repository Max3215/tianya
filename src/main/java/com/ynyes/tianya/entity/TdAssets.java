package com.ynyes.tianya.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


/**
 * 资产调节表
 * @author Max
 *
 */
@Entity
public class TdAssets {

	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private Long id;
	 
	 // 添加人
	 @Column
	 private Long managerId;
	 
	 @Column
	 private String manager;
	 
	 // 编号
	 @Column
	 private String assNumber;
	 
	 // 添加人结构
	 @Column
	 private String managerTree;
	 
	 // 添加时间
	 @Column
	 @DateTimeFormat(pattern="yyyy-MM-dd")
     @Temporal(TemporalType.TIMESTAMP)
	 private Date createTime;
	 
	 // 单位
	 @Column
	 private String company;
	 
	 // 用车时间
	 @Column
	 @DateTimeFormat(pattern="yyyy-MM-dd")
     @Temporal(TemporalType.TIMESTAMP)
	 private Date useDate;
	 
	 @Column
	 @DateTimeFormat(pattern="yyyy-MM-dd")
     @Temporal(TemporalType.TIMESTAMP)
	 private Date endDate;
	 
	 // 车型
	 @Column
	 private String models;
	 
	 // 行程
	 @Column
	 private String trip;
	 
	 // 数量
	 @Column
	 private Long number;
	 
	 // 成本
	 // 车费
	 @Column(scale=2)
	 private  Double CFare;
	 
	 // 已开票
	 @Column(scale=2)
	 private Double CInvoiced;
	 
	 // 未开票
	 @Column(scale=2)
	 private Double CUnbilled;
	 
	 // 已付款
	 @Column(scale=2)
	 private Double CAlready;
	 
	 // 营收
	 // 车费
	 @Column(scale=2)
	 private Double RFare;
	 
	 // 已开票
	 @Column(scale=2)
	 private Double RInvoiced;
	 
	 // 未开票
	 @Column(scale=2)
	 private Double RUnbilled;
	 
	 @Column(scale=2)
	 private Double unpaid;
	 
	 // 已收款
	 @Column(scale=2)
	 private Double RAlready;
	 
	 // 结算
	 @Column
	 private Boolean isSettlement;
	 
	 // 利润
	 @Column(scale=2)
	 private Double profit;
	 
	 // 车公司
	 @Column
	 private String carCompany;
	 
	 // 备注
	 @Column
	 private String mark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getAssNumber() {
		return assNumber;
	}

	public void setAssNumber(String assNumber) {
		this.assNumber = assNumber;
	}

	public String getManagerTree() {
		return managerTree;
	}

	public void setManagerTree(String managerTree) {
		this.managerTree = managerTree;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Date getUseDate() {
		return useDate;
	}

	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getModels() {
		return models;
	}

	public void setModels(String models) {
		this.models = models;
	}

	public String getTrip() {
		return trip;
	}

	public void setTrip(String trip) {
		this.trip = trip;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Double getCFare() {
		return CFare;
	}

	public void setCFare(Double cFare) {
		CFare = cFare;
	}

	public Double getCInvoiced() {
		return CInvoiced;
	}

	public void setCInvoiced(Double cInvoiced) {
		CInvoiced = cInvoiced;
	}

	public Double getCUnbilled() {
		return CUnbilled;
	}

	public void setCUnbilled(Double cUnbilled) {
		CUnbilled = cUnbilled;
	}

	public Double getCAlready() {
		return CAlready;
	}

	public void setCAlready(Double cAlready) {
		CAlready = cAlready;
	}

	public Double getRFare() {
		return RFare;
	}

	public void setRFare(Double rFare) {
		RFare = rFare;
	}

	public Double getRInvoiced() {
		return RInvoiced;
	}

	public void setRInvoiced(Double rInvoiced) {
		RInvoiced = rInvoiced;
	}

	public Double getRUnbilled() {
		return RUnbilled;
	}

	public void setRUnbilled(Double rUnbilled) {
		RUnbilled = rUnbilled;
	}

	public Double getRAlready() {
		return RAlready;
	}

	public void setRAlready(Double rAlready) {
		RAlready = rAlready;
	}

	public Boolean getIsSettlement() {
		return isSettlement;
	}

	public void setIsSettlement(Boolean isSettlement) {
		this.isSettlement = isSettlement;
	}

	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public String getCarCompany() {
		return carCompany;
	}

	public void setCarCompany(String carCompany) {
		this.carCompany = carCompany;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public Double getUnpaid() {
		return unpaid;
	}

	public void setUnpaid(Double unpaid) {
		this.unpaid = unpaid;
	}
	 

	
	 
	
}
