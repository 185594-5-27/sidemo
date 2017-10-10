package com.si.demo.sys.controller;

import com.si.demo.common.base.constant.SystemStaticConst;
import com.si.demo.common.base.controller.GenericController;
import com.si.demo.common.base.service.GenericService;
import com.si.demo.common.util.json.JsonHelper;
import com.si.demo.common.util.user.UserInfo;
import com.si.demo.sys.entity.*;
import com.si.demo.sys.service.TreeService;
import com.si.demo.sys.service.UserAssociateRoleService;
import com.si.demo.sys.service.UserRoleService;
import com.si.demo.sys.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/*
* 类描述：用户维护controller
* @auther linzf
* @create 2017/9/7 0007
*/
@Controller
@RequestMapping("/user")
public class UserController extends GenericController<User,QueryUser> {

    @Inject
    private UserService userService;
    @Inject
    private TreeService treeService;
    @Inject
    private UserRoleService userRoleService;
    @Inject
    private UserAssociateRoleService userAssociateRoleService;

    @Override
    protected GenericService<User, QueryUser> getService() {
        return userService;
    }

    /**
     * 功能描述：加载首页菜单节点的数据
     * @return
     */
    @RequestMapping(value="/mainTree",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> mainTree(){
        Map<String,Object> result = new HashMap<String, Object>();
        List<Tree> trees = UserInfo.loadUserTree(treeService);
        result.put("data",trees);
        result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
        return result;
    }

    /**
     * 功能描述：更新用户状态为禁用/启用
     * @param entity
     * @return
     */
    @RequestMapping(value="/userControl")
    @ResponseBody
    public Map<String,Object> userControl(User entity) throws Exception{
        Map<String,Object> result = new HashMap<String, Object>();
        if(userService.userControl(entity)){
            result.put(SystemStaticConst.RESULT,SystemStaticConst.SUCCESS);
            result.put(SystemStaticConst.MSG,"更新用户状态成功！");
            result.put("entity",entity);
        }else{
            result.put(SystemStaticConst.RESULT,SystemStaticConst.FAIL);
            result.put(SystemStaticConst.MSG,"更新用户状态失败！");
        }
        return result;
    }

    /**
     * 功能描述：跳转到更新用户的页面
     * @param entity
     * @param model
     * @return
     * @throws Exception
     */
    @Override
    public String updatePage(User entity, Model model) throws Exception {
        entity = getService().get(entity);
        entity.setRoleArray(JsonHelper.list2json( Optional.ofNullable(userService.findByLogin(entity.getLogin())).filter(u->u!=null).orElse(new User()).getRoles()));
        model.addAttribute("entity",entity);
        return getPageBaseRoot()+UPDATEPAGE;
    }

    /**
     * 功能描述：加载所有的权限数据
     * @return
     */
    @RequestMapping(value = "/loadRoles",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> loadRoles(){
        Map<String,Object> result = new HashMap<String, Object>();
        List<UserRole> userRoleList = userRoleService.query(null);
        result.put(SystemStaticConst.RESULT,SystemStaticConst.SUCCESS);
        result.put("list",userRoleList);
        return result;
    }

    /**
     * 功能描述：跳转到选择组织架构页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/pickGroup")
    public String pickGroup() throws Exception{
        return getPageBaseRoot() + "/group";
    }

}
