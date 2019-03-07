package com.ynyes.tianya.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TdCartGoodsPs {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	//服务Id
	@Column
	private Long goodsPsId;
	
	// 服务
	@Column
	private String title;
	
	// 价格1（成人价）
	@Column(scale=2)
	private Double price1;
	
	// 数量1
	@Column
	private Long quantity1;
	
	// 价格2（儿童价）
	@Column(scale=2)
	private Double price2;
	
	// 数量2
	@Column
	private Long quantity2;
	
	// 是否选择
	@Column
	private Boolean isSelect;
	
	// 类型  1 房型   2 服务
	@Column
	private Long type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGoodsPsId() {
		return goodsPsId;
	}

	public void setGoodsPsId(Long goodsPsId) {
		this.goodsPsId = goodsPsId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getPrice1() {
		return price1;
	}

	public void setPrice1(Double price1) {
		this.price1 = price1;
	}

	public Long getQuantity1() {
		return quantity1;
	}

	public void setQuantity1(Long quantity1) {
		this.quantity1 = quantity1;
	}

	public Double getPrice2() {
		return price2;
	}

	public void setPrice2(Double price2) {
		this.price2 = price2;
	}

	public Long getQuantity2() {
		return quantity2;
	}

	public void setQuantity2(Long quantity2) {
		this.quantity2 = quantity2;
	}

	public Boolean getIsSelect() {
		return isSelect;
	}

	public void setIsSelect(Boolean isSelect) {
		this.isSelect = isSelect;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}
	
	
	
	
	
}
