package com.yupi.springbootinit.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.constant.CommonConstant;
import com.yupi.springbootinit.constant.ProcessConstant;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.exception.ThrowUtils;
import com.yupi.springbootinit.mapper.ProcessMapper;
import com.yupi.springbootinit.mapper.SectorMapper;
import com.yupi.springbootinit.model.dto.process.AddProcessRequest;
import com.yupi.springbootinit.model.dto.process.ProcessQueryRequest;
import com.yupi.springbootinit.model.entity.Process;
import com.yupi.springbootinit.model.entity.ProcessSector;
import com.yupi.springbootinit.model.entity.Sector;
import com.yupi.springbootinit.model.entity.Task;
import com.yupi.springbootinit.model.vo.process.ProcessVO;
import com.yupi.springbootinit.model.vo.sector.SectorIsMustVO;
import com.yupi.springbootinit.model.vo.sector.SectorVO;
import com.yupi.springbootinit.service.ProcessSectorService;
import com.yupi.springbootinit.service.ProcessService;
import com.yupi.springbootinit.service.SectorService;
import com.yupi.springbootinit.utils.SqlUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 流程服务实现
 * @author Da
 */
@Service
public class ProcessServiceImpl extends ServiceImpl<ProcessMapper, Process>
    implements ProcessService{

    @Resource
    private ProcessSectorService processSectorService;
    @Resource
    private SectorService sectorService;
    @Resource
    private ProcessMapper processMapper;
    @Resource
    private SectorMapper sectorMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addProcess(AddProcessRequest addProcessRequest) {
        List<SectorIsMustVO> sectorIsMustVOList = addProcessRequest.getSectorIsMustVOList();
        ThrowUtils.throwIf(sectorIsMustVOList.isEmpty(), new BusinessException(ErrorCode.PARAMS_ERROR, "新增流程至少绑定一个环节"));
        Process process = new Process();
        BeanUtil.copyProperties(addProcessRequest, process);
        // 参数校验
        this.validProcess(process, true);
        // 插入数据
        boolean saveProcessResult = this.save(process);
        List<ProcessSector> processSectorList = new ArrayList<>();
        if (saveProcessResult) {
            Long newProcessId = process.getId();
            sectorIsMustVOList.forEach(sectorIsMustVO -> {
                // 校验环节是否存在
                ThrowUtils.throwIf(sectorService.getById(sectorIsMustVO.getSectorId()) == null, ErrorCode.PARAMS_ERROR, "绑定环节不存在");
                Long sectorId = sectorIsMustVO.getSectorId();
                ProcessSector processSector = new ProcessSector();
                processSector.setProcessId(newProcessId);
                processSector.setSectorId(sectorId);
                processSector.setIsMust(sectorIsMustVO.getIsMust());
                processSectorList.add(processSector);
            });
            return processSectorService.saveBatch(processSectorList);
        }
        return false;
    }

    @Override
    public void validProcess(Process process, boolean add) {
        ThrowUtils.throwIf(process == null, ErrorCode.PARAMS_ERROR, "流程为空");
        String name = process.getName();
        String year = process.getYear();
        String description = process.getDescription();
        Integer status = process.getStatus();
        Integer leaveType = process.getLeaveType();
        // 有参数则校验
        ThrowUtils.throwIf(StringUtils.isNotBlank(name) && name.length() > 255, ErrorCode.PARAMS_ERROR, "流程名称过长");
        ThrowUtils.throwIf(StringUtils.isNotBlank(year) && year.length() > 50, ErrorCode.PARAMS_ERROR, "流程年度过长");
        ThrowUtils.throwIf(StringUtils.isNotBlank(description) && description.length() > 1000, ErrorCode.PARAMS_ERROR, "流程描述过长");
        ThrowUtils.throwIf(status != null && !status.equals(ProcessConstant.CLOSE) && !status.equals(ProcessConstant.OPEN), ErrorCode.PARAMS_ERROR, "环节办理方式错误");
        ThrowUtils.throwIf(leaveType != null && !leaveType.equals(ProcessConstant.NORMAL) && !leaveType.equals(ProcessConstant.UN_NORMAL), ErrorCode.PARAMS_ERROR, "环节离校类型错误");
        // 添加校验
        if (add) {
            // 1、参数不能为空
            ThrowUtils.throwIf(StringUtils.isAnyBlank(name, description) || year == null , ErrorCode.PARAMS_ERROR, "请求参数为空");
            // 2、流程名不能重复
            LambdaQueryWrapper<Process> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Process::getName, name);
            ThrowUtils.throwIf(this.getOne(queryWrapper) != null, ErrorCode.PARAMS_ERROR, "流程名称重复");
        }
    }

    @Override
    public QueryWrapper getQueryWrapper(ProcessQueryRequest processQueryRequest) {
        QueryWrapper<Process> queryWrapper = new QueryWrapper<>();
        if (processQueryRequest == null) {
            return queryWrapper;
        }
        String searchText = processQueryRequest.getSearchText();
        Long id = processQueryRequest.getId();
        String name = processQueryRequest.getName();
        String year = processQueryRequest.getYear();
        String description = processQueryRequest.getDescription();
        Integer leaveType = processQueryRequest.getLeaveType();
        Integer status = processQueryRequest.getStatus();
        String sortField = processQueryRequest.getSortField();
        String sortOrder = processQueryRequest.getSortOrder();
        // 拼接查询条件
        if (StringUtils.isNotBlank(searchText)) {
            queryWrapper.and(qw -> qw.like("mc", searchText).or().like("ms", searchText));
        }
        queryWrapper.like(StringUtils.isNotBlank(name), "name", name);
        queryWrapper.like(StringUtils.isNotBlank(year), "year", year);
        queryWrapper.like(StringUtils.isNotBlank(description), "description", description);
        queryWrapper.eq(leaveType != null, "leaveType", leaveType);
        queryWrapper.eq(id != null && id > 0, "id", id);
        queryWrapper.eq(status != null, "status", status);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }

    @Override
    public ProcessVO getProcessVO(Process process) {
        ProcessVO processVO = ProcessVO.objToVo(process);
        Long processId = process.getId();
        // 1. 关联查询环节信息
        List<Map<String, String>> sectors = processMapper.listSectorProcessById(processId);
        List<Sector> sectorList = new ArrayList<>();
        sectors.forEach(sectorMap -> sectorList.add(BeanUtil.mapToBean(sectorMap, Sector.class, true, CopyOptions.create())));
        List<SectorVO> sectorVOList = new ArrayList<>();
        sectorList.forEach(sector -> {
            SectorVO sectorVO = new SectorVO();
            BeanUtil.copyProperties(sector, sectorVO);
            // 查询该流程下的环节是否必须
            LambdaQueryWrapper<ProcessSector> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(ProcessSector::getProcessId, processId).eq(ProcessSector::getSectorId, sector.getId());
            sectorVO.setIsMust(processSectorService.getOne(queryWrapper).getIsMust());
            sectorVOList.add(sectorVO);
        });
        // 2.关联查询任务信息
        sectorVOList.forEach(sectorVO -> {
            List<Map<String, String>> tasksList = sectorMapper.listTaskSectorById(sectorVO.getId());
            List<Task> taskList = new ArrayList<>();
            tasksList.forEach(taskMap -> taskList.add(BeanUtil.mapToBean(taskMap, Task.class, true, CopyOptions.create())));
            sectorVO.setTaskList(taskList);
        });
        processVO.setSectorVOList(sectorVOList);
        return processVO;
    }
}




