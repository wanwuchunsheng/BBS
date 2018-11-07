package com.bbs.controller.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbs.model.sys.SysUserInfo;
import com.bbs.model.view.BBSBigBoard;
import com.bbs.model.view.BBSMessage;
import com.bbs.model.view.BBSPosts;
import com.bbs.model.view.BBSSmallBoard;
import com.bbs.model.view.BaseParams;
import com.bbs.service.view.IIndexService;
import com.bbs.service.view.IInitService;
import com.bbs.service.view.IMineService;

/**
 * web_view:前端首页
 * 
 * */
@Controller
@RequestMapping("/view")
public class IndexController {
	
	@Autowired
	IIndexService indexService;
	
	@Autowired
	IMineService mineService;
	
	@Autowired
	IInitService initService;//测试刷新缓存
	
	
	
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
		List<BBSPosts> spostsAll=indexService.queryPostsAll();
		//查询站内新闻
		List<BBSPosts> spostsNews=BaseParams.getPosts();
		//基础数据
		request.setAttribute("postCommendAll", BaseParams.getPostCommendMap().get("C001"));
		request.setAttribute("bigBoardAll", bigBoardAll);
		request.setAttribute("smallBoardAll", smallBoardAll);
		request.setAttribute("spostsAll", spostsAll);
		request.setAttribute("spostsNews", spostsNews);
		
		return "/web_view/index";
	}

	/**
	 * 说明：index页面
	 * @author Administrator
	 * @createTime 2018年6月11日22:48:33
	 * */
	@RequestMapping("mes")
	@ResponseBody
	public String message(HttpSession session, HttpServletRequest request,BBSMessage bean){
		try {
			SysUserInfo bbsUserInfo=(SysUserInfo) session.getAttribute("bbsUserInfo");
			if(bbsUserInfo!=null){
				bean.setTyp(0);
				bean.setStatus(0);
				bean.setRecipientId(36);//默认系统管理员
				bean.setDel(0);
				bean.setCreateUserId(bbsUserInfo.getId());
				bean.setCreateDate(new Date());
				bean.setCreateUserName(bbsUserInfo.getUname());
				mineService.saveMessage(bean);
				return "1";
			}
			return "2";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "3";
	}
	
	
	/**
	 * 说明：index页面
	 * @author Administrator
	 * @createTime 2018年6月11日22:48:33
	 * */
	@RequestMapping("refCache")
	@ResponseBody
	public void refCache(HttpServletRequest request, HttpServletResponse response,BBSMessage bean){
		initService.queryBBSBigBoardAll();// 查询父版块
		initService.queryBBSSmallBoardAll();//查询子版块
		initService.queryPostCommend();//查询基础数据
		initService.queryPostsNews();//查询站内新闻
		//失败重定向
	    PrintWriter out;
		try {
			out = response.getWriter();
			out.println("<html>");    
		    out.println("<script>");    
		    out.println("window.open ('"+request.getContextPath()+"/mine/login','_top')");    
		    out.println("</script>");    
		    out.println("</html>"); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
