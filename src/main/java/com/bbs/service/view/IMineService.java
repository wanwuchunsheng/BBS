package com.bbs.service.view;

import java.util.List;

import com.bbs.model.sys.SysUserInfo;
import com.bbs.model.view.BBSIdeAuthentication;
import com.bbs.model.view.BBSMessage;
import com.bbs.model.view.BBSPosts;
import com.bbs.model.view.BBSReply;

public interface IMineService {

	String saveMineReg(SysUserInfo user);

	SysUserInfo querySysUserInfo(SysUserInfo user);

	SysUserInfo querySysUserById(SysUserInfo bean);

	List<BBSPosts> queryBBSPostsAll(SysUserInfo bean);

	List<BBSReply> queryBBSReplyAll(SysUserInfo bean);

	void updateUserPhotoPathById(SysUserInfo bbsSysUserInfo);

	void updateSysUser(SysUserInfo bean);

	List<BBSPosts> queryBBSPostsAllByCreateUserId(SysUserInfo bean);

	void delPosts(BBSPosts bean);

	int queryBBSMessageCount(SysUserInfo bbsUserInfo);

	List<BBSMessage> queryBBSMessageAll(SysUserInfo bbsUserInfo);

	void saveIdeAuthentication(BBSIdeAuthentication bia);

	List<BBSIdeAuthentication> queryBBSIdeAuthenAll(SysUserInfo sysUserInfo);

	void saveMessage(BBSMessage bean);

}
