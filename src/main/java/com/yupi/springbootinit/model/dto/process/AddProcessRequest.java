package com.yupi.springbootinit.model.dto.process;

import com.yupi.springbootinit.model.vo.sector.SectorIsMustVO;
import lombok.Data;

import java.util.List;

/**
 * 新增流程请求参数封装
 * @author Da
 * @version 1.0
 * @date 2024/2/22 10:23
 */
@Data
public class AddProcessRequest {

    /**
     * 流程名称
     */
    private String name;

    /**
     * 年度
     */
    private String year;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态（0-关闭 1-开启）
     */
    private Integer status;

    /**
     * 离校类型（0-正常离校 1-非正常离校）
     */
    private Integer leaveType;

    /**
     * 流程是否必须vo
     */
    private List<SectorIsMustVO> sectorIsMustVOList;


}
