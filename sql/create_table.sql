# 数据库初始化
# @Da
#

-- 创建库
create database if not exists leave_school_manage;

-- 切换库
use leave_school_manage;

CREATE TABLE `clazz`
(
    `id`         bigint(20)                              NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`       varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '班级名称',
    `createTime` datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `isDelete`   tinyint(4)                              NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='班级';

CREATE TABLE `college`
(
    `id`         bigint(20)                              NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`       varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '学院名称',
    `createTime` datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `isDelete`   tinyint(4)                              NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='学院';

CREATE TABLE `department`
(
    `id`         bigint(20)                              NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`       varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '部门名称',
    `createTime` datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `isDelete`   tinyint(4)                              NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='部门';

CREATE TABLE `grade`
(
    `id`         bigint(20)                              NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`       varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '年级名称',
    `createTime` datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `isDelete`   tinyint(4)                              NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='年级';

CREATE TABLE `leave_school_student_info`
(
    `id`                 bigint(20)                              NOT NULL AUTO_INCREMENT COMMENT 'id',
    `studentId`          varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '学号',
    `name`               varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '姓名',
    `age`                int(11)                                 NOT NULL COMMENT '年龄',
    `sex`                int(11)                                 NOT NULL DEFAULT '0' COMMENT '性别（0-女 1-男）',
    `intakeTime`         datetime                                NOT NULL COMMENT '入学时间',
    `graduationTimeTime` datetime                                NOT NULL COMMENT '毕业时间',
    `education`          varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '学历',
    `grade`              varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '年级',
    `departmentId`       varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '学院id',
    `specialityId`       varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '专业id',
    `clazzId`            varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '班级id',
    `leaveStatus`        int(11)                                 NOT NULL DEFAULT '0' COMMENT '性别（0-未离校 1-离校）',
    `leaveSchoolTime`    datetime                                NOT NULL COMMENT '离校时间',
    `createTime`         datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime`         datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `isDelete`           tinyint(4)                              NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='离校学生信息';

CREATE TABLE `process`
(
    `id`          bigint(20)                              NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`        varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '流程名称',
    `year`        int(11)                                 NOT NULL COMMENT '年度',
    `description` text COLLATE utf8mb4_unicode_ci         NOT NULL COMMENT '描述',
    `leaveType`   int(11)                                 NOT NULL COMMENT '离校类型（0-正常离校 1-非正常离校）',
    `status`      int(11)                                 NOT NULL COMMENT '状态（0-关闭 1-开启）',
    `createTime`  datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime`  datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `isDelete`    tinyint(4)                              NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1761353255564804099
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='流程';

CREATE TABLE `process_sector`
(
    `id`         bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `processId`  bigint(20) NOT NULL COMMENT '流程id',
    `sectorId`   bigint(20) NOT NULL COMMENT '环节id',
    `isMust`     int(11)    NOT NULL DEFAULT '0' COMMENT '是否必须（0-非必须 1-必须）',
    `createTime` datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `isDelete`   tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1761353255631912963
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='流程环节关联';

CREATE TABLE `process_student`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `processId`        bigint(20) NOT NULL COMMENT '流程id',
    `studentId`        bigint(20) NOT NULL COMMENT '学生id',
    `completionStatus` int(11)    NOT NULL DEFAULT '0' COMMENT '完成情况（0-未完成 1-已完成）',
    `completionTime`   datetime   NOT NULL COMMENT '完成时间',
    `createTime`       datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime`       datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `isDelete`         tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='流程学生关联';

CREATE TABLE `schooling`
(
    `id`         bigint(20)                              NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`       varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '学历名称',
    `createTime` datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `isDelete`   tinyint(4)                              NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='学历';

CREATE TABLE `sector`
(
    `id`          bigint(20)                              NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`        varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '环节名称',
    `description` text COLLATE utf8mb4_unicode_ci         NOT NULL COMMENT '描述',
    `doLocation`  varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '办理地点',
    `phone`       varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '联系方式',
    `doTime`      varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '办理时间',
    `doTip`       varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '办理提示',
    `createTime`  datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime`  datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `isDelete`    tinyint(4)                              NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1761351886053244930
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='环节';

CREATE TABLE `sector_student`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `sectorId`         bigint(20) NOT NULL COMMENT '环节id',
    `studentId`        bigint(20) NOT NULL COMMENT '学生id',
    `completionStatus` int(11)    NOT NULL DEFAULT '0' COMMENT '完成情况（0-未完成 1-已完成）',
    `completionTime`   datetime   NOT NULL COMMENT '完成时间',
    `createTime`       datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime`       datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `isDelete`         tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='环节学生关联';


CREATE TABLE `task_student`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `taskId`         bigint(20) NOT NULL COMMENT '任务id',
    `studentId`        bigint(20) NOT NULL COMMENT '学生id',
    `completionStatus` int(11)    NOT NULL DEFAULT '0' COMMENT '完成情况（0-未完成 1-已完成）',
    `completionTime`   datetime    COMMENT '完成时间',
    `createTime`       datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime`       datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `isDelete`         tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='任务学生关联';

CREATE TABLE `sector_task`
(
    `id`         bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `sectorId`   bigint(20) NOT NULL COMMENT '环节id',
    `taskId`     bigint(20) NOT NULL COMMENT '任务id',
    `createTime` datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `isDelete`   tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1761351886099382275
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='环节任务关联';

CREATE TABLE `speciality`
(
    `id`         bigint(20)                              NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`       varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '专业名称',
    `createTime` datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `isDelete`   tinyint(4)                              NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='专业';

CREATE TABLE `student`
(
    `id`             bigint(20)                              NOT NULL AUTO_INCREMENT COMMENT 'id',
    `studentId`      varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '学号',
    `name`           varchar(512) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '姓名',
    `sex`            int(11)                                 NOT NULL DEFAULT '0' COMMENT '性别（0-女 1-男）',
    `age`            int(11)                                 NOT NULL COMMENT '年龄',
    `intakeTime`     datetime                                NOT NULL COMMENT '入学时间',
    `graduationTime` datetime                                NOT NULL COMMENT '毕业时间',
    `gradeId`        bigint(20)                              NOT NULL COMMENT '年级id',
    `educationId`    bigint(20)                              NOT NULL COMMENT '学历id',
    `departmentId`   bigint(20)                              NOT NULL COMMENT '学院id',
    `specialityId`   bigint(20)                              NOT NULL COMMENT '专业id',
    `clazzId`        bigint(20)                              NOT NULL COMMENT '班级id',
    `createTime`     datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime`     datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `isDelete`       tinyint(4)                              NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='学生';

CREATE TABLE `task`
(
    `id`          bigint(20)                              NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`        varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '任务名称',
    `doWay`       int(11)                                 NOT NULL DEFAULT '0' COMMENT '办理方式（0-学生办理 1-非学生办理）',
    `description` text COLLATE utf8mb4_unicode_ci         NOT NULL COMMENT '任务描述',
    `createTime`  datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime`  datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `isDelete`    tinyint(4)                              NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1761351886053244932
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='任务';

CREATE TABLE `teacher`
(
    `id`           bigint(20)                              NOT NULL AUTO_INCREMENT COMMENT 'id',
    `teacherId`    varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '工号',
    `name`         varchar(512) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '姓名',
    `sex`          int(11)                                 NOT NULL DEFAULT '0' COMMENT '性别（0-女 1-男）',
    `age`          int(11)                                 NOT NULL COMMENT '年龄',
    `doWorkTime`   datetime                                NOT NULL COMMENT '入职时间',
    `departmentId` bigint(20)                              NOT NULL COMMENT '部门id',
    `clazzId`      bigint(20)                              NOT NULL COMMENT '班级id',
    `createTime`   datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime`   datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `isDelete`     tinyint(4)                              NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='老师';

CREATE TABLE `user`
(
    `id`           bigint(20)                              NOT NULL AUTO_INCREMENT COMMENT 'id',
    `userAccount`  varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账号',
    `userPassword` varchar(512) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
    `unionId`      varchar(256) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '微信开放平台id',
    `mpOpenId`     varchar(256) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '公众号openId',
    `userName`     varchar(256) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '用户昵称',
    `userAvatar`   varchar(1024) COLLATE utf8mb4_unicode_ci         DEFAULT NULL COMMENT '用户头像',
    `userProfile`  varchar(512) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '用户简介',
    `userRole`     varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'user' COMMENT '用户角色：user/admin/ban',
    `createTime`   datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime`   datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `isDelete`     tinyint(4)                              NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`),
    KEY `idx_unionId` (`unionId`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='用户'







