package com.front.cache;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.directwebremoting.json.types.JsonObject;
import org.express.database.DBManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.front.db.bean.CollectionCategoryBean;
import com.front.db.bean.CollectionLableBean;
import com.front.db.bean.CollectionPeriodBean;
import com.front.db.bean.EnumBean;
import com.front.db.bean.HeatBean;
import com.front.db.bean.HomeBean;
import com.front.db.bean.SpecialBean;
import com.front.db.bean.TaskPointsConfigBean;
import com.front.db.bean.UserTitleBean;
import com.front.web.framework.database.BaseDao;

/**
 * 系统常量变量和参数的缓存类
 * @author sung
 *
 */
public class SysConfigCache {
	/**
     * 日志类
     */
    private final static Logger logger = LoggerFactory.getLogger(SysConfigCache.class);

    //藏品类别
    private static volatile List<CollectionCategoryBean> collectionCategoryList = new ArrayList<CollectionCategoryBean>();
    //藏品时期
    private static volatile List<CollectionPeriodBean> collectionPeriodList = new ArrayList<CollectionPeriodBean>();
    
    private static volatile HomeBean homeBean;
    
    //private static volatile List<String> homeAdImageList = new ArrayList<String>();
    private static volatile List<JSONObject> homeAdImageList = new ArrayList<JSONObject>();
    
    private static volatile Map<BigDecimal, String> periodMap = new HashMap<BigDecimal, String>();
    
    private static volatile Map<BigDecimal, String> categoryMap = new HashMap<BigDecimal, String>();
    
    private static volatile Map<BigDecimal, String> labelMap = new HashMap<BigDecimal, String>();
    
    //热度系数值
    private static volatile Map<String, Integer> heatPointMap = new HashMap<String, Integer>();
    
    //任务积分设置
    private static volatile Map<String, TaskPointsConfigBean> taskPointsConfigMap = new HashMap<String, TaskPointsConfigBean>();
    
    //用户称号
    private static volatile Map<String, String> userTitleMap = new HashMap<String, String>();
    
    //专家和大咖的专项
    private static volatile Map<String, String> specialMap = new HashMap<String, String>();
    
    //枚举值
    private static volatile Map<String, String> enumMap = new HashMap<String, String>();
    
    //私有构造函数
    private SysConfigCache(){};
    
    static{
    	initSysConfigCache();
        //每隔段时间 重新加载数据库信息
        Thread t = new Thread(){
            public void run(){
                while(true){
                    try{
                        Thread.sleep(10 * 60 * 1000);
                        //重新加载引用到缓存
                        initSysConfigCache();
                        logger.info("reload initSysConfigCache data success.");
                    }catch (Exception e) {
                        logger.error("initSysConfigCache reload error", e);
                    }
                }
            }
        };
        t.setDaemon(true);//设置守护线程
        t.start();
    }
    
