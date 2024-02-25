package com.yupi.springbootinit.model.vo.sector;

import com.yupi.springbootinit.model.entity.Sector;
import com.yupi.springbootinit.model.vo.task.TaskVO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 环节VO
 *
 * @author Da
 */
@Data
public class SectorVO implements Serializable {

    public static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 环节名称
     */
    private String name;

    /**
     * 环节描述
     */
    private String description;

    /**
     * 办理地点
     */
    private String doLocation;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 办理时间
     */
    private String doTime;

    /**
     * 办理提示
     */
    private String doTip;

    /**
     * 是否必须
     */
    private Integer isMust;

    /**
     * 任务列表
     */
    private List<TaskVO> taskVOList;

    /**
     * 完成时间
     */
    private Date completionTime;

    /**
     * 完成状态
     */
    private Integer completionStatus;



    /**
     * 对象转包装类
     *
     * @param sector
     * @return
     */
    public static SectorVO objToVo(Sector sector) {
        if (sector == null) {
            return null;
        }
        SectorVO sectorVO = new SectorVO();
        BeanUtils.copyProperties(sector, sectorVO);
        return sectorVO;
    }

    /**
     * 包装类转对象
     *
     * @param sectorVO
     * @return
     */
    public static Sector voToObj(SectorVO sectorVO) {
        if (sectorVO == null) {
            return null;
        }
        Sector sector = new Sector();
        BeanUtils.copyProperties(sectorVO, sector);
        return sector;
    }
}
