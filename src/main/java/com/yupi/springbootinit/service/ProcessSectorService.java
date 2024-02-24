package com.yupi.springbootinit.service;

import com.yupi.springbootinit.model.entity.ProcessSector;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 流程环节关联服务
 * @author Da
 */
public interface ProcessSectorService extends IService<ProcessSector> {

    /**
     * 更新流程下的环节是否必须
     * @param processId
     * @param sectorId
     * @return
     */
    boolean updateSectorIsMust(Long processId, Long sectorId, Integer isMust);

}
