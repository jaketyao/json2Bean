package com.json2bean;

import java.util.List;
import java.util.Map;

public interface JsonParse{
	Map<String,Object> toMap(String json);
	
	List<Object> toArray(String json);
}