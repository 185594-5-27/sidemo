/*
Navicat MySQL Data Transfer

Source Server         : 31数据库
Source Server Version : 50716
Source Host           : 10.6.71.236:3306
Source Database       : hyll_springboot

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-10-11 20:20:02
*/

CREATE DATABASE hyll_springboot;
use hyll_springboot;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `dict`
-- ----------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `is_load` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dict
-- ----------------------------
INSERT INTO `dict` VALUES ('1', 'testCode', '测试参数', 'testType', 'testValue', '0');
INSERT INTO `dict` VALUES ('33', 'fileTempPath', '上传的临时文件的保存位置', 'fileTempPath', 'E://test//', '0');
INSERT INTO `dict` VALUES ('34', 'SYSTEM_USER', '系统用户', 'USER_TYPE', '1', '1');
INSERT INTO `dict` VALUES ('35', 'MOBILE_USER', '移动端用户', 'USER_TYPE', '2', '1');
INSERT INTO `dict` VALUES ('37', 'OK', '允许', 'DICT_IS_LOAD', '1', '1');
INSERT INTO `dict` VALUES ('38', 'NOT', '不允许', 'DICT_IS_LOAD', '0', '1');
INSERT INTO `dict` VALUES ('39', 'USE', '启用', 'USER_STATE', '1', '1');
INSERT INTO `dict` VALUES ('40', 'UNUSE', '禁用', 'USER_STATE', '0', '1');

-- ----------------------------
-- Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `img_url` varchar(255) DEFAULT NULL,
  `is_delete` varchar(255) DEFAULT NULL,
  `send_date` datetime DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '111', '11', '1', '2017-09-23 19:57:34', '1', '111');
INSERT INTO `message` VALUES ('11', '123', null, null, null, null, null);
INSERT INTO `message` VALUES ('12', '123', null, null, null, null, null);



