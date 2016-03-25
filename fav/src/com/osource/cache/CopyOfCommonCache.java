package com.osource.cache;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import oracle.net.ano.SupervisorService;

import com.hp.hpl.sparta.xpath.ThisNodeTest;
import com.osource.module.fav.model.AuctionDynamicsTypeInfo;
import com.osource.module.fav.model.AuctionInfo;
import com.osource.module.fav.model.CollectionCategoryInfo;
import com.osource.module.fav.model.CollectionLableInfo;
import com.osource.module.fav.model.CollectionPeriodInfo;
import com.osource.module.fav.model.EnumInfo;
import com.osource.module.fav.model.SpecialInfo;
import com.osource.module.fav.model.UserTitleInfo;
import com.osource.module.fav.service.AuctionDynamicsTypeService;
import com.osource.module.fav.service.AuctionService;
import com.osource.module.fav.service.CollectionCategoryService;
import com.osource.module.fav.service.CollectionLableService;
import com.osource.module.fav.service.CollectionPeriodService;
import com.osource.module.fav.service.EnumService;
import com.osource.module.fav.service.SpecialService;
import com.osource.module.fav.service.UserTitleService;
import com.osource.util.BooleanUtil;

/**
 * 缓存
 * @author gaoxiang
 *
 */
public final class CopyOfCommonCache {
	/**
	 * 枚举缓存
	 */
	private static Map<String, Map<String, String>> enumMap = new LinkedHashMap<String, Map<String, String>>();
	
	/**
	 * 藏品时期
	 */
	private static Map<Integer, String> collectionPeriodMap = new LinkedHashMap<Integer, String>();
	
	/**
	 * 藏品标签
	 */
	private static Map<Integer, String> collectionLableMap = new LinkedHashMap<Integer, String>();
	
	/**
	 * 藏品类别
	 */
	private static Map<Integer, String> collectionCategoryMap = new LinkedHashMap<Integer, String>();
	
	/**
	 * 拍卖行
	 */
	private static Map<Integer, String> auctionMap = new LinkedHashMap<Integer, String>();
	
	/**
	 * 专项
	 */
	private static Map<Integer, String> specialMap = new LinkedHashMap<Integer, String>();
	
	/**
	 * 用户称号
	 */
	private static Map<Integer, String> userTitleMap = new LinkedHashMap<Integer, String>();
	
	/**
	 * 用户称号
	 */
	private static Map<Integer, String> userTitleImgMap = new LinkedHashMap<Integer, String>();
	
	/**
	 * 拍卖行动态类别
	 */
	private static Map<Integer, String> auctionDynamicsTypeMap = new LinkedHashMap<Integer, String>();
	
	
	private static EnumService enumService;
	private static CollectionPeriodService collectionPeriodService;
	private static CollectionLableService collectionLableService;
	private static CollectionCategoryService collectionCategoryService;
	private static AuctionService auctionService;
	private static SpecialService specialService;
	private static UserTitleService userTitleService;
	private static AuctionDynamicsTypeService auctionDynamicsTypeService;
	/**
	 * 获取枚举
	 * @param enumType
	 * @return
	 */
	public static Map<String, String> getEnumInfos(String enumType){
		// 初始化枚举表
		List<EnumInfo> enumList = enumService.findAll();
		if(!BooleanUtil.isEmpty(enumList)){
			Map<String, String> enumInfos = null;
			for (EnumInfo enumInfo : enumList) {
				if (enumMap.containsKey(enumInfo.getEnumType())){
					enumInfos = enumMap.get(enumInfo.getEnumType());
				}else {
					enumInfos = new LinkedHashMap<String, String>();
				}
				enumInfos.put(enumInfo.getEnumCode(), enumInfo.getEnumName());
				enumMap.put(enumInfo.getEnumType(), enumInfos);
			}
		}
		return enumMap.get(enumType);
	}
	
	/**
	 * 获取藏品标签
	 * @return
	 */
	public static Map<Integer, String> getCollectionLables(){
		List<CollectionLableInfo> collectionLableList = collectionLableService.findAll();
		if(!BooleanUtil.isEmpty(collectionLableList)){
			for (CollectionLableInfo info : collectionLableList) {
				collectionLableMap.put(info.getId(), info.getName());
			}
		}
		return collectionLableMap;
	}
	
	/**
	 * 获取藏品类别
	 * @return
	 */
	public static Map<Integer, String> getCollectionCategorys(){
		return collectionCategoryMap;
	}
	
	/**
	 * 获取藏品时期
	 * @return
	 */
	public static Map<Integer, String> getCollectionPeriods(){
		return collectionPeriodMap;
	}
	
	/**
	 * 获取拍卖行
	 * @return
	 */
	public static Map<Integer, String> getAuctions(){
		return auctionMap;
	}
	
	/**
	 * 获取拍卖行
	 * @return
	 */
	public static void putAuction(AuctionInfo info){
		auctionMap.put(info.getId(), info.getName());
	}
	
	/**
	 * 获取专项
	 * @return
	 */
	public static Map<Integer, String> getSpecials(){
		List<SpecialInfo> specialInfos = specialService.findAll();
		if(!BooleanUtil.isEmpty(specialInfos)){
			for (SpecialInfo info : specialInfos) {
				specialMap.put(info.getId(), info.getName());
			}
		}
		return specialMap;
	}
	
