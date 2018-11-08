package com.bbs.controller.sys;

import com.bbs.model.page.DataTableRequest;
import com.bbs.model.page.DataTableResponse;
import com.bbs.model.sys.SysAuditCertRecord;
import com.bbs.model.sys.SysUserInfo;
import com.bbs.service.common.Constants;
import com.bbs.service.sys.ISysAuditCertRecordService;
import com.ibm.framework.dal.pagination.Pagination;
import com.ibm.framework.dal.pagination.PaginationResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 用户认真审核记录
 * author:boohsia
 */
@RestController
@RequestMapping("/web/auditcert")
public class SysAuditCertRecordController {
    Logger log=Logger.getLogger(this.getClass());

    @Autowired
    ISysAuditCertRecordService auditCertRecordService;



    @RequestMapping("list")
    public String pageList(HttpSession session,HttpServletRequest request, SysAuditCertRecord bean){
        try {
            return "/web_data/auditcert/auditcert_record_list"; //跳转审核记录页面
        } catch (Exception e) {
            log.error("ERROR:登录页面跳转失败！！！");
        }
        return "/web_data/error/405"; //跳转错误页面
    }


    /**
     * 审核认证 列表查询
     * @param session
     * @param bean
     * @param dataTable
     * @return
     */
    @RequestMapping("data")
    @ResponseBody
    public DataTableResponse<SysAuditCertRecord> pageData(HttpSession session, SysAuditCertRecord bean, DataTableRequest dataTable) {
        try {
            Pagination pagination = new Pagination(dataTable.getiDisplayLength(), dataTable.getCurrentPage());
            PaginationResult<List<SysAuditCertRecord>> paginationResult = auditCertRecordService.queryListAll(bean,pagination);
            return new DataTableResponse<SysAuditCertRecord>(dataTable.getsEcho(), paginationResult);
        } catch (Exception e) {
            log.error("ERROR:查询审核记录失败！！！");
        }
        return null;
    }

    @RequestMapping("/goAdd")
    public String addUserByObj(Model model) {
        return "web_data/auditcert/auditcert_record_add";
    }

    /**
     * 审核认证 跳转修改界面
     * @param request
     * @param acr
     * @return
     */
    @RequestMapping("/goEdit")
    public String goEdit(HttpServletRequest request, SysAuditCertRecord acr) {
        SysAuditCertRecord auditCertRecord=auditCertRecordService.getById(acr.getId().toString());
        //通过ID查询用户
        request.setAttribute("auditCertRecord",auditCertRecord );
        return "web_data/auditcert/auditcert_record_edit";
    }

    /**
     * 审核认证 新增
     * @param session
     * @param model
     * @param acr
     * @return
     */
    @RequestMapping("/addRecord")
    @ResponseBody
    public String addRecord(HttpSession session,Model model,SysAuditCertRecord acr) {
        try {
            //获得登录信息
            SysUserInfo userInfo=(SysUserInfo) session.getAttribute("userInfo");
            Date now = new Date();
            acr.setCreateTime(now);
            acr.setUpdateTime(now);
            acr.setCreateUserId(userInfo.getId());
            acr.setUpdateUserId(userInfo.getId());
            acr.setChannel("");
            acr.setDescribe("");
            acr.setRemark("");
            acr.setStatus("");
            acr.setType("");
            auditCertRecordService.add(acr);
            return Constants.RESULT_SUCESS;
        } catch (Exception e) {
            return Constants.RESULT_ERROR;
        }
    }

    /**
     * 审核认证 修改
     * @param session
     * @param model
     * @param acr
     * @return
     */
    @RequestMapping("/saveRecord")
    @ResponseBody
    public String updateUserById(HttpSession session,Model model,SysAuditCertRecord acr) {
        try {
            //获得登录信息
            SysUserInfo userInfo=(SysUserInfo) session.getAttribute("userInfo");
            acr.setUpdateTime(new Date());
            acr.setUpdateUserId(userInfo.getId());
            auditCertRecordService.update(acr);
            return Constants.RESULT_SUCESS;
        } catch (Exception e) {
            return Constants.RESULT_ERROR;
        }
    }


    /**
     * 审核认证 批量删除
     * @param ids
     * @return
     */
    @RequestMapping("/dels")
    @ResponseBody
    public String deleteUserById(String ids){
        try {
            String[] str=ids.split(",");
            auditCertRecordService.deleteAll(str);
            return Constants.RESULT_SUCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Constants.RESULT_ERROR;
    }
}
