-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: crtvu
-- ------------------------------------------------------
-- Server version	5.7.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_admin`
--

DROP TABLE IF EXISTS `tb_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_admin` (
  `admin_id` int(10) NOT NULL COMMENT '用户id',
  `password` varchar(32) NOT NULL COMMENT '用户密码',
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_arrange_subject`
--

DROP TABLE IF EXISTS `tb_arrange_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_arrange_subject` (
  `subject_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '题目id',
  `open_id` int(10) NOT NULL COMMENT '开设id',
  `teacher_id` int(10) NOT NULL COMMENT '教师工号',
  `name` varchar(50) NOT NULL COMMENT '课题名称',
  `requirement` text NOT NULL COMMENT '课题要求',
  PRIMARY KEY (`subject_id`),
  KEY `open_id` (`open_id`),
  KEY `teacher_id` (`teacher_id`),
  CONSTRAINT `tb_arrange_subject_ibfk_1` FOREIGN KEY (`open_id`) REFERENCES `tb_open` (`open_id`) ON DELETE CASCADE,
  CONSTRAINT `tb_arrange_subject_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `tb_teacher` (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='毕设题目发布表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_arrangement`
--

DROP TABLE IF EXISTS `tb_arrangement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_arrangement` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `open_id` int(10) NOT NULL COMMENT '开设id',
  `teacher_id` int(10) NOT NULL COMMENT '教师工号',
  `classroom` varchar(10) NOT NULL COMMENT '教室名',
  `class_name` varchar(10) NOT NULL COMMENT '班级名',
  `day` int(1) NOT NULL COMMENT '星期',
  `start_week` int(2) NOT NULL COMMENT '开始周',
  `end_week` int(2) NOT NULL COMMENT '结束周',
  `start_time` int(2) NOT NULL COMMENT '开始节次',
  `end_time` int(2) NOT NULL COMMENT '结束节次',
  PRIMARY KEY (`id`),
  KEY `open_id` (`open_id`),
  KEY `teacher_id` (`teacher_id`),
  KEY `classroom` (`classroom`),
  CONSTRAINT `tb_arrangement_ibfk_1` FOREIGN KEY (`open_id`) REFERENCES `tb_open` (`open_id`) ON DELETE CASCADE,
  CONSTRAINT `tb_arrangement_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `tb_teacher` (`teacher_id`),
  CONSTRAINT `tb_arrangement_ibfk_3` FOREIGN KEY (`classroom`) REFERENCES `tb_classroom` (`classroom`)
) ENGINE=InnoDB AUTO_INCREMENT=230 DEFAULT CHARSET=utf8 COMMENT='安排表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_classroom`
--

DROP TABLE IF EXISTS `tb_classroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_classroom` (
  `classroom` varchar(10) NOT NULL COMMENT '教室名',
  `people_num` int(3) NOT NULL COMMENT '教室容量',
  PRIMARY KEY (`classroom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教室表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_confirm_subject`
--

DROP TABLE IF EXISTS `tb_confirm_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_confirm_subject` (
  `student_id` int(10) NOT NULL COMMENT '学号',
  `subject_id` int(10) NOT NULL COMMENT '题目id',
  `grade` float(3,1) DEFAULT NULL COMMENT '成绩',
  `document` text COMMENT '文档',
  PRIMARY KEY (`student_id`,`subject_id`),
  KEY `subject_id` (`subject_id`),
  CONSTRAINT `tb_confirm_subject_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `tb_student` (`student_id`),
  CONSTRAINT `tb_confirm_subject_ibfk_2` FOREIGN KEY (`subject_id`) REFERENCES `tb_arrange_subject` (`subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生定题表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_course`
--

DROP TABLE IF EXISTS `tb_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_course` (
  `course_id` varchar(10) NOT NULL COMMENT '课程id',
  `course_name` varchar(100) NOT NULL COMMENT '课程名称',
  `credit` float(2,1) DEFAULT NULL COMMENT '课程学分',
  `nature` varchar(10) NOT NULL COMMENT '课程性质',
  `department` varchar(20) NOT NULL COMMENT '开设院系',
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课程表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_exam`
--

DROP TABLE IF EXISTS `tb_exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_exam` (
  `open_id` int(10) NOT NULL COMMENT '开设id',
  `time` datetime DEFAULT NULL COMMENT '考试时间',
  `classroom` varchar(10) DEFAULT NULL COMMENT '考试地点',
  `class` varchar(10) DEFAULT NULL COMMENT '班级',
  PRIMARY KEY (`open_id`),
  CONSTRAINT `tb_exam_ibfk_1` FOREIGN KEY (`open_id`) REFERENCES `tb_open` (`open_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考试安排表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_info`
--

DROP TABLE IF EXISTS `tb_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '通知id',
  `title` text NOT NULL COMMENT '通知标题',
  `context` text NOT NULL COMMENT '通知正文',
  `time` datetime NOT NULL COMMENT '通知时间',
  `sender` varchar(50) NOT NULL COMMENT '通知发布者',
  `student_id` int(10) DEFAULT NULL,
  `has_read` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_student_id` (`student_id`),
  CONSTRAINT `fk_student_id` FOREIGN KEY (`student_id`) REFERENCES `tb_student` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='通知表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_open`
--

DROP TABLE IF EXISTS `tb_open`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_open` (
  `open_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '开设id',
  `course_id` varchar(10) NOT NULL COMMENT '课程id',
  `school_year` varchar(10) NOT NULL COMMENT '学年',
  `term` tinyint(1) NOT NULL COMMENT '学期',
  `people_num` int(3) NOT NULL COMMENT '开设人数',
  `start_select_time` datetime DEFAULT NULL COMMENT '开选时间',
  `end_select_time` datetime DEFAULT NULL COMMENT '结选时间',
  PRIMARY KEY (`open_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `tb_open_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `tb_course` (`course_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20170233 DEFAULT CHARSET=utf8 COMMENT='开设表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_select_course`
--

DROP TABLE IF EXISTS `tb_select_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_select_course` (
  `student_id` int(10) NOT NULL COMMENT '学生学号',
  `open_id` int(10) NOT NULL COMMENT '开设id',
  `grade` float(3,1) DEFAULT NULL COMMENT '成绩',
  `evaluate` text COMMENT '评价',
  PRIMARY KEY (`student_id`,`open_id`),
  KEY `open_id` (`open_id`),
  CONSTRAINT `tb_select_course_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `tb_student` (`student_id`),
  CONSTRAINT `tb_select_course_ibfk_2` FOREIGN KEY (`open_id`) REFERENCES `tb_open` (`open_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生选课表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_select_subject`
--

DROP TABLE IF EXISTS `tb_select_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_select_subject` (
  `student_id` int(10) NOT NULL COMMENT '学号',
  `subject_id` int(10) NOT NULL COMMENT '题目id',
  `status` int(1) NOT NULL COMMENT '状态',
  PRIMARY KEY (`student_id`,`subject_id`),
  KEY `subject_id` (`subject_id`),
  CONSTRAINT `tb_select_subject_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `tb_student` (`student_id`),
  CONSTRAINT `tb_select_subject_ibfk_2` FOREIGN KEY (`subject_id`) REFERENCES `tb_arrange_subject` (`subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生候选表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_student`
--

DROP TABLE IF EXISTS `tb_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_student` (
  `student_id` int(10) NOT NULL COMMENT '学生学号',
  `student_name` varchar(50) NOT NULL COMMENT '学生姓名',
  `class_name` varchar(10) NOT NULL COMMENT '学生班级',
  `major` varchar(20) NOT NULL COMMENT '学生专业',
  `password` varchar(32) NOT NULL COMMENT '学生密码',
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_target_major`
--

DROP TABLE IF EXISTS `tb_target_major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_target_major` (
  `open_id` int(10) NOT NULL COMMENT '开设id',
  `major` varchar(20) NOT NULL COMMENT '专业',
  PRIMARY KEY (`open_id`,`major`),
  CONSTRAINT `tb_target_major_ibfk_1` FOREIGN KEY (`open_id`) REFERENCES `tb_open` (`open_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='面向专业表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_teach_course`
--

DROP TABLE IF EXISTS `tb_teach_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_teach_course` (
  `teacher_id` int(10) NOT NULL COMMENT '教师id',
  `open_id` int(10) NOT NULL COMMENT '开设id',
  PRIMARY KEY (`teacher_id`,`open_id`),
  KEY `open_id` (`open_id`),
  CONSTRAINT `tb_teach_course_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `tb_teacher` (`teacher_id`),
  CONSTRAINT `tb_teach_course_ibfk_2` FOREIGN KEY (`open_id`) REFERENCES `tb_open` (`open_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师授课表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_teacher`
--

DROP TABLE IF EXISTS `tb_teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_teacher` (
  `teacher_id` int(10) NOT NULL COMMENT '教师工号',
  `teacher_name` varchar(50) NOT NULL COMMENT '教师姓名',
  `title` varchar(20) NOT NULL COMMENT '教师职称',
  `password` varchar(32) NOT NULL COMMENT '教师密码',
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_term`
--

DROP TABLE IF EXISTS `tb_term`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_term` (
  `school_year` varchar(10) NOT NULL COMMENT '学年',
  `term` tinyint(1) NOT NULL COMMENT '学期',
  `begin_time` datetime NOT NULL COMMENT '开学时间',
  `over_time` datetime NOT NULL COMMENT '放假时间',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='学期表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `vw_major_schedule`
--

DROP TABLE IF EXISTS `vw_major_schedule`;
/*!50001 DROP VIEW IF EXISTS `vw_major_schedule`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `vw_major_schedule` AS SELECT 
 1 AS `open_id`,
 1 AS `major`,
 1 AS `id`,
 1 AS `teacher_id`,
 1 AS `classroom`,
 1 AS `class_name`,
 1 AS `day`,
 1 AS `start_week`,
 1 AS `end_week`,
 1 AS `start_time`,
 1 AS `end_time`,
 1 AS `course_id`,
 1 AS `school_year`,
 1 AS `term`,
 1 AS `people_num`,
 1 AS `start_select_time`,
 1 AS `end_select_time`,
 1 AS `course_name`,
 1 AS `credit`,
 1 AS `nature`,
 1 AS `department`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vw_open`
--

DROP TABLE IF EXISTS `vw_open`;
/*!50001 DROP VIEW IF EXISTS `vw_open`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `vw_open` AS SELECT 
 1 AS `course_id`,
 1 AS `open_id`,
 1 AS `school_year`,
 1 AS `term`,
 1 AS `people_num`,
 1 AS `start_select_time`,
 1 AS `end_select_time`,
 1 AS `course_name`,
 1 AS `credit`,
 1 AS `nature`,
 1 AS `department`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vw_schedule`
--

DROP TABLE IF EXISTS `vw_schedule`;
/*!50001 DROP VIEW IF EXISTS `vw_schedule`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `vw_schedule` AS SELECT 
 1 AS `open_id`,
 1 AS `id`,
 1 AS `teacher_id`,
 1 AS `classroom`,
 1 AS `class_name`,
 1 AS `day`,
 1 AS `start_week`,
 1 AS `end_week`,
 1 AS `start_time`,
 1 AS `end_time`,
 1 AS `course_id`,
 1 AS `school_year`,
 1 AS `term`,
 1 AS `people_num`,
 1 AS `start_select_time`,
 1 AS `end_select_time`,
 1 AS `course_name`,
 1 AS `credit`,
 1 AS `nature`,
 1 AS `department`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vw_student_schedule`
--

DROP TABLE IF EXISTS `vw_student_schedule`;
/*!50001 DROP VIEW IF EXISTS `vw_student_schedule`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `vw_student_schedule` AS SELECT 
 1 AS `class_name`,
 1 AS `student_id`,
 1 AS `student_name`,
 1 AS `major`,
 1 AS `password`,
 1 AS `open_id`,
 1 AS `id`,
 1 AS `teacher_id`,
 1 AS `classroom`,
 1 AS `day`,
 1 AS `start_week`,
 1 AS `end_week`,
 1 AS `start_time`,
 1 AS `end_time`,
 1 AS `course_id`,
 1 AS `school_year`,
 1 AS `term`,
 1 AS `people_num`,
 1 AS `start_select_time`,
 1 AS `end_select_time`,
 1 AS `course_name`,
 1 AS `credit`,
 1 AS `nature`,
 1 AS `department`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vw_teacher_schedule`
--

DROP TABLE IF EXISTS `vw_teacher_schedule`;
/*!50001 DROP VIEW IF EXISTS `vw_teacher_schedule`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `vw_teacher_schedule` AS SELECT 
 1 AS `teacher_id`,
 1 AS `teacher_name`,
 1 AS `title`,
 1 AS `password`,
 1 AS `open_id`,
 1 AS `id`,
 1 AS `classroom`,
 1 AS `class_name`,
 1 AS `day`,
 1 AS `start_week`,
 1 AS `end_week`,
 1 AS `start_time`,
 1 AS `end_time`,
 1 AS `course_id`,
 1 AS `school_year`,
 1 AS `term`,
 1 AS `people_num`,
 1 AS `start_select_time`,
 1 AS `end_select_time`,
 1 AS `course_name`,
 1 AS `credit`,
 1 AS `nature`,
 1 AS `department`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `vw_major_schedule`
--

/*!50001 DROP VIEW IF EXISTS `vw_major_schedule`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vw_major_schedule` AS select `tb_target_major`.`open_id` AS `open_id`,`tb_target_major`.`major` AS `major`,`vw_schedule`.`id` AS `id`,`vw_schedule`.`teacher_id` AS `teacher_id`,`vw_schedule`.`classroom` AS `classroom`,`vw_schedule`.`class_name` AS `class_name`,`vw_schedule`.`day` AS `day`,`vw_schedule`.`start_week` AS `start_week`,`vw_schedule`.`end_week` AS `end_week`,`vw_schedule`.`start_time` AS `start_time`,`vw_schedule`.`end_time` AS `end_time`,`vw_schedule`.`course_id` AS `course_id`,`vw_schedule`.`school_year` AS `school_year`,`vw_schedule`.`term` AS `term`,`vw_schedule`.`people_num` AS `people_num`,`vw_schedule`.`start_select_time` AS `start_select_time`,`vw_schedule`.`end_select_time` AS `end_select_time`,`vw_schedule`.`course_name` AS `course_name`,`vw_schedule`.`credit` AS `credit`,`vw_schedule`.`nature` AS `nature`,`vw_schedule`.`department` AS `department` from (`tb_target_major` join `vw_schedule` on((`tb_target_major`.`open_id` = `vw_schedule`.`open_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vw_open`
--

/*!50001 DROP VIEW IF EXISTS `vw_open`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vw_open` AS select `tb_open`.`course_id` AS `course_id`,`tb_open`.`open_id` AS `open_id`,`tb_open`.`school_year` AS `school_year`,`tb_open`.`term` AS `term`,`tb_open`.`people_num` AS `people_num`,`tb_open`.`start_select_time` AS `start_select_time`,`tb_open`.`end_select_time` AS `end_select_time`,`tb_course`.`course_name` AS `course_name`,`tb_course`.`credit` AS `credit`,`tb_course`.`nature` AS `nature`,`tb_course`.`department` AS `department` from (`tb_open` join `tb_course` on((`tb_open`.`course_id` = `tb_course`.`course_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vw_schedule`
--

/*!50001 DROP VIEW IF EXISTS `vw_schedule`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vw_schedule` AS select `tb_arrangement`.`open_id` AS `open_id`,`tb_arrangement`.`id` AS `id`,`tb_arrangement`.`teacher_id` AS `teacher_id`,`tb_arrangement`.`classroom` AS `classroom`,`tb_arrangement`.`class_name` AS `class_name`,`tb_arrangement`.`day` AS `day`,`tb_arrangement`.`start_week` AS `start_week`,`tb_arrangement`.`end_week` AS `end_week`,`tb_arrangement`.`start_time` AS `start_time`,`tb_arrangement`.`end_time` AS `end_time`,`vw_open`.`course_id` AS `course_id`,`vw_open`.`school_year` AS `school_year`,`vw_open`.`term` AS `term`,`vw_open`.`people_num` AS `people_num`,`vw_open`.`start_select_time` AS `start_select_time`,`vw_open`.`end_select_time` AS `end_select_time`,`vw_open`.`course_name` AS `course_name`,`vw_open`.`credit` AS `credit`,`vw_open`.`nature` AS `nature`,`vw_open`.`department` AS `department` from (`tb_arrangement` join `vw_open` on((`tb_arrangement`.`open_id` = `vw_open`.`open_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vw_student_schedule`
--

/*!50001 DROP VIEW IF EXISTS `vw_student_schedule`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vw_student_schedule` AS select `tb_student`.`class_name` AS `class_name`,`tb_student`.`student_id` AS `student_id`,`tb_student`.`student_name` AS `student_name`,`tb_student`.`major` AS `major`,`tb_student`.`password` AS `password`,`vw_schedule`.`open_id` AS `open_id`,`vw_schedule`.`id` AS `id`,`vw_schedule`.`teacher_id` AS `teacher_id`,`vw_schedule`.`classroom` AS `classroom`,`vw_schedule`.`day` AS `day`,`vw_schedule`.`start_week` AS `start_week`,`vw_schedule`.`end_week` AS `end_week`,`vw_schedule`.`start_time` AS `start_time`,`vw_schedule`.`end_time` AS `end_time`,`vw_schedule`.`course_id` AS `course_id`,`vw_schedule`.`school_year` AS `school_year`,`vw_schedule`.`term` AS `term`,`vw_schedule`.`people_num` AS `people_num`,`vw_schedule`.`start_select_time` AS `start_select_time`,`vw_schedule`.`end_select_time` AS `end_select_time`,`vw_schedule`.`course_name` AS `course_name`,`vw_schedule`.`credit` AS `credit`,`vw_schedule`.`nature` AS `nature`,`vw_schedule`.`department` AS `department` from (`tb_student` join `vw_schedule` on((`tb_student`.`class_name` = `vw_schedule`.`class_name`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vw_teacher_schedule`
--

/*!50001 DROP VIEW IF EXISTS `vw_teacher_schedule`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vw_teacher_schedule` AS select `tb_teacher`.`teacher_id` AS `teacher_id`,`tb_teacher`.`teacher_name` AS `teacher_name`,`tb_teacher`.`title` AS `title`,`tb_teacher`.`password` AS `password`,`vw_schedule`.`open_id` AS `open_id`,`vw_schedule`.`id` AS `id`,`vw_schedule`.`classroom` AS `classroom`,`vw_schedule`.`class_name` AS `class_name`,`vw_schedule`.`day` AS `day`,`vw_schedule`.`start_week` AS `start_week`,`vw_schedule`.`end_week` AS `end_week`,`vw_schedule`.`start_time` AS `start_time`,`vw_schedule`.`end_time` AS `end_time`,`vw_schedule`.`course_id` AS `course_id`,`vw_schedule`.`school_year` AS `school_year`,`vw_schedule`.`term` AS `term`,`vw_schedule`.`people_num` AS `people_num`,`vw_schedule`.`start_select_time` AS `start_select_time`,`vw_schedule`.`end_select_time` AS `end_select_time`,`vw_schedule`.`course_name` AS `course_name`,`vw_schedule`.`credit` AS `credit`,`vw_schedule`.`nature` AS `nature`,`vw_schedule`.`department` AS `department` from (`tb_teacher` join `vw_schedule` on((`tb_teacher`.`teacher_id` = `vw_schedule`.`teacher_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-21 16:37:10
