package com.bbs.controller.view;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbs.model.sys.SysUserInfo;
import com.bbs.model.view.BBSPostCommend;
import com.bbs.model.view.BBSPosts;
import com.bbs.model.view.BBSReply;
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
	public String gotoMineHome(HttpSession session){
		SysUserInfo bbsUserInfo=(SysUserInfo) session.getAttribute("bbsUserInfo");
		if(bbsUserInfo!=null){
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
	 * @author Administrator
	 * @createTime 2018年10月23日13:59:59
	 * */
	@RequestMapping("saveReg")
	public String saveMineReg(HttpServletRequest request, SysUserInfo user){
		//默认设置
		user.setPhotoPath("common.jpg");//默认头像
		user.setNiceName(user.getUname());//默认昵称和用户名一致， 后期可在个人中心编辑修改
		mineService.saveMineReg(user); //添加成功后，返回对象
		return "/web_view/mine/mine_login";
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
			SysUserInfo bbsUserInfo=mineService.querySysUserInfo(user);
			if(bbsUserInfo!=null){
				//存储BBS登录信息
				session.setAttribute("bbsUserInfo", bbsUserInfo);
				return "true";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "false";
		
	}
	
	/**
	 * 说明：个人中心
	 * @author Administrator
	 * @createTime 2018年10月23日13:59:59
	 * */
	@RequestMapping("vf/home")
	public String gotoMineHome(){
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

}
