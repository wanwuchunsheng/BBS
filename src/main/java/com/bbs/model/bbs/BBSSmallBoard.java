package com.bbs.model.bbs;

import java.util.Date;

public class BBSSmallBoard {
	
    private Integer id;

    private Integer bbId;

    private Integer num;

    private String title;

    private Integer contPreview;

    private String remark;

    private Integer createUserId;

    private String createUserName;

    private Date createDate;

    private Integer updateUserId;

    private String updateUserName;

    private Date updateDate;

    private String sbAdmin;

    private Integer del;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBbId() {
        return bbId;
    }

    public void setBbId(Integer bbId) {
        this.bbId = bbId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getContPreview() {
        return contPreview;
    }

    public void setContPreview(Integer contPreview) {
        this.contPreview = contPreview;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName == null ? null : updateUserName.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getSbAdmin() {
        return sbAdmin;
    }

    public void setSbAdmin(String sbAdmin) {
        this.sbAdmin = sbAdmin == null ? null : sbAdmin.trim();
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}