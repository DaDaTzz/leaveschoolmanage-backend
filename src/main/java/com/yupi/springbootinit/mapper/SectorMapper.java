package com.yupi.springbootinit.mapper;

import com.yupi.springbootinit.model.entity.Sector;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

/**
 * 环节数据库操作
 * @author Da
 */
public interface SectorMapper extends BaseMapper<Sector> {

    /**
     *根据 id 获取 任务列表
     * @param sectorId
     */
    @MapKey("id")
    List<Map<String, String>> listTaskSectorById(Long sectorId);
}




