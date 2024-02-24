package com.yupi.springbootinit.constant;

/**
 * 流程常量
 *
 * @author Da
 * 
 */
public interface ProcessConstant {

    //-----------流程状态------------
    /**
     * 关闭
     */
    Integer CLOSE = 0;


    /**
     * 开启
     */
    Integer OPEN = 1;


    //-----------流程离校类型------------
    /**
     * 正常离校
     */
    Integer NORMAL = 0;


    /**
     * 非正常离校
     */
    Integer UN_NORMAL = 1;
}
