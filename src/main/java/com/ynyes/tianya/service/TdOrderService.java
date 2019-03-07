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

import com.ynyes.tianya.entity.TdCostBalance;
import com.ynyes.tianya.entity.TdOrder;
import com.ynyes.tianya.repository.TdOrderRepo;
import com.ynyes.tianya.util.Criteria;
import com.ynyes.tianya.util.Restrictions;

/**
 * TdOrder 服务类
 * 
 * @author Sharon
 *
 */

@Service
@Transactional
public class TdOrderService {
    @Autowired
    TdOrderRepo repository;
    
    
    
    @Autowired
    private TdUserService tdUserService;
    
    // 待付款订单数量
    public int getUnpayNum(){
    	List<TdOrder> tol = repository.findByStatusId(2L);
    	return tol.size();
    }
    public List<TdOrder> findByUsernameAndGoodsId(String username, Long goodsId)
    {
        if (null == username || null == goodsId)
        {
            return null;
        }
        
        return repository.findByUsernameAndGoodsId(username, goodsId);
    }
    
    /**
     * 删除
     * 
     * @param id 菜单项ID
     */
    public void delete(Long id)
    {
        if (null != id)
        {
            repository.delete(id);
        }
    }
    
    /**
     * 删除
     * 
     * @param e 菜单项
     */
    public void delete(TdOrder e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }
    
    public void delete(List<TdOrder> entities)
    {
        if (null != entities)
        {
            repository.delete(entities);
        }
    }
    
    /**
     * 查找
     * 
     * @param id ID
     * @return
     */
    public TdOrder findOne(Long id)
    {
        if (null == id)
        {
            return null;
        }
        
        return repository.findOne(id);
    }
    
    /**
     * 查找
     * 
     * @param ids
     * @return
     */
    public List<TdOrder> findAll(Iterable<Long> ids)
    {
        return (List<TdOrder>) repository.findAll(ids);
    }
    
    public List<TdOrder> findAll()
    {
        return (List<TdOrder>) repository.findAll();
    }
    
