package com.yupi.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.exception.ThrowUtils;
import com.yupi.springbootinit.mapper.ProcessSectorMapper;
import com.yupi.springbootinit.model.entity.ProcessSector;
import com.yupi.springbootinit.service.ProcessSectorService;
import org.springframework.stereotype.Service;

/**
 * 流程环节关联服务实现
 * @author Da
 */
@Service
public class ProcessSectorServiceImpl extends ServiceImpl<ProcessSectorMapper, ProcessSector>
    implements ProcessSectorService{

    @Override
    public boolean updateSectorIsMust(Long processId, Long sectorId, Integer isMust) {
        ThrowUtils.throwIf(processId == null || sectorId == null, new BusinessException(ErrorCode.PARAMS_ERROR, "流程id | 环节id 为空"));
        ProcessSector processSector = new ProcessSector();
        processSector.setProcessId(processId);
        processSector.setSectorId(sectorId);
        processSector.setIsMust(isMust);
        return this.save(processSector);
    }

}




