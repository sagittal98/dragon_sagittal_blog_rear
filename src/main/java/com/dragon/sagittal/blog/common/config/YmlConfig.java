package com.dragon.sagittal.blog.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Data
public class YmlConfig {

    /**
     * 数据中心
     */
    @Value("${yml.dataCenterId}")
    private  long dataCenterId;
    /**
     * 机器码
     */
    @Value("${yml.machineId}")
    private  long machineId;

}
