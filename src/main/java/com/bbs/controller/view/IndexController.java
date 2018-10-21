package com.bbs.controller.view;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bbs.model.view.BBSBigBoard;
import com.bbs.model.view.BBSSmallBoard;
import com.bbs.service.sys.ISysUserService;
import com.bbs.service.view.IIndexService;

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
	public String gotoQueryNavPage(HttpSession session, HttpServletRequest request){
		//查询父版块
		List<BBSBigBoard> bigBoardAll=indexService.queryBigBoardAll();
		//查询子模块
		List<BBSSmallBoard> smallBoard=indexService.querySmallBoardAll();
		request.setAttribute("bigBoardAll", bigBoardAll);
		request.setAttribute("smallBoard", smallBoard);
		return "/web_view/index";
	}


	
	
}
