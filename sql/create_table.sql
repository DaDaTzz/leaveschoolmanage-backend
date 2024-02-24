# 数据库初始化
# @Da
#

-- 创建库
create database if not exists leave_school_manage;

-- 切换库
use leave_school_manage;

-- 用户表
create table if not exists user
(
    id           bigint auto_increment comment 'id' primary key,
    userAccount  varchar(256)                           not null comment '账号',
    userPassword varchar(512)                           not null comment '密码',
    unionId      varchar(256)                           null comment '微信开放平台id',
    mpOpenId     varchar(256)                           null comment '公众号openId',
    userName     varchar(256)                           null comment '用户昵称',
    userAvatar   varchar(1024)                          null comment '用户头像',
    userProfile  varchar(512)                           null comment '用户简介',
    userRole     varchar(256) default 'user'            not null comment '用户角色：user/admin/ban',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除',
    index idx_unionId (unionId)
) comment '用户' collate = utf8mb4_unicode_ci;


-- 班级表
create table if not exists clazz
(
    id         bigint auto_increment comment 'id' primary key,
    name       varchar(256)                       not null comment '班级名称',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除'
) comment '班级' collate = utf8mb4_unicode_ci;


-- 学院表
create table if not exists college
(
    id         bigint auto_increment comment 'id' primary key,
    name       varchar(256)                       not null comment '学院名称',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除'
) comment '学院' collate = utf8mb4_unicode_ci;

-- 部门表
create table if not exists department
(
    id         bigint auto_increment comment 'id' primary key,
    name       varchar(256)                       not null comment '部门名称',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除'
) comment '部门' collate = utf8mb4_unicode_ci;


-- 离校学生信息表
create table if not exists leave_school_student_info
(
    id                 bigint auto_increment comment 'id' primary key,
    studentId          varchar(256)                       not null comment '学号',
    name               varchar(256)                       not null comment '姓名',
    age                int                                not null comment '年龄',
    sex                int      default 0                 not null comment '性别（0-女 1-男）',
    intakeTime         datetime                           not null comment '入学时间',
    graduationTimeTime datetime                           not null comment '毕业时间',
    education          varchar(256)                       not null comment '学历',
    grade              varchar(256)                       not null comment '年级',
    departmentId       varchar(256)                       not null comment '学院id',
    specialityId       varchar(256)                       not null comment '专业id',
    clazzId            varchar(256)                       not null comment '班级id',
    leaveStatus        int      default 0                 not null comment '性别（0-未离校 1-离校）',
    leaveSchoolTime    datetime                           not null comment '离校时间',
    createTime         datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime         datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete           tinyint  default 0                 not null comment '是否删除'
) comment '离校学生信息' collate = utf8mb4_unicode_ci;


-- 流程表
create table if not exists process
(
    id          bigint auto_increment comment 'id' primary key,
    name        varchar(256)                       not null comment '流程名称',
    year        int                                not null comment '年度',
    description text                               not null comment '描述',
    leaveType   int                                not null comment '离校类型（0-正常离校 1-非正常离校）',
    status      int                                not null comment '状态（0-关闭 1-开启）',
    createTime  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete    tinyint  default 0                 not null comment '是否删除'
) comment '流程' collate = utf8mb4_unicode_ci;


-- 流程环节关联表
create table if not exists process_sector
(
    id         bigint auto_increment comment 'id' primary key,
    processId  bigint                             not null comment '流程id',
    sectorId   bigint                             not null comment '环节id',
    isMust     int      default 0                 not null comment '是否必须（0-非必须 1-必须）',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除'
) comment '流程环节关联' collate = utf8mb4_unicode_ci;


-- 流程学生关联表
create table if not exists process_student
(
    id               bigint auto_increment comment 'id' primary key,
    processId        bigint                             not null comment '流程id',
    studentId        bigint                             not null comment '学生id',
    completionStatus int      default 0                 not null comment '完成情况（0-未完成 1-已完成）',
    completionTime   datetime                           not null comment '完成时间',
    createTime       datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime       datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete         tinyint  default 0                 not null comment '是否删除'
) comment '流程学生关联' collate = utf8mb4_unicode_ci;


-- 学历表
create table if not exists schooling
(
    id         bigint auto_increment comment 'id' primary key,
    name       varchar(256)                       not null comment '学历名称',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除'
) comment '学历' collate = utf8mb4_unicode_ci;


