package com.bbs.model.sys;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 认证审核
 * 
 * */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity(name = "sys_authentication_record")
public class SysAuditCertRecord implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "create_time")
	private Date createTime;

	@Column(name = "create_user_id")
	private Integer createUserId; //创建人ID

	@Column(name = "update_time")
	private Date updateTime; //修改时间

	@Column(name = "update_user_id")
	private Integer updateUserId; //修改人ID

	@Column(name = "user")
	private String user;	//申请人ID

	@Column(name = "status")
	private String status;	//审核状态

	@Column(name = "describe")
	private String describe; //审核意见

	@Column(name = "type")
	private String type;	//认证类型

	@Column(name = "channel")
	private String channel;	//申请渠道

	@Column(name = "remark")
	private String remark;	//备注

	/*非持久化属性*/
	@Transient
	private String  uname;	//申请人昵称

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}
	
	
	
	
	
	
}
