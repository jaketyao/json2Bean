package com.json2bean.test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.json2bean.Json2Bean;
import com.json2bean.MyBeanGenerator;
import com.json2bean.MyJsonParser;
import com.json2bean.MyNameGenerator;

/**
 * 测试   生成的bean位于工程src目录下（refresh工程后显示）
 * @author Young
 *
 */
public class Main {


	
	public static void main(String[] args) throws IOException {
		
		String ss="{\"count\":0.0,\"doubleList\":[1,2,3.0,4],\"intList\":[1,2,3,4,5,6]}";
		System.out.println(ss);
	 	new Json2Bean(ss, "RootBean", new MyNameGenerator(), new MyJsonParser(),new MyBeanGenerator("com.test1")).execute();
	    System.out.println("------------------------------");
		
		
		String s="{\"doubleList\":[1,2.0000,3,4,5,6],\"intList\":[1,2,3,4,5,6],\"multyList\":[[[\"d\",\"e\",\"f\"],[\"d1\",\"e1\",\"f1\"]],[[\"d\",\"e\",\"f\"],[\"d2\",\"e2\",\"f2\"]]],\"code\":0,\"data\":{\"count\":\"6\",\"list\":[{\"pid\":\"236\",\"author\":\"M12345678977\",\"author_id\":\"31\",\"subject\":\"\u4e5d\u5934\",\"dateline\":\"1459232596\",\"message\":\"\u554a\u805a\u805a\u51e0\u53f7\u6765\",\"useip\":\"2104960333\",\"invisible\":\"0\",\"status\":\"1\",\"comment\":\"0\",\"position\":\"0\",\"tid\":\"121\",\"fid\":\"1\",\"dateline_show\":\"2016-03-29 14:23:16\",\"user_info\":{\"id\":\"31\",\"username\":\"M12345678977\",\"nickname\":\"123566\",\"head\":\"948\",\"group\":{\"adminid\":\"2\",\"alloweditpost\":\"1\",\"allowstickthread\":\"1\",\"allowdelpost\":\"1\",\"allowbanuser\":\"1\",\"allowdigestthread\":\"1\",\"allowpost\":\"1\",\"name\":\"\u7248\u4e3b\",\"fid\":\"1\"}},\"img\":[\"948\"]},{\"pid\":\"237\",\"author\":\"M18267152148\",\"author_id\":\"27\",\"subject\":\"\",\"dateline\":\"1459234314\",\"message\":\"thugs \",\"useip\":\"2062279264\",\"invisible\":\"0\",\"status\":\"1\",\"comment\":\"0\",\"position\":\"1\",\"tid\":\"121\",\"fid\":\"1\",\"dateline_show\":\"2016-03-29 14:51:54\",\"user_info\":{\"id\":\"27\",\"username\":\"M18267152148\",\"nickname\":\"123456\",\"head\":\"865\",\"group\":{\"adminid\":\"2\",\"alloweditpost\":\"1\",\"allowstickthread\":\"1\",\"allowdelpost\":\"1\",\"allowbanuser\":\"1\",\"allowdigestthread\":\"1\",\"allowpost\":\"1\",\"name\":\"\u7248\u4e3b\",\"fid\":\"1\"}},\"img\":[\"865\"]},{\"pid\":\"238\",\"author\":\"M18267152148\",\"author_id\":\"27\",\"subject\":\"\",\"dateline\":\"1459234741\",\"message\":\"hfs schizophrenia [:f00}[:f01}[:f02}\",\"useip\":\"2062279264\",\"invisible\":\"0\",\"status\":\"1\",\"comment\":\"0\",\"position\":\"2\",\"tid\":\"121\",\"fid\":\"1\",\"dateline_show\":\"2016-03-29 14:59:01\",\"user_info\":{\"id\":\"27\",\"username\":\"M18267152148\",\"nickname\":\"123456\",\"head\":\"865\",\"group\":{\"adminid\":\"2\",\"alloweditpost\":\"1\",\"allowstickthread\":\"1\",\"allowdelpost\":\"1\",\"allowbanuser\":\"1\",\"allowdigestthread\":\"1\",\"allowpost\":\"1\",\"name\":\"\u7248\u4e3b\",\"fid\":\"1\"}},\"img\":[]},{\"pid\":\"239\",\"author\":\"M18267152148\",\"author_id\":\"27\",\"subject\":\"\",\"dateline\":\"1459234984\",\"message\":\"[:f020}[:f021}[:f022}[:f010}[:f009}\",\"useip\":\"2062279264\",\"invisible\":\"0\",\"status\":\"1\",\"comment\":\"0\",\"position\":\"3\",\"tid\":\"121\",\"fid\":\"1\",\"dateline_show\":\"2016-03-29 15:03:04\",\"user_info\":{\"id\":\"27\",\"username\":\"M18267152148\",\"nickname\":\"123456\",\"head\":\"865\",\"group\":{\"adminid\":\"2\",\"alloweditpost\":\"1\",\"allowstickthread\":\"1\",\"allowdelpost\":\"1\",\"allowbanuser\":\"1\",\"allowdigestthread\":\"1\",\"allowpost\":\"1\",\"name\":\"\u7248\u4e3b\",\"fid\":\"1\"}},\"img\":[]},{\"pid\":\"240\",\"author\":\"M18267152148\",\"author_id\":\"27\",\"subject\":\"\",\"dateline\":\"1459235016\",\"message\":\"Sfyhgff\",\"useip\":\"2062279264\",\"invisible\":\"0\",\"status\":\"1\",\"comment\":\"0\",\"position\":\"4\",\"tid\":\"121\",\"fid\":\"1\",\"dateline_show\":\"2016-03-29 15:03:36\",\"user_info\":{\"id\":\"27\",\"username\":\"M18267152148\",\"nickname\":\"123456\",\"head\":\"865\",\"group\":{\"adminid\":\"2\",\"alloweditpost\":\"1\",\"allowstickthread\":\"1\",\"allowdelpost\":\"1\",\"allowbanuser\":\"1\",\"allowdigestthread\":\"1\",\"allowpost\":\"1\",\"name\":\"\u7248\u4e3b\",\"fid\":\"1\"}},\"img\":[]},{\"pid\":\"241\",\"author\":\"M12345678977\",\"author_id\":\"31\",\"subject\":\"\",\"dateline\":\"1459238898\",\"message\":\"\u6cd5\u56fd\u548c\u9ec4\u91d1\u5b63\u8282\",\"useip\":\"2104960333\",\"invisible\":\"0\",\"status\":\"1\",\"comment\":\"0\",\"position\":\"5\",\"tid\":\"121\",\"fid\":\"1\",\"dateline_show\":\"2016-03-29 16:08:18\",\"user_info\":{\"id\":\"31\",\"username\":\"M12345678977\",\"nickname\":\"123566\",\"head\":\"948\",\"group\":{\"adminid\":\"2\",\"alloweditpost\":\"1\",\"allowstickthread\":\"1\",\"allowdelpost\":\"1\",\"allowbanuser\":\"1\",\"allowdigestthread\":\"1\",\"allowpost\":\"1\",\"name\":\"\u7248\u4e3b\",\"fid\":\"1\"}},\"img\":[]}]},\"notify_id\":\"1459300528\"}";
		System.out.println(s);
	 	new Json2Bean(s, "RootBean", new MyNameGenerator(), new MyJsonParser(),new MyBeanGenerator("com.test2")).execute();
	 	
	 	
	 	
	    System.out.println("------------------------------");
	    
	    
		List<List<List<String>>>list=new ArrayList<>();
		
		List<List<String>>li1=new ArrayList<List<String>>();
		li1.add(Arrays.asList("d","e","f"));
		li1.add(Arrays.asList("d1","e1","f1"));
		
		List<List<String>>li2=new ArrayList<List<String>>();
		li2.add(Arrays.asList("d","e","f"));
		li2.add(Arrays.asList("d2","e2","f2"));
		
		list.add(li1);
		list.add(li2);

		
		s=JSON.toJSONString(list);
		System.out.println(s);
	 	new Json2Bean(s, "RootBean", new MyNameGenerator(), new MyJsonParser(),new MyBeanGenerator("com.test3")).execute();
	 	
	 	System.out.println("------------------------------");
	 	
	 	 s="{\"post_message\":\"[:f002}[:f003}[:f003}[:f004}[:f004}\",\"intlist\":[1,2,3],\"str\":\"{}\"}";
	 	System.out.println(JsonFormat.format(s));
	 	new Json2Bean(s, "RootBean", new MyNameGenerator(), new MyJsonParser(),new MyBeanGenerator("com.test4")).execute();
	 	
	 	s="[[[{\"name\":\"xm1\",\"age\":19},{\"name\":\"[xm2\",\"age\":19},{\"name\":\"{xm3\",\"age\":19}],[{\"name\":\"[[xm4\",\"age\":19},{\"name\":\"{{xm5\",\"age\":19}]],[[{\"name\":\"xm6\",\"age\":19},{\"name\":\"xm7\",\"age\":19}],[{\"name\":\"xm8\",\"age\":19}]]]";
	 	System.out.println(JsonFormat.format(s));
	 	new Json2Bean(s, "RootBean", new MyNameGenerator(), new MyJsonParser(),new MyBeanGenerator("com.test5")).execute();
	 	
	 	
	 	
	 	s="{\"multyList\":[[[{\"name\":\"xm1\",\"age\":19}]]]}";
	 	System.out.println(JsonFormat.format(s));
	 	new Json2Bean(s, "RootBean", new MyNameGenerator(), new MyJsonParser(),new MyBeanGenerator("com.test6")).execute();
	 	
	 	
	 	
	}

	
}
