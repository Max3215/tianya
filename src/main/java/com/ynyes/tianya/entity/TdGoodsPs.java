package com.ynyes.tianya.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * 商品附属信息
 * 
 * @author Sharon
 *
 */

@Entity
public class TdGoodsPs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    // 类别
    @Column
    private String type;
    
    // 名称
    @Column
    private String title;
    
    // 副标题
    @Column
    private String subTitle;
    
    // 副标题
    @Column
    private String subSubTitle;
    
    // 排序号
    @Column
    private Long sortId;

    // 价格1
    @Column
    private Double price1;
    
    // 价格2
    @Column
    private Double price2;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getSubSubTitle() {
		return subSubTitle;
	}

	public void setSubSubTitle(String subSubTitle) {
		this.subSubTitle = subSubTitle;
	}

	public Long getSortId() {
		return sortId;
	}

	public void setSortId(Long sortId) {
		this.sortId = sortId;
	}

	public Double getPrice1() {
		return price1;
	}

	public void setPrice1(Double price1) {
		this.price1 = price1;
	}

	public Double getPrice2() {
		return price2;
	}

	public void setPrice2(Double price2) {
		this.price2 = price2;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
}
