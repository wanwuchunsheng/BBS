package com.bbs.service.view.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbs.model.view.BBSBigBoard;
import com.bbs.model.view.BBSSmallBoard;
import com.bbs.service.view.IIndexService;
import com.ibm.framework.dal.client.IPaginationDalClient;
/**
 * 说明：BBS前端主页面
 * @dateTime 2018年10月21日22:27:50
 * @author wch
 * */
@Service("indexService")
public class IndexServiceImpl implements IIndexService{
	
	@Autowired
    IPaginationDalClient dalClient;

	/**
	 * 说明：查询主页-父版块
	 * @dateTime 2018年10月21日22:30:52
	 * @author wch
	 * */
	@Override
	public List<BBSBigBoard> queryBigBoardAll() {
		return dalClient.queryForList("bbsBigBoard.queryBBSBigBoardAll", null, BBSBigBoard.class);
	}

	/**
	 * 说明：查询主页-子版块
	 * @dateTime 2018年10月21日22:30:52
	 * @author wch
	 * */
	@Override
	public List<BBSSmallBoard> querySmallBoardAll() {
		return dalClient.queryForList("bbsSmallBoard.queryBBSSmallBoardAll", null, BBSSmallBoard.class);
	}
    
}
