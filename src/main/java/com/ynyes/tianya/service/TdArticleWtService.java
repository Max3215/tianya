package com.ynyes.tianya.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.tianya.entity.TdArticleCategory;
import com.ynyes.tianya.entity.TdArticleWt;
import com.ynyes.tianya.entity.TdGoodsPs;
import com.ynyes.tianya.repository.TdArticleCategoryRepo;
import com.ynyes.tianya.repository.TdArticleWtRepo;

/**
 * TdArticleWt 服务类
 * 
 * @author lulu
 *
 */

@Service
@Transactional
public class TdArticleWtService {
    @Autowired
    TdArticleWtRepo repository;

	public void delete(Long id) {
		// TODO Auto-generated method stub
		repository.delete(id);
	}

	public List<TdArticleWt> save(List<TdArticleWt> entities)
    {
		// TODO Auto-generated method stub
        return (List<TdArticleWt>) repository.save(entities);
    }
    
}
