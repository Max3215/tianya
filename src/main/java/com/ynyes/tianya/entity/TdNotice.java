package com.ynyes.tianya.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 广告
 * 
 * @author Sharon
 *
 */

@Entity
public class TdNotice {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// 通知内容
	@Lob
	private String content;

	// 通知人Id
	@Column
	private Long noticeId;

	// 通知人真实姓名
	@Column
	private String noticeRealName;

	// 被通知人Id链
	@Column
	private String noticedIds;

	// 创建时间
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(Long noticeId) {
		this.noticeId = noticeId;
	}

	public String getNoticeRealName() {
		return noticeRealName;
	}

	public void setNoticeRealName(String noticeRealName) {
		this.noticeRealName = noticeRealName;
	}

	public String getNoticedIds() {
		return noticedIds;
	}

	public void setNoticedIds(String noticedIds) {
		this.noticedIds = noticedIds;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
