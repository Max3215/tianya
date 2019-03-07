package com.ynyes.tianya.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.tianya.entity.TdAssets;
import com.ynyes.tianya.repository.TdAssetsRepo;

/**
 * 资产调节表服务类
 * @author Max
 *
 */
@Service
@Transactional
public class TdAssetsService {

	@Autowired
	private TdAssetsRepo repository;
	
	public TdAssets save(TdAssets e)
	{
		if(null == e)
		{
			return null;
		}
		// 新加数据设置编号
		if(null == e.getId())
		{
			StringBuffer str = new StringBuffer();
			str.append(e.getManager().substring(0, 1));
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("MM");
			str.append(sdf.format(date)+"-");
			str.append(countByManagerId(e.getManagerId())+1);
			e.setAssNumber(str.toString());
		}
		
		return repository.save(e);
	}
	
	public TdAssets findOne(Long id)
	{
		if(null == id){
			return null;
		}
		return repository.findOne(id);
	}
	
	public void delete(Long id)
	{
		if(null != id)
		{
			repository.delete(id);
		}
	}
	
	/**
	 * 
	 * 财务部查看方法
	 * @author Max
	 */
	public Page<TdAssets> findAll(int page,int size)
	{
		PageRequest pageRequest = new PageRequest(page, size, new Sort(
	                Direction.ASC, "createTime"));
		return repository.findAll(pageRequest);
	}
	
	public Page<TdAssets> findByUseTimeAfter(int page,int size,Date startTime)
	{
		PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "createTime"));
		return repository.findByUseDateAfter(startTime, pageRequest);
	}
	
	public Page<TdAssets> findByUseTimeBefore(int page,int size,Date endTime)
	{
		PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "createTime"));
		return repository.findByUseDateBefore(endTime, pageRequest);
	}
	
	public Page<TdAssets> findByUseTimeBetween(int page,int size,Date startTime,Date endTime)
	{
		PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "createTime"));
		return repository.findByUseDateBetween(startTime, endTime, pageRequest);
	}
	
	public Page<TdAssets> findByIsSettlement(boolean isSettlement,int page,int size)
	{
		PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "createTime"));
		return repository.findByIsSettlement(isSettlement,pageRequest);
	}
	
	public Page<TdAssets> findByUseTimeAfterAndIsSettlement(int page,int size,Date startTime,boolean isSettlement)
	{
		PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "createTime"));
		return repository.findByUseDateAfterAndIsSettlement(startTime,isSettlement, pageRequest);
	}
	
	public Page<TdAssets> findByUseTimeBeforeAndIsSettlement(int page,int size,Date endTime,boolean isSettlement)
	{
		PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "createTime"));
		return repository.findByUseDateBeforeAndIsSettlement(endTime,isSettlement, pageRequest);
	}
	
	public Page<TdAssets> findByUseTimeBetweenAndIsSettlement(int page,int size,Date startTime,Date endTime,boolean isSettlement)
	{
		PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "createTime"));
		return repository.findByUseDateBetweenAndIsSettlement(startTime, endTime, isSettlement,pageRequest);
	}
	
	/**
	 * 
	 * 其他部门查看
	 * @author Max
	 * 
	 */
	
	public Page<TdAssets> findByManagerId(Long managerId,int page,int size)
	{
		 PageRequest pageRequest = new PageRequest(page, size, new Sort(
	                Direction.ASC, "createTime"));
		String manager = "[" + managerId + "]";
		
		return repository.findByManagerTreeContaining(manager, pageRequest);
	}
	
	public List<TdAssets> findByManagerId(Long managerId)
	{
		String manager = "[" + managerId + "]";
		
		return repository.findByManagerTreeContaining(manager);
	}
	
	public Page<TdAssets> findByManagerAndUseDateBefore(Long managerId,Date endTime,int page,int size)
	{
		PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "createTime"));
		String manager = "[" + managerId + "]";
		
		return repository.findByManagerTreeContainingAndUseDateBefore(manager, endTime, pageRequest);
	}
	
	public Page<TdAssets> findByManagerAndUseDateAfter(Long managerId,Date startTime,int page,int size)
	{
		PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "createTime"));
		String manager = "[" + managerId + "]";
		
		return repository.findByManagerTreeContainingAndUseDateAfter(manager, startTime, pageRequest);
	}
	
	public Page<TdAssets> findByManagerIdAndUseTimeBetween(int page,int size,Long managerId,Date startTime,Date endTime)
	{
		PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "createTime"));
		String manager = "[" + managerId + "]";
		return repository.findByManagerTreeContainingAndUseDateBetween(manager, startTime, endTime, pageRequest);
	}
	
	public Page<TdAssets> findByManagerIdAndIsSettlement(Long managerId,boolean isSettlement,int page,int size)
	{
		 PageRequest pageRequest = new PageRequest(page, size, new Sort(
	                Direction.ASC, "createTime"));
		String manager = "[" + managerId + "]";
		
		return repository.findByManagerTreeContainingAndIsSettlement(manager,isSettlement, pageRequest);
	}
	
	public Page<TdAssets> findByManagerAndUseDateBeforeAndIsSettlement(Long managerId,Date endTime,boolean isSettlement,int page,int size)
	{
		PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "createTime"));
		String manager = "[" + managerId + "]";
		
		return repository.findByManagerTreeContainingAndUseDateBeforeAndIsSettlement(manager, endTime,isSettlement, pageRequest);
	}
	
	public Page<TdAssets> findByManagerAndUseDateAfterAndIsSettlement(Long managerId,Date startTime,boolean isSettlement,int page,int size)
	{
		PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "createTime"));
		String manager = "[" + managerId + "]";
		
		return repository.findByManagerTreeContainingAndUseDateAfterAndIsSettlement(manager, startTime,isSettlement, pageRequest);
	}
	
	public Page<TdAssets> findByManagerIdAndUseTimeBetweenAndIsSettlement(int page,int size,Long managerId,Date startTime,Date endTime,boolean isSettlement)
	{
		PageRequest pageRequest = new PageRequest(page, size, new Sort(
                Direction.ASC, "createTime"));
		String manager = "[" + managerId + "]";
		return repository.findByManagerTreeContainingAndUseDateBetweenAndIsSettlement(manager, startTime, endTime, isSettlement,pageRequest);
	}
	
	
	
	
	
	public int countByManagerId(Long managerId)
	{
		return repository.countByManagerId(managerId);
	}
	
}
