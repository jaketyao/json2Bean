package com.json2bean;

import java.io.IOException;
import java.util.Map;

public interface BeanGenerator {

	/**
	 * 
	 * @param className 类名
	 * @param map  字段及类型
	 * @throws IOException
	 */
	void writeBean(String className,Map<String,Object>map) throws IOException;
	/**
	 * 
	 * @param list List<....>
	 * @throws IOException
	 */
	void writeList (String list) throws IOException;
}
