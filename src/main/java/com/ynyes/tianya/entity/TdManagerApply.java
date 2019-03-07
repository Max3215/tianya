package com.ynyes.tianya.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 员工申请
 * 
 * @author gl
 *
 */
@Entity
public class TdManagerApply {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// 申请人Id
	@Column
	private Long managerId;

	// 申请人姓名
	@Column
	private String managerName;

	// 申请题目
	@Column
	private String title;

	// 申请类容
	@Column
	private String content;

	// 申请日期
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date applyTime;

	// 是否被拒绝
	@Column
	private Boolean isRefuse = false;

	// 拒绝理由
	@Column
	private String refuseReason;

	// 审批人Id字符串:格式为: id1_id2_id3,其中idn的前面是"+"号,表示该审批人已通过,若为"-"号,则表示该审批人还未通过审核
	@Column
	private String checkManagerStr;

	@Column
	private String state = "审核中";

	// 0:普通申请 1:商品上架申请2:商品下架申请
	@Column
	private Long applyType;

	// 上架申请的商品Id
//	@Column
//	private Long unsaleGoodsId;
	@Column
	private String unsaleGoodsId;
	
	// 下架申请的商品Id
//	@Column
//	private Long saleGoodsId;
	@Column
	private String saleGoodsId;

	// 上架申请商品链接
	@Column
	private String unsaleGoodsUrl;

	// 下架申请商品链接
	@Column
	private String saleGoodsUrl;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public String getCheckManagerStr() {
		return checkManagerStr;
	}

	public void setCheckManagerStr(String checkManagerStr) {
		this.checkManagerStr = checkManagerStr;
	}

	public Boolean getIsRefuse() {
		return isRefuse;
	}

	public void setIsRefuse(Boolean isRefuse) {
		this.isRefuse = isRefuse;
	}

	public String getRefuseReason() {
		return refuseReason;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getApplyType() {
		return applyType;
	}

	public void setApplyType(Long applyType) {
		this.applyType = applyType;
	}


	public String getUnsaleGoodsId() {
		return unsaleGoodsId;
	}

	public void setUnsaleGoodsId(String unsaleGoodsId) {
		this.unsaleGoodsId = unsaleGoodsId;
	}


	public String getSaleGoodsId() {
		return saleGoodsId;
	}

	public void setSaleGoodsId(String saleGoodsId) {
		this.saleGoodsId = saleGoodsId;
	}

	public String getUnsaleGoodsUrl() {
		return unsaleGoodsUrl;
	}

	public void setUnsaleGoodsUrl(String unsaleGoodsUrl) {
		this.unsaleGoodsUrl = unsaleGoodsUrl;
	}

	public String getSaleGoodsUrl() {
		return saleGoodsUrl;
	}

	public void setSaleGoodsUrl(String saleGoodsUrl) {
		this.saleGoodsUrl = saleGoodsUrl;
	}

}
