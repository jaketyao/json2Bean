package com.json2bean;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
/**
 * 
 * @author Young  2016-3-30</br></br>
 * 
 * 若json以 { 开头，则用fastjson解析成Map<String,Object>，
 * 依次取出map中的值，若值为null则不处理该字段，若instanceof Integer则该字段处理完毕，该字段对应的类型为Integer。
 * Double,String时同理。
 * </br></br>
 *  若值以 { 开头则又是一个新的Bean，类名为map中的键（nameGeneration.formatName格式化后的驼峰式写法），则交给新的Json2Bean处理</br></br>
 *  
 *若值以 [ 开头说明是一个List，泛型参数需要根据[ ] 内部json决定，待内部json处理完毕后返回的就是类型（例如  [{"name":"young","age":20}]   这个json是一个List,这种情况时泛型参数可以在[ ]内数据处理前就得到， 
 *但 如果json是这样的  [1,2,3]或 ["a","b","c"]  泛型参数就不能随意规定了，只能是Integer或String，所以要等[] 内部json处理完毕后才能得到类型）</br></br>
 *
 *若map中值以[ 开头说明值对应的json、是一个List，这时用fastjson解析得到List<Object>，若list为null或空，则不处理该字段（因 泛型参数无法确定）
 *同理，当list中第一个元素  instancof Double时说明是List<Double>，Integer时还要遍历list决定到底是Integer还是Double（例如 [1,2.0,3.0]），若值以 { 开头说明是一个对象，对象名我们无法根据json字符串获取，这时使用nameGeneration.nextName()
 *生成一个名字作为泛型参数，并构造该类
 */
public class Json2Bean{
	private String json;
	private JsonParse jsonParse;
	private String name;
	private NameGenerator nameGeneration;
	private BeanGenerator generationBean;
	public Json2Bean(String json,String name,NameGenerator nameGeneration,JsonParse jsonParse,BeanGenerator generationBean){
		this.json=json;
		this.name=name;
		this.nameGeneration=nameGeneration;
		this.jsonParse=jsonParse;
		this.generationBean=generationBean;
		
	}
	
	public String execute() throws IOException{
		if (json.startsWith("{")) {
			  parseMap();
			  return null;
		}else if (json.startsWith("[")) {
			String clz= parseArray();
			if (name==null) {
				return clz;
			}
 
			generationBean.writeList(clz);
			
			return clz;
		}
		return null;
	}

	private void parseMap() throws IOException {
		Map<String,Object>map=jsonParse.toMap(json);
		
		Iterator<Entry<String,Object>>itr=map.entrySet().iterator();
		
		while (itr.hasNext()) {
			Entry<String, Object> entry= itr.next();
			String k=entry.getKey();
			Object v=entry.getValue();
			if (v==null) {
				itr.remove();
				continue;
			}
			if (v instanceof Integer) {
				entry.setValue("Integer");
			}else if (v instanceof BigDecimal) {
				entry.setValue("Double");
			}else if (v instanceof String) {
				entry.setValue("String");
			}else{
				String childJson=v.toString();
				if (childJson.startsWith("{")) {
					 
					String childName=nameGeneration.formatName(k) ;
					entry.setValue(childName);
					new Json2Bean(childJson, childName, nameGeneration, jsonParse,generationBean).execute();
				}else if (childJson.startsWith("[")) {
				 
					String childName=new Json2Bean(childJson, null, nameGeneration, jsonParse,generationBean).execute();
 
					entry.setValue(childName);
				}else{
					entry.setValue("String");
				}
			
			}

		}
		 
		generationBean.writeBean(name, map);
 
	}
	
	private String parseArray() throws IOException {
		List<Object>list=jsonParse.toArray(json);
		if (list==null||list.size()==0) {
			return null;
		}
		Object v=list.get(0);
		if (v instanceof Integer) {
			for (int i =1; i < list.size(); i++) {
				v=list.get(i);
				if (v instanceof BigDecimal) {
					return "List<Double>" ;
				}
			}
			return "List<Integer>" ;
		}else if (v instanceof BigDecimal) {
			return "List<Double>" ;
		}else if (v instanceof String) {
			return "List<String>" ;
		}else{
			String childJson=v.toString();
			if (childJson.startsWith("{")) {
				String childName=nameGeneration.nextName();
				 
				new Json2Bean(childJson, childName, nameGeneration, jsonParse,generationBean).execute();
 
				return "List<"+childName+">" ;
			}else if (childJson.startsWith("[")) {
				String childName=new Json2Bean(childJson, null, nameGeneration, jsonParse,generationBean).execute();
				return "List<"+childName+">" ;
 
			}else{
				return "List<String>" ;
			}
		
		}

	}
	
	
}