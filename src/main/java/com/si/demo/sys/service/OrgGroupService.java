package com.si.demo.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.si.demo.common.base.service.GenericService;
import com.si.demo.common.base.dao.GenericDao;

import com.si.demo.sys.entity.OrgGroup;
import com.si.demo.sys.entity.QueryOrgGroup;
import com.si.demo.sys.dao.OrgGroupDao;
import org.springframework.transaction.annotation.Transactional;

/**
 *@author linzf
 **/
@Service("orgGroupService")
@Transactional(rollbackFor={IllegalArgumentException.class})
public class OrgGroupService extends GenericService<OrgGroup, QueryOrgGroup> {
	@Autowired
	@SuppressWarnings("SpringJavaAutowiringInspection")
	private OrgGroupDao orgGroupDao;
	@Override
	protected GenericDao<OrgGroup, QueryOrgGroup> getDao() {
		return orgGroupDao;
	}

	/**
	 * 功能描述：根据父节点来查询最大的节点的值
	 * @param parentNode
	 * @return
	 */
	public String getMaxOrgGroup(String parentNode){
		return orgGroupDao.getMaxOrgGroup(parentNode);
	}

	/**
	 * 功能描述：根据菜单节点NODE来查询节点数据
	 * @param node
	 * @return
	 */
	public OrgGroup findByNode(String node){
		return orgGroupDao.findByNode(node);
	}
}