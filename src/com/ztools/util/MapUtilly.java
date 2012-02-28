package com.ztools.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MapUtilly {

	/**
	 * &#25226;&#109;&#97;&#112;&#20013;&#107;&#101;&#121;&#30340;&#23545;&#24212;&#30340;&#20540;&#25918;&#20837;&#99;&#108;&#97;&#115;&#115;&#20013;&#30340;&#23545;&#24212;&#30340;&#23646;&#24615;&#32;
	 * @param map
	 * @param c
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	public static Object mapToObject(Map map,Class c) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException{
		if(c!=null&&map.size()>0){
			Object o = c.newInstance();
			Method[] methods = c.getMethods();
			Method m = null;
			String methodName = null;
			for(int i =0;i<methods.length;i++){
				m = methods[i];
				methodName = m.getName();
				if(methodName.startsWith("set")){
					Object emp = map.get(methodName.substring(3,4).toLowerCase()+methodName.substring(4));
					if(emp!=null){
						m.invoke(o,emp);
					}
					
				}
			}
			return o;
		}
		return null;
	}
	
	/**
	 * &#25226;&#111;&#98;&#106;&#101;&#99;&#116;&#20013;&#30340;&#23545;&#24212;&#30340;&#23646;&#24615;&#30340;&#20540;&#32;&#25918;&#20837;&#109;&#97;&#112;&#60;&#23545;&#35937;&#23646;&#24615;&#44;&#23646;&#24615;&#23545;&#24212;&#30340;&#20540;&#62;&#32;
	 * @param object
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException 
	 */
	public static Map ObjectTomap(Object object) throws IllegalArgumentException, InvocationTargetException, IllegalAccessException{
		Map map = null;
		if(object!=null){
			map = new HashMap();
			Class c = object.getClass();
			Method[] methods = c.getMethods();
			Method m = null;
			String methodName = null;
			for(int i =0;i<methods.length;i++){
				m = methods[i];
				methodName = m.getName();
				if(methodName.startsWith("get")&&!"getClass".equals(methodName)){
					map.put(methodName.substring(3,4).toLowerCase()+methodName.substring(4), m.invoke(object));
				}
			}
		}
		
		
		return map;
	}

}
