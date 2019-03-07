package com.ynyes.tianya.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 商品信息表
 * 
 * @author gl
 *
 */

@Entity
public class TdStationCenterStaffTravelLine {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// 所属客户Id
	@Column
	private Long clientId;

	// 所属管理员Id
	@Column
	private Long managerId;

	// 所属管理员
	@Column
	private String managerName;

	// 父级树
	@Column
	private String parentTree;

	
	// 单位
	@Column
	private Integer unitId;
	
	@Column
	private String unit;

	// 职工
	@Column
	private String staffName;

	// 家属
	@Column
	private Integer family;
	
	// Max   实际收费 成人
	@Column
	private Integer adultNumber;
	// 儿童
	@Column
	private Integer childNumber;

	// 出行时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date travelTime;

	// 合同价格
	@Column
	private Double contractPrice;

	// 前期余额
	@Column
	private Double previousBalance;

	// 优惠金额
	@Column
	private Double discountAmount;

	// 本次余额
	@Column
	private Double currentBalance;

	// 现金
	@Column
	private Double cashMoney;

	// 银行
	@Column
	private Double bankMoney;

	// 挂账
	@Column
	private Double creditMoney;

	// 签字人
	@Column
	private String siger;

	// 出行路线
	@Column
	private String travelLine;

	// 成本
	@Column
	private Double costing;
	
	// 保险费 Max
	@Column
	private Double premium;

	// 记录生成时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;
	
	@Column
	private String costNumber;

	// 是否已结算
	@Column
	private Boolean isSettle;
	
	// 备注
	@Column
	private String remark;
	
	/**
	 * 以下为6.8日新加字段
	 * @author Max
	 */
	// 团队名称编号
	@Column
	private String teamNumber;
	
	// 成人价
	@Column
	private Double aduitCost;
	
	// 儿童
	@Column
	private Double childCost;
	
	// 类型
	@Column
	private Integer type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public void setUnit(int unitId) {
		switch (unitId) {
		case 1:
			this.unit ="重庆交通运业有限责任公司";
			break;
		case 2:
			this.unit ="重庆交通运业有限责任公司渝北双凤桥汽车站";
			break;
		case 3:
			this.unit ="重庆交通运业有限责任公司重庆汽车站";
			break;
		case 4:
			this.unit ="重庆交通运业有限责任公司陈家坪汽车站";
			break;
		case 5:
			this.unit ="重庆交通运业有限责任公司龙头寺汽车站";
			break;
		case 6:
			this.unit ="重庆交通运业有限责任公司富丽大酒店";
			break;
		case 7:
			this.unit ="重庆交通运业有限责任公司站务中心汽车北站";
			break;
		case 8:
			this.unit ="重庆交通运业有限责任公司站务中心南坪汽车站";
			break;
		case 9:
			this.unit ="重庆市公路客运联网售票中心有限公司";
			break;
		case 10:
			this.unit ="重庆交通运业有限责任公司站务中心长途汽车站";
			break;
		case 11:
			this.unit ="重庆迅为四公里交通换乘枢纽有限公司";
			break;
		case 12:
			this.unit ="重庆天涯国际旅行社有限公司";
			break;
		case 13:
			this.unit ="重庆交通运业有限责任公司商旅服务分公司";
			break;
		case 14:
			this.unit ="重庆交通运业有限责任公司稽查大队";
			break;
		case 15:
			this.unit ="重庆交通运业有限责任公司交运快递";
			break;
		case 16:
			this.unit ="重庆交通运业有限责任公司富苑宾馆";
			break;
		case 18:
			this.unit ="重庆交通运业有限责任公司巴南龙洲湾汽车站";
			break;
		default:
			break;
		}
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public Integer getFamily() {
		return family;
	}

	public void setFamily(Integer family) {
		this.family = family;
	}

	public Integer getAdultNumber() {
		return adultNumber;
	}

	public void setAdultNumber(Integer adultNumber) {
		this.adultNumber = adultNumber;
	}

	public Integer getChildNumber() {
		return childNumber;
	}

	public void setChildNumber(Integer childNumber) {
		this.childNumber = childNumber;
	}

	public Date getTravelTime() {
		return travelTime;
	}

	public void setTravelTime(Date travelTime) {
		this.travelTime = travelTime;
	}

	public Double getContractPrice() {
		return contractPrice;
	}

	public void setContractPrice(Double contractPrice) {
		this.contractPrice = contractPrice;
	}

	public Double getPreviousBalance() {
		return previousBalance;
	}

	public void setPreviousBalance(Double previousBalance) {
		this.previousBalance = previousBalance;
	}

	public Double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(Double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public Double getCashMoney() {
		return cashMoney;
	}

	public void setCashMoney(Double cashMoney) {
		this.cashMoney = cashMoney;
	}

	public String getSiger() {
		return siger;
	}

	public void setSiger(String siger) {
		this.siger = siger;
	}

	public String getTravelLine() {
		return travelLine;
	}

	public void setTravelLine(String travelLine) {
		this.travelLine = travelLine;
	}

	public Double getBankMoney() {
		return bankMoney;
	}

	public void setBankMoney(Double bankMoney) {
		this.bankMoney = bankMoney;
	}

	public Double getCreditMoney() {
		return creditMoney;
	}

	public void setCreditMoney(Double creditMoney) {
		this.creditMoney = creditMoney;
	}

	public Double getCosting() {
		return costing;
	}

	public void setCosting(Double costing) {
		this.costing = costing;
	}

	public Double getPremium() {
		return premium;
	}

	public void setPremium(Double premium) {
		this.premium = premium;
	}

	public Boolean getIsSettle() {
		return isSettle;
	}

	public void setIsSettle(Boolean isSettle) {
		this.isSettle = isSettle;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getCostNumber() {
		return costNumber;
	}

	public void setCostNumber(String costNumber) {
		this.costNumber = costNumber;
	}

	public String getParentTree() {
		return parentTree;
	}

	public void setParentTree(String parentTree) {
		this.parentTree = parentTree;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public String getTeamNumber() {
		return teamNumber;
	}

	public void setTeamNumber(String teamNumber) {
		this.teamNumber = teamNumber;
	}

	public Double getAduitCost() {
		return aduitCost;
	}

	public void setAduitCost(Double aduitCost) {
		this.aduitCost = aduitCost;
	}

	public Double getChildCost() {
		return childCost;
	}

	public void setChildCost(Double childCost) {
		this.childCost = childCost;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
