package com.lemonyliu.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "mybatis-plus")
public class MybatisPlusProperties {
    /**
     * 分页最大数
     */
    private Long pageLimit = 500L;
    /**
     * 溢出总页数后是否进行处理
     */
    protected Boolean overflow = false;
    /**
     * join优化
     */
    private Boolean optimizeJoin = false;
}