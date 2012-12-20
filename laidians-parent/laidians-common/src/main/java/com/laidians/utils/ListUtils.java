/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.laidians.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.beanutils.BeanUtils;

/**
 * @author wxylon@gmail.com
 * @date 2012-8-17
 */
public class ListUtils {
	
	/**
	 * 
	 * @param <T>
	 * @param sources
	 * @param groupByAttribute
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @author wxylon@gmail.com
	 * @date 2012-8-17
	 */
	public static <T> List<List<T>> group(List<T> sources, final String groupByAttribute, String ... groupByAttributeSort) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List<List<T>> results = new ArrayList<List<T>>();
		if(null == groupByAttribute || groupByAttribute.trim().length() == 0){
			throw new NullPointerException("groupByAttribute must not be " + groupByAttribute);
		}
		
		if(null == sources || sources.isEmpty()){
			return results;
		}
		
		Map<Object, List<T>> values = new HashMap<Object, List<T>>();
		/**保存属性的次序*/
		final Set<String> sort_by = new TreeSet<String>();
		
		try {
			int index = 0;
			for(T t : sources){
				/**为空的对象，将对忽略掉*/
				if(null == t){
					continue;
				}
				/**根据指定属性进行分组*/
				
				String value = BeanUtils.getProperty(t, groupByAttribute);
				List<T> temp = values.get(value);
				if(temp == null){
					temp = new ArrayList<T>();
					values.put(value, temp);
				}
				temp.add(t);
				
				/**根据原集合的顺序，对 groupByAttribute属性进行次序保存*/
				sort_by.add(value);
			}
//		results.addAll(values.values());
			
			
			if(null == groupByAttributeSort || groupByAttributeSort.length == 0){
				for(String key : sort_by){
					results.add(values.get(key));
				}
			}else{
				for(int i = 0; i < groupByAttributeSort.length; i++){
					List<T> temp = values.get(groupByAttributeSort[i]);
					if(null == temp){
						temp = new ArrayList<T>();
					}
					results.add(temp);
				}
			}
		} catch (IllegalAccessException e) {
			throw e;
		} catch (InvocationTargetException e) {
			throw e;
		} catch (NoSuchMethodException e) {
			throw e;
		}
		return results;
	}
}

