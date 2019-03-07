package com.ynyes.tianya.controller.front;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ynyes.tianya.entity.TdAd;
import com.ynyes.tianya.entity.TdAdType;
import com.ynyes.tianya.entity.TdGoods;
import com.ynyes.tianya.entity.TdParameter;
import com.ynyes.tianya.entity.TdProductCategory;
import com.ynyes.tianya.service.TdAdService;
import com.ynyes.tianya.service.TdAdTypeService;
import com.ynyes.tianya.service.TdCommonService;
import com.ynyes.tianya.service.TdGoodsService;
import com.ynyes.tianya.service.TdParameterService;
import com.ynyes.tianya.service.TdProductCategoryService;
import com.ynyes.tianya.util.ClientConstant;

/**
 * 邮轮俱乐部控制器
 *
 */
@Controller
@RequestMapping("/list")
public class TdListController {

	@Autowired
	private TdCommonService tdCommonService;

	@Autowired
	private TdGoodsService tdGoodsService;

//	@Autowired
//	private TdArticleService tdArticleService;
//
//	@Autowired
//	private TdArticleCategoryService tdArticleCategoryService;

	@Autowired
	private TdProductCategoryService tdProductCategoryService;

	@Autowired
	private TdParameterService tdParameterService;

	@Autowired
	private TdAdTypeService tdAdTypeService;

	@Autowired
	private TdAdService tdAdService;
	

//	@Autowired
//	private TdBrandService tdBrandService;

	// 组成：typeID-[3*paramIndex]-[排序字段]-[综合排序标志]-[价格排序标志]-[销量排序标志]-[页号]_[价格低值]-[价格高值]
	@RequestMapping("/{listStr}")
	public String ship(@PathVariable String listStr, HttpServletRequest req,
			Device device, ModelMap map,String keyword, String page, String pt, String customData) {
		tdCommonService.setCommon(map, req);
		if(customData != null){
			customData = customData.replace("_", "=");
			customData = customData.replace("|", "&");
			String[] cda = customData.split("&");
			for(String s : cda){
				String[] ss = s.split("=");
				if(ss.length == 2){
					String name = ss[0];
					String value = ss[1];
					map.addAttribute(name, value);
				}
				 
			}
		}
		
		
		
		
		map.addAttribute("pt", pt);
		
		String username=(String)req.getSession().getAttribute("username");

		map.addAttribute("keyword", keyword);
		
		if (null == listStr || "".equals(listStr)) {
			return "/client/error_404";
		}
		// 拼接页码
		listStr =  listStrSplicePage(listStr, page);
		map.addAttribute("listStr", listStr);
		
		

		// 首页轮播广告
		TdAdType adType = tdAdTypeService.findByTitle("首页轮播广告");
		//查询最顶端的那个广告的显示
		if (null != adType) {
			map.addAttribute("big_scroll_ad_list", tdAdService
					.findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType.getId()));
		}
		
