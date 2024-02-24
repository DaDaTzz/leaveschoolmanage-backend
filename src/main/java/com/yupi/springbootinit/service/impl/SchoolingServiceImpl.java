package com.yupi.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.model.entity.Schooling;
import com.yupi.springbootinit.service.SchoolingService;
import com.yupi.springbootinit.mapper.SchoolingMapper;
import org.springframework.stereotype.Service;

/**
 * 学历服务实现
 * @author Da
 */
@Service
public class SchoolingServiceImpl extends ServiceImpl<SchoolingMapper, Schooling>
    implements SchoolingService{

}




