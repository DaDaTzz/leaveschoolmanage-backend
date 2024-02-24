package com.yupi.springbootinit.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.springbootinit.model.dto.sector.AddSectorRequest;
import com.yupi.springbootinit.model.dto.sector.SectorQueryRequest;
import com.yupi.springbootinit.model.entity.Sector;
import com.yupi.springbootinit.model.vo.sector.SectorVO;

/**
 * 环节服务
 * @author Da
 */
public interface SectorService extends IService<Sector> {


    /**
     * 新增环节
     * @param addSectorRequest
     * @return
     */
    Boolean addSector(AddSectorRequest addSectorRequest);

    /**
     * 校验
     * @param sector
     * @param add
     */
    void validSector(Sector sector, boolean add);

    /**
     * 查询条件封装
     * @param sectorQueryRequest
     * @return
     */
    QueryWrapper getQueryWrapper(SectorQueryRequest sectorQueryRequest);

    /**
     * 获取环节封装
     * @param sector
     * @return
     */
    SectorVO getSectorVO(Sector sector);

}