    public Page<TdOrder> findAllOrderBySortIdAsc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));
        
        return repository.findAll(pageRequest);
    }
    
    public Page<TdOrder> findAllOrderByIdDesc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
        
        return repository.findAll(pageRequest);
    }
    
    public Page<TdOrder> findByUsernameAndOrderType(String username, String orderType, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
        return repository.findByUsernameAndOrderTypeOrderByIdDesc(username, orderType, pageRequest);
        
    }
    
    public Page<TdOrder> findByStatusIdOrderByIdDesc(long statusId, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByStatusIdOrderByIdDesc(statusId, pageRequest);
    }
    
    public Page<TdOrder> findByUsername(String username, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByUsernameOrderByIdDesc(username, pageRequest);
    }
    
    public TdOrder findByOrderNumber(String orderNumber)
    {
        return repository.findByOrderNumber(orderNumber);
    }
    
    public Page<TdOrder> findByUsernameAndTimeAfter(String username, Date time, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByUsernameAndOrderTimeAfterOrderByIdDesc(username, time, pageRequest);
    }
    
    public Page<TdOrder> findByUsernameAndTimeAfterAndSearch(String username, Date time, String keywords, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByUsernameAndOrderTimeAfterAndOrderNumberContainingOrderByIdDesc(username, time, keywords, pageRequest);
    }
    
    public Page<TdOrder> findByUsernameAndSearch(String username, String keywords, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByUsernameAndOrderNumberContainingOrderByIdDesc(username, keywords, pageRequest);
    }
    
    public Page<TdOrder> findByUsernameAndStatusId(String username, long statusId, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByUsernameAndStatusIdOrderByIdDesc(username, statusId, pageRequest);
    }
    
    public Page<TdOrder> findByUsernameAndStatusIdAndSearch(String username, long statusId, String keywords, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByUsernameAndStatusIdAndOrderNumberContainingOrderByIdDesc(username, statusId, keywords, pageRequest);
    }
    
    public Page<TdOrder> findByUsernameAndStatusIdAndTimeAfter(String username, long statusId, Date time, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByUsernameAndStatusIdAndOrderTimeAfterOrderByIdDesc(username, statusId, time, pageRequest);
    }
    
    public Page<TdOrder> findByUsernameAndStatusIdAndTimeAfterAndSearch(String username, long statusId, Date time, String keywords, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByUsernameAndStatusIdAndOrderTimeAfterAndOrderNumberContainingOrderByIdDesc(username, statusId, time, keywords, pageRequest);
    }
    
    public Long countByUsernameAndStatusId(String username, long statusId)
    {
        return repository.countByUsernameAndStatusId(username, statusId);
    }
    
    public Long countByUsername(String username)
    {
        return repository.countByUsername(username);
    }
    
    /**
     * 保存
     * 
     * @param e
     * @return
     */
    public TdOrder save(TdOrder e)
    {
    	//如果订单价格为0 则进入下一个订单状态
//    	if (e.getStatusId().equals(2L) ) {
//			if (0 == e.getTotalPrice() && e.getTotalLeftPrice() != 0) {
//				if (null == e.getSmscode()) {
//					
//				}
//				e.setStatusId(3L);
//			}else if (0 == e.getTotalPrice() && e.getTotalLeftPrice() == 0) {
//				e.setStatusId(4L);
//			}
//		}else if (e.getStatusId().equals(3L)) {
//			if (0 == e.getTotalLeftPrice()) {
//				e.setStatusId(4L);
//			}
//		}
        
        return repository.save(e);
    }
       
    
    
    public List<TdOrder> save(List<TdOrder> entities)
    {
        
        return (List<TdOrder>) repository.save(entities);
    }
    /**
	 * @author lichong
	 * @注释：
	 */
    public Page<TdOrder> findByDiysitename(String diysitename, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByshopTitleOrderByIdDesc(diysitename, pageRequest);
    }
    public Page<TdOrder> findByDiysitenameAndSearch(String diysitename, String keywords, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByshopTitleAndOrderNumberContainingOrderByIdDesc(diysitename, keywords, pageRequest);
    }
    public Page<TdOrder> findByDiysitenameAndStatusIdAndSearch(String diysitename, long statusId, String keywords, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByshopTitleAndStatusIdAndOrderNumberContainingOrderByIdDesc(diysitename, statusId, keywords, pageRequest);
    }
    public Page<TdOrder> findByDiysitenameAndStatusId(String diysitename, long statusId, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByshopTitleAndStatusIdOrderByIdDesc(diysitename, statusId, pageRequest);
    }
    public Page<TdOrder> findByDiysitenameAndTimeAfter(String diysitename, Date time, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByshopTitleAndOrderTimeAfterOrderByIdDesc(diysitename, time, pageRequest);
    }
    public Page<TdOrder> findByDiysitenameAndTimeAfterAndSearch(String diysitename, Date time, String keywords, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByshopTitleAndOrderTimeAfterAndOrderNumberContainingOrderByIdDesc(diysitename, time, keywords, pageRequest);
    }
    public Page<TdOrder> findByDiysitenameAndStatusIdAndTimeAfterAndSearch(String diysitename, long statusId, Date time, String keywords, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByshopTitleAndStatusIdAndOrderTimeAfterAndOrderNumberContainingOrderByIdDesc(diysitename, statusId, time, keywords, pageRequest);
    }
    public Page<TdOrder> findByDiysitenameAndStatusIdAndTimeAfter(String diysitename, long statusId, Date time, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByshopTitleAndStatusIdAndOrderTimeAfterOrderByIdDesc(diysitename, statusId, time, pageRequest);
    }
    
    /**
	 * @author lc
	 * @注释：线下同盟店信息
	 */
    public List<TdOrder> findByshopIdAndstatusId(long shopId, long statusId){
    	return repository.findByShopIdAndStatusIdOrderByIdDesc(shopId, statusId);
    }
    /**
	 * @author lc
	 * @注释：订单收入查询
	 */
    public List<TdOrder> findAllVerifyBelongShopTitle(String diysitename){
    	return repository.findByStatusIdAndShopTitleOrStatusIdAndShopTitle(5L, diysitename, 6L, diysitename);
    }
    public Page<TdOrder> findAllVerifyBelongShopTitleOrderByIdDesc(String diysitename, int page, int size){
    	PageRequest pageRequest = new PageRequest(page, size);
    	return repository.findByStatusIdAndShopTitleOrStatusIdAndShopTitleOrderByIdDesc(5L, diysitename, 6L, diysitename, pageRequest);
    }
    public List<TdOrder> findAllVerifyBelongShopTitleAndTimeAfter(String diysitename, Date time){
    	return repository.findByStatusIdAndShopTitleAndOrderTimeAfterOrStatusIdAndShopTitleAndOrderTimeAfterOrderByIdDesc(5L, diysitename, time, 6L, diysitename, time);
    }
    public Page<TdOrder> findAllVerifyBelongShopTitleTimeAfterOrderByIdDesc(String diysitename, Date time,  int page, int size){
    	PageRequest pageRequest = new PageRequest(page, size);
    	return repository.findByStatusIdAndShopTitleAndOrderTimeAfterOrStatusIdAndShopTitleAndOrderTimeAfterOrderByIdDesc(5L, diysitename, time, 6L, diysitename, time, pageRequest);
    }
    
    /**
	 * @author lc
	 * @注释：订单返利查询
	 */
    public List<TdOrder> findByUsernameIn(List<String> tdUsers){
    	return repository.findByUsernameIn(tdUsers);
    }
    public Page<TdOrder> findByUsernameIn(List<String> tdUsers, int page, int size){
    	PageRequest pageRequest = new PageRequest(page, size);
    	return repository.findByUsernameInOrderByIdDesc(tdUsers, pageRequest);
    }
    
    public List<TdOrder> findByUsernameInAndOrderTimeAfter(List<String> tdUsers, Date time){
    	return repository.findByUsernameInAndOrderTimeAfterOrderByIdDesc(tdUsers, time);
    }
    public Page<TdOrder> findByUsernameInAndOrderTimeAfter(List<String> tdUsers, Date time, int page, int size){
    	PageRequest pageRequest = new PageRequest(page, size);
    	return repository.findByUsernameInAndOrderTimeAfterOrderByIdDesc(tdUsers, time, pageRequest);
    }
    /**
	 * @author lc
	 * @注释：根据订单类型和订单状态进行查询
	 */
    public Page<TdOrder> findByStatusAndTypeOrderByIdDesc(long statusId, long typeId, int page, int size){
    	PageRequest pageRequest = new PageRequest(page, size);
    	return repository.findByStatusIdAndTypeIdOrderByIdDesc(statusId, typeId, pageRequest);
    }
    public List<TdOrder> findByStatusAndTypeIdOrderByIdDesc(long statusId, long typeId){
    	return repository.findByStatusIdAndTypeIdOrderByIdDesc(statusId, typeId);
    }
    public Page<TdOrder> findByStatusOrderByIdDesc(long StatusId, int page, int size){
    	PageRequest pageRequest = new PageRequest(page, size);
    	return repository.findByStatusIdOrderByIdDesc(StatusId, pageRequest);
    }
    public List<TdOrder> findByStatusOrderByIdDesc(long StatusId){
    	return repository.findByStatusIdOrderByIdDesc(StatusId);
    }
    public Page<TdOrder> findBytypeIdOrderByIdDesc(long typeId, int page, int size){
    	PageRequest pageRequest = new PageRequest(page, size);
    	return repository.findBytypeIdOrderByIdDesc(typeId, pageRequest);
    }
    public List<TdOrder> findBytypeIdOrderByIdDesc(long typeId){
    	return repository.findBytypeIdOrderByIdDesc(typeId);
    }
    
    
    /**
	 * @author lc
	 * @注释 按时间、订单类型和订单状态查询
	 */
    public Page<TdOrder> findByTimeAfterOrderByIdDesc(Date time, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByOrderTimeAfterOrderByIdDesc(time, pageRequest);
    }
    public List<TdOrder> findByTimeAfterOrderByIdDesc(Date time){
    	return repository.findByOrderTimeAfterOrderByIdDesc(time);
    }
    
    public Page<TdOrder> findByStatusIdAndTypeIdAndTimeAfterOrderByIdDesc(long statusId, long typeId, Date time, int page, int size){
    	PageRequest pageRequest = new PageRequest(page, size);
    	return repository.findByStatusIdAndTypeIdAndOrderTimeAfterOrderByIdDesc(statusId, typeId, time, pageRequest);
    }
    public List<TdOrder> findByStatusAndTypeIdAndTimeAfterOrderByIdDesc(long statusId, long typeId, Date time){
    	return repository.findByStatusIdAndTypeIdAndOrderTimeAfterOrderByIdDesc(statusId, typeId, time);
    }
    public Page<TdOrder> findByStatusAndTimeAfterOrderByIdDesc(long StatusId, Date time, int page, int size){
    	PageRequest pageRequest = new PageRequest(page, size);
    	return repository.findByStatusIdAndOrderTimeAfterOrderByIdDesc(StatusId, time, pageRequest);
    }
    public List<TdOrder> findByStatusAndTimeAfterOrderByIdDesc(long StatusId, Date time){
    	return repository.findByStatusIdAndOrderTimeAfterOrderByIdDesc(StatusId, time);
    }
    public Page<TdOrder> findBytypeIdAndTimeAfterOrderByIdDesc(long typeId, Date time, int page, int size){
    	PageRequest pageRequest = new PageRequest(page, size);
    	return repository.findBytypeIdAndOrderTimeAfterOrderByIdDesc(typeId, time, pageRequest);
    }
    public List<TdOrder> findBytypeIdAndTimeAfterOrderByIdDesc(long typeId, Date time){
    	return repository.findBytypeIdAndOrderTimeAfterOrderByIdDesc(typeId, time);
    }
    /**
     * 按交易状态查询
     * @author libiao
     */
    public List<TdOrder> findByStatusId(Long statusId){
    	return repository.findByStatusId(statusId);
    }
    
    public List<TdOrder> findAll(Long statusId){
    	return (List<TdOrder>) repository.findAll();
    }
    
    /**
     * 订单编号的各类排序
     * @author libiao
     * 
     * @param orderNumber
     * @param page
     * @param size
     * @return
     */
    public Page<TdOrder> searchByOrderNumber(String orderNumber,int page,int size){
    	if(null == orderNumber){
    		return null;
    	}
    	PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
    	return repository.findByOrderNumberContainingIgnoreCaseOrUsernameContainingIgnoreCaseOrPayTypeTitleContainingIgnoreCaseOrOrderTypeContainingIgnoreCase(orderNumber, orderNumber, orderNumber,orderNumber, pageRequest);
    }
    
    public List<TdOrder> searchByOrderNumberAndTypeIdOrderByIdDesc(String keywords,Long type){
    	if(null == keywords){
    		return null;
    	}
    	return repository.findByOrderNumberContainingIgnoreCaseOrUsernameContainingIgnoreCaseOrPayTypeTitleContainingIgnoreCaseOrOrderTypeContainingIgnoreCaseOrderByIdDesc(keywords, keywords, keywords,keywords, type);
    }
    public Page<TdOrder> searchByOrderNumberAndTypeIdOrderByIdDesc(String keywords,Long type,int page, int size){
    	if(null == keywords){
    		return null;
    	}
    	PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
    	return repository.findByOrderNumberContainingIgnoreCaseAndTypeIdOrUsernameContainingIgnoreCaseAndTypeIdOrPayTypeTitleContainingIgnoreCaseAndTypeIdOrOrderTypeContainingIgnoreCaseAndTypeId(keywords, type, keywords, type, keywords, type,  keywords, type,pageRequest);
    }
    
    public Page<TdOrder> searchByOrderNumberAndStatusOrderByIdDesc(String keywords,long StatusId, int page, int size){
    	if(null == keywords){
    		return null;
    	}
    	PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
    	return repository.findByOrderNumberContainingIgnoreCaseAndStatusIdOrUsernameContainingIgnoreCaseAndStatusIdOrPayTypeTitleContainingIgnoreCaseAndStatusIdOrOrderTypeContainingIgnoreCaseAndStatusIdOrderByIdDesc(keywords, StatusId, keywords, StatusId, keywords, StatusId, keywords,StatusId, pageRequest);
    }
    
    public List<TdOrder> searchByOrderNumberAndStatusOrderByIdDesc(String keywords,long StatusId){
    	if(null == keywords){
    		return null;
    	}
    	return repository.findByOrderNumberContainingIgnoreCaseAndStatusIdOrUsernameContainingIgnoreCaseAndStatusIdOrPayTypeTitleContainingIgnoreCaseAndStatusIdOrOrderTypeContainingIgnoreCaseAndStatusIdOrderByIdDesc(keywords, StatusId, keywords, StatusId, keywords, StatusId,keywords, StatusId);
    }
    
    public Page<TdOrder> searchByOrderNumberAndStatusAndTypeOrderByIdDesc(String orderNumber,long statusId, long typeId, int page, int size){
    	if(null == orderNumber){
    		return null;
    	}
    	PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
    	return repository.findByOrderNumberContainingIgnoreCaseAndStatusIdAndTypeIdOrUsernameContainingIgnoreCaseAndStatusIdAndTypeIdOrPayTypeTitleContainingIgnoreCaseAndStatusIdAndTypeIdOrOrderTypeContainingIgnoreCaseAndStatusIdAndTypeId(orderNumber,statusId, typeId, orderNumber,statusId, typeId, orderNumber,statusId, typeId,  orderNumber,statusId, typeId, pageRequest);
    }
    public List<TdOrder> searchByOrderNumberAndStatusAndTypeIdOrderByIdDesc(String orderNumber,long statusId, long typeId){
    	if(null == orderNumber){
    		return null;
    	}
    	return repository.findByOrderNumberContainingIgnoreCaseAndStatusIdAndTypeIdOrUsernameContainingIgnoreCaseAndStatusIdAndTypeIdOrPayTypeTitleContainingIgnoreCaseAndStatusIdAndTypeIdOrOrderTypeContainingIgnoreCaseAndStatusIdAndTypeIdOrderByIdDesc(orderNumber,statusId, typeId, orderNumber,statusId, typeId,orderNumber,statusId, typeId, orderNumber,statusId, typeId);
    }
    
    public Page<TdOrder> searchByOrderNumberAndTimeAfterOrderByIdDesc(String orderNumber,Date time, int page, int size)
    {
    	if(null == orderNumber){
    		return null;
    	}
    	PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
        
        return repository.findByOrderNumberContainingIgnoreCaseAndOrderTimeAfterOrUsernameContainingIgnoreCaseAndOrderTimeAfterOrPayTypeTitleContainingIgnoreCaseAndOrderTimeAfterOrOrderTypeContainingIgnoreCaseAndOrderTimeAfter(orderNumber, time, orderNumber, time,  orderNumber, time,orderNumber, time, pageRequest);
    }
    public List<TdOrder> searchByOrderNumberAndTimeAfterOrderByIdDesc(String orderNumber,Date time){
    	if(null == orderNumber){
    		return null;
    	}
    	return repository.findByOrderNumberContainingIgnoreCaseAndOrderTimeAfterOrUsernameContainingIgnoreCaseAndOrderTimeAfterOrPayTypeTitleContainingIgnoreCaseAndOrderTimeAfterOrOrderTypeContainingIgnoreCaseAndOrderTimeAfterOrderByIdDesc(orderNumber, time, orderNumber, time, orderNumber, time,orderNumber, time);
    }
    
    public Page<TdOrder> searchByOrderNumberAndtypeIdAndTimeAfterOrderByIdDesc(String orderNumber,long typeId, Date time, int page, int size){
    	if(null == orderNumber){
    		return null;
    	}
    	PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
    	return repository.findByOrderNumberContainingIgnoreCaseAndTypeIdAndOrderTimeAfterOrUsernameContainingIgnoreCaseAndTypeIdAndOrderTimeAfterOrPayTypeTitleContainingIgnoreCaseAndTypeIdAndOrderTimeAfterOrOrderTypeContainingIgnoreCaseAndTypeIdAndOrderTimeAfter(orderNumber,typeId, time, orderNumber,typeId, time, orderNumber,typeId, time, orderNumber,typeId, time,pageRequest);
    }
    public List<TdOrder> searchByOrderNumberAndtypeIdAndTimeAfterOrderByIdDesc(String orderNumber,long typeId, Date time){
    	if(null == orderNumber){
    		return null;
    	}
    	return repository.findByOrderNumberContainingIgnoreCaseAndTypeIdAndOrderTimeAfterOrUsernameContainingIgnoreCaseAndTypeIdAndOrderTimeAfterOrPayTypeTitleContainingIgnoreCaseAndTypeIdAndOrderTimeAfterOrOrderTypeContainingIgnoreCaseAndTypeIdAndOrderTimeAfterOrderByIdDesc(orderNumber,typeId, time,orderNumber,typeId, time, orderNumber,typeId, time, orderNumber,typeId, time);
    }
    
    public Page<TdOrder> searchByOrderNumberAndStatusAndTimeAfterOrderByIdDesc(String orderNumber,long StatusId, Date time, int page, int size){
    	if(null == orderNumber){
    		return null;
    	}
    	PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
    	return repository.findByOrderNumberContainingIgnoreCaseAndStatusIdAndOrderTimeAfterOrUsernameContainingIgnoreCaseAndStatusIdAndOrderTimeAfterOrPayTypeTitleContainingIgnoreCaseAndStatusIdAndOrderTimeAfterOrOrderTypeContainingIgnoreCaseAndStatusIdAndOrderTimeAfter(orderNumber,StatusId, time, orderNumber,StatusId, time, orderNumber,StatusId, time, orderNumber,StatusId, time, pageRequest);
    }
    public List<TdOrder> searchByOrderNumberAndStatusAndTimeAfterOrderByIdDesc(String orderNumber,long StatusId, Date time){
    	if(null == orderNumber){
    		return null;
    	}
    	return repository.findByOrderNumberContainingIgnoreCaseAndStatusIdAndOrderTimeAfterOrUsernameContainingIgnoreCaseAndStatusIdAndOrderTimeAfterOrPayTypeTitleContainingIgnoreCaseAndStatusIdAndOrderTimeAfterOrOrderTypeContainingIgnoreCaseAndStatusIdAndOrderTimeAfterOrderByIdDesc(orderNumber,StatusId, time, orderNumber,StatusId, time,orderNumber,StatusId, time,  orderNumber,StatusId, time);
    }
    public Page<TdOrder> searchByOrderNumberAndStatusIdAndTypeIdAndTimeAfterOrderByIdDesc(String orderNumber,long statusId, long typeId, Date time, int page, int size){
    	if(null == orderNumber){
    		return null;
    	}
    	PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
    	return repository.findByOrderNumberContainingIgnoreCaseAndStatusIdAndTypeIdAndOrderTimeAfterOrUsernameContainingIgnoreCaseAndStatusIdAndTypeIdAndOrderTimeAfterOrPayTypeTitleContainingIgnoreCaseAndStatusIdAndTypeIdAndOrderTimeAfterOrOrderTypeContainingIgnoreCaseAndStatusIdAndTypeIdAndOrderTimeAfter(orderNumber,statusId, typeId, time, orderNumber,statusId, typeId, time,orderNumber,statusId, typeId, time, orderNumber,statusId, typeId, time, pageRequest);
    }
    public List<TdOrder> searchByOrderNumberAndStatusAndTypeIdAndTimeAfterOrderByIdDesc(String orderNumber,long statusId, long typeId, Date time){
    	if(null == orderNumber){
    		return null;
    	}
    	return repository.findByOrderNumberContainingIgnoreCaseAndStatusIdAndTypeIdAndOrderTimeAfterOrUsernameContainingIgnoreCaseAndStatusIdAndTypeIdAndOrderTimeAfterOrPayTypeTitleContainingIgnoreCaseAndStatusIdAndTypeIdAndOrderTimeAfterOrOrderTypeContainingIgnoreCaseAndStatusIdAndTypeIdAndOrderTimeAfterOrderByIdDesc(orderNumber,statusId, typeId, time, orderNumber,statusId, typeId, time,orderNumber,statusId, typeId, time, orderNumber,statusId, typeId, time);
    }

	public Page<TdOrder> findByUsernameAndOrderTypeAndStatusId(String username, String orderType, long l, Integer page,
			int pagesize) {
		PageRequest pageRequest = new PageRequest(page, pagesize);
		return repository.findByUsernameAndOrderTypeAndStatusIdOrderByIdDesc(username,orderType,l,pageRequest);
	}

	public Page<TdOrder> findByUsernameAndOrderNumberAndStatusId(String username, String orderNumber, long l,
			Integer page, int pagesize) {
		PageRequest pageRequest = new PageRequest(page, pagesize);
		return repository.findByUsernameAndOrderNumberAndStatusIdOrderByIdDesc(username,orderNumber,l,pageRequest);
	}

	public Page<TdOrder> findByOrderNumberAndOrderTypeAndStatusId(String orderNumber, String orderType, long l, Integer page,
			int pagesize) {
		PageRequest pageRequest = new PageRequest(page, pagesize);
		return repository.findByOrderNumberAndOrderTypeAndStatusIdOrderByIdDesc(orderNumber,orderType,l,pageRequest);
	}

	public Page<TdOrder> findByUsernameAndOrderType(String username, String orderType, Integer page, int pagesize) {
		PageRequest pageRequest = new PageRequest(page, pagesize);
		return repository.findByUsernameAndOrderTypeOrderByIdDesc(username,orderType,pageRequest);
	}

	public Page<TdOrder> findByUsernameAndOrderNumber(String username, String orderNumber, Integer page, int pagesize) {
		PageRequest pageRequest = new PageRequest(page, pagesize);
		return repository.findByUsernameAndOrderNumberOrderByIdDesc(username,orderNumber,pageRequest);
	}

	public Page<TdOrder> findByOrderNumberAndOrderType(String orderNumber, String orderType, Integer page, int pagesize) {
		PageRequest pageRequest = new PageRequest(page, pagesize);
		return repository.findByOrderNumberAndOrderTypeOrderByIdDesc(orderNumber,orderType,pageRequest);
	}

	public TdOrder findById(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	public Page<TdOrder> findByTypeIdAndOrderTypeOrderByIdDesc(long typeId,String string, Integer page, Integer size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByTypeIdAndOrderTypeOrderByIdDesc(typeId,string,pageRequest);
	}

	public Page<TdOrder> searchAndOrderBySortIdAsc(String keywords, Integer page, Integer size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByShippingNameContainingOrOrderNumberContainingOrGoodsTitleContainingOrShippingPhoneContainingOrderByIdDesc(keywords, keywords, keywords, keywords, pageRequest);
	}

//	public Page<TdOrder> findByTypeIdOrderByIdDesc(long l, Integer page, Integer size) {
//		PageRequest pageRequest = new PageRequest(page, size);
//		return repository.findByTypeIdOrderByIdDesc(l,pageRequest);
//	}

    /**
     * 后台订单筛选
     * @author Max
     */
	public Page<TdOrder> searchByOrderNumberAndOrderTimeBefore(String orderNumber,Date endTime,int page,int size){
    	if(null == orderNumber){
    		return null;
    	}
    	PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
    	return repository.findByOrderNumberContainingIgnoreCaseAndOrderTimeBeforeOrUsernameContainingIgnoreCaseAndOrderTimeBefore(orderNumber, endTime, orderNumber, endTime, pageRequest);
    }
    
    public Page<TdOrder> findByOrderTimeBeforeOrderByIdDesc(Date orderTime,int page,int size)
    {
    	PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
    	return repository.findByOrderTimeBefore(orderTime, pageRequest);
    }
    
    public Page<TdOrder> searchByOrderNumberAndStatusAndOrderTimeBeforeOrderByIdDesc(String keywords,long StatusId,Date endTime, int page, int size){
    	if(null == keywords){
    		return null;
    	}
    	PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
    	return repository.findByOrderNumberContainingIgnoreCaseAndStatusIdAndOrderTimeBeforeOrUsernameContainingIgnoreCaseAndStatusIdAndOrderTimeBefore(keywords, StatusId,endTime, keywords, StatusId,endTime, pageRequest);
    }
    
    public Page<TdOrder> findByStatusAndOrderTimeBeforeOrderByIdDesc(long StatusId,Date endTime, int page, int size){
    	PageRequest pageRequest = new PageRequest(page, size,new Sort(Direction.DESC, "id"));
    	return repository.findByStatusIdAndOrderTimeBefore(StatusId,endTime, pageRequest);
    }
    
    // ============================================================================================================================
    // ============================================================================================================================
    /**
     * 
     *  增加起始时间的筛选
     *  
     */
    public Page<TdOrder> searchByOrderNumberAndOrderTimeAfter(String orderNumber,Date startTime,int page,int size){
    	if(null == orderNumber){
    		return null;
    	}
    	PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
    	return repository.findByOrderNumberContainingIgnoreCaseAndOrderTimeAfterOrUsernameContainingIgnoreCaseAndOrderTimeAfter(orderNumber, startTime, orderNumber, startTime, pageRequest);
    }
    
    public Page<TdOrder> findByOrderTimeAfterOrderByIdDesc(Date orderTime,int page,int size)
    {
    	PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
    	return repository.findByOrderTimeAfter(orderTime, pageRequest);
    }
    
    public Page<TdOrder> searchByOrderNumberAndStatusAndOrderTimeAfterOrderByIdDesc(String keywords,long StatusId,Date orderTime, int page, int size){
    	if(null == keywords){
    		return null;
    	}
    	PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
    	return repository.findByOrderNumberContainingIgnoreCaseAndStatusIdAndOrderTimeAfterOrUsernameContainingIgnoreCaseAndStatusIdAndOrderTimeAfter(keywords, StatusId, orderTime, keywords, StatusId, orderTime, pageRequest);
    }
    
    public Page<TdOrder> findByStatusAndOrderTimeAfterOrderByIdDesc(long StatusId,Date orderTime, int page, int size){
    	PageRequest pageRequest = new PageRequest(page, size,new Sort(Direction.DESC, "id"));
    	return repository.findByStatusIdAndOrderTimeAfter(StatusId,orderTime, pageRequest);
    }
    
    // ↓↓↓↓↓↓↓ 起始时间+截止时间  ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    
    public Page<TdOrder> searchByOrderNumberAndOrderTimeDetween(String orderNumber,Date startTime,Date endTime,int page,int size){
    	if(null == orderNumber){
    		return null;
    	}
    	PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
    	return repository.findByOrderNumberContainingIgnoreCaseAndOrderTimeBetweenOrUsernameContainingIgnoreCaseAndOrderTimeBetween(orderNumber, startTime, endTime, orderNumber, startTime, endTime, pageRequest);
    }
    
    public Page<TdOrder> findByOrderTimeBetweenOrderByIdDesc(Date startTime,Date endTime,int page,int size)
    {
    	PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
    	return repository.findByOrderTimeBetween(startTime, endTime, pageRequest);
    }
    
    public Page<TdOrder> searchByOrderNumberAndStatusAndOrderTimeBetweenOrderByIdDesc(String keywords,long StatusId,Date startTime,Date endTime, int page, int size){
    	if(null == keywords){
    		return null;
    	}
    	PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
    	return repository.findByOrderNumberContainingIgnoreCaseAndStatusIdAndOrderTimeBetweenOrUsernameContainingIgnoreCaseAndStatusIdAndOrderTimeBetween(
    																		keywords, StatusId, startTime, endTime,
    																		keywords, StatusId, startTime, endTime, pageRequest);
    }
    
    public Page<TdOrder> findByStatusAndOrderTimeBetweenOrderByIdDesc(long StatusId,Date startTime,Date endTime, int page, int size){
    	PageRequest pageRequest = new PageRequest(page, size,new Sort(Direction.DESC, "id"));
    	return repository.findByStatusIdAndOrderTimeBetween(StatusId,startTime,endTime, pageRequest);
    }
    
    // ============================================================================================
    // ============================================================================================
    /**
     *  订单类型
     *  
     */
    public Page<TdOrder> searchByOrderNumberAndTypeId(String orderNumber,Long typeId,int page,int size){
    	if(null == orderNumber){
    		return null;
    	}
    	PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
    	return repository.findByOrderNumberContainingIgnoreCaseAndTypeIdOrUsernameContainingIgnoreCaseAndTypeId(orderNumber,typeId, orderNumber,typeId, pageRequest);
    }
    
    public Page<TdOrder> findByTypeIdOrderByIdDesc(Long typeId,int page,int size)
    {
    	PageRequest pageRequest = new PageRequest(page, size);
    	return repository.findByTypeIdOrderByIdDesc(typeId, pageRequest);
    }
    
    public Page<TdOrder> searchByOrderNumberAndStatusAndTypeIdOrderByIdDesc(String keywords,long statusId,Long typeId, int page, int size){
    	if(null == keywords){
    		return null;
    	}
    	PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
    	return repository.findByOrderNumberContainingIgnoreCaseAndStatusIdAndTypeIdOrUsernameContainingIgnoreCaseAndStatusIdAndTypeId(keywords, statusId,typeId, keywords, statusId,typeId, pageRequest);
    }
    
    public Page<TdOrder> searchByOrderNumberAndTypeIdAndOrderTimeBefore(String orderNumber,Long typeId,Date endTime,int page,int size){
    	if(null == orderNumber){
    		return null;
    	}
    	PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
    	return repository.findByOrderNumberContainingIgnoreCaseAndTypeIdAndOrderTimeBeforeOrUsernameContainingIgnoreCaseAndAndTypeIdAndOrderTimeBefore(orderNumber, typeId,endTime, orderNumber,typeId, endTime, pageRequest);
    }
    
    public Page<TdOrder> findByTypeIdAndOrderTimeBeforeOrderByIdDesc(Long typeId,Date endTime,int page,int size)
    {
    	PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
    	return repository.findByTypeIdAndOrderTimeBefore(typeId, endTime, pageRequest);
    }
    
    public Page<TdOrder> searchByOrderNumberAndStatusAndTypeIdAndOrderTimeBeforeOrderByIdDesc(String keywords,long StatusId, Long typeId, Date endTime, int page, int size){
    	if(null == keywords){
    		return null;
    	}
    	PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
    	return repository.findByOrderNumberContainingIgnoreCaseAndStatusIdAndTypeIdAndOrderTimeBeforeOrUsernameContainingIgnoreCaseAndStatusIdAndTypeIdAndOrderTimeBefore(keywords, StatusId, typeId, endTime, keywords, StatusId, typeId ,endTime, pageRequest);
    }
    
    public Page<TdOrder> findByStatusAndTypeIdAndOrderTimeBeforeOrderByIdDesc(long StatusId,Long typeId,Date endTime, int page, int size){
    	PageRequest pageRequest = new PageRequest(page, size,new Sort(Direction.DESC, "id"));
    	return repository.findByStatusIdAndTypeIdAndOrderTimeBefore(StatusId, typeId ,endTime, pageRequest);
    }
    
    public Page<TdOrder> searchByOrderNumberAndTypeIdAndOrderTimeAfter(String orderNumber, Long typeId, Date startTime,int page,int size){
    	if(null == orderNumber){
    		return null;
    	}
    	PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
    	return repository.findByOrderNumberContainingIgnoreCaseAndTypeIdAndOrderTimeAfterOrUsernameContainingIgnoreCaseAndTypeIdAndOrderTimeAfter(orderNumber,typeId, startTime, orderNumber,typeId, startTime, pageRequest);
    }
    
    public Page<TdOrder> findByTypeIdAndOrderTimeAfterOrderByIdDesc(Long typeId,Date orderTime,int page,int size)
    {
    	PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
    	return repository.findByTypeIdAndOrderTimeAfter(typeId, orderTime, pageRequest);
    }
    
    public Page<TdOrder> searchByOrderNumberAndStatusAndTypeIdAndOrderTimeAfterOrderByIdDesc(String keywords,long StatusId, Long typeId, Date orderTime, int page, int size){
    	if(null == keywords){
    		return null;
    	}
    	PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
    	return repository.findByOrderNumberContainingIgnoreCaseAndStatusIdAndTypeIdAndOrderTimeAfterOrUsernameContainingIgnoreCaseAndStatusIdAndTypeIdAndOrderTimeAfter(keywords, StatusId, typeId, orderTime, keywords, StatusId, typeId, orderTime, pageRequest);
    }
    
    public Page<TdOrder> findByStatusAndTypeIdAndOrderTimeAfterOrderByIdDesc(long StatusId, Long typeId, Date orderTime, int page, int size){
    	PageRequest pageRequest = new PageRequest(page, size,new Sort(Direction.DESC, "id"));
    	return repository.findByStatusIdAndTypeIdAndOrderTimeAfter(StatusId, typeId, orderTime, pageRequest);
    }
     
    public Page<TdOrder> searchByOrderNumberAndTypeIdAndOrderTimeDetween(String orderNumber,Long typeId, Date startTime,Date endTime,int page,int size){
    	if(null == orderNumber){
    		return null;
    	}
    	PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
    	return repository.findByOrderNumberContainingIgnoreCaseAndTypeIdAndOrderTimeBetweenOrUsernameContainingIgnoreCaseAndTypeIdAndOrderTimeBetween(orderNumber,typeId, startTime, endTime, orderNumber,typeId, startTime, endTime, pageRequest);
    }
    
    public Page<TdOrder> findByTypeIdAndOrderTimeBetweenOrderByIdDesc(Long typeId,Date startTime,Date endTime,int page,int size)
    {
    	PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
    	return repository.findByTypeIdAndOrderTimeBetween(typeId, startTime, endTime, pageRequest);
    }
    
    public Page<TdOrder> searchByOrderNumberAndStatusAndTypeIdAndOrderTimeBetweenOrderByIdDesc(String keywords,long StatusId, Long typeId, Date startTime,Date endTime, int page, int size){
    	if(null == keywords){
    		return null;
    	}
    	PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
    	return repository.findByOrderNumberContainingIgnoreCaseAndStatusIdAndTypeIdAndOrderTimeBetweenOrUsernameContainingIgnoreCaseAndStatusIdAndTypeIdAndOrderTimeBetween(
    																		keywords, StatusId, typeId, startTime, endTime,
    																		keywords, StatusId, typeId, startTime, endTime, pageRequest);
    }
    
    public Page<TdOrder> findByStatusAndTypeIdAndOrderTimeBetweenOrderByIdDesc(long StatusId, Long typeId, Date startTime,Date endTime, int page, int size){
    	PageRequest pageRequest = new PageRequest(page, size,new Sort(Direction.DESC, "id"));
    	return repository.findByStatusIdAndTypeIdAndOrderTimeBetween(StatusId,typeId, startTime,endTime, pageRequest);
    }
    
    public Page<TdOrder> findAll(String username,Long statusId,String orderNumber,String orderType,int page,int size)
    {
    	PageRequest pageRequest = new PageRequest(page, size);
    	Criteria<TdOrder> c = new Criteria<>();
    	
    	if(null != username){
    		c.add(Restrictions.eq("username", username, true));
    	}
    	if(null != statusId && statusId != 0){
    		c.add(Restrictions.eq("statusId", statusId, true));
    	}
    	if(null != orderNumber && !orderNumber.isEmpty()){
    		c.add(Restrictions.like("orderNumber", orderNumber, true));
    	}
    	if(null != orderType && !orderType.isEmpty()){
    		c.add(Restrictions.like("orderType", orderType, true));
    	}
    	
    	return repository.findAll(c,pageRequest);
    }
    
}
