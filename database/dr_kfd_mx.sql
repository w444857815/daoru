# MySQL-Front 5.0  (Build 1.96)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;


# Host: 127.0.0.1    Database: shiro
# ------------------------------------------------------
# Server version 5.5.53

#
# Table structure for table dr_kfd_mx
#

DROP TABLE IF EXISTS `dr_kfd_mx`;
CREATE TABLE `dr_kfd_mx` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `jishu` varchar(255) DEFAULT NULL COMMENT '计数',
  `jl_time` datetime DEFAULT NULL COMMENT '时间',
  `goods_name` varchar(255) DEFAULT NULL COMMENT '品名',
  `goods_guige` varchar(255) DEFAULT NULL COMMENT '规格',
  `goods_unit` varchar(255) DEFAULT NULL COMMENT '单位',
  `goods_num` int(10) DEFAULT NULL COMMENT '数量',
  `shop_name` varchar(255) DEFAULT NULL COMMENT '店名',
  `shop_address` varchar(255) DEFAULT NULL COMMENT '放货地址',
  `shop_lxr` varchar(255) DEFAULT NULL COMMENT '联系人',
  `shop_lxrmobile` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `if_pay` varchar(255) DEFAULT NULL COMMENT '是否付款',
  `if_bills_sh` varchar(255) DEFAULT NULL COMMENT '单据是否收回',
  `shop_property` varchar(255) DEFAULT NULL COMMENT '店铺属性',
  `shop_disappear` varchar(255) DEFAULT NULL COMMENT '是否撤店',
  `shop_zrr` varchar(255) DEFAULT NULL COMMENT '责任人',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `zpff` varchar(255) DEFAULT NULL COMMENT '赠品发放（打火机）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL,
  `delete_flag` int(11) DEFAULT NULL,
  `status` tinyint(2) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1137 DEFAULT CHARSET=utf8;

#
# Dumping data for table dr_kfd_mx
#
LOCK TABLES `dr_kfd_mx` WRITE;
/*!40000 ALTER TABLE `dr_kfd_mx` DISABLE KEYS */;

INSERT INTO `dr_kfd_mx` VALUES (1136,'111','1899-12-29','1111','111','1',1,'1','1','1','1','1','1','1','1','1','1','1','1899-12-30 01:00:00','1899-12-30 01:00:00',0,0);
/*!40000 ALTER TABLE `dr_kfd_mx` ENABLE KEYS */;
UNLOCK TABLES;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
