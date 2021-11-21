/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 8.0.26 : Database - healthiness
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`healthiness` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `healthiness`;

/*Table structure for table `t_checkgroup` */

DROP TABLE IF EXISTS `t_checkgroup`;

CREATE TABLE `t_checkgroup` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `helpCode` varchar(32) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `remark` varchar(128) DEFAULT NULL,
  `attention` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb3;

/*Data for the table `t_checkgroup` */

insert  into `t_checkgroup`(`id`,`code`,`name`,`helpCode`,`sex`,`remark`,`attention`) values (5,'0001','一般检查','YBJC','0','一般检查','无'),(6,'0002','视力色觉','SLSJ','0','视力色觉',NULL),(7,'0003','血常规','XCG','0','血常规',NULL),(8,'0004','尿常规','NCG','0','尿常规',NULL),(9,'0005','肝功三项','GGSX','0','肝功三项',NULL),(10,'0006','肾功三项','NGSX','0','肾功三项',NULL),(11,'0007','血脂四项','XZSX','0','血脂四项',NULL),(12,'0008','心肌酶三项','XJMSX','0','心肌酶三项',NULL),(13,'0009','甲功三项','JGSX','0','甲功三项',NULL),(14,'0010','子宫附件彩超','ZGFJCC','2','子宫附件彩超',NULL),(15,'0011','胆红素三项','DHSSX','0','胆红素三项',NULL);

/*Table structure for table `t_checkgroup_checkitem` */

DROP TABLE IF EXISTS `t_checkgroup_checkitem`;

CREATE TABLE `t_checkgroup_checkitem` (
  `checkgroup_id` int NOT NULL DEFAULT '0',
  `checkitem_id` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`checkgroup_id`,`checkitem_id`),
  KEY `item_id` (`checkitem_id`),
  CONSTRAINT `group_id` FOREIGN KEY (`checkgroup_id`) REFERENCES `t_checkgroup` (`id`),
  CONSTRAINT `item_id` FOREIGN KEY (`checkitem_id`) REFERENCES `t_checkitem` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `t_checkgroup_checkitem` */

insert  into `t_checkgroup_checkitem`(`checkgroup_id`,`checkitem_id`) values (5,28),(5,29),(5,30),(5,31),(5,32),(6,33),(6,34),(6,35),(6,36),(6,37),(7,38),(7,39),(7,40),(7,41),(7,42),(7,43),(7,44),(7,45),(7,46),(7,47),(7,48),(7,49),(7,50),(7,51),(7,52),(7,53),(7,54),(7,55),(7,56),(8,57),(8,58),(8,59),(8,60),(8,61),(8,62),(8,63),(8,64),(8,65),(8,66),(8,67),(8,68),(8,69),(8,70),(8,71),(9,72),(9,73),(9,74),(10,75),(10,76),(10,77),(11,78),(11,79),(11,80),(11,81),(12,82),(12,83),(12,84),(13,85),(13,86),(13,87),(14,88),(14,89),(15,90),(15,91),(15,92);

/*Table structure for table `t_checkitem` */

DROP TABLE IF EXISTS `t_checkitem`;

CREATE TABLE `t_checkitem` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(16) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `age` varchar(32) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `type` char(1) DEFAULT NULL COMMENT '查检项类型,分为检查和检验两种',
  `attention` varchar(128) DEFAULT NULL,
  `remark` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8mb3;

/*Data for the table `t_checkitem` */

