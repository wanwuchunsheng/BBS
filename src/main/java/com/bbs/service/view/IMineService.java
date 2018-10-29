package com.bbs.service.view;

import java.util.List;

import com.bbs.model.sys.SysUserInfo;
import com.bbs.model.view.BBSPosts;
import com.bbs.model.view.BBSReply;

public interface IMineService {

	void saveMineReg(SysUserInfo user);

	SysUserInfo querySysUserInfo(SysUserInfo user);

	SysUserInfo querySysUserById(SysUserInfo bean);

	List<BBSPosts> queryBBSPostsAll(SysUserInfo bean);

	List<BBSReply> queryBBSReplyAll(SysUserInfo bean);

}
