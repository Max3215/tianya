package com.ynyes.tianya.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


/**
 * 问题
 * 
 * @author lulu
 *
 */

@Entity
public class TdArticleWt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
   
    // 标题
    @Column
    private String question;
    
    // 所属栏目ID
    @Column
    private Long articleId;

    // 解答内容
    @Column
    private String content;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
    
}
