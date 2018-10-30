package com.bbs.controller.common;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.bbs.service.view.IInitService;

/**
 * 服务器启动时，访问数据库，加载所有实例化信息到静态类中
 * <li>@author sch
 */
@WebServlet("/InitResourceServlet")
public class InitResourceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Logger log = Logger.getLogger(this.getClass());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitResourceServlet() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		IInitService comboboxs=ApplicationContextUtil.getBean(IInitService.class);
		comboboxs.queryBBSBigBoardAll();// 查询父版块
		comboboxs.queryBBSSmallBoardAll();//查询子版块
		comboboxs.queryPostCommend();//查询基础数据
		comboboxs.queryPostsNews();//查询站内新闻
		log.info("初始化数据成功！！");
	}

}
