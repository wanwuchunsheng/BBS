package com.bbs.service.view.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbs.model.view.BBSBigBoard;
import com.bbs.model.view.BBSPostCommend;
import com.bbs.model.view.BBSSmallBoard;
import com.bbs.model.view.BaseParams;
import com.bbs.service.view.IInitService;
import com.ibm.framework.dal.client.IPaginationDalClient;
/**
 * 说明：系统启动，初始化数据
 * @dateTime 2018年10月21日22:27:50
 * @author wch
 * */
@Service("initService")
public class InitServiceImpl implements IInitService{
	
	@Autowired
    IPaginationDalClient dalClient;
	
	/**
	 * 说明:查找父模块
	 * @author Administrator
	 * @dateTime 2018年10月23日15:09:06
	 * */
	@Override
	public void queryBBSBigBoardAll() {
		List<BBSBigBoard> bigBoard=  dalClient.queryForList("bbsBigBoard.queryBBSBigBoardAll", null, BBSBigBoard.class);
	    BaseParams.setBigBoard(bigBoard);
	}

	/**
	 * 说明:查找子模块
	 * @author Administrator
	 * @dateTime 2018年10月23日15:09:06
	 * */
	@Override
	public void queryBBSSmallBoardAll() {
		List<BBSSmallBoard> smallBoard= dalClient.queryForList("bbsSmallBoard.queryBBSSmallBoardAll", null, BBSSmallBoard.class);
		BaseParams.setSmallBoard(smallBoard);
	}

	/**
	 * 说明:查找基础数据
	 * @author Administrator
	 * @dateTime 2018年10月23日15:09:06
	 * */
	@Override
	public void queryPostCommend() {
		List<BBSPostCommend> postCommend= dalClient.queryForList("bbsPostCommend.queryBbsPostCommendAll", null, BBSPostCommend.class);
		BaseParams.setPostCommend(postCommend);
	}
	
	
	
	
	
	
}
