package com.yupi.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.model.entity.Clazz;
import com.yupi.springbootinit.service.ClazzService;
import com.yupi.springbootinit.mapper.ClazzMapper;
import org.springframework.stereotype.Service;

/**
 * 班级服务实现
 * @author Da
 */
@Service
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper, Clazz>
        implements ClazzService {

}




