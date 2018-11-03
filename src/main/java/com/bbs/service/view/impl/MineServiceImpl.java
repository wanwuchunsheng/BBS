package com.bbs.service.view.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbs.model.sys.SysUserInfo;
import com.bbs.model.view.BBSIdeAuthentication;
import com.bbs.model.view.BBSMessage;
import com.bbs.model.view.BBSPostCommend;
import com.bbs.model.view.BBSPosts;
import com.bbs.model.view.BBSReply;
import com.bbs.service.view.IMineService;
import com.ibm.framework.dal.client.IPaginationDalClient;
import com.ibm.framework.dal.transaction.template.CallBackTemplate;
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
	public String saveMineReg(SysUserInfo user) {
		 SysUserInfo userinfo=dalClient.queryForObject("user.querySysUserByEmail", user, SysUserInfo.class);
		 if(userinfo!=null){
			 return "2";
		 }else{
			 dalClient.persist(user);
			 return "1";
		 }
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

	/**
	 * 修改用户图片路劲
	 * 
	 * */
	@Override
	public void updateUserPhotoPathById(SysUserInfo bbsSysUserInfo) {
		dalClient.execute("user.updateUserPhotoPathById", bbsSysUserInfo);
	}

	/**
	 * 修改用户信息
	 * 
	 * */
	@Override
	public void updateSysUser(SysUserInfo bean) {
		dalClient.dynamicMerge(bean);
	}

	/**
	 * 说明：查询个人中心-发帖纪录
	 * 
	 * */
	@Override
	public List<BBSPosts> queryBBSPostsAllByCreateUserId(SysUserInfo bean) {
		return dalClient.queryForList("bbsPosts.queryBBSPostsAllByCreateUserId", bean, BBSPosts.class);
	}

	/**
	 * 说明：个人中心-删除发帖记录
	 * 
	 * */
	@Override
	public void delPosts(final BBSPosts bean) {
		dalClient.getTransactionTemplate().execute(new CallBackTemplate<Integer>() {
			@Override
			public Integer invoke() {
				dalClient.dynamicMerge(bean);
				dalClient.execute("bbsReply.delReply",bean);
				return null;
			}
		});
	}

	/**
	 * 说明：查询消息纪录
	 * 
	 * */
	@Override
	public int queryBBSMessageCount(SysUserInfo bean) {
		List<BBSMessage> cot=dalClient.queryForList("message.queryBBSMessageCount", bean,  BBSMessage.class);
		if(cot!=null && cot.size()>0){
			return cot.size();
		}
		return 0;
	}

	/**
	 * 说明：查询所有消息
	 * 
	 * */
	@Override
	public List<BBSMessage> queryBBSMessageAll(SysUserInfo bean) {
		// TODO Auto-generated method stub
		return dalClient.queryForList("message.queryBBSMessageAll", bean,BBSMessage.class);
	}

	/**
	 * 说明：保存身份证
	 * 
	 * */
	@Override
	public void saveIdeAuthentication(BBSIdeAuthentication bia) {
		dalClient.persist(bia);
	}

	/**
	 * 说明：查询身份认证信息
	 * 
	 * */
	@Override
	public List<BBSIdeAuthentication> queryBBSIdeAuthenAll(
			SysUserInfo sysUserInfo) {
		// TODO Auto-generated method stub
		return dalClient.queryForList("message.queryBBSIdeAuthenAll", sysUserInfo,BBSIdeAuthentication.class);
	}

	/***
	 * bug反馈留言
	 * 
	 * */
	@Override
	public void saveMessage(BBSMessage bean) {
		dalClient.persist(bean);	
	}
	
	
	
}
