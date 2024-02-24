package com.yupi.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.model.entity.Speciality;
import com.yupi.springbootinit.service.SpecialityService;
import com.yupi.springbootinit.mapper.SpecialityMapper;
import org.springframework.stereotype.Service;

/**
 * 专业服务实现
 * @author Da
 */
@Service
public class SpecialityServiceImpl extends ServiceImpl<SpecialityMapper, Speciality>
    implements SpecialityService{

}




