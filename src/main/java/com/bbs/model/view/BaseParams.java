package com.bbs.model.view;

import java.util.ArrayList;
import java.util.List;


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
	private static List<BBSPostCommend> postCommend=new ArrayList<BBSPostCommend>();
	
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
	public static List<BBSPostCommend> getPostCommend() {
		return postCommend;
	}
	public static void setPostCommend(List<BBSPostCommend> postCommend) {
		BaseParams.postCommend = postCommend;
	}
	
	
	
}