    @SuppressWarnings("unchecked")
	private static void initSysConfigCache()
    {
    	try {
    		//缓存藏品类别
			List<CollectionCategoryBean> ccbList = BaseDao.getListByAnnotation(CollectionCategoryBean.class, "use_flag = 1 ORDER BY display_order ASC", null);
			if (CollectionUtils.isNotEmpty(ccbList))
			{
				collectionCategoryList = ccbList;
				Map<BigDecimal, String> tempMap = new HashMap<BigDecimal, String>();
				for (CollectionCategoryBean ccBean : ccbList)
				{
					tempMap.put(ccBean.getId(), ccBean.getCategory_name());
				}
				categoryMap = tempMap;
				logger.info("SysConfigCache reload CollectionCategoryBean size["+ ccbList.size() +"] success.");
			}
		} catch (Exception e) {
			logger.error("query CollectionCategoryBean error.", e);
		}
		
		try {
    		//缓存藏品类别
			List<CollectionPeriodBean> cpbList = BaseDao.getListByAnnotation(CollectionPeriodBean.class, "use_flag = 1 ORDER BY display_order ASC", null);
			if (CollectionUtils.isNotEmpty(cpbList))
			{
				collectionPeriodList = cpbList;
				Map<BigDecimal, String> tempMap = new HashMap<BigDecimal, String>();
				for(CollectionPeriodBean cpBean : cpbList)
				{
					tempMap.put(cpBean.getId(), cpBean.getName());
				}
				periodMap = tempMap;
				logger.info("SysConfigCache reload CollectionPeriodBean size["+ cpbList.size() +"] success.");
			}
		} catch (Exception e) {
			logger.error("query CollectionPeriodBean error.", e);
		}
    	
		try {
    		//首页参数
			List<HomeBean> haList = BaseDao.getListByAnnotation(HomeBean.class, "use_flag = 1 ORDER BY insert_date DESC", null);
			if (CollectionUtils.isNotEmpty(haList))
			{
				homeBean = haList.get(0);
				logger.info("SysConfigCache reload homeBean success.");
				setHomeAdImages(homeBean);
				
			}
		} catch (Exception e) {
			logger.error("query HomeBean error.", e);
		}
		
		try {
    		//标签参数
			List<CollectionLableBean> clList = BaseDao.getListByAnnotation(CollectionLableBean.class, "use_flag = 1 ORDER BY display_order DESC", null);
			if (CollectionUtils.isNotEmpty(clList))
			{
				Map<BigDecimal, String> tempMap = new HashMap<BigDecimal, String>();
				for(CollectionLableBean cl : clList)
				{
					tempMap.put(cl.getId(), cl.getName());
				}
				labelMap = tempMap;
				logger.info("SysConfigCache reload CollectionLableBean success.");
			}
		} catch (Exception e) {
			logger.error("query HomeBean error.", e);
		}
		
		try {
    		//标签参数
			List<HeatBean> hbList = BaseDao.getListByAnnotation(HeatBean.class, "use_flag = 1", null);
			if (CollectionUtils.isNotEmpty(hbList))
			{
				Map<String, Integer> tempMap = new HashMap<String, Integer>();
				for(HeatBean hb : hbList)
				{
					tempMap.put(hb.getName(), hb.getValue());
				}
				heatPointMap = tempMap;
				logger.info("SysConfigCache reload HeatBean success.");
			}
		} catch (Exception e) {
			logger.error("query HeatBean error.", e);
		}
		
		try {
    		//标签参数
			List<TaskPointsConfigBean> tpcList = BaseDao.getListByAnnotation(TaskPointsConfigBean.class, "use_flag = 1", null);
			if (CollectionUtils.isNotEmpty(tpcList))
			{
				Map<String, TaskPointsConfigBean> tempMap = new HashMap<String, TaskPointsConfigBean>();
				for(TaskPointsConfigBean tpc : tpcList)
				{
					tempMap.put(tpc.getTask_name(), tpc);
				}
				taskPointsConfigMap = tempMap;
				logger.info("SysConfigCache reload TaskPointsConfigBean success.");
			}
		} catch (Exception e) {
			logger.error("query TaskPointsConfigBean error.", e);
		}
		
		try {
    		//标签参数
			List<UserTitleBean> userTitleList = BaseDao.getListByAnnotation(UserTitleBean.class, "use_flag = 1", null);
			if (CollectionUtils.isNotEmpty(userTitleList))
			{
				Map<String, String> tempMap = new HashMap<String, String>();
				for(UserTitleBean utb : userTitleList)
				{
					//tempMap.put(utb.getId().toString(), utb.getName());
					tempMap.put(utb.getId().toString(), utb.getImg_path());
				}
				userTitleMap = tempMap;
				logger.info("SysConfigCache reload UserTitleBean success.");
			}
		} catch (Exception e) {
			logger.error("query UserTitleBean error.", e);
		}
		try {
    		//专家和大咖的专项
			List<SpecialBean> specialBeanList = BaseDao.getListByAnnotation(SpecialBean.class, "use_flag = 1", null);
			if (CollectionUtils.isNotEmpty(specialBeanList))
			{
				Map<String, String> tempMap = new HashMap<String, String>();
				for(SpecialBean sb : specialBeanList)
				{
					tempMap.put(sb.getId().toString(), sb.getName());
				}
				specialMap = tempMap;
				logger.info("SysConfigCache reload SpecialBean success.");
			}
		} catch (Exception e) {
			logger.error("query SpecialBean error.", e);
		}
		
		try {
    		//枚举值
			List<EnumBean> enumBeanList = BaseDao.getListByAnnotation(EnumBean.class, "use_flag = 1", null);
			if (CollectionUtils.isNotEmpty(enumBeanList))
			{
				Map<String, String> tempMap = new HashMap<String, String>();
				for(EnumBean eb : enumBeanList)
				{
					tempMap.put(eb.getEnum_type() + eb.getEnum_code(), eb.getEnum_name());
				}
				enumMap = tempMap;
				logger.info("SysConfigCache reload EnumBean success.");
			}
		} catch (Exception e) {
			logger.error("query EnumBean error.", e);
		}
		
		//手动关闭数据库连接
		DBManager.closeConnection();
    }
    
    public static List<CollectionCategoryBean>  getCollectionCategorys()
    {
    	return collectionCategoryList;
    }
    
    public static List<CollectionPeriodBean>  getCollectionPeriods()
    {
    	return collectionPeriodList;
    }
    
    public static HomeBean getHomeBean()
    {
    	return homeBean;
    }
    