-- ----------------------------
-- Table structure for `org_group`
-- ----------------------------
DROP TABLE IF EXISTS `org_group`;
CREATE TABLE `org_group` (
  `group_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `existing_num` bigint(20) DEFAULT NULL,
  `group_code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `node` varchar(255) DEFAULT NULL,
  `num` bigint(20) DEFAULT NULL,
  `parent_node` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of org_group
-- ----------------------------
INSERT INTO `org_group` VALUES ('1', null, '0022', '福建好运联联22', '001', '222', '0');
INSERT INTO `org_group` VALUES ('2', null, null, '技术部', '001001', '0', '001');
INSERT INTO `org_group` VALUES ('3', null, null, '运营部', '001002', '0', '001');
INSERT INTO `org_group` VALUES ('5', '0', '00212', '福建2', '002', '12', '0');
INSERT INTO `org_group` VALUES ('6', '0', '3333', '2222', '002001', '2', '002');
INSERT INTO `org_group` VALUES ('8', '0', '4444', '444', '002001', '4', '002');


-- ----------------------------
-- Table structure for `tree`
-- ----------------------------
DROP TABLE IF EXISTS `tree`;
CREATE TABLE `tree` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `p_id` bigint(20) DEFAULT NULL,
  `tree_order` bigint(20) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `state` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tree
-- ----------------------------
INSERT INTO `tree` VALUES ('1', '#', 'fa fa-fw fa-cogs', '系统管理', '0', '1', '#', '1');
INSERT INTO `tree` VALUES ('2', null, 'fa fa-fw fa-tree', '菜单管理', '1', '1', 'treeList', '1');
INSERT INTO `tree` VALUES ('7', 'groupManager', 'fa fa-fw fa-group', '组织架构', '1', '2', 'groupList', '1');
INSERT INTO `tree` VALUES ('8', 'userRoleManager', 'fa fa-fw fa-user-secret', '角色管理', '1', '3', 'userRoleList', '1');
INSERT INTO `tree` VALUES ('9', 'userManager', 'fa fa-fw fa-user', '用户维护', '1', '4', 'userList', '1');
INSERT INTO `tree` VALUES ('10', 'dictManager', 'fa fa-fw fa-book', '字典维护', '1', '5', 'dictList', '0');
INSERT INTO `tree` VALUES ('17', 'sysManager', 'fa fa-fw fa-desktop', '系统功能', '0', '2', '#', '1');
INSERT INTO `tree` VALUES ('18', '111', '111', '系统功能', '17', '4', '11', '1');
INSERT INTO `tree` VALUES ('20', 'goodtest', '', '测试菜单', '17', '3', 'good', '1');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `job` varchar(255) DEFAULT NULL,
  `group_id` bigint(20) DEFAULT NULL,
  `birth_date` datetime DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `street_address` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `last_login_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgcws90nffc311tac0vg2xy6cw` (`group_id`),
  CONSTRAINT `FKgcws90nffc311tac0vg2xy6cw` FOREIGN KEY (`group_id`) REFERENCES `org_group` (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'hyll', '140b6ce18716153fba3bf98a52722bd5', 'linzf', '福建省福州市晋安区铜盘路29号超大大厦', 'java开发', '2', null, '福州市', '晋安区', '福建省', '铜盘路29号超大大厦', '1', '1', '2017-10-10 15:49:06');
INSERT INTO `user` VALUES ('2', 'hyll144', '140b6ce18716153fba3bf98a52722bd5', '福建好运联联', '北京市北京市市辖区东城区阿道夫的辐射大', 'java开发', '2', null, '北京市市辖区', '东城区', '北京市', '阿道夫的辐射大', '1', '1', null);
INSERT INTO `user` VALUES ('9', 'linzhefeng23', '140b6ce18716153fba3bf98a52722bd5', '林泽锋', '福建省福州市晋安区秀山路188号', 'java开发', '2', null, '福州市', '晋安区', '福建省', '秀山路188号', '0', '1', null);
INSERT INTO `user` VALUES ('20', 'linzf1', '140b6ce18716153fba3bf98a52722bd5', 'linzf2', '福建省福州市晋安区linzf12', 'linzf2', '1', null, '福州市', '晋安区', '福建省', 'linzf12', '1', null, null);

-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', 'ROLE_ADMIN', '系统管理员');
INSERT INTO `user_role` VALUES ('2', 'ROLE_USER', '普通用户');

-- ----------------------------
-- Table structure for `user_associate_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_associate_role`;
CREATE TABLE `user_associate_role` (
  `user_id` int(11) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FK4kspd00l9tn3oi6swv1cjf7vh` (`role_id`),
  KEY `FKl7kmw86eevxmoxwxu55poq7bm` (`user_id`),
  CONSTRAINT `FK4kspd00l9tn3oi6swv1cjf7vh` FOREIGN KEY (`role_id`) REFERENCES `user_role` (`id`),
  CONSTRAINT `FKl7kmw86eevxmoxwxu55poq7bm` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_associate_role
-- ----------------------------
INSERT INTO `user_associate_role` VALUES ('1', '1');
INSERT INTO `user_associate_role` VALUES ('1', '2');
INSERT INTO `user_associate_role` VALUES ('20', '1');

-- ----------------------------


-- ----------------------------
-- Table structure for `role_associate_tree`
-- ----------------------------
DROP TABLE IF EXISTS `role_associate_tree`;
CREATE TABLE `role_associate_tree` (
  `role_id` bigint(20) NOT NULL,
  `tree_id` bigint(20) NOT NULL,
  KEY `FKdslec8iii7ggslupebobmpu4` (`tree_id`),
  KEY `FKgkuemjgtln0vlvcokl9pn38f7` (`role_id`),
  CONSTRAINT `FKdslec8iii7ggslupebobmpu4` FOREIGN KEY (`tree_id`) REFERENCES `tree` (`id`),
  CONSTRAINT `FKgkuemjgtln0vlvcokl9pn38f7` FOREIGN KEY (`role_id`) REFERENCES `user_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_associate_tree
-- ----------------------------
INSERT INTO `role_associate_tree` VALUES ('1', '1');
INSERT INTO `role_associate_tree` VALUES ('1', '2');
INSERT INTO `role_associate_tree` VALUES ('1', '7');
INSERT INTO `role_associate_tree` VALUES ('1', '8');
INSERT INTO `role_associate_tree` VALUES ('1', '9');
INSERT INTO `role_associate_tree` VALUES ('1', '10');
INSERT INTO `role_associate_tree` VALUES ('1', '17');
INSERT INTO `role_associate_tree` VALUES ('1', '18');
INSERT INTO `role_associate_tree` VALUES ('2', '17');
INSERT INTO `role_associate_tree` VALUES ('2', '18');

-- ----------------------------
-- Table structure for `message_associate_user`
-- ----------------------------
DROP TABLE IF EXISTS `message_associate_user`;
CREATE TABLE `message_associate_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_delete` varchar(255) DEFAULT '1',
  `read_date` datetime DEFAULT NULL,
  `state` varchar(255) DEFAULT '0',
  `message_id` bigint(20) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKaucwt2f9waols3hrbma6p40ky` (`message_id`),
  KEY `FKju1emlnuk8xc4tbt497muikhu` (`user_id`),
  CONSTRAINT `FKaucwt2f9waols3hrbma6p40ky` FOREIGN KEY (`message_id`) REFERENCES `message` (`id`),
  CONSTRAINT `FKju1emlnuk8xc4tbt497muikhu` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message_associate_user
-- ----------------------------
INSERT INTO `message_associate_user` VALUES ('1', '1', null, '0', '1', '1');