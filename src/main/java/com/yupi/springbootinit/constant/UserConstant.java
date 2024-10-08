package com.yupi.springbootinit.constant;

/**
 * 用户常量
 *
 * @author Da
 * 
 */
public interface UserConstant {

    /**
     * 用户登录态键
     */
    String USER_LOGIN_STATE = "user_login";

    //  region 权限

    /**
     * 默认角色
     */
    String DEFAULT_ROLE = "user";

    /**
     * 管理员角色
     */
    String ADMIN_ROLE = "admin";

    /**
     * 离校管理员角色
     */
    String LEAVEMANAGER_ROLE = "leaveManager";

    /**
     * 老师角色
     */
    String TEACHER_ROLE = "teacher";

    /**
     * 学生角色
     */
    String STUDENT_ROLE = "student";

    /**
     * 被封号
     */
    String BAN_ROLE = "ban";

    // endregion
}
