package com.itheima.utils;

import java.util.UUID;

//CommonsUtils中用来封装项目中经常使用到的方法
public class CommonsUtils {

	//生成uuid方法
	public static String getUUID(){
		return UUID.randomUUID().toString();
	}
	
}
