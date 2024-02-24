package com.yupi.springbootinit.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.springbootinit.model.dto.process.AddProcessRequest;
import com.yupi.springbootinit.model.dto.process.ProcessQueryRequest;
import com.yupi.springbootinit.model.entity.Process;
import com.yupi.springbootinit.model.vo.process.ProcessVO;

/**
 * 流程服务
 * @author Da
 */
public interface ProcessService extends IService<Process> {

    /**
     * 新增流程
     * @param addProcessRequest
     * @return
     */
    boolean addProcess(AddProcessRequest addProcessRequest);

    /**
     * 校验
     * @param process
     * @param add
     */
    void validProcess(Process process, boolean add);

    /**
     * 查询条件封装
     * @param processQueryRequest
     * @return
     */
    QueryWrapper getQueryWrapper(ProcessQueryRequest processQueryRequest);

    /**
     * 获取流程封装
     * @param process
     * @return
     */
    ProcessVO getProcessVO(Process process);
}
