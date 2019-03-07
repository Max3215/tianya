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
 * 订单
 *
 * 记录了订单详情
 * 
 * @author Sharon
 *
 */

@Entity
public class TdOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// 订单号
	@Column(unique = true)
	private String orderNumber;

	// 订单商品
	@OneToMany
	@JoinColumn(name = "tdOrderId")
	private List<TdOrderGoods> orderGoodsList;
	// 订单旅客信息
	@OneToMany
	@JoinColumn(name = "tdOrderId")
	private List<TdOrderVisitor> orderVisitorList;

	// 临租押金
	@Column
	private Double includePrice;

	@Column
	private String carCode;
	@Column
	private String carType;
	@Column
	private String postalCode;
	@Column
	private String shopTitle;
	@Column
	private String invoiceTitle;
	@Column
	private String invoiceContent;
	@Column
	private String invoiceType;
	// 签证资料路劲
	@Column
	private String dataUpload;

	// 收货地址
	@Column
	private String shippingAddress;

	// 收货人
	@Column
	private String shippingName;

	// 收货电话
	@Column
	private String shippingPhone;

	// 线下同盟店
	@Column
	private Long shopId;

	@Column
	private Long ertongshu;

	// 同盟店所获返利
	private Double rebate;

	// 同盟店订单收入
	private Double orderIncome;

	// 支付方式
	@Column
	private Long payTypeId;

	// 支付方式名称
	@Column
	private String payTypeTitle;

	// 支付方式手续费
	@Column(scale = 2)
	private Double payTypeFee;

	// 配送方式
	@Column
	private Long deliverTypeId;

	// 配送方式名称
	@Column
	private String deliverTypeTitle;

	// 配送方式名称
	@Column(scale = 2)
	private Double deliverTypeFee;

	// 订单类型 （国类游或者国外游）
	@Column
	private String orderType;

	// 快递公司
	@Column
	private String expressCampany;

	// 快递单号
	@Column
	private String expressNumber;

	// 快递详情查询接口
	@Column
	private String expressUri;

	// 用户留言备注
	@Column
	private String userRemarkInfo;

	// 后台备注
	@Column
	private String remarkInfo;

	// 是否索要发票
	@Column
	private Boolean isNeedInvoice;

	// 取车时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date appointmentTime;
	// 还车时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date returnTime;

	// 下单时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date orderTime;

	// 取消时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date cancelTime;

	// 确认时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date checkTime;

	// 付款时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date payTime;

	// 付尾款时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date payLeftTime;

	// 配送时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date deliveryTime;

	// 服务时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date serviceTime;

	// 收货时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date receiveTime;

	// 评价时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date commentTime;

	// 完成时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date finishTime;

	// 订单状态 1:待确认 2:待付款 3:待服务 4:待评价 5: 已完成 6: 已取消
	@Column
	private Long statusId;

	// 订单类型 0：邮轮俱乐部订单1：目的地订单 2：主题活动订单 3：签证订单 4：汽车租赁订单 5：旅游直通车订单 6：特产商城订单 7:门票订单
	@Column
	private Long typeId;

	// 订单取消原因
	@Column
	private String cancelReason;

	// 用户
	@Column
	private String username;

	// 发货时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date sendTime;

	// 配货人
	@Column
	private String deliveryPerson;

	// 配送人
	@Column
	private String distributionPerson;

	// 收款人
	@Column
	private String moneyReceivePerson;

	// 验证码
	@Column
	private String smscode;

	// 商品总金额
	@Column(scale = 2)
	private Double totalGoodsPrice;

	// 订单总金额
	@Column(scale = 2)
	private Double totalPrice;

	// 修改总金额备注
	@Column
	private String totalPriceChangeInfo;

	// 修改支付手续费备注
	@Column
	private String payTypePriceChangeInfo;

	// 修改配送手续费备注
	@Column
	private String deliverTypePriceChangeInfo;

	// 订单尾款总金额，有些订单需付尾款
	@Column(scale = 2)
	private Double totalLeftPrice;

	// 排序号
	@Column
	private Long sortId;

	// 使用积分数
	@Column
	private Long pointUse;

	// 是否已返粮草
	private Boolean isReturnPoints;

	// 可获取积分
	@Column
	private Long points;

	// 使用优惠券抵用额度
	@Column
	private Double couponUse;

	@Column
	private String couponTitle;

	// 是否在线付款
	@Column
	private Boolean isOnlinePay;

	// 是否申请售后
	@Column
	private Boolean isReturn;

	// 商城收入
	@Column
	private Double platformService;

	// 培训费
	@Column
	private Double trainService;

	// 商品id
	@Column
	private Long goodsId;

	// 商品名称
	@Column
	private String goodsTitle;

	// 出发时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
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
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	private Date turnDate;

	// 订单证件信息
	@OneToMany
	@JoinColumn(name = "tdOrderId")
	private List<TdOrderVisitor> visitorList;

	public List<TdOrderVisitor> getVisitorList() {
		return visitorList;
	}

	public void setVisitorList(List<TdOrderVisitor> visitorList) {
		this.visitorList = visitorList;
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

	public String getSmscode() {
		return smscode;
	}

	public void setSmscode(String smscode) {
		this.smscode = smscode;
	}

	public String getCouponTitle() {
		return couponTitle;
	}

	public void setCouponTitle(String couponTitle) {
		this.couponTitle = couponTitle;
	}

	public Boolean getIsReturnPoints() {
		return isReturnPoints;
	}

	public void setIsReturnPoints(Boolean isReturnPoints) {
		this.isReturnPoints = isReturnPoints;
	}

	public Boolean getIsReturn() {
		return isReturn;
	}

	public void setIsReturn(Boolean isReturn) {
		this.isReturn = isReturn;
	}

	public Double getPlatformService() {
		return platformService;
	}

	public void setPlatformService(Double platformService) {
		this.platformService = platformService;
	}

	public Double getTrainService() {
		return trainService;
	}

	public void setTrainService(Double trainService) {
		this.trainService = trainService;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getCouponUse() {
		return couponUse;
	}

	public void setCouponUse(Double couponUse) {
		this.couponUse = couponUse;
	}

	public Double getOrderIncome() {
		return orderIncome;
	}

	public void setOrderIncome(Double orderIncome) {
		this.orderIncome = orderIncome;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public List<TdOrderGoods> getOrderGoodsList() {
		return orderGoodsList;
	}

	public void setOrderGoodsList(List<TdOrderGoods> orderGoodsList) {
		this.orderGoodsList = orderGoodsList;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getShippingName() {
		return shippingName;
	}

	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}

	public String getShippingPhone() {
		return shippingPhone;
	}

	public void setShippingPhone(String shippingPhone) {
		this.shippingPhone = shippingPhone;
	}

	public Long getPayTypeId() {
		return payTypeId;
	}

	public void setPayTypeId(Long payTypeId) {
		this.payTypeId = payTypeId;
	}

	public String getPayTypeTitle() {
		return payTypeTitle;
	}

	public void setPayTypeTitle(String payTypeTitle) {
		this.payTypeTitle = payTypeTitle;
	}

	public Double getPayTypeFee() {
		return payTypeFee;
	}

	public void setPayTypeFee(Double payTypeFee) {
		this.payTypeFee = payTypeFee;
	}

	public String getDeliverTypeTitle() {
		return deliverTypeTitle;
	}

	public void setDeliverTypeTitle(String deliverTypeTitle) {
		this.deliverTypeTitle = deliverTypeTitle;
	}

	public Long getDeliverTypeId() {
		return deliverTypeId;
	}

	public void setDeliverTypeId(Long deliverTypeId) {
		this.deliverTypeId = deliverTypeId;
	}

	public Double getDeliverTypeFee() {
		return deliverTypeFee;
	}

	public void setDeliverTypeFee(Double deliverTypeFee) {
		this.deliverTypeFee = deliverTypeFee;
	}

	public String getUserRemarkInfo() {
		return userRemarkInfo;
	}

	public void setUserRemarkInfo(String userRemarkInfo) {
		this.userRemarkInfo = userRemarkInfo;
	}

	public String getRemarkInfo() {
		return remarkInfo;
	}

	public void setRemarkInfo(String remarkInfo) {
		this.remarkInfo = remarkInfo;
	}

	public Boolean getIsNeedInvoice() {
		return isNeedInvoice;
	}

	public void setIsNeedInvoice(Boolean isNeedInvoice) {
		this.isNeedInvoice = isNeedInvoice;
	}

	public String getInvoiceTitle() {
		return invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

	public String getInvoiceContent() {
		return invoiceContent;
	}

	public void setInvoiceContent(String invoiceContent) {
		this.invoiceContent = invoiceContent;
	}

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getExpressCampany() {
		return expressCampany;
	}

	public void setExpressCampany(String expressCampany) {
		this.expressCampany = expressCampany;
	}

	public String getExpressNumber() {
		return expressNumber;
	}

	public void setExpressNumber(String expressNumber) {
		this.expressNumber = expressNumber;
	}

	public String getExpressUri() {
		return expressUri;
	}

	public void setExpressUri(String expressUri) {
		this.expressUri = expressUri;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getDeliveryPerson() {
		return deliveryPerson;
	}

	public void setDeliveryPerson(String deliveryPerson) {
		this.deliveryPerson = deliveryPerson;
	}

	public String getDistributionPerson() {
		return distributionPerson;
	}

	public void setDistributionPerson(String distributionPerson) {
		this.distributionPerson = distributionPerson;
	}

	public String getMoneyReceivePerson() {
		return moneyReceivePerson;
	}

	public void setMoneyReceivePerson(String moneyReceivePerson) {
		this.moneyReceivePerson = moneyReceivePerson;
	}

	public Double getTotalGoodsPrice() {
		return totalGoodsPrice;
	}

	public void setTotalGoodsPrice(Double totalGoodsPrice) {
		this.totalGoodsPrice = totalGoodsPrice;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getSortId() {
		return sortId;
	}

	public void setSortId(Long sortId) {
		this.sortId = sortId;
	}

	public Long getPoints() {
		return points;
	}

	public void setPoints(Long points) {
		this.points = points;
	}

	public Boolean getIsOnlinePay() {
		return isOnlinePay;
	}

	public void setIsOnlinePay(Boolean isOnlinePay) {
		this.isOnlinePay = isOnlinePay;
	}

	public Date getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}

	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	public Double getRebate() {
		return rebate;
	}

	public void setRebate(Double rebate) {
		this.rebate = rebate;
	}

	public Long getPointUse() {
		return pointUse;
	}

	public void setPointUse(Long pointUse) {
		this.pointUse = pointUse;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getShopTitle() {
		return shopTitle;
	}

	public void setShopTitle(String shopTitle) {
		this.shopTitle = shopTitle;
	}

	public Date getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(Date appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public String getCarCode() {
		return carCode;
	}

	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public Double getTotalLeftPrice() {
		return totalLeftPrice;
	}

	public void setTotalLeftPrice(Double totalLeftPrice) {
		this.totalLeftPrice = totalLeftPrice;
	}

	public Date getPayLeftTime() {
		return payLeftTime;
	}

	public void setPayLeftTime(Date payLeftTime) {
		this.payLeftTime = payLeftTime;
	}

	public Date getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(Date serviceTime) {
		this.serviceTime = serviceTime;
	}

	public String getTotalPriceChangeInfo() {
		return totalPriceChangeInfo;
	}

	public void setTotalPriceChangeInfo(String totalPriceChangeInfo) {
		this.totalPriceChangeInfo = totalPriceChangeInfo;
	}

	public String getPayTypePriceChangeInfo() {
		return payTypePriceChangeInfo;
	}

	public void setPayTypePriceChangeInfo(String payTypePriceChangeInfo) {
		this.payTypePriceChangeInfo = payTypePriceChangeInfo;
	}

	public String getDeliverTypePriceChangeInfo() {
		return deliverTypePriceChangeInfo;
	}

	public void setDeliverTypePriceChangeInfo(String deliverTypePriceChangeInfo) {
		this.deliverTypePriceChangeInfo = deliverTypePriceChangeInfo;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getDataUpload() {
		return dataUpload;
	}

	public void setDataUpload(String dataUpload) {
		this.dataUpload = dataUpload;
	}

	public Date getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	public List<TdOrderVisitor> getOrderVisitorList() {
		return orderVisitorList;
	}

	public void setOrderVisitorList(List<TdOrderVisitor> orderVisitorList) {
		this.orderVisitorList = orderVisitorList;
	}

	public Long getErtongshu() {
		return ertongshu;
	}

	public void setErtongshu(Long ertongshu) {
		this.ertongshu = ertongshu;
	}

	public Double getIncludePrice() {
		return includePrice;
	}

	public void setIncludePrice(Double includePrice) {
		this.includePrice = includePrice;
	}

}
