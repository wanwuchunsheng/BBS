package com.bbs.model.view;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * 回帖实体类
 * */
@Entity(name = "bbs_reply")
public class BBSReply implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer bbId;

    private Integer smboId;

    private Integer postsId;

    private String content;

    private Integer contGood;

    private Integer contBad;

    private Integer replyId;

    private Integer gradeId;
    
    
    private Integer createUserId;

    private String createUserName;

    private Date createDate;

    private Integer del;
    
    //
    private String imgPath;//头像路径

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name = "bb_id")
    public Integer getBbId() {
        return bbId;
    }

    public void setBbId(Integer bbId) {
        this.bbId = bbId;
    }

    @Column(name = "smbo_id")
    public Integer getSmboId() {
        return smboId;
    }

    public void setSmboId(Integer smboId) {
        this.smboId = smboId;
    }
    @Column(name = "posts_id")
    public Integer getPostsId() {
        return postsId;
    }

    public void setPostsId(Integer postsId) {
        this.postsId = postsId;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Column(name = "cont_good")
    public Integer getContGood() {
        return contGood;
    }

    public void setContGood(Integer contGood) {
        this.contGood = contGood;
    }

    @Column(name = "cont_bad")
    public Integer getContBad() {
        return contBad;
    }

    public void setContBad(Integer contBad) {
        this.contBad = contBad;
    }
    @Column(name = "reply_id")
    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    @Column(name = "grade_id")
    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }
    @Column(name = "create_user_id")
    public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	@Column(name = "create_user_name")
	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Column(name = "del")
	public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
    
    
}