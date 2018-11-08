package com.bbs.controller.view;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbs.controller.common.tools.EncrypMD5;
import com.bbs.controller.common.tools.StringUtil;
import com.bbs.model.sys.SysUserInfo;
import com.bbs.model.view.BBSIdeAuthentication;
import com.bbs.model.view.BBSMessage;
import com.bbs.model.view.BBSPosts;
import com.bbs.model.view.BBSReply;
import com.bbs.model.view.BaseParams;
import com.bbs.service.common.Constants;
import com.bbs.service.view.IMineService;

/**
 * 说明：个人中心
 *   部分功能进入拦截器
 * 
 * */
@Controller
@RequestMapping("/mine")
public class MineGlController {
	
	@Autowired
	IMineService mineService;
	
	/**
	 * 说明：我的
	 *  手机端：点击我的，验证是否登录
	 *  跳转登录页面，否则跳转个人中心页
	 * @author Administrator
	 * @createTime 2018年10月23日13:59:59
	 * */
	@RequestMapping("index")
	public String gotoMineHome(HttpSession session,HttpServletRequest request){
		SysUserInfo bbsUserInfo=(SysUserInfo) session.getAttribute("bbsUserInfo");
		if(bbsUserInfo!=null){
			request.setAttribute("messageCount", mineService.queryBBSMessageCount(bbsUserInfo));
			return "/web_view/mine/mine_home";
		}
		return "/web_view/mine/mine_login";
	}
	
