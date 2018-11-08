package com.bbs.model.view;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * 收藏实体类
 * 
 * */
@Entity(name = "bbs_post_collection")
public class BBSPostCollection implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer bbId;

    private Integer smboId;

    private Integer postsId;

    private Integer createUserId;

    private String createUserName;

    private Date createDate;

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
}