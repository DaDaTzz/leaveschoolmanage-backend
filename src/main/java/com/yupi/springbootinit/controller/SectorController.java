package com.yupi.springbootinit.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.springbootinit.common.BaseResponse;
import com.yupi.springbootinit.common.DeleteRequest;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.common.ResultUtils;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.exception.ThrowUtils;
import com.yupi.springbootinit.model.dto.sector.AddSectorRequest;
import com.yupi.springbootinit.model.dto.sector.SectorQueryRequest;
import com.yupi.springbootinit.model.dto.sector.UpdateSectorRequest;
import com.yupi.springbootinit.model.entity.ProcessSector;
import com.yupi.springbootinit.model.entity.Sector;
import com.yupi.springbootinit.model.entity.SectorTask;
import com.yupi.springbootinit.model.vo.sector.SectorVO;
import com.yupi.springbootinit.service.ProcessSectorService;
import com.yupi.springbootinit.service.SectorService;
import com.yupi.springbootinit.service.SectorTaskService;
import com.yupi.springbootinit.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 环节接口
 *
 * @author Da
 * @version 1.0
 * {@code @date} 2024/2/22 10:18
 */
@RestController
@RequestMapping("/sector")
@Slf4j
public class SectorController {

    @Resource
    private SectorService sectorService;
    @Resource
    private SectorTaskService sectorTaskService;
    @Resource
    private TaskService taskService;
    @Resource
    private ProcessSectorService processSectorService;

    /**
     * 新增环节（仅管理员使用）
     *
     * @param addSectorRequest 新增环节请求参数
     * @return true 新增成功 | false 新增失败
     */
    @PostMapping("/add")
    public BaseResponse<Boolean> addSector(@RequestBody AddSectorRequest addSectorRequest) {
        // TODO 权限校验 【管理员】
        ThrowUtils.throwIf(addSectorRequest == null, new BusinessException(ErrorCode.PARAMS_ERROR));
        return ResultUtils.success(sectorService.addSector(addSectorRequest));
    }

    /**
     * 修改环节（仅管理员使用）
     *
     * @param updateSectorRequest 修改环节请求参数
     * @return true 修改成功 | false 修改失败
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateSector(@RequestBody UpdateSectorRequest updateSectorRequest) {
        // todo 权限校验 【管理员】
        ThrowUtils.throwIf(updateSectorRequest == null || updateSectorRequest.getId() <= 0, new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数错误"));
        Sector sector = new Sector();
        BeanUtil.copyProperties(updateSectorRequest, sector);
        // 参数校验
        sectorService.validSector(sector, false);
        Long id = updateSectorRequest.getId();
        // 判断是否存在
        Sector oldSector = sectorService.getById(id);
        ThrowUtils.throwIf(oldSector == null, ErrorCode.NOT_FOUND_ERROR);
        boolean result = sectorService.updateById(sector);
        return ResultUtils.success(result);
    }


    /**
     * 分页获取环节列表（仅管理员）
     *
     * @param sectorQueryRequest 流程查询请求参数封装
     * @return 环节分页对象
     */
    @PostMapping("/list/sector")
    public BaseResponse<Page<Sector>> listSectorByPage(@RequestBody SectorQueryRequest sectorQueryRequest) {
        // todo 权限校验 【管理员】
        long current = sectorQueryRequest.getCurrent();
        long size = sectorQueryRequest.getPageSize();
        Page<Sector> sectorPage = sectorService.page(new Page<>(current, size),
                sectorService.getQueryWrapper(sectorQueryRequest));
        return ResultUtils.success(sectorPage);
    }


    /**
     * 根据 id 获取
     *
     * @param id 环节id
     * @return 环节VO （环节信息， 任务信息）
     */
    @GetMapping("/get/vo")
    public BaseResponse<SectorVO> getSectorVOById(Long id) {
        ThrowUtils.throwIf(id == null || id <= 0, ErrorCode.PARAMS_ERROR, "id为空");
        Sector sector = sectorService.getById(id);
        ThrowUtils.throwIf(sector == null, ErrorCode.NOT_FOUND_ERROR, "该环节不存在");
        return ResultUtils.success(sectorService.getSectorVO(sector));
    }


    /**
     * 删除环节（支持批量删除）
     *
     * @param deleteRequest 删除请求参数封装
     * @return true | false
     */
    @PostMapping("/delete")
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse<Boolean> deleteSector(@RequestBody DeleteRequest deleteRequest) {
        ThrowUtils.throwIf(deleteRequest == null || deleteRequest.getIdList().isEmpty(), ErrorCode.PARAMS_ERROR);
        // todo 权限校验 【管理员】
        List<Long> idList = deleteRequest.getIdList();
        // 绑定了流程的环节不允许删除
        LambdaQueryWrapper<ProcessSector> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(ProcessSector::getSectorId, idList);
        List<ProcessSector> processSectorList = processSectorService.list(queryWrapper);
        ThrowUtils.throwIf(!processSectorList.isEmpty(), ErrorCode.PARAMS_ERROR, "环节已绑定流程，不允许删除！");
        // 执行删除
        boolean removeSectorResult = sectorService.removeByIds(idList);
        // 删除绑定的任务以及关联关系
        LambdaQueryWrapper<SectorTask> sectorTaskLambdaQueryWrapper = new LambdaQueryWrapper<>();
        sectorTaskLambdaQueryWrapper.in(SectorTask::getSectorId, idList);
        List<SectorTask> sectorTaskList = sectorTaskService.list(sectorTaskLambdaQueryWrapper);
        List<Long> taskIdList = new ArrayList<>();
        List<Long> sectorTaskIdList = new ArrayList<>();
        sectorTaskList.forEach(sectorTask -> {
            taskIdList.add(sectorTask.getTaskId());
            sectorTaskIdList.add(sectorTask.getId());
        });
        boolean removeTaskResult = taskService.removeByIds(taskIdList);
        boolean removeSectorTaskResult = sectorTaskService.removeByIds(sectorTaskIdList);
        return ResultUtils.success(removeSectorResult && removeTaskResult && removeSectorTaskResult);
    }


}
