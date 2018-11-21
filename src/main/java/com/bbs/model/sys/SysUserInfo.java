package com.bbs.model.sys;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * 用户表
 * 
 * */
@Entity(name = "bbs_sys_user")
public class SysUserInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date createTime;
	private Integer createUserId; //创建人ID
	private Date updateTime; //修改时间
	private Integer updateUserId; //修改人ID
	private String uname; //姓名
	private String upwd; //密码
	private String eno; //卡号
	private Integer gender; //性别 0-女  1-男
	private String tel; //电话
	private String phone; //手机
	private String email; //邮箱
	private Integer del; //0-默认  1：删除
	/**
	 * 0-后端管理用户 
		1-普通注册用户  
		2-官网认证用户  
		3-业主
	 * 
	 * */
	private Integer securityLevel; 
	private String webchat; //微信
	private String qq; //QQ
	private Integer usrStatus; //状态
	private String smsNotitfy; //短信通知
	private String photoPath; //图片地址
	private String qrcodePath;//二维码
	private String address; //地址
	private Integer dpnum;//修改次数
	private String job;//职位，工作
	private String nowAddress;//现居地，收货地址
	private String niceName;//昵称
	private String remark;//备注
	private String idcode;//身份证号
	private String birthdate;//出生年月
	
	private Integer expPoints;//经验值
	private String isCheck;//BBS签到
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name = "create_user_id")
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	
	@Column(name = "update_time")
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Column(name = "update_user_id")
	public Integer getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}
	
	@Column(name = "uname")
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	
	@Column(name = "upwd")
	public String getUpwd() {
		return upwd;
	}
	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}
	
	@Column(name = "eno")
	public String getEno() {
		return eno;
	}
	public void setEno(String eno) {
		this.eno = eno;
	}
	
	@Column(name = "gender")
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	
	@Column(name = "tel")
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name = "email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "del")
	public Integer getDel() {
		return del;
	}
	public void setDel(Integer del) {
		this.del = del;
	}
	
	@Column(name = "security_level")
	public Integer getSecurityLevel() {
		return securityLevel;
	}
	public void setSecurityLevel(Integer securityLevel) {
		this.securityLevel = securityLevel;
	}
	
	@Column(name = "webchat")
	public String getWebchat() {
		return webchat;
	}
	public void setWebchat(String webchat) {
		this.webchat = webchat;
	}
	
	@Column(name = "qq")
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	
	@Column(name = "usr_status")
	public Integer getUsrStatus() {
		return usrStatus;
	}
	public void setUsrStatus(Integer usrStatus) {
		this.usrStatus = usrStatus;
	}
	
	@Column(name = "sms_notitfy")
	public String getSmsNotitfy() {
		return smsNotitfy;
	}
	public void setSmsNotitfy(String smsNotitfy) {
		this.smsNotitfy = smsNotitfy;
	}
	
	@Column(name = "photo_path")
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	
	@Column(name = "qrcode_path")
	public String getQrcodePath() {
		return qrcodePath;
	}
	public void setQrcodePath(String qrcodePath) {
		this.qrcodePath = qrcodePath;
	}
	
	@Column(name = "address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "dpnum")
	public Integer getDpnum() {
		return dpnum;
	}
	public void setDpnum(Integer dpnum) {
		this.dpnum = dpnum;
	}
	@Column(name = "job")
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	@Column(name = "now_address")
	public String getNowAddress() {
		return nowAddress;
	}
	public void setNowAddress(String nowAddress) {
		this.nowAddress = nowAddress;
	}
	@Column(name = "nice_name")
	public String getNiceName() {
		return niceName;
	}
	public void setNiceName(String niceName) {
		this.niceName = niceName;
	}
	@Column(name = "exp_points")
	public Integer getExpPoints() {
		return expPoints;
	}
	public void setExpPoints(Integer expPoints) {
		this.expPoints = expPoints;
	}
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIsCheck() {
		return isCheck;
	}
	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}
	@Column(name = "idcode")
	public String getIdcode() {
		return idcode;
	}
	public void setIdcode(String idcode) {
		this.idcode = idcode;
	}
	@Column(name = "birthdate")
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	
	
	
	
	
}
