package com.ynyes.tianya.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class TdUserTravelNotes {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne
	@JoinColumn(name = "travelNotesId")
	private TdTravelPraise travelNotesPraise;

	// 封面图片路径
	@Column
	private String imgUrl;

	@Column
	private String username;
	// 标题
	@Column
	private String title;
	// 所属分类
	@Column
	private String classify;
	// 游记内容
	@Column
	private String content;
	// 单篇日志的评论数
	@Column
	private Long countComment;

	@Column
	private Long statusId = 0L;
	// 单篇日志的点赞数
	@Column
	private Long countPraise;
	// 区别攻略游记 1旅行攻略, 0旅行感受, 3旅行图片 ，4旅行推荐 
	@Column
	private Long type;

	// 排序号
	@Column
	private Long sortId;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getCountComment() {
		return countComment;
	}

	public void setCountComment(Long countComment) {
		this.countComment = countComment;
	}

	public Long getCountPraise() {
		return countPraise;
	}

	public void setCountPraise(Long countPraise) {
		this.countPraise = countPraise;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public TdTravelPraise getTravelNotesPraise() {
		return travelNotesPraise;
	}

	public void setTravelNotesPraise(TdTravelPraise travelNotesPraise) {
		this.travelNotesPraise = travelNotesPraise;
	}

	public Long getSortId() {
		return sortId;
	}

	public void setSortId(Long sortId) {
		this.sortId = sortId;
	}
	
	

}
