package com.bbs.service.sys;

import com.bbs.model.sys.SysAuditCertRecord;
import com.bbs.model.sys.SysUserInfo;
import com.ibm.framework.dal.pagination.Pagination;
import com.ibm.framework.dal.pagination.PaginationResult;

import java.util.List;

public interface ISysAuditCertRecordService {

	//查询处理记录
	List<SysAuditCertRecord> getByUid(String uid);

	//根据ID查询
	SysAuditCertRecord getById(String id);

    //列表查询
	PaginationResult<List<SysAuditCertRecord>> queryListAll(SysAuditCertRecord auditCertRecord,
                                                            Pagination pagination);
    //添加
	void add(SysAuditCertRecord acr);
	//修改
	void update(SysAuditCertRecord acr);

	//删除用户
	void deleteAll(String[] str);

}
