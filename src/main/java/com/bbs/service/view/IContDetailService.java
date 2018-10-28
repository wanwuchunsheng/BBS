package com.bbs.service.view;

import java.util.List;

import com.bbs.model.view.BBSPosts;
import com.bbs.model.view.BBSReply;
import com.bbs.model.view.BBSSignin;
import com.bbs.model.view.BBSSmallBoard;

public interface IContDetailService {

	BBSPosts queryPostsObjById(BBSPosts bean);

	List<BBSPosts> queryBBSPostsAll(BBSPosts bean);

	BBSSmallBoard queryBBSSmallBoardById(BBSPosts bean);

	void savePosts(BBSPosts bean);

	void addBbsReply(BBSReply bean);

	List<BBSReply> queryBBSReplyAll(BBSPosts posts);

	void addsignin(BBSSignin bean);


}
