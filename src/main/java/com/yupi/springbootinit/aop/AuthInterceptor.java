package com.yupi.springbootinit.aop;

import com.google.gson.Gson;
import com.yupi.springbootinit.annotation.AuthCheck;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.model.entity.User;
import com.yupi.springbootinit.model.enums.UserRoleEnum;
import com.yupi.springbootinit.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * 权限校验 AOP
 *
 * @author Da
 * 
 */
@Aspect
@Component
public class AuthInterceptor {

    @Resource
    private UserService userService;

    /**
     * 执行拦截
     *
     * @param joinPoint
     * @param authCheck
     * @return
     */
    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        String mustRole = authCheck.mustRole();
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        // 当前登录用户
        User loginUser = userService.getLoginUser(request);
        // 必须有该权限才通过
        if (StringUtils.isNotBlank(mustRole)) {
            UserRoleEnum mustUserRoleEnum = UserRoleEnum.getEnumByValue(mustRole);
            if (mustUserRoleEnum == null) {
                throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
            }
            String userRoleStr = loginUser.getUserRole();
            Gson gson = new Gson();
            List<String> userRolesList = Arrays.asList(gson.fromJson(userRoleStr, String[].class));
            // 如果被封号，直接拒绝
            if(userRolesList.contains(UserRoleEnum.BAN.getValue())){
                throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
            }
//            if (UserRoleEnum.BAN.equals(mustUserRoleEnum)) {
//                throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
//            }
            // 必须有管理员权限
            if (UserRoleEnum.ADMIN.equals(mustUserRoleEnum)) {
                if(!userRolesList.contains(mustRole)){
                    throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
                }
//                if (!mustRole.equals(userRole)) {
//                    throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
//                }
            }
            // 必须有离校管理员权限
            if (UserRoleEnum.LEAVEMANAGER.equals(mustUserRoleEnum)) {
                if(!userRolesList.contains(mustRole)){
                    throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
                }
            }
            // 必须有老师权限
            if (UserRoleEnum.TEACHER.equals(mustUserRoleEnum)) {
                if(!userRolesList.contains(mustRole)){
                    throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
                }
            }
        }
        // 通过权限校验，放行
        return joinPoint.proceed();
    }
}

