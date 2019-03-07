package com.ynyes.tianya.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.tianya.entity.TdGoods;
import com.ynyes.tianya.entity.TdTimePrice;
import com.ynyes.tianya.repository.TdTimePriceRepo;

/**
 * 价格日历表服务类
 * @author Max
 *
 */
@Service
@Transactional
public class TdTimePriceService {
	
	@Autowired
	TdTimePriceRepo repostiry;
	
	public TdTimePrice save(TdTimePrice e)
	{
		if(null == e){
			return null;
		}
		return repostiry.save(e);
	}
	
	public void delete(Long id)
	{
		if(null != id){
			repostiry.delete(id);
		}
	}
	
	public TdTimePrice findOne(Long id)
	{
		if(null == id)
		{
			return null;
		}
		return repostiry.findOne(id);
	}

	public List<TdTimePrice> findByGoodsId(Long goodsId,Date Time)
	{
		if(null == goodsId){
			return null;
		}
		return repostiry.findByGoodsIdAndTimeAfter(goodsId, Time);
	}
	
	public Page<TdTimePrice> findAll(int page,int size)
	{
		PageRequest pageRequest = new PageRequest(page, size,new Sort(Direction.DESC, "id"));
		return repostiry.findAll(pageRequest);
	}
	
	public TdTimePrice findByGoodsIdAndTime(Long goodsId,String time)
	{
		if(null == goodsId || null == time){
			return null;
		}
		return repostiry.findByGoodsIdAndTime(goodsId, time);
	}
	
	//author liuxinbing
	public Page<TdTimePrice> findByCategoryIdTreeContaining(
            Long catId, int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size);

        String catIdStr = "[" + catId + "]";

        return repostiry
                .findByCategoryIdTreeContaining(
                        catIdStr, pageRequest);
    }
	
	
	/**
	 * @author Max
	 *
	 */
	public List<TdTimePrice> findByPriceId(Long priceId)
	{
		if(null == priceId)
		{
			return null;
		}
		return repostiry.findByPriceId(priceId);
	}
	
	/**
	 * @author Max
	 * 
	 */
	public void deleteByPriceId(Long priceId)
	{
		if(null != priceId){
			repostiry.deleteByPriceId(priceId);
		}
	}
	
}
