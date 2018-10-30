package com.bbs.service.view.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbs.model.view.BBSBigBoard;
import com.bbs.model.view.BBSPostCommend;
import com.bbs.model.view.BBSPosts;
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
	 *  分组查询
	 *  通过不同分组，查询对象集合
	 *  分组保存MAP
	 * @author Administrator
	 * @dateTime 2018年10月23日15:09:06
	 * */
	@Override
	public void queryPostCommend() {
		Map<String,List<BBSPostCommend>> map=new HashMap<String, List<BBSPostCommend>>();
		List<BBSPostCommend> list=null;
		//分组
		List<BBSPostCommend> cd_list=dalClient.queryForList("bbsPostCommend.queryBBSPostCommendGroupByTyp", null, BBSPostCommend.class);
		if(cd_list!=null && cd_list.size()>0){
			List<BBSPostCommend> postCommend= dalClient.queryForList("bbsPostCommend.queryBbsPostCommendAll", null, BBSPostCommend.class);
			for(BBSPostCommend bd:cd_list){
				list=new ArrayList<BBSPostCommend>();
				for(BBSPostCommend b:postCommend){
					if(bd.getTyp().equals( b.getTyp() ) ){
						list.add(b);
					}
				}
				map.put(bd.getTyp(), list);
			 }
		 }
		 BaseParams.setPostCommendMap(map);
	}

	/**
	 * 说明：查询首页站内新闻
	 * 
	 * */
	@Override
	public void queryPostsNews() {
		List<BBSPosts> list= dalClient.queryForList("bbsPosts.queryBBSPostsByTyp", null, BBSPosts.class);
		BaseParams.setPosts(list);
	}
	
	
	
	
	
	
}
