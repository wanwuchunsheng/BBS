package com.bbs.service.common;

/*
 * Copyright (C), 2014-2015, 以利真皮沙发厂
 * FileName: CccSgmConstants.java
 * Author:  万昌煌
 * Date:     2014年4月3日 下午13:00:00
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
public interface Constants {

	/**
     *  提交返回提示：
     *  
     *   1-成功 
     *   2-失败 
     */
    public static final String RESULT_SUCESS = "1";
    public static final String RESULT_ERROR = "2";
    
    public static final String[] dpnums={"JH"};
    
    /**
     * 图片保存地址
     * */
    //BBS上传
    public static final String BBS_REALPATH = "javascript:;";
    public static final String BBS_SAVE_IMG = "E:/www2/zhangt-0b44449e649aee1e0164b519f6cd054b/webapp/images/bbs/";
    public static final String BBS_REQ_IMG="https://cloudfy.cn/images/bbs/";
    
    
    /**
     * 分页
     * 
     * */
    public static final Integer ABOUT_INDEX = 0;
    /**
     * bbs签到默认5个经验值
     * 
     * */
    public static final Integer EXP_POINTS = 5;
    /**
     * 验证是否签到
     *  
     *   1-已签到
     * 
     * */
    public static final String IS_CHECK = "1";
    /**
     * 每次发帖子，都扣5个经验值
     * 
     * */
    public static final Integer DED_EXP_POINTS = 5;
    
}
