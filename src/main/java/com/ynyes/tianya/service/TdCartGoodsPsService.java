package com.ynyes.tianya.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.tianya.entity.TdCartGoodsPs;
import com.ynyes.tianya.repository.TdCartGoodsPsRepo;

/**
 * 
 * @author Max
 *
 */
@Service
@Transactional
public class TdCartGoodsPsService {

	@Autowired
	private TdCartGoodsPsRepo repository;
	
	
	public TdCartGoodsPs save(TdCartGoodsPs e)
	{
		if(null == e)
		{
			return null;
		}
		return repository.save(e);
	}
	
	
	public List<TdCartGoodsPs> save(List<TdCartGoodsPs> entities)
	{
		return (List<TdCartGoodsPs>) repository.save(entities);
		
	}
	
	public TdCartGoodsPs findOne(Long id)
	{
		return repository.findOne(id);
	}
	
	public TdCartGoodsPs findTopByCartIdAndGoodsPsId(Long cartId,Long goodsPsId)
	{
		if(null == cartId || null == goodsPsId)
		{
			return null;
		}
		return repository.findTopByCartIdAndGoodsPsId(cartId, goodsPsId);
	}
	
	public void delete(List<TdCartGoodsPs> entities)
	{
		if(null != entities)
		{
			repository.delete(entities);
		}
	}
	
	public void delete(TdCartGoodsPs e)
	{
		if(null != e)
		{
			repository.delete(e);
		}
	}
	
	public void delete(Long id)
	{
		repository.delete(id);
	}
	
}
