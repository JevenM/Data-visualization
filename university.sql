/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.24-log : Database - university
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`university` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `university`;

/*Table structure for table `class` */

DROP TABLE IF EXISTS `class`;

CREATE TABLE `class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `className` varchar(50) DEFAULT NULL,
  `boys` int(11) DEFAULT NULL,
  `girls` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `class` */

insert  into `class`(`id`,`className`,`boys`,`girls`) values (1,'19软件1班',26,18),(2,'19软件2班',17,20),(3,'19大数据1班',24,30),(4,'19web工程1班',21,24);

/*Table structure for table `department` */

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `dept_name` varchar(20) NOT NULL,
  `building` varchar(15) DEFAULT NULL,
  `budget` decimal(12,0) DEFAULT NULL,
  PRIMARY KEY (`dept_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `department` */

insert  into `department`(`dept_name`,`building`,`budget`) values ('Biology','Watson','90000');

/*Table structure for table `flyway_schema_history` */

DROP TABLE IF EXISTS `flyway_schema_history`;

CREATE TABLE `flyway_schema_history` (
  `installed_rank` int(11) NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int(11) DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int(11) NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `flyway_schema_history` */

insert  into `flyway_schema_history`(`installed_rank`,`version`,`description`,`type`,`script`,`checksum`,`installed_by`,`installed_on`,`execution_time`,`success`) values (1,'1','<< Flyway Baseline >>','BASELINE','<< Flyway Baseline >>',NULL,'root','2022-11-15 15:06:12',0,1),(2,'1.0.1','create tables','SQL','V1.0.1__create_tables.sql',-1687985131,'root','2022-11-15 15:06:13',495,1),(3,'1.0.2','update tables','SQL','V1.0.2__update_tables.sql',386145748,'root','2022-11-15 15:06:13',1,1),(4,'1.0.3','update table','SQL','V1.0.3__update_table.sql',-884490196,'root','2022-11-15 15:06:13',1,1),(5,'1.0.4','create table','SQL','V1.0.4__create_table.sql',-657603045,'root','2022-11-15 15:06:13',108,1),(6,'1.0.5','add access table','SQL','V1.0.5__add_access_table.sql',531950527,'root','2022-11-15 15:06:13',91,1),(7,'1.0.6','add dict jdbcdriver','SQL','V1.0.6__add_dict_jdbcdriver.sql',1423265422,'root','2022-11-15 15:06:13',3,1),(8,'1.0.7','add dict airmap','SQL','V1.0.7__add_dict_airmap.sql',1334519290,'root','2022-11-15 15:06:13',1,1),(9,'1.0.8','add dict file','SQL','V1.0.8__add_dict_file.sql',-574588614,'root','2022-11-15 15:06:13',12,0);

/*Table structure for table `province` */

DROP TABLE IF EXISTS `province`;

CREATE TABLE `province` (
  `name` varchar(200) DEFAULT NULL,
  `nums` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `province` */

insert  into `province`(`name`,`nums`) values ('上海',100),('北京',300),('云南',23),('安徽',242),('山西',32),('广东',566),('福建',126),('新疆',345),('河北',198),('湖北',253),('甘肃',98),('陕西',103),('黑龙江',155);

/*Table structure for table `stu_score` */

DROP TABLE IF EXISTS `stu_score`;

CREATE TABLE `stu_score` (
  `sid` int(10) NOT NULL,
  `sname` varchar(100) DEFAULT NULL,
  `card` varchar(10) DEFAULT NULL,
  `politics` int(3) DEFAULT NULL,
  `english` int(3) DEFAULT NULL,
  `math` int(3) DEFAULT NULL,
  `computer` int(3) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `stu_score` */

insert  into `stu_score`(`sid`,`sname`,`card`,`politics`,`english`,`math`,`computer`) values (345658,'林德明','1443091860',56,75,120,102),(465123,'王雨晴','1443091861',65,72,124,84);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
