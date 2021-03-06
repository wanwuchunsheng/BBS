package com.bbs.controller.common.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串工具类 <li>@author Leejean <li>@create 2014-6-24 下午04:13:19
 */
public class StringUtil {

	/**
	 * 获取字符串img src路径
	 * 
	 * */
	public static Set<String> getImgStr(String htmlStr) {
		Set<String> pics = new HashSet<>();
		String img = "";
		Pattern p_image;
		Matcher m_image;
		// String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址
		String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
		p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
		m_image = p_image.matcher(htmlStr);
		while (m_image.find()) {
			// 得到<img />数据
			img = m_image.group();
			// 匹配<img>中的src数据
			// http://zhangt.java.cdnjsp.org/images/editor/a7248cd8-c8ff-466b-838a-00d5ff31c28f.jpeg
			Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)")
					.matcher(img);
			while (m.find()) {
				pics.add(m.group(1));
			}
		}
		return pics;
	}

	/**
	 * 生成随机数当作getItemID n ： 需要的长度
	 * 
	 * @return
	 */
	public static String getItemId(int n) {
		String val = "";
		Random random = new Random();
		for (int i = 0; i < n; i++) {
			String str = random.nextInt(2) % 2 == 0 ? "num" : "char";
			if ("char".equalsIgnoreCase(str)) { // 产生字母
				int nextInt = random.nextInt(2) % 2 == 0 ? 65 : 97;
				// System.out.println(nextInt + "!!!!"); 1,0,1,1,1,0,0
				val += (char) (nextInt + random.nextInt(26));
			} else if ("num".equalsIgnoreCase(str)) { // 产生数字
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}
	
	/**
	 * 【功能说明】：获取客户端的IP
	 * 
	 * */
	public static String getClientIp(HttpServletRequest request) {
	        String ip = request.getHeader("X-Forwarded-For");
	        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
	            //多次反向代理后会有多个ip值，第一个ip才是真实ip
	            int index = ip.indexOf(",");
	            if(index != -1){
	                return ip.substring(0,index);
	            }else{
	                return ip;
	            }
	        }
	        ip = request.getHeader("X-Real-IP");
	        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
	            return ip;
	        }
	        return request.getRemoteAddr();
	 }
	
	

	/**
	 * 获取随机字符串 (采用截取8位当前日期数 + 4位随机整数)
	 * 
	 * @return
	 */
	public static String getNonceStr() {
		// 获得当前日期
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String currTime = outFormat.format(now);
		// 截取8位
		String strTime = currTime.substring(8, currTime.length());
		// 得到4位随机整数
		int num = 1;
		double random = Math.random();
		if (random < 0.1) {
			random = random + 0.1;
		}
		for (int i = 0; i < 4; i++) {
			num = num * 10;
		}
		num = (int) random * num;
		return strTime + num;
	}
	
	
	public static String getNowTimeString (String stl){
	   SimpleDateFormat df = new SimpleDateFormat(stl);//设置日期格式
	   return df.format(new Date());
	}
 
	
	
}
