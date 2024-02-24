package com.yupi.springbootinit.model.vo.process;

import com.yupi.springbootinit.model.entity.Process;
import com.yupi.springbootinit.model.vo.sector.SectorVO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 流程VO
 *
 * @author Da
 */
@Data
public class ProcessVO implements Serializable {

    public static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 流程名称
     */
    private String name;

    /**
     * 年度
     */
    private String year;

    /**
     * 描述
     */
    private String description;

    /**
     * 离校类型（0-正常离校 1-非正常离校）
     */
    private Integer leaveType;


    /**
     * 状态（0-关闭 1-开启）
     */
    private Integer status;


    /**
     * 环节VO列表
     */
    private List<SectorVO> sectorVOList;


    /**
     * 对象转包装类
     *
     * @param process
     * @return
     */
    public static ProcessVO objToVo(Process process) {
        if (process == null) {
            return null;
        }
        ProcessVO processVO = new ProcessVO();
        BeanUtils.copyProperties(process, processVO);
        return processVO;
    }

    /**
     * 包装类转对象
     *
     * @param processVO
     * @return
     */
    public static Process voToObj(ProcessVO processVO) {
        if (processVO == null) {
            return null;
        }
        Process process = new Process();
        BeanUtils.copyProperties(processVO, process);
        return process;
    }
}
