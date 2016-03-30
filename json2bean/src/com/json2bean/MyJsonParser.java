package com.json2bean;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class MyJsonParser implements JsonParse{

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> toMap(String json) {
		return JSON.parseObject(json, Map.class);
	}

	@Override
	public List<Object> toArray(String json) {
		return JSON.parseArray(json, Object.class);
	}

	 

}
