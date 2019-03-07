package com.ynyes.tianya.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TdBargainSetting {
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private Long id;
	 
	 // 1000以上剩余
	 @Column
	 private Long leftNumber_1000;
	 
	 // 500以上剩余
	 @Column
	 private Long leftNumber_500;
	 
	 // 0元以上
	 @Column
	 private Long leftNumber_0;
	 	 
	 // 起始价格
	 @Column
	 private Double startPrice;
	 
	 // 参与人数
	 @Column
	 private Long totalParticipant;
	 
	 // 总共销售数量
	 @Column
	 private Long totalSoldNumber;

	 // 总共剩余
	 @Column
	 private Long totalLeftNumber;
	 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(Double startPrice) {
		this.startPrice = startPrice;
	}

	public Long getTotalParticipant() {
		return totalParticipant;
	}

	public void setTotalParticipant(Long totalParticipant) {
		this.totalParticipant = totalParticipant;
	}

	public Long getTotalSoldNumber() {
		return totalSoldNumber;
	}

	public void setTotalSoldNumber(Long totalSoldNumber) {
		this.totalSoldNumber = totalSoldNumber;
	}

	public Long getLeftNumber_1000() {
		return leftNumber_1000;
	}

	public void setLeftNumber_1000(Long leftNumber_1000) {
		this.leftNumber_1000 = leftNumber_1000;
	}

	public Long getLeftNumber_500() {
		return leftNumber_500;
	}

	public void setLeftNumber_500(Long leftNumber_500) {
		this.leftNumber_500 = leftNumber_500;
	}

	public Long getLeftNumber_0() {
		return leftNumber_0;
	}

	public void setLeftNumber_0(Long leftNumber_0) {
		this.leftNumber_0 = leftNumber_0;
	}

	public Long getTotalLeftNumber() {
		return totalLeftNumber;
	}

	public void setTotalLeftNumber(Long totalLeftNumber) {
		this.totalLeftNumber = totalLeftNumber;
	}
	 
	 
}