insert  into `t_checkitem`(`id`,`code`,`name`,`sex`,`age`,`price`,`type`,`attention`,`remark`) values (28,'0001','身高','0','0-100',5,'1','无','身高'),(29,'0002','体重','0','0-100',5,'1','无','体重'),(30,'0003','体重指数','0','0-100',5,'1','无','体重指数'),(31,'0004','收缩压','0','0-100',5,'1','无','收缩压'),(32,'0005','舒张压','0','0-100',5,'1','无','舒张压'),(33,'0006','裸视力（右）','0','0-100',5,'1','无','裸视力（右）'),(34,'0007','裸视力（左）','0','0-100',5,'1','无','裸视力（左）'),(35,'0008','矫正视力（右）','0','0-100',5,'1','无','矫正视力（右）'),(36,'0009','矫正视力（左）','0','0-100',5,'1','无','矫正视力（左）'),(37,'0010','色觉','0','0-100',5,'1','无','色觉'),(38,'0011','白细胞计数','0','0-100',10,'2','无','白细胞计数'),(39,'0012','红细胞计数','0','0-100',10,'2',NULL,'红细胞计数'),(40,'0013','血红蛋白','0','0-100',10,'2',NULL,'血红蛋白'),(41,'0014','红细胞压积','0','0-100',10,'2',NULL,'红细胞压积'),(42,'0015','平均红细胞体积','0','0-100',10,'2',NULL,'平均红细胞体积'),(43,'0016','平均红细胞血红蛋白含量','0','0-100',10,'2',NULL,'平均红细胞血红蛋白含量'),(44,'0017','平均红细胞血红蛋白浓度','0','0-100',10,'2',NULL,'平均红细胞血红蛋白浓度'),(45,'0018','红细胞分布宽度-变异系数','0','0-100',10,'2',NULL,'红细胞分布宽度-变异系数'),(46,'0019','血小板计数','0','0-100',10,'2',NULL,'血小板计数'),(47,'0020','平均血小板体积','0','0-100',10,'2',NULL,'平均血小板体积'),(48,'0021','血小板分布宽度','0','0-100',10,'2',NULL,'血小板分布宽度'),(49,'0022','淋巴细胞百分比','0','0-100',10,'2',NULL,'淋巴细胞百分比'),(50,'0023','中间细胞百分比','0','0-100',10,'2',NULL,'中间细胞百分比'),(51,'0024','中性粒细胞百分比','0','0-100',10,'2',NULL,'中性粒细胞百分比'),(52,'0025','淋巴细胞绝对值','0','0-100',10,'2',NULL,'淋巴细胞绝对值'),(53,'0026','中间细胞绝对值','0','0-100',10,'2',NULL,'中间细胞绝对值'),(54,'0027','中性粒细胞绝对值','0','0-100',10,'2',NULL,'中性粒细胞绝对值'),(55,'0028','红细胞分布宽度-标准差','0','0-100',10,'2',NULL,'红细胞分布宽度-标准差'),(56,'0029','血小板压积','0','0-100',10,'2',NULL,'血小板压积'),(57,'0030','尿比重','0','0-100',10,'2',NULL,'尿比重'),(58,'0031','尿酸碱度','0','0-100',10,'2',NULL,'尿酸碱度'),(59,'0032','尿白细胞','0','0-100',10,'2',NULL,'尿白细胞'),(60,'0033','尿亚硝酸盐','0','0-100',10,'2',NULL,'尿亚硝酸盐'),(61,'0034','尿蛋白质','0','0-100',10,'2',NULL,'尿蛋白质'),(62,'0035','尿糖','0','0-100',10,'2',NULL,'尿糖'),(63,'0036','尿酮体','0','0-100',10,'2',NULL,'尿酮体'),(64,'0037','尿胆原','0','0-100',10,'2',NULL,'尿胆原'),(65,'0038','尿胆红素','0','0-100',10,'2',NULL,'尿胆红素'),(66,'0039','尿隐血','0','0-100',10,'2',NULL,'尿隐血'),(67,'0040','尿镜检红细胞','0','0-100',10,'2',NULL,'尿镜检红细胞'),(68,'0041','尿镜检白细胞','0','0-100',10,'2',NULL,'尿镜检白细胞'),(69,'0042','上皮细胞','0','0-100',10,'2',NULL,'上皮细胞'),(70,'0043','无机盐类','0','0-100',10,'2',NULL,'无机盐类'),(71,'0044','尿镜检蛋白定性','0','0-100',10,'2',NULL,'尿镜检蛋白定性'),(72,'0045','丙氨酸氨基转移酶','0','0-100',10,'2',NULL,'丙氨酸氨基转移酶'),(73,'0046','天门冬氨酸氨基转移酶','0','0-100',10,'2',NULL,'天门冬氨酸氨基转移酶'),(74,'0047','Y-谷氨酰转移酶','0','0-100',10,'2',NULL,'Y-谷氨酰转移酶'),(75,'0048','尿素','0','0-100',10,'2',NULL,'尿素'),(76,'0049','肌酐','0','0-100',10,'2',NULL,'肌酐'),(77,'0050','尿酸','0','0-100',10,'2',NULL,'尿酸'),(78,'0051','总胆固醇','0','0-100',10,'2',NULL,'总胆固醇'),(79,'0052','甘油三酯','0','0-100',10,'2',NULL,'甘油三酯'),(80,'0053','高密度脂蛋白胆固醇','0','0-100',10,'2',NULL,'高密度脂蛋白胆固醇'),(81,'0054','低密度脂蛋白胆固醇','0','0-100',10,'2',NULL,'低密度脂蛋白胆固醇'),(82,'0055','磷酸肌酸激酶','0','0-100',10,'2',NULL,'磷酸肌酸激酶'),(83,'0056','磷酸肌酸激酶同工酶','0','0-100',10,'2',NULL,'磷酸肌酸激酶同工酶'),(84,'0057','乳酸脱氢酶','0','0-100',10,'2',NULL,'乳酸脱氢酶'),(85,'0058','三碘甲状腺原氨酸','0','0-100',10,'2',NULL,'三碘甲状腺原氨酸'),(86,'0059','甲状腺素','0','0-100',10,'2',NULL,'甲状腺素'),(87,'0060','促甲状腺激素','0','0-100',10,'2',NULL,'促甲状腺激素'),(88,'0061','子宫','2','0-100',10,'2',NULL,'子宫'),(89,'0062','附件','2','0-100',10,'2',NULL,'附件'),(90,'0063','总胆红素','0','0-100',10,'2',NULL,'总胆红素'),(91,'0064','直接胆红素','0','0-100',10,'2',NULL,'直接胆红素'),(92,'0065','间接胆红素','0','0-100',10,'2',NULL,'间接胆红素');

