/*
 Navicat Premium Data Transfer

 Source Server         : 本地虚拟服务器
 Source Server Type    : MySQL
 Source Server Version : 50531
 Source Host           : 192.168.22.128:3306
 Source Schema         : market

 Target Server Type    : MySQL
 Target Server Version : 50531
 File Encoding         : 65001

 Date: 27/06/2020 22:07:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `userid` int(11) NOT NULL COMMENT '用户编号',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  PRIMARY KEY (`userid`, `username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (1, 'admin', 'admin');
INSERT INTO `account` VALUES (2, 'user', '123456');
INSERT INTO `account` VALUES (5, '123', '123456');
INSERT INTO `account` VALUES (15, 'test', '123456');
INSERT INTO `account` VALUES (16, 'test1', '123456');
INSERT INTO `account` VALUES (17, 'test2', '123456');

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员编号',
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录密码',
  `phone` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员联系方式',
  `tname` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员真实姓名',
  `logintime` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员最后登录时间',
  `lv` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '2' COMMENT '账户级别(1:boss, 2:管理员)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', 'admin', '18713827231', '拓', '2020-06-05 09:27:09', '1');
INSERT INTO `admin` VALUES (2, 'Zero', '111111', '110', '张三', '2020-04-23 14:16:08', '2');
INSERT INTO `admin` VALUES (3, 'Mr.chen', '123456', '123456', '李四', NULL, '2');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论编号',
  `pid` int(11) NOT NULL COMMENT '商品编号',
  `uid` int(11) NOT NULL COMMENT '用户编号',
  `text` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论文本',
  `time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发布时间',
  `status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '评论状态(1:正常, 2:屏蔽)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `pro_com`(`pid`) USING BTREE,
  INDEX `usr_com`(`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 7, 1, '价格可以面谈', '2020-04-16 12:52:53', '1');
INSERT INTO `comment` VALUES (2, 2, 5, '1111', '2020-04-23 14:03:23', '1');
INSERT INTO `comment` VALUES (3, 13, 1, '有需要的速速联系~', '2020-04-26 19:45:26', '1');
INSERT INTO `comment` VALUES (4, 1, 2, '特别好看', '2020-04-26 21:38:04', '1');
INSERT INTO `comment` VALUES (5, 1, 2, '呵呵', '2020-04-26 21:39:44', '1');
INSERT INTO `comment` VALUES (6, 1, 2, '嗯', '2020-04-26 21:40:13', '2');
INSERT INTO `comment` VALUES (7, 13, 2, '加入购物车', '2020-04-26 21:50:14', '1');
INSERT INTO `comment` VALUES (8, 12, 2, '离散数学', '2020-04-26 21:52:26', '1');
INSERT INTO `comment` VALUES (9, 9, 2, '1', '2020-05-24 00:01:44', '1');
INSERT INTO `comment` VALUES (10, 12, 1, '离散', '2020-06-02 10:43:07', '1');
INSERT INTO `comment` VALUES (11, 18, 1, '便宜点\n', '2020-06-04 13:30:57', '1');
INSERT INTO `comment` VALUES (12, 18, 1, '便宜100有人要没', '2020-06-04 13:32:27', '1');
INSERT INTO `comment` VALUES (13, 18, 1, '面谈也行\n', '2020-06-04 13:32:48', '1');

-- ----------------------------
-- Table structure for fc
-- ----------------------------
DROP TABLE IF EXISTS `fc`;
CREATE TABLE `fc`  (
  `id` int(11) NOT NULL COMMENT '一级分类编号',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '一级分类名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of fc
-- ----------------------------
INSERT INTO `fc` VALUES (1, '书籍资料');
INSERT INTO `fc` VALUES (2, '学习用品');
INSERT INTO `fc` VALUES (3, '电子设备');
INSERT INTO `fc` VALUES (4, '日常家居');
INSERT INTO `fc` VALUES (5, '校园出行');
INSERT INTO `fc` VALUES (6, '体育用品');
INSERT INTO `fc` VALUES (7, '电子配件');
INSERT INTO `fc` VALUES (8, '其他');

-- ----------------------------
-- Table structure for order_product
-- ----------------------------
DROP TABLE IF EXISTS `order_product`;
CREATE TABLE `order_product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '数据编号',
  `oid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单编号',
  `pid` int(50) NOT NULL COMMENT '商品编号',
  `status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '商品状态(1:未发货，2：已发货，3：已收货，4：已申请退款，5：已退款，6：退款失败',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of order_product
-- ----------------------------
INSERT INTO `order_product` VALUES (1, '381ABECC-438C-4B5F-8CC6-C6CC4623E802', 16, '1');
INSERT INTO `order_product` VALUES (2, '381ABECC-438C-4B5F-8CC6-C6CC4623E802', 6, '1');
INSERT INTO `order_product` VALUES (3, '160B5CCD-DFA3-48D0-8790-C7F632E18DD8', 11, '1');
INSERT INTO `order_product` VALUES (4, '00C61EE3-F135-4AED-96E7-28C6461E9FA1', 32, '1');
INSERT INTO `order_product` VALUES (5, '00C61EE3-F135-4AED-96E7-28C6461E9FA1', 19, '1');
INSERT INTO `order_product` VALUES (6, '50610B36-A8A3-4E48-90AB-D7458B6F982D', 18, '1');
INSERT INTO `order_product` VALUES (7, '7BD6B182-1ECA-4980-80F6-A34E56B9D7D2', 3, '1');
INSERT INTO `order_product` VALUES (8, '7722A5C2-70DA-47B2-8324-685B13CCA8EE', 1, '1');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '数据编号',
  `oid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单编号',
  `cid` int(11) NOT NULL COMMENT '创建人编号',
  `createperson` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `createtime` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间',
  `pay` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '支付状态（1：未支付，2：已支付）',
  `paytime` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付时间',
  `productnumber` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '商品数量',
  `producttotal` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '商品总价',
  `consignee` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货人',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货人联系方式',
  `mail` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货人邮箱',
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货地址',
  `remark` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1, '381ABECC-438C-4B5F-8CC6-C6CC4623E802', 0, 'user', '2020-06-10 16:13:26', '2', '2020-06-10 16:13:31', '2', '10', 'user', '18713827231', '2459550727@qq.com', '河北唐山', NULL);
INSERT INTO `orders` VALUES (2, '160B5CCD-DFA3-48D0-8790-C7F632E18DD8', 0, 'user', '2020-06-10 16:14:13', '2', '2020-06-10 16:14:15', '1', '5', 'user', '18713827231', '2459550727@qq.com', '河北唐山', NULL);
INSERT INTO `orders` VALUES (3, '00C61EE3-F135-4AED-96E7-28C6461E9FA1', 0, 'user', '2020-06-10 16:18:30', '2', '2020-06-10 16:18:33', '2', '31', 'user', '18713827231', '2459550727@qq.com', '河北唐山', NULL);
INSERT INTO `orders` VALUES (4, '50610B36-A8A3-4E48-90AB-D7458B6F982D', 2, 'user', '2020-06-10 17:16:19', '2', '2020-06-10 17:16:22', '1', '500', 'user', '18713827231', '2459550727@qq.com', '河北唐山', NULL);
INSERT INTO `orders` VALUES (5, '7BD6B182-1ECA-4980-80F6-A34E56B9D7D2', 2, 'user', '2020-06-10 17:17:28', '2', '2020-06-10 17:17:30', '1', '10', 'user', '18713827231', '2459550727@qq.com', '河北唐山', NULL);
INSERT INTO `orders` VALUES (6, '7722A5C2-70DA-47B2-8324-685B13CCA8EE', 2, 'user', '2020-06-10 17:22:42', '2', '2020-06-10 17:22:44', '1', '20', 'user', '18713827231', '2459550727@qq.com', '河北唐山', NULL);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '商品编号',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `details` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品描述',
  `images` varchar(9000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品图片',
  `clicks` int(20) NOT NULL DEFAULT 0 COMMENT '商品点击量',
  `creattime` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间',
  `originalprice` decimal(10, 2) NOT NULL COMMENT '商品原价',
  `currentprice` decimal(10, 2) NOT NULL COMMENT '商品现价',
  `deal` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品交易方式',
  `address` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品交易地址',
  `comments` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '商品留言数',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '商品状态（1：在线，2：下线)',
  `payed` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '交易状态（1：未售，2：已售）',
  `sid` int(10) NOT NULL COMMENT '商品类别编号',
  `uid` int(10) NOT NULL COMMENT '发布人编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, '东野圭吾：白夜行', '我一直走在白夜里，从来就没有太阳，所以不怕失去。', '[\"http://192.168.22.128/group1/M00/00/03/wKgWgF6XHACAE0S7AAEE_ggE8P8010.jpg\",\"http://192.168.22.128/group1/M00/00/03/wKgWgF6XHACAW0PrAADQrDQMmsM893.jpg\",\"http://192.168.22.128/group1/M00/00/03/wKgWgF6XHACAa-_9AADr-wnjZyo528.jpg\",\"http://192.168.22.128/group1/M00/00/03/wKgWgF6XHACAEZ-aAAD_ZvYCQFc408.jpg\",\"http://192.168.22.128/group1/M00/00/03/wKgWgF6XHACARUuEAAB8ftuOfPQ601.jpg\"]', 14, '2020-04-15 22:29:52', 41.00, 20.00, '线上交易', '唐山学院北校区A大厅', '3', '2', '2', 1, 1);
INSERT INTO `product` VALUES (2, '东野圭吾：解忧杂货店', '胡歌、王俊凯、刘昊然倾情推荐，易中天、王安忆盛赞，畅销天王东野圭吾长篇小说代表作，登顶北大图书馆预约榜，5年销量超900万册，豆瓣40万人点评8.5高分', '[\"http://192.168.22.128/group1/M00/00/05/wKgWgF7YwiiAWH-9AAGi1Y56rTs156.jpg\",\"http://192.168.22.128/group1/M00/00/05/wKgWgF7YwiiAFXQUAAHxEIKI8mM774.jpg\",\"http://192.168.22.128/group1/M00/00/05/wKgWgF7YwiiABAT4AAJyzw2WeNU926.jpg\",\"http://192.168.22.128/group1/M00/00/05/wKgWgF7YwiiAWhrjAAEl7ImD__U450.jpg\",\"http://192.168.22.128/group1/M00/00/05/wKgWgF7YwkKANvMJAACV_KV84MY195.jpg\"]', 12, '2020-04-15 22:41:35', 50.00, 10.00, '线上交易', '唐山学院南校区图书馆门前', '1', '1', '1', 1, 1);
INSERT INTO `product` VALUES (3, '局外人（诺奖得主作品）', '你格格不入吗？一定要读局外人！收录《局外人》治愈之作《流放与王国》，随书附赠精美书签！对于世界，我永远是个陌生人！', '[\"http://192.168.22.128/group1/M00/00/03/wKgWgF6XH4uAClJdAADeBIKcXAQ471.jpg\",\"http://192.168.22.128/group1/M00/00/03/wKgWgF6XH4uAT-ziAADeBIKcXAQ031.jpg\",\"http://192.168.22.128/group1/M00/00/03/wKgWgF6XH4uAOu30AADJmbaBYFs602.jpg\",\"http://192.168.22.128/group1/M00/00/03/wKgWgF6XH4uAAb1yAAEGUVwKj80917.jpg\",\"http://192.168.22.128/group1/M00/00/03/wKgWgF6XH4uACRbhAAEqXV9I_pU727.jpg\"]', 24, '2020-04-15 22:50:48', 26.00, 10.00, '线上交易', '唐山学院北校区B大厅', '0', '2', '2', 1, 1);
INSERT INTO `product` VALUES (4, '你想活出怎样的人生', '无论世界变得如何混乱与残酷，我们也能决定以怎样的姿态好好活着。', '[\"http://192.168.22.128/group1/M00/00/03/wKgWgF6X1aqABBcAAAHHEv5ODAU738.jpg\",\"http://192.168.22.128/group1/M00/00/03/wKgWgF6X1aqAf6xSAAFnzbllhmE284.jpg\",\"http://192.168.22.128/group1/M00/00/03/wKgWgF6X1aqAOXQmAAHvEgFf2Qo701.jpg\",\"http://192.168.22.128/group1/M00/00/03/wKgWgF6X1aqAcfTJAAJNtuO9TCs216.jpg\",\"http://192.168.22.128/group1/M00/00/03/wKgWgF6X1auAQoAIAAC-7hmgl80376.jpg\"]', 29, '2020-04-16 11:44:22', 4940.00, 20.00, '线上交易', '唐山学院北校区图书馆', '0', '1', '1', 1, 1);
INSERT INTO `product` VALUES (5, '平凡的世界+活着新版', '余华 中国现当代小说经典著作 当代文学作品畅销书籍 新华书店正版', '[\"http://192.168.22.128/group1/M00/00/03/wKgWgF6X1tSAb2TmAAFjv_i-10M127.png\",\"http://192.168.22.128/group1/M00/00/03/wKgWgF6X1tSAdztEAAA3ly1z6AA044.jpg\"]', 4, '2020-04-16 11:49:19', 7450.00, 30.00, '线上交易', '唐山学院东校区图书馆', '0', '1', '1', 1, 1);
INSERT INTO `product` VALUES (6, '人间失格', '畅销百万册，日本文学大家“太宰治”灵魂深处的生命绝唱巨著。', '[\"http://192.168.22.128/group1/M00/00/03/wKgWgF6X14WAEvo_AACE0vCfoyI557.jpg\",\"http://192.168.22.128/group1/M00/00/03/wKgWgF6X14WAEwrXAAFqcP4QnjQ261.jpg\",\"http://192.168.22.128/group1/M00/00/03/wKgWgF6X14WADdyFAAFS5HNwbko298.jpg\",\"http://192.168.22.128/group1/M00/00/03/wKgWgF6X14WAMFtGAAFjSXJCuyc326.jpg\",\"http://192.168.22.128/group1/M00/00/03/wKgWgF6X14WAWf_-AAE_qxl7pcw613.jpg\"]', 15, '2020-04-16 11:54:18', 19.00, 5.00, '线下交易', '唐山学院西校区', '0', '2', '2', 1, 1);
INSERT INTO `product` VALUES (7, '我是猫', '夏目漱石长篇代表作 以猫眼看世界，探索明治时代知识分子心灵的经典之作 穿越百年和风，追寻大师夏目漱石', '[\"http://192.168.22.128/group1/M00/00/03/wKgWgF6X2imAYZIsAAGau9DhUmI338.jpg\",\"http://192.168.22.128/group1/M00/00/03/wKgWgF6X2imAELDKAAEyrsloAeg060.jpg\",\"http://192.168.22.128/group1/M00/00/03/wKgWgF6X2imAcvyPAACI4_51Pdc236.jpg\",\"http://192.168.22.128/group1/M00/00/03/wKgWgF6X2imAQKrGAACOMwmHXIs046.jpg\",\"http://192.168.22.128/group1/M00/00/03/wKgWgF6X2imAZ0EkAACKDrRVSkg507.jpg\"]', 10, '2020-04-16 11:54:18', 40.00, 10.00, '线上交易', '唐山学院北校区', '1', '1', '1', 1, 1);
INSERT INTO `product` VALUES (8, '肖申克的救赎', '惊悚题材永远的王者！畅销霸主！同名电影获奥斯卡奖七项提名，被誉为电影*完美影片之一！', '[\"http://192.168.22.128/group1/M00/00/03/wKgWgF6X2tqARUoQAAF8tdIO4K0255.jpg\",\"http://192.168.22.128/group1/M00/00/04/wKgWgF6X2tqAcLb3AAEfzAT5u2s951.jpg\",\"http://192.168.22.128/group1/M00/00/04/wKgWgF6X2tqAPwo-AAGAhASFu_c269.jpg\",\"http://192.168.22.128/group1/M00/00/04/wKgWgF6X2tqAQll8AAGFRwQ9t8w935.jpg\"]', 6, '2020-04-16 12:08:25', 36.00, 10.00, '线上交易', '唐山学院南校区', '0', '1', '1', 1, 1);
INSERT INTO `product` VALUES (9, '小王子', '畅销380万册新版，法国“圣埃克苏佩里基金会”官方认可简体中文【果麦经典】', '[\"http://192.168.22.128/group1/M00/00/04/wKgWgF6X22yAQtPBAAFeIRXMJ-w068.jpg\",\"http://192.168.22.128/group1/M00/00/04/wKgWgF6X22yADRxzAAFwZ91eF-k146.jpg\",\"http://192.168.22.128/group1/M00/00/04/wKgWgF6X22yANp9jAAFZjvZ_p_k246.jpg\",\"http://192.168.22.128/group1/M00/00/04/wKgWgF6X22yAJtAiAAFvhKXnRSY076.jpg\",\"http://192.168.22.128/group1/M00/00/04/wKgWgF6X22yARyO9AAHOYW2Gc5E172.jpg\"]', 13, '2020-04-16 12:08:25', 39.00, 10.00, '线上交易', '唐山学院西校区', '1', '1', '1', 1, 1);
INSERT INTO `product` VALUES (10, '月亮与六便士', '寻找心灵家园的思考 毛姆著 现实主义文学代表作 世界名著畅销书上篇 小说书籍 HD', '[\"http://192.168.22.128/group1/M00/00/04/wKgWgF6X3x2APFGBAACvyLieBOI549.jpg\"]', 39, '2020-04-16 12:28:30', 20.00, 5.00, '线上交易', '唐山学院南校区', '0', '1', '1', 1, 1);
INSERT INTO `product` VALUES (11, '高等数学（第七版 上册）', '《高等数学（第七版 上册）》分上、下两册出版，上册包括函数与极限、导数与微分、微分中值定理与导数的应用、不定积分、定积分及其应用、微分方程等内容，书末还附有二阶和三阶行列式简介、基本初等函数的图形、几', '[\"http://192.168.22.128/group1/M00/00/04/wKgWgF6YF8iAVkhbAAEUAWlejQI546.jpg\",\"http://192.168.22.128/group1/M00/00/04/wKgWgF6YF8iAAqGoAABLnS2ZruM247.jpg\",\"http://192.168.22.128/group1/M00/00/04/wKgWgF6YF8iAWptHAAB1Qy7YE04899.jpg\"]', 13, '2020-04-16 16:28:13', 38.00, 5.00, '线上交易', '唐山学院南校区', '0', '2', '2', 2, 1);
INSERT INTO `product` VALUES (12, '离散数学学习指导与习题解析', '本书是教育部高等学校“十一五”规划教材《离散数学》（第2版）的配套辅导教学参考书。本书分为数理逻辑、集合论、代数结构、组合数学、图论、初等数论六个部分. 每部分按照章对相关知识点进行了全面的总结，并对', '[\"http://192.168.22.128/group1/M00/00/04/wKgWgF6YGHSAdAIwAACxAceEZEs413.jpg\",\"http://192.168.22.128/group1/M00/00/04/wKgWgF6YGHSAKDUNAAFG4Ri82lY832.jpg\",\"http://192.168.22.128/group1/M00/00/04/wKgWgF6YGHSAW0uGAADapZy3KPY029.jpg\"]', 20, '2020-04-16 16:31:48', 28.00, 5.00, '线上交易', '唐山学院北校区', '2', '1', '1', 2, 1);
INSERT INTO `product` VALUES (13, '毛东思想和中国特色社会主义理论', '马克思主义理论研究和建设工程重点教材:思想道德修养与法律基础(修订版)是中宣部、组织专家学者修订编写的“马克思主义理论研究和建设工程重点教材”。本书充分反映了去年以来我们党在理论和实践上的创新成果，特', '[\"http://192.168.22.128/group1/M00/00/04/wKgWgF6YGQSADn6DAAFWCPxRNfs964.jpg\"]', 25, '2020-04-16 16:31:48', 28.00, 5.00, '线上交易', '唐山学院南校区', '2', '1', '1', 2, 1);
INSERT INTO `product` VALUES (14, '数据结构题集（C语言版）', '《清华大学计算机系列教材：数据结构题集（C语言版）》由严蔚敏和吴伟民编著出版的数据结构系列教材有C和Pascal两种描述语言的版本。这本题集是与《数据结构》（C语言版）（清华大学出版社）配套编写的，习', '[\"http://192.168.22.128/group1/M00/00/04/wKgWgF6YGWSAA7bIAAAwureyc5E943.jpg\"]', 6, '2020-04-16 16:31:48', 22.00, 5.00, '线上交易', '唐山学院北校区', '0', '1', '1', 2, 1);
INSERT INTO `product` VALUES (15, '工程数学 线性代数 第六版', '李娜主编的《工程数学线性代数<同济第六版>同 步辅导及习题全解(新版)/高校经典教材同步辅导丛 书/九章丛书》是与高等教育出版社出版，同济大学 数学系编的《工程数学·线性代数》(第六版)一书配 套的同', '[\"http://192.168.22.128/group1/M00/00/04/wKgWgF6YGeOAJHw8AAD_AJnHKmM534.jpg\",\"http://192.168.22.128/group1/M00/00/04/wKgWgF6YGeOALVeLAABhP2bexh4786.jpg\",\"http://192.168.22.128/group1/M00/00/04/wKgWgF6YGeOADfIxAAB5fjpyIl8577.jpg\"]', 8, '2020-04-16 16:31:48', 29.00, 8.00, '线上交易', '唐山学院', '0', '1', '1', 2, 1);
INSERT INTO `product` VALUES (16, '中国近现代史纲要2018版', '本书分为从鸦片战争到五四运动前夜(1840-1919)、从五四运动到新中国成立(1919-1949)、从新中国成立到社会主义现代化建设新时期(1949-2018)三部分内容。', '[\"http://192.168.22.128/group1/M00/00/04/wKgWgF6YGnKAdl4KAADg1lGDmBc548.png\"]', 4, '2020-04-16 16:40:50', 44.00, 5.00, '线上交易', '唐山学院南校区', '0', '2', '2', 2, 1);
INSERT INTO `product` VALUES (17, 'Apple iPhone 11', '128GB 黑色 移动联通电信4G手机  双卡双待', '[\"http://192.168.22.128/group1/M00/00/04/wKgWgF6YHWOAEEQeAAEGq-EXink417.jpg\",\"http://192.168.22.128/group1/M00/00/04/wKgWgF6YHWOAae0YAACvMz2dm-Y160.jpg\",\"http://192.168.22.128/group1/M00/00/04/wKgWgF6YHWOAQ9y1AAEtiBTHRYw938.jpg\"]', 7, '2020-04-16 16:51:08', 5999.00, 100.00, '线上交易', '唐山学院南校区', '0', '1', '1', 14, 1);
INSERT INTO `product` VALUES (18, '福运鸽新款新国标电动车', '48V锂电池双人电单代步车 炫酷黑 配官网可验-20A超威或天-能电池跑95公里', '[\"http://192.168.22.128/group1/M00/00/04/wKgWgF6YHf2AS5-PAAD6BtBTP_c183.jpg\"]', 11, '2020-04-16 16:56:43', 1396.00, 500.00, '线上交易', '唐山学院北校区', '3', '2', '2', 31, 1);
INSERT INTO `product` VALUES (19, '折叠床上懒人笔记本电脑桌', '家用可升降折叠小桌子卧室小型懒人架托床上书桌 A8L加大版', '[\"http://192.168.22.128/group1/M00/00/04/wKgWgF6YHu2AYRz5AAC7u5Zazyk093.jpg\",\"http://192.168.22.128/group1/M00/00/04/wKgWgF6YHu2AUWBkAAEKI42wz_Y246.jpg\",\"http://192.168.22.128/group1/M00/00/04/wKgWgF6YHu2ANE1cAACsbSMI_9g653.jpg\",\"http://192.168.22.128/group1/M00/00/04/wKgWgF6YHu2AN9JVAACcUe_W7K8713.jpg\"]', 5, '2020-04-16 16:58:02', 129.00, 30.00, '线上交易', '唐山学院北校区', '0', '2', '2', 20, 1);
INSERT INTO `product` VALUES (20, '联想小新Pro13锐龙版', '锐龙标压处理器性能更强劲，16G双通道内存响应更快速，超窄边框，“真”全面屏！', '[\"http://192.168.22.128/group1/M00/00/04/wKgWgF6ZJsyAHHMgAACJriDbKz4195.jpg\",\"http://192.168.22.128/group1/M00/00/04/wKgWgF6ZJsyAG6kOAACDL-TuQms388.jpg\",\"http://192.168.22.128/group1/M00/00/04/wKgWgF6ZJsyAM7k6AAB_-_V_YPY103.jpg\",\"http://192.168.22.128/group1/M00/00/04/wKgWgF6ZJsyAVvOXAAAnegI2gfw562.jpg\",\"http://192.168.22.128/group1/M00/00/04/wKgWgF6ZJsyAKgieAAB6Ni32X-c815.jpg\"]', 6, '2020-04-17 11:44:23', 4999.00, 1000.00, '线上交易', '唐山学院北校区', '0', '1', '1', 13, 1);
INSERT INTO `product` VALUES (21, '惠普 Z系列ZBook15G6-61PA', '惠普（HP）Z系列ZBook15G6-61PA 15.6英寸 设计本笔记本 E-2286M/64G/2T/RTX3000 6G独显/W10P/4K/3年保修', '[\"http://192.168.22.128/group1/M00/00/04/wKgWgF6ZbTCASNHMAAGLFCzKnzs883.jpg\",\"http://192.168.22.128/group1/M00/00/04/wKgWgF6ZbTCAbsfWAAEkj7RJmTA360.jpg\",\"http://192.168.22.128/group1/M00/00/04/wKgWgF6ZbTCANKLBAAD0B_96JNU048.jpg\",\"http://192.168.22.128/group1/M00/00/04/wKgWgF6ZbTCAD4d3AAGq4Js14c8195.jpg\",\"http://192.168.22.128/group1/M00/00/04/wKgWgF6ZbTCAYrCGAABWPfjiL_U870.jpg\"]', 9, '2020-04-17 16:46:55', 39599.00, 5000.00, '线上交易', '唐山学院南校区', '0', '1', '1', 13, 1);
INSERT INTO `product` VALUES (22, '山地自行车', '山浦 山地自行车男女成人公路赛车网红学生变速单车 高配辐条白黑 26寸21速', '[\"http://192.168.22.128/group1/M00/00/04/wKgWgF6ZeuSAVXXeAAC_ZtOlJPg786.jpg\",\"http://192.168.22.128/group1/M00/00/04/wKgWgF6ZeuSAC5ahAACw1KB78RY256.jpg\",\"http://192.168.22.128/group1/M00/00/04/wKgWgF6ZeuSAZaFtAADMcGFNFE0184.jpg\",\"http://192.168.22.128/group1/M00/00/04/wKgWgF6ZeuSAaK3VAADIj1TO3QE957.jpg\"]', 49, '2020-04-17 17:41:04', 275.00, 50.00, '线下交易', '唐山学院南校区', '0', '1', '1', 30, 1);
INSERT INTO `product` VALUES (23, 'test', '阿斯顿发', '[\"http://192.168.22.128/group1/M00/00/04/wKgWgF6hMTyAYCvMAAe95L2GSjA811.jpg\",\"http://192.168.22.128/group1/M00/00/04/wKgWgF6hMTyAQWV-AAe6S66joO8467.jpg\",\"http://192.168.22.128/group1/M00/00/04/wKgWgF6hMTyAUFAAAAnHrP6yUAI765.jpg\"]', 7, '2020-04-23 14:09:06', 50000.00, 2200.00, '线上交易', '河北唐山', '0', '1', '1', 30, 5);
INSERT INTO `product` VALUES (25, '解忧杂货店', '文学经典', '[\"http://192.168.22.128/group1/M00/00/05/wKgWgF7ZdSCAQeBOAAGi1Y56rTs588.jpg\",\"http://192.168.22.128/group1/M00/00/05/wKgWgF7ZdSCAU_BtAAHxEIKI8mM231.jpg\",\"http://192.168.22.128/group1/M00/00/05/wKgWgF7ZdSCAUo-SAAJyzw2WeNU011.jpg\",\"http://192.168.22.128/group1/M00/00/05/wKgWgF7ZdSCAXAgHAAEl7ImD__U026.jpg\",\"http://192.168.22.128/group1/M00/00/05/wKgWgF7ZdSCAB0LDAACV_KV84MY363.jpg\"]', 4, '2020-06-05 06:25:57', 30.00, 12.00, '线上交易', '图书馆', '0', '1', '1', 1, 2);
INSERT INTO `product` VALUES (26, '白夜行', '文学经典', '[\"http://192.168.22.128/group1/M00/00/05/wKgWgF7ZdXWATCbWAAEE_ggE8P8521.jpg\",\"http://192.168.22.128/group1/M00/00/05/wKgWgF7ZdXWACLcNAADQrDQMmsM132.jpg\",\"http://192.168.22.128/group1/M00/00/05/wKgWgF7ZdXWAeK6GAADr-wnjZyo811.jpg\",\"http://192.168.22.128/group1/M00/00/05/wKgWgF7ZdXWADHv0AAD_ZvYCQFc039.jpg\",\"http://192.168.22.128/group1/M00/00/05/wKgWgF7ZdXWAJfDfAAB8ftuOfPQ980.jpg\"]', 4, '2020-06-05 06:27:22', 50.00, 11.00, '线上交易', '唐山', '0', '1', '1', 1, 2);
INSERT INTO `product` VALUES (27, '高数', '高等数学上册', '[\"http://192.168.22.128/group1/M00/00/05/wKgWgF7ZdiqAQd6iAAEUAWlejQI531.jpg\",\"http://192.168.22.128/group1/M00/00/05/wKgWgF7ZdiqAZVxSAABLnS2ZruM686.jpg\",\"http://192.168.22.128/group1/M00/00/05/wKgWgF7ZdiqAdDHxAAB1Qy7YE04997.jpg\"]', 4, '2020-06-05 06:30:19', 30.00, 5.00, '线上交易', '唐山', '0', '1', '1', 2, 2);
INSERT INTO `product` VALUES (28, '线性代数', '教材课本', '[\"http://192.168.22.128/group1/M00/00/05/wKgWgF7ZezuAWmhKAAD_AJnHKmM803.jpg\",\"http://192.168.22.128/group1/M00/00/05/wKgWgF7ZezuAesf3AABhP2bexh4276.jpg\",\"http://192.168.22.128/group1/M00/00/05/wKgWgF7ZezuAXCkYAAB5fjpyIl8158.jpg\"]', 5, '2020-06-05 06:51:26', 20.00, 5.00, '线上交易', '唐山', '0', '1', '1', 2, 1);
INSERT INTO `product` VALUES (29, '中国近现代史', '教材课本', '[\"http://192.168.22.128/group1/M00/00/05/wKgWgF7Ze_2AAvKVAADg1lGDmBc595.png\"]', 2, '2020-06-05 06:55:13', 30.00, 10.00, '线上交易', '唐山', '0', '1', '1', 2, 1);
INSERT INTO `product` VALUES (30, '小王子与六便士', '1', '[\"http://192.168.22.128/group1/M00/00/05/wKgWgF7ZfJKAIXqiAACvyLieBOI975.jpg\"]', 3, '2020-06-05 06:57:10', 10.00, 10.00, '线上交易', '1', '0', '1', '1', 1, 2);
INSERT INTO `product` VALUES (31, '肖申克的救赎', '2', '[\"http://192.168.22.128/group1/M00/00/05/wKgWgF7ZfQWAOuoXAAF8tdIO4K0765.jpg\",\"http://192.168.22.128/group1/M00/00/05/wKgWgF7ZfQWAdyEoAAEfzAT5u2s970.jpg\",\"http://192.168.22.128/group1/M00/00/05/wKgWgF7ZfQWAZCqtAAGAhASFu_c116.jpg\"]', 6, '2020-06-05 06:57:10', 12.00, 12.00, '线上交易', '121', '0', '1', '1', 1, 2);
INSERT INTO `product` VALUES (32, '钢笔', '1', '[\"http://192.168.22.128/group1/M00/00/05/wKgWgF7ZgviAKaTCAAAJeXYNfzY608.jpg\",\"http://192.168.22.128/group1/M00/00/05/wKgWgF7ZgviAEC9wAAAHYunSM-Y724.jpg\",\"http://192.168.22.128/group1/M00/00/05/wKgWgF7ZgviAeNN4AAAHdaQeFmM889.jpg\",\"http://192.168.22.128/group1/M00/00/05/wKgWgF7ZgviAYxkgAAAOokoOB6o891.jpg\",\"http://192.168.22.128/group1/M00/00/05/wKgWgF7ZgviAWQj8AAARiFcUu38973.jpg\"]', 3, '2020-06-05 07:02:51', 2.00, 1.00, '线上交易', '1', '0', '2', '2', 6, 1);
INSERT INTO `product` VALUES (33, '笔记本', '记事本', '[\"http://192.168.22.128/group1/M00/00/05/wKgWgF7Zm5CASsH4AAAKvzz2Hck439.jpg\",\"http://192.168.22.128/group1/M00/00/05/wKgWgF7Zm5CAAGAzAAAJ6Ekgx3c926.jpg\",\"http://192.168.22.128/group1/M00/00/05/wKgWgF7Zm5CAVXnIAAAPTxm7Jvw644.jpg\",\"http://192.168.22.128/group1/M00/00/05/wKgWgF7Zm5CAbh8BAAAOGzejQ3c073.jpg\",\"http://192.168.22.128/group1/M00/00/05/wKgWgF7Zm5CASDGQAAANJdl6WY4457.jpg\"]', 3, '2020-06-05 09:09:47', 30.00, 12.00, '线上交易', '唐山学院', '0', '1', '1', 11, 17);

-- ----------------------------
-- Table structure for recommend
-- ----------------------------
DROP TABLE IF EXISTS `recommend`;
CREATE TABLE `recommend`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '数据编号',
  `pid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品编号',
  `status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '当前状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of recommend
-- ----------------------------
INSERT INTO `recommend` VALUES (4, '6', '1');
INSERT INTO `recommend` VALUES (5, '7', '1');
INSERT INTO `recommend` VALUES (6, '10', '1');
INSERT INTO `recommend` VALUES (7, '12', '1');
INSERT INTO `recommend` VALUES (8, '17', '1');
INSERT INTO `recommend` VALUES (9, '14', '1');
INSERT INTO `recommend` VALUES (10, '19', '1');
INSERT INTO `recommend` VALUES (11, '3', '1');
INSERT INTO `recommend` VALUES (12, '2', '1');
INSERT INTO `recommend` VALUES (13, '8', '1');

-- ----------------------------
-- Table structure for sc
-- ----------------------------
DROP TABLE IF EXISTS `sc`;
CREATE TABLE `sc`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '二级分类编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '二级分类名称',
  `fcid` int(11) NOT NULL COMMENT '一级分类编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 49 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sc
-- ----------------------------
INSERT INTO `sc` VALUES (1, '畅销小说', 1);
INSERT INTO `sc` VALUES (2, '教材教辅', 1);
INSERT INTO `sc` VALUES (3, '学习考试', 1);
INSERT INTO `sc` VALUES (4, '经管励志', 1);
INSERT INTO `sc` VALUES (5, '文学文艺', 1);
INSERT INTO `sc` VALUES (6, '钢笔', 2);
INSERT INTO `sc` VALUES (7, '签字笔', 2);
INSERT INTO `sc` VALUES (11, '记事本', 2);
INSERT INTO `sc` VALUES (12, '作图尺规', 2);
INSERT INTO `sc` VALUES (13, '电脑', 3);
INSERT INTO `sc` VALUES (14, '手机', 3);
INSERT INTO `sc` VALUES (15, '耳机', 3);
INSERT INTO `sc` VALUES (16, '键盘', 3);
INSERT INTO `sc` VALUES (17, 'iPad', 3);
INSERT INTO `sc` VALUES (18, '相机', 3);
INSERT INTO `sc` VALUES (19, '智能手环', 3);
INSERT INTO `sc` VALUES (20, '书桌', 4);
INSERT INTO `sc` VALUES (21, '书架', 4);
INSERT INTO `sc` VALUES (22, '灯具', 4);
INSERT INTO `sc` VALUES (23, '插座', 4);
INSERT INTO `sc` VALUES (24, '手提箱', 4);
INSERT INTO `sc` VALUES (25, '收纳箱', 4);
INSERT INTO `sc` VALUES (26, '双肩包', 4);
INSERT INTO `sc` VALUES (27, '手提包', 4);
INSERT INTO `sc` VALUES (28, '雨伞', 4);
INSERT INTO `sc` VALUES (29, '自行车', 5);
INSERT INTO `sc` VALUES (30, '山地车', 5);
INSERT INTO `sc` VALUES (31, '电动车', 5);
INSERT INTO `sc` VALUES (32, '平衡车', 5);
INSERT INTO `sc` VALUES (33, '电动滑板车', 5);
INSERT INTO `sc` VALUES (34, '滑板', 5);
INSERT INTO `sc` VALUES (35, '轮滑鞋', 5);
INSERT INTO `sc` VALUES (36, '足球', 6);
INSERT INTO `sc` VALUES (37, '篮球', 6);
INSERT INTO `sc` VALUES (38, '乒乓球', 6);
INSERT INTO `sc` VALUES (39, '羽毛球', 6);
INSERT INTO `sc` VALUES (40, '网球', 6);
INSERT INTO `sc` VALUES (41, '棋牌', 6);
INSERT INTO `sc` VALUES (42, '移动电源', 7);
INSERT INTO `sc` VALUES (43, '手机壳', 7);
INSERT INTO `sc` VALUES (44, '数据线', 7);
INSERT INTO `sc` VALUES (45, '手机支架', 7);
INSERT INTO `sc` VALUES (46, '自拍杆', 7);
INSERT INTO `sc` VALUES (47, '接口转换器', 7);
INSERT INTO `sc` VALUES (48, '其他', 8);

-- ----------------------------
-- Table structure for statisData
-- ----------------------------
DROP TABLE IF EXISTS `statisData`;
CREATE TABLE `statisData`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '数据编号',
  `dates` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录日期',
  `clickNum` int(200) UNSIGNED NOT NULL DEFAULT 1 COMMENT '网站点击量',
  `visitNum` int(200) UNSIGNED NOT NULL DEFAULT 1 COMMENT '网站访问量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 64 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of statisData
-- ----------------------------
INSERT INTO `statisData` VALUES (1, '2020-01-20', 1, 2);
INSERT INTO `statisData` VALUES (2, '2020-02-14', 23, 1);
INSERT INTO `statisData` VALUES (31, '2020-03-30', 172, 20);
INSERT INTO `statisData` VALUES (32, '2020-03-20', 1, 1);
INSERT INTO `statisData` VALUES (33, '2020-04-05', 24, 8);
INSERT INTO `statisData` VALUES (34, '2020-04-12', 37, 19);
INSERT INTO `statisData` VALUES (35, '2020-04-14', 30, 24);
INSERT INTO `statisData` VALUES (36, '2020-04-15', 28, 13);
INSERT INTO `statisData` VALUES (37, '2020-04-16', 94, 47);
INSERT INTO `statisData` VALUES (38, '2020-04-17', 17, 9);
INSERT INTO `statisData` VALUES (39, '2020-04-22', 24, 10);
INSERT INTO `statisData` VALUES (40, '2020-04-23', 65, 34);
INSERT INTO `statisData` VALUES (41, '2020-04-26', 172, 45);
INSERT INTO `statisData` VALUES (42, '2020-04-27', 104, 29);
INSERT INTO `statisData` VALUES (46, '2020-04-28', 28, 6);
INSERT INTO `statisData` VALUES (47, '2020-04-29', 11, 5);
INSERT INTO `statisData` VALUES (48, '2020-04-30', 3, 2);
INSERT INTO `statisData` VALUES (49, '2020-05-01', 2, 2);
INSERT INTO `statisData` VALUES (50, '2020-05-03', 6, 4);
INSERT INTO `statisData` VALUES (51, '2020-05-04', 36, 6);
INSERT INTO `statisData` VALUES (52, '2020-05-05', 35, 1);
INSERT INTO `statisData` VALUES (53, '2020-05-15', 21, 1);
INSERT INTO `statisData` VALUES (54, '2020-05-17', 86, 1);
INSERT INTO `statisData` VALUES (55, '2020-05-19', 28, 1);
INSERT INTO `statisData` VALUES (56, '2020-05-21', 26, 1);
INSERT INTO `statisData` VALUES (57, '2020-05-23', 23, 1);
INSERT INTO `statisData` VALUES (58, '2020-05-24', 18, 1);
INSERT INTO `statisData` VALUES (59, '2020-06-02', 68, 1);
INSERT INTO `statisData` VALUES (60, '2020-06-03', 76, 1);
INSERT INTO `statisData` VALUES (61, '2020-06-04', 250, 18);
INSERT INTO `statisData` VALUES (62, '2020-06-05', 218, 5);
INSERT INTO `statisData` VALUES (63, '2020-06-10', 67, 3);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `userimgpath` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像链接',
  `phonenumber` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户手机号码',
  `mail` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户邮箱地址',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户家庭住址',
  `birthday` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户出生日期',
  `sex` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '男' COMMENT '用户性别',
  PRIMARY KEY (`id`, `username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'http://192.168.22.128/group1/M00/00/05/wKgWgF7Y_EKAa-qTAAJyzw2WeNU705.jpg', '18713827231', '2459550727@qq.com', '陕西西安周至哑柏', '1998-09-07', '男');
INSERT INTO `user` VALUES (2, 'user', 'http://192.168.22.128/group1/M00/00/04/wKgWgF6gWi2AZU79AAC8x4pHAoQ374.jpg', '18713827231', '2459550727@qq.com', '河北唐山', '2015-04-15', '女');
INSERT INTO `user` VALUES (5, '123', 'http://192.168.22.128/group1/M00/00/04/wKgWgF6hMP2AO0KqAAC8x4pHAoQ182.jpg', '18713827231', '2459550727@qq.com', '河北唐山', '2020-04-07', '女');
INSERT INTO `user` VALUES (15, 'test', NULL, '18713827231', '2459550727@qq.com', '河北唐山', '2020-06-10', '男');
INSERT INTO `user` VALUES (16, 'test1', 'http://192.168.22.128/group1/M00/00/05/wKgWgF7Y_VWAWpDmAAEl7ImD__U214.jpg', '18713827231', '2459550727@qq.com', 'asdfasdf', '2020-06-09', '男');
INSERT INTO `user` VALUES (17, 'test2', NULL, '18713827231', '2459550727@qq.com', '河北111', '2020-06-16', '男');

-- ----------------------------
-- Table structure for usercart
-- ----------------------------
DROP TABLE IF EXISTS `usercart`;
CREATE TABLE `usercart`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '数据编号',
  `uid` int(11) NOT NULL COMMENT '用户编号',
  `pid` int(11) NOT NULL COMMENT '商品编号',
  `status` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '当前状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of usercart
-- ----------------------------
INSERT INTO `usercart` VALUES (1, 5, 2, '1');

-- ----------------------------
-- Table structure for userlove
-- ----------------------------
DROP TABLE IF EXISTS `userlove`;
CREATE TABLE `userlove`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '数据编号',
  `uid` int(11) NOT NULL COMMENT '用户编号',
  `pid` int(11) NOT NULL COMMENT '商品编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of userlove
-- ----------------------------
INSERT INTO `userlove` VALUES (4, 5, 3);
INSERT INTO `userlove` VALUES (5, 5, 5);
INSERT INTO `userlove` VALUES (6, 5, 6);
INSERT INTO `userlove` VALUES (10, 2, 16);
INSERT INTO `userlove` VALUES (11, 2, 6);
INSERT INTO `userlove` VALUES (17, 2, 17);

SET FOREIGN_KEY_CHECKS = 1;
