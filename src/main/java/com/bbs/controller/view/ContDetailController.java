package com.bbs.controller.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbs.model.sys.SysUserInfo;
import com.bbs.model.view.BBSPosts;
import com.bbs.model.view.BBSReply;
import com.bbs.model.view.BBSSignin;
import com.bbs.model.view.BBSSmallBoard;
import com.bbs.model.view.BaseParams;
import com.bbs.service.common.Constants;
import com.bbs.service.view.IContDetailService;
import com.ibm.framework.dal.pagination.Pagination;

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
	public String gotoContIndex(HttpSession session, HttpServletRequest request,BBSPosts bean,Pagination pagination){
		//内容列表数据
		List<BBSPosts> postsAll=contDetailService.queryBBSPostsAll(bean,pagination);
		request.setAttribute("postsAll", postsAll);
		//当前位置信息数据
		if(null==bean.getSmboId()){
			request.setAttribute("bbsSmallBoard", null);
		}else{
			//通过快照取值，减少查询数据库
			for(BBSSmallBoard b:BaseParams.getSmallBoard()){
				if(bean.getSmboId().equals(b.getId())){
					request.setAttribute("bbsSmallBoard", b);
					break;
				}
			}
		}
		request.setAttribute("typ", bean.getTyp());//0-全部 
		request.setAttribute("postCommendAll", BaseParams.getPostCommendMap().get("C001"));
		request.setAttribute("pageObj", pagination);
		return "/web_view/cont/cont_index";
	}
	
	/**
	 * 说明：综合区懒加载
	 * 
	 * */
	@RequestMapping("indexPage")
	@ResponseBody
	public String gotoContIndexPage(HttpSession session, HttpServletRequest request,BBSPosts bean,Pagination pagination){
		//内容列表数据
		List<BBSPosts> postsAll=contDetailService.queryBBSPostsAll(bean,pagination);
		if(postsAll!=null && postsAll.size()>0){
			JSONArray jsonArray2 = JSONArray.fromObject(postsAll);//将集合转换为json格式
			String jsonString=jsonArray2.toString();//将jisn转换为字符串
			return jsonString;
		}
		return "2";//失败
	}
	
	
	
	
	/**
	 * 说明：预览详细
	 *   1/通过ID查询对象内容
	 *   2/查询该对象所有回帖
	 * @author Administrator
	 * @createTime 2018年6月11日22:48:33
	 * */
	@RequestMapping("detail")
	public String gotoContIndex(HttpSession session, HttpServletRequest request,BBSPosts bean){
		BBSPosts posts=contDetailService.queryPostsObjById(bean);
		request.setAttribute("postsObj", posts);
		request.setAttribute("replyAll",contDetailService.queryBBSReplyAll(posts) );
		return "/web_view/cont/cont_detail";
	}
	
	/**
	 * 说明：跳转发帖页
	 *   进入拦截器，未登录跳转登录页面
	 * @author Administrator
	 * @createTime 2018年6月11日22:48:33
	 * */
	@RequestMapping("vf/add")
	public String gotoContAddPage(HttpServletRequest request,BBSSmallBoard bean){
		//获取参数数据
		request.setAttribute("bigBoardAll", BaseParams.getBigBoard());//父版块
		request.setAttribute("smallBoardALL", BaseParams.getSmallBoard());//子版块
		request.setAttribute("postCommend", BaseParams.getPostCommendMap().get("C001"));//基础类型数据
		request.setAttribute("smallBoard", bean);//首页类别或综合区
		return "/web_view/cont/cont_add";
	}
	
	/**
	 * 说明：添加增帖子
	 *   拦截器-验证是否登录
	 *   保存完成后， 如果有悬赏，扣除用户悬赏分
	 *   重定向综合区
	 *   每发帖子一次，扣除5个经验值
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
			bean.setCreateDate(new Date());
			bean.setUpdateDate(bean.getCreateDate());
			bean.setContPreview(0);//预览0
			bean.setContReply(0);//回复0
			bean.setContTop(0);//置顶0
			bean.setContRec(0);//
			bean.setContGood(0);//点赞0
			bean.setContBad(0);//反对0
			bean.setContStatus(0);//默认状态0  1-完结
			bean.setDel(0);//默认0-显示  1-禁用 
			bean.setExpPoints(bean.getContReward()+Constants.DED_EXP_POINTS);//提交的经验值+默认发帖扣5经验值
			contDetailService.savePosts(bean);
			//更新session 经验值
			bbsUserInfo.setExpPoints( bbsUserInfo.getExpPoints()-bean.getExpPoints() );
			session.setAttribute("bbsUserInfo", bbsUserInfo);
			//重定向
			PrintWriter out = response.getWriter();
			out.println("<html>");    
		    out.println("<script>");    
		    out.println("window.open ('"+request.getContextPath()+"/cont/index','_top')");    
		    out.println("</script>");    
		    out.println("</html>");  
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	}
	
	/**
	 * 说明：回帖
	 *   拦截器-验证是否登录
	 *   增加事物处理
	 *   保存完成后,更新主贴回复数1
	 * @author Administrator
	 * @createTime 2018年6月11日22:48:33
	 * */
	@RequestMapping("vf/addBbsReply")
	@ResponseBody
	public String addBbsReply(HttpSession session, HttpServletResponse response,HttpServletRequest request,BBSReply bean){
		try {
			SysUserInfo bbsUserInfo=(SysUserInfo) session.getAttribute("bbsUserInfo");
			bean.setCreateUserId(bbsUserInfo.getId());
			bean.setCreateUserName(bbsUserInfo.getUname());
			bean.setCreateDate(new Date());
			bean.setContGood(0);//点赞0
			bean.setContBad(0);//反对0
			bean.setDel(0);//默认0-显示  1-禁用 
			contDetailService.addBbsReply(bean);//保存 
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return "false";
	}
	
	/**
	 * 说明：签到
	 *   拦截器-验证是否登录
	 *   每个签到给用户新增3个经验值
	 *   向签到表新增签到记录
	 *   修改session ischeck数据1-已签到
	 * @author Administrator
	 * @createTime 2018年6月11日22:48:33
	 * */
	@RequestMapping("vf/addsignin")
	public void addsignin(HttpSession session, HttpServletResponse response,HttpServletRequest request,  BBSSignin bean){
		try {
			SysUserInfo bbsUserInfo=(SysUserInfo) session.getAttribute("bbsUserInfo");
			bean.setCreateUserId(bbsUserInfo.getId());
			bean.setCreateUserName(bbsUserInfo.getUname());
			bean.setCreateDate(new Date());
			bean.setExpPoints(Constants.EXP_POINTS);
			contDetailService.addsignin(bean);
			//修改session
			bbsUserInfo.setIsCheck(Constants.IS_CHECK);
			session.setAttribute("bbsUserInfo", bbsUserInfo);
			//重定向综合区
			PrintWriter out = response.getWriter();
			out.println("<html>");    
		    out.println("<script>");    
		    out.println("window.open ('"+request.getContextPath()+"/cont/index','_top')");    
		    out.println("</script>");    
		    out.println("</html>");  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
