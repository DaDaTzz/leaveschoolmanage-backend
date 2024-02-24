package com.yupi.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.model.entity.College;
import com.yupi.springbootinit.service.CollegeService;
import com.yupi.springbootinit.mapper.CollegeMapper;
import org.springframework.stereotype.Service;

/**
 * 学院服务实现
 * @author Da
 */
@Service
public class CollegeServiceImpl extends ServiceImpl<CollegeMapper, College>
    implements CollegeService{

}




