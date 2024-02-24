package com.yupi.springbootinit.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yupi.springbootinit.common.BaseResponse;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.common.ResultUtils;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.exception.ThrowUtils;
import com.yupi.springbootinit.model.dto.processSector.UpdateSectorIsMustRequest;
import com.yupi.springbootinit.model.entity.ProcessSector;
import com.yupi.springbootinit.service.ProcessSectorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 流程环节接口
 *
 * @author Da
 * @version 1.0
 * &#064;date  2024/2/22 15:46
 */
@RestController
@RequestMapping("/processSector")
public class ProcessSectorController {

    @Resource
    private ProcessSectorService processSectorService;

    /**
     * 更新流程下的环节是否必须（仅管理员）
     * @param updateSectorIsMustRequest 更新流程下的环节是否必须请求参数封装
     * @return true | false
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateSectorIsMust(@RequestBody UpdateSectorIsMustRequest updateSectorIsMustRequest) {
        // todo 权限校验 【管理员】
        ThrowUtils.throwIf(updateSectorIsMustRequest == null, new BusinessException(ErrorCode.PARAMS_ERROR));
        Long sectorId = updateSectorIsMustRequest.getSectorId();
        Long processId = updateSectorIsMustRequest.getProcessId();
        Integer isMust = updateSectorIsMustRequest.getIsMust();
        ThrowUtils.throwIf(sectorId == null || processId == null || isMust == null, new BusinessException(ErrorCode.PARAMS_ERROR));
        LambdaQueryWrapper<ProcessSector> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProcessSector::getProcessId, processId).eq(ProcessSector::getSectorId, sectorId);
        ProcessSector processSector = new ProcessSector();
        processSector.setIsMust(isMust);
        return ResultUtils.success(processSectorService.update(processSector, queryWrapper));
    }
}
