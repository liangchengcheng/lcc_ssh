package com.lcc.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DaoFactory {
	private static Properties p=new Properties();
	//缓存集合
	private static Map<String,Object> cach=new HashMap<String,Object>();
	
	static{
		InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream("dao.properties");
		try {
			p.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Object getInstacne(String daoName){
		Object obj=cach.get(daoName);
		if(obj==null){
			String clazz=p.getProperty(daoName);
			if(clazz!=null&&!clazz.equals("")){
				try {
					obj=Class.forName(clazz).newInstance();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		return obj;
	}

	/**
	 * 传入dao名，创建这个实现类的一个实例
	 * @param daoName dao名
	 * @return 这个实现类的一个实例
	 */
	public synchronized static <T> T getInstance(String daoName, Class daoClass){
		T obj = (T)cach.get(daoName);
		
		if(null == obj){
			String className = p.getProperty(daoName);
			
			if(null != className && !"".equals(className)){
				try {
					//加载指定名字的字节码到虚拟机内存中
					Class clazz = Class.forName(className);
					//通过反射机制调用无参数的那个构造方法来创建出一个对象
					obj = (T)daoClass.cast(clazz.newInstance());
					//往缓存池中存放
					cach.put(daoName, obj);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		return obj;
	}
}
