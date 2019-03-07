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
 * 内部职工表
 * @author Max
 *
 */
@Entity
public class TdStaff {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	 // 公司ID
	@Column
	private Integer companyId;
	
	// 公司
	@Column
	private String company;
	
	// 职工姓名
	@Column( unique=true)
	private String username;
	
	// 性别
	@Column
	private Integer sex;
	
	// 入职时间
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
	private Date entryTime;
	
	// 工龄
	@Column
	private Integer workYear;
	
	// 公休假天数
	@Column
	private Long leaveDay;
	
	// 优惠金额
	@Column(scale = 2 )
	private Double discount;
	
	// 可使用优惠金额
	@Column(scale = 2)
	private Double surDiscount;
	
	// 结余金额
	@Column(scale = 2)
	private Double surplus;
	
	// 是否结算
	@Column
	private Boolean isClose;
	
	// 备注
	@Column
	private String remark;
	
	// 出行时间
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
	private Date travelTime;
	
	// 结算时间
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
	private Date accountTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(int companyId) {
//		this.company = companyId;
		switch (companyId) {
		case 1:
			this.company ="重庆交通运业有限责任公司";
			break;
		case 2:
			this.company ="重庆交通运业有限责任公司渝北双凤桥汽车站";
			break;
		case 3:
			this.company ="重庆交通运业有限责任公司重庆汽车站";
			break;
		case 4:
			this.company ="重庆交通运业有限责任公司陈家坪汽车站";
			break;
		case 5:
			this.company ="重庆交通运业有限责任公司龙头寺汽车站";
			break;
		case 6:
			this.company ="重庆交通运业有限责任公司富丽大酒店";
			break;
		case 7:
			this.company ="重庆交通运业有限责任公司站务中心汽车北站";
			break;
		case 8:
			this.company ="重庆交通运业有限责任公司站务中心南坪汽车站";
			break;
		case 9:
			this.company ="重庆市公路客运联网售票中心有限公司";
			break;
		case 10:
			this.company ="重庆交通运业有限责任公司站务中心长途汽车站";
			break;
		case 11:
			this.company ="重庆迅为四公里交通换乘枢纽有限公司";
			break;
		case 12:
			this.company ="重庆天涯国际旅行社有限公司";
			break;
		case 13:
			this.company ="重庆交通运业有限责任公司商旅服务分公司";
			break;
		case 14:
			this.company ="重庆交通运业有限责任公司稽查大队";
			break;
		case 15:
			this.company ="重庆交通运业有限责任公司交运快递";
			break;
		case 16:
			this.company ="重庆交通运业有限责任公司富苑宾馆";
			break;
		case 18:
			this.company ="重庆交通运业有限责任公司巴南龙洲湾汽车站";
			break;
		default:
			break;
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}

	public Integer getWorkYear() {
		return workYear;
	}

	public void setWorkYear(Integer workYear) {
		this.workYear = workYear;
	}

	public Long getLeaveDay() {
		return leaveDay;
	}

	public void setLeaveDay(Long leaveDay) {
		this.leaveDay = leaveDay;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	
	public Double getSurDiscount() {
		return surDiscount;
	}

	public void setSurDiscount(Double surDiscount) {
		this.surDiscount = surDiscount;
	}

	public Double getSurplus() {
		return surplus;
	}

	public void setSurplus(Double surplus) {
		this.surplus = surplus;
	}

	public Boolean getIsClose() {
		return isClose;
	}

	public void setIsClose(Boolean isClose) {
		this.isClose = isClose;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getTravelTime() {
		return travelTime;
	}

	public void setTravelTime(Date travelTime) {
		this.travelTime = travelTime;
	}

	public Date getAccountTime() {
		return accountTime;
	}

	public void setAccountTime(Date accountTime) {
		this.accountTime = accountTime;
	}


	
}
