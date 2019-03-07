package com.ynyes.tianya.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;


/**
 * 用户评论
 * 
 * @author Sharon
 *
 */

@Entity
public class TdUserTravelComment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    // 评论标题
    @Column
    private String title;
    
    // 评论内容
    @Column
    private String content;
    
    // 评论标签
    @Column
    private String tags;
    
    @Column
    private Long compositeStar;
     
	// 评论被点击“有用”的数量
    @Column
    private Long positiveNumber;
    
    // 评论被点击“无用”的数量
    @Column
    private Long negativeNumber;
    
 // 用户头像
    @Column
    private String userHeadUri;
    
    // 评论时间
    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date commentTime;
    
    //是否已回复
    @Column
    private Boolean isReplied;
    
    //评论回复
    @Column
    private String reply;
    
    //回复时间
    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date replyTime;
    
    //评论的类型：为0表示为游记评论，为1表示为攻略评论。
    @Column
    private Long typeId;
    
    //评论的游记ID
    @Column
    private Long commentId;
    //发表评论的用户
    @Column
    private String username;
    // 显示状态
    @Column
    private Long statusId;
    // 排序号
    @Column
    private Long sortId;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public Long getCompositeStar() {
		return compositeStar;
	}
	public void setCompositeStar(Long compositeStar) {
		this.compositeStar = compositeStar;
	}
	public Long getPositiveNumber() {
		return positiveNumber;
	}
	public void setPositiveNumber(Long positiveNumber) {
		this.positiveNumber = positiveNumber;
	}
	public Long getNegativeNumber() {
		return negativeNumber;
	}
	public void setNegativeNumber(Long negativeNumber) {
		this.negativeNumber = negativeNumber;
	}
	public Date getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}
	public Boolean getIsReplied() {
		return isReplied;
	}
	public void setIsReplied(Boolean isReplied) {
		this.isReplied = isReplied;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public Date getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}
	public Long getCommentId() {
		return commentId;
	}
	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public Long getSortId() {
		return sortId;
	}
	public void setSortId(Long sortId) {
		this.sortId = sortId;
	}
	public String getUserHeadUri() {
		return userHeadUri;
	}
	public void setUserHeadUri(String userHeadUri) {
		this.userHeadUri = userHeadUri;
	}
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
    
    
}
