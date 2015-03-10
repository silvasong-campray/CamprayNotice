/**   
 * @Title: ObjectPeropertyMapping.java 
 * @Package com.bps.commons 
 *
 * @Description: User Points Management System
 * 
 * @date Nov 8, 2014 4:32:19 PM
 * @version V1.0   
 */ 
package com.campraynotice.commons;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;

/** 
 * <p>Types Description</p>
 * @ClassName: ObjectPeropertyMapping 
 * @author Phills Li 
 * 
 */
public class ConvertTools {
	
	public static void json2Model(JSONObject jsonObj,Object model){
		Field[] fields=model.getClass().getDeclaredFields(); 		
		for (Field field : fields) {
			String name=field.getName();
			if(jsonObj.containsKey(name)){
				try {
					if(field.get(model) instanceof String){
						field.set(model, jsonObj.getString(name));
					}
					else if(field.get(model) instanceof Integer){
						field.setInt(model, jsonObj.getInteger(name));
					}
					else if(field.get(model) instanceof Long){
						field.setLong(model, jsonObj.getLong(name));
					}
					else if(field.get(model) instanceof Short){
						field.setShort(model, jsonObj.getShort(name));
					}
					else if(field.get(model) instanceof Float){
						field.setFloat(model, jsonObj.getFloat(name));
					}
					else if(field.get(model) instanceof Double){
						field.setDouble(model, jsonObj.getDouble(name));
					}
					else if(field.get(model) instanceof Byte){
						field.setByte(model, jsonObj.getByte(name));
					}
					else if(field.get(model) instanceof Boolean){
						field.setBoolean(model, jsonObj.getBoolean(name));
					}
					else if(field.get(model) instanceof Object){
						field.set(model, jsonObj.get(name));
					}
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch (IllegalAccessException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			
		}
	}
	
	public static Integer[] stringArr2IntArr(String[] strArr){
		Integer[] idArr=new Integer[strArr.length];
		for (int i = 0; i < idArr.length; i++) {
			idArr[i]=Integer.parseInt(strArr[i]);
		}
		return idArr;
	}
	
	public static String longToDateString(long dateLong){
		SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return sdf.format(new Date(dateLong));
	}
	
	public static long dateStringToLong(String date) throws ParseException{
		SimpleDateFormat sdf =new SimpleDateFormat("dd/mm/yyyy hh:mm");
		return sdf.parse(date).getTime();
	}
}
