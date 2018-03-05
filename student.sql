CREATE SCHEMA `student` ;

CREATE TABLE `student`.`t_catalog` (
  `id` INT NOT NULL COMMENT 'id',
  `parentid` INT NULL COMMENT '父级栏目id',
  `name` VARCHAR(45) NULL COMMENT '栏目名',
  `url` VARCHAR(45) NULL COMMENT '对应模块名',
  PRIMARY KEY (`id`))
  COMMENT = '栏目表';
ALTER TABLE `student`.`t_catalog` 
CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'id' ;


CREATE TABLE `student`.`t_user` (
  `id` INT NOT NULL COMMENT 'id',
  `name` VARCHAR(45) NULL COMMENT '用户名',
  `password` VARCHAR(100) NULL COMMENT '密码',
  `create_time` VARCHAR(45) NULL COMMENT '创建时间（yyyy-MM-dd hh:mm:ss）',
  `is_admin` INT NULL COMMENT '是否管理员',
  `status` INT NULL COMMENT '状态（0,1）',
  `role` INT NULL COMMENT '角色（1,2）',
  `relation` VARCHAR(45) NULL COMMENT '关系人姓名',
  `phone` VARCHAR(45) NULL COMMENT '手机号',
  PRIMARY KEY (`id`))
  COMMENT = '用户表';

ALTER TABLE `student`.`t_user` 
CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'id' ;

INSERT INTO `student`.`t_user` (`name`, `password`, `create_time`, `is_admin`, `status`, `role`, `phone`) VALUES ('admin', 'U2FsdGVkX1+1FtBLO/LuYYgMH8LMU+uJicXIVfGNo3g=', '2018-03-01 11:11:11', '1', '1', '1', '18872697123');


CREATE TABLE `student`.`t_news` (
  `id` INT NOT NULL COMMENT 'id',
  `name` VARCHAR(45) NULL COMMENT '消息名称',
  `create_time` VARCHAR(45) NULL COMMENT '创建时间（yyyy-MM-dd hh:mm:ss）',
  `update_time` VARCHAR(45) NULL COMMENT '更新时间（yyyy-MM-dd hh:mm:ss）',
  `publisher` VARCHAR(45) NULL COMMENT '发布者',
  `updater` VARCHAR(45) NULL COMMENT '更新者',
  `content` VARCHAR(10000) NULL COMMENT '内容',
  PRIMARY KEY (`id`))
  COMMENT = '消息表';
ALTER TABLE `student`.`t_news` 
CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'id' ;


CREATE TABLE `student`.`t_daily` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL COMMENT '学生名',
  `lesson` INT NULL COMMENT '上课（1:好,2:良,3:差）',
  `phone` INT NULL COMMENT '手机（1：上交,2：未上交,3：未带）',
  `homework` INT NULL COMMENT '作业（1：上交,2：迟交,3：未带）',
  `daily` INT NULL COMMENT '考勤（1：到勤,2：缺勤）',
  `clean` INT NULL COMMENT '卫生（1：值日,2：未值日）',
  `date` VARCHAR(45) NULL COMMENT '日期（yyyy-MM-dd）',
  PRIMARY KEY (`id`))
  COMMENT = '日常行为表';
ALTER TABLE `student`.`t_daily` 
CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT ;


CREATE TABLE `student`.`t_score` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL COMMENT '学生姓名',
  `exam` INT NULL COMMENT '考试类型',
  `week1` VARCHAR(45) NULL COMMENT '周测第一次',
  `week2` VARCHAR(45) NULL COMMENT '周测第二次',
  `week3` VARCHAR(45) NULL COMMENT '周测第三次',
  `english` VARCHAR(45) NULL COMMENT '英语',
  `social` VARCHAR(45) NULL COMMENT 'social',
  `science` VARCHAR(45) NULL COMMENT 'science',
  `chinese` VARCHAR(45) NULL COMMENT '中文',
  `math` VARCHAR(45) NULL COMMENT '数学',
  `physical` VARCHAR(45) NULL COMMENT '物理',
  PRIMARY KEY (`id`))
  COMMENT = '考试分数表';
ALTER TABLE `student`.`t_score` 
CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT ;

INSERT INTO `student`.`t_catalog` (`id`, `name`) VALUES ('1', '消息管理');
INSERT INTO `student`.`t_catalog` (`id`, `name`) VALUES ('2', '学生管理');
INSERT INTO `student`.`t_catalog` (`id`, `name`) VALUES ('3', '权限管理');
INSERT INTO `student`.`t_catalog` (`id`, `parentid`, `name`, `url`) VALUES ('4', '1', '最新消息', 'news');
INSERT INTO `student`.`t_catalog` (`id`, `parentid`, `name`, `url`) VALUES ('5', '2', '考勤管理', 'daily');
INSERT INTO `student`.`t_catalog` (`id`, `parentid`, `name`, `url`) VALUES ('6', '2', '成绩管理', 'score');
INSERT INTO `student`.`t_catalog` (`id`, `parentid`, `name`, `url`) VALUES ('7', '3', '用户管理', 'users');


CREATE TABLE `student`.`t_lesson` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `semester` VARCHAR(45) NULL COMMENT '学期',
  `period` VARCHAR(45) NULL COMMENT '时间段',
  `time` VARCHAR(45) NULL COMMENT '时间',
  `day1` VARCHAR(45) NULL COMMENT '星期一',
  `day2` VARCHAR(45) NULL COMMENT '星期二',
  `day3` VARCHAR(45) NULL COMMENT '星期三',
  `day4` VARCHAR(45) NULL COMMENT '星期四',
  `day5` VARCHAR(45) NULL COMMENT '星期五',
  PRIMARY KEY (`id`))
COMMENT = '课程表';
