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
 * 管理员
 * 
 * @author Sharon
 *
 */

@Entity
public class TdManager {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// 上一级领导Id
	@Column
	private Long parentId;

	// 类型
	@Column
	private Long roleId;
	@Column
	private Long layerCount;

	// 用户名
	@Column
	private String username;

	// 密码
	@Column
	private String password;

	// 姓名
	@Column
	private String realName;

	// 电话
	@Column
	private String telephone;

	// 邮箱
	@Column
	private String email;

	// 本次登录IP
	@Column
	private String ip;

	// 上次登录IP
	@Column
	private String lastLoginIp;

	// 上次登录时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastLoginTime;

	// 本次登录时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date loginTime;

	// 是否开启
	@Column
	private Boolean isEnable;

	// 创建时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	// 排序号
	@Column
	private Long sortId;

	// 拥有的客户
	@OneToMany
	@JoinColumn(name = "managerId")
	private List<TdClient> tdClientList;

	// 父级树 Max
	@Column
	private String parentTree;

	// 签章图片
	@Column
	private String signatureImgUri;

	// 是否有上下架权利
	@Column
	private Boolean canUpDown;

	public String getParentTree() {
		return parentTree;
	}

	public void setParentTree(String parentTree) {
		this.parentTree = parentTree;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public List<TdClient> getTdClientList() {
		return tdClientList;
	}

	public void setTdClientList(List<TdClient> tdClientList) {
		this.tdClientList = tdClientList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getSortId() {
		return sortId;
	}

	public void setSortId(Long sortId) {
		this.sortId = sortId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Long getLayerCount() {
		return layerCount;
	}

	public void setLayerCount(Long layerCount) {
		this.layerCount = layerCount;
	}

	public String getSignatureImgUri() {
		return signatureImgUri;
	}

	public void setSignatureImgUri(String signatureImgUri) {
		this.signatureImgUri = signatureImgUri;
	}

	public Boolean getCanUpDown() {
		return canUpDown;
	}

	public void setCanUpDown(Boolean canUpDown) {
		this.canUpDown = canUpDown;
	}

}
