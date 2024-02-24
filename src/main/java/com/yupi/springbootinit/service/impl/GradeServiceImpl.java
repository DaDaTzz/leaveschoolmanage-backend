package com.yupi.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.model.entity.Grade;
import com.yupi.springbootinit.service.GradeService;
import com.yupi.springbootinit.mapper.GradeMapper;
import org.springframework.stereotype.Service;

/**
 * 年级服务实现
 * @author Da
 */
@Service
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade>
    implements GradeService{

}




