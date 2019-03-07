package com.ynyes.tianya.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.tianya.entity.TdLimitTimeProduct;
import com.ynyes.tianya.repository.TdLimitTimeProductRepo;

/**
 * TdArticleCategory 服务类
 * 
 * @author Sharon
 *
 */

@Service
@Transactional
public class TdLimitTimeProductService {
    @Autowired
    TdLimitTimeProductRepo repository;
    
    public List<TdLimitTimeProduct> findAll(){
    	return (List<TdLimitTimeProduct>) repository.findAll();
    }
}
