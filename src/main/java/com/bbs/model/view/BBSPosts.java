package com.bbs.model.view;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * 发帖实体bean
 * 
 * */
@Entity(name = "bbs_posts")
public class BBSPosts {
	
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

    private Integer del;

    private String content;
    
    //
    private String imgPath;//用户头像路径
    //
    private Integer securityLevel;//用户级别  0-默认用户  1-官网认证
    private String niceName; //昵称
    private String bbName;//主版块名称
    private String smboName;//子版块名称 
    private Integer expPoints;//用户签到经验值


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
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }
    @Column(name = "typ")
    public Integer getTyp() {
        return typ;
    }

    public void setTyp(Integer typ) {
        this.typ = typ;
    }
    @Column(name = "cont_preview")
    public Integer getContPreview() {
        return contPreview;
    }

    public void setContPreview(Integer contPreview) {
        this.contPreview = contPreview;
    }
    @Column(name = "cont_reply")
    public Integer getContReply() {
        return contReply;
    }

    public void setContReply(Integer contReply) {
        this.contReply = contReply;
    }
    @Column(name = "cont_reward")
    public Integer getContReward() {
        return contReward;
    }

    public void setContReward(Integer contReward) {
        this.contReward = contReward;
    }
    @Column(name = "cont_top")
    public Integer getContTop() {
        return contTop;
    }

    public void setContTop(Integer contTop) {
        this.contTop = contTop;
    }
    @Column(name = "cont_rec")
    public Integer getContRec() {
        return contRec;
    }

    public void setContRec(Integer contRec) {
        this.contRec = contRec;
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
    @Column(name = "cont_status")
    public Integer getContStatus() {
        return contStatus;
    }

    public void setContStatus(Integer contStatus) {
        this.contStatus = contStatus;
    }
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }
    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    @Column(name = "update_user_id")
    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }
    @Column(name = "update_user_name")
    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName == null ? null : updateUserName.trim();
    }
    @Column(name = "update_date")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
   
    @Column(name = "del")
    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Integer getSecurityLevel() {
		return securityLevel;
	}

	public void setSecurityLevel(Integer securityLevel) {
		this.securityLevel = securityLevel;
	}

	public String getNiceName() {
		return niceName;
	}

	public void setNiceName(String niceName) {
		this.niceName = niceName;
	}

	public String getBbName() {
		return bbName;
	}

	public void setBbName(String bbName) {
		this.bbName = bbName;
	}

	public String getSmboName() {
		return smboName;
	}

	public void setSmboName(String smboName) {
		this.smboName = smboName;
	}

	public Integer getExpPoints() {
		return expPoints;
	}

	public void setExpPoints(Integer expPoints) {
		this.expPoints = expPoints;
	}

	
	
}