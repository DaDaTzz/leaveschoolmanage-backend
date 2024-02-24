package com.yupi.springbootinit.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.constant.CommonConstant;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.exception.ThrowUtils;
import com.yupi.springbootinit.mapper.SectorMapper;
import com.yupi.springbootinit.model.dto.sector.AddSectorRequest;
import com.yupi.springbootinit.model.dto.sector.SectorQueryRequest;
import com.yupi.springbootinit.model.dto.task.AddTaskRequest;
import com.yupi.springbootinit.model.entity.Sector;
import com.yupi.springbootinit.model.entity.SectorTask;
import com.yupi.springbootinit.model.entity.Task;
import com.yupi.springbootinit.model.vo.sector.SectorVO;
import com.yupi.springbootinit.service.SectorService;
import com.yupi.springbootinit.service.SectorTaskService;
import com.yupi.springbootinit.service.TaskService;
import com.yupi.springbootinit.utils.SqlUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 环节服务实现
 * @author Da
 */
@Service
public class SectorServiceImpl extends ServiceImpl<SectorMapper, Sector>
        implements SectorService {

    @Resource
    private TaskService taskService;
    @Resource
    private SectorTaskService sectorTaskService;
    @Resource
    private SectorMapper sectorMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addSector(AddSectorRequest addSectorRequest) {
        // 添加环节至少绑定一个任务
        List<AddTaskRequest> addTaskRequestList = addSectorRequest.getAddTaskRequestList();
        ThrowUtils.throwIf(addTaskRequestList.isEmpty(), new BusinessException(ErrorCode.PARAMS_ERROR, "环节至少绑定一个任务"));
        Sector sector = new Sector();
        BeanUtil.copyProperties(addSectorRequest, sector);
        // 校验环节参数
        validSector(sector, true);
        ArrayList<Task> addTaskList = new ArrayList<>();
        addTaskRequestList.forEach(addTaskRequest -> {
            Task task = new Task();
            BeanUtil.copyProperties(addTaskRequest, task);
            // 校验任务参数
            taskService.validTask(task, true);
            addTaskList.add(task);
        });
        // 插入数据
        boolean saveSectorResult = this.save(sector);
        boolean saveTasksResult = taskService.saveBatch(addTaskList);
        List<SectorTask> sectorTasList = new ArrayList<>();
        if (saveSectorResult && saveTasksResult) {
            Long newSectorId = sector.getId();
            addTaskList.forEach(newTask -> {
                Long newTaskId = newTask.getId();
                SectorTask sectorTask = new SectorTask();
                sectorTask.setSectorId(newSectorId);
                sectorTask.setTaskId(newTaskId);
                sectorTasList.add(sectorTask);
            });
            return sectorTaskService.saveBatch(sectorTasList);
        }
        return false;
    }

    @Override
    public void validSector(Sector sector, boolean add) {
        ThrowUtils.throwIf(sector == null, ErrorCode.PARAMS_ERROR, "环节为空");
        String name = sector.getName();
        String description = sector.getDescription();
        String doLocation = sector.getDoLocation();
        String phone = sector.getPhone();
        String doTip = sector.getDoTip();
        String doTime = sector.getDoTime();
        ThrowUtils.throwIf(StringUtils.isNotBlank(name) && name.length() > 255, ErrorCode.PARAMS_ERROR, "环节名称过长");
        ThrowUtils.throwIf(StringUtils.isNotBlank(description) && description.length() > 1000, ErrorCode.PARAMS_ERROR, "环节描述过长");
        ThrowUtils.throwIf(StringUtils.isNotBlank(doTip) && doTip.length() > 1000, ErrorCode.PARAMS_ERROR, "环节提示信息过长");
        ThrowUtils.throwIf(StringUtils.isNotBlank(doTime) && doTime.length() > 255, ErrorCode.PARAMS_ERROR, "环节办理时间错误");
        ThrowUtils.throwIf(StringUtils.isNotBlank(doLocation) && doLocation.length() > 255, ErrorCode.PARAMS_ERROR, "环节办理地点过长");
        ThrowUtils.throwIf(StringUtils.isNotBlank(phone) && phone.length() > 11, ErrorCode.PARAMS_ERROR, "环节联系方式过长");
        // 添加校验
        if (add) {
            // 1、参数不能为空
            ThrowUtils.throwIf(StringUtils.isAnyBlank(name, description, doLocation, phone, doTip) || doTime == null, ErrorCode.PARAMS_ERROR, "请求参数为空");
            // 2、环节名不能重复
            LambdaQueryWrapper<Sector> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Sector::getName, name);
            ThrowUtils.throwIf(this.getOne(queryWrapper) != null, ErrorCode.PARAMS_ERROR, "环节名称重复");
        }
    }


    @Override
    public QueryWrapper getQueryWrapper(SectorQueryRequest sectorQueryRequest) {
        QueryWrapper<Sector> queryWrapper = new QueryWrapper<>();
        if (sectorQueryRequest == null) {
            return queryWrapper;
        }
        String searchText = sectorQueryRequest.getSearchText();
        Long id = sectorQueryRequest.getId();
        String name = sectorQueryRequest.getName();
        String description = sectorQueryRequest.getDescription();
        String doLocation = sectorQueryRequest.getDoLocation();
        String phone = sectorQueryRequest.getPhone();
        String doTime = sectorQueryRequest.getDoTime();
        String doTip = sectorQueryRequest.getDoTip();
        String doWay = sectorQueryRequest.getDoWay();
        String sortField = sectorQueryRequest.getSortField();
        String sortOrder = sectorQueryRequest.getSortOrder();

        // 拼接查询条件
        if (org.apache.commons.lang3.StringUtils.isNotBlank(searchText)) {
            queryWrapper.and(qw -> qw.like("mc", searchText).or().like("ms", searchText));
        }
        queryWrapper.like(StringUtils.isNotBlank(name), "name", name);
        queryWrapper.like(StringUtils.isNotBlank(description), "description", description);
        queryWrapper.like(StringUtils.isNotBlank(doLocation), "doLocation", doLocation);
        queryWrapper.like(StringUtils.isNotBlank(doTime), "doTime", doTime);
        queryWrapper.like(StringUtils.isNotBlank(doTip), "doTip", doTip);
        queryWrapper.like(StringUtils.isNotBlank(doWay), "doWay", doWay);
        queryWrapper.eq(StringUtils.isNotBlank(phone), "phone", phone);
        queryWrapper.eq(id != null && id > 0, "id", id);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }

    @Override
    public SectorVO getSectorVO(Sector sector) {
        SectorVO sectorVO = SectorVO.objToVo(sector);
        Long sectorId = sector.getId();
        // 1. 关联查询任务信息
        List<Map<String, String>> tasksList = sectorMapper.listTaskSectorById(sectorId);
        List<Task> taskList = new ArrayList<>();
        tasksList.forEach(taskMap -> taskList.add(BeanUtil.mapToBean(taskMap, Task.class, true, CopyOptions.create())));
        sectorVO.setTaskList(taskList);
        return sectorVO;
    }

}




