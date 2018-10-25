package com.bbs.controller.view;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;

public class Demo {
	
	public static void main(String[] args) {
		 Map<String,String> map=new HashMap<String, String>();
		 Map<String,String> map2=new HashMap<String, String>();
      	 /*  {
		  "code": 0 ,"msg": ""  ,"data": {"src": "图片路径","title": "test"}
		} */
		 String url="http://www.baidu.com";
		 String path="{\"code\": 0 ,\"msg\": \"\"  ,\"data\": {\"src\": \""+url+"\",\"title\": \"test\"}}";
      	
      	 System.out.println(path.toString());
	}

}
