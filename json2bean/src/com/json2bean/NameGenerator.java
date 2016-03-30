package com.json2bean;


public interface NameGenerator{
	String nextName();
	/**
	 * 格式化标识符，驼峰式写法
	 * @param name
	 * @return
	 */
	String formatName(String name);
}