-- 环节表
create table if not exists sector
(
    id          bigint auto_increment comment 'id' primary key,
    name        varchar(256)                       not null comment '环节名称',
    description text                               not null comment '描述',
    doLocation  varchar(256)                       not null comment '办理地点',
    phone       varchar(256)                       not null comment '联系方式',
    doTime      datetime                           not null comment '办理时间',
    doTip       varchar(256)                       not null comment '办理提示',
    createTime  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete    tinyint  default 0                 not null comment '是否删除'
) comment '环节' collate = utf8mb4_unicode_ci;




-- 环节学生关联表
create table if not exists sector_student
(
    id               bigint auto_increment comment 'id' primary key,
    sectorId        bigint                             not null comment '环节id',
    studentId        bigint                             not null comment '学生id',
    completionStatus int      default 0                 not null comment '完成情况（0-未完成 1-已完成）',
    completionTime   datetime                           not null comment '完成时间',
    createTime       datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime       datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete         tinyint  default 0                 not null comment '是否删除'
) comment '环节学生关联' collate = utf8mb4_unicode_ci;



-- 环节任务关联表
create table if not exists sector_task
(
    id               bigint auto_increment comment 'id' primary key,
    sectorId        bigint                             not null comment '环节id',
    taskId        bigint                             not null comment '任务id',
    createTime       datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime       datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete         tinyint  default 0                 not null comment '是否删除'
) comment '环节任务关联' collate = utf8mb4_unicode_ci;


-- 环节用户关联表
create table if not exists sector_task
(
    id               bigint auto_increment comment 'id' primary key,
    sectorId        bigint                             not null comment '环节id',
    userId        bigint                             not null comment '用户id',
    createTime       datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime       datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete         tinyint  default 0                 not null comment '是否删除'
) comment '环节用户关联' collate = utf8mb4_unicode_ci;



-- 专业表
create table if not exists speciality
(
    id         bigint auto_increment comment 'id' primary key,
    name       varchar(256)                       not null comment '专业名称',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除'
) comment '专业' collate = utf8mb4_unicode_ci;




-- 学生表
create table if not exists student
(
    id           bigint auto_increment comment 'id' primary key,
    studentId  varchar(256)                           not null comment '学号',
    name varchar(512)                           not null comment '姓名',
    sex int                   default 0       not null comment '性别（0-女 1-男）',
    age int                         not null comment '年龄',
    intakeTime   datetime     not null comment '入学时间',
    graduationTime   datetime     not null comment '毕业时间',
    gradeId bigint not null comment '年级id',
    educationId bigint not null comment '学历id',
    departmentId bigint not null comment '学院id',
    specialityId bigint not null comment '专业id',
    clazzId bigint not null comment '班级id',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除'
) comment '学生' collate = utf8mb4_unicode_ci;



-- 任务表
create table if not exists task
(
    id         bigint auto_increment comment 'id' primary key,
    name       varchar(256)                       not null comment '任务名称',
    doWay       int                 default 0     not null comment '办理方式（0-学生办理 1-非学生办理）',
    description       text                   not null comment '任务描述',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除'
) comment '任务' collate = utf8mb4_unicode_ci;



-- 任务学生关联表
create table if not exists sector_task
(
    id               bigint auto_increment comment 'id' primary key,
    taskId        bigint                             not null comment '任务id',
    studentId        bigint                             not null comment '学生id',
    completionStatus        int default 0                            not null comment '完成情况（0-未完成 1-已完成）',
    completionTime       datetime  not null comment '完成时间',
    createTime       datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime       datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete         tinyint  default 0                 not null comment '是否删除'
) comment '任务学生关联' collate = utf8mb4_unicode_ci;




-- 老师表
create table if not exists teacher
(
    id           bigint auto_increment comment 'id' primary key,
    teacherId  varchar(256)                           not null comment '工号',
    name varchar(512)                           not null comment '姓名',
    sex int                   default 0       not null comment '性别（0-女 1-男）',
    age int                         not null comment '年龄',
    doWorkTime   datetime     not null comment '入职时间',
    departmentId bigint not null comment '部门id',
    clazzId bigint not null comment '班级id',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除'
) comment '老师' collate = utf8mb4_unicode_ci;


-- 年级表
create table if not exists grade
(
    id         bigint auto_increment comment 'id' primary key,
    name       varchar(256)                       not null comment '年级名称',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除'
) comment '年级' collate = utf8mb4_unicode_ci;






