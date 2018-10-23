package com.bbs.service.view;

import com.bbs.model.sys.SysUserInfo;

public interface IMineService {

	void saveMineReg(SysUserInfo user);

	SysUserInfo querySysUserInfo(SysUserInfo user);

}