		// 邮轮轮播广告
		TdAdType adType6 = tdAdTypeService.findByTitle("邮轮轮播广告");
		//查询最顶端的那个广告的显示
		if (null != adType6) {
			map.addAttribute("big_scroll_ad_list6", tdAdService
					.findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType6.getId()));
		}
		// 目的地轮播广告
		TdAdType adType1 = tdAdTypeService.findByTitle("目的地轮播广告");
		//查询最顶端的那个广告的显示
		if (null != adType1) {
			map.addAttribute("big_scroll_ad_list1", tdAdService
					.findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType1.getId()));
		}
		// 主题轮播广告
		TdAdType adType2 = tdAdTypeService.findByTitle("主题轮播广告");
		//查询最顶端的那个广告的显示
		if (null != adType2) {
			map.addAttribute("big_scroll_ad_list2", tdAdService
					.findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType2.getId()));
		}
		// 签证轮播广告
		TdAdType adType3 = tdAdTypeService.findByTitle("签证轮播广告");
		//查询最顶端的那个广告的显示
		if (null != adType3) {
			map.addAttribute("big_scroll_ad_list3", tdAdService
					.findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType3.getId()));
		}
		// 签证轮播广告
		TdAdType adType4 = tdAdTypeService.findByTitle("主题中间广告");
		//查询最顶端的那个广告的显示
		if (null != adType4) {
			map.addAttribute("big_scroll_ad_list4", tdAdService
					.findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType4.getId()));
		}
		
		// 私家定制轮播广告
		TdAdType adType5 = tdAdTypeService.findByTitle("私家定制轮播广告");
		//查询最顶端的那个广告的显示
		if (null != adType5) {
			map.addAttribute("big_scroll_ad_list5", tdAdService
					.findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType5.getId()));
		}
		// 私家定制轮播广告
		TdAdType adType7 = tdAdTypeService.findByTitle("特产商城轮播广告");
		//查询最顶端的那个广告的显示
		if (null != adType7) {
			map.addAttribute("big_scroll_ad_list7", tdAdService
					.findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType7.getId()));
		}
		
		//上面是前奏准备
		// 组成：typeID-[totalParams*paramIndex]-[排序字段]-[综合排序标志]-[价格排序标志]-[销量排序标志]-[页号]_[价格低值]-[价格高值]
		int totalParams = 3;
		int totalSorts = 3;
		
		Map<String, Long> idxMap = parseListStr(listStr, totalParams, totalSorts);
		//根据key值取typeId的值
		Long typeId = idxMap.get("typeId");
		
		if (null == typeId) {
			return "/client/error_404";
		}else{
			// 主题轮播，点击子主题，对应子主题广告为第一个轮播图片，轮播广告必须和子主题名称一样
			String subScrollTilte = tdProductCategoryService.findOne(typeId).getTitle();
			List<TdAd> adList =  sortAsSubtitle(tdAdService
					.findByTypeIdAndIsValidTrueOrderBySortIdAsc(adType2.getId()), subScrollTilte);
			map.addAttribute("big_scroll_ad_list2", adList);
			
		}

		map.addAttribute("type", typeId);
		// 商品类别
		TdProductCategory tpc = tdProductCategoryService
				.findOne(typeId);

		// 邮轮俱乐部
		TdProductCategory shipCat = tdProductCategoryService
				.findByTitle("邮轮俱乐部");
		// 目的地
		TdProductCategory destinationCat = tdProductCategoryService
				.findByTitle("目的地");
		// 主题活动
		TdProductCategory activityCat = tdProductCategoryService
				.findByTitle("主题活动");
		// 私家定制
		TdProductCategory customCat = tdProductCategoryService
				.findByTitle("私家定制");
		// 特产商城
		TdProductCategory specialtyCat = tdProductCategoryService
				.findByTitle("特产商城");
		// 签证
		TdProductCategory visaCat = tdProductCategoryService.findByTitle("签证");

		if (null != tpc) {
			map.addAttribute("category", tpc);

			// 获取该类型所有父类型
			if (null != tpc.getParentTree() && !"".equals(tpc.getParentTree())) {
				List<TdProductCategory> catList = new ArrayList<TdProductCategory>();
				//取出逗号
				for (String cid : tpc.getParentTree().split(",")) {
					if (!"".equals(cid)) {
						// 去除方括号
						cid = cid.replace("[", "");
						cid = cid.replace("]", "");

						TdProductCategory cat = tdProductCategoryService
								.findOne(Long.parseLong(cid));

						if (null != cat) {
							catList.add(cat);
						}
					}
				}

				map.addAttribute("category_tree_list", catList);
			}

			// 邮轮俱乐部
			if (null != shipCat
					&& tpc.getParentTree()
							.contains("[" + shipCat.getId() + "]")) {
				// 顶级菜单
				if (tpc.getLayerCount().equals(1L)) {
					// 热门航线
					map.addAttribute("hot_ship_page", tdGoodsService
							.findByCategoryIdAndIsHotTrueAndIsOnSaleTrue(
									tpc.getId(), 0, 5));
					map.addAttribute("ship_list_page", tdGoodsService
							.findByCategoryIdAndIsRecommendTypeTrueAndIsOnSaleTrueOrderByIdDesc(
									tpc.getId(), 0, 10));
					
					map.addAttribute("ship_cat_list", tdProductCategoryService
							.findByParentIdOrderBySortIdAsc(shipCat.getId()));

					return "/client/ship_index";
				}
				// 非顶级菜单
				else {
					// 查找所有二级分类 
					List<TdProductCategory> level2Cat = tdProductCategoryService
							.findByParentIdOrderBySortIdAsc(shipCat.getId());
					map.addAttribute("level2_cat_list", level2Cat);

					// 获取该类型所有父类型
					if (null != tpc) {
						if (null != tpc.getParentTree()
								&& !"".equals(tpc.getParentTree())) {
							List<TdProductCategory> catList = new ArrayList<TdProductCategory>();

							for (String cid : tpc.getParentTree().split(",")) {
								if (!"".equals(cid)) {
									// 去除方括号
									cid = cid.replace("[", "");
									cid = cid.replace("]", "");

									TdProductCategory cat = tdProductCategoryService
											.findOne(Long.parseLong(cid));

									if (null != cat) {
										catList.add(cat);
									}
								}
							}

							map.addAttribute("category_tree_list", catList);
						}
					}

					// 参数列表
					if (null != tpc.getParamCategoryId()) {
						Long paramCategoryId = tpc.getParamCategoryId();

						List<TdParameter> paramList = tdParameterService
								.findByCategoryTreeContainingAndIsSearchableTrue(paramCategoryId);
						
						// 必须重新解析一次
						idxMap = parseListStr(listStr, Math.min(totalParams, paramList.size()), totalSorts);
						
						map.addAttribute("total_params", Math.min(totalParams, paramList.size()));
						map.addAttribute("total_sorts", totalSorts);
						
						if (null != idxMap) {
							// 参数解析
							List<String> paramValueList = new ArrayList<String>();
							List<Long> paramIndexList = new ArrayList<Long>();
							for (int i = 0; i < Math.min(totalParams, paramList.size()); i++) {
								TdParameter param = paramList.get(i);
								Long paramIdx = idxMap.get("param" + i);

								if (null != paramIdx) {
									paramIndexList.add(paramIdx);

									if (paramIdx > 0
											&& null != param.getValueList()
											&& !"".equals(param.getValueList())) {
										String[] values = param.getValueList()
												.split(",");

										if (values.length >= paramIdx) {
											String value = values[paramIdx
													.intValue() - 1].trim();
											paramValueList.add(value);
										}
									}
								}
							} // for

							map.addAttribute("param_index_list", paramIndexList);
							map.addAttribute("param_list", paramList);

							// 排序序号
							Long sortId = idxMap.get("sortId");
							Long sortOrder; 

							// 排序
							if (null == sortId) {
								sortId = 0L;
							}
							
							map.addAttribute("sortId", sortId);
							
							sortOrder = idxMap.get("sort" + sortId);
							
							// 默认降序
							if (null == sortOrder)
							{
								sortOrder = 0L;
							}
							
							map.addAttribute("sortOrder", sortOrder);

							// 页号
							Long pageId = idxMap.get("pageId");
							// listStr没有页码，即无条件的listStr
							if (null == pageId) {
								if(page == null){
									pageId = 0L;									
								}else{
									pageId = Long.parseLong(page);
								}
							}
							map.addAttribute("page", pageId);
							
							
							
							map.addAttribute("pageId", pageId);

							String[] sortName = { "sortId", "salePrice", "soldNumber" };

							// 查找商品
							PageRequest pageRequest = null;

							// 0: 降序 1: 升序
							if (sortOrder.equals(0L)) {
								pageRequest = new PageRequest(
										pageId.intValue(),
										ClientConstant.pageSize,
										new Sort(Direction.DESC,
												sortName[sortId.intValue()])
												.and(new Sort(Direction.DESC,
														"id")));
							} else {
								pageRequest = new PageRequest(
										pageId.intValue(),
										ClientConstant.pageSize,
										new Sort(Direction.ASC, sortName[sortId
												.intValue()]).and(new Sort(
												Direction.DESC, "id")));
							}

							// 价格区间
							Long priceLow = idxMap.get("priceLow");
							Long priceHigh = idxMap.get("priceHigh");
							
							map.addAttribute("priceLow", priceLow);
							map.addAttribute("priceHigh", priceHigh);

							Page<TdGoods> goodsPage = null;
							
							if (null == priceHigh)
							{
								priceHigh = 9999999999L;
							}

							if (null != typeId) {
//								if (null == priceLow) {
//									goodsPage = tdGoodsService
//											.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
//													typeId, paramValueList,keyword,
//													pageRequest);
//								} else {
//									goodsPage = tdGoodsService
//											.findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
//													typeId, priceLow,
//													priceHigh,keyword, paramValueList,
//													pageRequest);
//								}
								if (null == priceLow) {
									if(null != keyword && !"".equalsIgnoreCase(keyword.trim()))
									{
										if(null != paramValueList && paramValueList.size()>0){
											goodsPage = tdGoodsService
													.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
															typeId, paramValueList,keyword,pageRequest);
										}else{
											goodsPage = tdGoodsService
													.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
															typeId,keyword,pageRequest);
										}
									}else{
										if(null != paramValueList && paramValueList.size()>0)
										{
											goodsPage = tdGoodsService
													.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
															typeId, paramValueList,pageRequest);
										}else{
											goodsPage = tdGoodsService
													.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
															typeId, pageRequest);
										}
									}
								} else {
									if(null !=keyword && !"".equalsIgnoreCase(keyword.trim()))
									{
										if(null != paramValueList && paramValueList.size()>0)
										{
											goodsPage = tdGoodsService
													.findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
															typeId, priceLow,priceHigh,keyword, paramValueList,pageRequest);
										}else{
											goodsPage = tdGoodsService
													.findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
															typeId, priceLow,priceHigh,keyword,pageRequest);
										}
									}else{
										if(null != paramValueList && paramValueList.size()>0)
										{
											goodsPage = tdGoodsService
													.findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
															typeId, priceLow,priceHigh, paramValueList,pageRequest);
										}else{
											goodsPage = tdGoodsService
													.findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
															typeId, priceLow,priceHigh,pageRequest);
										}
									}
								}
							}
							
							map.addAttribute("goods_page", goodsPage);

							// 查找商品
						}
					}
					map.addAttribute("cat", shipCat);
					// map.addAttribute("param_count", paramCount);
					// map.addAttribute("param_index_list", paramIndexList);
					return "client/ship_list";
				}
			}
			// 目的地
			else if (null != destinationCat
					&& tpc.getParentTree().contains(
							"[" + destinationCat.getId() + "]")) {
				// 顶级菜单
				if (tpc.getLayerCount().equals(1L)) {
					map.addAttribute("hot_destination_page", tdGoodsService
							.findByCategoryIdAndIsHotTrueAndIsOnSaleTrue(
									tpc.getId(), 0, 5));
					
					map.addAttribute("destination_list_page", tdGoodsService
							.findByCategoryIdAndIsRecommendTypeTrueAndIsOnSaleTrueOrderByIdDesc(
									tpc.getId(), 0, 15));
					//
					map.addAttribute("destination_cat_list" ,
							tdProductCategoryService.findByParentIdOrderBySortIdAsc(destinationCat.getId()));

					return "/client/destination_index";
				}
				// 非顶级菜单
				else {
					// 查找所有二级分类  这是二级分类
					List<TdProductCategory> level2Cat = tdProductCategoryService
							.findByParentIdOrderBySortIdAsc(destinationCat.getId());
					map.addAttribute("level2_cat_list", level2Cat);

					// 获取该类型所有父类型
					if (null != tpc) {
						if (null != tpc.getParentTree()
								&& !"".equals(tpc.getParentTree())) {
							List<TdProductCategory> catList = new ArrayList<TdProductCategory>();

							for (String cid : tpc.getParentTree().split(",")) {
								if (!"".equals(cid)) {
									// 去除方括号
									cid = cid.replace("[", "");
									cid = cid.replace("]", "");

									TdProductCategory cat = tdProductCategoryService
											.findOne(Long.parseLong(cid));

									if (null != cat) {
										catList.add(cat);
									}
								}
							}

							map.addAttribute("category_tree_list", catList);
						}
					}

					// 参数列表
					if (null != tpc.getParamCategoryId()) {
						Long paramCategoryId = tpc.getParamCategoryId();

						List<TdParameter> paramList = tdParameterService
								.findByCategoryTreeContainingAndIsSearchableTrue(paramCategoryId);
						
						// 必须重新解析一次
						idxMap = parseListStr(listStr, Math.min(totalParams, paramList.size()), totalSorts);
						
						map.addAttribute("total_params", Math.min(totalParams, paramList.size()));
						map.addAttribute("total_sorts", totalSorts);
						
						if (null != idxMap) {
							// 参数解析
							List<String> paramValueList = new ArrayList<String>();
							List<Long> paramIndexList = new ArrayList<Long>();
							for (int i = 0; i < Math.min(totalParams, paramList.size()); i++) {
								TdParameter param = paramList.get(i);
								Long paramIdx = idxMap.get("param" + i);

								if (null != paramIdx) {
									paramIndexList.add(paramIdx);

									if (paramIdx > 0
											&& null != param.getValueList()
											&& !"".equals(param.getValueList())) {
										String[] values = param.getValueList()
												.split(",");

										if (values.length >= paramIdx) {
											String value = values[paramIdx
													.intValue() - 1].trim();
											paramValueList.add(value);
										}
									}
								}
							} // for

							map.addAttribute("param_index_list", paramIndexList);
							map.addAttribute("param_list", paramList);

							// 排序序号
							Long sortId = idxMap.get("sortId");
							Long sortOrder; 

							// 排序
							if (null == sortId) {
								sortId = 0L;
							}
							
							map.addAttribute("sortId", sortId);
							
							sortOrder = idxMap.get("sort" + sortId);
							
							// 默认降序
							if (null == sortOrder)
							{
								sortOrder = 0L;
							}
							
							map.addAttribute("sortOrder", sortOrder);

							// 页号
							Long pageId = idxMap.get("pageId");
							
							// listStr没有页码，即无条件的listStr
							if (null == pageId) {
								if(page == null){
									pageId = 0L;									
								}else{
									pageId = Long.parseLong(page);
								}
							}
							map.addAttribute("page", pageId);
							
							map.addAttribute("pageId", pageId);

							String[] sortName = { "sortId", "salePrice", "soldNumber" };

							// 查找商品
							PageRequest pageRequest = null;

							// 0: 降序 1: 升序
							if (sortOrder.equals(0L)) {
								pageRequest = new PageRequest(
										pageId.intValue(),
										ClientConstant.pageSize,
										new Sort(Direction.DESC,
												sortName[sortId.intValue()])
												.and(new Sort(Direction.DESC,
														"id")));
							} else {
								pageRequest = new PageRequest(
										pageId.intValue(),
										ClientConstant.pageSize,
										new Sort(Direction.ASC, sortName[sortId
												.intValue()]).and(new Sort(
												Direction.DESC, "id")));
							}

							// 价格区间
							Long priceLow = idxMap.get("priceLow");
							Long priceHigh = idxMap.get("priceHigh");
							
							map.addAttribute("priceLow", priceLow);
							map.addAttribute("priceHigh", priceHigh);

							Page<TdGoods> goodsPage = null;
							
							if (null == priceHigh)
							{
								priceHigh = 9999999999L;
							}

							if (null != typeId) {
								if (null == priceLow) {
									if(null != keyword  && !"".equalsIgnoreCase(keyword.trim()))
									{
										if(null != paramValueList && paramValueList.size()>0){
											goodsPage = tdGoodsService
													.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
															typeId, paramValueList,keyword,pageRequest);
										}else{
											goodsPage = tdGoodsService
													.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
															typeId,keyword,pageRequest);
										}
									}else{
										if(null != paramValueList && paramValueList.size()>0)
										{
											goodsPage = tdGoodsService
													.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
															typeId, paramValueList,pageRequest);
										}else{
											goodsPage = tdGoodsService
													.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
															typeId, pageRequest);
										}
									}
								} else {
									if(null !=keyword && !"".equalsIgnoreCase(keyword.trim()))
									{
										if(null != paramValueList && paramValueList.size()>0)
										{
											goodsPage = tdGoodsService
													.findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
															typeId, priceLow,priceHigh,keyword, paramValueList,pageRequest);
										}else{
											goodsPage = tdGoodsService
													.findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
															typeId, priceLow,priceHigh,keyword,pageRequest);
										}
									}else{
										if(null != paramValueList && paramValueList.size()>0)
										{
											goodsPage = tdGoodsService
													.findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
															typeId, priceLow,priceHigh, paramValueList,pageRequest);
										}else{
											goodsPage = tdGoodsService
													.findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
															typeId, priceLow,priceHigh,pageRequest);
										}
									}
								}
							}
							
							map.addAttribute("goods_page", goodsPage);

							// 查找商品
						}
					}

					// map.addAttribute("param_count", paramCount);
					// map.addAttribute("param_index_list", paramIndexList);
					return "client/destination_list";
				}
			}
			// 主题活动
			else if (null != activityCat
					&& tpc.getParentTree().contains(
							"[" + activityCat.getId() + "]")) {
				// 顶级菜单
				if (tpc.getLayerCount().equals(1L)) {
					 map.addAttribute("hot_activity_page" ,
					 tdGoodsService.findByCategoryIdAndIsRecommendTypeTrueAndIsOnSaleTrueOrderByIdDesc(activityCat.getId(),
					 0, 5));
					
					 map.addAttribute("activity_cat_list" ,
					 tdProductCategoryService.findByParentIdOrderBySortIdAsc(activityCat.getId()));

					return "/client/activity_index";
				}
				// 非顶级菜单
				else {
					
					// 查找所有二级分类  这是二级分类
					List<TdProductCategory> level2Cat = tdProductCategoryService
							.findByParentIdOrderBySortIdAsc(activityCat.getId());
					map.addAttribute("level2_cat_list", level2Cat);

					// 获取该类型所有父类型
					if (null != tpc) {
						if (null != tpc.getParentTree()
								&& !"".equals(tpc.getParentTree())) {
							List<TdProductCategory> catList = new ArrayList<TdProductCategory>();

							for (String cid : tpc.getParentTree().split(",")) {
								if (!"".equals(cid)) {
									// 去除方括号
									cid = cid.replace("[", "");
									cid = cid.replace("]", "");

									TdProductCategory cat = tdProductCategoryService
											.findOne(Long.parseLong(cid));

									if (null != cat) {
										catList.add(cat);
									}
								}
							}

							map.addAttribute("category_tree_list", catList);
						}
					}

					// 参数列表
					if (null != tpc.getParamCategoryId()) {
						Long paramCategoryId = tpc.getParamCategoryId();

						List<TdParameter> paramList = tdParameterService
								.findByCategoryTreeContainingAndIsSearchableTrue(paramCategoryId);
						
						// 必须重新解析一次
						idxMap = parseListStr(listStr, Math.min(totalParams, paramList.size()), totalSorts);
						
						map.addAttribute("total_params", Math.min(totalParams, paramList.size()));
						map.addAttribute("total_sorts", totalSorts);
						
						if (null != idxMap) {
							// 参数解析
							List<String> paramValueList = new ArrayList<String>();
							List<Long> paramIndexList = new ArrayList<Long>();
							for (int i = 0; i < Math.min(totalParams, paramList.size()); i++) {
								TdParameter param = paramList.get(i);
								Long paramIdx = idxMap.get("param" + i);

								if (null != paramIdx) {
									paramIndexList.add(paramIdx);

									if (paramIdx > 0
											&& null != param.getValueList()
											&& !"".equals(param.getValueList())) {
										String[] values = param.getValueList()
												.split(",");

										if (values.length >= paramIdx) {
											String value = values[paramIdx
													.intValue() - 1].trim();
											paramValueList.add(value);
										}
									}
								}
							} // for

							map.addAttribute("param_index_list", paramIndexList);
							map.addAttribute("param_list", paramList);

							// 排序序号
							Long sortId = idxMap.get("sortId");
							Long sortOrder; 

							// 排序
							if (null == sortId) {
								sortId = 0L;
							}
							
							map.addAttribute("sortId", sortId);
							
							sortOrder = idxMap.get("sort" + sortId);
							
							// 默认降序
							if (null == sortOrder)
							{
								sortOrder = 0L;
							}
							
							map.addAttribute("sortOrder", sortOrder);

							// 页号
							Long pageId = idxMap.get("pageId");

							// listStr没有页码，即无条件的listStr
							if (null == pageId) {
								if(page == null){
									pageId = 0L;									
								}else{
									pageId = Long.parseLong(page);
								}
							}
							map.addAttribute("page", pageId);
							
							map.addAttribute("pageId", pageId);

							String[] sortName = { "sortId", "salePrice", "soldNumber" };

							// 查找商品
							PageRequest pageRequest = null;

							// 0: 降序 1: 升序
							if (sortOrder.equals(0L)) {
								pageRequest = new PageRequest(
										pageId.intValue(),
										ClientConstant.pageSize,
										new Sort(Direction.DESC,
												sortName[sortId.intValue()])
												.and(new Sort(Direction.DESC,
														"id")));
							} else {
								pageRequest = new PageRequest(
										pageId.intValue(),
										ClientConstant.pageSize,
										new Sort(Direction.ASC, sortName[sortId
												.intValue()]).and(new Sort(
												Direction.DESC, "id")));
							}

							// 价格区间
							Long priceLow = idxMap.get("priceLow");
							Long priceHigh = idxMap.get("priceHigh");
							
							map.addAttribute("priceLow", priceLow);
							map.addAttribute("priceHigh", priceHigh);

							Page<TdGoods> goodsPage = null;
							
							if (null == priceHigh)
							{
								priceHigh = 9999999999L;
							}

							if (null != typeId) {
//								if (null == priceLow) {
//									goodsPage = tdGoodsService
//											.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
//													typeId, paramValueList,keyword,
//													pageRequest);
//								} else {
//									goodsPage = tdGoodsService
//											.findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
//													typeId, priceLow,
//													priceHigh,keyword, paramValueList,
//													pageRequest);
//								}
								if (null == priceLow) {
									if(null != keyword && !"".equalsIgnoreCase(keyword.trim()))
									{
										if(null != paramValueList && paramValueList.size()>0){
											goodsPage = tdGoodsService
													.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
															typeId, paramValueList,keyword,pageRequest);
										}else{
											goodsPage = tdGoodsService
													.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
															typeId,keyword,pageRequest);
										}
									}else{
										if(null != paramValueList && paramValueList.size()>0)
										{
											goodsPage = tdGoodsService
													.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
															typeId, paramValueList,pageRequest);
										}else{
											goodsPage = tdGoodsService
													.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
															typeId, pageRequest);
										}
									}
								} else {
									if(null !=keyword && !"".equalsIgnoreCase(keyword.trim()))
									{
										if(null != paramValueList && paramValueList.size()>0)
										{
											goodsPage = tdGoodsService
													.findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
															typeId, priceLow,priceHigh,keyword, paramValueList,pageRequest);
										}else{
											goodsPage = tdGoodsService
													.findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
															typeId, priceLow,priceHigh,keyword,pageRequest);
										}
									}else{
										if(null != paramValueList && paramValueList.size()>0)
										{
											goodsPage = tdGoodsService
													.findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
															typeId, priceLow,priceHigh, paramValueList,pageRequest);
										}else{
											goodsPage = tdGoodsService
													.findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
															typeId, priceLow,priceHigh,pageRequest);
										}
									}
								}
							}
							
							map.addAttribute("goods_page", goodsPage);

							// 查找商品
						}
					}

					// map.addAttribute("param_count", paramCount);
					// map.addAttribute("param_index_list", paramIndexList);
					
					return "client/activity_list";
				}
			}
			// 私家定制
			else if (null != customCat
					&& tpc.getParentTree().contains(
							"[" + customCat.getId() + "]")) {
				if(null==username){
					//return "redirect:/login";
				}
				// 顶级菜单
				if (tpc.getLayerCount().equals(1L)) {
					 map.addAttribute("hot_custom_page" ,
					 tdGoodsService.findByCategoryIdAndIsHotTrueAndIsOnSaleTrue(tpc.getId(),
					 0, 5));
					
					 map.addAttribute("custom_cat_list" ,
					 tdProductCategoryService.findByParentIdOrderBySortIdAsc(customCat.getId()));
					
					 return "/client/custom_index";
				}
				// 非顶级菜单
				else {
					// 查找所有二级分类  这是二级分类
					List<TdProductCategory> level2Cat = tdProductCategoryService
							.findByParentIdOrderBySortIdAsc(customCat.getId());
					map.addAttribute("level2_cat_list", level2Cat);

					// 获取该类型所有父类型
					if (null != tpc) {
						if (null != tpc.getParentTree()
								&& !"".equals(tpc.getParentTree())) {
							List<TdProductCategory> catList = new ArrayList<TdProductCategory>();

							for (String cid : tpc.getParentTree().split(",")) {
								if (!"".equals(cid)) {
									// 去除方括号
									cid = cid.replace("[", "");
									cid = cid.replace("]", "");

									TdProductCategory cat = tdProductCategoryService
											.findOne(Long.parseLong(cid));

									if (null != cat) {
										catList.add(cat);
									}
								}
							}

							map.addAttribute("category_tree_list", catList);
						}
					}

					// 参数列表
					if (null != tpc.getParamCategoryId()) {
						Long paramCategoryId = tpc.getParamCategoryId();

						List<TdParameter> paramList = tdParameterService
								.findByCategoryTreeContainingAndIsSearchableTrue(paramCategoryId);
						
						// 必须重新解析一次
						idxMap = parseListStr(listStr, Math.min(totalParams, paramList.size()), totalSorts);
						
						map.addAttribute("total_params", Math.min(totalParams, paramList.size()));
						map.addAttribute("total_sorts", totalSorts);
						
						if (null != idxMap) {
							// 参数解析
							List<String> paramValueList = new ArrayList<String>();
							List<Long> paramIndexList = new ArrayList<Long>();
							for (int i = 0; i < Math.min(totalParams, paramList.size()); i++) {
								TdParameter param = paramList.get(i);
								Long paramIdx = idxMap.get("param" + i);

								if (null != paramIdx) {
									paramIndexList.add(paramIdx);

									if (paramIdx > 0
											&& null != param.getValueList()
											&& !"".equals(param.getValueList())) {
										String[] values = param.getValueList()
												.split(",");

										if (values.length >= paramIdx) {
											String value = values[paramIdx
													.intValue() - 1].trim();
											paramValueList.add(value);
										}
									}
								}
							} // for

							map.addAttribute("param_index_list", paramIndexList);
							map.addAttribute("param_list", paramList);

							// 排序序号
							Long sortId = idxMap.get("sortId");
							Long sortOrder; 

							// 排序
							if (null == sortId) {
								sortId = 0L;
							}
							
							map.addAttribute("sortId", sortId);
							
							sortOrder = idxMap.get("sort" + sortId);
							
							// 默认降序
							if (null == sortOrder)
							{
								sortOrder = 0L;
							}
							
							map.addAttribute("sortOrder", sortOrder);

							// 页号
							Long pageId = idxMap.get("pageId");

							// listStr没有页码，即无条件的listStr
							if (null == pageId) {
								if(page == null){
									pageId = 0L;									
								}else{
									pageId = Long.parseLong(page);
								}
							}
							map.addAttribute("page", pageId);
							
							map.addAttribute("pageId", pageId);

							String[] sortName = { "sortId", "salePrice", "soldNumber" };

							// 查找商品
							PageRequest pageRequest = null;

							// 0: 降序 1: 升序
							if (sortOrder.equals(0L)) {
								pageRequest = new PageRequest(
										pageId.intValue(),
										ClientConstant.pageSize,
										new Sort(Direction.DESC,
												sortName[sortId.intValue()])
												.and(new Sort(Direction.DESC,
														"id")));
							} else {
								pageRequest = new PageRequest(
										pageId.intValue(),
										ClientConstant.pageSize,
										new Sort(Direction.ASC, sortName[sortId
												.intValue()]).and(new Sort(
												Direction.DESC, "id")));
							}

							// 价格区间
							Long priceLow = idxMap.get("priceLow");
							Long priceHigh = idxMap.get("priceHigh");
							
							map.addAttribute("priceLow", priceLow);
							map.addAttribute("priceHigh", priceHigh);

							Page<TdGoods> goodsPage = null;
							
							if (null == priceHigh)
							{
								priceHigh = 9999999999L;
							}

							if (null != typeId) {
//								if (null == priceLow) {
//									goodsPage = tdGoodsService
//											.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
//													typeId, paramValueList,keyword,
//													pageRequest);
//								} else {
//									goodsPage = tdGoodsService
//											.findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
//													typeId, priceLow,
//													priceHigh,keyword, paramValueList,
//													pageRequest);
//								}
								if (null == priceLow) {
									if(null != keyword && !"".equalsIgnoreCase(keyword.trim()))
									{
										if(null != paramValueList && paramValueList.size()>0){
											goodsPage = tdGoodsService
													.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
															typeId, paramValueList,keyword,pageRequest);
										}else{
											goodsPage = tdGoodsService
													.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
															typeId,keyword,pageRequest);
										}
									}else{
										if(null != paramValueList && paramValueList.size()>0)
										{
											goodsPage = tdGoodsService
													.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
															typeId, paramValueList,pageRequest);
										}else{
											goodsPage = tdGoodsService
													.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
															typeId, pageRequest);
										}
									}
								} else {
									if(null !=keyword && !"".equalsIgnoreCase(keyword.trim()))
									{
										if(null != paramValueList && paramValueList.size()>0)
										{
											goodsPage = tdGoodsService
													.findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
															typeId, priceLow,priceHigh,keyword, paramValueList,pageRequest);
										}else{
											goodsPage = tdGoodsService
													.findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
															typeId, priceLow,priceHigh,keyword,pageRequest);
										}
									}else{
										if(null != paramValueList && paramValueList.size()>0)
										{
											goodsPage = tdGoodsService
													.findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
															typeId, priceLow,priceHigh, paramValueList,pageRequest);
										}else{
											goodsPage = tdGoodsService
													.findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
															typeId, priceLow,priceHigh,pageRequest);
										}
									}
								}
							}
							
							map.addAttribute("goods_page", goodsPage);

							// 查找商品
						}
					}
					return "client/custom_list";
				}
			}
			
			//特产商城
			if (null != specialtyCat
					&& tpc.getParentTree()
							.contains("[" + specialtyCat.getId() + "]")) {
				// 顶级菜单
				if (tpc.getLayerCount().equals(1L)) {
					// 热门
					map.addAttribute("hot_specialty_page", tdGoodsService
							.findByCategoryIdAndIsHotTrueAndIsOnSaleTrue(
									tpc.getId(), 0, 5));

					map.addAttribute("specialty_cat_list", tdProductCategoryService
							.findByParentIdOrderBySortIdAsc(specialtyCat.getId()));
					
					Page<TdGoods> tdGoods = tdGoodsService.findByIsSpecialPriceTrueAndIsOnSaleTrue(0,6);
			        map.addAttribute("goodsss_page", tdGoods);
					
					return "/client/specialty_index";
				}
				// 非顶级菜单
				else {
					// 查找所有二级分类 
					List<TdProductCategory> level2Cat = tdProductCategoryService
							.findByParentIdOrderBySortIdAsc(specialtyCat.getId());
					map.addAttribute("level2_cat_list", level2Cat);

					// 获取该类型所有父类型
					if (null != tpc) {
						if (null != tpc.getParentTree()
								&& !"".equals(tpc.getParentTree())) {
							List<TdProductCategory> catList = new ArrayList<TdProductCategory>();

							for (String cid : tpc.getParentTree().split(",")) {
								if (!"".equals(cid)) {
									// 去除方括号
									cid = cid.replace("[", "");
									cid = cid.replace("]", "");

									TdProductCategory cat = tdProductCategoryService
											.findOne(Long.parseLong(cid));

									if (null != cat) {
										catList.add(cat);
									}
								}
							}

							map.addAttribute("category_tree_list", catList);
						}
					}

					// 参数列表
					if (null != tpc.getParamCategoryId()) {
						Long paramCategoryId = tpc.getParamCategoryId();

						List<TdParameter> paramList = tdParameterService
								.findByCategoryTreeContainingAndIsSearchableTrue(paramCategoryId);
						
						// 必须重新解析一次
						idxMap = parseListStr(listStr, Math.min(totalParams, paramList.size()), totalSorts);
						
						map.addAttribute("total_params", Math.min(totalParams, paramList.size()));
						map.addAttribute("total_sorts", totalSorts);
						
						if (null != idxMap) {
							// 参数解析
							List<String> paramValueList = new ArrayList<String>();
							List<Long> paramIndexList = new ArrayList<Long>();
							for (int i = 0; i < Math.min(totalParams, paramList.size()); i++) {
								TdParameter param = paramList.get(i);
								Long paramIdx = idxMap.get("param" + i);

								if (null != paramIdx) {
									paramIndexList.add(paramIdx);

									if (paramIdx > 0
											&& null != param.getValueList()
											&& !"".equals(param.getValueList())) {
										String[] values = param.getValueList()
												.split(",");

										if (values.length >= paramIdx) {
											String value = values[paramIdx
													.intValue() - 1].trim();
											paramValueList.add(value);
										}
									}
								}
							} // for

							map.addAttribute("param_index_list", paramIndexList);
							map.addAttribute("param_list", paramList);

							// 排序序号
							Long sortId = idxMap.get("sortId");
							Long sortOrder; 

							// 排序
							if (null == sortId) {
								sortId = 0L;
							}
							
							map.addAttribute("sortId", sortId);
							
							sortOrder = idxMap.get("sort" + sortId);
							
							// 默认降序
							if (null == sortOrder)
							{
								sortOrder = 0L;
							}
							
							map.addAttribute("sortOrder", sortOrder);

							// 页号
							Long pageId = idxMap.get("pageId");

							// listStr没有页码，即无条件的listStr
							if (null == pageId) {
								if(page == null){
									pageId = 0L;									
								}else{
									pageId = Long.parseLong(page);
								}
							}
							map.addAttribute("page", pageId);
							
							map.addAttribute("pageId", pageId);

							String[] sortName = { "sortId", "salePrice", "soldNumber" };

							// 查找商品
							PageRequest pageRequest = null;

							// 0: 降序 1: 升序
							if (sortOrder.equals(0L)) {
								pageRequest = new PageRequest(
										pageId.intValue(),
										ClientConstant.pageSize,
										new Sort(Direction.DESC,
												sortName[sortId.intValue()])
												.and(new Sort(Direction.DESC,
														"id")));
							} else {
								pageRequest = new PageRequest(
										pageId.intValue(),
										ClientConstant.pageSize,
										new Sort(Direction.ASC, sortName[sortId
												.intValue()]).and(new Sort(
												Direction.DESC, "id")));
							}

							// 价格区间
							Long priceLow = idxMap.get("priceLow");
							Long priceHigh = idxMap.get("priceHigh");
							
							map.addAttribute("priceLow", priceLow);
							map.addAttribute("priceHigh", priceHigh);

							Page<TdGoods> goodsPage = null;
							
							if (null == priceHigh)
							{
								priceHigh = 9999999999L;
							}

							if (null != typeId) {
//								if (null == priceLow) {
//									goodsPage = tdGoodsService
//											.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
//													typeId, paramValueList,keyword,
//													pageRequest);
//								} else {
//									goodsPage = tdGoodsService
//											.findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
//													typeId, priceLow,
//													priceHigh,keyword, paramValueList,
//													pageRequest);
//								}
								if (null == priceLow /*&& !"".equalsIgnoreCase(keyword.trim())*/) {
									if(null != keyword && !"".equalsIgnoreCase(keyword.trim()))
									{
										if(null != paramValueList && paramValueList.size()>0){
											goodsPage = tdGoodsService
													.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
															typeId, paramValueList,keyword,pageRequest);
										}else{
											goodsPage = tdGoodsService
													.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
															typeId,keyword,pageRequest);
										}
									}else{
										if(null != paramValueList && paramValueList.size()>0)
										{
											goodsPage = tdGoodsService
													.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
															typeId, paramValueList,pageRequest);
										}else{
											goodsPage = tdGoodsService
													.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
															typeId, pageRequest);
										}
									}
								} else {
									if(null !=keyword && !"".equalsIgnoreCase(keyword.trim()))
									{
										if(null != paramValueList && paramValueList.size()>0)
										{
											goodsPage = tdGoodsService
													.findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
															typeId, priceLow,priceHigh,keyword, paramValueList,pageRequest);
										}else{
											goodsPage = tdGoodsService
													.findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
															typeId, priceLow,priceHigh,keyword,pageRequest);
										}
									}else{
										if(null != paramValueList && paramValueList.size()>0)
										{
											goodsPage = tdGoodsService
													.findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
															typeId, priceLow,priceHigh, paramValueList,pageRequest);
										}else{
											goodsPage = tdGoodsService
													.findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
															typeId, priceLow,priceHigh,pageRequest);
										}
									}
								}
							}
							
							map.addAttribute("goods_page", goodsPage);

							// 查找商品
						}
					}
					
					// map.addAttribute("param_count", paramCount);
					// map.addAttribute("param_index_list", paramIndexList);
					return "client/specialty_list";
				}
			}
			
			// 签证
			else if (null != visaCat
					&& tpc.getParentTree()
							.contains("[" + visaCat.getId() + "]")) {
				// 参数列表
				if (null != tpc.getParamCategoryId()) {
					Long paramCategoryId = tpc.getParamCategoryId();

					List<TdParameter> paramList = tdParameterService
							.findByCategoryTreeContainingAndIsSearchableTrue(paramCategoryId);
					
					// 必须重新解析一次
					idxMap = parseListStr(listStr, Math.min(totalParams, paramList.size()), totalSorts);
					
					map.addAttribute("total_params", Math.min(totalParams, paramList.size()));
					map.addAttribute("total_sorts", totalSorts);
					
					if (null != idxMap) {
						// 参数解析
						List<String> paramValueList = new ArrayList<String>();
						List<Long> paramIndexList = new ArrayList<Long>();
						for (int i = 0; i < Math.min(totalParams, paramList.size()); i++) {
							TdParameter param = paramList.get(i);
							Long paramIdx = idxMap.get("param" + i);

							if (null != paramIdx) {
								paramIndexList.add(paramIdx);

								if (paramIdx > 0
										&& null != param.getValueList()
										&& !"".equals(param.getValueList())) {
									String[] values = param.getValueList()
											.split(",");

									if (values.length >= paramIdx) {
										String value = values[paramIdx
												.intValue() - 1].trim();
										paramValueList.add(value);
									}
								}
							}
						} // for

						map.addAttribute("param_index_list", paramIndexList);
						map.addAttribute("param_list", paramList);
				
				// 顶级菜单
				if (tpc.getLayerCount().equals(1L)) {
					 map.addAttribute("hot_visa_page" ,
					 tdGoodsService.findByCategoryIdAndIsHotTrueAndIsOnSaleTrue(tpc.getId(),
					 0, 5));
					
					 List<TdProductCategory> list = tdProductCategoryService.findByParentIdOrderBySortIdAsc(visaCat.getId());
					 if(null != list && list.size()>0)
					 {
						 map.addAttribute("type", list.get(0).getId());
					 }
					
					 return "/client/visa_index";
				}
				// 非顶级菜单
				else {
					// 查找所有二级分类 
					List<TdProductCategory> level2Cat = tdProductCategoryService
							.findByParentIdOrderBySortIdAsc(visaCat.getId());
					map.addAttribute("level2_cat_list", level2Cat);

					// 获取该类型所有父类型
					if (null != tpc) {
						if (null != tpc.getParentTree()
								&& !"".equals(tpc.getParentTree())) {
							List<TdProductCategory> catList = new ArrayList<TdProductCategory>();

							for (String cid : tpc.getParentTree().split(",")) {
								if (!"".equals(cid)) {
									// 去除方括号
									cid = cid.replace("[", "");
									cid = cid.replace("]", "");

									TdProductCategory cat = tdProductCategoryService
											.findOne(Long.parseLong(cid));

									if (null != cat) {
										catList.add(cat);
									}
								}
							}

							map.addAttribute("category_tree_list", catList);
						}
					}

							// 排序序号
							Long sortId = idxMap.get("sortId");
							Long sortOrder; 

							// 排序
							if (null == sortId) {
								sortId = 0L;
							}
							
							map.addAttribute("sortId", sortId);
							
							sortOrder = idxMap.get("sort" + sortId);
							
							// 默认降序
							if (null == sortOrder)
							{
								sortOrder = 0L;
							}
							
							map.addAttribute("sortOrder", sortOrder);

							// 页号
							Long pageId = idxMap.get("pageId");

							// listStr没有页码，即无条件的listStr
							if (null == pageId) {
								if(page == null){
									pageId = 0L;									
								}else{
									pageId = Long.parseLong(page);
								}
							}
							map.addAttribute("page", pageId);
							
							map.addAttribute("pageId", pageId);

							String[] sortName = { "sortId", "salePrice", "soldNumber" };

							// 查找商品
							PageRequest pageRequest = null;

							// 0: 降序 1: 升序
							if (sortOrder.equals(0L)) {
								pageRequest = new PageRequest(
										pageId.intValue(),
										ClientConstant.pageSize,
										new Sort(Direction.DESC,
												sortName[sortId.intValue()])
												.and(new Sort(Direction.DESC,
														"id")));
							} else {
								pageRequest = new PageRequest(
										pageId.intValue(),
										ClientConstant.pageSize,
										new Sort(Direction.ASC, sortName[sortId
												.intValue()]).and(new Sort(
												Direction.DESC, "id")));
							}

							// 价格区间
							Long priceLow = idxMap.get("priceLow");
							Long priceHigh = idxMap.get("priceHigh");
							
							map.addAttribute("priceLow", priceLow);
							map.addAttribute("priceHigh", priceHigh);

							Page<TdGoods> goodsPage = null;
							
							if (null == priceHigh)
							{
								priceHigh = 9999999999L;
							}

							if (null != typeId) {
//								if (null == priceLow) {
//									goodsPage = tdGoodsService
//											.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
//													typeId, paramValueList,keyword,
//													pageRequest);
//								} else {
//									goodsPage = tdGoodsService
//											.findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
//													typeId, priceLow,
//													priceHigh,keyword, paramValueList,
//													pageRequest);
//								}
								if (null == priceLow) {
									if(null != keyword && !"".equalsIgnoreCase(keyword.trim()))
									{
										if(null != paramValueList && paramValueList.size()>0){
											goodsPage = tdGoodsService
													.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
															typeId, paramValueList,keyword,pageRequest);
										}else{
											goodsPage = tdGoodsService
													.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
															typeId,keyword,pageRequest);
										}
									}else{
										if(null != paramValueList && paramValueList.size()>0)
										{
											goodsPage = tdGoodsService
													.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
															typeId, paramValueList,pageRequest);
										}else{
											goodsPage = tdGoodsService
													.findByCategoryIdAndParamsLikeAndIsOnSaleTrue(
															typeId, pageRequest);
										}
									}
								} else {
									if(null !=keyword && !"".equalsIgnoreCase(keyword.trim()))
									{
										if(null != paramValueList && paramValueList.size()>0)
										{
											goodsPage = tdGoodsService
													.findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
															typeId, priceLow,priceHigh,keyword, paramValueList,pageRequest);
										}else{
											goodsPage = tdGoodsService
													.findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
															typeId, priceLow,priceHigh,keyword,pageRequest);
										}
									}else{
										if(null != paramValueList && paramValueList.size()>0)
										{
											goodsPage = tdGoodsService
													.findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
															typeId, priceLow,priceHigh, paramValueList,pageRequest);
										}else{
											if(priceLow == null || priceHigh == null){
												goodsPage = tdGoodsService.
														findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
																typeId, 0.0, Double.MAX_VALUE,pageRequest);
											}else{
												goodsPage = tdGoodsService.
														findByCategoryIdAndSalePriceBetweenAndParamsLikeAndIsOnSaleTrue(
																typeId, priceLow,priceHigh,pageRequest);
												
											}
										}
									}
								}
							}
