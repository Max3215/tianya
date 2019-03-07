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
 * 商品信息表
 * 
 * @author Sharon
 *
 */

@Entity
public class TdGoods {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// 产品Id
	@Column
	private Long productId;

	// 汽车租赁中，是否自驾：0表示自驾，1表示代驾，2表示可供用户选择自驾或代驾。
	@Column
	private Long driveId;

	// 商品名称
	@Column
	private String name;

	// 商品标题
	@Column
	private String title;

	// 副标题
	@Column
	private String subTitle;

	// 封面图片
	@Column
	private String coverImageUri;

	// 签证资料上传
	@Column
	private String visaDataUri;

	// 行程介绍图片
	@Column
	private String xcImageUri;

	// 封面图片宽度
	@Column
	private Double coverImageWidth;

	// 封面图片高度
	@Column
	private Double coverImageHeight;

	// 视频
	@Column
	private String videoUri;

	// 轮播展示图片，多张图片以,隔开
	@Column
	private String showPictures;

	// 到达港口
	@Column
	private String reachPort;

	// 评价平均分
	@Column
	private Double averagePoints;

	// 途经港口
	@Column
	private String passPort;

	// 邮轮公司
	@Column
	private String shipCompany;

	// 出发港口
	@Column
	private String leavePort;

	// 配送区域
	@Column
	private String deliveryArea;

	// 邮轮介绍
	@Column
	private String shipDescription;

	// 行程介绍
	@Column
	private String tripDescription;

	// 费用说明
	@Column
	private String feeDescription;

	// 签证签注
	@Column
	private String visaDescription;

	// 预定须知
	@Column
	private String bookDescription;

	// 是否上架
	@Column
	private Boolean isOnSale;

	// 汽车租赁那块的车辆类型：true表示小汽车，false表示客车
	@Column
	private Boolean carType;

	// 是否首页推荐
	@Column
	private Boolean isRecommendIndex;

	// 是否分类推荐
	@Column
	private Boolean isRecommendType;

	// 是否热销
	@Column
	private Boolean isHot;

	// 是否新品
	@Column
	private Boolean isNew;

	// 是否特价
	@Column
	private Boolean isSpecialPrice;

	// 库存递减时机 分为下订单递减、支付完成递减、发货递减等
	@Column
	private Long numberDecType;

	// 商品类型
	@Column
	private Long categoryId;

	// 商品类型名称
	@Column
	private String categoryTitle;

	// 商品所有类型
	@Column
	private String categoryIdTree;

	// 商品参数
	@OneToMany
	@JoinColumn(name = "goodsId")
	private List<TdGoodsParameter> paramList;

	// 商品附加信息
	@OneToMany
	@JoinColumn(name = "goodsId")
	private List<TdGoodsPs> psList;

	/**
	 * lulu
	 */
	// 邮轮、目的地、主题的行程信息
	@OneToMany
	@JoinColumn(name = "goodsId")
	private List<TdGoodsXc> xcList;

	// 参数值，用于搜索
	@Column
	private String paramValueCollect;

	// 筛选参数一值
	@Column
	private String selectOneValue;

	// 筛选参数二值
	@Column
	private String selectTwoValue;

	// 筛选参数三值
	@Column
	private String selectThreeValue;

	// 销售方式
	@Column
	private String saleType;

	// 成本价
	@Column(scale = 2)
	private Double costPrice;

	// 市场价
	@Column(scale = 2)
	private Double marketPrice;

	// 销售价
	@Column(nullable = false, scale = 2)
	private Double salePrice;
	// 儿童价
	@Column(nullable = false, scale = 2)
	private Double salePrice1;

	// 出厂价
	@Column(scale = 2)
	private Double outFactoryPrice;

	// 包含价值，销售靓号时会包含话费
	@Column(scale = 2)
	private Double includePrice;

	// 工时费
	@Column
	private Double installationPrice;

	@Column
	private Double otherPrice;

