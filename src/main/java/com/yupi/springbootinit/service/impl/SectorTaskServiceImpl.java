package com.yupi.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.model.entity.SectorTask;
import com.yupi.springbootinit.service.SectorTaskService;
import com.yupi.springbootinit.mapper.SectorTaskMapper;
import org.springframework.stereotype.Service;

/**
 * 环节任务关联服务实现
 * @author Da
 */
@Service
public class SectorTaskServiceImpl extends ServiceImpl<SectorTaskMapper, SectorTask>
    implements SectorTaskService{

}




