package com.bbs.controller.view;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bbs.model.view.BBSPosts;
import com.bbs.model.view.BBSSmallBoard;
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
	 * 说明：进入子版块-查询所有帖子信息
	 *  带分页
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
	 * 说明：通过发帖ID，查询相关对象
	 *   1/修改预览记录
	 * @author Administrator
	 * @createTime 2018年6月11日22:48:33
	 * */
	@RequestMapping("detail")
	public String gotoContIndex(HttpSession session, HttpServletRequest request,BBSPosts bean){
		BBSPosts posts=contDetailService.queryPostsObjById(bean);
		request.setAttribute("postsObj", posts);
		/*BBSSmallBoard smallBoard=new BBSSmallBoard();
		smallBoard.setDel(bean.getSmboId());
		request.setAttribute("bbsSmallBoard", contDetailService.queryBBSSmallBoardById(smallBoard));
		*/return "/web_view/cont/cont_detail";
	}

}