	// 站点ID
	@Column
	private Long siteId;

	// 站点名
	@Column
	private String siteTitle;

	// 仓库ID
	@Column
	private Long warehouseId;

	// 仓库名
	@Column
	private String warehouseTitle;

	// 供应商ID
	@Column
	private Long providerId;

	// 供应商名
	@Column
	private String providerTitle;

	// 库存数量
	@Column
	private Long leftNumber;

	// 该版本的已售数量
	@Column
	private Long soldNumber;

	// 商品价格单位
	@Column
	private String priceUnit;

	// 上架时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date onSaleTime;

	// 最近修改改时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedTime;

	// 创建日期
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	// 商品标签
	@Column
	private String tags;

	// 赠品
	@OneToMany
	@JoinColumn(name = "ownerGoodsId")
	private List<TdGoodsGift> giftList;

	// 赠品数量
	@Column
	private Integer totalGift;

	// 商品组合
	@OneToMany
	@JoinColumn(name = "ownerGoodsId")
	private List<TdGoodsCombination> combList;

	// 组合数量
	private Integer totalComb;

	// 排序号
	@Column
	private Long sortId;

	// 是否支持限时抢购
	@Column
	private Boolean isFlashSale;

	// 限时抢购开始时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date flashSaleStartTime;

	// 限时抢购结束时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date flashSaleStopTime;

	// 限时抢购价格
	@Column(scale = 2)
	private Double flashSalePrice;

	// 限时抢购成交价格
	@Column(scale = 2)
	private Double flashSaleTransactionPrice;

	// 抢拍展示图
	@Column
	private String flashSaleImage;

	// 限时抢购剩余数量
	@Column
	private Long flashSaleLeftNumber;

	// 限时抢购已售数量
	@Column
	private Long flashSaleSoldNumber;

	// 是否支持团购
	@Column
	private Boolean isGroupSale;

	// 团购开始时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date groupSaleStartTime;

	// 团购结束时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date groupSaleStopTime;

	// 出发时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date leaveDate;

	// 预付价格
	@Column(scale = 2)
	private Double groupSalePrice;

	// 三人团价格
	@Column(scale = 2)
	private Double groupSaleThreePrice;

	// 七人团价格
	@Column(scale = 2)
	private Double groupSaleSevenPrice;

	// 十人团价格
	@Column(scale = 2)
	private Double groupSaleTenPrice;

	// 开启百人团购
	@Column
	private Boolean isGroupSaleHundred;

	// 百人团开始时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date groupSaleHundredStartTime;

	// 百人团结束时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date groupSaleHundredStopTime;

	// 百人团预付定金
	@Column(scale = 2)
	private Double groupSalePrePayPrice;

	// 百人团价格
	@Column(scale = 2)
	private Double groupSaleHundredPrice;

	// 团购展示图
	@Column
	private String groupSaleImage;

	// 团购剩余数量
	@Column
	private Long groupSaleLeftNumber;

	// 团购已售数量
	@Column
	private Long groupSaleSoldNumber;

	// 百人团剩余数量
	@Column
	private Long groupSaleHundredLeftNumber;

	// 百人团已售数量
	@Column
	private Long groupSaleHundredSoldNumber;

	// 品牌
	@Column
	private String brandTitle;

	// 品牌ID
	@Column
	private Long brandId;

	// 商品返现金额
	@Column(scale = 2)
	private Double returnPrice;

	// 赠送积分
	@Column
	private Long returnPoints;

	// 好评赠送粮草限制
	@Column
	private Long commentReturnPoints;

	// 购买积分限额
	@Column
	private Long pointLimited;

	// SEO标题
	@Column
	private String seoTitle;

	// SEO关键字
	@Column
	private String seoKeywords;

	// SEO描述
	@Column
	private String seoDescription;

	// 可购买会员等级限制
	@Column
	private Long userLevelLimit;

	// 评论数
	@Column
	private Long totalComments;

