package com.bbs.service.view;

import java.util.List;

import com.bbs.model.view.BBSPosts;
import com.bbs.model.view.BBSSmallBoard;

public interface IContDetailService {

	BBSPosts queryPostsObjById(BBSPosts bean);

	List<BBSPosts> queryBBSPostsAll(BBSSmallBoard bean);

	BBSSmallBoard queryBBSSmallBoardById(BBSSmallBoard bean);


}
