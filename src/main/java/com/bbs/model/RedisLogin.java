package com.bbs.model;

import java.io.Serializable;

public class RedisLogin implements Serializable{
	
	private static final long serialVersionUID = 8116817810829835862L;
	
	private String uid;
	private String token;//jwt生成的token信息
	private long refTime;//登录或刷新应用的时间

	public RedisLogin(){

	}

	public RedisLogin(String uid, String token, long refTime){

	this.uid = uid;

	this.token = token;

	this.refTime = refTime;

	}

	public String getUid() {

	return uid;

	}

	public void setUid(String uid) {

	this.uid = uid;

	}

	public String getToken() {

	return token;

	}

	public void setToken(String token) {

	this.token = token;

	}

	public long getRefTime() {

	return refTime;

	}

	public void setRefTime(long refTime) {

	this.refTime = refTime;

	}

	

}
