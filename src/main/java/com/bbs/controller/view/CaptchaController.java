package com.bbs.controller.view;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bbs.controller.common.tools.MailSender;
import com.bbs.controller.common.tools.StringUtil;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

@Controller
@RequestMapping(value = "/captcha")
public class CaptchaController {
	
	private static final Log log = LogFactory.getLog(CaptchaController.class);
	
	
    @Autowired
    private Producer captchaProducer;
 
    /**
     * 页面入口
     * @param model
     * @return
     */
    @RequestMapping(value = "/getCaptcha", method = RequestMethod.GET)
    public String getCaptcha(Model model) {
        model.addAttribute("timestamp", System.currentTimeMillis());
        return "captcha";
    }

    /**
     * 生成带验证码的图片
     * @param model
     * @param request
     * @param response
     * @param timestamp
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getCaptchaImage.jpg", method = RequestMethod.GET)
    public ModelAndView getCaptchaImage(Model model,HttpServletRequest request, HttpServletResponse response,
                                        @RequestParam(value = "timestamp", required = false) String timestamp) throws IOException {
        if (StringUtils.isEmpty(timestamp)) {
            //System.out.println("没有时间戳\ttimestamp:" + timestamp);
            model.addAttribute("timestamp", System.currentTimeMillis());
        } else {
            //System.out.println("有时间戳\ttimestamp:" + timestamp);
            model.addAttribute("timestamp", timestamp);
        }

        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        String capText = captchaProducer.createText();

        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        log.info("======生成了一个验证码，内容为：" + capText);
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
        return null;
    }

    /**
     *
     * @param timestamp
     * @param code
     * @param request
     * @return true或fasle,ture表示验证成功,false表示验证失败
     */
    @RequestMapping(value = "/checkCaptcha", method = RequestMethod.POST)
    @ResponseBody
    public String checkCaptcha(@RequestParam(value = "timestamp", required = false) String timestamp, @RequestParam(value = "code", required = false) String code,HttpServletRequest request) {
        boolean returnStr = false;
        String original =(String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (StringUtils.isNotEmpty(code)) {
            if (code.equalsIgnoreCase(original)) {
                returnStr = true;
            }
        }
        log.info("======验证结果" + returnStr);
        return returnStr + "";
    }
    
    
    /**
    *  说明：邮箱验证码验证
    * @param timestamp
    * @param code
    * @param request
    * @return true或fasle,ture表示验证成功,false表示验证失败
    */
   @RequestMapping(value = "/checkEmail", method = RequestMethod.POST)
   @ResponseBody
   public String checkEmail( @RequestParam(value = "email", required = false) String email,HttpServletRequest request) {
       //获得随机数
	   String emailCode=StringUtil.getItemId(6);
	   System.out.println("生产邮箱验证码："+emailCode);
	   MailSender mes=new MailSender();
	   //发送邮箱
	   mes.sendMail(email, "您好，邮箱验证码【"+emailCode+"】，请尽快完成注册验证，谢谢！武汉青山海伦社区欢迎您！！！官方网址：https://cloudfy.cn");
       return emailCode;
   }
	

}
