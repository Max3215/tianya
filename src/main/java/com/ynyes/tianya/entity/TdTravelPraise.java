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

@Entity
public class TdTravelPraise {
	//主键
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//用户名
	@Column
	private String name;
	
	//点赞时间
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date praiseDate;
	
	//游记攻略Id
	@Column
	private Long travelNotesId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getPraiseDate() {
		return praiseDate;
	}

	public void setPraiseDate(Date praiseDate) {
		this.praiseDate = praiseDate;
	}

	public Long getTravelNotesId() {
		return travelNotesId;
	}

	public void setTravelNotesId(Long travelNotesId) {
		this.travelNotesId = travelNotesId;
	}
	
	
}
