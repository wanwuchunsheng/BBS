package com.bbs.model.bbs;

public class BBSReply {
    private Integer id;

    private Integer biboId;

    private Integer smboId;

    private Integer postsId;

    private String content;

    private Integer contGood;

    private Integer contBad;

    private Integer replyId;

    private Integer gradeId;

    private Integer del;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBiboId() {
        return biboId;
    }

    public void setBiboId(Integer biboId) {
        this.biboId = biboId;
    }

    public Integer getSmboId() {
        return smboId;
    }

    public void setSmboId(Integer smboId) {
        this.smboId = smboId;
    }

    public Integer getPostsId() {
        return postsId;
    }

    public void setPostsId(Integer postsId) {
        this.postsId = postsId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}