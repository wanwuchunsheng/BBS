package com.bbs.controller.view;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bbs.service.view.IMineService;

/**
 * 说明：问卷调查
 *   
 * 
 * */
@Controller
@RequestMapping("/que")
public class QuestionnaireController {
	
	@Autowired
	IMineService mineService;
	
	/**
	 * 说明：跳转问卷页 
	 * 
	 * @author Administrator
	 * @createTime 2018年10月23日13:59:59
	 * */
	@RequestMapping("index")
	public String gotoQueIndex(HttpSession session){
		return "/web_view/que/que_index";
	}
	
	

}
