package com.yupi.springbootinit.model.dto.sector;

import com.yupi.springbootinit.common.PageRequest;
import lombok.Data;

/**
 * 环节查询请求参数封装
 * @author Da
 * @version 1.0
 * @date 2024/2/22 18:16
 */
@Data
public class SectorQueryRequest extends PageRequest {

    /**
     * 搜索词
     */
    private String searchText;

    /**
     * id
     */
    private Long id;

    /**
     *环节名称
     */
    private String name;

    /**
     *环节描述
     */
    private String description;

    /**
     * 办理地点
     */
    private String doLocation;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 办理时间
     */
    private String doTime;

    /**
     * 办理提示
     */
    private String doTip;


    /**
     * 办理方式
     */
    private String doWay;

}
