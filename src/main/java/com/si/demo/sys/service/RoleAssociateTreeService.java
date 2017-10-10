package com.si.demo.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.si.demo.common.base.service.GenericService;
import com.si.demo.common.base.dao.GenericDao;

import com.si.demo.sys.entity.RoleAssociateTree;
import com.si.demo.sys.entity.QueryRoleAssociateTree;
import com.si.demo.sys.dao.RoleAssociateTreeDao;
import org.springframework.transaction.annotation.Transactional;

/**
 *@author linzf
 **/
@Service("roleAssociateTreeService")
@Transactional(rollbackFor={IllegalArgumentException.class})
public class RoleAssociateTreeService extends GenericService<RoleAssociateTree, QueryRoleAssociateTree> {
	@Autowired
	@SuppressWarnings("SpringJavaAutowiringInspection")
	private RoleAssociateTreeDao roleAssociateTreeDao;
	@Override
	protected GenericDao<RoleAssociateTree, QueryRoleAssociateTree> getDao() {
		return roleAssociateTreeDao;
	}
}