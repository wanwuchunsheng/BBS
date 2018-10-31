package com.bbs.controller.common.servlet;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.bbs.controller.common.ApplicationContextUtil;
import com.bbs.model.sys.SysUserInfo;
import com.bbs.service.common.Constants;
import com.bbs.service.sys.ISysUserService;
import com.bbs.service.view.IIndexService;
import com.bbs.service.view.IInitService;
import com.bbs.service.view.IMineService;

public class UploadImgServiet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public UploadImgServiet() {
        super();
    }

    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
        // Put your code here
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * 图片上传
     * bbs_reply -回帖上传
     * bbs_editor -发帖上传
     * 要求返回的json格式不同，分开判断
     * 
     * */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String docName=request.getParameter("docName");
    	String path =Constants.BBS_SAVE_IMG+docName;
    	System.out.println("localhost url>>"+path);
        File file = new File(path);
        if (!file.exists())
            file.mkdirs();
        String fileName = "";// 文件名称
        /**上传文件处理内容**/
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload sfu = new ServletFileUpload(factory);
        sfu.setHeaderEncoding("UTF-8"); // 处理中文问题
        sfu.setSizeMax(2 * 1024 * 1024); // 限制文件大小
        try {
            List<FileItem> fileItems = sfu.parseRequest(request); // 解码请求
            for (FileItem fi : fileItems) {
                fileName = UUID.randomUUID()+fi.getName().substring(fi.getName().lastIndexOf("."),fi.getName().length());
                fi.write(new File(path, fileName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /******* 上次成功，返回处理 **********/
        //获取图片url地址
        String imgUrl = Constants.BBS_REQ_IMG+docName+"/"+ fileName;
        response.setContentType("text/text;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String,String> map=new HashMap<String, String>();
        if("bbs_editor".equals(docName)){ 
        	/**
        	 * 富文本-图片上传
        	 * 
        	 * */
        	map.put("url", imgUrl);
        	JSONArray json = JSONArray.fromObject(map); 
            out.print(json.toString());  //返回url地址
            out.flush();
            out.close();
        }else if("bbs_reply".equals(docName)){ 
        	 /**
        	  * 回帖-图片上传
        	  * 
        	  * */
        	 String str="{\"code\": 0 ,\"msg\": \"\"  ,\"data\": {\"src\": \""+imgUrl+"\",\"title\": \"\"}}";
        	 out.print(str);  //返回url地址
             out.flush();
             out.close();
        }else if("bbs_icon".equals(docName)){ 
        	/**
        	 * 个人资料-头像上传
        	 * 
        	 * */
        	HttpSession session=request.getSession();
        	//判断session
        	SysUserInfo bbsSysUserInfo= (SysUserInfo)session.getAttribute("bbsUserInfo");
        	if(bbsSysUserInfo!=null){
        		String oldFileName=bbsSysUserInfo.getPhotoPath();//旧的图片路径
        		bbsSysUserInfo.setPhotoPath(fileName);//更新session 图片路径
        		//修改用户
        		IMineService comboboxs=ApplicationContextUtil.getBean(IMineService.class);
        		comboboxs.updateUserPhotoPathById(bbsSysUserInfo);
        		//修改session url
        		session.setAttribute("bbsUserInfo", bbsSysUserInfo);
        		//删除旧的服务器本地图片
        		try {
        			String oldImgUrl = Constants.BBS_SAVE_IMG+docName+"/"+oldFileName;
            		File delfile = new File(oldImgUrl);
            		if (delfile.isFile() && delfile.exists()) {
            			delfile.delete();
            		}
				} catch (Exception e) {
					e.printStackTrace();
				}
        		//返回新的URL
        		map.put("url", imgUrl);
            	JSONArray json = JSONArray.fromObject(map); 
                out.print(json.toString());  //返回url地址
                out.flush();
                out.close();
        	}else{
        		//重定向到登录页面
    			PrintWriter fout = response.getWriter();
    			fout.println("<html>");    
    		    fout.println("<script>");    
    		    fout.println("window.open ('"+request.getContextPath()+"/cont/index','_top')");    
    		    fout.println("</script>");    
    		    fout.println("</html>");  
        	}
        }
    }

    public void init() throws ServletException {
        // Put your code here
    }
}