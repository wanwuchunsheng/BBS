package com.bbs.service.view.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbs.model.sys.SysUserInfo;
import com.bbs.model.view.BBSPostCollection;
import com.bbs.model.view.BBSPosts;
import com.bbs.model.view.BBSReply;
import com.bbs.model.view.BBSSignin;
import com.bbs.model.view.BBSSmallBoard;
import com.bbs.service.view.IContDetailService;
import com.ibm.framework.dal.client.IPaginationDalClient;
import com.ibm.framework.dal.pagination.Pagination;
import com.ibm.framework.dal.pagination.PaginationResult;
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
	public List<BBSPosts> queryBBSPostsAll(BBSPosts bean,Pagination pagination) {
		PaginationResult<List<BBSPosts>> result = dalClient.queryForList("bbsPosts.queryBBSPostsAllOrBbId", bean,BBSPosts.class, pagination);
        
		//List<BBSPosts> postsAll=dalClient.queryForList("bbsPosts.queryBBSPostsAllOrBbId", bean, BBSPosts.class);
		List<BBSPosts> postsAll=result.getR();
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
	 *   如果有悬赏，扣除用户悬赏分
	 * @author wch
	 * */
	@Override
	public void savePosts(final BBSPosts bean) {
		dalClient.getTransactionTemplate().execute(new CallBackTemplate<Integer>() {
			@Override
			public Integer invoke() {
				dalClient.persist(bean);
				if(bean.getContReward()>0){
					dalClient.execute("user.updateSysUserByExpPoints", bean);
				}
				return null;
			}
		});
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
	public List<BBSReply> queryBBSReplyAll(BBSPosts bean,Pagination pagination) {
        PaginationResult<List<BBSReply>> result = dalClient.queryForList("bbsReply.queryBBSReplyAll", bean,BBSReply.class, pagination);
        
		//List<BBSReply> replyAll=dalClient.queryForList("bbsReply.queryBBSReplyAll", posts, BBSReply.class);
		List<BBSReply> replyAll=result.getR();
		return replyAll;
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

	/**
	 * 说明：帖子详细-回帖留言查询
	 * @author Administrator
	 * 
	 * */
	@Override
	public List<BBSReply> queryBBSReplyByReplyId(BBSReply bean) {
		// TODO Auto-generated method stub
		return dalClient.queryForList("bbsReply.queryBBSReplyByReplyId", bean, BBSReply.class);
	}

	/**
	 * 说明：帖子详细-回帖留言查询
	 * 1、通过ID查询最新对象
	 * 2、判断type  1：赞  2：反对
	 * @author Administrator
	 * 
	 * */
	@Override
	public void updatePostsByCont(BBSPosts bean, Integer type) {
		BBSPosts posts=dalClient.find(BBSPosts.class,bean);
		BBSPosts ps=new BBSPosts();
		ps.setId(posts.getId());
		if(type==1){
			ps.setContGood(posts.getContGood()+1);
		}else if(type==2){
			ps.setContBad(posts.getContBad()+1);
		}
		dalClient.dynamicMerge(ps);
	}

	/**
	 * 说明：用户收藏
	 * 1:通过帖子ID ,查询数据
	 * 2:再将数据复制收藏表
	 * 
	 * */
	@Override
	public boolean addBBSPostCollection(SysUserInfo bbsUserInfo, BBSPosts bean) {
		BBSPosts posts=dalClient.find(BBSPosts.class,bean);
		BBSPostCollection col=new BBSPostCollection();
		col.setCreateDate(new Date());
		col.setCreateUserId(bbsUserInfo.getId());
		col.setCreateUserName(bbsUserInfo.getUname());
		col.setBbId(posts.getBbId());
		col.setPostsId(posts.getId());
		col.setSmboId(posts.getSmboId());
		//插入之前查询下是否已被当前登录人收藏
		BBSPostCollection bpc=dalClient.queryForObject("coll.queryCollectionByCreateUserId", col, BBSPostCollection.class);
		if(bpc==null){
			dalClient.persist(col);
			return true;
		}
		return false;
	}

}
