package com.yupi.springbootinit.model.vo.task;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 任务
 * @TableName task
 * @author Da
 */
@Data
public class TaskVO implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 任务名称
     */
    private String name;

    /**
     * 办理方式（0-学生办理 1-非学生办理）
     */
    private Integer doWay;

    /**
     * 任务描述
     */
    private String description;


    /**
     * 完成时间
     */
    private Date completionTime;

    /**
     * 完成状态
     */
    private Integer completionStatus;



    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}