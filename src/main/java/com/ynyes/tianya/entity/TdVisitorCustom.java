package com.ynyes.tianya.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * 客户管理实体类
 * 
 * @author gl
 *
 */

@Entity
public class TdVisitorCustom {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// 我想在...
	// 几月
	@Column
	private String month;

	// 去哪里
	@Column
	private String place;

	// 玩几天
	@Column
	private String totalDays;

	// 几个人
	@Column
	private String totalPersons;

	// 电话
	@Column
	private String telephone;

	// 管理员备注
	private String note;

	// 定制时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date customTime;

	@Column
	private String content;

	@Column
	Boolean areadyCheck;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(String totalDays) {
		this.totalDays = totalDays;
	}

	public String getTotalPersons() {
		return totalPersons;
	}

	public void setTotalPersons(String totalPersons) {
		this.totalPersons = totalPersons;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getCustomTime() {
		return customTime;
	}

	public void setCustomTime(Date customTime) {
		this.customTime = customTime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getAreadyCheck() {
		return areadyCheck;
	}

	public void setAreadyCheck(Boolean areadyCheck) {
		this.areadyCheck = areadyCheck;
	}

}