    private static void setHomeAdImages(HomeBean homeBean)
    {    	
		List<JSONObject> adImagesList = new ArrayList<JSONObject>();
		if (homeBean != null) {
			/*Method[] methods = HomeBean.class.getMethods();
			for (Method method : methods) {
				if (method.getName().startsWith("getAd_images")) {
					try {
						String adImage = (String) method.invoke(homeBean, null);
						if (StringUtils.isNotBlank(adImage)) {
							adImagesList.add(adImage);
						}
					} catch (IllegalArgumentException e) {
						logger.error("setHomeAdImages error.", e);
					} catch (IllegalAccessException e) {
						logger.error("setHomeAdImages error.", e);
					} catch (InvocationTargetException e) {
						logger.error("setHomeAdImages error.", e);
					}
				}
			}*/
			Method method = null;
			JSONObject adJson = null;
			for(int i= 1; i < 10; i++)
			{
				try {
					adJson = new JSONObject();
					method = HomeBean.class.getMethod("getAd_images" + i, null);
					String adImage = (String) method.invoke(homeBean, null);
					method = HomeBean.class.getMethod("getAd_path" + i, null);
					String adPath = (String) method.invoke(homeBean, null);
					if (StringUtils.isNotBlank(adImage)) {
						adJson.put("ad_image", adImage);
						if (StringUtils.isBlank(adPath))
						{
							adPath = "";
						}
						adJson.put("ad_path", adPath);
						adImagesList.add(adJson);
					}
				} catch (SecurityException e) {
					logger.error("setHomeAdImages error.", e);
				} catch (NoSuchMethodException e) {
					logger.error("setHomeAdImages error.", e);
				} catch (IllegalArgumentException e) {
					logger.error("setHomeAdImages error.", e);
				} catch (IllegalAccessException e) {
					logger.error("setHomeAdImages error.", e);
				} catch (InvocationTargetException e) {
					logger.error("setHomeAdImages error.", e);
				}
			}
		}
		
		if (CollectionUtils.isNotEmpty(adImagesList))
		{
			homeAdImageList = adImagesList;
		}
    }
    
    public static List<JSONObject> getHomeAdImages()
    {
    	return homeAdImageList;
    }
    
    public static List<JSONObject> getHomeAdImageList() {
		return homeAdImageList;
	}

	public static void setHomeAdImageList(List<JSONObject> homeAdImageList) {
		SysConfigCache.homeAdImageList = homeAdImageList;
	}

	public static void setHomeBean(HomeBean homeBean) {
		SysConfigCache.homeBean = homeBean;
	}

	public static String getCollectionLable(BigDecimal id)
    {
        String lableName = "";
    	if(id != null && labelMap.containsKey(id))
        {
    		lableName = labelMap.get(id);
        }
        return lableName;
    }
    
    public static String getCollectionPeriod(BigDecimal id)
    {
    	String period = "";
    	if(id != null && periodMap.containsKey(id))
        {
    		period = periodMap.get(id);
        }
        return period;
    }
    
    public static String getCollectionCategory(BigDecimal id)
    {
    	String category = "";
    	if(id != null && categoryMap.containsKey(id))
        {
    		category = categoryMap.get(id);
        }
        return category;
    }
    
    public static int getHeatValue(String name)
    {
    	int value = 1;
    	if (heatPointMap.containsKey(name))
    	{
    		value = heatPointMap.get(name);
    	}
    	return value;
    }
    
    public static Map<String, TaskPointsConfigBean> getTaskPointsConfigMap()
    {
    	return taskPointsConfigMap;
    }
    
    public static int getPointsValue(String name)
    {
    	int value = 0;
    	if (taskPointsConfigMap.containsKey(name))
    	{
    		value = taskPointsConfigMap.get(name).getPoints();
    	}
    	return value;
    }
    
    public static BigDecimal getPointTaskId(String name)
    {
    	BigDecimal id = new BigDecimal(0);
    	if (taskPointsConfigMap.containsKey(name))
    	{
    		id = taskPointsConfigMap.get(name).getId();
    	}
    	return id;
    }
    
    public static List<String> getUserTitle(String titleIds)
    {
    	List<String> titles = new ArrayList<String>();
    	if (StringUtils.isNotBlank(titleIds))
    	{
    		String[] ids = titleIds.split(",");
    		for(String id : ids)
    		{
    			if (userTitleMap.containsKey(id))
    			{
    				titles.add(userTitleMap.get(id));
    			}
    		}
    	}
    	return titles;
    }
    
    /**
     * 获得专家和大咖的专项
     * @param titleIds
     * @return
     */
    public static String getSpecialTitle(String titleIds)
    {
    	String titles = "";
    	if (StringUtils.isNotBlank(titleIds))
    	{
    		StringBuffer titlestr = new StringBuffer();
    		String[] ids = titleIds.split(",");
    		for(String id : ids)
    		{
				if (specialMap.containsKey(id))
				{
					titlestr.append("，").append(specialMap.get(id));
				}    				
    		}
    		if (titlestr.length() > 0)
    		{
    			titles = titlestr.substring(1);
    		}
    	}
    	return titles;
    }
    
    public static String getEnum(String code)
    {
    	if(enumMap.containsKey(code))
    	{
    		return enumMap.get(code);
    	}
    	return "";
    }
}
