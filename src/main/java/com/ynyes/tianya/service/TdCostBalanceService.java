package com.ynyes.tianya.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.tianya.entity.TdCostBalance;
import com.ynyes.tianya.repository.TdCostBalanceRepo;
import com.ynyes.tianya.util.Criteria;
import com.ynyes.tianya.util.Restrictions;

/**
 * 
 * 客户结算表服务类
 * 
 * @author Max
 *
 */

@Service
@Transactional
public class TdCostBalanceService {

	@Autowired
	private TdCostBalanceRepo repository;

	public TdCostBalance getByClientId(Long clientId) {
		if (clientId != null) {
			return repository.findByClientId(clientId);
		} else {
			return null;
		}
	}

	public TdCostBalance save(TdCostBalance cb) {
		if (cb != null) {
			return repository.save(cb);
		} else {
			return null;
		}
	}

	public TdCostBalance findOne(Long id) {
		if (id != null) {
			return repository.findOne(id);
		} else {
			return null;
		}
	}

	public void delete(TdCostBalance tcb) {
		if (tcb != null) {
			repository.delete(tcb);
		}
	}

	// 需要签字的结算表
	public Page<TdCostBalance> needToAssign(String s, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
		return repository.findBycheckPersonIdListContaining(s, pageRequest);
	}

	public Page<TdCostBalance> findAll(int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
		return repository.findAll(pageRequest);
	}

	public Page<TdCostBalance> findByManagerTreeContainingOrderByCreateTimeDesc(String managerIdStr, int page,
			int size) {
		PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "createTime"));
		return repository.findByManagerTreeContaining(managerIdStr, pageRequest);
	}

	// 搜索
	public Page<TdCostBalance> searchCostBalance(Boolean isCw, String managerIdStr, Date begin, Date end, int page,
			int size) {
		PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "createTime"));
		Criteria<TdCostBalance> c = new Criteria<>();
		if (begin != null) {
			c.add(Restrictions.gte("createTime", begin, true));
		}
		if (end != null) {
			c.add(Restrictions.lte("createTime", end, true));
		}
		if (!isCw) {
			if (managerIdStr != null && !managerIdStr.equals("")) {
				c.add(Restrictions.like("managerTree", managerIdStr, true));
			}
		}
		return repository.findAll(c, pageRequest);
	}
	
	public void deleteOne(Long id){
		if(id != null){
			repository.delete(id);
		}
	}

}
