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
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 客户
 * 
 * @author gl
 * 
 */
@Entity
public class TdClient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// 客户类别
	@Column
	private String typeName;

	// 客户姓名
	@Column
	private String name;

	// 客户描述
	@Column
	private String description;

	// 客户电话
	@Column
	private String phoneNum;

	// 所属员工Id
	@Column
	private Long managerId;

	// 所属员工姓名
	@Column
	private String managerName;

	// 客户创建时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;

	// 拥有金额的车站
	@Column
	private String stationsWithMoney;

	// 收入<-银行
	@Column
	private Double inMoneyFromBank;

	// 收入<-现金
	@Column
	private Double inMoneyFromCash;

	// 收入<-转账
	@Column
	private Double inMoneyFromTransfer;

	// 支出项目
	@Transient
	private List<TdOutMoneyItem> outMoneyItemList;

	// 旅游线路
	@Transient
	private List<TdStationCenterStaffTravelLine> tdStationCenterStaffTravelLineList;

	// 成本
	@Column
	private Double cost;

	// 利润
	@Column
	private Double profits;

	// 营业收入
	@Column
	private Double operationIncome;

	// 录入人
	@Column
	private String editor;

	// 费用结算表
	@Transient
	private TdCostBalance costBalance;

	public List<TdStationCenterStaffTravelLine> getTdStationCenterStaffTravelLineList() {
		return tdStationCenterStaffTravelLineList;
	}

	public void setTdStationCenterStaffTravelLineList(
			List<TdStationCenterStaffTravelLine> tdStationCenterStaffTravelLineList) {
		this.tdStationCenterStaffTravelLineList = tdStationCenterStaffTravelLineList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getStationsWithMoney() {
		return stationsWithMoney;
	}

	public void setStationsWithMoney(String stationsWithMoney) {
		this.stationsWithMoney = stationsWithMoney;
	}

	public Double getInMoneyFromBank() {
		return inMoneyFromBank;
	}

	public void setInMoneyFromBank(Double inMoneyFromBank) {
		this.inMoneyFromBank = inMoneyFromBank;
	}

	public Double getInMoneyFromCash() {
		return inMoneyFromCash;
	}

	public void setInMoneyFromCash(Double inMoneyFromCash) {
		this.inMoneyFromCash = inMoneyFromCash;
	}

	public Double getInMoneyFromTransfer() {
		return inMoneyFromTransfer;
	}

	public void setInMoneyFromTransfer(Double inMoneyFromTransfer) {
		this.inMoneyFromTransfer = inMoneyFromTransfer;
	}

	public List<TdOutMoneyItem> getOutMoneyItemList() {
		return outMoneyItemList;
	}

	public void setOutMoneyItemList(List<TdOutMoneyItem> outMoneyItemList) {
		this.outMoneyItemList = outMoneyItemList;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Double getProfits() {
		return profits;
	}

	public void setProfits(Double profits) {
		this.profits = profits;
	}

	public Double getOperationIncome() {
		return operationIncome;
	}

	public void setOperationIncome(Double operationIncome) {
		this.operationIncome = operationIncome;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public TdCostBalance getCostBalance() {
		return costBalance;
	}

	public void setCostBalance(TdCostBalance costBalance) {
		this.costBalance = costBalance;
	}

}
