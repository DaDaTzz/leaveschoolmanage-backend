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
import com.yupi.springbootinit.model.dto.process.AddProcessRequest;
import com.yupi.springbootinit.model.dto.process.ProcessQueryRequest;
import com.yupi.springbootinit.model.dto.process.UpdateProcessRequest;
import com.yupi.springbootinit.model.entity.Process;
import com.yupi.springbootinit.model.entity.ProcessSector;
import com.yupi.springbootinit.model.vo.process.ProcessVO;
import com.yupi.springbootinit.service.ProcessSectorService;
import com.yupi.springbootinit.service.ProcessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 流程接口
 *
 * @author Da
 * @version 1.0
 * &#064;date  2024/2/22 15:46
 */
@RestController
@RequestMapping("/process")
@Slf4j
public class ProcessController {

    @Resource
    private ProcessService processService;
    @Resource
    private ProcessSectorService processSectorService;


    /**
     * 新增流程（仅管理员）
     *
     * @param addProcessRequest 新增流程请求参数封装
     * @return true 新增成功 | false 新增失败
     */
    @PostMapping("/add")
    public BaseResponse<Boolean> addProcess(@RequestBody AddProcessRequest addProcessRequest) {
        // todo 权限校验 【管理员】
        ThrowUtils.throwIf(addProcessRequest == null, new BusinessException(ErrorCode.PARAMS_ERROR));
        return ResultUtils.success(processService.addProcess(addProcessRequest));
    }


    /**
     * 分页获取流程列表（仅管理员）
     *
     * @param processQueryRequest 流程查询请求参数封装
     * @return 环节分页对象
     */
    @PostMapping("/list/process")
        public BaseResponse<Page<Process>> listProcessByPage(@RequestBody ProcessQueryRequest processQueryRequest) {
            // todo 权限校验 【管理员】
            long current = processQueryRequest.getCurrent();
            long size = processQueryRequest.getPageSize();
            Page<Process> processPage = processService.page(new Page<>(current, size),
                    processService.getQueryWrapper(processQueryRequest));
            return ResultUtils.success(processPage);
    }

    /**
     * 根据 id 获取
     *
     * @param id 流程id
     * @return 流程 VO（流程信息，环节信息，任务信息）
     */
    @GetMapping("/get/vo")
    public BaseResponse<ProcessVO> getProcessVOById(Long id) {
        ThrowUtils.throwIf(id == null || id <= 0, ErrorCode.PARAMS_ERROR, "id为空");
        Process process = processService.getById(id);
        ThrowUtils.throwIf(process == null, ErrorCode.NOT_FOUND_ERROR, "该流程不存在");
        return ResultUtils.success(processService.getProcessVO(process));
    }


    /**
     * 删除流程（支持批量删除）
     *
     * @param deleteRequest 删除请求参数封装
     * @return true | false
     */
    @PostMapping("/delete")
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse<Boolean> deleteProcess(@RequestBody DeleteRequest deleteRequest) {
        ThrowUtils.throwIf(deleteRequest == null || deleteRequest.getIdList().isEmpty(), ErrorCode.PARAMS_ERROR);
        // todo 权限校验 【管理员】
        List<Long> idList = deleteRequest.getIdList();
        // 执行删除
        boolean removeProcessResult = processService.removeByIds(idList);
        // 删除流程环节关联关系
        LambdaQueryWrapper<ProcessSector> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(ProcessSector::getProcessId, idList);
        boolean removeProcessSectorResult = processSectorService.remove(queryWrapper);
        return ResultUtils.success(removeProcessResult && removeProcessSectorResult);
    }


    /**
     * 修改流程（仅管理员使用）
     *
     * @param updateProcessRequest 修改流程请求参数封装
     * @return true 修改成功 | false 修改失败
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateProcess(@RequestBody UpdateProcessRequest updateProcessRequest) {
        // todo 权限校验 【管理员】
        ThrowUtils.throwIf(updateProcessRequest == null || updateProcessRequest.getId() <= 0, new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数错误"));
        Process process = new Process();
        BeanUtil.copyProperties(updateProcessRequest, process);
        // 参数校验
        processService.validProcess(process, false);
        Long id = updateProcessRequest.getId();
        // 判断是否存在
        Process oldProcess = processService.getById(id);
        ThrowUtils.throwIf(oldProcess == null, ErrorCode.NOT_FOUND_ERROR);
        boolean result = processService.updateById(process);
        return ResultUtils.success(result);
    }


}
