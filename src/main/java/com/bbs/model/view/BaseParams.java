package com.bbs.model.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 内存变量
 * @author wch
 * 
 * */
public class BaseParams {

	//父版块
	private static List<BBSBigBoard> bigBoard=new ArrayList<BBSBigBoard>();
    //子版块
	private static List<BBSSmallBoard> smallBoard=new ArrayList<BBSSmallBoard>();
	//基础类型（基础数据）
	private static Map<String,List<BBSPostCommend>> postCommendMap=new HashMap<String, List<BBSPostCommend>>();
	//站内新闻
	private static List<BBSPosts> posts=new ArrayList<BBSPosts>();
	
	public static List<BBSBigBoard> getBigBoard() {
		return bigBoard;
	}
	public static void setBigBoard(List<BBSBigBoard> bigBoard) {
		BaseParams.bigBoard = bigBoard;
	}
	public static List<BBSSmallBoard> getSmallBoard() {
		return smallBoard;
	}
	public static void setSmallBoard(List<BBSSmallBoard> smallBoard) {
		BaseParams.smallBoard = smallBoard;
	}
	public static Map<String, List<BBSPostCommend>> getPostCommendMap() {
		return postCommendMap;
	}
	public static void setPostCommendMap(
			Map<String, List<BBSPostCommend>> postCommendMap) {
		BaseParams.postCommendMap = postCommendMap;
	}
	public static List<BBSPosts> getPosts() {
		return posts;
	}
	public static void setPosts(List<BBSPosts> posts) {
		BaseParams.posts = posts;
	}
	
	
}
