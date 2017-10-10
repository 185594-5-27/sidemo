package com.si.demo.sys.dao;

import com.si.demo.common.base.dao.GenericDao;

import com.si.demo.sys.entity.User;
import com.si.demo.sys.entity.UserAssociateRole;
import com.si.demo.sys.entity.QueryUserAssociateRole;

/**
 *@author linzf
 **/
public interface UserAssociateRoleDao extends GenericDao<UserAssociateRole, QueryUserAssociateRole> {

    /**
     * 功能描述：根据用户的ID来删除用户的权限数据
     * @param user
     * @return
     */
    int removeUserRole(User user);
}