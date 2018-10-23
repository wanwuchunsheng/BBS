package com.bbs.controller.common.inter;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.bbs.model.sys.SysUserInfo;
/**
 * bbs拦截器
 * 
 * */
public class BBSUserInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session=request.getSession();
		//验证后端登录
		SysUserInfo bbsBserInfo= (SysUserInfo) session.getAttribute("bbsUserInfo");
		System.out.println("进入拦截器:"+bbsBserInfo);
		if(bbsBserInfo!=null){
			return true;
		}
		//失败重定向
	    PrintWriter out = response.getWriter();
	    out.println("<html>");    
	    out.println("<script>");    
	    out.println("window.open ('"+request.getContextPath()+"/mine/login','_top')");    
	    out.println("</script>");    
	    out.println("</html>");  
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
