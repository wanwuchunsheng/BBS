package com.bbs.model.view;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * 子版块
 * 
 * */
@Entity(name = "bbs_small_board")
public class BBSSmallBoard implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
    private String imgPath;
    private Integer del;
    


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
    @Column(name = "num")
    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }
    @Column(name = "cont_preview")
    public Integer getContPreview() {
        return contPreview;
    }

    public void setContPreview(Integer contPreview) {
        this.contPreview = contPreview;
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

    @Column(name = "sb_admin")
	public String getSbAdmin() {
		return sbAdmin;
	}

	public void setSbAdmin(String sbAdmin) {
		this.sbAdmin = sbAdmin;
	}
	@Column(name = "img_path")
	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	
    
}