	// 关注数
	@Column
	private Long totalCollects;

	// 点击数
	@Column
	private Long totalclicks;

	// 同盟店返利比例
	@Column(scale = 2)
	private Double shopReturnRation;

	// 平台服务费比例
	@Column(scale = 2)
	private Double platformServiceReturnRation;

	// 培训服务费比例
	@Column(scale = 2)
	private Double trainServiceReturnRation;

	// 同盟店利润
	@Column(scale = 2)
	private Double shopReturnProfit;

	// 同盟店返利
	@Column(scale = 2)
	private Double shopReturn;

	// 平台毛利
	@Column(scale = 2)
	private Double platformServiceReturnProfit;

	// 培训费用
	@Column(scale = 2)
	private Double trainServiceReturnProfit;

	// 商品详情
	@Column
	private String detail;

	// 保险信息
	@Column
	private String insurance;

	// 取车地点
	@Column
	private String qcdd;

	// 取车地址
	@Column
	private String qcdz;

	// 还车地点
	@Column
	private String hcdd;

	// 还车地址
	@Column
	private String hcdz;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getCoverImageUri() {
		return coverImageUri;
	}

	public void setCoverImageUri(String coverImageUri) {
		this.coverImageUri = coverImageUri;
	}

	public Double getCoverImageWidth() {
		return coverImageWidth;
	}

	public void setCoverImageWidth(Double coverImageWidth) {
		this.coverImageWidth = coverImageWidth;
	}

	public Double getCoverImageHeight() {
		return coverImageHeight;
	}

	public void setCoverImageHeight(Double coverImageHeight) {
		this.coverImageHeight = coverImageHeight;
	}

	public String getVideoUri() {
		return videoUri;
	}

	public void setVideoUri(String videoUri) {
		this.videoUri = videoUri;
	}

	public String getShowPictures() {
		return showPictures;
	}

	public void setShowPictures(String showPictures) {
		this.showPictures = showPictures;
	}

	public String getReachPort() {
		return reachPort;
	}

	public void setReachPort(String reachPort) {
		this.reachPort = reachPort;
	}

	public Double getAveragePoints() {
		return averagePoints;
	}

	public void setAveragePoints(Double averagePoints) {
		this.averagePoints = averagePoints;
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

	public String getDeliveryArea() {
		return deliveryArea;
	}

	public void setDeliveryArea(String deliveryArea) {
		this.deliveryArea = deliveryArea;
	}

	public String getShipDescription() {
		return shipDescription;
	}

	public void setShipDescription(String shipDescription) {
		this.shipDescription = shipDescription;
	}

	public String getTripDescription() {
		return tripDescription;
	}

	public void setTripDescription(String tripDescription) {
		this.tripDescription = tripDescription;
	}

	public String getFeeDescription() {
		return feeDescription;
	}

	public void setFeeDescription(String feeDescription) {
		this.feeDescription = feeDescription;
	}

	public String getVisaDescription() {
		return visaDescription;
	}

	public void setVisaDescription(String visaDescription) {
		this.visaDescription = visaDescription;
	}

	public String getBookDescription() {
		return bookDescription;
	}

	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}

	public Boolean getIsOnSale() {
		return isOnSale;
	}

	public void setIsOnSale(Boolean isOnSale) {
		this.isOnSale = isOnSale;
	}

	public Boolean getIsRecommendIndex() {
		return isRecommendIndex;
	}

	public void setIsRecommendIndex(Boolean isRecommendIndex) {
		this.isRecommendIndex = isRecommendIndex;
	}

	public Boolean getIsRecommendType() {
		return isRecommendType;
	}

	public void setIsRecommendType(Boolean isRecommendType) {
		this.isRecommendType = isRecommendType;
	}

	public Boolean getIsHot() {
		return isHot;
	}

	public void setIsHot(Boolean isHot) {
		this.isHot = isHot;
	}

