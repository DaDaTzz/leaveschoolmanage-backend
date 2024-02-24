package com.yupi.springbootinit.mapper;

import com.yupi.springbootinit.model.entity.Process;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

/**
 * 流程数据库操作
 * @author Da
 */
public interface ProcessMapper extends BaseMapper<Process> {

    /**
     *
     * 根据流程id查询环节信息
     * @param processId
     * @return
     */
    @MapKey("id")
    List<Map<String, String>> listSectorProcessById(Long processId);
}