//							String retUrl = req.getHeader("Referer");
							
							map.addAttribute("goods_page", goodsPage);

							// 查找商品
						}
					}

					// map.addAttribute("param_count", paramCount);
					// map.addAttribute("param_index_list", paramIndexList);
					return "client/visa_list";
				}
			}
			// 汽车租赁
			// else if (null != shipCat && tpc.getParentTree().contains("[" +
			// shipCat.getId() + "]"))
			// {
			// // 顶级菜单
			// if (tpc.getLayerCount().equals(1L))
			// {
			// map.addAttribute("hot_ship_page" ,
			// tdGoodsService.findByCategoryIdAndIsHotTrueAndIsOnSaleTrue(tpc.getId(),
			// 0, 5));
			//
			// map.addAttribute("ship_cat_list" ,
			// tdProductCategoryService.findByParentIdOrderBySortIdAsc(shipCat.getId()));
			//
			// return "/client/ship_index";
			// }
			// // 非顶级菜单
			// else
			// {
			//
			// }
			// }
			// // 旅游直通车
			// else if (null != shipCat && tpc.getParentTree().contains("[" +
			// shipCat.getId() + "]"))
			// {
			// // 顶级菜单
			// if (tpc.getLayerCount().equals(1L))
			// {
			// map.addAttribute("hot_ship_page" ,
			// tdGoodsService.findByCategoryIdAndIsHotTrueAndIsOnSaleTrue(tpc.getId(),
			// 0, 5));
			//
			// map.addAttribute("ship_cat_list" ,
			// tdProductCategoryService.findByParentIdOrderBySortIdAsc(shipCat.getId()));
			//
			// return "/client/ship_index";
			// }
			// // 非顶级菜单
			// else
			// {
			//
			// }
			// }
		}

		return "/client/error_404";
	}

	/**
	 * 根据list字符串，解析里面的各字段
	 * 
	 * @param str
	 *            list字符串
	 * @param totalParams
	 *            参数个数
	 * @param totalSortNum
	 *            排序字段个数
	 * @return
	 */
	// 组成：typeID-[3*paramIndex]-[排序字段]-[综合排序标志]-[价格排序标志]-[销量排序标志]-[页号]_[价格低值]-[价格高值]
	Map<String, Long> parseListStr(String listStr, int totalParams,
			int totalSortNum) {
		//判断listStr是否为空
		if (null == listStr || listStr.isEmpty()) {
			return null;
		}
		
		Map<String, Long> resMap = new HashMap<String, Long>();

		// 包含价格最低值和最高值
		if (listStr.contains("_")) {
			String[] listGroup = listStr.split("_");

			if (listGroup.length > 1) {
				String[] priceGroup = listGroup[1].split("-");

				if (priceGroup.length > 0) {
					resMap.put("priceLow", Long.parseLong(priceGroup[0]));
				}
				
				if (priceGroup.length > 1) {
					resMap.put("priceHigh", Long.parseLong(priceGroup[1]));
				}
			}
			//给listStr重新赋值为前面一段（下面会用）
			listStr = listGroup[0];
		}

		String[] numberGroup = listStr.split("-");

		// 组成：typeID-[3*paramIndex]-[排序字段]-[综合排序标志]-[价格排序标志]-[销量排序标志]-[页号]_[价格低值]-[价格高值]

		// typeId
		if (numberGroup.length > 0) {
			resMap.put("typeId", Long.parseLong(numberGroup[0]));
		}

		if (numberGroup.length < 1 + totalParams) {
			return resMap;
		}

		// 参数表
		for (int i = 0; i < totalParams; i++) {
			resMap.put("param" + i, Long.parseLong(numberGroup[i + 1]));
		}

		if (numberGroup.length < 2 + totalParams) {
			return resMap;
		}

		// 排序字段
		resMap.put("sortId", Long.parseLong(numberGroup[totalParams + 1]));

		if (numberGroup.length < 2 + totalParams + totalSortNum) {
			return resMap;
		}

		// 排序标志位
		for (int i = 0; i < totalSortNum; i++) {
			resMap.put("sort" + i,
					Long.parseLong(numberGroup[i + totalParams + 2]));
		}

		if (numberGroup.length < 3 + totalParams + totalSortNum) {
			return resMap;
		}

		// 页号
		resMap.put("pageId",
				Long.parseLong(numberGroup[totalSortNum + totalParams + 2]));

		return resMap;
	}
	
	//目的地、主题活动分页拼接
	public String listStrSplicePage(String listStr, String page){
		String[] listStrArray =  listStr.split("_");
		// 不做处理，等待后续拼接
		if(listStrArray[0].split("-").length == 1){
			return listStr;
		}else{
		// 有分页的listStr
			String listStr_containPage = listStrArray[0];
			if(page != null){
				listStrArray[0] = listStr_containPage.substring(0, listStr_containPage.lastIndexOf("-")) + "-" + page;							
			}
		}
		
		String listStr_paged = "";
		if(listStrArray.length > 1){
			listStr_paged = listStrArray[0] + "_" + listStrArray[1];  
		}else{
			listStr_paged = listStrArray[0];
		}
		
		return listStr_paged;
	}
	
	// 子主题确定轮播顺序
	private List<TdAd> sortAsSubtitle(List<TdAd> adList, String firstTitle){
		List<TdAd> tdAdList = new ArrayList<>();
		if(adList == null){
			return null;
		}else{
			for(TdAd a : adList){
				if(a.getTitle().equals(firstTitle)){
					tdAdList.add(a);
					adList.remove(a);
					break;
				}
			}
			tdAdList.addAll(adList);
			return tdAdList;
		}
	}
}
