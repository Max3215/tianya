package com.ynyes.tianya.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * @author Max
 *
 */
@Entity
public class TdOrderVisitor {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String visitorName;
	
	@Column
	private String sex;
	//证件类型
	@Column
	private String certificateType;
	//证件号
	@Column
	private String certificateCardCode;
	
	@Column
	private String telephone;
	
	@Column
	private Long tdOrderId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVisitorName() {
		return visitorName;
	}

	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}

	public String getCertificateCardCode() {
		return certificateCardCode;
	}

	public void setCertificateCardCode(String certificateCardCode) {
		this.certificateCardCode = certificateCardCode;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Long getTdOrderId() {
		return tdOrderId;
	}

	public void setTdOrderId(Long tdOrderId) {
		this.tdOrderId = tdOrderId;
	}
}