	public Boolean getIsNew() {
		return isNew;
	}

	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}

	public Boolean getIsSpecialPrice() {
		return isSpecialPrice;
	}

	public void setIsSpecialPrice(Boolean isSpecialPrice) {
		this.isSpecialPrice = isSpecialPrice;
	}

	public Long getNumberDecType() {
		return numberDecType;
	}

	public void setNumberDecType(Long numberDecType) {
		this.numberDecType = numberDecType;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public String getCategoryIdTree() {
		return categoryIdTree;
	}

	public void setCategoryIdTree(String categoryIdTree) {
		this.categoryIdTree = categoryIdTree;
	}

	public List<TdGoodsParameter> getParamList() {
		return paramList;
	}

	public void setParamList(List<TdGoodsParameter> paramList) {
		this.paramList = paramList;
	}

	public String getParamValueCollect() {
		return paramValueCollect;
	}

	public void setParamValueCollect(String paramValueCollect) {
		this.paramValueCollect = paramValueCollect;
	}

	public String getSelectOneValue() {
		return selectOneValue;
	}

	public void setSelectOneValue(String selectOneValue) {
		this.selectOneValue = selectOneValue;
	}

	public String getSelectTwoValue() {
		return selectTwoValue;
	}

	public void setSelectTwoValue(String selectTwoValue) {
		this.selectTwoValue = selectTwoValue;
	}

	public String getSelectThreeValue() {
		return selectThreeValue;
	}

	public void setSelectThreeValue(String selectThreeValue) {
		this.selectThreeValue = selectThreeValue;
	}

	public String getSaleType() {
		return saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}

	public Double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	public Double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public Double getOutFactoryPrice() {
		return outFactoryPrice;
	}

	public void setOutFactoryPrice(Double outFactoryPrice) {
		this.outFactoryPrice = outFactoryPrice;
	}

	public Double getIncludePrice() {
		return includePrice;
	}

	public void setIncludePrice(Double includePrice) {
		this.includePrice = includePrice;
	}

	public Double getInstallationPrice() {
		return installationPrice;
	}

	public void setInstallationPrice(Double installationPrice) {
		this.installationPrice = installationPrice;
	}

	public Double getOtherPrice() {
		return otherPrice;
	}

	public void setOtherPrice(Double otherPrice) {
		this.otherPrice = otherPrice;
	}

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public String getSiteTitle() {
		return siteTitle;
	}

	public void setSiteTitle(String siteTitle) {
		this.siteTitle = siteTitle;
	}

	public Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getWarehouseTitle() {
		return warehouseTitle;
	}

	public void setWarehouseTitle(String warehouseTitle) {
		this.warehouseTitle = warehouseTitle;
	}

	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}

	public String getProviderTitle() {
		return providerTitle;
	}

	public void setProviderTitle(String providerTitle) {
		this.providerTitle = providerTitle;
	}

	public Long getLeftNumber() {
		return leftNumber;
	}

	public void setLeftNumber(Long leftNumber) {
		this.leftNumber = leftNumber;
	}

	public Long getSoldNumber() {
		return soldNumber;
	}

	public void setSoldNumber(Long soldNumber) {
		this.soldNumber = soldNumber;
	}

	public String getPriceUnit() {
		return priceUnit;
	}

	public void setPriceUnit(String priceUnit) {
		this.priceUnit = priceUnit;
	}

	public Date getOnSaleTime() {
		return onSaleTime;
	}

	public void setOnSaleTime(Date onSaleTime) {
		this.onSaleTime = onSaleTime;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public List<TdGoodsGift> getGiftList() {
		return giftList;
	}

	public void setGiftList(List<TdGoodsGift> giftList) {
		this.giftList = giftList;
	}

	public Integer getTotalGift() {
		return totalGift;
	}

	public void setTotalGift(Integer totalGift) {
		this.totalGift = totalGift;
	}

	public List<TdGoodsCombination> getCombList() {
		return combList;
	}

	public void setCombList(List<TdGoodsCombination> combList) {
		this.combList = combList;
	}

	public Integer getTotalComb() {
		return totalComb;
	}

	public void setTotalComb(Integer totalComb) {
		this.totalComb = totalComb;
	}

	public Long getSortId() {
		return sortId;
	}

	public void setSortId(Long sortId) {
		this.sortId = sortId;
	}

	public Boolean getIsFlashSale() {
		return isFlashSale;
	}

	public void setIsFlashSale(Boolean isFlashSale) {
		this.isFlashSale = isFlashSale;
	}

	public Date getFlashSaleStartTime() {
		return flashSaleStartTime;
	}

	public void setFlashSaleStartTime(Date flashSaleStartTime) {
		this.flashSaleStartTime = flashSaleStartTime;
	}

	public Date getFlashSaleStopTime() {
		return flashSaleStopTime;
	}

	public void setFlashSaleStopTime(Date flashSaleStopTime) {
		this.flashSaleStopTime = flashSaleStopTime;
	}

	public Double getFlashSalePrice() {
		return flashSalePrice;
	}

	public void setFlashSalePrice(Double flashSalePrice) {
		this.flashSalePrice = flashSalePrice;
	}

	public Double getFlashSaleTransactionPrice() {
		return flashSaleTransactionPrice;
	}

	public void setFlashSaleTransactionPrice(Double flashSaleTransactionPrice) {
		this.flashSaleTransactionPrice = flashSaleTransactionPrice;
	}

	public String getFlashSaleImage() {
		return flashSaleImage;
	}

	public void setFlashSaleImage(String flashSaleImage) {
		this.flashSaleImage = flashSaleImage;
	}

	public Long getFlashSaleLeftNumber() {
		return flashSaleLeftNumber;
	}

	public void setFlashSaleLeftNumber(Long flashSaleLeftNumber) {
		this.flashSaleLeftNumber = flashSaleLeftNumber;
	}

	public Long getFlashSaleSoldNumber() {
		return flashSaleSoldNumber;
	}

	public void setFlashSaleSoldNumber(Long flashSaleSoldNumber) {
		this.flashSaleSoldNumber = flashSaleSoldNumber;
	}

	public Boolean getIsGroupSale() {
		return isGroupSale;
	}

	public void setIsGroupSale(Boolean isGroupSale) {
		this.isGroupSale = isGroupSale;
	}

	public Date getGroupSaleStartTime() {
		return groupSaleStartTime;
	}

	public void setGroupSaleStartTime(Date groupSaleStartTime) {
		this.groupSaleStartTime = groupSaleStartTime;
	}

	public Date getGroupSaleStopTime() {
		return groupSaleStopTime;
	}

	public void setGroupSaleStopTime(Date groupSaleStopTime) {
		this.groupSaleStopTime = groupSaleStopTime;
	}

	public Double getGroupSalePrice() {
		return groupSalePrice;
	}

	public void setGroupSalePrice(Double groupSalePrice) {
		this.groupSalePrice = groupSalePrice;
	}

	public Double getGroupSaleThreePrice() {
		return groupSaleThreePrice;
	}

	public void setGroupSaleThreePrice(Double groupSaleThreePrice) {
		this.groupSaleThreePrice = groupSaleThreePrice;
	}

	public Double getGroupSaleSevenPrice() {
		return groupSaleSevenPrice;
	}

	public void setGroupSaleSevenPrice(Double groupSaleSevenPrice) {
		this.groupSaleSevenPrice = groupSaleSevenPrice;
	}

	public Double getGroupSaleTenPrice() {
		return groupSaleTenPrice;
	}

	public void setGroupSaleTenPrice(Double groupSaleTenPrice) {
		this.groupSaleTenPrice = groupSaleTenPrice;
	}

	public Boolean getIsGroupSaleHundred() {
		return isGroupSaleHundred;
	}

	public void setIsGroupSaleHundred(Boolean isGroupSaleHundred) {
		this.isGroupSaleHundred = isGroupSaleHundred;
	}

	public Date getGroupSaleHundredStartTime() {
		return groupSaleHundredStartTime;
	}

	public void setGroupSaleHundredStartTime(Date groupSaleHundredStartTime) {
		this.groupSaleHundredStartTime = groupSaleHundredStartTime;
	}

	public Date getGroupSaleHundredStopTime() {
		return groupSaleHundredStopTime;
	}

	public void setGroupSaleHundredStopTime(Date groupSaleHundredStopTime) {
		this.groupSaleHundredStopTime = groupSaleHundredStopTime;
	}

	public Double getGroupSalePrePayPrice() {
		return groupSalePrePayPrice;
	}

	public void setGroupSalePrePayPrice(Double groupSalePrePayPrice) {
		this.groupSalePrePayPrice = groupSalePrePayPrice;
	}

	public Double getGroupSaleHundredPrice() {
		return groupSaleHundredPrice;
	}

	public void setGroupSaleHundredPrice(Double groupSaleHundredPrice) {
		this.groupSaleHundredPrice = groupSaleHundredPrice;
	}

	public String getGroupSaleImage() {
		return groupSaleImage;
	}

	public void setGroupSaleImage(String groupSaleImage) {
		this.groupSaleImage = groupSaleImage;
	}

	public Long getGroupSaleLeftNumber() {
		return groupSaleLeftNumber;
	}

	public void setGroupSaleLeftNumber(Long groupSaleLeftNumber) {
		this.groupSaleLeftNumber = groupSaleLeftNumber;
	}

	public Long getGroupSaleSoldNumber() {
		return groupSaleSoldNumber;
	}

	public void setGroupSaleSoldNumber(Long groupSaleSoldNumber) {
		this.groupSaleSoldNumber = groupSaleSoldNumber;
	}

	public Long getGroupSaleHundredLeftNumber() {
		return groupSaleHundredLeftNumber;
	}

	public void setGroupSaleHundredLeftNumber(Long groupSaleHundredLeftNumber) {
		this.groupSaleHundredLeftNumber = groupSaleHundredLeftNumber;
	}

	public Long getGroupSaleHundredSoldNumber() {
		return groupSaleHundredSoldNumber;
	}

	public void setGroupSaleHundredSoldNumber(Long groupSaleHundredSoldNumber) {
		this.groupSaleHundredSoldNumber = groupSaleHundredSoldNumber;
	}

	public String getBrandTitle() {
		return brandTitle;
	}

	public void setBrandTitle(String brandTitle) {
		this.brandTitle = brandTitle;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public Double getReturnPrice() {
		return returnPrice;
	}

	public void setReturnPrice(Double returnPrice) {
		this.returnPrice = returnPrice;
	}

	public Long getReturnPoints() {
		return returnPoints;
	}

	public void setReturnPoints(Long returnPoints) {
		this.returnPoints = returnPoints;
	}

	public Long getCommentReturnPoints() {
		return commentReturnPoints;
	}

	public void setCommentReturnPoints(Long commentReturnPoints) {
		this.commentReturnPoints = commentReturnPoints;
	}

	public Long getPointLimited() {
		return pointLimited;
	}

	public void setPointLimited(Long pointLimited) {
		this.pointLimited = pointLimited;
	}

	public String getSeoTitle() {
		return seoTitle;
	}

	public void setSeoTitle(String seoTitle) {
		this.seoTitle = seoTitle;
	}

	public String getSeoKeywords() {
		return seoKeywords;
	}

	public void setSeoKeywords(String seoKeywords) {
		this.seoKeywords = seoKeywords;
	}

	public String getSeoDescription() {
		return seoDescription;
	}

	public void setSeoDescription(String seoDescription) {
		this.seoDescription = seoDescription;
	}

	public Long getUserLevelLimit() {
		return userLevelLimit;
	}

	public void setUserLevelLimit(Long userLevelLimit) {
		this.userLevelLimit = userLevelLimit;
	}

	public Long getTotalComments() {
		return totalComments;
	}

	public void setTotalComments(Long totalComments) {
		this.totalComments = totalComments;
	}

	public Long getTotalCollects() {
		return totalCollects;
	}

	public void setTotalCollects(Long totalCollects) {
		this.totalCollects = totalCollects;
	}

	public Long getTotalclicks() {
		return totalclicks;
	}

	public void setTotalclicks(Long totalclicks) {
		this.totalclicks = totalclicks;
	}

	public Double getShopReturnRation() {
		return shopReturnRation;
	}

	public void setShopReturnRation(Double shopReturnRation) {
		this.shopReturnRation = shopReturnRation;
	}

	public Double getPlatformServiceReturnRation() {
		return platformServiceReturnRation;
	}

	public void setPlatformServiceReturnRation(Double platformServiceReturnRation) {
		this.platformServiceReturnRation = platformServiceReturnRation;
	}

	public Double getTrainServiceReturnRation() {
		return trainServiceReturnRation;
	}

	public void setTrainServiceReturnRation(Double trainServiceReturnRation) {
		this.trainServiceReturnRation = trainServiceReturnRation;
	}

	public Double getShopReturnProfit() {
		return shopReturnProfit;
	}

	public void setShopReturnProfit(Double shopReturnProfit) {
		this.shopReturnProfit = shopReturnProfit;
	}

	public Double getShopReturn() {
		return shopReturn;
	}

	public void setShopReturn(Double shopReturn) {
		this.shopReturn = shopReturn;
	}

	public Double getPlatformServiceReturnProfit() {
		return platformServiceReturnProfit;
	}

	public void setPlatformServiceReturnProfit(Double platformServiceReturnProfit) {
		this.platformServiceReturnProfit = platformServiceReturnProfit;
	}

	public Double getTrainServiceReturnProfit() {
		return trainServiceReturnProfit;
	}

	public void setTrainServiceReturnProfit(Double trainServiceReturnProfit) {
		this.trainServiceReturnProfit = trainServiceReturnProfit;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public List<TdGoodsPs> getPsList() {
		return psList;
	}

	public void setPsList(List<TdGoodsPs> psList) {
		this.psList = psList;
	}

	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	public List<TdGoodsXc> getXcList() {
		return xcList;
	}

	public void setXcList(List<TdGoodsXc> xcList) {
		this.xcList = xcList;
	}

	public String getXcImageUri() {
		return xcImageUri;
	}

	public void setXcImageUri(String xcImageUri) {
		this.xcImageUri = xcImageUri;
	}

	public String getVisaDataUri() {
		return visaDataUri;
	}

	public void setVisaDataUri(String visaDataUri) {
		this.visaDataUri = visaDataUri;
	}

	public Long getDriveId() {
		return driveId;
	}

	public void setDriveId(Long driveId) {
		this.driveId = driveId;
	}

	public Boolean getCarType() {
		return carType;
	}

	public void setCarType(Boolean carType) {
		this.carType = carType;
	}

	public String getInsurance() {
		return insurance;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	public Double getSalePrice1() {
		return salePrice1;
	}

	public void setSalePrice1(Double salePrice1) {
		this.salePrice1 = salePrice1;
	}

	public String getQcdd() {
		return qcdd;
	}

	public void setQcdd(String qcdd) {
		this.qcdd = qcdd;
	}

	public String getHcdd() {
		return hcdd;
	}

	public void setHcdd(String hcdd) {
		this.hcdd = hcdd;
	}

	public String getQcdz() {
		return qcdz;
	}

	public void setQcdz(String qcdz) {
		this.qcdz = qcdz;
	}

	public String getHcdz() {
		return hcdz;
	}

	public void setHcdz(String hcdz) {
		this.hcdz = hcdz;
	}

}
