package com.bbs.service.view.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbs.model.sys.SysUserInfo;
import com.bbs.model.view.BBSPostCommend;
import com.bbs.model.view.BBSPosts;
import com.bbs.model.view.BBSReply;
import com.bbs.service.view.IMineService;
import com.ibm.framework.dal.client.IPaginationDalClient;
/**
 * 说明：个人中心
 *  登录注册
 *  信息编辑
 *  历史记录查询
 * @dateTime 2018年10月23日15:08:55
 * @author wch
 * */
@Service("mineService")
public class MineServiceImpl implements IMineService{

	@Autowired
    IPaginationDalClient dalClient;

	/**
	 * 说明 用户注册
	 * @author Administrator
	 * @dateTime 2018年10月23日15:09:06
	 * */
	@Override
	public void saveMineReg(SysUserInfo user) {
		 dalClient.persist(user);
	}

	/**
	 * 说明：BBS用户登录验证
	 * 
	 * */
	@Override
	public SysUserInfo querySysUserInfo(SysUserInfo user) {
		return dalClient.queryForObject("user.verifyBBSUser", user, SysUserInfo.class);
	}

	/**
	 * 说明：查询用户
	 * 
	 * */
	@Override
	public SysUserInfo querySysUserById(SysUserInfo bean) {
		return dalClient.find(SysUserInfo.class, bean);
	}

	/**
	 * 说明：查询发帖纪录
	 * 
	 * */
	@Override
	public List<BBSPosts> queryBBSPostsAll(SysUserInfo bean) {
		return dalClient.queryForList("bbsPosts.queryBBSPostsByCreateUserId", bean, BBSPosts.class);
	}
	/**
	 * 说明：查询回帖纪录
	 * 
	 * */
	@Override
	public List<BBSReply> queryBBSReplyAll(SysUserInfo bean) {
		return dalClient.queryForList("bbsReply.queryBBSReplyByCreateUserId", bean, BBSReply.class);
	}
	
	
	
}
