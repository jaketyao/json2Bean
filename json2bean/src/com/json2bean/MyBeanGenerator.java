package com.json2bean;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MyBeanGenerator implements BeanGenerator {

	String packName;
	public MyBeanGenerator(String packName) {
		// TODO Auto-generated constructor stub
		this.packName=packName;
	}

	@Override
	public void writeBean(String className, Map<String, Object> map) throws IOException {
		File file=new File("src/"+packName.replace(".", "/"));
		if (!file.exists()||file.exists()&&file.isFile()) {
			file.mkdirs();
		}
		BufferedWriter bw=new BufferedWriter(new FileWriter(new File(file,  className+".java")));
		bw.write("package ");
		bw.write(packName);
		bw.write(";\n");
		bw.write("import java.util.List;\n\n");
		
		bw.write("/**\n");
		bw.write(" *auto generate\n");
		bw.write(" *\n");
		bw.write(" *@author Young\n");
		bw.write(" *\n");
		bw.write(" */\n");
		
		bw.write("public class ");bw.write(className); bw.write("{\n");
		
		Set<Entry<String, Object>> set=map.entrySet();
		
		for (Entry<String, Object> entry : set) {
			bw.write("    ");
			bw.write(entry.getValue().toString()); bw.write(" "); bw.write(entry.getKey());bw.write(";\n");
		}
		bw.write("\n");
		set=map.entrySet();
		
		for (Entry<String, Object> entry : set) {
		
			bw.write("    public "); bw.write(entry.getValue().toString());
			bw.write(" get"); bw.write(capitalUpperCase(entry.getKey()));
			bw.write("(){\n");
			bw.write("        ");
			bw.write("return ");
			bw.write( entry.getKey());
			
			bw.write(";\n    }\n\n");
			
			//////////////////////////
			
			bw.write("    public void ");  
			bw.write("set"); bw.write(capitalUpperCase(entry.getKey()));
			bw.write("("); bw.write(entry.getValue().toString()); bw.write(" ");
			bw.write(entry.getKey());
			bw.write("){\n");
			bw.write("        ");
			bw.write("this.");
			bw.write( entry.getKey());
			bw.write("=");
			bw.write(entry.getKey());
			bw.write(";\n    }\n");
			
			
			bw.write("\n");
			
			
		}
		bw.write("}");
		
		
		bw.close();
	}

	private String capitalUpperCase(String s){
		char[]chs=s.toCharArray();
		if (chs[0]>='a'&&chs[0]<='z') {
			chs[0]=(char) (chs[0]-32);
		}
		return new String(chs);
		
	}
	@Override
	public void writeList(String list) throws IOException {
		
	 
		
		BufferedWriter bw=new BufferedWriter(new FileWriter("src/"+list.replaceAll("<|>", "_")+".txt"));
		
		 bw.write(list);
		 bw.write(";");
		
		bw.close();
		
	}

	 


	 

}
