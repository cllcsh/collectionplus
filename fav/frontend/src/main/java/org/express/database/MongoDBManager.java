package org.express.database;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.bson.types.ObjectId;
import org.express.common.bean.PageBean;
import org.express.util.Convert;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.MongoOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;

/**
 * Mongo数据库管理器
 * @author pingxl
 *
 */
@SuppressWarnings("deprecation")
public class MongoDBManager
{
	private static Mongo mongo = null;
	private static DB db = null;
	private final static MongoDBManager instance = new MongoDBManager();

	/**
	 * 实例化
	 *  
	 * @return
	 * @throws Exception
	 */
	public static MongoDBManager getInstance() throws Exception
	{
		return instance;
	}

	static
	{
		try
		{
			Properties dbProperties = new Properties();
			dbProperties.load(MongoDBManager.class.getResourceAsStream("/config/mongo.properties"));
			
			Properties cp_props = new Properties();
			for (Object key : dbProperties.keySet())
			{
				String skey = (String) key;
				if (skey.startsWith("mongo."))
				{
					String name = skey.substring(6);
					cp_props.put(name, dbProperties.getProperty(skey));
				}
			}
			
			MongoOptions mongoOptions = new MongoOptions();
			mongoOptions.connectionsPerHost = Convert.toInt(cp_props.getProperty("connectionsPerHost"));
			mongoOptions.connectTimeout=Convert.toInt(cp_props.getProperty("connectTimeout"));
			mongoOptions.slaveOk = true;
			
			List<ServerAddress> list= new ArrayList<ServerAddress>();
			ServerAddress sap0 = new ServerAddress(cp_props.getProperty("host"),Convert.toInt(cp_props.getProperty("port")));
			list.add(sap0);

			mongo = new Mongo(list,mongoOptions);
			mongo.setReadPreference(ReadPreference.SECONDARY);
			db = mongo.getDB(cp_props.getProperty("database"));
			db.authenticate(cp_props.getProperty("user"), cp_props.getProperty("password").toCharArray());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	

	public static DBCollection getCollection(String collection)
	{
		return db.getCollection(collection);
	}

	/**
	 * 插入
	 * 
	 * @param collection
	 * @param map
	 */
	public void insert(String collection, Map<String, Object> map)
	{
		try
		{
			DBObject dbObject = map2Obj(map);
			getCollection(collection).insert(dbObject);
		}
		catch (MongoException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 批量插入
	 * 
	 * @param collection
	 * @param list
	 */
	public void insertBatch(String collection, List<Map<String, Object>> list)
	{
		if (list == null || list.isEmpty())
		{
			return;
		}
		try
		{
			List<DBObject> objects = new ArrayList<DBObject>();
			for (int i = 0; i < list.size(); i++)
			{
				DBObject dbObject = map2Obj(list.get(i));
				objects.add(dbObject);
			}
			getCollection(collection).insert(objects);
		}
		catch (MongoException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 删除
	 * 
	 * @param collection
	 * @param map
	 */
	public void delete(String collection, Map<String, Object> map)
	{
		DBObject obj = map2Obj(map);
		getCollection(collection).remove(obj);
	}

	/**
	 * 批量删除
	 * 
	 * @param collection
	 * @param list
	 */
	public void deleteBatch(String collection, List<Map<String, Object>> list)
	{
		if (list == null || list.isEmpty())
		{
			return;
		}
		for (int i = 0; i < list.size(); i++)
		{
			getCollection(collection).remove(map2Obj(list.get(i)));
		}
	}

	/**
	 * 删除全部
	 * 
	 * @param collection
	 * @param map
	 */
	public void deleteAll(String collection)
	{
		List<DBObject> rs = findAll(collection);
		if (rs != null && !rs.isEmpty())
		{
			for (int i = 0; i < rs.size(); i++)
			{
				getCollection(collection).remove(rs.get(i));
			}
		}
	}

	/**
	 * 更新一条
	 * 只更新指定字段
	 * @param collection
	 * @param setFields
	 * @param whereFields
	 */
	public void update(String collection, Map<String, Object> whereFields, Map<String, Object> setFields)
	{
		DBObject where = map2Obj(whereFields);
		DBObject set = map2Obj(setFields);
		DBObject updateSetValue = new BasicDBObject("$set",set);
		getCollection(collection).update(where, updateSetValue);
	}

	/**
	 * 批量更新
	 * 只更新指定字段
	 * @param collection
	 * @param whereFields
	 * @param setFields
	 */
	public void updateMulti(String collection,Map<String,Object> whereFields,Map<String,Object> setFields)
	{
		DBObject where = map2Obj(whereFields);
		DBObject set = map2Obj(setFields);
		DBObject updateSetValue = new BasicDBObject("$set",set);
		getCollection(collection).updateMulti(where, updateSetValue);
	}
	
	/**
	 * 计算满足条件条数
	 * 
	 * @param collection
	 * @param map
	 */
	public long getCount(String collection, Map<String, Object> map)
	{
		return getCollection(collection).getCount(map2Obj(map));
	}

	/**
	 * 计算集合总条数
	 * 
	 * @param collection
	 * @param map
	 */
	public long getCount(String collection)
	{
		DBCursor dc = getCollection(collection).find();
		long count = dc.count();
		dc.close();
		return count;
	}

	/**
	 * 查找对象（根据主键_id）
	 * 
	 * @param collection
	 * @param _id
	 */
	public DBObject findById(String collection, String _id)
	{
		DBObject obj = new BasicDBObject();
		obj.put("_id", ObjectId.massageToObjectId(_id));
		return getCollection(collection).findOne(obj);
	}

	/**
	 * 查找（返回一个对象）
	 * 
	 * @param map
	 * @param collection
	 */
	public DBObject findOne(String collection, Map<String, Object> map)
	{
		DBCollection coll = getCollection(collection);
		return coll.findOne(map2Obj(map));
	}

	/**
	 * 查找（返回一个List<DBObject>）
	 * 带入排序
	 * @param <DBObject>
	 * @param map
	 * @param collection
	 * @throws Exception
	 */
	public List<DBObject> find(String collection, Map<String, Object> map,DBObject orderBy)
	{
		DBCollection coll = getCollection(collection);
		DBCursor c = coll.find(map2Obj(map)).sort(orderBy);
		try
		{
			if (c != null)
				return c.toArray();
				
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			c.close();
		}
		return null;
	}
	
	/**
	 * 查找（返回一个List<DBObject>）
	 * 带入排序和分页
	 * @param <DBObject>
	 * @param map
	 * @param collection
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	public List<DBObject> find(String collection, DBObject map, DBObject orderBy, PageBean bean)
	{
		DBCollection coll = getCollection(collection);
		DBCursor c = null;
		//条件查询
		if(map != null)
			c = coll.find(map);
		
		//排序
		if(orderBy != null)
			c = c.sort(orderBy);
		
		//分页
		if(bean != null)
		{
			if(bean.getPageIndex() == 0)
				c = c.skip(0).limit(bean.getPageCount());
			else {
				c = c.skip(bean.getPageIndex()-1).limit(bean.getPageCount());
			}
		}
			
		
		try
		{
			if (c != null)
				return c.toArray();
				
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			c.close();
		}
		return null;
	}
	
	public List<DBObject> find(String collection, Map<String, Object> map)
	{
		DBCollection coll = getCollection(collection);
		DBCursor c = coll.find(map2Obj(map));
		try
		{
			if (c != null)
				return c.toArray();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			c.close();
		}
		return null;
	}

	/**
	 * 查找集合所有对象
	 * 
	 * @param collection
	 */
	public List<DBObject> findAll(String collection)
	{
		return getCollection(collection).find().toArray();
	}

	private DBObject map2Obj(Map<String, Object> map)
	{
		DBObject dbObject = new BasicDBObject();
		if(map != null && map.size() > 0)
		{
			Iterator<String> it = map.keySet().iterator();
			while (it.hasNext())
			{
				String key = it.next();
				dbObject.put(key, map.get(key));
			}
		}
		return dbObject;
	}
}
