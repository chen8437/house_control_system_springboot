/*
 Navicat MySQL Data Transfer

 Source Server         : 叶秋蓉的阿里云mysql
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : rm-2ze8ph9ygc9g94hktro.mysql.rds.aliyuncs.com:3306
 Source Schema         : house_control_system

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 30/01/2020 13:59:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for buy_house
-- ----------------------------
DROP TABLE IF EXISTS `buy_house`;
CREATE TABLE `buy_house`  (
  `buy_order` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '单号',
  `buy_statue` int(1) NOT NULL COMMENT '订单状态',
  `buy_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '发布时间',
  `buy_uuid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '求租人',
  `sell_uuid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '租的人',
  `buy_address` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地址',
  `buy_introduction` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '房源描述',
  `buy_price` decimal(10, 0) NOT NULL COMMENT '价格',
  `buy_pick` int(6) NOT NULL COMMENT '点击数',
  `is_delete` int(1) NULL DEFAULT NULL COMMENT '是否逻辑删除1是0否',
  `flag1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留位',
  `flag2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留位',
  `flag3` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '预留位',
  `flag4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留位',
  `flag5` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留位',
  PRIMARY KEY (`buy_order`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of buy_house
-- ----------------------------
INSERT INTO `buy_house` VALUES ('order-buyy-1580204247729zfSGJ28W', 2, '2020-01-28 20:58:23', 'a4e4dd282a7a4ee3967352620bd1dcbe', 'c7eb2705189248279abe4d825042699e', '天津市宁河区芦台镇沿河路', '包水电费物业费宽带费\n交通便利', 6000, 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `buy_house` VALUES ('order-buyy-1580207514945EIW7QeOL', 0, '2020-01-29 12:57:03', 'a4e4dd282a7a4ee3967352620bd1dcbe', 'c7eb2705189248279abe4d825042699e', '湖北省武汉市随意', '哪里都行 我就要去武汉浪', 1000, 1, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `buy_house` VALUES ('order-buyy-1580218822517qN1uLOxg', 0, '2020-01-29 13:24:47', 'a4e4dd282a7a4ee3967352620bd1dcbe', 'a4e4dd282a7a4ee3967352620bd1dcbe', '湖北武汉大学', '我想租大学旁边的房子', 1000, 1, 0, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for question_order
-- ----------------------------
DROP TABLE IF EXISTS `question_order`;
CREATE TABLE `question_order`  (
  `ques_order` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '单号',
  `ques_statue` int(1) NOT NULL COMMENT '订单状态',
  `ques_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '发布时间',
  `ques_address` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '求租房源地址',
  `ques_intruduction` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '求租房源描述',
  `ques_seller` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出售方',
  `ques_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '购买方',
  `is_delete` int(1) NULL DEFAULT NULL COMMENT '是否逻辑删除1是0否',
  `flag1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留位',
  `flag2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留位',
  `flag3` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '预留位',
  `flag4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留位',
  `flag5` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留位',
  PRIMARY KEY (`ques_order`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question_order
-- ----------------------------
INSERT INTO `question_order` VALUES ('order-ques-1580290874786kDrTGDkq', 3, '2020-01-29 18:52:23', '湖北省武汉市某大学', '坑我钱财 拜我财产', '无良商家', NULL, 0, '15122430390', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sell_house
-- ----------------------------
DROP TABLE IF EXISTS `sell_house`;
CREATE TABLE `sell_house`  (
  `sell_order` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '单号',
  `sell_statue` int(1) NOT NULL COMMENT '订单状态',
  `sell_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '发布时间',
  `sell_uuid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '卖房子的人',
  `sell_address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地址',
  `sell_imgs` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `sell_introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '房源描述',
  `sell_price` decimal(10, 0) NOT NULL COMMENT '价格',
  `buy_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '买房uuid',
  `sell_pick` int(6) NULL DEFAULT NULL COMMENT '点击数',
  `is_delete` int(1) NULL DEFAULT NULL COMMENT '是否逻辑删除1是0否',
  `flag1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留位',
  `flag2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留位',
  `flag3` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '预留位',
  `flag4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留位',
  `flag5` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留位',
  PRIMARY KEY (`sell_order`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sell_house
-- ----------------------------
INSERT INTO `sell_house` VALUES ('order-buyy-1580202818000jfvrqFSe', 1, '2020-01-29 21:13:24', 'a4e4dd282a7a4ee3967352620bd1dcbe', '天津市宁河区芦台镇沿河路建设楼', NULL, '交通便利 包括水电费物业费宽带费\n周围有各种娱乐场所', 2000, 'a4e4dd282a7a4ee3967352620bd1dcbe', 1000, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sell_house` VALUES ('order-buyy-1580202903685OsoTmcaN', 1, '2020-01-29 21:13:26', 'a4e4dd282a7a4ee3967352620bd1dcbe', '北京市昌平区霍营华龙苑南里', NULL, '包物业取暖费\n交通便利', 5000, 'a4e4dd282a7a4ee3967352620bd1dcbe', 100, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sell_house` VALUES ('order-buyy-1580203018753SszJu5Fj', 1, '2020-01-29 21:13:29', 'a4e4dd282a7a4ee3967352620bd1dcbe', '天津市天津火车站', NULL, '交通便利 剩下全不管', 1000000, 'a4e4dd282a7a4ee3967352620bd1dcbe', 110, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sell_house` VALUES ('order-buyy-15802040268432uQzenA5', 1, '2020-01-28 17:33:47', 'a4e4dd282a7a4ee3967352620bd1dcbe', '天津市宁河区芦台镇沿河路', NULL, '包水电费 包物业费 交通便利', 6000, 'a4e4dd282a7a4ee3967352620bd1dcbe', 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sell_house` VALUES ('order-sell-15802187517425C77qb8w', 0, '2020-01-29 13:27:52', 'c7eb2705189248279abe4d825042699e', '湖北武汉的房子', NULL, '没人住了 出租', 1000, 'a4e4dd282a7a4ee3967352620bd1dcbe', 0, 0, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uuid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户唯一ID',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录账号',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录密码',
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `roles` int(1) NULL DEFAULT NULL COMMENT '用户角色',
  `introduction` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户描述',
  `avater` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `is_delete` int(1) NULL DEFAULT NULL COMMENT '是否逻辑删除1是0否',
  `flag1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留位',
  `flag2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留位',
  `flag3` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '预留位',
  `flag4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留位',
  `flag5` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留位',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('0108c0512d9e4799ad4ae65c5b4e881f', 'xiaomin', '123456', '肖敏', 1, NULL, 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('a4e4dd282a7a4ee3967352620bd1dcbe', 'qianhuazheng', '123456', '钱华政', 1, '我是钱华政', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('c7eb2705189248279abe4d825042699e', 'zhangxiaoyu', '123456', '张筱雨', 2, '我叫张晓宇', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('d84162ee1ce64d2fb361a4012a6d7d0c', 'yechrom', '123456', '叶秋蓉', 0, '我是一个美男子', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 0, NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