	/**
	 * 说明：跳转注册页 
	 * @author Administrator
	 * @createTime 2018年10月23日13:59:59
	 * */
	@RequestMapping("reg")
	public String gotoMineReg(){
		return "/web_view/mine/mine_reg";
	}
	
	
	/**
	 * 说明：保存注册信息
	 * @return 1-注册成功  2-邮箱已被注册  3-注册失败
	 * @author Administrator
	 * @createTime 2018年10月23日13:59:59
	 * */
	@RequestMapping("saveReg")
	@ResponseBody
	public String saveMineReg(HttpServletRequest request, SysUserInfo user){
		try {
			//默认设置
			user.setPhotoPath(Constants.COMMON_ICON);//默认头像
			user.setDel(0);
			user.setUsrStatus(0);
			user.setCreateUserId(0);
			user.setUpdateUserId(0);
			user.setExpPoints(5);
			user.setSecurityLevel(1);//0-管理员 1- 普通注册 2-官网认证  3-业主
			user.setDpnum(5);//个人基本资料默认5次修改机会
			user.setGender(2);//默认性别未知
			user.setNiceName(user.getUname());//默认昵称和用户名一致， 后期可在个人中心编辑修改
			Date now = new Date();
			user.setCreateTime(now);
			user.setUpdateTime(now);
			//随机生成邀请码
			user.setEno(UUID.randomUUID()+StringUtil.getItemId(4));
			//密码加密
			user.setUpwd(EncrypMD5.SHA256(user.getUpwd()));
			return mineService.saveMineReg(user); //添加成功后，返回对象
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "3";
	}
	
	/**
	 * 说明：跳转登录页
	 *   
	 * @author Administrator
	 * @createTime 2018年10月23日13:59:59
	 * */
	@RequestMapping("login")
	public String gotoMineLogin(HttpSession session,SysUserInfo user){
		return "/web_view/mine/mine_login";
	}
	
	/**
	 * 说明：用户登录
	 *   登录成功，跳转个人中心
	 * @author Administrator
	 * @createTime 2018年10月23日13:59:59
	 * */
	@RequestMapping("vfLogin")
	@ResponseBody
	public String verifyLoginUser(HttpSession session,SysUserInfo user){
		try {
			//加密查询 
			user.setUpwd(EncrypMD5.SHA256(user.getUpwd()));
			SysUserInfo bbsUserInfo=mineService.querySysUserInfo(user);
			if(bbsUserInfo!=null){
				//存储BBS登录信息
				session.setAttribute("bbsUserInfo", bbsUserInfo);
				return "true";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "false";
		
	}
	
	/**
	 * 说明：个人中心
	 * @author Administrator
	 * @createTime 2018年10月23日13:59:59
	 * */
	@RequestMapping("vf/home")
	public String gotoMineHome(HttpServletRequest request,HttpSession session){
		SysUserInfo bbsUserInfo=(SysUserInfo) session.getAttribute("bbsUserInfo");
		//查询系统消息
		request.setAttribute("messageCount", mineService.queryBBSMessageCount(bbsUserInfo));
		return "/web_view/mine/mine_home";
	}
	
	/**
	 * 说明：注销登录
	 * @author Administrator
	 * @createTime 2018年10月23日13:59:59
	 * */
	@RequestMapping("logout")
	public String saveMineLogout(HttpSession session){
		//默认设置
		session.removeAttribute("bbsUserInfo");
		return "/web_view/mine/mine_login";
	}
	
	/**
	 * 说明：查看个人信息
	 *   通过用户ID，查询用户表
	 *   通过用户ID，查询发帖纪录列表
	 *   通过用户ID，查询最近回帖
	 * @author Administrator
	 * @createTime 2018年10月23日13:59:59
	 * */
	@RequestMapping("preview")
	public String previewMineIndex(HttpSession session,HttpServletRequest request, SysUserInfo bean){
		//查询用户
		request.setAttribute("bbsUser", mineService.querySysUserById(bean));
		//查询用户发帖纪录
		List<BBSPosts> bbsPosts=mineService.queryBBSPostsAll(bean);
		request.setAttribute("bbsPosts", bbsPosts);
		//查询用户回帖纪录
		List<BBSReply> bbspcommend=mineService.queryBBSReplyAll(bean);
		request.setAttribute("bbspcommend", bbspcommend);
		return "/web_view/mine/mine_index";
	}


	
	/**
	 * 说明：我的消息
	 * @author Administrator
	 * @createTime 2018年6月11日22:48:33
	 * */
	@RequestMapping("vf/message")
	public String gotoContMessage(HttpSession session, HttpServletRequest request){
		SysUserInfo bbsUserInfo=(SysUserInfo) session.getAttribute("bbsUserInfo");
		List<BBSMessage> list=mineService.queryBBSMessageAll(bbsUserInfo);
		request.setAttribute("messageAll", list);
		request.setAttribute("commendAll", BaseParams.getPostCommendMap().get("SY001"));
		return "/web_view/mine/mine_message";
	}
	
	/**
	 * 说明：发帖记录
	 * @author Administrator
	 * @createTime 2018年6月11日22:48:33
	 * */
	@RequestMapping("vf/history")
	public String gotoContHistory(HttpSession session, HttpServletRequest request){
		//查询发帖
		SysUserInfo bean=(SysUserInfo) session.getAttribute("bbsUserInfo");
		request.setAttribute("bbsPostsAll", mineService.queryBBSPostsAllByCreateUserId(bean)); 
		return "/web_view/mine/mine_history";
	}
	
	/**
	 * 说明：重置
	 * @author Administrator
	 * @createTime 2018年6月11日22:48:33
	 * */
	@RequestMapping("vf/forget")
	public String gotoContForget(HttpSession session, HttpServletRequest request){
		
		return "/web_view/mine/mine_forget";
	}
	
	/**
	 * 说明：基础信息设置
	 * @author Administrator
	 * @createTime 2018年6月11日22:48:33
	 * */
	@RequestMapping("vf/set")
	public String gotoContSet(HttpSession session, HttpServletRequest request){
		//查询用户基本信息
		SysUserInfo bbsUserInfo=(SysUserInfo) session.getAttribute("bbsUserInfo");
		SysUserInfo sysUserInfo=mineService.querySysUserInfo(bbsUserInfo);//mineService.querySysUserById(bbsUserInfo);
		//查询用户认证信息
		List<BBSIdeAuthentication> ideAuthenAll=mineService.queryBBSIdeAuthenAll(sysUserInfo);
		request.setAttribute("ideAuthenAll", ideAuthenAll);
		session.setAttribute("bbsUserInfo", sysUserInfo);
		request.setAttribute("userInfoObj", sysUserInfo);
		return "/web_view/mine/mine_set";
	}
	
	/**
	 * 说明：修改基本资料
	 * @author Administrator
	 * @createTime 2018年6月11日22:48:33
	 * */
	@RequestMapping("vf/updateSysUser")
	@ResponseBody
	public String updateSysUser(HttpSession session, HttpServletRequest request,SysUserInfo bean){
		try {
			bean.setUpdateTime(new Date());
			bean.setDpnum(bean.getDpnum()-1);
			mineService.updateSysUser(bean);
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "false";
	}
	
	/**
	 * 说明：删除发帖纪录
	 *   修改帖子状态1
	 *   修改对应帖子回帖1
	 * @author Administrator
	 * @createTime 2018年6月11日22:48:33
	 * */
	@RequestMapping("vf/delPosts")
	public String delPosts(HttpSession session, HttpServletRequest request,BBSPosts bean){
		bean.setUpdateDate(new Date());
		bean.setDel(1);//1-禁用
		mineService.delPosts(bean);
		gotoContHistory(session, request);
		return "/web_view/mine/mine_history";
	}
	
	
	/**
	 * 说明：编辑帖子-跳转
	 *  
	 * @author Administrator
	 * @createTime 2018年6月11日22:48:33
	 * */
	@RequestMapping("vf/editPosts")
	public String editPosts(HttpSession session, HttpServletRequest request,BBSPosts bean){
		request.setAttribute("postsObj",mineService.queryBBSPostsObj(bean));
		return "/web_view/mine/mine_edit";
	}
	
	
	@RequestMapping("vf/saveEditPosts")
	public void saveEditPosts(HttpSession session,HttpServletResponse response, HttpServletRequest request,BBSPosts bean){
		try {
			bean.setUpdateDate(new Date());
			mineService.saveEditPosts(bean);
			//重定向
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
