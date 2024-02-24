package com.yupi.springbootinit.common;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 删除请求
 *
 * @author Da
 */
@Data
public class DeleteRequest implements Serializable {

    /**
     * id
     */
    private List<Long> idList;

    private static final long serialVersionUID = 1L;
}