	/**
	 * 获取用户称号
	 * @return
	 */
	public static Map<Integer, String> getUserTitles(){
		List<UserTitleInfo> userTitleInfos = userTitleService.findAll();
		if(!BooleanUtil.isEmpty(userTitleInfos)){
			for (UserTitleInfo info : userTitleInfos) {
				userTitleMap.put(info.getId(), info.getName());
				userTitleImgMap.put(info.getId(), info.getImgPath());
			}
		}
		return userTitleMap;
	}
	
	/**
	 * 获取用户称号
	 * @return
	 */
	public static Map<Integer, String> getUserTitleImgs(){
		return userTitleImgMap;
	}
	
	/**
	 * 获取拍卖行动态类别
	 * @return
	 */
	public static Map<Integer, String> getAuctionDynamicsTypes(){
		List<AuctionDynamicsTypeInfo> auctionDynamicsTypeInfos = auctionDynamicsTypeService.findAll();
		if(!BooleanUtil.isEmpty(auctionDynamicsTypeInfos)){
			for (AuctionDynamicsTypeInfo info : auctionDynamicsTypeInfos) {
				auctionDynamicsTypeMap.put(info.getId(), info.getName());
			}
		}
		return auctionDynamicsTypeMap;
	}
	
	/**
	 * 刷新所有缓存
	 * @return
	 */
	public static boolean reflashCache(EnumService enumService, CollectionPeriodService collectionPeriodService,
		CollectionLableService collectionLableService, CollectionCategoryService collectionCategoryService,
		AuctionService auctionService, SpecialService specialService, UserTitleService userTitleService,
		AuctionDynamicsTypeService auctionDynamicsTypeService){
		CopyOfCommonCache.enumService = enumService;
		CopyOfCommonCache.collectionPeriodService = collectionPeriodService;
		CopyOfCommonCache.collectionLableService = collectionLableService;
		CopyOfCommonCache.collectionCategoryService = collectionCategoryService;
		CopyOfCommonCache.auctionService = auctionService;
		CopyOfCommonCache.specialService = specialService;
		CopyOfCommonCache.userTitleService = userTitleService;
		CopyOfCommonCache.auctionDynamicsTypeService = auctionDynamicsTypeService;
		
		// 初始化枚举表
		List<EnumInfo> enumList = enumService.findAll();
		if(!BooleanUtil.isEmpty(enumList)){
			Map<String, String> enumInfos = null;
			for (EnumInfo enumInfo : enumList) {
				if (enumMap.containsKey(enumInfo.getEnumType())){
					enumInfos = enumMap.get(enumInfo.getEnumType());
				}else {
					enumInfos = new LinkedHashMap<String, String>();
				}
				enumInfos.put(enumInfo.getEnumCode(), enumInfo.getEnumName());
				enumMap.put(enumInfo.getEnumType(), enumInfos);
			}
		}
		
		// 初始化藏品时期
		List<CollectionPeriodInfo> collectionPeriodList = collectionPeriodService.findAll();
		if(!BooleanUtil.isEmpty(collectionPeriodList)){
			for (CollectionPeriodInfo collectionPeriodInfo : collectionPeriodList) {
				collectionPeriodMap.put(collectionPeriodInfo.getId(), collectionPeriodInfo.getName());
			}
		}
		
		// 初始化藏品标签
		List<CollectionLableInfo> collectionLableList = collectionLableService.findAll();
		if(!BooleanUtil.isEmpty(collectionLableList)){
			for (CollectionLableInfo info : collectionLableList) {
				collectionLableMap.put(info.getId(), info.getName());
			}
		}
		
		// 初始化藏品类别
		List<CollectionCategoryInfo> collectionCategoryList = collectionCategoryService.findAll();
		if(!BooleanUtil.isEmpty(collectionCategoryList)){
			for (CollectionCategoryInfo info : collectionCategoryList) {
				collectionCategoryMap.put(info.getId(), info.getCategoryName());
			}
		}
		
		// 初始化拍卖行
		List<AuctionInfo> auctionInfos = auctionService.findAll();
		if(!BooleanUtil.isEmpty(auctionInfos)){
			for (AuctionInfo info : auctionInfos) {
				auctionMap.put(info.getId(), info.getName());
			}
		}
		
		// 初始化专项
		List<SpecialInfo> specialInfos = specialService.findAll();
		if(!BooleanUtil.isEmpty(specialInfos)){
			for (SpecialInfo info : specialInfos) {
				specialMap.put(info.getId(), info.getName());
			}
		}
		
		// 初始化用户称号
		List<UserTitleInfo> userTitleInfos = userTitleService.findAll();
		if(!BooleanUtil.isEmpty(userTitleInfos)){
			for (UserTitleInfo info : userTitleInfos) {
				userTitleMap.put(info.getId(), info.getName());
				userTitleImgMap.put(info.getId(), info.getImgPath());
			}
		}
		
		// 初始化拍卖行动态类别
		List<AuctionDynamicsTypeInfo> auctionDynamicsTypeInfos = auctionDynamicsTypeService.findAll();
		if(!BooleanUtil.isEmpty(auctionDynamicsTypeInfos)){
			for (AuctionDynamicsTypeInfo info : auctionDynamicsTypeInfos) {
				auctionDynamicsTypeMap.put(info.getId(), info.getName());
			}
		}
		return true;
	}
}