/*Table structure for table `t_examridesult` */

DROP TABLE IF EXISTS `t_examridesult`;

CREATE TABLE `t_examridesult` (
  `id` int NOT NULL AUTO_INCREMENT,
  `orderDate` date DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `phoneNumber` varchar(11) DEFAULT NULL,
  `setmealName` varchar(100) DEFAULT NULL,
  `remark` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;

/*Data for the table `t_examridesult` */

insert  into `t_examridesult`(`id`,`orderDate`,`name`,`phoneNumber`,`setmealName`,`remark`) values (11,'2021-11-14','王鹏','18337723923','入职无忧体检套餐（男女通用）','结果已出'),(13,NULL,NULL,NULL,'入职无忧体检套餐（男女通用）',NULL),(14,NULL,NULL,NULL,'粉红珍爱(女)升级TM12项筛查体检套餐',NULL),(15,NULL,NULL,NULL,'阳光爸妈升级肿瘤12项筛查（男女单人）体检套餐',NULL),(16,NULL,NULL,NULL,'孕前检查套餐（女）-精英版',NULL),(17,NULL,NULL,NULL,'珍爱高端升级肿瘤12项筛查（男女单人）',NULL);

/*Table structure for table `t_member` */

DROP TABLE IF EXISTS `t_member`;

CREATE TABLE `t_member` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fileNumber` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `sex` varchar(8) DEFAULT NULL,
  `idCard` varchar(18) DEFAULT NULL,
  `phoneNumber` varchar(11) DEFAULT NULL,
  `regTime` date DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `remark` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8mb3;

/*Data for the table `t_member` */

insert  into `t_member`(`id`,`fileNumber`,`name`,`sex`,`idCard`,`phoneNumber`,`regTime`,`password`,`email`,`birthday`,`remark`) values (1,NULL,NULL,NULL,NULL,NULL,'2020-09-01',NULL,NULL,NULL,NULL),(2,NULL,NULL,NULL,NULL,NULL,'2020-10-02',NULL,NULL,NULL,NULL),(3,NULL,NULL,NULL,NULL,NULL,'2020-10-01',NULL,NULL,NULL,NULL),(4,NULL,NULL,NULL,NULL,NULL,'2020-10-03',NULL,NULL,NULL,NULL),(5,NULL,NULL,NULL,NULL,NULL,'2020-10-04',NULL,NULL,NULL,NULL),(6,NULL,NULL,NULL,NULL,NULL,'2020-10-05',NULL,NULL,NULL,NULL),(7,NULL,NULL,NULL,NULL,NULL,'2020-11-06',NULL,NULL,NULL,NULL),(8,NULL,NULL,NULL,NULL,NULL,'2020-11-17',NULL,NULL,NULL,NULL),(9,NULL,NULL,NULL,NULL,NULL,'2020-11-18',NULL,NULL,NULL,NULL),(10,NULL,NULL,NULL,NULL,NULL,'2020-11-19',NULL,NULL,NULL,NULL),(11,NULL,NULL,NULL,NULL,NULL,'2020-11-20',NULL,NULL,NULL,NULL),(12,NULL,NULL,NULL,NULL,NULL,'2020-11-21',NULL,NULL,NULL,NULL),(13,NULL,NULL,NULL,NULL,NULL,'2020-12-06',NULL,NULL,NULL,NULL),(14,NULL,NULL,NULL,NULL,NULL,'2020-12-17',NULL,NULL,NULL,NULL),(15,NULL,NULL,NULL,NULL,NULL,'2020-12-18',NULL,NULL,NULL,NULL),(16,NULL,NULL,NULL,NULL,NULL,'2020-12-19',NULL,NULL,NULL,NULL),(17,NULL,NULL,NULL,NULL,NULL,'2020-12-20',NULL,NULL,NULL,NULL),(18,NULL,NULL,NULL,NULL,NULL,'2020-12-21',NULL,NULL,NULL,NULL),(19,NULL,NULL,NULL,NULL,NULL,'2020-12-01',NULL,NULL,NULL,NULL),(82,NULL,'小明','1','123456789000999999','18811679442','2020-09-08',NULL,NULL,NULL,NULL),(83,NULL,'王美丽','1','132333333333333','13412345678','2020-09-11',NULL,NULL,NULL,NULL),(92,NULL,'王鹏','1','411381199901303010','18337723923','2021-11-13',NULL,'1277@qq.com','1999-01-30','结果已出'),(93,NULL,NULL,NULL,NULL,NULL,'2021-01-06',NULL,NULL,NULL,NULL),(94,NULL,NULL,NULL,NULL,NULL,'2021-01-17',NULL,NULL,NULL,NULL),(95,NULL,NULL,NULL,NULL,NULL,'2021-01-18',NULL,NULL,NULL,NULL),(96,NULL,NULL,NULL,NULL,NULL,'2021-01-19',NULL,NULL,NULL,NULL),(97,NULL,NULL,NULL,NULL,NULL,'2021-01-20',NULL,NULL,NULL,NULL),(98,NULL,NULL,NULL,NULL,NULL,'2021-01-21',NULL,NULL,NULL,NULL),(99,NULL,NULL,NULL,NULL,NULL,'2021-01-01',NULL,NULL,NULL,NULL),(100,NULL,NULL,NULL,NULL,NULL,'2021-01-03',NULL,NULL,NULL,NULL),(101,NULL,NULL,NULL,NULL,NULL,'2021-01-04',NULL,NULL,NULL,NULL),(102,NULL,NULL,NULL,NULL,NULL,'2021-01-05',NULL,NULL,NULL,NULL),(103,NULL,NULL,NULL,NULL,NULL,'2021-02-06',NULL,NULL,NULL,NULL),(104,NULL,NULL,NULL,NULL,NULL,'2021-02-17',NULL,NULL,NULL,NULL),(105,NULL,NULL,NULL,NULL,NULL,'2021-02-18',NULL,NULL,NULL,NULL),(106,NULL,NULL,NULL,NULL,NULL,'2021-02-19',NULL,NULL,NULL,NULL),(107,NULL,NULL,NULL,NULL,NULL,'2021-02-20',NULL,NULL,NULL,NULL),(108,NULL,NULL,NULL,NULL,NULL,'2021-02-21',NULL,NULL,NULL,NULL),(109,NULL,NULL,NULL,NULL,NULL,'2021-03-06',NULL,NULL,NULL,NULL),(110,NULL,NULL,NULL,NULL,NULL,'2021-03-17',NULL,NULL,NULL,NULL),(111,NULL,NULL,NULL,NULL,NULL,'2021-03-18',NULL,NULL,NULL,NULL),(112,NULL,NULL,NULL,NULL,NULL,'2021-03-19',NULL,NULL,NULL,NULL),(113,NULL,NULL,NULL,NULL,NULL,'2021-03-20',NULL,NULL,NULL,NULL),(114,NULL,NULL,NULL,NULL,NULL,'2021-03-21',NULL,NULL,NULL,NULL),(115,NULL,NULL,NULL,NULL,NULL,'2021-03-01',NULL,NULL,NULL,NULL),(116,NULL,NULL,NULL,NULL,NULL,'2021-04-06',NULL,NULL,NULL,NULL),(117,NULL,NULL,NULL,NULL,NULL,'2021-04-17',NULL,NULL,NULL,NULL),(118,NULL,NULL,NULL,NULL,NULL,'2021-04-18',NULL,NULL,NULL,NULL),(119,NULL,NULL,NULL,NULL,NULL,'2021-05-06',NULL,NULL,NULL,NULL),(120,NULL,NULL,NULL,NULL,NULL,'2021-05-17',NULL,NULL,NULL,NULL),(121,NULL,NULL,NULL,NULL,NULL,'2021-06-06',NULL,NULL,NULL,NULL),(122,NULL,NULL,NULL,NULL,NULL,'2021-06-17',NULL,NULL,NULL,NULL),(123,NULL,NULL,NULL,NULL,NULL,'2021-07-06',NULL,NULL,NULL,NULL),(124,NULL,NULL,NULL,NULL,NULL,'2021-07-17',NULL,NULL,NULL,NULL);

/*Table structure for table `t_menu` */

DROP TABLE IF EXISTS `t_menu`;

CREATE TABLE `t_menu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `linkUrl` varchar(128) DEFAULT NULL,
  `path` varchar(128) DEFAULT NULL,
  `priority` int DEFAULT NULL,
  `icon` varchar(64) DEFAULT NULL,
  `description` varchar(128) DEFAULT NULL,
  `parentMenuId` int DEFAULT NULL,
  `level` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_13` (`parentMenuId`),
  CONSTRAINT `FK_Reference_13` FOREIGN KEY (`parentMenuId`) REFERENCES `t_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb3;

/*Data for the table `t_menu` */

insert  into `t_menu`(`id`,`name`,`linkUrl`,`path`,`priority`,`icon`,`description`,`parentMenuId`,`level`) values (1,'会员管理',NULL,'2',1,'fa-user-md',NULL,NULL,1),(2,'会员档案','member.html','/2-1',1,NULL,NULL,1,2),(3,'体检上传',NULL,'/2-2',2,NULL,NULL,1,2),(4,'会员统计',NULL,'/2-3',3,NULL,NULL,1,2),(5,'预约管理',NULL,'3',2,'fa-tty',NULL,NULL,1),(6,'预约列表','ordersettinglist.html','/3-1',1,NULL,NULL,5,2),(7,'预约设置','ordersetting.html','/3-2',2,NULL,NULL,5,2),(8,'套餐管理','setmeal.html','/3-3',3,NULL,NULL,5,2),(9,'检查组管理','checkgroup.html','/3-4',4,NULL,NULL,5,2),(10,'检查项管理','checkitem.html','/3-5',5,NULL,NULL,5,2),(11,'健康评估',NULL,'4',3,'fa-stethoscope',NULL,NULL,1),(12,'中医体质辨识',NULL,'/4-1',1,NULL,NULL,11,2),(13,'统计分析',NULL,'5',4,'fa-heartbeat',NULL,NULL,1),(14,'会员数量','report_member.html','/5-1',1,NULL,NULL,13,2),(15,'系统设置',NULL,'6',5,'fa-users',NULL,NULL,1),(16,'菜单管理','menu.html','/6-1',1,NULL,NULL,15,2),(17,'权限管理','permission.html','/6-2',2,NULL,NULL,15,2),(18,'角色管理','role.html','/6-3',3,NULL,NULL,15,2),(19,'用户管理','user.html','/6-4',4,NULL,NULL,15,2),(20,'套餐占比','report_setmeal.html','/5-2',2,NULL,NULL,13,2),(21,'运营数据','report_business.html','/5-3',3,NULL,NULL,13,2);

/*Table structure for table `t_order` */

DROP TABLE IF EXISTS `t_order`;

CREATE TABLE `t_order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `member_id` int DEFAULT NULL COMMENT '员会id',
  `orderDate` date DEFAULT NULL COMMENT '约预日期',
  `orderType` varchar(8) DEFAULT NULL COMMENT '约预类型 电话预约/微信预约',
  `orderStatus` varchar(8) DEFAULT NULL COMMENT '预约状态（是否到诊）',
  `setmeal_id` int DEFAULT NULL COMMENT '餐套id',
  `remark` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `key_member_id` (`member_id`),
  KEY `key_setmeal_id` (`setmeal_id`),
  CONSTRAINT `key_member_id` FOREIGN KEY (`member_id`) REFERENCES `t_member` (`id`),
  CONSTRAINT `key_setmeal_id` FOREIGN KEY (`setmeal_id`) REFERENCES `t_setmeal` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;

/*Data for the table `t_order` */

insert  into `t_order`(`id`,`member_id`,`orderDate`,`orderType`,`orderStatus`,`setmeal_id`,`remark`) values (11,92,'2021-11-14','微信预约','已到诊',5,'结果已出'),(13,1,NULL,NULL,NULL,5,NULL),(14,2,NULL,NULL,NULL,6,NULL),(15,3,NULL,NULL,NULL,7,NULL),(16,4,NULL,NULL,NULL,9,NULL),(17,5,NULL,NULL,NULL,11,NULL);

/*Table structure for table `t_ordersetting` */

DROP TABLE IF EXISTS `t_ordersetting`;

CREATE TABLE `t_ordersetting` (
  `id` int NOT NULL AUTO_INCREMENT,
  `orderDate` date DEFAULT NULL COMMENT '约预日期',
  `number` int DEFAULT NULL COMMENT '可预约人数',
  `reservations` int DEFAULT NULL COMMENT '已预约人数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8mb3;

/*Data for the table `t_ordersetting` */

insert  into `t_ordersetting`(`id`,`orderDate`,`number`,`reservations`) values (72,'2021-11-10',100,100),(73,'2021-11-11',100,100),(74,'2021-11-12',100,100),(75,'2021-11-13',100,0),(76,'2021-11-14',100,1),(78,'2021-11-16',100,0),(79,'2021-11-17',100,0),(80,'2021-11-18',200,0),(81,'2021-11-19',200,0),(82,'2021-11-20',300,0),(83,'2021-11-21',100,0),(84,'2021-11-22',100,0),(85,'2021-11-23',100,0),(86,'2021-11-24',100,0),(87,'2021-11-25',100,0),(88,'2021-11-26',100,0),(89,'2021-11-27',100,0),(90,'2021-11-28',100,0),(91,'2021-11-29',100,0),(92,'2021-11-30',100,0),(93,'2021-12-01',100,0),(94,'2021-12-02',100,0),(95,'2021-12-03',100,0),(96,'2021-12-04',100,0),(97,'2021-12-05',100,0),(98,'2021-12-06',100,0),(99,'2021-12-07',100,0),(100,'2021-12-08',100,0),(101,'2021-12-09',100,0),(102,'2021-12-10',100,0),(103,'2021-12-11',100,0),(104,'2021-12-12',100,0),(105,'2021-12-13',100,0),(106,'2021-12-14',100,0),(107,'2021-12-15',100,0),(108,'2021-12-16',100,0),(109,'2021-12-17',100,0),(110,'2021-12-18',100,0),(111,'2021-12-19',100,0),(112,'2021-12-20',100,0),(113,'2021-12-21',100,0),(114,'2021-12-22',100,0),(115,'2021-12-23',100,0),(116,'2021-12-24',100,0),(117,'2021-12-25',100,0),(118,'2021-12-26',100,0),(119,'2021-12-27',100,0),(120,'2021-12-28',100,0),(121,'2021-12-29',100,0),(122,'2021-12-30',100,0),(123,'2021-12-31',100,0);

/*Table structure for table `t_ordersettinglist` */

DROP TABLE IF EXISTS `t_ordersettinglist`;

CREATE TABLE `t_ordersettinglist` (
  `id` int NOT NULL AUTO_INCREMENT,
  `orderDate` date DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `phoneNumber` varchar(11) DEFAULT NULL,
  `orderType` varchar(8) DEFAULT NULL,
  `orderStatus` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;

/*Data for the table `t_ordersettinglist` */

insert  into `t_ordersettinglist`(`id`,`orderDate`,`name`,`phoneNumber`,`orderType`,`orderStatus`) values (11,'2021-11-14','王鹏','18337723923','微信预约','已到诊'),(13,NULL,NULL,NULL,NULL,NULL),(14,NULL,NULL,NULL,NULL,NULL),(15,NULL,NULL,NULL,NULL,NULL),(16,NULL,NULL,NULL,NULL,NULL),(17,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `t_permission` */

DROP TABLE IF EXISTS `t_permission`;

CREATE TABLE `t_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `keyword` varchar(64) DEFAULT NULL,
  `description` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb3;

/*Data for the table `t_permission` */

insert  into `t_permission`(`id`,`name`,`keyword`,`description`) values (1,'新增检查项','CHECKITEM_ADD',NULL),(2,'删除检查项','CHECKITEM_DELETE',NULL),(3,'编辑检查项','CHECKITEM_EDIT',NULL),(4,'查询检查项','CHECKITEM_QUERY',NULL),(5,'新增检查组','CHECKGROUP_ADD',NULL),(6,'删除检查组','CHECKGROUP_DELETE',NULL),(7,'编辑检查组','CHECKGROUP_EDIT',NULL),(8,'查询检查组','CHECKGROUP_QUERY',NULL),(9,'新增套餐','SETMEAL_ADD',NULL),(10,'删除套餐','SETMEAL_DELETE',NULL),(11,'编辑套餐','SETMEAL_EDIT',NULL),(12,'查询套餐','SETMEAL_QUERY',NULL),(13,'预约设置','ORDERSETTING',NULL),(14,'查看统计报表','REPORT_VIEW',NULL),(15,'新增菜单','MENU_ADD',NULL),(16,'删除菜单','MENU_DELETE',NULL),(17,'编辑菜单','MENU_EDIT',NULL),(18,'查询菜单','MENU_QUERY',NULL),(19,'新增角色','ROLE_ADD',NULL),(20,'删除角色','ROLE_DELETE',NULL),(21,'编辑角色','ROLE_EDIT',NULL),(22,'查询角色','ROLE_QUERY',NULL),(23,'新增用户','USER_ADD',NULL),(24,'删除用户','USER_DELETE',NULL),(25,'编辑用户','USER_EDIT',NULL),(26,'查询用户','USER_QUERY',NULL);

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `keyword` varchar(64) DEFAULT NULL,
  `description` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

/*Data for the table `t_role` */

insert  into `t_role`(`id`,`name`,`keyword`,`description`) values (1,'系统管理员','ROLE_ADMIN',NULL),(2,'健康管理师','ROLE_HEALTH_MANAGER',NULL);

/*Table structure for table `t_role_menu` */

DROP TABLE IF EXISTS `t_role_menu`;

CREATE TABLE `t_role_menu` (
  `role_id` int NOT NULL,
  `menu_id` int NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`),
  KEY `FK_Reference_10` (`menu_id`),
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`menu_id`) REFERENCES `t_menu` (`id`),
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `t_role_menu` */

insert  into `t_role_menu`(`role_id`,`menu_id`) values (1,1),(2,1),(1,2),(2,2),(1,3),(2,3),(1,4),(2,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,21);

/*Table structure for table `t_role_permission` */

DROP TABLE IF EXISTS `t_role_permission`;

CREATE TABLE `t_role_permission` (
  `role_id` int NOT NULL,
  `permission_id` int NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`),
  KEY `FK_Reference_12` (`permission_id`),
  CONSTRAINT `FK_Reference_11` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`),
  CONSTRAINT `FK_Reference_12` FOREIGN KEY (`permission_id`) REFERENCES `t_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `t_role_permission` */

insert  into `t_role_permission`(`role_id`,`permission_id`) values (1,1),(2,1),(1,2),(2,2),(1,3),(2,3),(1,4),(2,4),(1,5),(2,5),(1,6),(2,6),(1,7),(2,7),(1,8),(2,8),(1,9),(2,9),(1,10),(2,10),(1,11),(2,11),(1,12),(2,12),(1,13),(2,13),(1,14),(2,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,21),(1,22),(1,23),(1,24),(1,25),(1,26);

/*Table structure for table `t_setmeal` */

DROP TABLE IF EXISTS `t_setmeal`;

CREATE TABLE `t_setmeal` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `code` varchar(8) DEFAULT NULL,
  `helpCode` varchar(16) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `age` varchar(32) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `remark` varchar(128) DEFAULT NULL,
  `attention` varchar(128) DEFAULT NULL,
  `img` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb3;

/*Data for the table `t_setmeal` */

insert  into `t_setmeal`(`id`,`name`,`code`,`helpCode`,`sex`,`age`,`price`,`remark`,`attention`,`img`) values (5,'入职无忧体检套餐（男女通用）','0001','RZTJ','0','18-60',300,'入职体检套餐',NULL,'03a36073-a140-4942-9b9b-712cecb144901.jpg'),(6,'粉红珍爱(女)升级TM12项筛查体检套餐','0002','FHZA','2','18-60',1200,'本套餐针对宫颈(TCT检查、HPV乳头瘤病毒筛查）、乳腺（彩超，癌抗125），甲状腺（彩超，甲功验血）以及胸片，血常规肝功等有全面检查，非常适合女性全面疾病筛查使用。',NULL,'ac3b5a4d-33a5-4f37-bd49-99e06ce17d202.jpg'),(7,'阳光爸妈升级肿瘤12项筛查（男女单人）体检套餐','0003','YGBM','0','55-100',1400,'本套餐主要针对常见肿瘤筛查，肝肾、颈动脉、脑血栓、颅内血流筛查，以及风湿、颈椎、骨密度检查。',NULL,'ee7dcf84-8a3a-4ab9-b981-9c5d272fd58d3.jpg'),(9,'孕前检查套餐（女）-精英版','0005','YQJCNV','2','18-50',1500,'孕前检查套餐（女）-精英版',NULL,'3bd90d2c-4e82-42a1-a401-882c88b06a1a2.jpg'),(11,'珍爱高端升级肿瘤12项筛查（男女单人）','0006','ZAGD','0','14-20',2400,'本套餐是一款针对生化五项检查，心，肝，胆，胃，甲状腺，颈椎，肺功能，脑部检查（经颅多普勒）以及癌症筛查，适合大众人群体检的套餐。',NULL,'e373b2eb-0e50-4e95-a09b-03f2c1ee1d351.jpg');

/*Table structure for table `t_setmeal_checkgroup` */

DROP TABLE IF EXISTS `t_setmeal_checkgroup`;

CREATE TABLE `t_setmeal_checkgroup` (
  `setmeal_id` int NOT NULL DEFAULT '0',
  `checkgroup_id` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`setmeal_id`,`checkgroup_id`),
  KEY `checkgroup_key` (`checkgroup_id`),
  CONSTRAINT `checkgroup_key` FOREIGN KEY (`checkgroup_id`) REFERENCES `t_checkgroup` (`id`),
  CONSTRAINT `setmeal_key` FOREIGN KEY (`setmeal_id`) REFERENCES `t_setmeal` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `t_setmeal_checkgroup` */

insert  into `t_setmeal_checkgroup`(`setmeal_id`,`checkgroup_id`) values (5,5),(6,5),(7,5),(9,5),(11,5),(5,6),(6,6),(7,6),(9,6),(11,6),(5,7),(6,7),(7,7),(9,7),(11,7),(5,8),(6,8),(7,8),(9,8),(11,8),(5,9),(6,9),(7,9),(9,9),(5,10),(6,10),(7,10),(9,10),(5,11),(6,11),(7,11),(9,11),(6,12),(7,12),(9,12),(6,13),(7,13),(9,13),(6,14),(7,14),(9,14),(6,15),(7,15),(9,15);

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `birthday` date DEFAULT NULL,
  `gender` varchar(1) DEFAULT NULL,
  `username` varchar(32) DEFAULT NULL,
  `password` varchar(256) DEFAULT NULL,
  `remark` varchar(32) DEFAULT NULL,
  `station` varchar(1) DEFAULT NULL,
  `telephone` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`birthday`,`gender`,`username`,`password`,`remark`,`station`,`telephone`) values (1,NULL,NULL,'admin','$2a$10$u/BcsUUqZNWUxdmDhbnoeeobJy6IBsL1Gn/S0dMxI2RbSgnMKJ.4a',NULL,NULL,NULL),(2,NULL,NULL,'xiaoming','$2a$10$3xW2nBjwBM3rx1LoYprVsemNri5bvxeOd/QfmO7UDFQhW2HRHLi.C',NULL,NULL,NULL),(3,NULL,NULL,'test','$2a$10$zYJRscVUgHX1wqwu90WereuTmIg6h/JGirGG4SWBsZ60wVPCgtF8W',NULL,NULL,NULL);

/*Table structure for table `t_user_role` */

DROP TABLE IF EXISTS `t_user_role`;

CREATE TABLE `t_user_role` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_Reference_8` (`role_id`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `t_user_role` */

insert  into `t_user_role`(`user_id`,`role_id`) values (1,1),(2,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
