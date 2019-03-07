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
 * 价格日历表数据储存类
 * @author Max
 *
 */
@Entity
public class TdTimePrice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	// 商品Id
	@Column
	private Long goodsId;
	
	// 商品类型主Id
	@Column
	private Long cateId;
	
	// 商品类型
    @Column
    private Long categoryId;
	
	// 类型树
	 @Column
    private String categoryIdTree;
	
	// 时间
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
	private Date time;
	
	// 价格
	@Column(scale=2)
	private Double price;
	
	// 儿童价
	@Column(scale=2)
	private Double childPrice;
	
	// 商品标题
	@Column
	private String goodsTitle;
	
	// 区间表ID
	@Column
	private Long priceId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getChildPrice() {
		return childPrice;
	}

	public void setChildPrice(Double childPrice) {
		this.childPrice = childPrice;
	}

	public String getGoodsTitle() {
		return goodsTitle;
	}

	public void setGoodsTitle(String goodsTitle) {
		this.goodsTitle = goodsTitle;
	}

	public Long getCateId() {
		return cateId;
	}

	public void setCateId(Long cateId) {
		this.cateId = cateId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryIdTree() {
		return categoryIdTree;
	}

	public void setCategoryIdTree(String categoryIdTree) {
		this.categoryIdTree = categoryIdTree;
	}

	public Long getPriceId() {
		return priceId;
	}

	public void setPriceId(Long priceId) {
		this.priceId = priceId;
	}
	
	
}
