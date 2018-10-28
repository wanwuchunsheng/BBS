package com.bbs.service.view.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbs.model.view.BBSPosts;
import com.bbs.model.view.BBSReply;
import com.bbs.model.view.BBSSignin;
import com.bbs.model.view.BBSSmallBoard;
import com.bbs.service.view.IContDetailService;
import com.ibm.framework.dal.client.IPaginationDalClient;
import com.ibm.framework.dal.transaction.template.CallBackTemplate;
/**
 * 说明：跳转详细页面
 * @dateTime 2018年10月22日16:48:15
 * @author wch
 * */
@Service("contDetailService")
public class ContDetailServiceImpl implements IContDetailService{
	
	@Autowired
    IPaginationDalClient dalClient;
	
	/**
	 * 说明：查看帖子详细
	 *   1:修改预览量
	 *   2：查询对象
	 * */
	@Override
	public BBSPosts queryPostsObjById(BBSPosts bean) {
		dalClient.execute("bbsPosts.updateBBSPostsById", bean);//更新预览量
		return dalClient.queryForObject("bbsPosts.queryBBSPostsObjById", bean, BBSPosts.class);
	}

	/**
	 * 说明：综合
	 * //1,当有bbId时，查询bbId子版块 
		//2,当bbId为空时，查询所有综合
	 * */
	@Override
	public List<BBSPosts> queryBBSPostsAll(BBSPosts bean) {
		List<BBSPosts> postsAll=dalClient.queryForList("bbsPosts.queryBBSPostsAllOrBbId", bean, BBSPosts.class);
		return postsAll;
	}

	/**
	 * 说明：子模块-通过ID查询对象
	 * 
	 * */
	@Override
	public BBSSmallBoard queryBBSSmallBoardById(BBSPosts bean) {
		return dalClient.queryForObject("bbsSmallBoard.queryBBSSmallBoardById", bean, BBSSmallBoard.class);
	}
	
	/**
	 * 说明：添增新帖
	 * @author wch
	 * */
	@Override
	public void savePosts(BBSPosts bean) {
		dalClient.persist(bean);
	}

	/**
	 * 说明：保存回帖
	 *   事物
	 * @author wch
	 * */
	@Override
	public void addBbsReply(final BBSReply bean) {
		dalClient.getTransactionTemplate().execute(new CallBackTemplate<Integer>() {
			@Override
			public Integer invoke() {
				//保存
        		dalClient.persist(bean);
        		//更新帖子回复数量及最后修改时间
        		dalClient.execute("bbsPosts.updateBBSPostsByContReply", bean);
				return null;
			}
		});
	}

	/**
	 * 说明：查询回帖
	 * @author wch
	 * */
	@Override
	public List<BBSReply> queryBBSReplyAll(BBSPosts posts) {
		// TODO Auto-generated method stub
		return dalClient.queryForList("bbsReply.queryBBSReplyAll", posts, BBSReply.class);
	}

	/**
	 * 说明：签到
	 * @createTime 2018年10月28日20:11:41
	 * */
	@Override
	public void addsignin(final BBSSignin bean) {
		dalClient.getTransactionTemplate().execute(new CallBackTemplate<Integer>() {
			@Override
			public Integer invoke() {
				//保存
				dalClient.persist(bean);
        		//修改用户表用户经验值
        		dalClient.execute("user.updateSysUserById", bean);
				return null;
			}
		});
		
		
	}

}
