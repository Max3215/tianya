package com.ynyes.tianya.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 天涯私家定制
 * 
 * @author lulu
 */
@Entity
public class TdDemand {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// 提交时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date time;

	// 定制类型 0表示普通定制 1表示邮轮定制
	@Column
	private Long statusId;

	// 是否已回复
	// 注意：由于私家定制取消回复功能，故将此字段当做是否交易成功
	@Column
	private Boolean isReplied;

	// 回复
	@Column
	private String reply;

	// 回复时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date replyTime;
	// 目的地
	@Column
	private String reachPort;
	// 总人数
	@Column
	private String totalPeople;
	// 出发时间
	@Column
	private String groupSaleStartTime;
	// 返程时间
	@Column
	private String groupSaleStopTime;

	// 用户名
	@Column
	private String name;

	// 邮箱
	@Column
	private String email;
	// 出发方式
	@Column
	private String way;
	// 酒店星级
	@Column
	private String hotel;
	// 预支付
	@Column
	private String money;
	// 备注
	@Column
	private String remark;

	// 联系方式
	@Column
	private String mobile;

	@Column
	private String privateType;

	@Column
	private Integer status=1;

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public Boolean getIsReplied() {
		return isReplied;
	}

	public void setIsReplied(Boolean isReplied) {
		this.isReplied = isReplied;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	public String getReachPort() {
		return reachPort;
	}

	public void setReachPort(String reachPort) {
		this.reachPort = reachPort;
	}

	public String getTotalPeople() {
		return totalPeople;
	}

	public void setTotalPeople(String totalPeople) {
		this.totalPeople = totalPeople;
	}

	public String getGroupSaleStartTime() {
		return groupSaleStartTime;
	}

	public void setGroupSaleStartTime(String groupSaleStartTime) {
		this.groupSaleStartTime = groupSaleStartTime;
	}

	public String getGroupSaleStopTime() {
		return groupSaleStopTime;
	}

	public void setGroupSaleStopTime(String groupSaleStopTime) {
		this.groupSaleStopTime = groupSaleStopTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWay() {
		return way;
	}

	public void setWay(String way) {
		this.way = way;
	}

	public String getHotel() {
		return hotel;
	}

	public void setHotel(String hotel) {
		this.hotel = hotel;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPrivateType() {
		return privateType;
	}

	public void setPrivateType(String privateType) {
		this.privateType = privateType;
	}

}
