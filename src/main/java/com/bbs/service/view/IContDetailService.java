package com.bbs.service.view;

import java.util.List;

import com.bbs.model.sys.SysUserInfo;
import com.bbs.model.view.BBSPosts;
import com.bbs.model.view.BBSReply;
import com.bbs.model.view.BBSSignin;
import com.bbs.model.view.BBSSmallBoard;
import com.ibm.framework.dal.pagination.Pagination;

public interface IContDetailService {

	BBSPosts queryPostsObjById(BBSPosts bean);

	List<BBSPosts> queryBBSPostsAll(BBSPosts bean,Pagination pagination);

	BBSSmallBoard queryBBSSmallBoardById(BBSPosts bean);

	void savePosts(BBSPosts bean);

	void addBbsReply(BBSReply bean);

	List<BBSReply> queryBBSReplyAll(BBSPosts posts,Pagination pagination);

	void addsignin(BBSSignin bean);

	List<BBSReply> queryBBSReplyByReplyId(BBSReply bean);

	void updatePostsByCont(BBSPosts bean, Integer type);

	boolean addBBSPostCollection(SysUserInfo bbsUserInfo, BBSPosts bean);

	//Object queryBBSReplyAll(BBSPosts posts, Pagination pagination);


}
