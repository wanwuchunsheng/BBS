package com.bbs.model.view;

import java.util.Date;

public class BBSPost {
    private Integer id;

    private Integer bbId;

    private Integer smboId;

    private String title;

    private Integer typ;

    private Integer contPreview;

    private Integer contReply;

    private Integer contReward;

    private Integer contTop;

    private Integer contRec;

    private Integer contGood;

    private Integer contBad;

    private Integer contStatus;

    private String remark;

    private Integer createUserId;

    private String createUserName;

    private Date createDate;

    private Integer updateUserId;

    private String updateUserName;

    private Date updateDate;

    private String sbAdmin;

    private Integer del;

    private String content;

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

    public Integer getSmboId() {
        return smboId;
    }

    public void setSmboId(Integer smboId) {
        this.smboId = smboId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getTyp() {
        return typ;
    }

    public void setTyp(Integer typ) {
        this.typ = typ;
    }

    public Integer getContPreview() {
        return contPreview;
    }

    public void setContPreview(Integer contPreview) {
        this.contPreview = contPreview;
    }

    public Integer getContReply() {
        return contReply;
    }

    public void setContReply(Integer contReply) {
        this.contReply = contReply;
    }

    public Integer getContReward() {
        return contReward;
    }

    public void setContReward(Integer contReward) {
        this.contReward = contReward;
    }

    public Integer getContTop() {
        return contTop;
    }

    public void setContTop(Integer contTop) {
        this.contTop = contTop;
    }

    public Integer getContRec() {
        return contRec;
    }

    public void setContRec(Integer contRec) {
        this.contRec = contRec;
    }

    public Integer getContGood() {
        return contGood;
    }

    public void setContGood(Integer contGood) {
        this.contGood = contGood;
    }

    public Integer getContBad() {
        return contBad;
    }

    public void setContBad(Integer contBad) {
        this.contBad = contBad;
    }

    public Integer getContStatus() {
        return contStatus;
    }

    public void setContStatus(Integer contStatus) {
        this.contStatus = contStatus;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}