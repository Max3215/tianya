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

import com.ynyes.tianya.entity.TdStaff;
import com.ynyes.tianya.entity.TdStationCenterStaffTravelLine;
import com.ynyes.tianya.repository.TdStationCenterStaffTravelLineRepo;
import com.ynyes.tianya.util.Criteria;
import com.ynyes.tianya.util.Restrictions;
/**
 * 旅游线路
 * @author Administrator
 *
 */
@Service
@Transactional
public class TdStationCenterStaffTravelLineService {
	
	@Autowired
	TdStationCenterStaffTravelLineRepo repository;
	
	@Autowired
	TdStaffService tdStaffService;
	
	public TdStationCenterStaffTravelLine save(TdStationCenterStaffTravelLine scstl)
    {	
		if(null == scstl.getIsSettle() || !scstl.getIsSettle() )
		{
			// 包含职工
			if(null != scstl.getUnit() && !scstl.getUnit().equals("散客"))
			{
				String staffName = scstl.getStaffName();
				String[] names = staffName.split("，");
				
				for (int i = 0; i < names.length; i++) {
					// 根据名字查找职工表，获取职工的优惠金额以及前期余额
					TdStaff staff = tdStaffService.findByUsername(names[i]);
					if(null != staff)
					{
						// 保存出行时间和结算时间
						staff.setAccountTime(scstl.getCreateTime());
						staff.setTravelTime(scstl.getTravelTime());
						
						// 清除可使用优惠金额，修改结算状态
						staff.setSurDiscount(0.0);
						staff.setSurplus(0.0);
						staff.setIsClose(true);
						
						tdStaffService.save(staff);
					}
				}
			}
		}
        return repository.save(scstl);
    }
	
	public List<TdStationCenterStaffTravelLine> tdStationCenterStaffTravelLineListByClientId(Long clientId){
		return repository.findByClientId(clientId);
	} 
	
	public void deleteTdStationCenterStaffTravelLine(Long id){
		if(id != null){
			repository.delete(id);
		}
	}

	public List<TdStationCenterStaffTravelLine> findByClientId(Long id) {
		if(null==id){
			return null;
		}
		return repository.findByClientId(id);
	}
	
	
	public Page<TdStationCenterStaffTravelLine> findByManagerIdAndIsSettleAndCreateTimeBetween(Long managerId,
			Boolean isSettle, Date calendar1, Date calendar2, int page, int size){
		PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "createTime"));
		return repository.findByManagerIdAndIsSettleAndCreateTimeBetween(managerId, isSettle, calendar1, calendar2, pageRequest);
	}
	
	public Page<TdStationCenterStaffTravelLine> findByManagerIdAndIsSettleAndCreateTimeAfter(Long managerId,
			Boolean isSettle, Date calendar1, int page, int size){
		PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "createTime"));
		return repository.findByManagerIdAndIsSettleAndCreateTimeAfter(managerId, isSettle, calendar1, pageRequest);
	}
	
	public Page<TdStationCenterStaffTravelLine> findByManagerIdAndIsSettleAndCreateTimeBefore(Long managerId,
			Boolean isSettle, Date calendar2, int page, int size){
		PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "createTime"));
		return repository.findByManagerIdAndIsSettleAndCreateTimeBefore(managerId, isSettle, calendar2, pageRequest);
	}
	
	public Page<TdStationCenterStaffTravelLine> findByManagerIdAndIsSettle(Long managerId,
			Boolean isSettle, int page, int size){
		PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "createTime"));
		return repository.findByManagerIdAndIsSettle(managerId, isSettle, pageRequest);
	}
	
	public TdStationCenterStaffTravelLine findOne(Long id){
		if(id != null){
			return repository.findOne(id);
		}else{
			return null;
		}
	}
	
	
	public void deleteOne(Long id){
		repository.delete(id);
	}
	
	// 搜索
	public Page<TdStationCenterStaffTravelLine> searchStaffTravel(Integer unit,String keywords, Boolean isSettle, Date begin, Date end, String managerIdStr, boolean isCw, int page, int size){
		PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "createTime"));
		Criteria<TdStationCenterStaffTravelLine> c = new Criteria<TdStationCenterStaffTravelLine>();
		if(unit != null){
			c.add(Restrictions.eq("unitId", unit, true));
		}
		if(null != keywords && !keywords.isEmpty())
		{
			c.add(Restrictions.like("teamNumber", keywords, true));
		}
		
		if(isSettle != null){
			c.add(Restrictions.eq("isSettle", isSettle, true));
		}
		if(begin != null){
			c.add(Restrictions.gte("createTime", begin, true));
		}
		if(end != null){
			c.add(Restrictions.lte("createTime", end, true));
		}
		if(!isCw){
			if(managerIdStr != null && !managerIdStr.equals("")){
				c.add(Restrictions.lte("createTime", end, true));
				c.add(Restrictions.like("parentTree", managerIdStr, true));
			}			
		}
		return repository.findAll(c, pageRequest);
	}
}
