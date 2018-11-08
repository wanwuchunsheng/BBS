package com.bbs.service.sys.impl;

import com.bbs.model.sys.SysAuditCertRecord;
import com.bbs.model.sys.SysResourceInfo;
import com.bbs.model.sys.SysUserInfo;
import com.bbs.service.sys.ISysAuditCertRecordService;
import com.ibm.framework.dal.client.IPaginationDalClient;
import com.ibm.framework.dal.pagination.Pagination;
import com.ibm.framework.dal.pagination.PaginationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 审核认证实现
 * 
 * */
@Service("auditCertRecordService")
public class SysAuditCertRecordServiceImpl implements ISysAuditCertRecordService {

   
	@Autowired
    IPaginationDalClient dalClient;

	/**
	 * 查询审核记录
	 * @param uid
	 * @return
	 */
	@Override
	public List<SysAuditCertRecord> getByUid(String uid) {
		return dalClient.queryForList("auditCertRecord.getByUid", uid, SysAuditCertRecord.class);
	}

	/**
	 * 根据id查询指定记录
	 * @param id
	 * @return
	 */
	@Override
	public SysAuditCertRecord getById(String id) {
		return dalClient.queryForObject("auditCertRecord.getByUid", id, SysAuditCertRecord.class);
	}

	/**
	 * 审核认证列表查询
	 * @param auditCertRecord
	 * @param pagination
	 * @return
	 */
	@Override
	public PaginationResult<List<SysAuditCertRecord>> queryListAll(SysAuditCertRecord auditCertRecord, Pagination pagination) {
		return  dalClient.queryForList("auditCertRecord.queryListAll", auditCertRecord,SysAuditCertRecord.class, pagination);
	}

	/**
	 * 新增
	 * @param acr
	 */
	@Override
	public void add(SysAuditCertRecord acr) {
		dalClient.persist(acr);
	}

	/**
	 * 更新
	 * @param acr
	 */
	@Override
	public void update(SysAuditCertRecord acr) {
		dalClient.dynamicMerge(acr);
	}

	/**
	 * 批量删除
	 * @param str
	 */
	@Override
	public void deleteAll(String[] str) {
		for(String s:str){
			dalClient.execute("auditCertRecord.delete", str);
		}
	}
}
