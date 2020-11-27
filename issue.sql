/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50623
 Source Host           : localhost:3306
 Source Schema         : issue

 Target Server Type    : MySQL
 Target Server Version : 50623
 File Encoding         : 65001

 Date: 26/11/2020 09:05:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for issuetable
-- ----------------------------
DROP TABLE IF EXISTS `issuetable`;
CREATE TABLE `issuetable`  (
  `issueID` int(200) NOT NULL AUTO_INCREMENT,
  `userID` int(200) NULL DEFAULT NULL,
  `creater` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `level` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `version` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `plantime` timestamp(0) NULL DEFAULT NULL,
  `acttime` timestamp(0) NULL DEFAULT NULL,
  `step` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `solution` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `issuestate` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`issueID`) USING BTREE,
  INDEX `userID`(`userID`) USING BTREE,
  CONSTRAINT `userID` FOREIGN KEY (`userID`) REFERENCES `usertable` (`userID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of issuetable
-- ----------------------------
INSERT INTO `issuetable` VALUES (1, 311706, '彭于晏', '功能bug', '2020-11-23 09:27:56', '代码完善', '一般', 'version1', '2020-11-23 09:27:11', NULL, '1.xx 2.xx', '', '待验证');
INSERT INTO `issuetable` VALUES (2, 311706, '彭于晏', '代码bug', '2020-11-23 09:27:56', '代码检查', '一般', 'version1', '2020-11-23 09:27:11', NULL, '1.xx 2.xx', '', '待修改');
INSERT INTO `issuetable` VALUES (3, 311706, '彭于晏', '格式bug', '2020-11-23 09:27:56', '格式化', '一般', 'version1', '2020-11-23 09:27:11', NULL, '1.xx 2.xx', '', '待修改');
INSERT INTO `issuetable` VALUES (4, 311706, '彭于晏', '界面bug', '2020-11-23 09:27:56', '界面检查', '一般', 'version2', '2020-11-23 09:27:11', NULL, '1.xx 2.xx', '', '待修改');
INSERT INTO `issuetable` VALUES (5, 311706, '彭于晏', '接口bug', '2020-11-23 09:27:56', '规范化接口', '严重', 'version1', '2020-11-23 09:27:11', '0000-00-00 00:00:00', '1.xx 2.xx', '解决方案：', '待验证');
INSERT INTO `issuetable` VALUES (6, 311706, '彭于晏', '界面布局', '2020-11-23 09:27:56', '界面美化', '严重', 'version2', '2020-11-23 09:27:11', '0000-00-00 00:00:00', '1.xx 2.xx', '解决方案：', '待验证');
INSERT INTO `issuetable` VALUES (7, 311706, '彭于晏', '界面美化', '2020-11-23 09:27:56', '界面美化', '严重', 'version2', '2020-11-23 09:27:11', '2020-11-24 09:38:42', '1.xx 2.xx', '解决方案：', '已关闭');
INSERT INTO `issuetable` VALUES (8, 311707, '王境泽', '功能bug', '2020-11-23 09:27:56', '代码完善', '一般', 'version1', '2020-11-23 09:27:11', NULL, '1.xx 2.xx', '', '待修改');
INSERT INTO `issuetable` VALUES (9, 311707, '王境泽', '代码bug', '2020-11-23 09:27:56', '代码检查', '一般', 'version1', '2020-11-23 09:27:11', NULL, '1.xx 2.xx', '', '待修改');
INSERT INTO `issuetable` VALUES (10, 311707, '王境泽', '格式bug', '2020-11-23 09:27:56', '格式化', '一般', 'version1', '2020-11-23 09:27:11', NULL, '1.xx 2.xx', '', '待修改');
INSERT INTO `issuetable` VALUES (11, 311707, '王境泽', '界面bug', '2020-11-23 09:27:56', '界面检查', '一般', 'version2', '2020-11-23 09:27:11', NULL, '1.xx 2.xx', '', '待修改');
INSERT INTO `issuetable` VALUES (12, 311707, '王境泽', '接口bug', '2020-11-23 09:27:56', '规范化接口', '严重', 'version1', '2020-11-23 09:27:11', '0000-00-00 00:00:00', '1.xx 2.xx', '解决方案：', '待验证');
INSERT INTO `issuetable` VALUES (13, 311708, '王境泽', '界面布局', '2020-11-23 09:27:56', '界面美化', '严重', 'version2', '2020-11-23 09:27:11', '0000-00-00 00:00:00', '1.xx 2.xx', '解决方案：', '待验证');
INSERT INTO `issuetable` VALUES (14, 311709, '马保国', '界面美化', '2020-11-23 09:27:56', '界面美化', '严重', 'version2', '2020-11-23 09:27:11', '2020-11-24 09:38:42', '1.xx 2.xx', '解决方案：', '已关闭');

-- ----------------------------
-- Table structure for roletable
-- ----------------------------
DROP TABLE IF EXISTS `roletable`;
CREATE TABLE `roletable`  (
  `roleID` int(10) NOT NULL,
  `role` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`roleID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of roletable
-- ----------------------------
INSERT INTO `roletable` VALUES (0, '普通员工');
INSERT INTO `roletable` VALUES (1, '经理');
INSERT INTO `roletable` VALUES (2, '超级Admin');

-- ----------------------------
-- Table structure for usertable
-- ----------------------------
DROP TABLE IF EXISTS `usertable`;
CREATE TABLE `usertable`  (
  `userID` int(200) NOT NULL AUTO_INCREMENT,
  `roleID` int(10) NULL DEFAULT NULL,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userstate` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `registertime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `password` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userID`) USING BTREE,
  INDEX `roleID`(`roleID`) USING BTREE,
  INDEX `userID`(`userID`) USING BTREE,
  CONSTRAINT `roleID` FOREIGN KEY (`roleID`) REFERENCES `roletable` (`roleID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1111111112 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of usertable
-- ----------------------------
INSERT INTO `usertable` VALUES (311700, 2, 'Admin', '2895677675@qq.coom', '激活', '2020-11-17 14:55:10', 'Admin123');
INSERT INTO `usertable` VALUES (311701, 1, '李四', '2578896778@qq.com', '激活', '2020-11-17 15:57:32', '12345678');
INSERT INTO `usertable` VALUES (311702, 1, '王五', '2578896778@qq.com', '激活', '2020-11-17 15:57:32', '12345678');
INSERT INTO `usertable` VALUES (311703, 1, '赵六', '1291344282@qq.com', '激活', '2020-11-17 15:57:32', '12345678');
INSERT INTO `usertable` VALUES (311704, 1, '刘刚', '1231234444@qq.com', '激活', '2020-11-19 17:31:34', '12345678');
INSERT INTO `usertable` VALUES (311705, 0, '彭于晏', '1231231244@qq.com', '激活', '2020-11-17 19:35:00', '12345678');
INSERT INTO `usertable` VALUES (311706, 0, '王境泽', '1231236466@qq.com', '激活', '2020-11-17 20:25:03', '12345678');
INSERT INTO `usertable` VALUES (311707, 0, '何锡全', '1231666666@qq.com', '激活', '2020-11-19 20:16:04', '12345678');
INSERT INTO `usertable` VALUES (311708, 0, '张三', '2765588777@qq.com', '激活', '2020-11-17 14:59:21', '12345678');
INSERT INTO `usertable` VALUES (311709, 0, '马保国', '1291334282@qq.com', '激活', '2020-11-17 19:36:59', '12345678');
INSERT INTO `usertable` VALUES (311710, 0, '三三', '1232444447@qq.com', '激活', '2020-11-18 09:32:28', '12345678');
INSERT INTO `usertable` VALUES (311711, 0, '乌蝇哥', '1231231555@qq.com', '激活', '2020-11-18 10:02:36', '12345678');
INSERT INTO `usertable` VALUES (311712, 0, '彭怀', '1231231243@qq.com', '激活', '2020-11-23 19:35:00', '12345678');
INSERT INTO `usertable` VALUES (311713, 0, '刘盼', '2856969250@qq.com', '激活', '2020-11-23 09:21:15', '88888888');
INSERT INTO `usertable` VALUES (311714, 0, '王坎', '2856969251@qq.com', '激活', '2020-11-23 09:21:59', '88888888');
INSERT INTO `usertable` VALUES (311715, 0, '李口', '2856969252@qq.com', '激活', '2020-11-23 09:22:35', '88888888');
INSERT INTO `usertable` VALUES (311716, 0, '朱星', '2856969253@qq.com', '注销', '2020-11-23 09:23:22', '88888888');

SET FOREIGN_KEY_CHECKS = 1;
