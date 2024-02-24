package com.yupi.springbootinit.model.vo.sector;

import lombok.Data;

/**
 * 环节是否必须 vo
 * @author Da
 * @version 1.0
 * @date 2024/2/22 16:18
 */
@Data
public class SectorIsMustVO {
    /**
     * id
     */
    private Long sectorId;

    /**
     * 是否必须
     */
    private Integer isMust;
}
