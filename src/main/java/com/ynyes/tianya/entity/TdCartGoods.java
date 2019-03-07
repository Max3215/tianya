package com.ynyes.tianya.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


/**
 * 购物车
 * 
 * @author Sharon
 *
 */

@Entity
public class TdCartGoods {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    // 用户
    @Column
    private String username;
    
    // 商品ID
    @Column
    private Long goodsId;
    
    // 商品标题
    private String goodsTitle;
    
    // 商品封面
    private String goodsCoverImageUri;
    
    // 商品数量
    private Long quantity;
    
    // 成交价
    @Column
    private Double price;
    
    // 是否选中，选中的商品将进行结算
    @Column
    private Boolean isSelected;
    
    // 是否是登陆用户
    @Column
    private Boolean isLoggedIn;
    
    // 商品附加信息
    @OneToMany
    @JoinColumn(name="cartId")
    private List<TdCartGoodsPs> psList;
    
    // 出发时间
    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date leaveDate;

    // 到达港口
    @Column
    private String reachPort;
    
    // 途经港口
    @Column
    private String passPort;
    
    // 邮轮公司
    @Column
    private String shipCompany;
    
    // 出发港口
    @Column
    private String leavePort;
    
    // 返航时间
    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date turnDate;
    
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsTitle() {
        return goodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }

    public String getGoodsCoverImageUri() {
        return goodsCoverImageUri;
    }

    public void setGoodsCoverImageUri(String goodsCoverImageUri) {
        this.goodsCoverImageUri = goodsCoverImageUri;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

    public Boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(Boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

	public List<TdCartGoodsPs> getPsList() {
		return psList;
	}

	public void setPsList(List<TdCartGoodsPs> psList) {
		this.psList = psList;
	}

	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	public String getReachPort() {
		return reachPort;
	}

	public void setReachPort(String reachPort) {
		this.reachPort = reachPort;
	}

	public String getPassPort() {
		return passPort;
	}

	public void setPassPort(String passPort) {
		this.passPort = passPort;
	}

	public String getShipCompany() {
		return shipCompany;
	}

	public void setShipCompany(String shipCompany) {
		this.shipCompany = shipCompany;
	}

	public String getLeavePort() {
		return leavePort;
	}

	public void setLeavePort(String leavePort) {
		this.leavePort = leavePort;
	}

	public Date getTurnDate() {
		return turnDate;
	}

	public void setTurnDate(Date turnDate) {
		this.turnDate = turnDate;
	}
    
    
}
