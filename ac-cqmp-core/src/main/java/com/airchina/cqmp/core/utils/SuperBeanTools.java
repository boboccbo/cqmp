package com.airchina.cqmp.core.utils;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 超级Bean工具类，可以解决你的所有问题！
*<p>
* CopyRight (c):Sinosoft 
*</p>
*<p> 
* Project: prpall 
* </p>
 */
public class SuperBeanTools {
	
	@SuppressWarnings("unused")
	private static final String UID_NAME = "serialVersionUID";
	
	private static final String T_TABLE_PREFIX = "prpt";
	
	private static final String R_TABLE_PREFIX = "prpr";
	
	private static final String CP_TABLE_PREFIX = "prpcp";
	
	private static Logger logger = LoggerFactory.getLogger(SuperBeanTools.class);
	
	
	/** mergePO 时支持的数据类型 */
	@SuppressWarnings("unchecked")
	private static Map<Class, String> supportTypeMap = new HashMap<Class, String>();
	static {
		supportTypeMap.put(Integer.class, "");
		supportTypeMap.put(Long.class, "");
		supportTypeMap.put(Double.class, "");
		supportTypeMap.put(BigDecimal.class, "");
		supportTypeMap.put(String.class, "");
		supportTypeMap.put(Date.class, "");
		supportTypeMap.put(Boolean.class, "");
		supportTypeMap.put(byte[].class, "");
	}

	
	
	
	
	public SuperBeanTools(){
		
	}
	
	/**
	 * 超级bean拷贝工具
	 * @param source 拷贝来源
	 * @param target 拷贝目标
	 * @param isCopyNull 是否拷贝空值
	 * @throws  
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static void simpleCopy(Object source ,Object target,Boolean isCopyNull){
		if (target == null || source == null) {
			return;
		}
		List targetMethodList = BeanUtils.getSetter(target.getClass());
		List sourceMethodList = BeanUtils.getGetter(source.getClass());
		Map<String, Method> map = new HashMap<String, Method>();
		for (Iterator iter = sourceMethodList.iterator(); iter.hasNext();) {
			Method method = (Method) iter.next();
			map.put(method.getName().toUpperCase(), method);
		}
		for (Iterator iter = targetMethodList.iterator(); iter.hasNext();) {
			Method method = (Method) iter.next();
			String fieldName = method.getName().substring(3);
			try {
				Method sourceMethod = (Method) map.get("get".toUpperCase() + fieldName.toUpperCase());
				if (sourceMethod == null) {
					sourceMethod = (Method) map.get("is".toUpperCase() + fieldName.toUpperCase());
				}
				if (sourceMethod == null) {
					continue;
				}
				if (!supportTypeMap.containsKey(sourceMethod.getReturnType())) {
					continue;
				}

				Object value = sourceMethod.invoke(source, new Object[0]);
				if (isCopyNull) {
					method.invoke(target, new Object[] { value });
				} else {
					if (value != null) {
						method.invoke(target, new Object[] { value });
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				if(logger.isDebugEnabled()){
					logger.debug("超级bean拷贝工具拷贝方法出错",e);
				}
			}
		}
		
	}
	
	private static String genKey(Field field){
		if(field.getName().toLowerCase().startsWith(CP_TABLE_PREFIX)){
			return field.getName().substring(CP_TABLE_PREFIX.length()).toUpperCase();
			
		}else if(field.getName().toLowerCase().startsWith(T_TABLE_PREFIX)){
			return field.getName().substring(T_TABLE_PREFIX.length()).toUpperCase();
			
		}else if(field.getName().toLowerCase().startsWith(R_TABLE_PREFIX)){
			return field.getName().substring(R_TABLE_PREFIX.length()).toUpperCase();
		}
		
		
		return null;
	}

	@SuppressWarnings("unchecked")
	public static List getGenericList(List source,Class type) throws InstantiationException, IllegalAccessException, SecurityException, NoSuchFieldException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
		List list = new ArrayList();
		for (Object object : source) {
			Object ele = type.newInstance();
			Map<String,Field> fieldMap = new HashMap<String, Field>();
			for(Field ff : type.getDeclaredFields()){
				fieldMap.put(ff.getName(), ff);
			}
			
			simpleCopy(object, ele, true);
			
			
			if(fieldMap.containsKey("id")){
				Class idClazz = type.getDeclaredField("id").getType();
				Object idObj = idClazz.newInstance();
				Object dataIdObj =object.getClass().getMethod("getId", new Class[]{}).invoke(object, new Object[]{});
				simpleCopy(dataIdObj, idObj, true);
				Method setIdMethod = ele.getClass().getMethod("setId", new Class[]{idClazz});
				setIdMethod.invoke(ele, new Object[]{idObj});
			}
			list.add(ele);
		}
		return list;
	}
	
	
	
	/**
	 * 返回指定对象对应的Class内的字段域，不包括classzz中指定的类型
	 * @param obj 
	 * @param clazzes 不包括的类型数组
	 * @return 如果clazzes为空，则返回所有field
	 */
	@SuppressWarnings("unchecked")
	public static List<Field> getFieldsWithOutType(Object obj ,Class[] clazzes){
		List<Field> retList = new ArrayList<Field>(0);
		Field [] fields = obj.getClass().getDeclaredFields();
		if(clazzes == null || clazzes.length ==0){
			return Arrays.asList(fields);
		}
		for (Field field : fields) {
			for (Class clazz : clazzes) {
				if(!field.getType().isAssignableFrom(clazz)){
					retList.add(field);
				}
			}
		}
		return retList;
	}
	
	
	public static void printPOJO(Object obj) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		Method[] methods = obj.getClass().getMethods();
		for (Method method : methods) {
			if(method.getName().startsWith("get")){
				@SuppressWarnings("unused")
				Object ojbret = method.invoke(obj, new Object[]{});
			}
		}
	}
	
	public static Date stringToDate(String str) {   
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
        Date date2 = null;
        try {   
             date2 = df.parse(str);   
        } catch (ParseException e) {   
            e.printStackTrace();   
        } 
        return date2;
    }   

	
	public static void main(String[] args) throws SecurityException, NoSuchMethodException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
	}
	

}
