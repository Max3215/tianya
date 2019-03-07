package com.ynyes.tianya.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class TdCostBalance {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// 填表年
	@Column
	private Integer fillYear;

	// 填表月
	@Column
	private Integer fillMonth;

	// 填表日
	@Column
	private Integer fillDay;

	// 出发年
	@Column
	private Integer goYear;

	// 出发月
	@Column
	private Integer goMonth;

	// 出发日
	@Column
	private Integer goDay;

	// 返回月
	@Column
	private Integer backMonth;

	// 返回日
	@Column
	private Integer backDay;

	// 共几天
	@Column
	private Integer totalDay;

	// 共几夜
	@Column
	private Integer totalNight;

	// 编号
	@Column
	private String theNum;

	// 团队名称
	@Column
	private String teamName;

	// 合同编号
	@Column
	private String contactNum;

	// 地接旅行社
	@Column
	private String travelAgence;

	// 陪同
	@Column
	private String accompany;

	// 证件号码
	@Column
	private String idNum;

	// 实际成人人数
	@Column
	private Integer adultNum;

	// 实际儿童数
	@Column
	private Integer childNum;

	// 实际收费人数
	@Column
	private Integer personNum;

	// 成人报价
	@Column(scale = 2)
	private Double adultPrice;

	// 儿童报价
	@Column(scale = 2)
	private Double childPrice;

	// 地接确认时间

	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date confirmTime;

	// 营业收入总额
	@Column(scale = 2)
	private Double totalIncome;

	// 交通总成本
	@Column(scale = 2)
	private Double travelCost;

	// 汽车成本
	@Column(scale = 2)
	private Double carCost;

	// 火车成本
	@Column(scale = 2)
	private Double trainCost;

	// 轮船成本
	@Column(scale = 2)
	private Double shipCost;

	// 飞机成本
	@Column(scale = 2)
	private Double airCost;

	// 房费
	@Column(scale = 2)
	private Double houseCost;

	// 餐费
	@Column(scale = 2)
	private Double eatCost;

	// 门票
	@Column(scale = 2)
	private Double ticketCost;

	// 保险
	@Column(scale = 2)
	private Double insuranceCost;

	// 代理费
	@Column(scale = 2)
	private Double agentCost;

	// 旅游签证费
	@Column(scale = 2)
	private Double visiaCost;

	// 陪同费用及补助
	@Column(scale = 2)
	private Double accompanyAndAllowance;

	// 其他
	@Column(scale = 2)
	private Double otherCost;

	// 地接综合费用
	@Column(scale = 2)
	private Double comprehensiveCost;

	// 合计
	@Column(scale = 2)
	private Double totalCost;

	// 营业净收入
	@Column(scale = 2)
	private Double pureIncome;

	// 营业净收入大写
	@Column(scale = 2)
	private String pureIncomeStr;

	// 原始单据张数
	@Column
	private Integer receiptNum;

	// 负责人
	@Column
	private String responsiblePerson;

	// 分管领导
	@Column
	private String fgLeader;

	// 财务主管
	@Column
	private String financeSupervisor;

	// 出纳
	@Column
	private String cashier;

	// 制表人
	@Column
	private String lister;

	// 负责人Id
	@Column
	private Long responsiblePersonId;

	// 分管领导Id
	@Column
	private Long fgLeaderId;

	// 财务主管Id
	@Column
	private Long financeSupervisorId;

	// 出纳Id
	@Column
	private Long cashierId;

	// 制表人Id
	@Column
	private Long listerId;

	// 格式[id1], [id2], [id3]...
	@Column
	private String checkPersonIdList;

	// 所属客户
	@Column
	private Long clientId;

	/**
	 * 新加字段
	 * 
	 * @author Max
	 * 
	 */
	// 结算时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;

	// 结算人
	@Column
	private Long managerId;

	@Column
	private String manager;

	@Column
	private String managerTree;

	@Column
	private String costNumber;

	// 状态：审核中，已通过，已拒绝
	@Column
	private String state;

	@Lob
	private String rejectReason;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getFillYear() {
		return fillYear;
	}

	public void setFillYear(Integer fillYear) {
		this.fillYear = fillYear;
	}

	public Integer getFillMonth() {
		return fillMonth;
	}

	public void setFillMonth(Integer fillMonth) {
		this.fillMonth = fillMonth;
	}

	public Integer getFillDay() {
		return fillDay;
	}

	public void setFillDay(Integer fillDay) {
		this.fillDay = fillDay;
	}

	public Integer getGoYear() {
		return goYear;
	}

	public void setGoYear(Integer goYear) {
		this.goYear = goYear;
	}

	public Integer getGoMonth() {
		return goMonth;
	}

	public void setGoMonth(Integer goMonth) {
		this.goMonth = goMonth;
	}

	public Integer getGoDay() {
		return goDay;
	}

	public void setGoDay(Integer goDay) {
		this.goDay = goDay;
	}

	public Integer getBackMonth() {
		return backMonth;
	}

	public void setBackMonth(Integer backMonth) {
		this.backMonth = backMonth;
	}

	public Integer getBackDay() {
		return backDay;
	}

	public void setBackDay(Integer backDay) {
		this.backDay = backDay;
	}

	public Integer getTotalDay() {
		return totalDay;
	}

	public void setTotalDay(Integer totalDay) {
		this.totalDay = totalDay;
	}

	public Integer getTotalNight() {
		return totalNight;
	}

	public void setTotalNight(Integer totalNight) {
		this.totalNight = totalNight;
	}

	public String getTheNum() {
		return theNum;
	}

	public void setTheNum(String theNum) {
		this.theNum = theNum;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getContactNum() {
		return contactNum;
	}

	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}

	public String getTravelAgence() {
		return travelAgence;
	}

	public void setTravelAgence(String travelAgence) {
		this.travelAgence = travelAgence;
	}

	public String getAccompany() {
		return accompany;
	}

	public void setAccompany(String accompany) {
		this.accompany = accompany;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public Integer getAdultNum() {
		return adultNum;
	}

	public void setAdultNum(Integer adultNum) {
		this.adultNum = adultNum;
	}

	public Integer getChildNum() {
		return childNum;
	}

	public void setChildNum(Integer childNum) {
		this.childNum = childNum;
	}

	public Integer getPersonNum() {
		return personNum;
	}

	public void setPersonNum(Integer personNum) {
		this.personNum = personNum;
	}

	public Double getAdultPrice() {
		return adultPrice;
	}

	public void setAdultPrice(Double adultPrice) {
		this.adultPrice = adultPrice;
	}

	public Double getChildPrice() {
		return childPrice;
	}

	public void setChildPrice(Double childPrice) {
		this.childPrice = childPrice;
	}

	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	public Double getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(Double totalIncome) {
		this.totalIncome = totalIncome;
	}

	public Double getTravelCost() {
		return travelCost;
	}

	public void setTravelCost(Double travelCost) {
		this.travelCost = travelCost;
	}

	public Double getCarCost() {
		return carCost;
	}

	public void setCarCost(Double carCost) {
		this.carCost = carCost;
	}

	public Double getTrainCost() {
		return trainCost;
	}

	public void setTrainCost(Double trainCost) {
		this.trainCost = trainCost;
	}

	public Double getShipCost() {
		return shipCost;
	}

	public void setShipCost(Double shipCost) {
		this.shipCost = shipCost;
	}

	public Double getAirCost() {
		return airCost;
	}

	public void setAirCost(Double airCost) {
		this.airCost = airCost;
	}

	public Double getHouseCost() {
		return houseCost;
	}

	public void setHouseCost(Double houseCost) {
		this.houseCost = houseCost;
	}

	public Double getEatCost() {
		return eatCost;
	}

	public void setEatCost(Double eatCost) {
		this.eatCost = eatCost;
	}

	public Double getTicketCost() {
		return ticketCost;
	}

	public void setTicketCost(Double ticketCost) {
		this.ticketCost = ticketCost;
	}

	public Double getInsuranceCost() {
		return insuranceCost;
	}

	public void setInsuranceCost(Double insuranceCost) {
		this.insuranceCost = insuranceCost;
	}

	public Double getAgentCost() {
		return agentCost;
	}

	public void setAgentCost(Double agentCost) {
		this.agentCost = agentCost;
	}

	public Double getVisiaCost() {
		return visiaCost;
	}

	public void setVisiaCost(Double visiaCost) {
		this.visiaCost = visiaCost;
	}

	public Double getAccompanyAndAllowance() {
		return accompanyAndAllowance;
	}

	public void setAccompanyAndAllowance(Double accompanyAndAllowance) {
		this.accompanyAndAllowance = accompanyAndAllowance;
	}

	public Double getOtherCost() {
		return otherCost;
	}

	public void setOtherCost(Double otherCost) {
		this.otherCost = otherCost;
	}

	public Double getComprehensiveCost() {
		return comprehensiveCost;
	}

	public void setComprehensiveCost(Double comprehensiveCost) {
		this.comprehensiveCost = comprehensiveCost;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public Double getPureIncome() {
		return pureIncome;
	}

	public void setPureIncome(Double pureIncome) {
		this.pureIncome = pureIncome;
	}

	public String getPureIncomeStr() {
		return pureIncomeStr;
	}

	public void setPureIncomeStr(String pureIncomeStr) {
		this.pureIncomeStr = pureIncomeStr;
	}

	public Integer getReceiptNum() {
		return receiptNum;
	}

	public void setReceiptNum(Integer receiptNum) {
		this.receiptNum = receiptNum;
	}

	public String getResponsiblePerson() {
		return responsiblePerson;
	}

	public void setResponsiblePerson(String responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}

	public String getFgLeader() {
		return fgLeader;
	}

	public void setFgLeader(String fgLeader) {
		this.fgLeader = fgLeader;
	}

	public String getFinanceSupervisor() {
		return financeSupervisor;
	}

	public void setFinanceSupervisor(String financeSupervisor) {
		this.financeSupervisor = financeSupervisor;
	}

	public String getCashier() {
		return cashier;
	}

	public void setCashier(String cashier) {
		this.cashier = cashier;
	}

	public String getLister() {
		return lister;
	}

	public void setLister(String lister) {
		this.lister = lister;
	}

	public Long getResponsiblePersonId() {
		return responsiblePersonId;
	}

	public void setResponsiblePersonId(Long responsiblePersonId) {
		this.responsiblePersonId = responsiblePersonId;
	}

	public Long getFgLeaderId() {
		return fgLeaderId;
	}

	public void setFgLeaderId(Long fgLeaderId) {
		this.fgLeaderId = fgLeaderId;
	}

	public Long getFinanceSupervisorId() {
		return financeSupervisorId;
	}

	public void setFinanceSupervisorId(Long financeSupervisorId) {
		this.financeSupervisorId = financeSupervisorId;
	}

	public Long getCashierId() {
		return cashierId;
	}

	public void setCashierId(Long cashierId) {
		this.cashierId = cashierId;
	}

	public Long getListerId() {
		return listerId;
	}

	public void setListerId(Long listerId) {
		this.listerId = listerId;
	}

	public String getCheckPersonIdList() {
		return checkPersonIdList;
	}

	public void setCheckPersonIdList(String checkPersonIdList) {
		this.checkPersonIdList = checkPersonIdList;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
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

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getManagerTree() {
		return managerTree;
	}

	public void setManagerTree(String managerTree) {
		this.managerTree = managerTree;
	}

	public String getCostNumber() {
		return costNumber;
	}

	public void setCostNumber(String costNumber) {
		this.costNumber = costNumber;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

}
