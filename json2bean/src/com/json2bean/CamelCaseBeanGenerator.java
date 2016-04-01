package com.json2bean;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
/**
 * 驼峰式写法
 * @author Young
 *
 */
public class CamelCaseBeanGenerator extends MyBeanGenerator {

	public CamelCaseBeanGenerator(String packName) {
		super(packName);
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
			bw.write(entry.getValue().toString()); bw.write(" "); bw.write(formatReference(entry.getKey()));bw.write(";\n");
		}
		bw.write("\n");
		set=map.entrySet();
		
		for (Entry<String, Object> entry : set) {
		
			bw.write("    public "); bw.write(entry.getValue().toString());
			bw.write(" get"); bw.write(capitalUpper(entry.getKey()));
			bw.write("(){\n");
			bw.write("        ");
			bw.write("return ");
			bw.write( formatReference(entry.getKey()));
			
			bw.write(";\n    }\n\n");
			
			//////////////////////////
			
			bw.write("    public void ");  
			bw.write("set"); bw.write(capitalUpper(entry.getKey()));
			bw.write("("); bw.write(entry.getValue().toString()); bw.write(" ");
			bw.write(formatReference(entry.getKey()));
			bw.write("){\n");
			bw.write("        ");
			bw.write("this.");
			bw.write( formatReference(entry.getKey()));
			bw.write("=");
			bw.write(formatReference(entry.getKey()));
			bw.write(";\n    }\n");
			
			
			bw.write("\n");
			
			
		}
		bw.write("}");
		
		
		bw.close();
	}
	
	private String capitalUpper(String getset){
		String s=formatReference(getset);
		char[]chs=s.toCharArray();
		if (chs[0]>'a'&&chs[0]<'z') {
			chs[0]=(char) (chs[0]-32);
		}
		
		return new String(chs);
		
	}
	private String formatReference(String ref){
		
		char[]chs=ref.toCharArray();
		
		if (chs[0]>='A'&&chs[0]<='Z') {
			chs[0]=(char) (chs[0]+32);
		}
		StringBuilder stringBuilder=new StringBuilder();
		stringBuilder.append(chs[0]);
		for (int i = 1; i < chs.length; i++) {
			if (chs[i]=='_') {
				continue;
			}
			if (chs[i-1]=='_') {
				if (chs[i]>='a'&&chs[i]<='z') {
					stringBuilder.append((char)(chs[i]-32));
				}else{
					stringBuilder.append(chs[i]);
				}
			}else{
				stringBuilder.append(chs[i]);
			}
		}
		
		return stringBuilder.toString();
	}
}
