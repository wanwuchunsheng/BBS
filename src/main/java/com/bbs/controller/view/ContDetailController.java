package com.bbs.controller.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bbs.model.sys.SysUserInfo;
import com.bbs.model.view.BBSPosts;
import com.bbs.model.view.BBSSmallBoard;
import com.bbs.model.view.BaseParams;
import com.bbs.service.view.IContDetailService;

/**
 * 说明：详细内容页面
 * 
 * */
@Controller
@RequestMapping("/cont")
public class ContDetailController {
	
	@Autowired
	IContDetailService contDetailService;
	
	/**
	 * 说明：进入版块
	 *   1/判断是否进入子版块
	 *   2/查询子版块对象
	 *   3/查询子版块所有帖子
	 * @author Administrator
	 * @createTime 2018年6月11日22:48:33
	 * */
	@RequestMapping("index")
	public String gotoBBSSmallBoard(HttpSession session, HttpServletRequest request,BBSSmallBoard bean){
		List<BBSPosts> postsAll=contDetailService.queryBBSPostsAll(bean);
		request.setAttribute("postsAll", postsAll);
		request.setAttribute("bbsSmallBoard", contDetailService.queryBBSSmallBoardById(bean));
		return "/web_view/cont/cont_index";
	}
	
	/**
	 * 说明：预览详细
	 *   1/通过ID查询对象内容
	 * @author Administrator
	 * @createTime 2018年6月11日22:48:33
	 * */
	@RequestMapping("detail")
	public String gotoContIndex(HttpSession session, HttpServletRequest request,BBSPosts bean){
		BBSPosts posts=contDetailService.queryPostsObjById(bean);
		request.setAttribute("postsObj", posts);
		return "/web_view/cont/cont_detail";
	}
	
	/**
	 * 说明：跳转发帖页
	 *   进入拦截器，未登录跳转登录页面
	 * @author Administrator
	 * @createTime 2018年6月11日22:48:33
	 * */
	@RequestMapping("vf/add")
	public String gotoContAddPage(HttpServletRequest request){
		//获取参数数据
		request.setAttribute("bigBoardAll", BaseParams.getBigBoard());//父版块
		request.setAttribute("smallBoardALL", BaseParams.getSmallBoard());//子版块
		request.setAttribute("postCommend", BaseParams.getPostCommend());//基础类型数据
		return "/web_view/cont/cont_add";
	}
	
	/**
	 * 说明：添加增帖子
	 *   保存完成后，重定向综合区
	 * @author Administrator
	 * @createTime 2018年6月11日22:48:33
	 * */
	@RequestMapping("vf/savePosts")
	public void savePosts(HttpSession session, HttpServletResponse response,HttpServletRequest request,BBSPosts bean){
		try {
			//保存
			SysUserInfo bbsUserInfo=(SysUserInfo) session.getAttribute("bbsUserInfo");
			bean.setCreateUserId(bbsUserInfo.getId());
			bean.setUpdateUserId(bbsUserInfo.getId());
			bean.setCreateUserName(bbsUserInfo.getUname());
			bean.setUpdateUserName(bbsUserInfo.getUname());
			contDetailService.savePosts(bean);
			//重定向
			PrintWriter out = response.getWriter();
			out.println("<html>");    
		    out.println("<script>");    
		    out.println("window.open ('"+request.getContextPath()+"/cont/index','_top')");    
		    out.println("</script>");    
		    out.println("</html>");  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}

}
