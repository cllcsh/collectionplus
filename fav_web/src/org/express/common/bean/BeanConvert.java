package org.express.common.bean;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.express.common.convert.BooleanConvert;
import org.express.common.convert.DateConvert;
import org.express.common.convert.DecimalConvert;
import org.express.common.convert.LongConvert;
import org.express.data.DataColumn;
import org.express.data.DataRow;
import org.express.data.DataTable;
import org.express.data.DataTypes.DataType;

public class BeanConvert 
{
	/**
	 * 将Bean转化成Map
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> convertBean(Object obj) 
	{
		Map<String, Object> map = new HashMap<String, Object>();
		 @SuppressWarnings("rawtypes")
		Class cls = obj.getClass();
	    Field[] fields = cls.getDeclaredFields();
	    for(int i=0; i<fields.length; i++){
	    	Field f = fields[i];
			f.setAccessible(true);
			try {
				FieldMeta meta = f.getAnnotation(FieldMeta.class);
				if(meta != null && !meta.isNative())
				{
					continue;
				}
				
				map.put(f.getName(), f.get(obj));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	    }
		return map;
	}
	
	/**
	 * 将Map转化成指定的Bean
	 * @param beanClass
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T convertBean(Class<T> beanClass , Map<String,Object> map)
	{
		T t = null;
		
		try {
			t = (T) Class.forName(beanClass.getName()).newInstance();
			for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext();) {
				String key = iterator.next();
				BeanUtils.setProperty(t, key, map.get(key));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return t;
	}
	
	/**
	 * 将指定的List<bean>对象转化成DataTable
	 * @param beanClass
	 * @param list
	 * @return
	 */
	public static <T> DataTable convertBean(Class<T> beanClass ,List<T> list)
	{
		DataTable dt = new DataTable();
		Field[] fields = beanClass.getDeclaredFields();
	    for(int i=0; i<fields.length; i++){
	    	Field f = fields[i];
			f.setAccessible(true);
			try {
				FieldMeta meta = f.getAnnotation(FieldMeta.class);
				if(meta != null && !meta.isNative())
				{
					continue;
				}
				dt.addColumn(f.getName() , DataType.Object);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    
	    if(list != null)
	    {
	    	for(T t : list)
	    	{
	    		DataRow dr = dt.newRow();
	    		
	    		 for(int i=0; i<fields.length; i++){
    		    	Field f = fields[i];
    				f.setAccessible(true);
    				try {
    					FieldMeta meta = f.getAnnotation(FieldMeta.class);
    					if(meta != null && !meta.isNative())
    					{
    						continue;
    					}
    					dr.setValue(f.getName(), f.get(t));
    				} catch (IllegalArgumentException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}
	    		
	    		dt.addRow(dr);
	    	}
	    }
	    
		return dt;
	}
	
	/**
	 * 将DataTable转化成指定的List<Bean>
	 * @param beanClass
	 * @param dt
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> convertBean(Class<T> beanClass , DataTable dt) {
		List<T> list = new ArrayList<T>();
		T t = null;
		
		try {
			RegisterConvert();
			t = (T) Class.forName(beanClass.getName()).newInstance();
			if(dt != null)
			{
				for(DataRow dr : dt.getRows())
				{
					for(DataColumn dc : dt.getColumns())
					{
						BeanUtils.setProperty(t, dc.getColumnName(), dr.getValue(dc.getColumnName()));
					}
					list.add(t);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static void RegisterConvert()
	{
		ConvertUtils.register(new DateConvert(), Date.class);
		ConvertUtils.register(new BooleanConvert(), boolean.class);
		ConvertUtils.register(new DecimalConvert(), BigDecimal.class);
		ConvertUtils.register(new LongConvert(), BigDecimal.class);
	}
	
	/*public static class test
	{
		@FieldMeta(isPrimary=true , order=1)
		private String a = "";
		@FieldMeta(order=2)
		private String b = "";
		@FieldMeta(order=3)
		private String c = "";
		@FieldMeta(order=4)
		private String d = "";
		@FieldMeta(isNative=false , order=5)
		private String e = "";
		public String getA() {
			return a;
		}
		public void setA(String a) {
			this.a = a;
		}
		public String getB() {
			return b;
		}
		public void setB(String b) {
			this.b = b;
		}
		public String getC() {
			return c;
		}
		public void setC(String c) {
			this.c = c;
		}
		public String getD() {
			return d;
		}
		public void setD(String d) {
			this.d = d;
		}
		public String getE() {
			return e;
		}
		public void setE(String e) {
			this.e = e;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return a +","+  b+"," + c+"," + d+"," + e;
		}
	}
	
	public static void main(String[] args) 
	{
		test t  = new test();
		t.setA("a1");
		t.setB("b2");
		t.setC("c3");
		t.setD("d4");
		t.setE("e5");
		
		Map<String, Object> map = convertBean(t);
		test t1 = convertBean(test.class, map);
		List<test> list = new ArrayList<BeanConvert.test>();
		list.add(t1);
		DataTable dt = convertBean(test.class , list);
		List<test> list1 = convertBean(test.class , dt);
		
		System.out.println(map);
		System.out.println(t1);
		System.out.println(dt.getRows().get(0));
		System.out.println(list1.get(0));
	}*/
}
