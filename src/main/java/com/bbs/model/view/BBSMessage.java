package com.bbs.model.view;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * 消息类
 * */
@Entity(name = "bbs_message")
public class BBSMessage implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer typ;

    private Integer status;
    
    private String content;

    private Integer createUserId;

    private String createUserName;

    private Date createDate;

    private Integer del;
    
    private Integer recipientId;//接收人
    
   

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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
    
    @Column(name = "typ")
	public Integer getTyp() {
		return typ;
	}
	public void setTyp(Integer typ) {
		this.typ = typ;
	}
	
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Column(name = "recipient_id")
	public Integer getRecipientId() {
		return recipientId;
	}
	public void setRecipientId(Integer recipientId) {
		this.recipientId = recipientId;
	}

	
    
}