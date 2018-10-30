package com.bbs.controller.view;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bbs.controller.common.tools.StringUtil;
import com.bbs.model.view.BBSBigBoard;
import com.bbs.model.view.BBSPosts;
import com.bbs.model.view.BBSSmallBoard;
import com.bbs.model.view.BaseParams;
import com.bbs.service.view.IIndexService;
import com.ibm.framework.uts.util.StringUtils;

/**
 * web_view:前端首页
 * 
 * */
@Controller
@RequestMapping("/view")
public class IndexController {
	
	@Autowired
	IIndexService indexService;
	
	/**
	 * 说明：index页面
	 * @author Administrator
	 * @createTime 2018年6月11日22:48:33
	 * */
	@RequestMapping("index")
	public String gotoIndexView(HttpSession session, HttpServletRequest request){
		//查询父版块
		List<BBSBigBoard> bigBoardAll= BaseParams.getBigBoard();//indexService.queryBigBoardAll();
		//查询子模块
		List<BBSSmallBoard> smallBoardAll= BaseParams.getSmallBoard();//indexService.querySmallBoardAll();
		//查询站内推荐
		List<BBSPosts> spostsAll=BaseParams.getPosts();// indexService.queryPostsAll();
		//查询站内新闻
		List<BBSPosts> spostsNews=indexService.queryPostsNews();
		//基础数据
		request.setAttribute("postCommendAll", BaseParams.getPostCommendMap().get("C001"));
		request.setAttribute("bigBoardAll", bigBoardAll);
		request.setAttribute("smallBoardAll", smallBoardAll);
		request.setAttribute("spostsAll", spostsAll);
		request.setAttribute("spostsNews", spostsNews);
		
		return "/web_view/index";
	}


	
	
}
