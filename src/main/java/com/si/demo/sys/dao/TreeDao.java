package com.si.demo.sys.dao;

import com.si.demo.common.base.dao.GenericDao;

import com.si.demo.sys.entity.Tree;
import com.si.demo.sys.entity.QueryTree;
import com.si.demo.sys.entity.User;

import java.util.List;

/**
 *@author linzf
 **/
public interface TreeDao extends GenericDao<Tree, QueryTree> {

    /**
     * 功能描述：加载用户的菜单树的数据
     * @param user
     * @return
     */
	List<Tree> loadUserTree(User user);
}