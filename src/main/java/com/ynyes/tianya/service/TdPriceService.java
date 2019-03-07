package com.ynyes.tianya.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.tianya.entity.TdPrice;
import com.ynyes.tianya.repository.TdPriceRepo;
import com.ynyes.tianya.util.Criteria;
import com.ynyes.tianya.util.Restrictions;

/**
 * 区间价目表服务类
 * @author Max
 *
 */
@Service
@Transactional
public class TdPriceService {

	@Autowired
	TdPriceRepo repostory;
	
	
	public TdPrice save(TdPrice e) 
	{
		if(null == e){
			return null;
		}
		return repostory.save(e);
	}
	
	public void delete(Long id)
	{
		if(null != id)
		{
			repostory.delete(id);
		}
	}
	
	public TdPrice findOne(Long id )
	{
		if(null == id){
			return null;
		}
		return repostory.findOne(id);
	}
	
	public List<TdPrice> findByGoodsId(Long goodsId){
		if(null == goodsId){
			return null ;
		}
		return repostory.findByGoodsIdOrderByStartTimeDesc(goodsId);
	}
	
	public Page<TdPrice> findAll(Long categoryId,String keywords,int page,int size){
		PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "startTime"));
		
		Criteria<TdPrice> c = new Criteria<>();
		if(null != categoryId){
			c.add(Restrictions.like("categoryIdTree", "["+categoryId+"]", true));
		}
		if(null != keywords && !keywords.isEmpty()){
			c.add(Restrictions.like("goodsTitle", keywords, true));
		}
		
		return repostory.findAll(c, pageRequest);
	}
	
}
